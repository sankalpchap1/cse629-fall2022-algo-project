package com.algoproject.model;

import jdk.jfr.Description;

@Description("Data Structure to store the edge between two vertices")
public class Edge {

    private final int vertex1;

    private final int vertex2;

    private final int edgeWeight;

    public Edge(final int vertex1, final int vertex2, final int edgeWeight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.edgeWeight = edgeWeight;
    }

    public int getVertex1() {
        return vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }
}
