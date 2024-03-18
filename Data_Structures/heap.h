#ifndef _HEAP_H
#define _HEAP_H

#include <iostream>
#include <math.h>
using namespace std;

// A utility function to swap two elements
void swap(int& x, int& y) {
	int temp = x;
	x = y;
	y = temp;
}

class MinHeap {
public:
	int* harr;		// pointer to array of elements in heap
	int capacity;	// maximum possible size of min heap
	int heap_size;	// current number of elements in min heap

	MinHeap(int cap) {
		heap_size = 0;
		capacity = cap;
		harr = new int[cap];
	}
	int parent(int i) {
		return (i - 1) / 2;
	}

	int left(int i) {
		return (2 * i + 1);
	}

	int right(int i) {
		return (2 * i + 2);
	}

	int getMin() {
		return harr[0];
	}

	void insertKey(int k) {
		if (heap_size == capacity) {
			cout << "\nOverflow: could not insert key\n";
			return;
		}

		// First insert the new key at the end
		heap_size++;
		int i = heap_size - 1;
		harr[i] = k;

		// Fix the min heap property if it is violated
		while (i != 0 && harr[parent(i)] > harr[i]) {
			swap(harr[i], harr[parent(i)]);
			i = parent(i);
		}
	}

	void decreaseKey(int i, int new_val) {
		harr[i] = new_val;
		while (i != 0 && harr[parent(i)] > harr[i]) {
			swap(&harr[i], &harr[parent(i)]);
			i = parent(i);
		}
	}

	// This function deletes key at index i. It first reduced value to minus
	// infinite, then calls extractMin()
	void deleteKey(int i) {
		decreaseKey(i, INT_MIN);
		extractMin();
	}

	void linearSearch(int val) {
		for (int i = 0; i < heap_size; i++) {
			if (harr[i] == val) {
				cout << "Value found!";
				return;
			}
		}
		cout << "Value not found.";
	}

	// Method to remove minimum element (or root) from min heap
	int extractMin() {
		if (heap_size <= 0) {
			return INT_MAX;
		}
		if (heap_size == 1) {
			heap_size--;
			return harr[0];
		}

		// Store the minimum value, and remove it from the heap
		int root = harr[0];
		harr[0] = harr[heap_size - 1];
		heap_size--;
		minHeapify(0);
		return root;
	}

	// A recursive method to heapify a subtree with the root at given index.
	// This method assumes that the subtrees are already heapified
	void minHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heap_size && harr[l] < harr[i])
			smallest = l;
		if (r < heap_size && harr[r] > harr[smallest])
			smallest = r;
		if (smallest != i) {
			swap(&harr[i], &harr[smallest]);
			minHeapify(smallest);
		}
	}

	void printArray() {
		for (int i = 0; i < heap_size; i++) {
			cout << harr[i] < " ";
			cout << endl;
		}
	}

	int height() {
		return ceil(log2(heap_size + 1)) - 1;
	}
};

#endif
