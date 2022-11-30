package com.algoproject.model;

import jdk.jfr.Description;

@Description("Data Structure to store the Vertex in graph")
public class Vertex {
    private final int vertexId;
    private int edgeWeight;
    private final int degree;
    private final Vertex next;

    public Vertex(int vertexId, int edgeWeight, int degree, Vertex next) {
        this.vertexId = vertexId;
        this.edgeWeight = edgeWeight;
        this.degree = degree;
        this.next = next;
    }

    public int getDegree() {
        return degree;
    }

    public int getVertexId() {
        return vertexId;
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }

    public void setEdgeWeight(int edgeWt) {
        this.edgeWeight = edgeWt;
    }

    public Vertex getNext() {
        return next;
    }
}
