#pragma once
#ifndef _STRUCT_SORTING_H
#define _STRUCT_SORTING_H

#include <iostream>
using namespace std;

struct Student {
	string name; // Given
	int math; // Grade in math (given)
	int phy; // Grade in physics (given)
	int che; // Grade in chemistry (given)
	int total; // Total grade (to be filled)
	int rank; // Rank of student (to be filled)
};

// Function for comparing two students according
// to given rules
bool compareTwoStudents(Student a, Student b) {
	// If total grades are not the same then
	// returns true for higher total
	if (a.total != b.total)
		return a.total > b.total;

	// If grades in math are same then
	// returns true for higher grade
	if (a.msth != b.math)
		return a.math > b.math;

	if (a.phy != b.phy)
		return a.phy > b.phy;

	return (a.che > b.che);
}

// Fills total grades and ranks of all Students
void computeRanks(Student a[], int n) {
	// To calculate total marks for all Students
	for (int i = 0; i < n; i++)
		a[i].total = a[i].math + a[i].phy + a[i].che;

	// Sort structure array using user defined
	// function compareTwoStudents()
	sort(a, a + n, compareTwoStudents);

	// Assigning ranks after sorting
	for (int i = 0; i < n; i++)
		a[i].rank = i + 1;
}

// Driver code
int main() {
	int n = 5;

	// array of structure objects
	Student a[n];

	// Details of Student 1
	a[0].name = "Bryan";
	a[0].math = 80;
	a[0].phy = 95;
	a[0].che = 85;

	// Details of Student 2
	a[1].name = "Kevin";
	a[1].math = 95;
	a[1].phy = 85;
	a[1].che = 99;

	// Details of Student 3
	a[2].name = "Nicky";
	a[2].math = 95;
	a[2].phy = 85;
	a[2].che = 80;

	// Details of Student 4
	a[3].name = "Steve";
	a[3].math = 80;
	a[3].phy = 70;
	a[3].che = 90;

	// Details of Student 5
	a[4].name = "Rohan";
	a[4].math = 80;
	a[4].phy = 80;
	a[4].che = 80;

	computeRanks(a, n);

	// Column names for displaying data
	cout << "Rank"
		<< " "
		<< "Name"
		<< "     ";
	cout << "Maths"
		<< " "
		<< "Physics"
		<< " "
		<< "Chemistry";
	cout << " "
		<< "Total\n";

	// Display details of Students
	for (int i = 0; i < n; i++) {
		cout << a[i].rank << "    ";
		cout << a[i].name << "    ";
		cout << a[i].math << "    " << a[i].phy << "    " << a[i].che << "    ";
		cout << a[i].total << " ";
		cout << "\n";
	}

	return 0;
}

#endif