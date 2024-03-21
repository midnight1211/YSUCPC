public class Queue {
    private int front;
    private int rear;
    private int[] arr = new int[5];

    public Queue() {
        front = -1;
        rear = -1;
        for (int i = 0; i < 5; i++) {
            arr[i] = 0;
        }
    }

    public boolean isEmpty() {
        if (front == -1 && rear == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if (rear == 4)
            return true;
        else
            return false;
    }

    public void enqueue(int val) {
        if (isFull()) {
            System.out.println("Queue is full\n");
            return;
        } else if (isEmpty()) {
            rear = 0;
            front = 0;
            arr[rear] = val;
        } else {
            rear++;
            arr[rear] = val;
        }
    }

    public int dequeue() {
        int x;
        if (isEmpty()) {
            System.out.println("Queue is empty\n");
            return 0;
        } else if (front == rear) {
            x = arr[front];
            arr[front] = 0;
            rear = -1;
            front = -1;
            return x;
        } else {
            x = arr[front];
            arr[front] = 0;
            front++;
            return x;
        }
    }

    public int count() {
        return (rear - front + 1);
    }

    public void display() {
        System.out.println("All values in the Queue are - \n");
        for (int i = 0; i < 5; i++) {
            System.out.println(arr[i]);
        }
    }
}
