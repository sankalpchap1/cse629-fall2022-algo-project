package com.algoproject.heap;

import jdk.jfr.Description;

import java.util.Arrays;

@Description("Data Structure to implement max Heap for Vertices using H, P and D array used in Dijkstra's algorithm")
public class VertexHeap {
    // D array as per project description
    private static int[] heapArray; // To store BW of vertex

    // H array as per project description
    private static int[] vertexArray;// To get the vertex value

    // P array as per project description
    private static int[] positionArray;// To get the index index referenced to heap from Vertex Number

    private static int maxsize;

    // Current size of the heap
    private static int size;


    public static void init(int maxsize) {
        size = 0;
        VertexHeap.maxsize = maxsize;
        heapArray = new int[maxsize];
        vertexArray = new int[maxsize];
        positionArray = new int[maxsize];
        Arrays.fill(positionArray, -1);
    }

    public static int getSize() {
        return size;
    }

    private static int parent(int pos) {
        return (pos - 1) / 2;
    }

    private static boolean isLeaf(int pos) {
        return pos <= size && pos > (size / 2);
    }

    private static int rightChild(int pos) {
        return 2 + (2 * pos);
    }

    private static int leftChild(int pos) {
        return 1 + (2 * pos);
    }

    private static void swapNodes(int pos1, int pos2) {
        positionArray[vertexArray[pos1]] = pos2;
        positionArray[vertexArray[pos2]] = pos1;

        int tempVertices = vertexArray[pos1];
        vertexArray[pos1] = vertexArray[pos2];
        vertexArray[pos2] = tempVertices;

        int dummy = heapArray[pos1];
        heapArray[pos1] = heapArray[pos2];
        heapArray[pos2] = dummy;
    }

    private static void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        if (heapArray[pos] < heapArray[leftChild(pos)]
                || heapArray[pos] < heapArray[rightChild(pos)]) {

            if (heapArray[leftChild(pos)]
                    > heapArray[rightChild(pos)]) {
                swapNodes(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swapNodes(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    public static void insert(int vertex, int bandwidth) {
        handleCapacityOverflow();
        heapArray[size] = bandwidth;
        vertexArray[size] = vertex;
        positionArray[vertex] = size;

        int curr = size;
        while (heapArray[parent(curr)] < heapArray[curr]) {
            swapNodes( parent(curr), curr);
            curr = parent(curr);
        }
        size++;
    }

    private static void handleCapacityOverflow() {
        if (size == maxsize) {
            heapArray = Arrays.copyOf(heapArray, maxsize * 2);
            vertexArray = Arrays.copyOf(vertexArray, maxsize * 2);
            positionArray = Arrays.copyOf(positionArray, maxsize * 2);
            maxsize = maxsize * 2;
        }
    }

    public static int popMax(int index) {
        int popped = vertexArray[index];
        size--;
        heapArray[index] = heapArray[size];
        vertexArray[index] = vertexArray[size];
        positionArray[vertexArray[size]] = positionArray[popped];

        maxHeapify(index);

        heapArray[size] = 0;
        vertexArray[size] = 0;
        positionArray[popped] = -1;

        return popped;
    }

    public static void main(String[] arg) {
        System.out.println("Testing Vertex Heap...");

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
        System.out.println("The max val is " + popMax(0));
    }

    public static void delete(int vertex) {
        int pos = positionArray[vertex];
        popMax(pos);
    }
}
