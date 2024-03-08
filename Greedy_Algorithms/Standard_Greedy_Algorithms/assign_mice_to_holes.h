#pragma once
#ifndef _ASSIGN_MICE_TO_HOLES_H
#define _ASSIGN_MICE_TO_HOLES_H

#include <iostream>
#include <algorithm>
using namespace std;

// Returns minimum time required
// to place mice in holes
int assignHole(int mice[], int holes[], int n, int m) {
	// Baso condition
	// Number of mice and holes should be the same
	if (n != m)
		return -1;

	// Sort the arrays
	sort(mice, mice + n);
	sort(holes, holes + m);

	// Finding max difference between
	// ith mice and hole
	int max = 0;
	for (int i = 0; i < n; i++) {
		if (max < abs(mice[i] - holes[i]))
			max = abs(mice[i] - holes[i]);
	}
	return max;
}

// Driver code
int main() {
	// Position of mice
	int mice[] = { 4, -4, 2 };

	// Position of holes
	int holes[] = { 4, 0, 5 };

	// Number of mice
	int n = sizeof(mice) / sizeof(mice[0]);

	// Number of holes
	int m = sizeof(holes) / sizeof(holes[0]);

	// The required answer is returned
	// from the function
	int minTime = assignHole(mice, holes, n, m);

	cout << "The last mouse gets into the hole in time: " << minTime << endl;

	return 0;
}

#endif