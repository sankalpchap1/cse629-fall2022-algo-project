package com.algoproject.algorithms;

import com.algoproject.model.Edge;
import com.algoproject.model.Graph;
import com.algoproject.model.Vertex;

import java.util.*;

public class Kruskal {
    static int maxBandwidth = 0;
    static List<Integer> maxBwPath = new ArrayList<>();

    public static int apply(Graph graph, int src, int dest, int noOfNodes) {
        Map<Integer, List<Vertex>> maxST = new HashMap<>();
        UnionFind omuf = new UnionFind(noOfNodes);
        maxST.clear();
        maxST = new HashMap<>(noOfNodes);

        List<Edge> edgeList = graph.getEdges();
        //sort the edges based on their weights
        omuf.sort(edgeList);

        //get MST by performing union and find operations on the edges
        for (int i = 0; i < noOfNodes; i++) {
            maxST.put(i, new LinkedList<>());
        }
        for (Edge edge : edgeList) {
            int r1 = omuf.find(edge.getVertex1());
            int r2 = omuf.find(edge.getVertex2());
            if (r1 != r2) {
                omuf.union(edge.getVertex1(), edge.getVertex2());
                maxST.get(edge.getVertex1()).add(new Vertex(edge.getVertex2(), edge.getEdgeWeight(), 1, null));
                maxST.get(edge.getVertex2()).add(new Vertex(edge.getVertex1(), edge.getEdgeWeight(), 1, null));
            }
        }

        //perfrom dfs on MST to get bw and path
        boolean[] isVisited = new boolean[noOfNodes];
        ArrayList<Integer> path = new ArrayList<>();
        path.add(src);
        maxBandwidth = 0;
        maxBwPath.clear();
        dfs(maxST, isVisited, src, dest, path, Integer.MAX_VALUE);
        System.out.println("Kruskal max bw : " + maxBandwidth);
//        System.out.print("max bw: " + maxBandwidth + "\npath: ");
//        int k = 1;
//        for (int i = maxBwPath.size() - 1; i > 0; i--) {
////            System.out.print(maxBwPath.get(i) + " <-- ");
//            //used to print output properly
//            if (k % 25 == 0) {
//                System.out.println();
//            }
//            k++;
//        }
////        System.out.println(maxBwPath.get(0));
        return maxBandwidth;
    }


    private static void dfs(Map<Integer, List<Vertex>> maxST ,boolean[] isVisited, int node, int dest, List<Integer> path, int bandwidth) {
        if (maxBwPath != null && !maxBwPath.isEmpty()) {
            return;
        }

        //get the bw and path when dest is found
        if (node == dest) {
            maxBwPath = new ArrayList<>(path);
            maxBandwidth = bandwidth;
            return;
        }

        //dfs recursive call on the neighbors
        isVisited[node] = true;
        List<Vertex> edges = maxST.get(node);
        for (Vertex edge : edges) {
            if (!isVisited[edge.getVertex()]) {
                path.add(edge.getVertex());
                dfs(maxST, isVisited, edge.getVertex(), dest, path, Math.min(bandwidth, edge.getEdgeWeight()));
                path.remove(path.size() - 1);
            }
        }
    }
}
