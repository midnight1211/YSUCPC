import java.lang.Math;
import java.lang.Integer;
public class MinHeap {
    public int[] harr;
    public int capacity;
    public int heap_size;

    MinHeap(int cap) {
        heap_size = 0;
        capacity = cap;
        harr = new int[cap];
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int left(int i){
        return (2 * i + 1);
    }

    int right(int i) {
        return (2 * i + 2);
    }

    int getMin() {
        return harr[0];
    }

    public void insertKey(int k) {
        if (heap_size == capacity) {
            System.out.println("\nOverflow: could not insert key\n");
            return;
        }

        heap_size++;
        int i = heap_size - 1;
        harr[i] = k;

        // Fix the min heap property if it is violated
        while (i != 0 && harr[parent(i)] > harr[i]) {
            int temp = harr[i];
            harr[i] = harr[parent(i)];
            harr[parent(i)] = temp;
            i = parent(i);
        }
    }

    public void decreaseKey(int i, int new_val) {
        harr[i] = new_val;
        while(i != 0 && harr[parent(i)] > harr[i]) {
            int temp = harr[i];
            harr[i] = harr[parent(i)];
            harr[parent(i)] = temp;
            i = parent(i);
        }
    }

    public void deleteKey(int i) {
        decreaseKey(i, Integer.MAX_VALUE);
        extractMin();
    }

    public void linearSearch(int val) {
        for (int i = 0; i < heap_size; i++) {
            if (harr[i] == val) {
                System.out.println("Value found!");
                return;
            }
        }

        System.out.println("Value not found.");
    }

    public int extractMin() {
        if (heap_size <= 0) {
            return Integer.MAX_VALUE;
        }
        if (heap_size == 1) {
            heap_size--;
            return harr[0];
        }

        int root = harr[0];
        harr[0] = harr[heap_size - 1];
        heap_size--;
        minHeapify(0);
        return root;
    }

    public void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap_size && harr[l] < harr[i])
            smallest = l;
        if (r < heap_size && harr[r] > harr[smallest])
            smallest = r;
        if (smallest != i) {
            int temp = harr[i];
            harr[i] = harr[smallest];
            harr[smallest] = temp;
            minHeapify(smallest);
        }
    }

    public void printArray() {
        for (int i = 0; i < heap_size; i++) {
            System.out.println(harr[i] + " ");
            System.out.println("\n");
        }
    }

    public int height() {
        return (int)(Math.ceil(Math.log(heap_size + 1) / Math.log(2))) - 1;
    }
}
