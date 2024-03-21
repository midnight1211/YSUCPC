public class LinkedListStack {
    public Node top;

    public LinkedListStack() {
        top = null;
    }

    public boolean isEmpty() {
        if (top == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfNodeExists(Node n) {
        Node temp = top;
        boolean exist = false;
        while (temp != null) {
            if (temp.key == n.key) {
                exist = true;
                break;
            }
            temp = temp.next;
        }
        return exist;
    }

    public void push(Node n) {
        if (top == null) {
            top = n;
            System.out.println("Node pushed successfully\n");
        }
        else if (checkIfNodeExists(n)) {
            System.out.println("Node already exists with this key value. Enter different key value.\n");
        }
        else {
            Node temp = top;
            top = n;
            n.next = temp;
            System.out.println("Node pushed successfully\n");
        }
    }

    public Node pop() {
        Node temp = null;
        if (isEmpty()) {
            System.out.println("Stack underflow\n");
            return temp;
        }
        else {
            temp = top;
            top = top.next;
            return temp;
        }
    }

    public Node peek() {
        Node temp = null;
        if (isEmpty()) {
            System.out.println("Stack underflow\n");
            return null;
        }
        else {
            return top;
        }
    }

    public int count() {
        int count  = 0;
        Node temp = top;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public void display() {
        System.out.println("All values in the stack are: \n");
        Node temp = top;
        while (temp != null) {
            System.out.println("(" + temp.key + "," + temp.data + ") -> \n");
            temp = temp.next;
        }
        System.out.println("Total number of nodes in the stack: " + count() + "\n");
    }
}