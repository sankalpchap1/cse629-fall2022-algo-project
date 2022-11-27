package com.algoproject.model;

import jdk.jfr.Description;

import java.util.List;
import java.util.Map;

@Description("Data Structure to store the graph")
public class Graph {

    private final Vertex[] vertices;

    private final List<Edge> edges;

    private final Map<String, Integer> edgeMap;

    private final Map<Integer, List<Vertex>> maxSpanningTree;

    public Graph(final Vertex[] vertices,
                 final List<Edge> edges,
                 final Map<String, Integer> edgeMap,
                 final Map<Integer, List<Vertex>> maxSpanningTree) {
        this.vertices = vertices;
        this.edges = edges;
        this.edgeMap = edgeMap;
        this.maxSpanningTree = maxSpanningTree;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Map<String, Integer> getEdgeMap() {
        return edgeMap;
    }

    public Map<Integer, List<Vertex>> getMaxSpanningTree() {
        return maxSpanningTree;
    }

}
