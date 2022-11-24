package com.algoproject.algorithms;

import com.algoproject.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class DijkstraWithHeap {
    public static void apply(Vertex[] graph, int s, int target, int noOfNodes) {
        int unseen = 0;
        int fringe = 1;
        int intree = 2;
        int[] status = new int[noOfNodes];
        int[] bw = new int[noOfNodes];
        int[] dad = new int[noOfNodes];
        List<Vertex> fringes = new ArrayList<>();

        status[s] = intree;

        // add nodes from src
        Vertex temp = graph[s];
        while (temp != null) {
            status[temp.getVertex()] = fringe;
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
            status[maxFringe.getVertex()] = intree;
            Vertex node = graph[maxFringe.getVertex()];
            while (node != null) {
                if (status[node.getVertex()] == unseen) {
                    status[node.getVertex()] = fringe;
                    dad[node.getVertex()] = maxFringe.getVertex();
                    bw[node.getVertex()] = Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight());
//                    node.setBw(bw[node.getVertex()]);
//                    heap.insert(node);
                    heap.insert(new Vertex(node.getVertex(), bw[node.getVertex()], 0, null));
                } else if (status[node.getVertex()] == fringe
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
    }
}
