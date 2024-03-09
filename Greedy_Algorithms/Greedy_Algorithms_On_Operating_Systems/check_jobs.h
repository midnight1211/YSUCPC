#pragma once
#ifndef _CHECK_JOBS_H
#define _CHECK_JOBS_H

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool checkJobs(int startin[], int endin[], int n) {
	// making a pair of starting and ending times of jobs
	vector<pair<int, int> > a;
	for (int i = 0; i < n; i++)
		a.push_back(make_pair(startin[i], endin[i]));
	
	// sorting according to starting time of job
	sort(a.begin(), a.end());

	// starting first and second job simultaneously
	long long tv1 = a[0].second, tv2 = a[1].second;

	for (int i = 2; i < n; i++) {
		// Checking if starting time of next new job
		// is greater than ending time of currently
		// scheduled first job
		if (a[i].first >= tv1) {
			tv1 = tv2;
			tv2 = a[i].second;
		}

		// Checking if starting time of next new job
		// is greater than ending time of currently
		// scheduled second job
		else if (a[i].first >= tv2)
			tv2 = a[i].second;

		else
			return false;
	}
	return true;
}

// Driver code
int main() {
	int startin[] = { 1, 2, 4 };
	int endin[] = { 2, 3, 5 };
	int n = sizeof(startin) / sizeof(startin[0]);
	cout << checkJobs(startin, endin, n);
	return 0;
}

#endif