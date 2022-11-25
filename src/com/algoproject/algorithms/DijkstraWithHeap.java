package com.algoproject.algorithms;

import com.algoproject.model.Edge;
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

        HeapV3GFG heap = new HeapV3GFG(graph.getVertices().length);
        // add nodes from src
        Vertex temp = vertices[s];
        while (temp != null) {
            status[temp.getVertex()] = FRINGE;
            dad[temp.getVertex()] = s;
            bw[temp.getVertex()] = temp.getEdgeWeight();
//            temp.setBw(temp.getEdgeWeight());
            fringes.add(temp);
            HeapV3GFG.insert(temp.getVertex(),bw[temp.getVertex()]);
            temp = temp.getNext();
        }
        for (Vertex vertex: fringes){

        }

        // add nodes for unseen in heap and update status, dad and bw for unseen and
        // fringe
        while (HeapV3GFG.getSize()!=0) {
//            Vertex maxFringe = HeapV2;
            int maxIndex = HeapV3GFG.extractMax(0);
            status[maxIndex] = INTREE;
//            HeapV2.delete(1);
            Vertex node = vertices[maxIndex];
            while (node != null) {
                if (status[node.getVertex()] == UNSEEN) {
                    status[node.getVertex()] = FRINGE;
                    dad[node.getVertex()] = maxIndex;
                    bw[node.getVertex()] = Math.min(bw[maxIndex], node.getEdgeWeight());
//                    node.setBw(bw[node.getVertex()]);
//                    heap.insert(node);
                    HeapV3GFG.insert(node.getVertex(), bw[node.getVertex()]);
                } else if (status[node.getVertex()] == FRINGE
                        && bw[node.getVertex()] < Math.min(bw[maxIndex], node.getEdgeWeight())) {
                    HeapV3GFG.delete(node.getVertex());
                    dad[node.getVertex()] = maxIndex;
                    bw[node.getVertex()] = Math.min(bw[maxIndex], node.getEdgeWeight());
                    HeapV3GFG.insert(node.getVertex(), bw[node.getVertex()]);
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
