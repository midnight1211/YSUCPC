public class DoublyLinkedList {
    public DoublyLinkedNode head;

    public DoublyLinkedList() {
        head = null;
    }

    public DoublyLinkedList(DoublyLinkedNode n) {
        head = n;
    }

    public DoublyLinkedNode nodeExists(int k) {
        DoublyLinkedNode temp = null;
        DoublyLinkedNode ptr = head;

        while (ptr != null) {
            if (ptr.key == k) {
                temp = ptr;
            }
            ptr = ptr.next;
        }

        return temp;
    }

    public void appendNode(DoublyLinkedNode n) {
        if (nodeExists(n.key) != null) {
            System.out.println("Node already exists with key value: " + n.key + ". Append another node with a different key value.\n");
        } else {
            if (head == null) {
                head = n;
                System.out.println("Node appended as Head node.\n");
            } else {
                DoublyLinkedNode ptr = head;
                while (ptr.next != null) {
                    ptr = ptr.next;
                }
                ptr.next = n;
                n.previous = ptr;
                System.out.println("Node Appended\n");
            }
        }
    }

    public void prependNode(DoublyLinkedNode n) {
        if (nodeExists(n.key) != null) {
            System.out.println("Node already exists with key value: " + n.key + ". Append another node with a different key value.\n");
        }
        else {
            if (head == null) {
                head = n;
                System.out.println("Node prepended as Head node.\n");
            }
            else {
                head.previous = n;
                n.next = head;
                head = n;
                System.out.println("Node prepended.\n");
            }
        }
    }

    public void insertNode(int k, DoublyLinkedNode n) {
        DoublyLinkedNode ptr = nodeExists(k);
        if (ptr == null) {
            System.out.println("No node exists with key value: " + k + "\n");
        }
        else {
            if (nodeExists(n.key) != null) {
                System.out.println("Node already exists with key value: " + n.key + ". Append another node with different key value.\n");
            }
            else {
                DoublyLinkedNode nextNode = ptr.next;
                if (nextNode == null) {
                    ptr.next = n;
                    n.previous = ptr;
                    System.out.println("Node inserted at the END\n");
                }
                else {
                    n.next = nextNode;
                    nextNode.previous = n;
                    n.previous = ptr;
                    ptr.next = n;
                    System.out.println("Node inserted in between\n");
                }
            }
        }
    }

    public void deleteNodeByKey(int k) {
        DoublyLinkedNode ptr = nodeExists(k);
        if (ptr == null) {
            System.out.println("No node exists with key value: " + k + "\n");
        }
        else {
            if (head.key == k) {
                head = head.next;
                System.out.println("Node UNLINKED with key value: " + k + "\n");
            }
            else {
                DoublyLinkedNode nextNode = ptr.next;
                DoublyLinkedNode prevNode = ptr.previous;
                if (nextNode == null) {
                    prevNode.next = null;
                    System.out.println("Node deleted at the END\n");
                }

                else {
                    prevNode.next = nextNode;
                    nextNode.previous = prevNode;
                    System.out.println("Node deleted in between\n");
                }
            }
        }
    }

    public void updateNodeByKey(int k, int d) {
        DoublyLinkedNode ptr = nodeExists(k);
        if (ptr != null) {
            ptr.data = d;
            System.out.println("Node data updated successfully.\n");
        }
        else {
            System.out.println("Node doesn't exist with key value: " + k + "\n");
        }
    }

    public void printList() {
        if (head == null) {
            System.out.println("No nodes in doubly linked list.");
        }
        else {
            System.out.println("\nDoubly Linked List Values: ");
            DoublyLinkedNode temp = head;

            while (temp != null) {
                System.out.println("(" + temp.key + "," + temp.data + ") <--> ");
                temp = temp.next;
            }
        }
    }
}