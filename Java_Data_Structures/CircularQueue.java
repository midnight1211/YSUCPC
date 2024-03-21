import java.util.Arrays;
public class CircularQueue {
    private int front;
    private int rear;
    private int arr[];
    private int itemCount;

    public CircularQueue() {
        itemCount = 0;
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
        if ((rear + 1) % 5 == front) {
            return true;
        } else {
            return false;
        }
    }

    public void enqueue(int val) {
        if (isFull()) {
            System.out.println("Queue is full\n");
            return;
        }
        else if (isEmpty()) {
            rear = 0;
            front = 0;
            arr[rear] = val;
        }
        else {
            rear = (rear + 1) % 5;
            arr[rear] = val;
        }
        itemCount++;
    }

    public int dequeue() {
        int x = 0;
        if (isEmpty()) {
            System.out.println("Queue is empty\n");
            return 0;
        }
        else if (rear == front) {
            x = arr[rear];
            rear = -1;
            front = -1;
            itemCount--;
            return x;
        }
        else {
            System.out.println("Front value: " + front + "\n");
            x = arr[front];
            arr[front] = 0;
            front = (front + 1) % 5;
            itemCount--;
            return x;
        }
    }

    public int count() {
        return itemCount;
    }

    public void display() {
        System.out.println("All values in the Queue are: \n");
        for (int i = 0; i < 5; i++) {
            System.out.println(arr[i] + " ");
        }
    }
}
