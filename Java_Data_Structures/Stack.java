class Stack {
    private int top;
    private int[] arr = new int[5];

    public Stack() {
        top = -1;
        for (int i  = 0; i < 5; i++) {
            arr[i] = 0;
        }
    }

    public boolean isEmpty() {
        if (top == -1)
            return true;
        else
            return false;
    }

    public boolean isFull() {
        if (top == 4)
            return true;
        else
            return false;
    }

    public void push(int val) {
        if (isFull()) {
            System.out.println("Stack overflow\n");
        } else {
            top++;
            arr[top] = val;
        }
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow\n");
        } else {
            int popValue = arr[top];
            arr[top] = 0;
            top--;
            return popValue;
        }
        return 0;
    }

    public int count() {
        return (top + 1);
    }

    public int peek(int pos) {
        if (isEmpty()) {
            System.out.println("Stack underflow\n");
            return 0;
        } else{
            return arr[pos];
        }
    }

    public void change(int pos, int val) {
        arr[pos] = val;
        System.out.println("Value changed at location" + pos);
    }

    public void display() {
        System.out.println("All values in the stack are: \n");
        for (int i = 4; i >= 0; i--) {
            System.out.println(arr[i] + "\n");
        }
    }
}