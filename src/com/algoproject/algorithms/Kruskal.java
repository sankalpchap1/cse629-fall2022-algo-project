package com.algoproject.algorithms;

import com.algoproject.graph.GraphGeneration;
import com.algoproject.heap.EdgeHeap;
import com.algoproject.model.Edge;
import com.algoproject.model.Graph;
import com.algoproject.model.UnionFind;
import com.algoproject.model.Vertex;
import jdk.jfr.Description;

import java.util.List;

@Description("Implementation of Kruskal's Algorithms with using MaxHeap - EdgeHeap")
public class Kruskal {
    public static int apply(Graph graph, int source, int target, int n) {
        int[] dad = new int[n];
        UnionFind unionFind = new UnionFind(n);

        List<Edge> edgeList = graph.getEdges();
        EdgeHeap edgeHeap = new EdgeHeap(edgeList.size()+1);
        edgeList.forEach(edgeHeap::insert);

        Vertex[] mst = new Vertex[n];// To store the maximum spanning tree

        while (edgeHeap.getSize()>0) {
            Edge edge = edgeHeap.extractMax();
            int vertex1 = edge.getVertex1();
            int vertex2 = edge.getVertex2();

            int parent1 = unionFind.find(vertex1);
            int parent2 = unionFind.find(vertex2);

            if (parent1 != parent2) {
                unionFind.union(parent1, parent2);

                if (mst[vertex1] == null) {
                    mst[vertex1] = new Vertex(vertex2, edge.getEdgeWeight(), 1, null);
                } else {
                    mst[vertex1] = new Vertex(vertex2, edge.getEdgeWeight(),
                            mst[vertex1].getDegree() + 1,
                            mst[vertex1]);
                }

                if (mst[vertex2] == null) {
                    mst[vertex2] = new Vertex(vertex1, edge.getEdgeWeight(), 1, null);
                } else {
                    mst[vertex2] = new Vertex(vertex1, edge.getEdgeWeight(),
                            mst[vertex2].getDegree() + 1, mst[vertex2]);
                }
            }
        }

        boolean[] isVisited = new boolean[n];

        dfs(mst, source, isVisited, dad);
        int maxBw = Integer.MAX_VALUE;
//        System.out.print("s-t path: ");
        while (dad[target] != source) {
            String key = GraphGeneration.getEdge(target, dad[target]);
            maxBw = Math.min(graph.getEdgeMap().getOrDefault(key, Integer.MAX_VALUE), maxBw);
//            System.out.print(target + " <-- ");
            target = dad[target];
        }
//        System.out.println(target);
        System.out.println("The max bandwidth of graph with Kruskal is: " + maxBw);
        return maxBw;
    }

    public static void dfs(Vertex[] graph, int v, boolean[] visited, int[] dad) {
        visited[v] = true;
        Vertex vw = graph[v];
        while (vw != null) {
            if (!visited[vw.getVertex()]) {
                dad[vw.getVertex()] = v;
                dfs(graph, vw.getVertex(), visited, dad);
            }
            vw = vw.getNext();
        }
    }
}
