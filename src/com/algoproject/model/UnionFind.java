package com.algoproject.model;

import java.util.Arrays;

//Union Find
public class UnionFind {

    private final int[] rank; // Stores the rank of the nodes
    private final int[] dad; //Stores the parent of nodes in Union Find set

    //	Initialize Union Find set Constructor
    public UnionFind(int n) {
        rank = new int[n];
        dad = new int[n];

        Arrays.fill(rank, 1);

        for (int i = 0; i < n; i++) {
            dad[i] = i;
        }
    }

    //	Finds the group vertex belongs to
    public int find(int x) {
        if (dad[x] != x) {
            dad[x] = find(dad[x]);
        }
        return dad[x];
    }

    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (rank[p1] < rank[p2]) {
            // set1 is less than set2
            // make p2 the parent(root) of p1
            dad[p1] = p2;
        } else {
            // set1 is larger than set2
            // make p1 the parent(root) of p2
            dad[p2] = p1;

            // set1 is as the same rank as set2
            // increase the rank of the root p1
            if (rank[p1] == rank[p2]) {
                rank[p1]++;
            }
        }
    }
}