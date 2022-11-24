package com.algoproject.algorithms;

import com.algoproject.model.Edge;
import com.algoproject.model.UnionFind;
import com.algoproject.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {
    public static void apply(Vertex[] graph, int source, int target, int n){
        int[] dadMaxSpan = new int[n];
        UnionFind unionFind = new UnionFind(n);

        List<Edge> edgeList = getEdgesGraph(graph);
        EdgeHeap edgeHeap = new EdgeHeap(edgeList);

//		Collections.sort(edgeList, (e1, e2) -> (e2.edgeWt - e1.edgeWt));

        Vertex[] maxSpanningTree = new Vertex[n];

        while (!edgeHeap.maxHeap.isEmpty()) {
            Edge edge = edgeHeap.popMax();
            int v1 = edge.getVertex1();
            int v2 = edge.getVertex2();

            int r1 = unionFind.find(v1);
            int r2 = unionFind.find(v2);

            if (r1 != r2) {
                unionFind.union(r1, r2);

                if (maxSpanningTree[v1] == null) {
                    maxSpanningTree[v1] = new Vertex((v2) % n, edge.getEdgeWeight(), 1, null);
                } else {
                    maxSpanningTree[v1] = new Vertex((v2) % n, edge.getEdgeWeight(), maxSpanningTree[v1].getDegree() + 1,
                            maxSpanningTree[v1]);
                }

                if (maxSpanningTree[v2] == null) {
                    maxSpanningTree[v2] = new Vertex((v1) % n, edge.getEdgeWeight(), 1, null);
                } else {
                    maxSpanningTree[v2] = new Vertex((v1) % n, edge.getEdgeWeight(), maxSpanningTree[v2].getDegree() + 1,
                            maxSpanningTree[v2]);
                }
            }
        }

        boolean[] isVisited = new boolean[n];

        dfs(maxSpanningTree, source, isVisited, dadMaxSpan);
        int maxBw = Integer.MAX_VALUE;
        System.out.print("s-t path: ");
        while (dadMaxSpan[target] != source) {
            maxBw = Math.min(getEdgeWeight(target, dadMaxSpan[target], graph), maxBw);
//            System.out.print(target + " <-- ");
            target = dadMaxSpan[target];
        }
        System.out.println(target);
        System.out.println("The max bandwidth of graph with Kruskal is: " + maxBw);
    }

    public static List<Edge> getEdgesGraph(Vertex[] graph) {
        int n = graph.length;
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Vertex vw = graph[i];
            while (vw != null) {
                int v = i;
                int w = vw.getVertex();
                int e = vw.getEdgeWeight();
                edgeList.add(new Edge(v, w, e));
                vw = vw.getNext();
            }
        }
        return edgeList;
    }

    public static int getEdgeWeight(int v1, int v2, Vertex[] graph) {
        Vertex vw = graph[v1];
        while (vw != null) {
            if (vw.getVertex() == v2) {
//				System.out.println(v1 + "-" + vw.vertex + "<->" + vw.edgeWt);
                return vw.getEdgeWeight();
            }
            vw = vw.getNext();
        }
        return Integer.MAX_VALUE;
    }

    public static void dfs(Vertex[] graph, int v, boolean[] visited, int[] dadMaxSpan) {
        visited[v] = true;
        Vertex vw = graph[v];
        while (vw != null) {
            if (!visited[vw.getVertex()]) {
                dadMaxSpan[vw.getVertex()] = v;
                dfs(graph, vw.getVertex(), visited, dadMaxSpan);
            }
            vw = vw.getNext();
        }
//		System.out.print(v + "-->");
//		visited[v] = true;
    }
}
