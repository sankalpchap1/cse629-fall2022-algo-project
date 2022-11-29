package com.algoproject.model;

import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Description("Data Structure to store the graph")
public class Graph {

    private final Integer noOfNodes;

    private final GraphType type;

    private final Vertex[] vertices;

    private final List<Edge> edges;

    private final Map<Integer, List<Vertex>> maxSpanningTree;

    public Graph(final Integer noOfNodes, final GraphType type) {
        this.noOfNodes = noOfNodes;
        this.type = type;
        this.vertices = new Vertex[noOfNodes];
        this.edges = new ArrayList<>();
        this.maxSpanningTree = new HashMap<>(noOfNodes);
    }

    public Integer getNoOfNodes() {
        return noOfNodes;
    }

    public GraphType getType() {
        return type;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Map<Integer, List<Vertex>> getMaxSpanningTree() {
        return maxSpanningTree;
    }

}
