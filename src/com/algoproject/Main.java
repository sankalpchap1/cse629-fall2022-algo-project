package com.algoproject;

import com.algoproject.algorithms.DijkstraWithHeap;
import com.algoproject.algorithms.DijkstraWithoutHeap;
import com.algoproject.algorithms.Kruskal;
import com.algoproject.model.Graph;

import static com.algoproject.graph.GraphGeneration.generateGraph;
import static com.algoproject.graph.GraphGeneration.getRandomSourceAndTarget;

public class Main {
    public static int noOfNodes = 5000; // No. of vertices
    public static int weightLimit = 20000; // wts for edges

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {

            System.out.println("===================Operation for graph 1===================");
            Graph graph1 = new Graph(noOfNodes);
            generateGraph(graph1, weightLimit, 5);

            System.out.println("Testing Graph1 for 5 s-t pairs");
            for (int j = 0; j < 5; j++) {
                int[] randomSourceTarget = getRandomSourceAndTarget(noOfNodes);
                int source = randomSourceTarget[0];
                int target = randomSourceTarget[1];
                System.out.println("source: " + source + "; target: " + target);

                long startTime = System.nanoTime();
                DijkstraWithoutHeap.apply(graph1, source, target);
                long endTime = System.nanoTime();
                long timeElapsed = (endTime - startTime) / 1000000;
                System.out.println("TimeRequires for DijkstraWithoutHeap (in milliseconds): " + timeElapsed + "\n");

                startTime = System.nanoTime();
                DijkstraWithHeap.apply(graph1, source, target);
                endTime = System.nanoTime();
                timeElapsed = (endTime - startTime) / 1000000;
                System.out.println("TimeRequires for DijkstraWithHeap (in milliseconds): " + timeElapsed + "\n");

                startTime = System.nanoTime();
                Kruskal.apply(graph1, source, target);
                endTime = System.nanoTime();
                timeElapsed = endTime - startTime;
                System.out.println("TimeRequires for Kruskal (in nanoseconds): " + timeElapsed);

                System.out.println("==================================================================");
            }

            System.out.println("===================Operation for graph 2===================");
            Graph graph2 = new Graph(noOfNodes);
            generateGraph(graph2, weightLimit, (int) Math.round(noOfNodes * 0.165));
            System.out.println("Testing Graph2 for 5 s-t pairs");
            for (int j = 0; j < 5; j++) {
                int[] randomSourceTarget = getRandomSourceAndTarget(noOfNodes);
                int source = randomSourceTarget[0];
                int target = randomSourceTarget[1];
                System.out.println("source: " + source + "; target: " + target);

                long startTime = System.nanoTime();
                DijkstraWithoutHeap.apply(graph2, source, target);
                long endTime = System.nanoTime();
                long timeElapsed = (endTime - startTime) / 1000000;
                System.out.println("TimeRequires for DijkstraWithoutHeap (in milliseconds): " + timeElapsed + "\n");

                startTime = System.nanoTime();
                DijkstraWithHeap.apply(graph2, source, target);
                endTime = System.nanoTime();
                timeElapsed = (endTime - startTime) / 1000000;
                System.out.println("TimeRequires for DijkstraWithHeap (in milliseconds): " + timeElapsed + "\n");

                startTime = System.nanoTime();
                Kruskal.apply(graph2, source, target);
                endTime = System.nanoTime();
                timeElapsed = endTime - startTime;
                System.out.println("TimeRequires for Kruskal (in nanoseconds): " + timeElapsed);

                System.out.println("==================================================================");
            }

            System.out.println("Ending here");
        }
    }
}
