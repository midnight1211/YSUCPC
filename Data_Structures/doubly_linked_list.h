#pragma once
#ifndef _DOUBLY_LINKED_LIST_H
#define _DOUBLY_LINKED_LIST_H

#include <iostream>
using namespace std;

class Node {
public:
	int key;
	int data;
	Node* next;
    Node* previous;

	Node() {
		key = 0;
		data = 0;
		next = NULL;
		previous = NULL;
	}

	Node(int k, int d) {
		key = k;
		data = d;
	}
};

class DoublyLinkedList {

public:
    Node* head;

    DoublyLinkedList() {
        head = NULL;
    }
    DoublyLinkedList(Node* n) {
        head = n;
    }

    // 1. CHeck if node exists using key value
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
            cout << "Node Already exists with key value : " << n->key << ". Append another node with different Key value" << endl;
        }
        else {
            if (head == NULL) {
                head = n;
                cout << "Node Appended as Head Node" << endl;
            }
            else {
                Node* ptr = head;
                while (ptr->next != NULL) {
                    ptr = ptr->next;
                }
                ptr->next = n;
                n->previous = ptr;
                cout << "Node Appended" << endl;
            }
        }
    }

    // 3. Prepend Node - Attach a node at the start
    void prependNode(Node* n) {
        if (nodeExists(n->key) != NULL) {
            cout << "Node Already exists with key value : " << n->key << ". Append another node with different Key value" << endl;
        }
        else {
            if (head == NULL) {
                head = n;
                cout << "Node Prepended as Head Node" << endl;
            }
            else {
                head->previous = n;
                n->next = head;
                head = n;
                cout << "Node Prepended" << endl;
            }

        }
    }

    // 4. Insert a Node after a particular node in the list
    void insertNodeAfter(int k, Node* n) {
        Node* ptr = nodeExists(k);
        if (ptr == NULL) {
            cout << "No node exists with key value: " << k << endl;
        }
        else {
            if (nodeExists(n->key) != NULL) {
                cout << "Node Already exists with key value : " << n->key << ". Append another node with different Key value" << endl;
            }
            else {
                Node* nextNode = ptr->next;
                // inserting at the end
                if (nextNode == NULL) {
                    ptr->next = n;
                    n->previous = ptr;
                    cout << "Node Inserted at the END" << endl;
                }

                //inserting in between
                else {
                    n->next = nextNode;
                    nextNode->previous = n;
                    n->previous = ptr;
                    ptr->next = n;

                    cout << "Node Inserted in Between" << endl;
                }
            }
        }
    }

    // 5. Delete node by unique key. Basically De-Link not delete
    void deleteNodeByKey(int k) {
        Node* ptr = nodeExists(k);
        if (ptr == NULL) {
            cout << "No node exists with key value: " << k << endl;
        }
        else {
            if (head->key == k) {
                head = head->next;
                cout << "Node UNLINKED with keys value : " << k << endl;
            }
            else {
                Node* nextNode = ptr->next;
                Node* prevNode = ptr->previous;
                // deleting at the end
                if (nextNode == NULL) {
                    prevNode->next = NULL;
                    cout << "Node Deleted at the END" << endl;
                }

                //deleting in between
                else {
                    prevNode->next = nextNode;
                    nextNode->previous = prevNode;
                    cout << "Node Deleted in Between" << endl;
                }
            }
        }
    }

    // 6th update node
    void updateNodeByKey(int k, int d) {

        Node* ptr = nodeExists(k);
        if (ptr != NULL) {
            ptr->data = d;
            cout << "Node Data Updated Successfully" << endl;
        }
        else {
            cout << "Node Doesn't exist with key value : " << k << endl;
        }
    }

    // 7th printing
    void printList() {
        if (head == NULL) {
            cout << "No Nodes in Doubly Linked List";
        }
        else {
            cout << endl << "Doubly Linked List Values : ";
            Node* temp = head;

            while (temp != NULL) {
                cout << "(" << temp->key << "," << temp->data << ") <--> ";
                temp = temp->next;
            }
        }
    }
};

#endif