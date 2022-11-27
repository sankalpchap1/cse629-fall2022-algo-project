package com.algoproject.model;

import jdk.jfr.Description;

@Description("Data Structure to store the Vertex in graph")
public class Vertex {
    private final int vertex;
    private int edgeWeight;
    private final int degree;
    private final Vertex next;

    public Vertex(int vertex, int edgeWeight, int degree, Vertex next) {
        this.vertex = vertex;
        this.edgeWeight = edgeWeight;
        this.degree = degree;
        this.next = next;
    }

    public int getDegree() {
        return degree;
    }

    public int getVertex() {
        return vertex;
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
