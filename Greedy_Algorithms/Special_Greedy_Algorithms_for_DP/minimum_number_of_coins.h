#pragma once
#ifndef _MINIMUM_NUMBER_OF_COINS_H
#define _MINIMUM_NUMBER_OF_COINS_H

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// All denominations of bills
int denomination[] = { 1, 2, 5, 10, 20, 50, 100 };
int n = sizeof(denomination) / sizeof(denomination[0]);

void findMin(int V) {
	sort(denomination, denomination + n);

	// Initialize result
	vector<int> ans;

	// Traverse through all denominations
	for (int i = n - 1; i >= 0; i--) {
		// Find denominations
		while (V >= denomination[i]) {
			V -= denomination[i];
			ans.push_back(denomination[i]);
		}
	}

	// Print result
	for (int i = 0; i < ans.size(); i++)
		cout << ans[i] << " ";
}

// Driver code
int driver() {
	int n = 93;
	cout << "Following is minimal number of change for " << n << ": ";

	// Function call
	findMin(n);
	return 0;
}

#endif