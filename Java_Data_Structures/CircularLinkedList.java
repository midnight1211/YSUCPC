public class CircularLinkedList {
    public CircularLinkedNode head;

    CircularLinkedList() {
        head = null;
    }

    public CircularLinkedNode nodeExists(int k) {
        CircularLinkedNode temp = null;
        CircularLinkedNode ptr = head;

        if (ptr == null) {
            return temp;
        }
        else {
            do {
                if (ptr.key == k) {
                    temp = ptr;
                }
                ptr = ptr.next;
            } while (ptr != head);
        }
        return temp;
    }

    public void appendNode(CircularLinkedNode new_node) {
        if (nodeExists(new_node.key) != null) {
            System.out.println("Node already exists with key value: " + new_node.key + ". Append another node with different key value.\n");
        }
        else {
            if (head == null) {
                head = new_node;
                System.out.println("Node appended at first Head position.\n");
            }
            else {
                CircularLinkedNode ptr = head;
                while (ptr.next != head) {
                    ptr = ptr.next;
                }
                ptr.next = new_node;
                new_node.next = head;
                System.out.println("Node appended.\n");
            }
        }
    }

    public void prependNode(CircularLinkedNode new_node) {
        if (nodeExists(new_node.key) != null) {
            System.out.println("Node already exists with key value: " + new_node.key + ". Append another node with different key value.\n");
        }
        else {
            if (head == null) {
                head = new_node;
                new_node.next = head;
                System.out.println("Node prepended at first Head position.\n");
            }
            else {
                CircularLinkedNode ptr = head;
                while (ptr.next != head) {
                    ptr = ptr.next;
                }

                ptr.next = new_node;
                new_node.next = head;
                head = new_node;
                System.out.println("Node Prepended.\n");
            }
        }
    }

    public void insertNodeAfter(int k, CircularLinkedNode new_node) {
        CircularLinkedNode ptr = nodeExists(k);
        if (ptr == null) {
            System.out.println("No node exists with key value of: " + k + "\n");
        }
        else {
            if (nodeExists(new_node.key) != null) {
                System.out.println("Node already exists with key value: " + new_node.key + ", Append another node with different key value.\n");
            }
            else {
                if (ptr.next == head) {
                    new_node.next = head;
                    ptr.next = new_node;
                    System.out.println("Node inserted at the end.\n");
                }
                else {
                    new_node.next = ptr.next;
                    ptr.next = new_node;
                    System.out.println("Node inserted in between.\n");
                }
            }
        }
    }

    public void deleteNodeByKey(int k) {
        CircularLinkedNode ptr = nodeExists(k);
        if (ptr == null) {
            System.out.println("No node exists with key value of: " + k + "\n");
        }
        else {
            if (ptr == head) {
                if (head.next == null) {
                    head = null;
                    System.out.println("Head node unlinked... List Empty.\n");
                }
                else {
                    CircularLinkedNode ptr1 = head;
                    while (ptr1.next != head) {
                        ptr1 = ptr1.next;
                    }
                    ptr1.next = head.next;
                    head = head.next;
                    System.out.println("Node unlinked with key value: " + k + "\n");
                }
            }
            else {
                CircularLinkedNode temp = null;
                CircularLinkedNode prevptr = head;
                CircularLinkedNode currentptr = head.next;
                while (currentptr != null) {
                    if (currentptr.key == k) {
                        temp = currentptr;
                        currentptr = null;
                    }
                    else {
                        prevptr = prevptr.next;
                        currentptr = currentptr.next;
                    }
                }

                prevptr.next = temp.next;
                System.out.println("Node unlinked with key value: " + k + "\n");
            }
        }
    }

    public void updateBykey(int k, int new_data) {
        CircularLinkedNode ptr = nodeExists(k);
        if (ptr != null) {
            ptr.data = new_data;
            System.out.println("Node data updated successfully.\n");
        }
        else {
            System.out.println("Node doesn't exist with key value: " + k + "\n");
        }
    }

    public void printList() {
        if (head == null) {
            System.out.println("No nodes in circular linked list.");
        }
        else {
            System.out.println("\nhead addreass: " + head + "\n");
            System.out.println("Circular Linked List Values: \n");

            CircularLinkedNode temp = head;

            do {
                System.out.println("(" + temp.key + "," + temp.next + ") --> ");
                temp = temp.next;
            } while (temp != head);
        }
    }
}