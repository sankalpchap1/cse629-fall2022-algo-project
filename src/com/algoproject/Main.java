package com.algoproject;

import com.algoproject.algorithms.DijkstraWithHeap;
import com.algoproject.algorithms.DijkstraWithoutHeap;
import com.algoproject.algorithms.Kruskal;
import com.algoproject.model.Graph;
import com.algoproject.model.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.algoproject.graph.GraphGeneration.completeGraph;
import static com.algoproject.graph.GraphGeneration.generateConnectedGraph;

public class Main {

    public static Random random = new Random(); // random number generator
    public static int n = 5000; // No. of vertices
    public static int weightLimit = 20000; // wts for edges
    public static void main(String[] args) {

        int counter = 0;
        int krusCounter = 0;

        for (int i=0; i<5; i++) {

            System.out.println("============Operation for graph 1=============");
//            Vertex[] graph1 = new Vertex[n];
            Graph graph1 = new Graph(new Vertex[n], new ArrayList<>(), new HashMap<>());
            generateConnectedGraph(graph1, n, weightLimit);
//            completeGraph(graph1, n, weightLimit, 5);

            System.out.println("Testing Graph1 for 5 s-t pairs");
            for (int j = 0; j<5; j++){
                int s = random.nextInt(n);
                int t = s;
                while (s == t){
                    t = random.nextInt(n);
                }
                System.out.println("s: " + s + ";t: " + t);
                long start_time = System.nanoTime();
                int djWithoutBW = DijkstraWithoutHeap.apply(graph1, s, t, n);
                long end_time = System.nanoTime();
                long time_req = (end_time-start_time)/1000000;
                System.out.println("TimeRequires for DijkstraWithoutHeap: "+time_req);

                start_time = System.nanoTime();
                int djWithBW = DijkstraWithHeap.apply(graph1, s, t, n);
                end_time = System.nanoTime();
                time_req = (end_time-start_time)/1000000;
                System.out.println("TimeRequires for DijkstraWithHeap: "+time_req);

                start_time = System.nanoTime();
                int kruskalBW = Kruskal.apply(graph1, s, t, n);
                end_time = System.nanoTime();
                time_req = (end_time-start_time)/1000000;
                System.out.println("TimeRequires for Kruskal: "+time_req);
                if (djWithoutBW!=djWithBW) {
                    counter++;
                    System.out.println("graph 1 COUNTER::"+counter);
                }
                if (djWithoutBW!=kruskalBW){
                    krusCounter++;
                    System.out.println("krusCnt::"+krusCounter);
                }

                System.out.println("============================================");
            }

            System.out.println("============Operation for graph 2===============");
//            Vertex[] graph2 = new Vertex[n];
            Graph graph2 = new Graph(new Vertex[n], new ArrayList<>(), new HashMap<>());
            generateConnectedGraph(graph2, n, weightLimit);
            completeGraph(graph2, n, weightLimit, (int) Math.round(n * 0.165));

            System.out.println("Testing Graph2 for 5 s-t pairs");
            for (int j = 0; j<5; j++){
                int s = random.nextInt(n);
                int t = s;
                while (s == t){
                    t = random.nextInt(n);
                }
                System.out.println("s: " + s + ";t: " + t);

                long start_time = System.nanoTime();
                int djWithoutBW = DijkstraWithoutHeap.apply(graph2, s, t, n);
                long end_time = System.nanoTime();
                long time_req = (end_time-start_time)/1000000;
                System.out.println("TimeRequires for DijkstraWithoutHeap: "+time_req);

                start_time = System.nanoTime();
                int djWithBW = DijkstraWithHeap.apply(graph2, s, t, n);
                end_time = System.nanoTime();
                time_req = (end_time-start_time)/1000000;
                System.out.println("TimeRequires for DijkstraWithHeap: "+time_req);

                start_time = System.nanoTime();
                int kruskalBW = Kruskal.apply(graph2, s, t, n);
                end_time = System.nanoTime();
                time_req = (end_time-start_time)/1000000;
                System.out.println("TimeRequires for Kruskal: "+time_req);
                if (djWithoutBW!=djWithBW) {
                    counter++;
                    System.out.println("graph 2 COUNTER::"+counter);
                }

                if (djWithoutBW!=kruskalBW){
                    krusCounter++;
                    System.out.println("krusCnt::"+krusCounter);
                }

                System.out.println("============================================");
            }

            System.out.println("Ending here");

//        System.out.println("============Operation for graph 3===============");
//        // Connect each node to its next node for graph2
//        int n3 = 6;
//        int s3 = random.nextInt(n3);
//        int t3 = s3;
//        while (s3 == t3)
//            t3 = random.nextInt(n3);
//        Vertex[] graph3 = new Vertex[n3];
//        // Connect each node to its next node graph1
//        generateConnectedGraph(graph3, n3, weightLimit);
//        // Now complete other edges so that we get the avg degree = 6
//        completeGraph(graph3, n3, weightLimit, 3);
//        printAdjMatrix(graph3, n3);
//
//        testGraph(graph3);
//        DijkstraWithoutHeap.apply(graph3, s3, t3, n3);
//        testGraph(graph3);
//        DijkstraWithHeap.apply(graph3, s3, t3, n3);
//        System.out.println("Ending here");
        }
        System.out.println("FINAL COUNTER: "+counter);
        System.out.println("FINAL Kruskal COUNTER: "+krusCounter);
    }

    private static void printAdjMatrix(Vertex[] graph, int n){
        int[][] matrix = new int[n][n];

        for (int i=0; i<n; i++){
            Vertex temp = graph[i];
            while (temp!=null){
                matrix[i][temp.getVertex()] = 1;
                temp = temp.getNext();
            }
        }

        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("\n");
        }

    }

    private static void testGraph(Graph graph){
        System.out.println("start testing");
        // calculate the avg degree in the graph
        // Printing the adj list of 0th vertex in graph
//        Vertex temp = graph[0];
//        while (temp!=null){
//            System.out.print(temp.getVertex()+ " ");
//            temp = temp.getNext();
//        }
        System.out.println("\nEnd testing");
    }
}
