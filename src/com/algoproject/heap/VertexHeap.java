package com.algoproject.heap;

import com.algoproject.model.Vertex;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Description("Data Structure to implement max Heap for Vertices using H, P and D array used in Dijkstra's algorithm")
public class VertexHeap {
    // D array as per project description
    private static int[] Heap; // To store BW of vertex

    // H array as per project description
    private static int[] vertices;// To get the vertex value

    // P array as per project description
    private static int[] positionArray;// To get the index index referenced to heap from Vertex Number

    // Current size of the heap
    private static int size;

    private static int maxsize;

    public static void init(int maxsize) {
        VertexHeap.maxsize = maxsize;
        size = 0;
        Heap = new int[maxsize];
        vertices = new int[maxsize];
        positionArray = new int[maxsize];
        Arrays.fill(positionArray, -1);
    }

    public static int getSize() {
        return size;
    }

    private static int parent(int pos) {
        return (pos - 1) / 2;
    }

    private static int leftChild(int pos) {
        return 1 + (2 * pos);
    }

    private static int rightChild(int pos) {
        return 2 + (2 * pos);
    }

    private static boolean isLeaf(int pos) {
        return pos <= size && pos > (size / 2) ;
    }

    private static void swap(int pos1, int pos2) {
        positionArray[vertices[pos1]] = pos2;
        positionArray[vertices[pos2]] = pos1;

        int tempVertices = vertices[pos1];
        vertices[pos1] = vertices[pos2];
        vertices[pos2] = tempVertices;

        int tmp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = tmp;
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
        ensureCapacity();
        Heap[size] = element;
        vertices[size] = vertex;
        positionArray[vertex] = size;

        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    private static void ensureCapacity() {
        if (size == maxsize) {
            Heap = Arrays.copyOf(Heap, maxsize * 2);
            vertices = Arrays.copyOf(vertices, maxsize * 2);
            positionArray = Arrays.copyOf(positionArray, maxsize * 2);
            maxsize = maxsize * 2;
        }
    }

    public static int extractMax(int index) {
        int popped = vertices[index];
        size--;
        Heap[index] = Heap[size];
        vertices[index] = vertices[size];
        positionArray[vertices[size]] = positionArray[popped];

        maxHeapify(index);

        Heap[size] = 0;
        vertices[size] = 0;
        positionArray[popped] = -1;

        return popped;
    }

    public static void main(String[] arg) {
        System.out.println("Testing Vertex Heap ");

        VertexHeap.init(15);

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

        delete(8);
        System.out.println("The max val is " + extractMax(0));
    }

    public static void delete(int vertex) {
        int pos = positionArray[vertex];
        extractMax(pos);
    }
}
