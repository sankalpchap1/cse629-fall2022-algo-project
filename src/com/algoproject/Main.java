package com.algoproject;

import com.algoproject.algorithms.DijkstraWithHeap;
import com.algoproject.algorithms.DijkstraWithoutHeap;
import com.algoproject.algorithms.Kruskal;
import com.algoproject.model.Graph;
import com.algoproject.model.Vertex;

import java.util.ArrayList;
import java.util.HashMap;

import static com.algoproject.graph.GraphGeneration.generateGraph;
import static com.algoproject.graph.GraphGeneration.getRandomSourceAndTarget;

public class Main {
    public static int n = 5000; // No. of vertices
    public static int weightLimit = 20000; // wts for edges

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {

            System.out.println("===================Operation for graph 1===================");
            Graph graph1 = new Graph(new Vertex[n], new ArrayList<>(), new HashMap<>(), new HashMap<>(n));
            generateGraph(graph1, n, weightLimit, 5);

            System.out.println("Testing Graph1 for 5 s-t pairs");
            for (int j = 0; j < 5; j++) {
                int[] randomSourceTarget = getRandomSourceAndTarget(n);
                int s = randomSourceTarget[0];
                int t = randomSourceTarget[1];
                System.out.println("s: " + s + ";t: " + t);

                long start_time = System.nanoTime();
                DijkstraWithoutHeap.apply(graph1, s, t, n);
                long end_time = System.nanoTime();
                long time_req = (end_time - start_time) / 1000000;
                System.out.println("TimeRequires for DijkstraWithoutHeap (in milliseconds): " + time_req + "\n");

                start_time = System.nanoTime();
                DijkstraWithHeap.apply(graph1, s, t, n);
                end_time = System.nanoTime();
                time_req = (end_time - start_time) / 1000000;
                System.out.println("TimeRequires for DijkstraWithHeap (in milliseconds): " + time_req + "\n");

                start_time = System.nanoTime();
                Kruskal.apply(graph1, s, t, n);
                end_time = System.nanoTime();
                time_req = end_time - start_time;
                System.out.println("TimeRequires for Kruskal (in nanoseconds): " + time_req);

                System.out.println("==================================================================");
            }

            System.out.println("===================Operation for graph 2===================");
            Graph graph2 = new Graph(new Vertex[n], new ArrayList<>(), new HashMap<>(), new HashMap<>(n));
            generateGraph(graph2, n, weightLimit, (int) Math.round(n * 0.165));
            System.out.println("Testing Graph2 for 5 s-t pairs");
            for (int j = 0; j < 5; j++) {
                int[] randomSourceTarget = getRandomSourceAndTarget(n);
                int s = randomSourceTarget[0];
                int t = randomSourceTarget[1];
                System.out.println("s: " + s + ";t: " + t);

                long start_time = System.nanoTime();
                DijkstraWithoutHeap.apply(graph2, s, t, n);
                long end_time = System.nanoTime();
                long time_req = (end_time - start_time) / 1000000;
                System.out.println("TimeRequires for DijkstraWithoutHeap (in milliseconds): " + time_req + "\n");

                start_time = System.nanoTime();
                DijkstraWithHeap.apply(graph2, s, t, n);
                end_time = System.nanoTime();
                time_req = (end_time - start_time) / 1000000;
                System.out.println("TimeRequires for DijkstraWithHeap (in milliseconds): " + time_req + "\n");

                start_time = System.nanoTime();
                Kruskal.apply(graph2, s, t, n);
                end_time = System.nanoTime();
                time_req = end_time - start_time;
                System.out.println("TimeRequires for Kruskal (in nanoseconds): " + time_req);

                System.out.println("==================================================================");
            }

            System.out.println("Ending here");
        }
    }
}
