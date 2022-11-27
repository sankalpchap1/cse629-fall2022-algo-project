package com.algoproject.algorithms;

import com.algoproject.model.Graph;
import com.algoproject.model.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kruskal {
    static int maxBandwidth = 0;
    static List<Integer> maxBwPath = new ArrayList<>();

    public static void apply(Graph graph, int src, int dest, int n) {
        boolean[] isVisited = new boolean[n];
        ArrayList<Integer> path = new ArrayList<>();
        path.add(src);
        maxBandwidth = 0;
        maxBwPath.clear();
        dfs(graph.getMaxSpanningTree(), isVisited, src, dest, path, Integer.MAX_VALUE);
        System.out.println("Max BW from Kruskal's using Heap is: " + maxBandwidth);

        System.out.print("s-t path: ");
        for (int i = maxBwPath.size() - 1; i > 0; i--) {
            System.out.print(maxBwPath.get(i) + " <–– ");
        }
        System.out.println(maxBwPath.get(0));
    }


    private static void dfs(Map<Integer, List<Vertex>> maxST, boolean[] isVisited, int node, int dest, List<Integer> path, int bandwidth) {
        if (maxBwPath != null && !maxBwPath.isEmpty()) {
            return;
        }

        if (node == dest) {
            maxBwPath = new ArrayList<>(path);
            maxBandwidth = bandwidth;
            return;
        }

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
