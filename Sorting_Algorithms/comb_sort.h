#pragma once
#ifndef _COMB_SORT_H
#define _COMB_SORT_H

#include <iostream>
#include <algorithm>
#include "selection_sort.h"
using namespace std;

// To find gap between elements
int getNextGap(int gap) {
	// Shrink gap by shrink factor
	gap = (gap * 10) / 13;

	if (gap < 1)
		return 1;
	return gap;
}

// function to sort a[0..n-1] using comb sort
void combSort(int arr[], int n) {
	//Initialize gap
	int gap = n;

	// Initialize swapped as true to make sure that loop runs
	bool swapped = true;

	// Keep running while gap is more than 1 and last iteration caused a swap
	while (gap != 1 || swapped == true) {
		// Find next gap
		gap = getNextGap(gap);

		// Initialize swapped as false so that we can check if swap happened or not
		swapped = false;

		// Compare all elements with current gap
		for (int i = 0; i < n - gap; i++) {
			if (arr[i] > arr[i + gap]) {
				swap(arr[i], arr[i + gap]);
				swapped = true;
			}
		}
	}
	printArray(arr, n);
}

#endif