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
    public static void apply(Graph graph, int source, int target) {
        int n = graph.getNoOfNodes();

        // Initialization of three arrays
        int[] parent = new int[n];
        Status[] status = new Status[n];
        Arrays.fill(status, UNSEEN);
        int[] bw = new int[n];

        parent[source] = -1;
        status[source] = INTREE;
        bw[source] = Integer.MAX_VALUE;

        Vertex[] vertices = graph.getVertices();
        Vertex temp = vertices[source];
        List<Vertex> fringes = new ArrayList<>();
        // Add the neighbors of the source to fringes and mark their status as FRINGE
        while (temp != null) {
            status[temp.getVertexId()] = FRINGE;
            parent[temp.getVertexId()] = source;
            bw[temp.getVertexId()] = temp.getEdgeWeight();
            fringes.add(temp);
            temp = temp.getNext();
        }

        while (!fringes.isEmpty()) {
            int maxFringeId = extractFringeWithMaximumBandwidth(fringes);
            status[maxFringeId] = INTREE;
            Vertex node = vertices[maxFringeId];
            while (node != null) {
                if (status[node.getVertexId()] == UNSEEN) {
                    status[node.getVertexId()] = FRINGE;
                    parent[node.getVertexId()] = maxFringeId;
                    bw[node.getVertexId()] = Math.min(bw[maxFringeId], node.getEdgeWeight());
                    fringes.add(new Vertex(node.getVertexId(), bw[node.getVertexId()], 0, null));
                } else if (status[node.getVertexId()] == FRINGE
                        && bw[node.getVertexId()] < Math.min(bw[maxFringeId], node.getEdgeWeight())) {
                    parent[node.getVertexId()] = maxFringeId;
                    bw[node.getVertexId()] = Math.min(bw[maxFringeId], node.getEdgeWeight());
                    fringeUpdates(fringes, node.getVertexId(), bw[node.getVertexId()]);
                }
                node = node.getNext();
            }
        }

        // Printing Max BW of Target and the corresponding s-t path
        System.out.println("Max BW from Dijkstra's without using Heap is: " + bw[target]);
        System.out.print("s-t path: ");
        while (target != source) {
            System.out.print(target + " <–– ");
            target = parent[target];
        }
        System.out.println(target);
    }

    private static int extractFringeWithMaximumBandwidth(List<Vertex> fringes) {
        Vertex maxFringe = null;
        int maxBW = fringes.stream()
                .map(Vertex::getEdgeWeight)
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
        return maxFringe.getVertexId();
    }

    private static void fringeUpdates(List<Vertex> fringes, int vertex, int updatedWt) {
        for (Vertex fringe : fringes) {
            if (fringe.getVertexId() == vertex) {
                fringe.setEdgeWeight(updatedWt);
            }
        }
    }
}
