#pragma once
#ifndef _MAX_DIFFERENCE_H
#define _MAX_DIFFERENCE_H

#include <algorithm>
#include <iostream>
using namespace std;

// Function to calculate max_difference
int maxDifference(int arr[], int n, int k) {
	int m, s = 0, s1 = 0, max_difference = 0;

	// Sum of the array
	for (int i = 0; i < n; i++)
		s += arr[i];

	// Sort the array in descending order
	sort(arr, arr + n, greater<int>());
	m = max(k, n - k);
	for (int i = 0; i < m; i++)
		s1 += arr[i];

	// Calculating max_difference
	max_difference = s1 - (s - s1);
	return max_difference;
}

// Driver function
int main() {
	int arr[] = { 8, 4, 5, 2, 10 };
	int n = sizeof(arr) / sizeof(arr[0]);
	int k = 2;
	cout << maxDifference(arr, n, k) << endl;
	return 0;
}

#endif