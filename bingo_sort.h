#pragma once
#ifndef _BINGO_SORT_H
#define _BINGO_SORT_H

#include <iostream>
#include <vector>
using namespace std;

// Function for finding the maximum and minimun=m element of the array
void maxMin(vector<int> vec, int n, int& bingo. int& nextBingo) {
	for (int i = 1; i < n; bingo = min(bingo, vec[i]), nextbingo = max(nextBingo, vec[i]), i++)
		;
}

// Function to sort the array
void bingoSort(vector<int>& vec, int n) {
	int bingo = vec[0];
	int nextBingo = vec[0];
	maxMin(vec, n, bingo, nextBingo);
	int largestEle = nextBingo;
	int nextElePos = 0;
	while (bingo < nextBingo) {
		// Will keep track of the element position to shifted to their current position
		int startPos = nextElePos;
		for (int i = startPos; i < n; i++) {
			if (vec[i] == bingo) {
				swap(vec[i], vec[nextElePos]);
				nextElePos = nextElePos + 1;
			}
			// Here we are finding the next Bingo Element for the next pass
			else if (vec[i] < nextBingo)
				nextBingo = vec[i];
		}
		bingo = nextBingo;
		nextBingo = largestEle;
	}
}

// Function to print the array
void printUtil(vector<int> arr, int n) {
	cout << "Sorted array: ";
	for (int i = 0; i < n; i++) {
		cout << arr[i] << " ";
	}
	cout << endl;
}

void bingoDriver() {
	vector<int> arr = { 5, 4, 8, 5, 4, 8, 5, 4, 4, 4 };
	bingoSort(arr, arr.size());
	printUtil(arr, arr.size());

	vector<int> arr2 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
	bingoSort(arr2, arr2.size());
	printUtil(arr2, arr2.size());

	vector<int> arr3 = { 0, 1, 0, 1, 0, 1 };
	bingoSort(arr3, arr3.size());
	printUtil(arr3, arr3.size());
}

#endif