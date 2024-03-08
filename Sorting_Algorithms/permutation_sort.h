#pragma once
#ifndef _PERMUTATION_SORT_H
#define _PERMUTATION_SORT_H

#include <iostream>
using namespace std;

// To check if array is sorted or not
bool isSorted(int a[], int n) {
	while (--n > 0)
		if (a[n] < a[n - 1])
			return false;
	return true;
}

// To generate permutation of the array
void shuffle(int a[], int n) {
	for (int i = 0; i < n; i++)
		swap(a[i], a[rand() % n]);
}

// Sorts array a[0..n-1] using bogo sort (aka permutation sort)
void bogoSort(int a[], int n) {
	// if array is not sorted then shuffle
	// the array again
	while (!isSorted(a, n))
		shuffle(a, n);
}

// Print the array
void printArray(int a[], int n) {
	for (int i = 0; i < n; i++)
		cout << a[i] << " ";
	cout << "\n";
}

// Driver code
int main() {
	int a[] = { 3, 2, 5, 1, 0, 4 };
	int n = sizeof(a) / sizeof(a[0]);
	bogoSort(a, n);
	cout << "Sorted array: \n";
	printArray(a, n);
	return 0;
}

#endif