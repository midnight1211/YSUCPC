#pragma once
#ifndef _SHORTEST_JOB_FIRST_H
#define _SHORTEST_JOB_FIRST_H

#include <iostream>
using namespace std;

int shortestJobFirst() {
	// Matrix for storing process id, burst
	// time, average waiting time, and average
	// turn around time
	int A[100][4];
	int i, j, n, total = 0, index, temp;
	float avg_wt, avg_tat;

	cout << "Enter number of processes: ";
	cin >> n;

	cout << "Enter burst time: " << endl;

	// User input burst time and alloting process id
	for (i = 0; i < n; i++) {
		cout << "P" << i + 1 << ": ";
		cin >> A[i][1];
		A[i][0] = i + 1;
	}

	// Sorting processes according to their burst time
	for (i = 0; i < n; i++) {
		index = i;
		for (j = i + 1; j < n; j++)
			if (A[j][1] < A[index][1])
				index = j;
		temp = A[i][1];
		A[i][1] = A[index][1];
		A[index][1] = temp;

		temp = A[i][0];
		A[i][0] = A[index][0];
		A[index][0] = temp;
	}

	A[0][2] = 0;
	// Calculation of waiting times
	for (i = 1; i < n; i++) {
		A[i][2] = 0;
		for (j = 0; j < i; j++)
			A[i][2] += A[j][1];
		total += A[i][2];
	}

	avg_wt = (float)total / n;
	total = 0;
	cout << "P     BT     WT     TAT" << endl;

	// Calculation of turn around time and printing the data.
	for (i = 0; i < n; i++) {
		A[i][3] = A[i][1] + A[i][2];
		total += A[i][3];
		cout << "P" << A[i][0] << "     " << A[i][1] << "     " << A[i][2] << "     " << A[i][3] << endl;
	}

	avg_tat = (float)total / n;
	cout << "Average Waiting Time: " << avg_wt << endl;
	cout << "Average Turn around Time: " << avg_tat << endl;

	return 0;
}

#endif