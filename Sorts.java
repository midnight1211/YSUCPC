import java.util.*;

import static java.lang.Math.random;

class Person {
    private int id;
    private float salary;
    private int someBigObject;

    public Person(int id, float salary) {
        this.id = id;
        this.salary = salary;
        this.someBigObject = 0;
    }

    public float getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Person{id=" + id + ", salary=" + salary + ", someBigObject=" + someBigObject + "}";
    }
}

public class Sorts {

    /* Merge the sorted ranges [low, mid1), [mid1, mid2)
    and [mid2, high) mid1 is first midpoint
    index in overall range to merge mid2 is second
    midpoint index in overall range to merge*/
    public static void merge(ArrayList<Integer> gArray, int low, int mid1, int mid2, int high, ArrayList<Integer> destArray) {
        int i = low, j = mid1, k = mid2, l = high;

        // Choose smaller of the smallest in the three ranges
        while ((i < mid1) && (j < mid2) && (k < high)) {
            if (gArray.get(i) < gArray.get(j)) {
                if (gArray.get(i) < gArray.get(k)) {
                    destArray.set(l++, gArray.get(i++));
                }
                else {
                    destArray.set(l++, gArray.get(k++));
                }
            }
            else {
                if (gArray.get(j) < gArray.get(k)) {
                    destArray.set(l++, gArray.get(j++));
                }
                else {
                    destArray.set(l++, gArray.get(k++));
                }
            }
        }

        // Case where first and second ranges
        // have remaining values
        while ((i < mid1) && (j < mid2)) {
            if (gArray.get(i) < gArray.get(j)) {
                destArray.set(l++, gArray.get(i++));
            }
            else {
                destArray.set(l++, gArray.get(j++));
            }
        }

        // Case where second and third ranges
        // have remaining values
        while ((j < mid2) && (k < high)) {
            if (gArray.get(j) < gArray.get(k)) {
                destArray.set(l++, gArray.get(j++));
            }
            else {
                destArray.set(l++, gArray.get(k++));
            }
        }

        // Case where first and third ranges have
        // remaining values
        while ((i < mid1) && (k < high)) {
            if (gArray.get(i) < gArray.get(k)) {
                destArray.set(l++, gArray.get(k));
            }
            else {
                destArray.set(l++, gArray.get(k++));
            }
        }

        // Copy remaining values from the first range
        while (i < mid1)
            destArray.set(l++, gArray.get(i++));

        // Copy remaining values from the second range
        while (j < mid2)
            destArray.set(l++, gArray.get(j++));

        // Copy remaining values from the third range
        while (k < high)
            destArray.set(l++, gArray.get(k++));
    }

    /* Performing the merge sort algorithm on the
    given array of values in the rangeof indices
    [low, high). low is minimum index, high is
    maximum inndex (exclusive) */
    public static void mergeSort3WayRec(ArrayList<Integer> gArray, int low, int high, ArrayList<Integer> destArray) {
        // If array size is 1 then do nothing
        if (high - low < 2)
            return;

        // Splitting array into 3 parts
        int mid1 = low + ((high - low) / 3);
        int mid2 = low + 2 * ((high - low) / 3) + 1;

        // Sorting 3 arrays recursively
        mergeSort3WayRec(destArray, low, mid1, gArray);
        mergeSort3WayRec(destArray, mid1, mid2, gArray);
        mergeSort3WayRec(destArray, mid2, high, gArray);

        // Merging the sorted arrays
        merge(destArray, low, mid1, mid2, high, gArray);
    }

    public static void mergeSort3Way(ArrayList<Integer> gArray, int n) {
        // If array size is zero return null
        if (n == 0)
            return;

        // creating duplicate of given array
        ArrayList<Integer> fArray = new ArrayList<>(n);

        // copying elements of given array into
        // duplicate array
        for (int i = 0; i < n; i++)
            fArray.set(i, gArray.get(i));

        // sort function
        mergeSort3WayRec(fArray, 0, n, gArray);

        // copy back elements of duplicate array
        // to given array
        for (int i = 0; i < n; i++)
            gArray.set(i, fArray.get(i));
    }

