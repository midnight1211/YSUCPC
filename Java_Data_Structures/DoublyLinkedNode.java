public class DoublyLinkedNode {
    public int key;
    public int data;
    public DoublyLinkedNode next;
    public DoublyLinkedNode previous;

    public DoublyLinkedNode() {
        key = 0;
        data = 0;
        next = null;
        previous = null;
    }

    public DoublyLinkedNode(int k, int d) {
        key = k;
        data = d;
    }
}
