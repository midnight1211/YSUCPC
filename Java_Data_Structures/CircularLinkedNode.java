public class CircularLinkedNode {
    public int key;
    public int data;
    public CircularLinkedNode next;

    CircularLinkedNode() {
        key = 0;
        data = 0;
        next = null;
    }

    CircularLinkedNode(int k, int d) {
        key = k;
        data = d;
    }
}
