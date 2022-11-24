package com.algoproject.model;

//Union Find
public class UnionFind {

	private final int[] rank; // Stores the rank of the nodes
	private final int[] dad; //Stores the parent of nodes in Union Find set

//	Initialize Union Find set Constructor
	public UnionFind(int n) {
		rank = new int[n];
		dad = new int[n];

		for (int i = 0; i < n; i++) {
			rank[i] = 1;
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

//	Groups 2 sets of vertices into 1
	private void group(int p1, int p2) {
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

//	Checks if vertices v1 and v2 are part of the same group
	public void union(int v1, int v2) {
		group(find(v1), find(v2));
	}
}