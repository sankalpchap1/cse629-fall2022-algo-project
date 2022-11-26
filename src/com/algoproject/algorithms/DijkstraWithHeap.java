package com.algoproject.algorithms;

import com.algoproject.heap.VertexHeap;
import com.algoproject.model.Graph;
import com.algoproject.model.Status;
import com.algoproject.model.Vertex;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.algoproject.model.Status.*;

@Description("Implementation of Dijkstra's Algorithms with using MaxHeap - VertexHeap")
public class DijkstraWithHeap {
    public static int apply(Graph graph, int s, int target, int n) {
        Status[] status = new Status[n];
        Arrays.fill(status, UNSEEN);
        int[] bw = new int[n];
        int[] dad = new int[n];
        Vertex[] vertices = graph.getVertices();
        List<Vertex> fringes = new ArrayList<>();

        status[s] = INTREE;

        VertexHeap.init(n);
        // add nodes from src
        Vertex temp = vertices[s];
        while (temp != null) {
            status[temp.getVertex()] = FRINGE;
            dad[temp.getVertex()] = s;
            bw[temp.getVertex()] = temp.getEdgeWeight();
//            temp.setBw(temp.getEdgeWeight());
            fringes.add(temp);
            VertexHeap.insert(temp.getVertex(),bw[temp.getVertex()]);
            temp = temp.getNext();
        }

        // add nodes for unseen in heap and update status, dad and bw for unseen and
        // fringe
        while (VertexHeap.getSize()!=0) {
            int maxIndex = VertexHeap.extractMax(0);
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

        // get max bw and path from dest to src
        System.out.println("Max bandwidth with heap using Dijkstra is: " + bw[target]);
//        System.out.print("s-target path: ");
//        while (target != s) {
//            System.out.print(target + " <-- ");
//            target = dad[target];
//        }
//        System.out.println(target);
        return bw[target];
    }
}
