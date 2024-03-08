#pragma once
#ifndef _CIRCULAR_LINKED_LIST_H
#define _CIRCULAR_LINKED_LIST_H

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

class CircularLinkedList {
public:
	Node* head;
	
	CircularLinkedList() {
		head = NULL;
	}

	// 1. Check if node exists using key value
	Node* nodeExists(int k) {
		Node* temp = NULL;
		Node* ptr = head;

		if (ptr == NULL) {
			return temp;
		}
		else {
			do {
				if (ptr->key == k) {
					temp = ptr;
				}
				ptr = ptr->next;
			} while (ptr != head);
			return temp;
		}

		return temp;
	}

	// 2. Append a node to the list
	void appendNode(Node* new_node) {
        if (nodeExists(new_node->key) != NULL) {
            cout << "Node Already exists with key value : " <<
                new_node->key <<
                ". Append another node with different Key value" <<
                endl;
        }
        else {
            if (head == NULL) {
                head = new_node;
                new_node->next = head;
                cout << "Node Appended at first Head position" << endl;
            }
            else {
                Node* ptr = head;
                while (ptr->next != head) {
                    ptr = ptr->next;
                }
                ptr->next = new_node;
                new_node->next = head;
                cout << "Node Appended" << endl;
            }
        }

    }
    // 3. Prepend Node - Attach a node at the start
    void prependNode(Node* new_node) {
        if (nodeExists(new_node->key) != NULL) {
            cout << "Node Already exists with key value : " <<
                new_node->key <<
                ". Append another node with different Key value" <<
                endl;
        }
        else {
            if (head == NULL) {
                head = new_node;
                new_node->next = head;
                cout << "Node Prepended at first Head position" << endl;
            }
            else {
                Node* ptr = head;
                while (ptr->next != head) {
                    ptr = ptr->next;
                }

                ptr->next = new_node;
                new_node->next = head;
                head = new_node;
                cout << "Node Prepended" << endl;
            }

        }
    }

    // 4. Insert a Node after a particular node in the list
    void insertNodeAfter(int k, Node* new_node) {
        Node* ptr = nodeExists(k);
        if (ptr == NULL) {
            cout << "No node exists with key value OF: " << k << endl;
        }
        else {
            if (nodeExists(new_node->key) != NULL) {
                cout << "Node Already exists with key value : " <<
                    new_node->key <<
                    ". Append another node with different Key value" <<
                    endl;
            }
            else {
                if (ptr->next == head) {
                    new_node->next = head;
                    ptr->next = new_node;
                    cout << "Node Inserted at the End" << endl;
                }
                else {
                    new_node->next = ptr->next;
                    ptr->next = new_node;
                    cout << "Node Inserted in between" << endl;
                }

            }
        }
    }

    // 5. Delete node by unique key
    void deleteNodeByKey(int k) {
        Node* ptr = nodeExists(k);
        if (ptr == NULL) {
            cout << "No node exists with key value OF : " << k <<
                endl;
        }
        else {

            if (ptr == head) {
                if (head->next == NULL) {
                    head = NULL;
                    cout << "Head node Unlinked... List Empty";
                }
                else {
                    Node* ptr1 = head;
                    while (ptr1->next != head) {
                        ptr1 = ptr1->next;
                    }
                    ptr1->next = head->next;
                    head = head->next;
                    cout << "Node UNLINKED with keys value : " << k << endl;
                }
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

                prevptr->next = temp->next;
                cout << "Node UNLINKED with keys value : " << k << endl;

            }

        }

    }
    // 6th update node
    void updateNodeByKey(int k, int new_data) {

        Node* ptr = nodeExists(k);
        if (ptr != NULL) {
            ptr->data = new_data;
            cout << "Node Data Updated Successfully" << endl;
        }
        else {
            cout << "Node Doesn't exist with key value : " << k << endl;
        }

    }

    // 7th printing
    void printList() {
        if (head == NULL) {
            cout << "No Nodes in Circular Linked List";
        }
        else {
            cout << endl << "head address : " << head << endl;
            cout << "Circular Linked List Values : " << endl;

            Node* temp = head;

            do {
                cout << "(" << temp->key << "," << temp->data << "," << temp->next << ") --> ";
                temp = temp->next;
            } while (temp != head);
        }

    }

};

#endif