package com.algoproject.algorithms;

import com.algoproject.heap.VertexHeap;
import com.algoproject.model.Graph;
import com.algoproject.model.Status;
import com.algoproject.model.Vertex;
import jdk.jfr.Description;

import java.util.Arrays;

import static com.algoproject.model.Status.*;

@Description("Implementation of Dijkstra's Algorithms with using MaxHeap - VertexHeap")
public class DijkstraWithHeap {
    public static void apply(Graph graph, int source, int target) {
        int n = graph.getNoOfNodes();
        int[] dad = new int[n];
        Status[] status = new Status[n];
        Arrays.fill(status, UNSEEN);
        int[] bw = new int[n];

        status[source] = INTREE;

        // Initialize the vertex heap
        VertexHeap.init(n);
        // add nodes from src
        Vertex[] vertices = graph.getVertices();
        Vertex temp = vertices[source];
        while (temp != null) {
            status[temp.getVertex()] = FRINGE;
            dad[temp.getVertex()] = source;
            bw[temp.getVertex()] = temp.getEdgeWeight();
            VertexHeap.insert(temp.getVertex(), bw[temp.getVertex()]);
            temp = temp.getNext();
        }

        // add nodes for unseen in heap and update status, dad and bw for unseen and fringe
        while (VertexHeap.getSize() != 0) {
            int maxIndex = VertexHeap.popMax(0);
            status[maxIndex] = INTREE;
            Vertex node = vertices[maxIndex];
            while (node != null) {
                if (status[node.getVertex()] == UNSEEN) {
                    status[node.getVertex()] = FRINGE;
                    dad[node.getVertex()] = maxIndex;
                    bw[node.getVertex()] = Math.min(bw[maxIndex], node.getEdgeWeight());
                    VertexHeap.insert(node.getVertex(), bw[node.getVertex()]);
                } else if (status[node.getVertex()] == FRINGE
                        && bw[node.getVertex()] < Math.min(bw[maxIndex], node.getEdgeWeight())) {
                    VertexHeap.delete(node.getVertex());
                    dad[node.getVertex()] = maxIndex;
                    bw[node.getVertex()] = Math.min(bw[maxIndex], node.getEdgeWeight());
                    VertexHeap.insert(node.getVertex(), bw[node.getVertex()]);
                }
                node = node.getNext();
            }
        }

        System.out.println("Max BW from Dijkstra's using Max Heap is: " + bw[target]);
        System.out.print("s-t path: ");
        while (target != source) {
            System.out.print(target + " <–– ");
            target = dad[target];
        }
        System.out.println(target);
    }
}
