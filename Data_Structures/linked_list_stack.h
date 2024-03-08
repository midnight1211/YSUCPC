#ifndef _LINKED_LIST_STACK_H
#define _LINKED_LIST_STACK_H

#include <iostream>
using namespace std;

class Node {
public:
	int key;
	int data;
	Node* next;

	Node() {
		key = 0;
		data = 0;
		next = NULL;
	}

	Node(int k, int d) {
		key = k;
		data = d;
		next = NULL;
	}
};

class Stack {
public:
	Node* top;

	Stack() {
		top = NULL;
	}

	bool isEmpty() {
		if (top == NULL) {
			return true;
		}
		else {
			return false;
		}
	}

	bool checkIfNodeExists(Node* n) {
		Node* temp = top;
		bool exist = false;
		while (temp != NULL) {
			if (temp->key == n->key) {
				exist = true;
				break;
			}
			temp = temp->next;
		}
		return exist;
	}

	void push(Node* n) {
		if (top == NULL) {
			top = n;
			cout << "Node pushed successfully" << endl;
		}
		else if (checkIfNodeExists(n)) {
			cout << "Node already exists with this Key value." << "Enter different key value." << endl;
		}
		else {
			Node* temp = top;
			top = n;
			n->next = temp;
			cout << "Node pushed successfully" << endl;
		}
	}

	Node* pop() {
		Node* temp = NULL;
		if (isEmpty()) {
			cout << "stack underflow" << endl;
			return temp;
		}
		else {
			temp = top;
			top = top->next;
			return temp;
		}
	}

	Node* peek() {
		// Node *temp = NULL;
		if (isEmpty()) {
			cout << "stack underflow" << endl;
			return NULL;
		}
		else {
			return top;
		}
	}

	int count() {
		int count = 0;
		Node* temp = top;
		while (temp != NULL) {
			count++;
			temp = temp->next;
		}
		return count;
	}

	void display() {
		cout << "All values in the stack are: " << endl;
		Node* temp = top;
		while (temp != NULL) {
			cout << "(" << temp->key << "," << temp->data << ")" << " -> " << endl;
			temp = temp->next;
		}
		cout << "Total number of nodes in the stack:" << count() << endl;
	}
};

#endif