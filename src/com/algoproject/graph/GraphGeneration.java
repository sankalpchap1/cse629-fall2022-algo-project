package com.algoproject.graph;

import com.algoproject.model.Edge;
import com.algoproject.model.Graph;
import com.algoproject.model.Vertex;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GraphGeneration {
    public static Random random = new Random(); // random number generator

    //	Generate a connected graph -> Each vertex is connected to it's next vertex forming a circular graph
    public static void generateConnectedGraph(Graph graph, int n, int weightLimit) {
//        long countOfMaxWeight = 0;
//        long totalEdge = 0;
        List<Edge> edges = graph.getEdges();
        Map<String, Integer> edgeMap = graph.getEdgeMap();
        for (int i = 0; i < n; i++) {
            int randWeight = getRandomWeight(weightLimit);
//            if (randWeight == weightLimit){
//                countOfMaxWeight++;
//            }
//            totalEdge++;
            // Adding i+1 th vertex in i's adj list
            edges.add(new Edge(i, (i + 1) % n, randWeight));
            edgeMap.put(getEdge(i, (i + 1) % n), randWeight);
            if (graph.getVertices()[i] == null) {
                graph.getVertices()[i] = new Vertex((i + 1) % n, randWeight, 1, null);
            } else {
                Vertex newVertex = new Vertex((i + 1) % n, randWeight,
                        graph.getVertices()[i].getDegree() + 1,
                        graph.getVertices()[i]);
                graph.getVertices()[i] = newVertex;
            }

            // Now add the ith vertex into (i+1)'s adj list
            if (graph.getVertices()[(i + 1) % n] == null) {
                graph.getVertices()[(i + 1) % n] = new Vertex(i % n, randWeight, 1, null);
            } else {
                Vertex newVertex = new Vertex(i % n, randWeight,
                        graph.getVertices()[(i + 1) % n].getDegree() + 1,
                        graph.getVertices()[(i + 1) % n]);
                graph.getVertices()[(i + 1) % n] = newVertex;
            }
        }
//        System.out.println("countOfMaxWeight: "+countOfMaxWeight);
//        System.out.println("totalEdge: "+totalEdge);
    }

    public static String getEdge(int v1, int v2) {
        if (v1<v2){
            return v1+"-"+v2;
        }
        return v2+"-"+v1;
    }

    public static void completeGraph(Graph graph, int n, int weightLimit, int targetDegree) {
        int destination; // random destination variable
        int weight; // random edge weight generator variable
//        long countOfMaxWeight = 0;
//        long totalEdge = 0;
        List<Edge> edges = graph.getEdges();
        Map<String, Integer> edgeMap = graph.getEdgeMap();
        for (int i = 0; i < n; i++) {

            int currDegree = graph.getVertices()[i].getDegree();
            while (currDegree < targetDegree) {
                destination = random.nextInt(n);

                if (!checkAdjList(graph.getVertices()[i], destination)
                        && i != destination
                ) {
                    weight = getRandomWeight(weightLimit);
//                    if (weight == weightLimit){
//                        countOfMaxWeight++;
//                    }
//                    totalEdge++;
                    edges.add(new Edge(i, destination, weight));
                    edgeMap.put(getEdge(i, (destination)%n), weight);
                    graph.getVertices()[i] = new Vertex(destination, weight,
                            graph.getVertices()[i].getDegree() + 1, graph.getVertices()[i]);
                    graph.getVertices()[destination] = new Vertex(i, weight,
                            graph.getVertices()[destination].getDegree() + 1, graph.getVertices()[destination]);
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
            if (vertex.getVertex() == destination) {
                return true;
            } else {
                vertex = vertex.getNext();
            }
        }
        return false;
    }
}
