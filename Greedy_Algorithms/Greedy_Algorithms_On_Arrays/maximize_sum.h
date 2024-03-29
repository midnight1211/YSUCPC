#pragma once
#ifndef _MAXIMIZE_SUM_H
#define _MAXIMIZE_SUM_H

#include <iostream>
using namespace std;

// This function does k operations on array in a way that
// maximizes the array sum. index --> stores the index of
// current minimum element for j'th operation
int maximumSum(int arr[], int n, int k) {
	// Modify array K number of times
	for (int i = 1; i <= k; i++) {
		int min = INT_MAX;
		int index = -1;

		// Find minimum element in array for current
		// operation and modify it i.e. arr[j] --> arr[j]
		for (int j = 0; j < n; j++) {
			if (arr[j] < min) {
				min = arr[j];
				index = j;
			}
		}

		// this is the condition if we find 0 as minimum
		// element, so it will be useless to replace 0 by -(0)
		// for remaining operations
		if (min == 0)
			break;

		// Modify element of array
		arr[index] = -arr[index];
	}

	// Calculate sum of array
	int sum = 0;
	for (int i = 0; i < n; i++)
		sum += arr[i];
	return sum;
}

// Driver code
int main() {
	int arr[] = { -2, 0, 5, -1, 2 };
	int k = 4;
	int n = sizeof(arr) / sizeof(arr[0]);
	cout << maximumSum(arr, n, k);
	return 0;
}

#endif