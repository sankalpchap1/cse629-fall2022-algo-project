package com.algoproject.algorithms;

import com.algoproject.model.Vertex;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.List;

@Description("Data Structure for implementing Max Heap")
public class DijkstraWithoutHeap {
    public static void apply(Vertex[] graph, int source, int target, int n){
        int unseen = 0;
        int fringe = 1;
        int intree = 2;
        int[] status = new int[n];
        int[] bw = new int[n];
        int[] dad = new int[n];
        List<Vertex> fringes = new ArrayList<>();
        status[source] = intree;
        // add nodes from source's adj list to the fringes
        Vertex temp = graph[source];
        while (temp != null) {
            status[temp.getVertex()] = fringe;
            dad[temp.getVertex()] = source;
            bw[temp.getVertex()] = temp.getEdgeWeight();
            fringes.add(temp);
            temp = temp.getNext();
        }

        // add nodes for unseen and update status, dad and bw for unseen and fringe
        while (!fringes.isEmpty()){
            Vertex maxFringe = getMaxFringe(fringes);
            status[maxFringe.getVertex()] = intree;
            Vertex node = graph[maxFringe.getVertex()];
            while (node != null) {
                if (status[node.getVertex()] == unseen) {
                    status[node.getVertex()] = fringe;
                    dad[node.getVertex()] = maxFringe.getVertex();
                    bw[node.getVertex()] = Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight());
                    fringes.add(new Vertex(node.getVertex(), bw[node.getVertex()], 0, null) {
                    });
                } else if (status[node.getVertex()] == fringe
                        && bw[node.getVertex()] < Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight())) {
                    dad[node.getVertex()] = maxFringe.getVertex();
                    bw[node.getVertex()] = Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight());
                    updateFringe(fringes, node.getVertex(), bw[node.getVertex()]);
                }
                node = node.getNext();
            }
        }
        // get max bw and path from t to s
        System.out.println("Max bandwidth without heap using Dijkstra is: " + bw[target]);
        System.out.print("s-t path: ");
        while (target != source) {
            System.out.print(target + " <-- ");
            target = dad[target];
        }
        System.out.println(target);
    }

    //	Get the Max Fringe from a list of Fringes
    private static Vertex getMaxFringe(List<Vertex> fringes) {
        Vertex maxFringe = new Vertex(-1, -1, 0, null);
        int id = -1;
        for (int i = 0; i < fringes.size(); i++) {
            if (fringes.get(i).getEdgeWeight() > maxFringe.getEdgeWeight()) {
                maxFringe = fringes.get(i);
                id = i;
            }
        }
        fringes.remove(id);
        return maxFringe;
    }

    //	Update the value in a Fringe
    private static void updateFringe(List<Vertex> fringes, int vertex, int updatedWt) {
        for (Vertex fringe : fringes) {
            if (fringe.getVertex() == vertex) {
                fringe.setEdgeWeight(updatedWt);
            }
        }
    }
}
