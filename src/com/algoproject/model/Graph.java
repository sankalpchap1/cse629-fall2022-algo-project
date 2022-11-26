package com.algoproject.model;

import jdk.jfr.Description;

import java.util.List;
import java.util.Map;

@Description("Data Structure to store the graph")
public class Graph {

    private final Vertex[] vertices;

    private final List<Edge> edges;

    private final Map<String, Integer> edgeMap;

    public Graph(final Vertex[] vertices,
                 final List<Edge> edges,
                 final Map<String, Integer> edgeMap) {
        this.vertices = vertices;
        this.edges = edges;
        this.edgeMap = edgeMap;
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

}
