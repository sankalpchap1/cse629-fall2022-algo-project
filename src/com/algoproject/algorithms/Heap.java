package com.algoproject.algorithms;

import com.algoproject.model.Vertex;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Description("Data Structure for implementing Max Heap")
public class Heap {
    List<Vertex> maxHeap;
    Map<Integer, Integer> pos;

    int[] heap;
    int[] heapD;
    int[] heapP;

//	Could not get across some edge cases to use H[], D[] and P[] so a different approach to heap is used.

    //    Constructor to add list of fringes
    public Heap(List<Vertex> fringes) {
        maxHeap = new ArrayList<>(fringes.size());
        pos = new HashMap<>(fringes.size());
        for (int i = 0; i < fringes.size(); i++) {
            maxHeap.add(fringes.get(i));
            pos.put(fringes.get(i).getVertex(), i);
        }
        for (int i = maxHeap.size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    //    Pop max element of Heap
    public Vertex popMax() {
        Vertex max = maxHeap.get(0);
        delete(max.getVertex());

        return max;
    }

    //	Insert vertex into Heap
    public void insert(Vertex fringe) {
        maxHeap.add(fringe);
        int i = maxHeap.size() - 1;
        pos.put(fringe.getVertex(), i);

//		bottom-up heapify
        while (i > 0 && i < maxHeap.size() && maxHeap.get(i / 2).getEdgeWeight() - maxHeap.get(i).getEdgeWeight() < 0) {
            swap(i / 2, i);
            i = i / 2;
        }
    }

    //	Delete vertex from Heap
    public void delete(int vertex) {
        int p = pos.get(vertex);
        swap(p, maxHeap.size() - 1);
        maxHeap.remove(maxHeap.size() - 1);
        pos.remove(vertex);

//		Bottom-Up Heapify
        while (p > 0 && p < maxHeap.size() && maxHeap.get(p / 2).getEdgeWeight() - maxHeap.get(p).getEdgeWeight() < 0) {
            swap(p / 2, p);
            p = p / 2;
        }

//		Top-Down Heapify
        heapify(p);
    }

    //	Top-Down Heapify
    private void heapify(int n) {
        int leftChild = 2 * n;
        int rightChild = 2 * n + 1;
        int largeIndex = n;
        if (leftChild < maxHeap.size() && maxHeap.get(leftChild).getEdgeWeight() - maxHeap.get(n).getEdgeWeight() > 0) {
            largeIndex = leftChild;
        }
        if (rightChild < maxHeap.size() && maxHeap.get(rightChild).getEdgeWeight() - maxHeap.get(largeIndex).getEdgeWeight() > 0) {
            largeIndex = rightChild;
        }
        if (largeIndex != n) {
            swap(n, largeIndex);
            heapify(largeIndex);
        }
    }

    //	Swap position of vertices in Heap
    private void swap(int pos1, int pos2) {
        Vertex fringe1 = maxHeap.get(pos1);
        Vertex fringe2 = maxHeap.get(pos2);

        maxHeap.set(pos1, fringe2);
        maxHeap.set(pos2, fringe1);

        pos.put(fringe1.getVertex(), pos2);
        pos.put(fringe2.getVertex(), pos1);
    }
}
