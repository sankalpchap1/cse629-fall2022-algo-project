package com.algoproject.algorithms;

import com.algoproject.model.Graph;
import com.algoproject.model.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kruskal {
    static int maximumBandwidth;
    static List<Integer> maximumBandwidthPath = new ArrayList<>();

    public static void apply(Graph graph, int source, int destination) {
        int n = graph.getNoOfNodes();
        boolean[] visited = new boolean[n];
        maximumBandwidth = 0;
        maximumBandwidthPath.clear();
        ArrayList<Integer> path = new ArrayList<>();
        path.add(source);
        dfs(source, destination, graph.getMaxSpanningTree(), visited, path, Integer.MAX_VALUE);
        System.out.println("Max BW from Kruskal's using Heap is: " + maximumBandwidth);

        System.out.print("s-t path: ");
        for (int i = maximumBandwidthPath.size() - 1; i > 0; i--) {
            System.out.print(maximumBandwidthPath.get(i) + " <–– ");
        }
        System.out.println(maximumBandwidthPath.get(0));
    }


    private static void dfs(int node, int destination,
                            Map<Integer, List<Vertex>> maxST, boolean[] visited,
                            List<Integer> path, int bandwidth) {
        if (maximumBandwidthPath != null && !maximumBandwidthPath.isEmpty()) {
            return;
        }

        if (node == destination) {
            maximumBandwidthPath = new ArrayList<>(path);
            maximumBandwidth = bandwidth;
            return;
        }

        visited[node] = true;
        maxST.get(node).forEach(edge -> {
            if (!visited[edge.getVertex()]) {
                // Standard backtracking template
                path.add(edge.getVertex());
                int nextNode = edge.getVertex();
                dfs(nextNode, destination, maxST, visited, path, Math.min(bandwidth, edge.getEdgeWeight()));
                path.remove(path.size() - 1);
            }
        });
    }
}
