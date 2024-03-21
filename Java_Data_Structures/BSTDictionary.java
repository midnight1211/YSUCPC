import java.util.Arrays;

public class BSTDictionary<KeyType, ValueType> {
    private static final int NULL_INDEX = 0xffffffff;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private int root;
    private static int[] counts;
    private static int[] heights;
    private static int[] left;
    private static int[] right;
    private static int nTrees;
    private static int capacity;
    private static int freeListHead;
    private KeyType[] keys;
    private ValueType[] values;

    public BSTDictionary(int _cap) {
        if (nTrees == 0) {
            counts = new int[_cap];
            heights = new int[_cap];
            left = new int[_cap];
            right = new int[_cap];
            keys = (KeyType[]) new Object[_cap];
            values = (ValueType[]) new Object[_cap];

            generateFreeList(0, _cap);
            capacity = _cap;
        }

        nTrees++;
        root = NULL_INDEX;
    }

    public BSTDictionary() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public void clear() {
        prvClear(root);
        root = NULL_INDEX;
    }

    public int size() {
        return count(root);
    }

    public int height() {
        return calculateHeight(root);
    }

    public boolean isEmpty() {
        return (root == NULL_INDEX);
    }

    public ValueType search(KeyType k) {
        int n = root;
        while (n != NULL_INDEX) {
            if (k.equals(keys[n])) {
                return values[n];
            } else if (((Comparable<KeyType>) k).compareTo(keys[n]) < 0) {
                n = left[n];
            } else {
                n = right[n];
            }
        }

        throw new IllegalArgumentException("Search: Key not found");
    }

    public ValueType get(KeyType k) {
        return search(k);
    }

    public void put(KeyType k, ValueType v) {
        int temp = prvAllocate();

        int n = temp;
        root = prvInsert(root, n, k);

        if (n != temp) {
            prvFree(temp);
        }

        values[n] = v;
    }

    public void remove(KeyType k) {
        int ntbd = NULL_INDEX;
        root = prvRemove(root, ntbd, k);
    }

    private void generateFreeList(int start, int end) {
        for (int i = start; i < end - 1; i++) {
            left[i] = i + 1;
        }
        left[end - 1] = NULL_INDEX;
        freeListHead = start;
    }

    private int prvAllocate() {
        if (freeListHead == NULL_INDEX) {
            int[] tmpCounts = Arrays.copyOf(counts, 2 * capacity);
            int[] tmpHeights = Arrays.copyOf(heights, 2 * capacity);
            int[] tmpLeft = Arrays.copyOf(left, 2 * capacity);
            int[] tmpRight = Arrays.copyOf(right, 2 * capacity);
            KeyType[] tmpKeys = Arrays.copyOf(keys, 2 * capacity);
            ValueType[] tmpValues = Arrays.copyOf(values, 2 * capacity);

            counts = tmpCounts;
            heights = tmpHeights;
            left = tmpLeft;
            right = tmpRight;
            keys = tmpKeys;
            values = tmpValues;

            for (int i = capacity; i < 2 * capacity - 1; i++) {
                left[i] = i + 1;
            }

            left[2 * capacity - 1] = NULL_INDEX;
            freeListHead = capacity;

            capacity *= 2;
        }

        int temp = freeListHead;
        freeListHead = left[freeListHead];
        left[temp] = NULL_INDEX;
        right[temp] = NULL_INDEX;
        counts[temp] = 1;
        heights[temp] = 1;

        return temp;
    }

    private void prvFree(int n) {
        left[n] = freeListHead;
        freeListHead = n;
    }

    private void prvClear(int r) {
        if (r != NULL_INDEX) {
            prvClear(left[r]);
            prvClear(right[r]);
            prvFree(r);
        }
    }

    private int count(int r) {
        if (r == NULL_INDEX) {
            return 0;
        } else {
            return 1 + count(left[r]) + count(right[r]);
        }
    }

    private int calculateHeight(int r) {
        if (r == NULL_INDEX) {
            return 0;
        } else {
            int lHeight = calculateHeight(left[r]);
            int rHeight = calculateHeight(right[r]);
            return Math.max(lHeight, rHeight) + 1;
        }
    }

    private void prvAdjust(int r) {
        counts[r] = getCount(left[r]) + getCount(right[r]) + 1;
        heights[r] = Math.max(getHeight(left[r]), getHeight(right[r])) + 1;
    }

    private int getCount(int index) {
        return (index != NULL_INDEX) ? counts[index] : 0;
    }

    private int getHeight(int index) {
        return (index != NULL_INDEX) ? heights[index] : 0;
    }

    private int prvInsert(int r, int n, KeyType k) {
        if (r == NULL_INDEX) {
            keys[n] = k;
            return n;
        }

        if (((Comparable<KeyType>) k).compareTo(keys[r]) == 0) {
            n = r;
        } else if (((Comparable<KeyType>) k).compareTo(keys[r]) < 0) {
            left[r] = prvInsert(left[r], n, k);
        } else {
            right[r] = prvInsert(right[r], n, k);
        }

        prvAdjust(r);
        return r;
    }

    private int prvRemove(int r, int ntbd, KeyType k) {
        if (r == NULL_INDEX) {
            throw new IllegalArgumentException("Remove: Key not found");
        }

        if (((Comparable<KeyType>) k).compareTo(keys[r]) < 0) {
            left[r] = prvRemove(left[r], ntbd, k);
        } else if (((Comparable<KeyType>) k).compareTo(keys[r]) > 0) {
            right[r] = prvRemove(right[r], ntbd, k);
        } else {
            ntbd = r;

            if (left[r] == NULL_INDEX) {
                if (right[r] == NULL_INDEX) {
                    r = NULL_INDEX;
                } else {
                    r = right[r];
                }
            } else {
                if (right[r] == NULL_INDEX) {
                    r = left[r];
                } else {
                    if (getHeight(right[r]) > getHeight(left[r])) {
                        int temp = right[r];
                        while (left[temp] != NULL_INDEX) {
                            temp = left[temp];
                        }
                        keys[r] = keys[temp];
                        values[r] = values[temp];
                        keys[r] = keys[temp];
                        values[r] = values[temp];
                        right[r] = prvRemove(right[r], ntbd, (KeyType) keys[temp]);
                    } else {
                        int temp = left[r];
                        while (right[temp] != NULL_INDEX) {
                            temp = right[temp];
                        }
                        keys[r] = keys[temp];
                        values[r] = values[temp];
                        left[r] = prvRemove(left[r], ntbd, (KeyType) keys[temp]);
                    }
                }

                if (r != NULL_INDEX) {
                    prvAdjust(r);
                }
            }

            return r;
        }
        return r;
    }
}