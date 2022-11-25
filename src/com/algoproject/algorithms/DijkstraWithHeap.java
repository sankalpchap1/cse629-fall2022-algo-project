package com.algoproject.algorithms;

import com.algoproject.model.Graph;
import com.algoproject.model.Status;
import com.algoproject.model.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.algoproject.model.Status.*;

public class DijkstraWithHeap {
    public static int apply(Graph graph, int s, int target, int n) {
        Status[] status = new Status[n];
        Arrays.fill(status, UNSEEN);
        int[] bw = new int[n];
        int[] dad = new int[n];
        Vertex[] vertices = graph.getVertices();
        List<Vertex> fringes = new ArrayList<>();

        status[s] = INTREE;

        // add nodes from src
        Vertex temp = vertices[s];
        while (temp != null) {
            status[temp.getVertex()] = FRINGE;
            dad[temp.getVertex()] = s;
            bw[temp.getVertex()] = temp.getEdgeWeight();
//            temp.setBw(temp.getEdgeWeight());
            fringes.add(temp);
            temp = temp.getNext();
        }
        Heap heap = new Heap(fringes);

        // add nodes for unseen in heap and update status, dad and bw for unseen and
        // fringe
        while (!heap.maxHeap.isEmpty()) {
            Vertex maxFringe = heap.popMax();
            status[maxFringe.getVertex()] = INTREE;
            Vertex node = vertices[maxFringe.getVertex()];
            while (node != null) {
                if (status[node.getVertex()] == UNSEEN) {
                    status[node.getVertex()] = FRINGE;
                    dad[node.getVertex()] = maxFringe.getVertex();
                    bw[node.getVertex()] = Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight());
//                    node.setBw(bw[node.getVertex()]);
//                    heap.insert(node);
                    heap.insert(new Vertex(node.getVertex(), bw[node.getVertex()], 0, null));
                } else if (status[node.getVertex()] == FRINGE
                        && bw[node.getVertex()] < Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight())) {
                    heap.delete(node.getVertex());
                    dad[node.getVertex()] = maxFringe.getVertex();
                    bw[node.getVertex()] = Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight());
//                    node.setBw(bw[node.getVertex()]);
//                    heap.insert(node);
                    heap.insert(new Vertex(node.getVertex(), bw[node.getVertex()], 0, null));
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
