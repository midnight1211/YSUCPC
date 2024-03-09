#pragma once
#ifndef _NON-INCREASING_ARRAY_H
#define _NON-INCREASING_ARRAY_H

#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int decreasingArray(int a[], int n) {
	int sum = 0, dif = 0;

	// min heap
	priority_queue<int, vector<int>, greater<int> > pq;

	// Here in the loop we'll check that whether
	// the top of priority queue is less than
	// the upcoming array element. If yes then
	// we calculate the difference. After that
	// we will remove that element and push the
	// current element in queue. And the sum
	// is incremented by the value of the difference
	for (int i = 0; i < n; i++) {
		if (!pq.empty() && pq.top() < a[i]) {
			dif = a[i] - pq.top();
			sum += dif;

			// Removing that minimum element
			// which does follow the decreasing
			// property and replacing it with a[i]
			// to maintain the condition
			pq.pop();
			pq.push(a[i]);
		}

		// Push the current element as well
		pq.push(a[i]);
	}
	return sum;
}

// Driver code
int main() {
	int a[] = { 3, 1, 2, 1 };
	int n = sizeof(a) / sizeof(a[0]);

	cout << decreasingArray(a, n);

	return 0;
}

#endif