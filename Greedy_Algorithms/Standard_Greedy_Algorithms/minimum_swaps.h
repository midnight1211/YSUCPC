#pragma once
#ifndef _MINIMUM_SWAPS_H
#define _MINIMUM_SWAPS_H

#include <iostream>
#include <algorithm>
using namespace std;

// Function to calculate swaps required
int swapCount(string s) {
	// To store the answer
	int ans = 0;

	// To store the count of '['
	int count = 0;

	// Size of the string
	int n = s.size();

	// Traverse over the string
	for (int i = 0; i < n; i++) {
		// When '[' is encountered
		if (s[i] == '[') { count++; }
		// When ']' is encountered
		else { count--; }
		// When count becomes less than 0
		if (count < 0) {
			// Start searching for '[' from (i+1)th index
			int j = i + 1;
			while (j < n) {
				// When jth index contains '['
				if (s[j] == '[') { break; }
				j++;
			}

			// Increment answer
			ans += j - i;

			// Set count to 1 again
			count = 1;

			// Bring character at jth position to ith position
			// and shift all characters from i to j - 1
			// towards the right
			char ch = s[j];
			for (int k = j; k > i; k--) {
				s[k] = s[k - 1];
			}
			s[i] = ch;
		}
	}
	return ans;
}

// Driver code
int main() {
	string s = "[]][][";
	cout << swapCount(s) << "\n";

	s = "[[][]]";
	cout << swapCount(s) << "\n";

	return 0;
}

#endif