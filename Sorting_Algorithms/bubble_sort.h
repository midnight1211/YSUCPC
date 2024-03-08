#pragma once
#ifndef _BUBBLE_SORT_H
#define _BUBBLE_SORT_H

#include <iostream>
#include <algorithm>
#include "selection_sort.h"
using namespace std;

// An optimized version of bubble sort
void bubbleSort(int arr[], int n) {
	int i, j;
	bool swapped;
	for (i = 0; i < n - 1; i++) {
		swapped = false;
		for (j = 0; j < n - 1; j++) {
			if (arr[j] > arr[j + 1]) {
				swap(arr[j], arr[j + 1]);
				swapped = true;
			}
		}

		// If no two elements were swapped by inner loop, then break
		if (swapped == false)
			break;
	}
	// Print array function from selection_sort.h
	printArray(arr[], n);
}

#endif