import java.util.ArrayList;
import static java.lang.Math.*;
import static java.lang.Math.min;

public class Searches {
    public static int binarySearch(int[] a, int l, int r, int x) {
        while (1 <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (a[m] == x)
                return m;

            // If x greater, ignore left half
            if (a[m] < x)
                l = m + 1;

            // If x is smaller, ignore right half
            else
                r = m - 1;

        }

        // If we reach here, then element was not present
        return -1;
    }

    public static int exponentialSearch(int[] a, int n, int x) {
        // If x is present at first location itself
        if (a[0] == x)
            return 0;

        // Dind range for binary search
        // repeated doubling
        int i = 1;
        while (i < n && a[i] <= x)
            i = i * 2;

        // Call binary search for the found range
        return binarySearch(a, i / 2, min(i, n-1), x);
    }

    public static int fibonacciSearch(int[] a, int x, int n) {
        // Initialize fibonacci numbers
        int fibMMm2 = 0;            // (m - 2)'th Fibonacci Number
        int fibMMm1 = 1;            // (m - 1)'th Fibonacci Number
        int fibM = fibMMm2 + fibMMm1;   // m'th Fibonacci number

        // fibM is going to store the smallest fibonacci
        // Number greater than or equal to n
        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm1 + fibMMm2;
        }

        // Marks the eliminated range from front
        int offset = -1;

