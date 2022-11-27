package com.algoproject.algorithms;

import com.algoproject.model.Graph;
import com.algoproject.model.Status;
import com.algoproject.model.Vertex;
import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.algoproject.model.Status.*;

@Description("Implementation of Dijkstra's Algorithms without using MaxHeap")
public class DijkstraWithoutHeap {
    public static void apply(Graph graph, int source, int target, int n) {
        Status[] status = new Status[n];
        Arrays.fill(status, UNSEEN);
        int[] bw = new int[n];
        int[] dad = new int[n];
        Vertex[] vertices = graph.getVertices();
        List<Vertex> fringes = new ArrayList<>();
        status[source] = INTREE;
        bw[source] = Integer.MAX_VALUE;
        dad[source] = -1;

        // add nodes from source's adj list to the fringes
        Vertex temp = vertices[source];
        while (temp != null) {
            status[temp.getVertex()] = FRINGE;
            dad[temp.getVertex()] = source;
            bw[temp.getVertex()] = temp.getEdgeWeight();
            fringes.add(temp);
            temp = temp.getNext();
        }

        while (!fringes.isEmpty()) {
            Vertex maxFringe = getMaxFringe(fringes);
            status[maxFringe.getVertex()] = INTREE;
            Vertex node = vertices[maxFringe.getVertex()];
            while (node != null) {
                if (status[node.getVertex()] == UNSEEN) {
                    status[node.getVertex()] = FRINGE;
                    dad[node.getVertex()] = maxFringe.getVertex();
                    bw[node.getVertex()] = Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight());
                    fringes.add(new Vertex(node.getVertex(), bw[node.getVertex()], 0, null));
                } else if (status[node.getVertex()] == FRINGE
                        && bw[node.getVertex()] < Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight())) {
                    dad[node.getVertex()] = maxFringe.getVertex();
                    bw[node.getVertex()] = Math.min(bw[maxFringe.getVertex()], node.getEdgeWeight());
                    updateFringe(fringes, node.getVertex(), bw[node.getVertex()]);
                }
                node = node.getNext();
            }
        }

        // Printing Max BW of Target and the corresponding s-t path
        System.out.println("Max BW from Dijkstra's without using Heap is: " + bw[target]);
        System.out.print("s-t path: ");
        while (target != source) {
            System.out.print(target + " <–– ");
            target = dad[target];
        }
        System.out.println(target);
    }

    //	Get the Max Fringe from a list of Fringes
    private static Vertex getMaxFringe(List<Vertex> fringes) {
        Vertex maxFringe = null;
        int maxBW = fringes.stream().map(Vertex::getEdgeWeight)
                .mapToInt(v -> v)
                .max()
                .getAsInt();

        int id = -1;
        for (int i = 0; i < fringes.size(); i++) {
            if (fringes.get(i).getEdgeWeight() == maxBW) {
                id = i;
                maxFringe = fringes.get(i);
                break;
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
