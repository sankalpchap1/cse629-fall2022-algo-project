package com.algoproject.heap;


import com.algoproject.model.Edge;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Description("Data Structure to implement max Heap for Edges used in kruskal's algorithm")
public class EdgeHeap {
    Edge[] Heap;
    private int size;
    private int maxsize;

    public EdgeHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new Edge[maxsize];
    }

    private int parentIndex(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChildIndex(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChildIndex(int pos) {
        return (2 * pos) + 2;
    }

    public int getSize() {
        return size;
    }

    private void swap(int fpos, int spos) {
        Edge tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return parentIndex(index) >= 0;
    }

    private Edge leftChild(int parentIndex) {
        return Heap[leftChildIndex(parentIndex)];
    }

    private Edge rightChild(int parentIndex) {
        return Heap[rightChildIndex(parentIndex)];
    }

    private Edge parent(int childIndex) {
        return Heap[parentIndex(childIndex)];
    }

    private void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && parent(index).getEdgeWeight() < Heap[index].getEdgeWeight()) {
            swap(parentIndex(index), index);
            index = parentIndex(index);
        }
    }

    public void insert(Edge element) {
        ensureCapacity();
        Heap[size] = element;
        size++;
        heapifyUp();
    }

    private void ensureCapacity() {
        if (size == maxsize) {
            Heap = Arrays.copyOf(Heap, maxsize * 2);
            maxsize = maxsize * 2;
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallestChildIndex = leftChildIndex(index);

            if (hasRightChild(index) && rightChild(index).getEdgeWeight() > leftChild(index).getEdgeWeight()) {
                smallestChildIndex = rightChildIndex(index);
            }

            if (Heap[index].getEdgeWeight() < Heap[smallestChildIndex].getEdgeWeight()) {
                swap(index, smallestChildIndex);
            } else {
                break;
            }
            index = smallestChildIndex;
        }
    }

    public Edge extractMax() {
        if (size == 0)
            throw new NoSuchElementException();
        Edge popped = Heap[0];
        Heap[0] = Heap[size - 1];
        size--;
        heapifyDown();
        return popped;
    }

    public static void main(String[] arg) {

        System.out.println("Testing the Edge Heap");

        List<Edge> edges = new ArrayList<>(8);
        edges.add(new Edge(1, 2, 5));
        edges.add(new Edge(3, 2, 7));
        edges.add(new Edge(4, 5, 1));
        edges.add(new Edge(1, 6, 3));
        edges.add(new Edge(1, 7, 6));
        edges.add(new Edge(8, 11, 4));
        edges.add(new Edge(12, 11, 2));
        edges.add(new Edge(6, 2, 9));
        EdgeHeap maxHeapEdge = new EdgeHeap(edges.size());

        edges.forEach(maxHeapEdge::insert);

        for (int i = 0; i < edges.size(); i++) {
            System.out.println(i + " " + maxHeapEdge.extractMax().getEdgeWeight());
        }
    }
}