        /* While there are elements to be inspected, note that
        we compare a[fibMMm2] with x. When fibM becomes 1,
        fibMMm2 becomes 0 */
        while (fibM > 1) {
            // Check if fibMm2 is a valid location
            int i = min(offset + fibMMm2, n - 1);

            // If x is greater than the value at index fibMMm2,
            // cut the subarray from offset to i
            if (a[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }

            // If x is greater than the value at index fibMMm2,
            // cut the subarray from offset to i
            if (a[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }

            // If x is greater than the value at index fibMMm2,
            // cut the subarray after i+1
            else if (a[i] > x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }

            // Element found. Return index
            else
                return i;
        }

        // Comparing the last element with x
        if (!(fibMMm1 == 0 || (a[offset + 1] != x))) {
            return -1;
        }

        // element not found, return -1
        return -1;
    }

    // If x is present in arr[0..n-1], then returns
    // index of it, else returns -1
    public int recInterpolationSearch(int[] arr, int lo, int hi, int x) {
        int pos;

        // Since array is sorted, an element present
        // in array must be in range defined by corner
        if (lo <= hi && x >= arr[lo] && x <= arr[hi]) {
            // Probing the positiion with keeping
            // uniform distribution in mind.
            pos = (int) (lo + ((double)(hi - lo) / (arr[hi] - arr[lo])) * (x - arr[lo]));

            // Condition of target found
            if (arr[pos] == x)
                return pos;

            // If x is larger, x is in right subarray
            if (arr[pos] < x)
                return recInterpolationSearch(arr, pos + 1, hi, x);

            // If x is smaller, x is in left subarray
            if (arr[pos] > x)
                return recInterpolationSearch(arr, lo, pos - 1, x);
        }
        return -1;
    }

    public int exponential_search(ArrayList<Integer> a, int x) {
        int n = a.size();

        if (n == 0)
            return -1;

        // Find range for binary search by repeatedly doubling i
        int i = 1;
        while (i < n && a.get(i) < x)
            i *= 2;

        // Perform binary search on the range [i/2, min(i, n-1)]
        int left = i / 2;
        int right = min(i, n - 1);

        while (left <= right) {
            int mid = (left + right) / 2;

            if (a.get(mid) == x)
                return mid;
            else if (a.get(mid) < x)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    public static int ternarySearch(int l, int r, int key, int[] a) {
        if (r >= l) {
            // Find the mid1, mid2
            int mid1 = l + (r - l) / 3;
            int mid2 = r - (r - l) / 3;

            // Check if key is present at any mid
            if (a[mid1] == key)
                return mid1;
            if (a[mid2] == key)
                return mid2;

            // Since key is not present at mid,
            // check in which region it is present
            // then repeat the search operation
            // subarray it may lie
            if (key < a[mid1])
                // The key lies in between l and mid1
                return ternarySearch(l, mid1 - 1, key, a);
            else if (key > a[mid2])
                // The key lies in between mid2 and r
                return ternarySearch(mid2 + 1, r, key, a);
            else
                // The key lies in between mid1 and mid2
                return ternarySearch(mid1 + 1, mid2 - 1, key, a);
        }
            // Key not found
            return -1;
    }

    public static int sentinelSearch(int[] a, int n, int key) {
        // Last element of the array
        int last = a[n - 1];

        // Element to be search is
        // placed at the last index
        a[n - 1] = key;
        int i = 0;

        while (a[i] != key)
            i++;

        // Put the last element back
        a[n - 1] = last;

        if ((i < n - 1) || (a[n - 1] == key))
            System.out.println(key + " is present at index " + i);
        else
            System.out.println("Element not found");
        return last;
    }

    // A recursive binary search function. It returns
    // location of x in given array arr[l..r] is present,
    // otherwise -1
    int recBinarySearch(int[] a, int l, int r, int x) {
        if (r >= 1) {
            int mid = l + (r - l) / 2;

            // If the element is present at the middle
            // itself
            if (a[mid] == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (a[mid] > x)
                return recInterpolationSearch(a, l, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return recInterpolationSearch(a, mid + 1, r, x);
        }

        // We reach here when element is not
        // present in array
        return -1;
    }

    public int bsearch(ArrayList<Integer> a, int key_to_search) {
        int n = (int)a.size();
        // Set number of bits to represent largest array index
        int lg = (int) (Math.log(n - 1) / Math.log(2));

        while ((1 << lg) < n - 1)
            lg++;

        int pos = 0;
        for (int i = lg; i >= 0; i--) {
            if (a.get(pos) == key_to_search)
                return pos;

            // Incrementally construct the
            // index of the target value
            int new_pos = pos | (1 << i);

            // fing the element in one
            // direction and update position
            if ((new_pos < n) && (a.get(new_pos) <= key_to_search))
                pos = new_pos;
        }

        // If element found return pos otherwise -1
        if ((a.get(pos) == key_to_search))
            return pos;
        else
            return -1;
    }

    public int linearSearch(int[] a, int N, int x) {
        for (int j = 0; j < N; j++)
            if (a[j] == x)
                return j;
        return -1;
    }

    public static int jumpSearch(int[] arr, int x, int n) {
        // Finding block size to be jumped
        int step = (int) sqrt(n);

        // Finding the block where element is
        // present (if it is present)
        int prev = 0;
        while (arr[min(step, n) - 1] < x) {
            prev = step;
            step += (int) sqrt(n);
            if (prev >= n)
                return -1;
        }

        // Doing a linear search for x in block
        // beginning with prev.
        while (arr[prev] < x) {
            prev++;

            // If we reached next block or end of
            // array, element is not present
            if (prev == min(step, n))
                return -1;
        }

        // If element is found
        if (arr[prev] == x)
            return prev;

        return -1;
    }

    public // Function to perform Ternary Search
    int itTernarySearch(int l, int r, int key, int[] arr) {
        while (r >= l) {

            // Find the mid1 and mid2
            int mid1 = l + (r - l) / 3;
            int mid2 = r - (r - l) / 3;

            // Check if key is present at any mid
            if (arr[mid1] == key)
                return mid1;
            if (arr[mid2] == key)
                return mid2;

            // Since key is not present at mid,
            // check in which region it is present
            // then repeat the Search operation
            // in that region

            if (key < arr[mid1]) {
                // the key lies inbetween l and mid1
                r = mid1 - 1;
            }
            else if (key > arr[mid2]) {
                // The key lies in between mid2 and r
                l = mid2 + 1;
            }
            else {
                // The key lies inbetween mid1 and mid2
                l = mid1 + 1;
                r = mid2 - 1;
            }

            // Key not found
            return -1;
        }
        return 0;
    }

    public static int interpolationSearch(int[] arr, int n, int x, int x1) {
        // Find indices of two corners
        int low = 0, high = (n - 1);
        // Since array is sorted, an element present
        // in array must be in range defined by corner
        while (low <= high && x >= arr[low] && x <= arr[high]) {
            if (low == high) {
                if (arr[low] == x)
                    return low;
                return -1;
            }
            // Probing the position with keeping
            // uniform distribution in mind
            int pos = (int) (low + (((double)(high - low) / (arr[high] - arr[low])) * (x - arr[low])));

            // Condition of target found
            if (arr[pos] == x)
                return pos;
            // If x is larger, x is in upper part
            if (arr[pos] < x)
                low = pos + 1;
                // If x is smaller, x is in the lower part
            else
                high = pos - 1;
        }
        return -1;
    }

    int iterativeFibonacciSearch(int arr[], int n, int x) {
        if (n == 0)
            return -1;

        // Initialize fibonacci numbers
        int fib1 = 0, fib2 = 1, fib3 = fib1 + fib2;

        // Find the smallest Fibonacci number greater than or equal to n
        while (fib3 < n) {
            fib1 = fib2;
            fib2 = fib3;
            fib3 = fib1 + fib2;
        }

        // Initialize variables for the current and previous split points
        int offset = -1;
        while (fib3 > 1) {
            int i = min(offset + fib2, n - 1);

            // If x is greater than the value at index i, move the split point to the right
            if (arr[i] < x) {
                fib3 = fib2;
                fib2 = fib1;
                fib1 = fib3 - fib2;
                offset = i;
            }

            // If x is less than the value at index i, move the split point to the left
            else if (arr[i] > x) {
                fib3 = fib1;
                fib2 = fib2 - fib1;
                fib1 = fib3 - fib2;
            }

            // If x is equal to the value at index i, return the index
            else {
                return i;
            }
        }

        // If x is not found in the array, return -1
        if (fib2 == 1 && arr[offset + 1] == x) {
            return offset + 1;
        }
        else {
            return -1;
        }
    }

    public static void main(String[] args) {
        // Array to be searched
        int[] arr = {10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100, 235};
        int n = arr.length;

        // Elements to be searched
        int x1 = 10;
        int x2 = 235;
        int x3 = 50;

        // Binary Search
        int resultBinary = binarySearch(arr, 0, n - 1, x1);
        System.out.println("Binary Search: Element " + x1 + " is " +
                (resultBinary == -1 ? "not present" : "present at index " + resultBinary));

        // Exponential Search
        int resultExponential = exponentialSearch(arr, n, x2);
        System.out.println("Exponential Search: Element " + x2 + " is " +
                (resultExponential == -1 ? "not present" : "present at index " + resultExponential));

        // Fibonacci Search
        int resultFibonacci = fibonacciSearch(arr, x2, n);
        System.out.println("Fibonacci Search: Element " + x2 + " is " +
                (resultFibonacci == -1 ? "not present" : "present at index " + resultFibonacci));

        // Interpolation Search
        int resultInterpolation = interpolationSearch(arr, 0, n - 1, x1);
        System.out.println("Interpolation Search: Element " + x1 + " is " +
                (resultInterpolation == -1 ? "not present" : "present at index " + resultInterpolation));

        // Ternary Search
        int resultTernary = ternarySearch(0, n - 1, x3, arr);
        System.out.println("Ternary Search: Element " + x3 + " is " +
                (resultTernary == -1 ? "not present" : "present at index " + resultTernary));

        // Jump Search
        int resultJump = jumpSearch(arr, x1, n);
        System.out.println("Jump Search: Element " + x1 + " is " +
                (resultJump == -1 ? "not present" : "present at index " + resultJump));

        // Sentinel Search
        int resultSentinel = sentinelSearch(arr, n, x3);
        System.out.println("Sentinel Search: Element " + x3 + " is " +
                (resultSentinel == -1 ? "not present" : "present at index " + resultSentinel));
    }
}
