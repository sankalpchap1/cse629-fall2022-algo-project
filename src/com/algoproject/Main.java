package com.algoproject;

import com.algoproject.model.Vertex;

import java.util.Random;

public class Main {

    public static int n = 5000; // No. of vertices
    public static int weightLimit = 100; // wts for edges
    public static Random random = new Random(); // random number generator
    public static void main(String[] args) {

        Vertex[] graph1 = new Vertex[n];
        // Connect each node to its next node graph1
        generateConnectedGraph(graph1, n, weightLimit);
        // Now complete other edges so that we get the avg degree = 6
        completeGraph(graph1, n, weightLimit, 6);
        testGraph(graph1);

        // Connect each node to its next node for graph2
        Vertex[] graph2 = new Vertex[n];
        // Connect each node to its next node graph1
        generateConnectedGraph(graph2, n, weightLimit);
        // Now complete other edges so that we get the avg degree = 6
        completeGraph(graph2, n, weightLimit, (int) Math.round(n*0.2));
        testGraph(graph2);
    }

    private static void testGraph(Vertex[] graph){
        System.out.println("start testing");
        int sum = 0;
        for (int i=0; i<graph.length; i++){
            sum+=graph[i].getDegree();
//            System.out.println(i+ " "+graph[i].getDegree());
        }
        System.out.println((float)sum/n);
//        Vertex temp = graph[0];
//        while (temp!=null){
//            System.out.println(temp.getVertex()+ " "+ temp.getEdgeWeight());
//            temp = temp.getNext();
//        }
        System.out.println("End testing");
    }

    //	Generate a connected graph -> Each vertex is connected to it's next vertex forming a circular graph
    private static void generateConnectedGraph(Vertex[] graph, int n, int weightLimit) {
        for (int i=0; i<n; i++){
            int randWeight = random.nextInt(weightLimit + 1 - weightLimit / 2) + weightLimit / 2;

            // Adding i+1 th vertex in i's adj list
            if (graph[i] == null) {
                graph[i] = new Vertex((i + 1) % n, randWeight, 1, null);
            } else {
                Vertex newVertex = new Vertex((i + 1) % n, randWeight, graph[i].getDegree() + 1, graph[i]);
                graph[i] = newVertex;
            }

            // Now add the ith vertex into (i+1)'s adj list
            if (graph[(i + 1) % n] == null) {
                graph[(i + 1) % n] = new Vertex(i % n, randWeight, 1, null);
            } else {
                Vertex newVertex = new Vertex(i % n, randWeight, graph[(i + 1) % n].getDegree()+1, graph[(i + 1) % n]);
                graph[(i + 1) % n] = newVertex;
            }
        }
    }

    private static void completeGraph(Vertex[] graph, int n, int weightLimit, int targetDegree) {
        int destination; // random destination variable
        int weight; // random edge weight generator variable

        for (int i = 0; i < n; i++) {

            int currDegree = graph[i].getDegree();
            while (currDegree < targetDegree) {
                // Pick a destination randomly from the set
                destination = random.nextInt(n);

                if (!checkAdjList(graph[i], destination)
                        && i != destination
                ) {
                    weight = random.nextInt(weightLimit + 1 - weightLimit / 2) + weightLimit / 2;
                    graph[i] = new Vertex(destination, weight, graph[i].getDegree() + 1, graph[i]);
                    graph[destination] = new Vertex(i, weight, graph[destination].getDegree() + 1, graph[destination]);
                    currDegree++;
                }
            }
        }
    }

    private static boolean checkAdjList(Vertex vertex, int destination) {
        while (vertex != null) {
            if (vertex.getVertex() == destination)
                return true;
            vertex = vertex.getNext();
        }
        return false;
    }

}