    // Function for finging the maximum and minimum element of the array
    public static void maxMin(int[] vec, int n, int bingo, int nextBingo) {
        for (int i = 1; i < n; bingo = min(bingo, vec[i]));
            ;
    }

    // Function to sort the array
    public static void bingoSort(int[] vec, int n) {
        int bingo = vec[0];
        int nextBingo = vec[0];
        maxMin(vec, n, bingo, nextBingo);
        int largestEle = nextBingo;
        int nextElePos = 0;
        while (bingo < nextBingo) {
            // Will keep track of the element position to shifted to their current position
            int startPos = nextElePos;
            for (int i = startPos; i < n; i++) {
                if (vec[i] == bingo) {
                    swap(vec, i, nextElePos);
                    nextElePos += 1;
                }
                // Here we are finding the next Bingo Element for the neext pass
                else if (vec[i] < nextBingo)
                    nextBingo = vec[i];
            }
            bingo = nextBingo;
            nextBingo = largestEle;
        }
    }

    // Function to print the array
    public static void printUtil(int[] a, int n) {
        System.out.println("Sorted array: ");
        for (int i = 0; i < n; i++) {
            System.out.println(a[i] + " ");
        }
        System.out.println();
    }

    /* The parameter dir indicates the sorting direction, ASCENDING
or DESCENDING; if (a[i] > a[j]) agrees with the direction,
then a[i] and a[j] are interchanged.*/
    void compAndSwap(int[] a, int i, int j, int dir) {
        Comparator<Integer> comparator = dir == 1 ? Comparator.naturalOrder() : Comparator.reverseOrder();
        if (comparator.compare(a[i], a[j]) > 0)
            Arrays.sort(a, i, j);
    }

    /*It recursively sorts a bitonic sequence in ascending order
    if dir = 1, and in descending order otherwise (means dir = 0).
    The sequence to be sorted starts at index position low,
    the parameter cnt is the number of elements to be sorted.*/
    void bitonicMerge(int[] a, int low, int cnt, int dir) {
        if (cnt > 1) {
            int k = cnt / 2;
            for (int i = low; i < low + k; i++)
                compAndSwap(a, i, i + k, dir);
            bitonicMerge(a, low, k, dir);
            bitonicMerge(a, low + k, k, dir);
        }
    }

    /* This function first produces a bitonic sequence by recursively
    sorting its two halves in opposite sorting orders, and then
    calls bitonicMerge to make them in the same order */
    void bitonicSort(int[] a, int low, int cnt, int dir) {
        if (cnt > 1) {
            int k = cnt / 2;

            // sort in ascending order since dir here is 1
            bitonicSort(a, low, k, 1);

            // sort in descending order since dir here is 0
            bitonicSort(a, low + k, k, 0);

            // Will merge whole sequence in ascending order
            // since dir = 1.
            bitonicMerge(a, low, cnt, dir);
        }
    }

    /*Caller of bitonicSort for sorting the entire array of
    length N in ASCENDING order */
    void sort(int[] a, int N, int up) {
        bitonicSort(a, 0, N, up);
    }

