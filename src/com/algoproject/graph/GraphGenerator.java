package com.algoproject.graph;

import com.algoproject.heap.UnionFind;
import com.algoproject.model.Edge;
import com.algoproject.model.Graph;
import com.algoproject.model.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.algoproject.model.GraphType.*;

public class GraphGenerator {
    private static final Random random = new Random(); // random number generator

    public static void generateGraph(Graph graph, int weightLimit, int targetDegree) {
        System.out.println("------------Generating graph------------");
        long startTime = System.nanoTime();
        generateConnectedGraph(graph, weightLimit);
        if (graph.getType() == SPARSE) {
            completeSparseGraph(graph, weightLimit, targetDegree);
        } else {
            completeDenseGraph(graph, weightLimit, targetDegree);
        }
        long endTime = System.nanoTime();
        double timeElapsed = (endTime - startTime) / 1E6;
        System.out.println("TimeRequired for generatingGraph (in milliseconds): " + timeElapsed + "\n");

        System.out.println("------------Generating MST------------");
        startTime = System.nanoTime();
        generateMST(graph);
        endTime = System.nanoTime();
        timeElapsed = (endTime - startTime) / 1E6;
        System.out.println("TimeRequired for generatingMST (in milliseconds): " + timeElapsed + "\n");
    }

    public static void calculateAvgDegree(final Graph graph) {
        System.out.println("------------Calculating Avg Degree------------");
        double avgDegree = 2 * ((double) graph.getEdges().size()) / graph.getNoOfNodes();
        System.out.println("Avg Degree for this graph is: " + avgDegree + "\n");
    }

    public static void calculateDegreePercentage(final Graph graph) {
        System.out.println("------------Calculating neighbour percentage------------");
        int n = graph.getNoOfNodes();
        double avgDegree = 2 * ((double) graph.getEdges().size()) / n;
        double percentageConnection = 100.0 * avgDegree / n;

        System.out.println("Each node is connected to approx: " + percentageConnection + "% of adjacent vertices\n");
    }

    //	Generate a connected graph -> Each vertex is connected to it's next vertex forming a circular graph
    private static void generateConnectedGraph(Graph graph, int weightLimit) {
        int n = graph.getNoOfNodes();
        List<Edge> edges = graph.getEdges();
        for (int i = 0; i < n; i++) {
            int randWeight = getRandomWeight(weightLimit);

            // Adding i+1 th vertex in i's adj list
            edges.add(new Edge(i, (i + 1) % n, randWeight));
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

    /**
     * @param graph        : container for the dense graph
     * @param weightLimit  : max Weight assigned for the edges -> weights are generated from randomWeightGenerator
     * @param targetDegree : avg degree which is to be achieved for dense graph
     */
    public static void completeSparseGraph(Graph graph, int weightLimit, int targetDegree) {
        int n = graph.getNoOfNodes();
        Vertex[] vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        int avgEdgeSum = n * targetDegree / 2;
        int currEdgeSum = n;

        while (currEdgeSum < avgEdgeSum) {
            int source = random.nextInt(n);
            int destination = random.nextInt(n);

            if (checkAdjList(vertices[source], destination) && source != destination) {
                // random edge weight generator variable
                int weight = getRandomWeight(weightLimit);
                edges.add(new Edge(source, destination, weight));
                vertices[source] = new Vertex(destination, weight, vertices[source].getDegree() + 1, vertices[source]);
                vertices[destination] = new Vertex(source, weight, vertices[destination].getDegree() + 1, vertices[destination]);
                currEdgeSum++;
            }
        }
    }

    /**
     * @param graph        : container for the dense graph
     * @param weightLimit  : max Weight assigned for the edges -> weights are generated from randomWeightGenerator
     * @param targetDegree : avg degree which is to be achieved for dense graph
     */
    private static void completeDenseGraph(Graph graph, int weightLimit, int targetDegree) {
        int n = graph.getNoOfNodes();
        int destination; // random destination variable
        int weight; // random edge weight generator variable
        List<Edge> edges = graph.getEdges();
        for (int i = 0; i < n; i++) {

            int currDegree = graph.getVertices()[i].getDegree();
            while (currDegree < targetDegree) {
                destination = random.nextInt(n);

                if (checkAdjList(graph.getVertices()[i], destination)
                        && i != destination
                ) {
                    weight = getRandomWeight(weightLimit);
                    edges.add(new Edge(i, destination, weight));
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
        return random.nextInt(weightLimit) + 1;
    }

    /**
     * @param vertex      : head of the Linked List
     * @param destination : the destination
     * @return : false if the destination exists in the linked list otherwise true
     */
    private static boolean checkAdjList(Vertex vertex, int destination) {
        while (vertex != null) {
            if (vertex.getVertexId() == destination) {
                return false;
            } else {
                vertex = vertex.getNext();
            }
        }
        return true;
    }

    private static void generateMST(Graph graph) {
        int n = graph.getNoOfNodes();
        UnionFind unionFind = new UnionFind(n);
        Map<Integer, List<Vertex>> maxST = graph.getMaxSpanningTree();

        List<Edge> edgeList = graph.getEdges();
        //sort the edges based on their weights
        unionFind.sortEdges(edgeList);

        //get MST by performing union and find operations on the edges
        for (int i = 0; i < n; i++) {
            maxST.put(i, new ArrayList<>());
        }
        for (Edge edge : edgeList) {
            int r1 = unionFind.find(edge.getVertex1());
            int r2 = unionFind.find(edge.getVertex2());
            if (r1 != r2) {
                unionFind.union(edge.getVertex1(), edge.getVertex2());
                maxST.get(edge.getVertex1()).add(new Vertex(edge.getVertex2(), edge.getEdgeWeight(), 1, null));
                maxST.get(edge.getVertex2()).add(new Vertex(edge.getVertex1(), edge.getEdgeWeight(), 1, null));
            }
        }
    }

    public static int[] getRandomSourceAndTarget(int n) {
        int source = random.nextInt(n);
        int target = source;
        while (source == target) {
            target = random.nextInt(n);
        }
        return new int[]{source, target};
    }
}
