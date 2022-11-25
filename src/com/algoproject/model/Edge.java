package com.algoproject.model;

public class Edge {
    int vertex1;
    int vertex2;
    int edgeWeight;

    public Edge(int vertex1, int vertex2, int edgeWeight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.edgeWeight = edgeWeight;
    }

    public int getVertex1() {
        return vertex1;
    }

    public void setVertex1(int vertex) {
        this.vertex1 = vertex;
    }

    public int getVertex2() {
        return vertex2;
    }

    public void setVertex2(int vertex) {
        this.vertex2 = vertex;
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }

    public void setEdgeWeight(int weight) {
        this.edgeWeight = weight;
    }
}
