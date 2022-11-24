package com.algoproject;

import com.algoproject.algorithms.DijkstraWithHeap;
import com.algoproject.algorithms.DijkstraWithoutHeap;
import com.algoproject.algorithms.Kruskal;
import com.algoproject.model.Vertex;

import java.util.Random;

import static com.algoproject.graph.GraphGeneration.completeGraph;
import static com.algoproject.graph.GraphGeneration.generateConnectedGraph;

public class Main {

    public static Random random = new Random(); // random number generator
    public static int n = 5000; // No. of vertices
    public static int weightLimit = 20000; // wts for edges
    public static void main(String[] args) {


        for (int i=0; i<5; i++){
            int s = random.nextInt(n);
            int t = s;
            while (s == t)
                t = random.nextInt(n);

            System.out.println("============Operation for graph 1=============");
            Vertex[] graph1 = new Vertex[n];
            // Connect each node to its next node graph1
            generateConnectedGraph(graph1, n, weightLimit);
            // Now complete other edges so that we get the avg degree = 6
            completeGraph(graph1, n, weightLimit, 5);
//        testGraph(graph1);

            System.out.println("s: " + s + ";t: " + t);
            DijkstraWithoutHeap.apply(graph1, s, t, n);
//        testGraph(graph1);
            DijkstraWithHeap.apply(graph1, s, t, n);
            Kruskal.apply(graph1, s, t, n);

            System.out.println("============Operation for graph 2===============");
            // Connect each node to its next node for graph2
            Vertex[] graph2 = new Vertex[n];
            // Connect each node to its next node graph1
            generateConnectedGraph(graph2, n, weightLimit);
            completeGraph(graph2, n, weightLimit, (int) Math.round(n * 0.165));

//        testGraph(graph2);
            DijkstraWithoutHeap.apply(graph2, s, t, n);
//        testGraph(graph2);
            DijkstraWithHeap.apply(graph2, s, t, n);

            Kruskal.apply(graph2, s, t, n);

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

    private static void testGraph(Vertex[] graph){
        System.out.println("start testing");
        // calculate the avg degree in the graph
        int sum = 0;
        for (int i=0; i<graph.length; i++){
            sum+=graph[i].getEdgeWeight();
//            System.out.println(i+ " "+graph[i].getDegree());
        }
        System.out.println("Avg edge wt of graph: "+sum);
        // Printing the adj list of 0th vertex in graph
//        Vertex temp = graph[0];
//        while (temp!=null){
//            System.out.print(temp.getVertex()+ " ");
//            temp = temp.getNext();
//        }
        System.out.println("\nEnd testing");
    }
}