    // A function to sort the algorithm using Odd Even Sort
    void oddEvenSort(int[] arr, int n) {
        boolean isSorted = false; // Initially the array is unsorted
        while (!isSorted) {
            isSorted = true;

            // Perform Bubble Sort on odd indexed element
            for (int i = 1; i <= n - 2; i = i + 2) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, arr[i], arr[i + 1]);
                    isSorted = false;
                }
            }

            // Perform Bubble sort on even indexed element
            for (int i = 0; i <= n - 2; i = i + 2) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, arr[i], arr[i + 1]);
                    isSorted = false;
                }
            }
        }

        return;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    // An optimized version of Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // Function to sort arr[] of size n using bucket sort
    public static void bucketSort(float[] arr, int n) {
        if (n <= 0) return;

        // 1.) Create n empty buckets
        ArrayList<Float>[] buckets = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<Float>();
        }

        // 2.) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) (n * arr[i]);
            buckets[bucketIndex].add(arr[i]);
        }

        // 3.) Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // 4.) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (float value : buckets[i]) {
                arr[index++] = value;
            }
        }
    }

    // Sorts array a[0, ..., n-1] using Cocktail Sort
    void cocktailSort(int[] a, int n) {
        boolean swapped = true;
        int start = 0;
        int end = n - 1;

        while (swapped) {
            // reset the swapped flag on entering
            // the loop, because it might be true from
            // a previous iteration
            swapped = false;

            // loop from left to right same as
            // the bubble sort
            for (int i = start; i < end; i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, a[i], a[i + 1]);
                    swapped = true;
                }
            }

            // if nothing moved, then array is sorted
            if (!swapped)
                break;

            // otherwise, reset the swapped flag so that it
            // can be used in the next stage
            swapped = false;

            // move the end point back by one, because
            // item at the end is in its rightful spot
            --end;

            // from right to left, doing the
            // same comparison as in the previous stage
            for (int i = end - 1; i >= start; --i) {
                if (a[i] > a[i + 1]) {
                    swap(a, a[i], a[i + 1]);
                    swapped = true;
                }
            }

            // increase the starting point, because
            // the last stage would have moved the next
            // smallest number to its rightful spot
            ++start;
        }
    }

    // To find gap between elements
    private static int getNextGap(int gap) {
        // Shrink gap by shrink factor
        gap = (gap * 10) / 13;

        if (gap < 1)
            return 1;
        return gap;
    }

    // Function to sort a[0..n-1] using comb sort
    public static void combSort(int[] a, int n) {
        // Initialize gap
        int gap = n;

        // Initialize swapped as true to make sure that loop runs
        boolean swapped = true;

        // Keep running while gap is more than 1 and last iteration caused a swap
        while (gap != 1 || swapped == true) {
            // Find next gap
            gap = getNextGap(gap);

            // Initialize swapped as false so that we can check if swap happened or not
            swapped = false;

            // Compare all elements with current gap
            for (int i = 0; i < n - gap; i++) {
                if (a[i] > a[i + gap]) {
                    swap(a, a[i], a[i + gap]);
                    swapped = true;
                }
            }
        }
        printUtil(a, n);
    }

    public static ArrayList<Integer> countSort(ArrayList<Integer> inputArray) {
        int N = inputArray.size();

        // Finding the maximum element in inputArray
        int M = Collections.max(inputArray);

        // Initializing countArray with 0
        int[] countArray = new int[M + 1];

        // Mapping each element of inputArray as an index of countArray
        for (int i = 0; i < N; i++) {
            countArray[inputArray.get(i)]++;
        }

        // Calculating prefix sum at every index of countArray
        for (int i = 1; i <= M; i++) {
            countArray[i] += countArray[i - 1];
        }

        // Creating outputArray from countArray
        ArrayList<Integer> outputArray = new ArrayList<>(Collections.nCopies(N, 0));

        for (int i = N - 1; i >= 0; i--) {
            outputArray.set(countArray[inputArray.get(i)] - 1, inputArray.get(i));
            countArray[inputArray.get(i)]--;
        }

        return outputArray;
    }

    // Function to sort the array using Cycle Sort
    public static void cycleSort(int[] arr, int n) {
        // Count number of memory writes
        int writes = 0;

        // Traverse array elements and put them in the right place
        for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++) {
            // Initialize item as starting point
            int item = arr[cycle_start];

            // Find position where we put the item by counting all smaller elements on right side of item
            int pos = cycle_start;
            for (int i = cycle_start + 1; i < n; i++) {
                if (arr[i] < item) {
                    pos++;
                }
            }

            // If item is already in correct position
            if (pos == cycle_start) {
                continue;
            }

            // Ignore all duplicate elements
            while (item == arr[pos]) {
                pos += 1;
            }

            // Put the item to its right position
            if (pos != cycle_start) {
                int temp = item;
                item = arr[pos];
                arr[pos] = temp;
                writes++;
            }

            // Rotate the rest of the cycle
            while (pos != cycle_start) {
                pos = cycle_start;

                // Find position where we put the element
                for (int i = cycle_start + 1; i < n; i++) {
                    if (arr[i] < item) {
                        pos++;
                    }
                }

                // Ignore all duplicate elements
                while (item == arr[pos]) {
                    pos += 1;
                }

                // Put the item to its right position
                if (item != arr[pos]) {
                    int temp = item;
                    item = arr[pos];
                    arr[pos] = temp;
                    writes++;
                }
            }
        }

        // Number of memory writes or swaps
        System.out.println("Number of memory writes: " + writes);
    }

    public static void gnomeSort(int[] a, int n) {
        int index = 0;

        while (index < n) {
            if (index == 0)
                index++;
            if (a[index] >= a[index - 1])
                index++;
            else {
                swap(a, a[index], a[index - 1]);
                index--;
            }
        }
        return;
    }

    // Function to heapify a subtree rooted with node i, which is an index in arr[]
    // N is the size of the heap
    public static void heapify(int[] arr, int N, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left child
        int r = 2 * i + 2; // right child

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest]) {
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest]) {
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            // Swap arr[i] with arr[largest]
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(arr, N, largest);
        }
    }

    // Main function to do heap sort
    public static void heapSort(int[] arr) {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(arr, N, i);
        }

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int swap = arr[0];
            arr[0] = arr[i];
            arr[i] = swap;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // Utility function to print the array
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] a, int n) {
        int i, key, j;
        for (i = 1; i < n; i++) {
            key = a[i];
            j = i - 1;

            // Move elements of arr[0..i-1],
            // that are greater than key,
            // to one position ahead of their
            // current position
            while (j >= 0 && a[j] > key) {
                a[j+1] = a[j];
                j -= 1;
            }
            a[j + 1] = key;
        }
        printUtil(a, n);
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[left..mid]
    // Second subarray is arr[mid+1..right]
    public static void merge(int[] arr, int left, int mid, int right) {
        // Find the sizes of two subarrays to be merged
        int subArrayOne = mid - left + 1;
        int subArrayTwo = right - mid;

        // Create temporary arrays
        int[] leftArray = new int[subArrayOne];
        int[] rightArray = new int[subArrayTwo];

        // Copy data to temporary arrays
        for (int i = 0; i < subArrayOne; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < subArrayTwo; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        // Initial indexes of first and second subarrays
        int indexOfSubArrayOne = 0, indexOfSubArrayTwo = 0;

        // Initial index of merged array
        int indexOfMergedArray = left;

        // Merge the temporary arrays back into arr[left..right]
        while (indexOfSubArrayOne < subArrayOne && indexOfSubArrayTwo < subArrayTwo) {
            if (leftArray[indexOfSubArrayOne] <= rightArray[indexOfSubArrayTwo]) {
                arr[indexOfMergedArray] = leftArray[indexOfSubArrayOne];
                indexOfSubArrayOne++;
            } else {
                arr[indexOfMergedArray] = rightArray[indexOfSubArrayTwo];
                indexOfSubArrayTwo++;
            }
            indexOfMergedArray++;
        }

        // Copy the remaining elements of leftArray[], if any
        while (indexOfSubArrayOne < subArrayOne) {
            arr[indexOfMergedArray] = leftArray[indexOfSubArrayOne];
            indexOfSubArrayOne++;
            indexOfMergedArray++;
        }

        // Copy the remaining elements of rightArray[], if any
        while (indexOfSubArrayTwo < subArrayTwo) {
            arr[indexOfMergedArray] = rightArray[indexOfSubArrayTwo];
            indexOfSubArrayTwo++;
            indexOfMergedArray++;
        }
    }

    // Main function that sorts arr[left..right] using merge()
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Reverses arr[0..i]
    public static void flip(int[] arr, int i) {
        int temp, start = 0;
        while (start < i) {
            temp = arr[start];
            arr[start] = arr[i];
            arr[i] = temp;
            start++;
            i--;
        }
    }

    // Returns index of the maximum element in arr[0..n-1]
    public static int findMax(int[] arr, int n) {
        int mi = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[mi]) {
                mi = i;
            }
        }
        return mi;
    }

    // The main function that sorts the given array using flip operations
    public static void pancakeSort(int[] arr, int n) {
        // Start from the complete array and reduce current size by one
        for (int curr_size = n; curr_size > 1; curr_size--) {
            // Find index of the maximum element in arr[0..curr_size-1]
            int mi = findMax(arr, curr_size);

            // Move the maximum element to end of current array if it's not already at the end
            if (mi != curr_size - 1) {
                // To move at the end, first move maximum number to beginning
                flip(arr, mi);

                // Now move the maximum number to end by reversing current array
                flip(arr, curr_size - 1);
            }
        }
    }

    // To check if array is sorted or not
    public static boolean isSorted(int[] a, int n) {
        while (--n > 0)
            if (a[n] < a[n - 1])
                return false;
        return true;
    }

    // To generate permutation of the array
    public static void shuffle(int[] a, int n) {
        for (int i = 0; i < n; i++)
            swap(a, i, (int) (random() % n));
    }

    // Sorts array a[0, ..., n-1] using bogo sort (aka permutation sort)
    public static void bogoSort(int[] a, int n) {
        // If array is not sorted, shuffle it again
        while (!isSorted(a, n))
            shuffle(a, n);
    }

    // Sorts the array using the pigeonhole algorithm
    public static void pigeonholeSort(int[] arr, int n) {
        // Find minimum and maximum values in arr[]
        int min = arr[0], max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < min)
                min = arr[i];
            if (arr[i] > max)
                max = arr[i];
        }
        int range = max - min + 1; // Find range

        // Create an array of lists. Size of array is range.
        // Each list represents a hole that is going to contain matching elements.
        List<Integer>[] holes = new ArrayList[range];
        for (int i = 0; i < range; i++) {
            holes[i] = new ArrayList<>();
        }

        // Traverse through the input array and put every element in its respective hole
        for (int i = 0; i < n; i++) {
            holes[arr[i] - min].add(arr[i]);
        }

        // Traverse through all holes one by one.
        // For every hole, take its elements and put them back in the array.
        int index = 0; // Index in the sorted array
        for (int i = 0; i < range; i++) {
            for (int num : holes[i]) {
                arr[index++] = num;
            }
        }
    }

    public static int partition(int[] a, int low, int high) {
        // choose the pivot
        int pivot = a[high];
        // Index of smaller element and indicate the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high; j++) {
            // If current element is maller than the pivot
            if (a[j] < pivot) {
                // Incroement index of smaller element
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i+1, high);
        return (i+1);
    }

    public static void quickSort(int[] a, int low, int high) {
        // when low is less than high
        if (low < high) {
            // pi is the partition return index of pivot
            int pi = partition(a, low, high);

            // Recursion call
            // Smaller element than pivot goes left and higher element goes right
            quickSort(a, low, pi-1);
            quickSort(a, pi+1, high);
        }
        int n = a.length/ a[0];
        printUtil(a, n);
    }

    // A utility function to get maximum value in arr[]
    public static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // A function to do counting sort of arr[]
    // according to the digit represented by exp.
    public static void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n]; // Output array
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this digit in the output array
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now contains sorted
        // numbers according to the current digit
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // The main function that sorts arr[] of size n using Radix Sort
    public static void radixSort(int[] arr, int n) {
        // Find the maximum number to know the number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that instead of passing digit
        // number, exp is passed. exp is 10^i where i is the current digit number
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(arr, n, exp);
        }
    }

    // Selection Sort
    public static void selectionSort(int[] a, int n) {
        int i, j, min_idx;

        // One by one move boundary of unsorted subarray
        for (i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            min_idx = i;
            for (j = i + 1; j < n; j++) {
                if (a[j] < a[min_idx]) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first element
            if (min_idx != i)
                swap(a, min_idx, i);
        }
        printUtil(a, n);
    }

    // Function to sort an array using Shell Sort
    public static int shellSort(int[] a, int n) {
        // Start with a big gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size
            // The first gap elements a[o..gap-1] are already gapped in order
            // keep adding one more element until the entire array is
            // gap sorted
            for (int i = gap; i < n; i++) {
                // add a[i] to the elements that have been gap sorted
                // save a[i] in temp and make a hole at position i
                int temp = a[i];

                // shift earlier gap-sorted elements up until the correct
                // location for a[i] is found
                int j;
                for (j = i; j >= gap && a[j - gap] > temp; j -= gap)
                    a[j] = a[j - gap];

                // put temp (the original a[i]) in its correct loaction
                a[j] = temp;
            }
        }
        printUtil(a, n);
        return 0;
    }

    // Runnable class to perform the sleep and print operation
    static class SleepSortTask implements Runnable {
        private final int number;

        SleepSortTask(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            try {
                // Sleep for 'number' milliseconds
                Thread.sleep(number);
                // After sleep, print the number
                System.out.print(number + " ");
            } catch (InterruptedException e) {
                // Handle interruption
                Thread.currentThread().interrupt();
            }
        }
    }

    // Function to perform sleep sort
    public static void sleepSort(int[] arr) {
        // An array of threads, one for each of the elements in the input array
        Thread[] threads = new Thread[arr.length];

        // Create and start the threads for each of the input array elements
        for (int i = 0; i < arr.length; i++) {
            threads[i] = new Thread(new SleepSortTask(arr[i]));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < arr.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // Handle interruption
                Thread.currentThread().interrupt();
            }
        }
    }

    // Function to implement stooge sort
    public static void stoogeSort(int[] a, int l, int h) {
        if (l >= h)
            return;

        // If first element is smaller than last,
        // swap them
        if (a[l] > a[h])
            swap(a, l, h);

        // If there are more than 2 elements in
        // the array
        if (h - l + 1 > 2) {
            int t = (int) Math.floor((h - l + 1) / 3.0);

            // Recursively sort first 2/3 elements
            stoogeSort(a, l, h - t);

            // Recursively sort last 2/3 elements
            stoogeSort(a, l + t, h);

            // Recursively sort first 2/3 elements
            // again to confirm
            stoogeSort(a, l, h - t);
        }
    }

    // Function to perform Strand sort
    public static void strandSort(ArrayList<Integer> ip, ArrayList<Integer> op) {
        // Base case: input is empty
        if (ip.isEmpty()) {
            return;
        }

        // Create a sorted sublist with
        // first item of input list as
        // first item of the sublist
        ArrayList<Integer> sublist = new ArrayList<>();
        sublist.add(ip.remove(0)); // pop_front equivalent

        // Traverse remaining items of ip list
        for (int i = 0; i < ip.size(); ) {
            // If current item of input list
            // is greater than last added item
            // to sublist, move current item
            // to sublist as sorted order is
            // maintained
            if (ip.get(i) > sublist.get(sublist.size() - 1)) {
                sublist.add(ip.remove(i));
            } else {
                i++;
            }
        }

        // Merge current sublist into output
        op.addAll(sublist);
        Collections.sort(op);

        // Recur for remaining items in
        // input and current items in op
        strandSort(ip, op);
    }

    static class Student {
        String name;
        int math;
        int phy;
        int che;
        int total;
        int rank;

        Student(String name, int math, int phy, int che) {
            this.name = name;
            this.math = math;
            this.phy = phy;
            this.che = che;
            this.total = 0;
            this.rank = 0;
        }
    }

    // Comparator to compare two students based on total grades and individual subject grades
    static class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student a, Student b) {
            if (a.total != b.total) {
                return Integer.compare(b.total, a.total); // Higher total grades first
            }
            if (a.math != b.math) {
                return Integer.compare(b.math, a.math); // Higher math grades first
            }
            if (a.phy != b.phy) {
                return Integer.compare(b.phy, a.phy); // Higher physics grades first
            }
            return Integer.compare(b.che, a.che); // Higher chemistry grades first
        }
    }

    // Function to fill total grades and ranks of all students
    static void computeRanks(Student[] students) {
        // Calculate total grades for all students
        for (Student student : students) {
            student.total = student.math + student.phy + student.che;
        }

        // Sort students using the custom comparator
        Arrays.sort(students, new StudentComparator());

        // Assign ranks after sorting
        for (int i = 0; i < students.length; i++) {
            students[i].rank = i + 1;
        }
    }

    public static void tagSort(List<Person> persons, List<Integer> tag) {
        int n = persons.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (persons.get(tag.get(i)).getSalary() > persons.get(tag.get(j)).getSalary()) {
                    // Note that we are not sorting the actual Persons array, but only the tag array
                    int temp = tag.get(i);
                    tag.set(i, tag.get(j));
                    tag.set(j, temp);
                }
            }
        }
    }

    private static final int RUN = 32; // Size of run for insertion sort

    // Helper method for finding the minimum of two values
    private static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    // Helper method for insertion sort
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Helper method to merge two subarrays


    // Main Timsort function
    public static void timSort(int[] arr) {
        int n = arr.length;

        // Sort individual subarrays of size RUN
        for (int i = 0; i < n; i += RUN) {
            insertionSort(arr, i, min((i + RUN - 1), (n - 1)));
        }

        // Start merging from size RUN (or 32)
        for (int size = RUN; size < n; size = 2 * size) {
            // Pick starting point of left subarray
            for (int left = 0; left < n; left += 2 * size) {
                // Find ending point of left subarray
                int mid = left + size - 1;
                int right = min((left + 2 * size - 1), (n - 1));

                // Merge subarray arr[left..mid] & arr[mid+1..right]
                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    static class Node {
        int key;
        Node left, right;

        Node(int item) {
            key = item;
            left = right = null;
        }
    }

    // A utility function to create a new BST node
    private static Node newNode(int item) {
        return new Node(item);
    }

    // Stores inorder traversal of the BST in arr[]
    private static void storeSorted(Node root, int[] arr, int[] index) {
        if (root != null) {
            storeSorted(root.left, arr, index);
            arr[index[0]++] = root.key;
            storeSorted(root.right, arr, index);
        }
    }

    // A utility function to insert a new node with given key in BST
    private static Node insert(Node node, int key) {
        if (node == null)
            return newNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        return node;
    }

    // This function sorts arr[0..n-1] using Tree Sort
    public static void treeSort(int[] arr) {
        Node root = null;

        // Construct the BST
        for (int key : arr) {
            root = insert(root, key);
        }

        // Store inorder traversal of the BST in arr[]
        int[] index = {0};
        storeSorted(root, arr, index);
    }

    public static void main(String[] args) {
        int[] originalArray = generateRandomArray(10);

        // Bubble Sort
        int[] arr1 = originalArray.clone();
        bubbleSort(arr1);
        System.out.println("Bubble Sort: " + Arrays.toString(arr1));
        printArray(arr1);

        // Insertion Sort
        int[] arr2 = originalArray.clone();
        insertionSort(arr2, arr2.length);
        System.out.println("Insertion Sort: " + Arrays.toString(arr2));
        printArray(arr2);

        // Merge Sort
        int[] arr3 = originalArray.clone();
        mergeSort(arr3, 0, arr3.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(arr3));
        printArray(arr3);

        // Quick Sort
        int[] arr4 = originalArray.clone();
        quickSort(arr4, 0, arr4.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(arr4));
        printArray(arr4);

        // Heap Sort
        int[] arr5 = originalArray.clone();
        heapSort(arr5);
        System.out.println("Heap Sort: " + Arrays.toString(arr5));
        printArray(arr5);

        // Radix Sort
        int[] arr6 = originalArray.clone();
        radixSort(arr6, arr6.length);
        System.out.println("Radix Sort: " + Arrays.toString(arr6));
        printArray(arr6);

        // Additional sorts can be added similarly...
    }

    // Utility method to generate a random array
    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100); // Random numbers between 0 and 99
        }
        return arr;
    }
}
