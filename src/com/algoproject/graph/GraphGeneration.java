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
        List<Edge> edges = graph.getEdges();
        Map<String, Integer> edgeMap = graph.getEdgeMap();
        for (int i = 0; i < n; i++) {
            int randWeight = getRandomWeight(weightLimit);

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
    }

    public static void generate_sparse_graph(Graph graph, int n, int weightLimit) {
        int noOfEdges = n * 3, edges = 0;
        List<Edge> edgeList = graph.getEdges();
        Map<String, Integer> edgeMap = graph.getEdgeMap();

        //add edges to make cycle
        for (int i = 0; i < n; i++) {
            int weight = getRandomWeight(weightLimit);
            edgeList.add(new Edge(i, (i + 1) % n, weight));
            edgeMap.put(getEdge(i, (i + 1) % n), weight);
            if (graph.getVertices()[i] == null) {
                graph.getVertices()[i] = new Vertex((i + 1) % n, weight, 1, null);
            } else {
                graph.getVertices()[i].setNext(new Vertex((i + 1) % n, weight, 1, null));
            }
            if (graph.getVertices()[(i + 1) % n] == null) {
                graph.getVertices()[(i + 1) % n] = new Vertex(i % n, weight, 1, null);
            } else {
                graph.getVertices()[(i + 1) % n].setNext(new Vertex(i % n, weight, 1, null));
            }
            edges++;
        }

        //add edges randomly to get avg degree 6
        while (edges < noOfEdges) {
            int x = random.nextInt(n);
            int y = random.nextInt(n);
            if (x == y) {
                continue;
            }
            Vertex first = graph.getVertices()[x];
            while (first.getNext() != null && first.getVertex() != y) {
                first = first.getNext();
            }
            if (first.getNext() == null && first.getVertex() != y) {
                int weight = getRandomWeight(weightLimit);
                first.setNext(new Vertex(y, weight, 1, null));
                Vertex second = graph.getVertices()[y];
                while (second.getNext() != null) {
                    second = second.getNext();
                }
                second.setNext(new Vertex(x, weight, 1, null));
                edgeList.add(new Edge(x%n, y%n, weight));
                edgeMap.put(getEdge(x%n, y%n), weight);
                edges++;
            }
        }
    }

    public static void generate_dense_graph(Graph graph, int n, int weightLimit) {
        int minDegree = n / 5, maxDegree = n / 4;
        int[] degrees = new int[n];
        List<Edge> edgeList = graph.getEdges();
        Map<String, Integer> edgeMap = graph.getEdgeMap();

        //add edges to make cycle
        for (int i = 0; i < n; i++) {
            int weight = getRandomWeight(weightLimit);
            edgeList.add(new Edge(i, (i + 1) % n, weight));
            edgeMap.put(getEdge(i, (i + 1) % n), weight);
            if (graph.getVertices()[i] == null) {
                graph.getVertices()[i] = new Vertex((i + 1) % n, weight, 1, null);
//                        new ListNode(new int[]{(i + 1) % n, weight});
            } else {
                graph.getVertices()[i].setNext(new Vertex((i + 1) % n, weight, 1, null));
            }
            if (graph.getVertices()[(i + 1) % n] == null) {
                graph.getVertices()[(i + 1) % n] = new Vertex(i, weight,1 , null);
//                        new ListNode(new int[]{i, weight});
            } else {
                graph.getVertices()[(i + 1) % n].setNext(new Vertex(i, weight,1 , null));
            }
            degrees[i]++;
            degrees[(i + 1) % n]++;
        }

        //add edges randomly till each vertex is adjacent to about 20% of other vertices
        for (int x = 0; x < n; x++) {
            while (degrees[x] < minDegree) {
                int y = 0;
                while (true) {
                    y = random.nextInt(n);
                    if (x != y && degrees[y] < maxDegree) {
                        break;
                    }
                }
                Vertex first = graph.getVertices()[x];
                while (first.getNext() != null && first.getVertex() != y) {
                    first = first.getNext();
                }
                if (first.getNext() == null && first.getVertex() != y) {
                    int weight = getRandomWeight(weightLimit);
                    first.setNext(new Vertex(y, weight, 1, null));
                    Vertex second = graph.getVertices()[y];
                    while (second.getNext() != null) {
                        second = second.getNext();
                    }
                    second.setNext(new Vertex(x, weight, 1, null));
                    edgeList.add(new Edge(x%n, y%n, weight));
                    edgeMap.put(getEdge(x%n, y%n), weight);
                    degrees[x]++;
                    degrees[y]++;
                }
            }
        }
    }

    public static String getEdge(int v1, int v2) {
        if (v1 < v2) {
            return v1 + "-" + v2;
        }
        return v2 + "-" + v1;
    }

    public static void completeGraph(Graph graph, int n, int weightLimit, int targetDegree) {
        int destination; // random destination variable
        int weight; // random edge weight generator variable
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
                    edges.add(new Edge(i, destination, weight));
                    edgeMap.put(getEdge(i, (destination) % n), weight);
                    graph.getVertices()[i] = new Vertex(destination, weight,
                            graph.getVertices()[i].getDegree() + 1, graph.getVertices()[i]);
                    graph.getVertices()[destination] = new Vertex(i, weight,
                            graph.getVertices()[destination].getDegree() + 1, graph.getVertices()[destination]);
                    currDegree++;
                }
            }
        }
    }

    private static int getRandomWeight(int weightLimit) {
        return random.nextInt(weightLimit ) + 1;
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
