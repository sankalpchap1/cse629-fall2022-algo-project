package com.algoproject.model;

import jdk.jfr.Description;

import java.util.Arrays;

@Description("Data Structure to implement Union Find used for Kruskal's Algorithm")
public class UnionFind {

    private final int[] rank;
    private final int[] dad;

    public UnionFind(int n) {
        rank = new int[n];
        dad = new int[n];

        Arrays.fill(rank, 1);

        for (int i = 0; i < n; i++) {
            dad[i] = i;
        }
    }

    public int find(int x) {
        if (dad[x] != x) {
            dad[x] = find(dad[x]);
        }
        return dad[x];
    }

    public void union(int p1, int p2) {
        if (rank[p1] < rank[p2]) {
            dad[p1] = p2;
        } else {
            dad[p2] = p1;
            if (rank[p1] == rank[p2]) {
                rank[p1]++;
            }
        }
    }
}