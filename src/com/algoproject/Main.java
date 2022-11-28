package com.algoproject;

import com.algoproject.algorithms.DijkstraWithHeap;
import com.algoproject.algorithms.DijkstraWithoutHeap;
import com.algoproject.algorithms.Kruskal;
import com.algoproject.model.Graph;

import java.util.concurrent.TimeUnit;

import static com.algoproject.graph.GraphGenerator.generateGraph;
import static com.algoproject.graph.GraphGenerator.getRandomSourceAndTarget;

public class Main {
    public static int noOfNodes = 5000; // No. of vertices
    public static int weightLimit = 20000; // wts for edges

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {

            System.out.println("===================Operation for graph 1===================");
            Graph graph1 = new Graph(noOfNodes);
            generateGraph(graph1, weightLimit, 5);
            System.out.println("------------Testing Graph1 for 5 s-t pairs------------");
            applyAlgorithms(graph1);

            System.out.println("===================Operation for graph 2===================");
            Graph graph2 = new Graph(noOfNodes);
            generateGraph(graph2, weightLimit, (int) Math.round(noOfNodes * 0.165));
            System.out.println("------------Testing Graph2 for 5 s-t pairs------------");
            applyAlgorithms(graph2);

            System.out.println("Ending here");
        }
    }

    private static void applyAlgorithms(Graph graph) {
        for (int j = 0; j < 5; j++) {
            int[] randomSourceTarget = getRandomSourceAndTarget(noOfNodes);
            int source = randomSourceTarget[0];
            int target = randomSourceTarget[1];
            System.out.println("source: " + source + "; target: " + target);

            long startTime = System.nanoTime();
            DijkstraWithoutHeap.apply(graph, source, target);
            long endTime = System.nanoTime();
            double timeElapsed = (endTime - startTime) / 1E6;
            System.out.println("TimeRequires for DijkstraWithoutHeap (in milliseconds): " + timeElapsed + "\n");

            startTime = System.nanoTime();
            DijkstraWithHeap.apply(graph, source, target);
            endTime = System.nanoTime();
            timeElapsed = (endTime - startTime) / 1E6;
            System.out.println("TimeRequires for DijkstraWithHeap (in milliseconds): " + timeElapsed + "\n");

            startTime = System.nanoTime();
            Kruskal.apply(graph, source, target);
            endTime = System.nanoTime();
            timeElapsed = (endTime - startTime)/1E6;
            System.out.println("TimeRequires for Kruskal (in millisecond): " + timeElapsed);
            System.out.println("==================================================================");
        }
    }
}
