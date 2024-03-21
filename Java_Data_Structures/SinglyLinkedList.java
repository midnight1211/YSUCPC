class SinglyLinkedList {
    public Node head;

    public SinglyLinkedList() {
        head = null;
    }

    public SinglyLinkedList(Node n) {
        head = n;
    }

    // Check if node exists using key value
    public Node nodeExists(int k) {
        Node temp = null;

        Node ptr = head;
        while (ptr != null) {
            if (ptr.key == k) {
                temp = ptr;
            }
            ptr = ptr.next;
        }
        return temp;
    }

    // Append a node to the list
    public void appendNode(Node n) {
        if (nodeExists(n.key) != null) {
            System.out.println("Node already exists with key value: " + n.key + ". Append another node with different key value\n");
        } else {
            if (head == null) {
                head = n;
                System.out.println("Node appended\n");
            } else {
                Node ptr = head;
                while (ptr.next != null) {
                    ptr = ptr.next;
                }
                ptr.next = n;
            }
            System.out.println("Node appended\n");
        }
    }

    // Prepend node - attach a node to the start
    public void prependNode(Node n) {
        if (nodeExists(n.key) != null) {
            System.out.println("Node alreafy exists with key value: " + n.key + ". Append another node with different key value.\n");
        } else {
            n.next = head;
            head = n;
            System.out.println("Node Prepended\n");
        }
    }

    // Insert a node after a particular node in the list
    public void insertNodeAfter(int k, Node n) {
        Node ptr = nodeExists(k);
        if (ptr == null) {
            System.out.println("No node exists with key value: " + k + "\n");
        }
        else{
            if (nodeExists(n.key) != null) {
                System.out.println("Node already exists with key value: " + n.key + ". Append another node with different key value.\n");
            } else {
                n.next = ptr.next;
                ptr.next = n;
                System.out.println("Node inserted\n");
            }
        }
    }

    // Delete node by unique key
    public void deleteNodeByKey(int k) {
        if (head == null) {
            System.out.println("Singly Linked List already empty. Can not delete\n");
        }
        else if (head != null) {
            if (head.key == k) {
                head = head.next;
                System.out.println("Node UNLINKED with keys value: " + k + "\n");
            } else {
                Node temp = null;
                Node prevptr = head;
                Node currentptr = head.next;
                while (currentptr != null) {
                    if (currentptr.key == k) {
                        temp = currentptr;
                        currentptr = null;
                    } else {
                        prevptr = prevptr.next;
                        currentptr = currentptr.next;
                    }
                }
                if (temp != null) {
                    prevptr.next = temp.next;
                    System.out.println("Node UNLINKED with keys value: " + k + "\n");
                } else {
                    System.out.println("Node doesn't exist with key value: " + k + "\n");
                }
            }
        }
    }

    // Update node
    public void updateNodeByKey(int k, int d) {
        Node ptr = nodeExists(k);
        if (ptr != null) {
            ptr.data = d;
            System.out.println("Node data updated successfully\n");
        } else {
            System.out.println("Node doesn't exist with key value: " + k + "\n");
        }
    }

    // Print
    public void printList() {
        if (head == null) {
            System.out.println("No nodes in singly linked list.\n");
        }
        else {
            System.out.println("\nSingly Linked List Values: ");
            Node temp = head;

            while (temp != null) {
                System.out.println("(" + temp.key + "," + temp.data + ") --> ");
                temp = temp.next;
            }
        }
    }
}