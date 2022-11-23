package com.algoproject.model;

public class Vertex {
    private int vertex;
    private int edgeWeight;
    private int degree;
    private Vertex next;

    public Vertex(int vertex, int edgeWeight, int degree, Vertex next) {
        this.vertex = vertex;
        this.edgeWeight = edgeWeight;
        this.degree = degree;
        this.next = next;
    }

    public int getDegree() {
        return degree;
    }
    public void setDegree(int degree) {
        this.degree = degree;
    }
    public int getVertex() {
        return vertex;
    }
    public void setVertex(int vertex) {
        this.vertex = vertex;
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
    public void setNext(Vertex next) {
        this.next = next;
    }

}
