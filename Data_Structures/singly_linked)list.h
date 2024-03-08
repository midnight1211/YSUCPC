#pragma once

#ifndef _SINGLY_LINKED_LIST_H
#define _SINGLY_LINKED_LIST_H

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
	}
};

class SinglyLinkedList {
public:
	Node* head;

	SinglyLinkedList() {
		head = NULL;
	}

	SinglyLinkedList(Node* n) {
		head = n;
	}

	// 1. Check if node exists using key value
	Node* nodeExists(int k) {
		Node* temp = NULL;

		Node* ptr = head;
		while (ptr != NULL) {
			if (ptr->key == k) {
				temp = ptr;
			}
			ptr = ptr->next;
		}
		return temp;
	}

	// 2. Append a node to the list
	void appendNode(Node* n) {
		if (nodeExists(n->key) != NULL) {
			cout << "Node already exists with key value: " << n->key << ". Append another node with different key value" << endl;
		}
		else {
			if (head == NULL) {
				head = n;
				cout << "Node appended" << endl;
			}
			else {
				Node* ptr = head;
				while (ptr->next != NULL) {
					ptr = ptr->next;
				}
				ptr->next = n;
			}
			ptr->next = n;
			cout << "Node appended" << endl;
		}
	}

	// 3. Prepend node - attach a node to the start
	void prependNode(Node* n) {
		if (nodeExists(n->key) != NULL) {
			cout << "Node already exists with key value: " << n->key << ". Append another node with different key value" << endl;
		}
		else {
			n->next = head;
			head = n;
			cout << "Node Prepended" << endl;
		}
	}

	// 4. Insert a node after a particular node in the list
	void insertNodeAfter(int k, Node* n) {
		if (ptr == NULL) {
			cout << "No node exists with key value: " << k << endl;
		}
		else {
			if (nodeExists(n->key) != NULL) {
				cout << "Node already exists with key value: " << n->key << ". Append another node with different key value." << endl;
			}
			else {
				n->next = ptr->next;
				ptr->next = n;
				cout << "Node inserted" << endl;
			}
		}
	}

	// 5. Delete node by unique key
	void deleteNodeByKey(int k) {
		if (head == NULL) {
			cout << "Singly Linked List already empty. Can not delete" << endl;
		}
		else if (head != NULL) {
			if (head->key == k) {
				head = head->next;
				cout << "Node UNLINKED with keys value: " << k << endl;
			}
			else {
				Node* temp = NULL;
				Node* prevptr = head;
				Node* currentptr = head->next;
				while (currentptr != NULL) {
					if (currentptr->key == k) {
						temp = currentptr;
						currentptr = NULL;
					}
					else {
						prevptr = prevptr->next;
						currentptr = currentptr->next;
					}
				}
				if (temp != NULL) {
					prevptr->next = temp->next;
					cout << "Node UNLINKED with keys value: " << k << endl;
				}
				else {
					cout << "Node doesn't exist with key value: " << k << endl;
				}
			}
		}
	}

	// 6. Update node
	void updateNodeByKey(int k, int d) {
		Node* ptr = nodeExists(k);
		if (ptr != NULL) {
			ptr->data = d;
			cout << "Node data updated successfully" << endl;
		}
		else {
			cout << "Node doesn't exist with key value: " << k << endl;
		}
	}


	// 7. Print
	void printList() {
		if (head == NULL) {
			cout << "No nodes in singly linked list" << endl;
		}
		else {
			cout << endl << "Singly Linked List Values: ";
			Node* temp = head;

			while (temp != NULL) {
				cout << "(" << temp->key << "," << temp->data << ") --> ";
				temp = temp->next;
			}
		}
	}
};

#endif