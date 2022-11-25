package com.algoproject.model;

import java.util.List;
import java.util.Map;

public class Graph {
    Vertex[] vertices;
    List<Edge> edges;
    Map<String, Integer> edgeMap;

    public Graph(Vertex[] vertices,
                 List<Edge> edges,
                 Map<String, Integer> edgeMap) {
        this.vertices = vertices;
        this.edges = edges;
        this.edgeMap = edgeMap;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Map<String, Integer> getEdgeMap() {
        return edgeMap;
    }

    public void setEdgeMap(Map<String, Integer> edgeMap) {
        this.edgeMap = edgeMap;
    }
}
