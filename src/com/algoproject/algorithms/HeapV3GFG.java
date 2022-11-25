package com.algoproject.algorithms;

import com.algoproject.model.Vertex;

import java.util.HashMap;
import java.util.Map;

public class HeapV3GFG {
    private static int[] Heap;

    public static int getVertexNumberFromIndex(int index) {
        return vertices[index];
    }

    private static int[] vertices;

    // Key-> vertex Value and Value-> index of this vertex in the vertices array
    private static Map<Integer, Integer> map;

    public static int getSize() {
        return size;
    }

    private static int size;
    private int maxsize;
    public HeapV3GFG(int maxsize)
    {
        // This keyword refers to current instance itself
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize];
        vertices = new int[this.maxsize];
        map = new HashMap<>();
    }

    // Method 1
    // Returning position of parent
    private static int parent(int pos) { return (pos - 1) / 2; }

    // Method 2
    // Returning left children
    private static int leftChild(int pos) { return (2 * pos) + 1; }

    // Method 3
    // Returning right children
    private static int rightChild(int pos)
    {
        return (2 * pos) + 2;
    }

    // Method 4
    // Returning true of given node is leaf
    private static boolean isLeaf(int pos)
    {
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    // Method 5
    // Swapping nodes
    private static void swap(int fpos, int spos)
    {
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

    // Method 6
    // Recursive function to max heapify given subtree
    private static void maxHeapify(int pos)
    {
        if (isLeaf(pos))
            return;

        if (Heap[pos] < Heap[leftChild(pos)]
                || Heap[pos] < Heap[rightChild(pos)]) {

            if (Heap[leftChild(pos)]
                    > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    // Method 7
    // Inserts a new element to max heap
    public static void insert(int vertex, int element)
    {
        Heap[size] = element;
        vertices[size] = vertex;
        map.put(vertex, size);

        // Traverse up and fix violated property
        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    public static int extractMax(int index)
    {
        int popped = vertices[index];
        size--;
        Heap[index] = Heap[size];
        vertices[index] = vertices[size];
        map.put(vertices[size], map.get(popped));

        maxHeapify(index);

        Heap[size]=0;
        vertices[size]=0;
        map.remove(popped);

        return popped;
    }

    public static void main(String[] arg)
    {
        // Display message for better readability
        System.out.println("The Max Heap is ");

        HeapV3GFG maxHeap = new HeapV3GFG(15);

        // Inserting nodes
        // Custom inputs
        maxHeap.insert(1,5);
        maxHeap.insert(3,3);
        maxHeap.insert(5,17);
        maxHeap.insert(7,10);
        maxHeap.insert(9,84);
        maxHeap.insert(4, 19);
        maxHeap.insert(14,6);
        maxHeap.insert(8,22);
        maxHeap.insert(11,9);
        maxHeap.insert(2,90);

        // Calling maxHeap() as defined above
//        maxHeap.print();
        System.out.println("test");
        // Print and display the maximum value in heap
//        int maxIndex = maxHeap.extractMax(0);
        delete(8);
        System.out.println("The max val is "
                + maxHeap.extractMax(0));
    }

    public static void delete(int vertex) {
        int pos = map.get(vertex);
        extractMax(pos);

    }
}
