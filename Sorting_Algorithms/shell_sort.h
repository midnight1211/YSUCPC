#pragma once
#ifndef _SHELL_SORT_H
#define _SHELL_SORT_H

#include <iostream>
#include "selection_sort.h"
using namespace std;

// Function to sort an array using shell sort
int shellSort(int arr[], int n) {
	// Start with a big gap, then reduce the gap
	for (int gap = n / 2; gap > 0; gap /= 2) {
		// Do a gapped insertion sort for this gap size
		// The first gap elements a[0..gap-1] are already gapped in order
		// keep adding one more element until the entire array is
		// gap sorted
		for (int i = gap; i < n; i++) {
			// add a[i] to the elements that have been gap sorted
			// save a[i] in temp and make a hole at position i
			int temp = arr[i];

			// shift earlier gap-sorted elements up until the correct
			// location for a[i] is found
			int j;
			for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
				arr[j] = arr[j - gap];

			// put temp (the original a[i]) in its correct location
			arr[j] = temp;
		}
	}
	printArray(arr, n);
	return 0;
}

#endif