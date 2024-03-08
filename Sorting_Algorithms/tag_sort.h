#pragma once
#ifndef _TAG_SORT_H
#define _TAG_SORT_H

#include <iostream>
#include <vector>

class Person {
private:
	int id;
	float salary;
	int someBigObject;

public:
	Person(int id, float salary)
		: id(id), salary(salary), someBigObject(0) {}

	float getSalary)() {
		return salary;
	}

	// The toString method in C++ can be replaced with the friend ostream operator overloading.
	friend std::ostream& operator<<(std::ostream& os, const Person& person) {
		os << "Person{" << "id=" << person.id << ", salary=" << person.salary << ", someBigObject=" << person.someBigObject << "}";
		return os;
	}
};

void tagSort(std::vector<Person>& persons, std::vector<int>& tag) {
	int n = persons.size();
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if (persons[tag[i]].getSalary() > persons[tag[j]].getSalary()) {
				// Note that we are not sorting the actual Persons array, but only the tag array
				int temp = tag[i];
				tag[i] = tag[j];
				tag[j] = temp;
			}
		}
	}
}

int main() {
	// Creating objects and their original order (in tag vector)
	int n = 5;
	std::vector<Person> persons;
	persons.push_back(Person(0, 233.5f));
	persons.push_back(Person(1, 23.0f));
	persons.push_back(Person(2, 33.98f));
	persons.push_back(Person(3, 143.2f));
	persons.push_back(Person(4, 3.0f));

	std::vector<int> tag(n);
	for (int i = 0; i < n; i++) {
		tag[i] = i;
	}

	// Every Person object is tagged to an element in the tag vector.
	std::cout << "Given Person and Tag " << std::endl;
	for (int i = 0; i < n; i++) {
		std::cout << persons[i] << " : Tag: " << tag[i] << std::endl;
	}

	// Modifying the tag vector so that we can access persons in sorted order.
	tagSort(persons, tag);

	std::cout << "New Tag Array after getting sorted as per Person[]" << std::endl;
	for (int i = 0; i < n; i++) {
		std::cout << tag[i] << std::endl;
	}

	// Accessing persons in sorted (by salary) way using the modified tag vector
	for (int i = 0; i < n; i++) {
		std::cout << persons[tag[i]] << std::endl;
	}
}

#endif