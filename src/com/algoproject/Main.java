package com.algoproject;

import com.algoproject.algorithms.DijkstraWithHeap;
import com.algoproject.algorithms.DijkstraWithoutHeap;
import com.algoproject.model.Vertex;

import java.util.Random;

import static com.algoproject.graph.GraphGeneration.completeGraph;
import static com.algoproject.graph.GraphGeneration.generateConnectedGraph;

public class Main {

    public static Random random = new Random(); // random number generator
    public static int n = 5000; // No. of vertices
    public static int weightLimit = 100; // wts for edges
    public static void main(String[] args) {

        Vertex[] graph1 = new Vertex[n];
        // Connect each node to its next node graph1
        generateConnectedGraph(graph1, n, weightLimit);
        // Now complete other edges so that we get the avg degree = 6
        completeGraph(graph1, n, weightLimit, 6);
//        testGraph(graph1);
        int s = random.nextInt(n);
        int t = s;
        while (s == t)
            t = random.nextInt(n);
        System.out.println("s: " + s + ";t: " + t);
        DijkstraWithoutHeap.apply(graph1, s, t, n);

        DijkstraWithHeap.apply(graph1, s, t, n);

        // Connect each node to its next node for graph2
        Vertex[] graph2 = new Vertex[n];
        // Connect each node to its next node graph1
        generateConnectedGraph(graph2, n, weightLimit);
        // Now complete other edges so that we get the avg degree = 6
        completeGraph(graph2, n, weightLimit, (int) Math.round(n*0.2));
//        testGraph(graph2);
        DijkstraWithoutHeap.apply(graph2, s, t, n);

        DijkstraWithHeap.apply(graph2, s, t, n);
        System.out.println("Ending here");
    }

    private static void testGraph(Vertex[] graph){
        System.out.println("start testing");
        // calculate the avg degree in the graph
        int sum = 0;
        for (int i=0; i<graph.length; i++){
            sum+=graph[i].getDegree();
//            System.out.println(i+ " "+graph[i].getDegree());
        }
        System.out.println("Avg degree of graph: "+(float)sum/n);
        // Printing the adj list of 0th vertex in graph
        Vertex temp = graph[0];
        while (temp!=null){
            System.out.print(temp.getVertex()+ " ");
            temp = temp.getNext();
        }
        System.out.println("\nEnd testing");
    }



}
