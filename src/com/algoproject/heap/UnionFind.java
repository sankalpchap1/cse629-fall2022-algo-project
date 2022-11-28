package com.algoproject.heap;

import com.algoproject.model.Edge;
import jdk.jfr.Description;

import java.util.List;

@Description("Data Structure to implement Union Find used for Kruskal's Algorithm")
public class UnionFind {

    int[] rank;
    int[] dad;

    public UnionFind(int nodes) {
        rank = new int[nodes];
        dad = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            rank[i] = 0;
            dad[i] = i;
        }
    }

    //find root of that vertex
    public int find(int x) {
        if (dad[x] != x) {
            dad[x] = find(dad[x]);
        }
        return dad[x];
    }

    //join the shorter tree to the taller tree
    public void union(int node1, int node2) {
        int r1 = find(node1);
        int r2 = find(node2);
        if (rank[r1] < rank[r2]) {
            dad[r1] = r2;
        } else if (rank[r1] > rank[r2]) {
            dad[r2] = r1;
        } else {
            dad[r2] = r1;
            rank[r1]++;
        }
    }

    //heap sort to sort edges in decreasing order
    public void sort(List<Edge> edges) {
        int n = edges.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(edges, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            Edge temp = edges.get(0);
            edges.set(0, edges.get(i));
            edges.set(i, temp);
            heapify(edges, i, 0);
        }
    }

    //rearrange heap
    void heapify(List<Edge> edges, int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        if (leftChild < n && edges.get(leftChild).getEdgeWeight() < edges.get(largest).getEdgeWeight()) {
            largest = leftChild;
        }
        if (rightChild < n && edges.get(rightChild).getEdgeWeight() < edges.get(largest).getEdgeWeight()) {
            largest = rightChild;
        }
        if (largest != i) {
            Edge temp = edges.get(i);
            edges.set(i, edges.get(largest));
            edges.set(largest, temp);
            heapify(edges, n, largest);
        }
    }
}