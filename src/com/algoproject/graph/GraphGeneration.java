package com.algoproject.graph;

import com.algoproject.model.Vertex;

import java.util.Random;

public class GraphGeneration {
    public static Random random = new Random(); // random number generator
    //	Generate a connected graph -> Each vertex is connected to it's next vertex forming a circular graph
    public static void generateConnectedGraph(Vertex[] graph, int n, int weightLimit) {
//        long countOfMaxWeight = 0;
//        long totalEdge = 0;
        for (int i=0; i<n; i++){
            int randWeight = getRandomWeight(weightLimit);
//            if (randWeight == weightLimit){
//                countOfMaxWeight++;
//            }
//            totalEdge++;
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
//        System.out.println("countOfMaxWeight: "+countOfMaxWeight);
//        System.out.println("totalEdge: "+totalEdge);
    }

    public static void completeGraph(Vertex[] graph, int n, int weightLimit, int targetDegree) {
        int destination; // random destination variable
        int weight; // random edge weight generator variable
//        long countOfMaxWeight = 0;
//        long totalEdge = 0;
        for (int i = 0; i < n; i++) {

            int currDegree = graph[i].getDegree();
            while (currDegree < targetDegree) {
                destination = random.nextInt(n);

                if (!checkAdjList(graph[i], destination)
                        && i != destination
                ) {
                    weight = getRandomWeight(weightLimit);
//                    if (weight == weightLimit){
//                        countOfMaxWeight++;
//                    }
//                    totalEdge++;
                    graph[i] = new Vertex(destination, weight, graph[i].getDegree() + 1, graph[i]);
                    graph[destination] = new Vertex(i, weight, graph[destination].getDegree() + 1, graph[destination]);
                    currDegree++;
                }
            }
        }
//        System.out.println("countOfMaxWeight: "+countOfMaxWeight);
//        System.out.println("totalEdge: "+totalEdge);
    }

    private static int getRandomWeight(int weightLimit) {
        return random.nextInt(weightLimit + 1 - weightLimit / 2) + weightLimit / 2;
    }

    private static boolean checkAdjList(Vertex vertex, int destination) {
        while (vertex != null) {
            if (vertex.getVertex() == destination){
                return true;
            } else {
                vertex = vertex.getNext();
            }
        }
        return false;
    }
}
