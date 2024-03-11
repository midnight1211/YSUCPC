#pragma once
#ifndef _SINGLE_CYCLES_H
#define _SINGLE_CYCLES_H

#include <iostream>
#include <vector>
using namespace std;

const int N = 100000;

// degree of all the vertices
int degree[N];

// to keep track of all the vertices covered
// until now
bool found[N];

// all the vertices in a particular
// connected component of the graph
vector<int> curr_graph;

// adjacency list
vector<int> adj_list[N];

// depth-first traversal to identify all the
// nodes in a particular connected graph
// component
void DFS(int v) {
	found[v] = true;
	curr_graph.push_back(v);
	for (int it : adj_list[v])
		if (!found[it])
			DFS(it);
}

// function to add an edge in the graph
void addEdge(vector<int> adj_list[N], int src, int dest) {
	// for index decrement both src and dest.
	src--, dest--;
	adj_list[src].push_back(dest);
	adj_list[dest].push_back(src);
	degree[src]++;
	degree[dest]++;
}

int countSingleCycles(int n, int m) {
	// count of cycle graph components
	int count = 0;
	for (int i = 0; i < n; i++) {
		if (!found[i]) {
			curr_graph.clear();
			DFS(i);

			// traversing the nodes of the
			// current graph component
			int flag = 1;
			for (int v : curr_graph) {
				if (degree[v] == 2)
					continue;
				else {
					flag = 0;
					break;
				}
			}
			if (flag == 1) {
				count++;
			}
		}
	}
	return(count);
}

int main() {
	// n -> number of vertices
	// m -> number of edges
	int n = 15, m = 14;
	addEdge(adj_list, 1, 10);
	addEdge(adj_list, 1, 5);
	addEdge(adj_list, 5, 10);
	addEdge(adj_list, 2, 9);
	addEdge(adj_list, 9, 15);
	addEdge(adj_list, 2, 15);
	addEdge(adj_list, 2, 12);
	addEdge(adj_list, 12, 15);
	addEdge(adj_list, 13, 8);
	addEdge(adj_list, 6, 14);
	addEdge(adj_list, 14, 3);
	addEdge(adj_list, 3, 7);
	addEdge(adj_list, 7, 11);
	addEdge(adj_list, 11, 6);

	cout << countSingleCycles(n, m);

	return 0;
}

#endif
