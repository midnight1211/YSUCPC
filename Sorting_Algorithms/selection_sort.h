#pragma once
#ifndef SELECTION_SORT_H
#define SELECTION_SORT_H

#include <iostream>
#include <algorithm>
using namespace std;

// Selection sort function
void selectionSort(int arr[], int n) {
	int i, j, min_idx;

	// One by one move boundary of unsorted array
	for (i = 0; i < n - 1; i++) {
		// Find the minimum element in the unsorted array
		min_idx = i;
		for (j = i + 1; j < n; j++) {
			if (arr[j] < arr[min_idx]) {
				min_idx = j;
			}
		}

		// Swap the found minimum element with the first element
		if (min_idx != i)
			swap(arr[min_idx], arr[i]);
	}
	printArray(arr[], n);
}

// function to print an array
void printArray(int arr[], int size) {
	int i;
	for (i = 0; i < size; i++) {
		cout << arr[i] << " ";
		cout << endl;
	}
}

#endif