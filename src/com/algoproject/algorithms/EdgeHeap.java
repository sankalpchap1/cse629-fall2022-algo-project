package com.algoproject.algorithms;

import com.algoproject.model.Edge;

import java.util.ArrayList;
import java.util.List;

public class EdgeHeap {
    List<Edge> maxHeap;

    //  Constructor to add list of edges
    EdgeHeap(List<Edge> edges) {
        maxHeap = new ArrayList<>(edges.size());
        maxHeap.addAll(edges);
        for (int i = maxHeap.size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    //    Pop max element of Heap
    public Edge popMax() {
        Edge max = maxHeap.get(0);
        delete();

        return max;
    }

    //	Insert vertex into Heap
    public void insert(Edge edge) {
        maxHeap.add(edge);
        int i = maxHeap.size() - 1;

//		bottom-up heapify
        while (i > 0 && i < maxHeap.size() && maxHeap.get(i / 2).getEdgeWeight() - maxHeap.get(i).getEdgeWeight() < 0) {
            swap(i / 2, i);
            i = i / 2;
        }
    }

    //	Delete vertex from Heap
    public void delete() {
        swap(0, maxHeap.size() - 1);
        maxHeap.remove(maxHeap.size() - 1);

//		Top-Down Heapify
        heapify(0);
    }

    //	Top-Down Heapify
    private void heapify(int x) {
        int leftChild = 2 * x;
        int rightChild = 2 * x + 1;
        int largeIndex = x;
        if (leftChild < maxHeap.size() && maxHeap.get(leftChild).getEdgeWeight() - maxHeap.get(x).getEdgeWeight() > 0) {
            largeIndex = leftChild;
        }
        if (rightChild < maxHeap.size() && maxHeap.get(rightChild).getEdgeWeight() - maxHeap.get(largeIndex).getEdgeWeight() > 0) {
            largeIndex = rightChild;
        }
        if (largeIndex != x) {
            swap(x, largeIndex);
            heapify(largeIndex);
        }
    }

    //	Swap position of vertices in Heap
    private void swap(int pos1, int pos2) {
        Edge edge1 = maxHeap.get(pos1);
        Edge edge2 = maxHeap.get(pos2);

        maxHeap.set(pos1, edge2);
        maxHeap.set(pos2, edge1);
    }
}
