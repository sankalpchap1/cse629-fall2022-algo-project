package com.algoproject.heap;

import java.util.HashMap;
import java.util.Map;

public class VertexHeap {
    private static int[] Heap;

    private static int[] vertices;

    // Key-> vertex Value and Value-> index of this vertex in the vertices array
    private static Map<Integer, Integer> map;

    public static int getSize() {
        return size;
    }

    private static int size;

    public static void init(int maxsize) {
        size = 0;
        Heap = new int[maxsize];
        vertices = new int[maxsize];
        map = new HashMap<>();
    }

    // Method 1
    // Returning position of parent
    private static int parent(int pos) {
        return (pos - 1) / 2;
    }

    // Method 2
    // Returning left children
    private static int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    // Method 3
    // Returning right children
    private static int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    // Method 4
    // Returning true of given node is leaf
    private static boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    // Method 5
    // Swapping nodes
    private static void swap(int fpos, int spos) {
        map.put(vertices[fpos], spos);
        map.put(vertices[spos], fpos);

        int tempVertices = vertices[fpos];
        vertices[fpos] = vertices[spos];
        vertices[spos] = tempVertices;

        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private static void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        if (Heap[pos] < Heap[leftChild(pos)]
                || Heap[pos] < Heap[rightChild(pos)]) {

            if (Heap[leftChild(pos)]
                    > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    public static void insert(int vertex, int element) {
        Heap[size] = element;
        vertices[size] = vertex;
        map.put(vertex, size);

        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    public static int extractMax(int index) {
        int popped = vertices[index];
        size--;
        Heap[index] = Heap[size];
        vertices[index] = vertices[size];
        map.put(vertices[size], map.get(popped));

        maxHeapify(index);

        Heap[size] = 0;
        vertices[size] = 0;
        map.remove(popped);

        return popped;
    }

    public static void main(String[] arg) {
        System.out.println("The Max Heap is ");

        VertexHeap.init(15);

        // Inserting nodes
        // Custom inputs
        insert(1, 5);
        insert(3, 3);
        insert(5, 17);
        insert(7, 10);
        insert(9, 84);
        insert(4, 19);
        insert(14, 6);
        insert(8, 22);
        insert(11, 9);
        insert(2, 90);

        System.out.println("testing Vertex Heap");
        // Print and display the maximum value in heap
//        int maxIndex = maxHeap.extractMax(0);
        delete(8);
        System.out.println("The max val is "
                + extractMax(0));
    }

    public static void delete(int vertex) {
        int pos = map.get(vertex);
        extractMax(pos);

    }
}
