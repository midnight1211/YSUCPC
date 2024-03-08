#ifndef _BST_DICTIONARY_H
#define _BST_DICTIONARY_H

#include <cstdint>      // for uint32_t
#include <stdexcept>    // for domain_error
#include <algorithm>

static const uint32_t
NULL_INDEX = 0xffffffff,
DEFAULT_INITIAL_CAPACITY = 16;

template <typename KeyType, typename ValueType>
class BSTDictionary {
public:
    explicit BSTDictionary(uint32_t _cap = DEFAULT_INITIAL_CAPACITY) {
        // Initialize your BSTDictionary with an optional initial capacity
        // Allocate memory or initialize variables as needed
        if (nTrees == 0) {
            // Allocate space for the six arrays (counts, heights, left, right, keys, values)
            counts = new uint32_t[_cap];
            heights = new uint32_t[_cap];
            left = new uint32_t[_cap];
            right = new uint32_t[_cap];
            keys = new KeyType[_cap];
            values = new ValueType[_cap];

            // Generate the free list
            GenerateFreeList(0, _cap);
            capacity = _cap;
        }

        nTrees++;
        root = NULL_INDEX;
    }

    ~BSTDictionary() {
        // Destructor to clean up resources
        // Deallocate memory or perform necessary cleanp
        nTrees--;
        if (nTrees == 0) {
            // Delete all shared arrays if no other dictionaries exist
            delete[] counts;
            delete[] heights;
            delete[] left;
            delete[] right;
            delete[] keys;
            delete[] values;
        }
        else {
            prvClear(root);     // Otherwise, deallocate nodes in this dictionary
        }
    }

    void clear() {
        // Clear the contents of the dictionary
        prvClear(root);
        root = NULL_INDEX;
    }

    uint32_t size() {
        // Return the number of elements in the dictionary
        return count(root);
    }

    uint32_t height() {
        // Return the height of the binary search tree
        return calculateHeight(root);
    }

    bool isEmpty() {
        // Check if the dictionary is empty
        return (root == NULL_INDEX);
    }

    ValueType& search(const KeyType& k) {
        // Search for a key in the dictionary
        // Return a reference to its corresponding value if found
        // Throw a domain error exception if not found
        // Implement the logic using your shared data pool and BST
        uint32_t n = root;
        while (n != NULL_INDEX) {
            if (k == keys[n]) {
                return values[n]; // Return reference to the key's value if found
            }
            else if (k < keys[n]) {
                n = left[n];
            }
            else {
                n = right[n];
            }
        }

        throw std::domain_error("Search: Key not found");   // Throw if key is not found
    }

    ValueType& operator[](const KeyType& k) {
        // Similar to search(), but add the key-value pair if not found
        // IMplement the logic using your shared data pool and BST
        uint32_t temp = prvAllocate();

        uint32_t n = temp;
        root = prvInsert(root, n, k);

        if (n != temp) {
            prvFree(temp);       // Deallocate unused node
        }

        return values[n];
    }

    void remove(const KeyType& k) {
        uint32_t ntbd = NULL_INDEX;
        // Remove the key and its corresponding value from the dictionary
        // throw a domain error exception if the key doesn't exist
        // Implement the logic using your shared data pool and BST
        root = prvRemove(root, ntbd, k);
    }

private:
    // Private helper functions for managing shared data pool and BST
    uint32_t root;      //Tree root

    static uint32_t* counts;        // Counts for each node
    static uint32_t* heights;       // Heights for each node
    static uint32_t* left;          // Left node indexes
    static uint32_t* right;         // Right node indexes
    static uint32_t nTrees;         // Number of BSTs with this key and value type
    static uint32_t capacity;       // Size of arrays
    static uint32_t freeListHead;   // The head of the unused node list (the free list)
    static KeyType* keys;           // Pool of keys
    static ValueType* values;       // Pool of values

    // GenerateFreeList function
    void GenerateFreeList(uint32_t start, uint32_t end) {
        for (uint32_t i = start; i < end - 1; i++) {
            left[i] = i + 1;
        }
        left[end - 1] = NULL_INDEX;
        freeListHead = start;
    }

    uint32_t prvAllocate() {
        if (freeListHead == NULL_INDEX) {
            // Allocate temporary arrays with 2 * capacity elements
            uint32_t* tmpCounts = new uint32_t[2 * capacity];
            uint32_t* tmpHeights = new uint32_t[2 * capacity];
            uint32_t* tmpLeft = new uint32_t[2 * capacity];
            uint32_t* tmpRight = new uint32_t[2 * capacity];
            KeyType* tmpKeys = new KeyType[2 * capacity];
            ValueType* tmpValues = new ValueType[2 * capacity];

            // Copy data from old arrays to temporary arrays
            for (uint32_t i = 0; i < capacity; i++) {
                tmpCounts[i] = counts[i];
                tmpHeights[i] = heights[i];
                tmpLeft[i] = left[i];
                tmpRight[i] = right[i];
                tmpKeys[i] = keys[i];
                tmpValues[i] = values[i];
            }

            // Delete old arrays
            delete[] counts;
            delete[] heights;
            delete[] left;
            delete[] right;
            delete[] keys;
            delete[] values;

            // Point shared pointers to temporary arrays
            counts = tmpCounts;
            heights = tmpHeights;
            left = tmpLeft;
            right = tmpRight;
            keys = tmpKeys;
            values = tmpValues;

            // Regenerate the free list
            for (uint32_t i = capacity; i < 2 * capacity - 1; i++) {
                left[i] = i + 1;
            }

            left[2 * capacity - 1] = NULL_INDEX;
            freeListHead = capacity;

            capacity *= 2; // Update capacity
        }

        uint32_t temp = freeListHead;
        freeListHead = left[freeListHead];
        left[temp] = NULL_INDEX;
        right[temp] = NULL_INDEX;
        counts[temp] = 1;
        heights[temp] = 1;

        return temp;
    }

    void prvFree(uint32_t n) {
        left[n] = freeListHead;
        freeListHead = n;
    }

    void prvClear(uint32_t r) {
        // Clear the nodes starting from index r in your parallel arrays
        // Implement the logic for clearing nodes
        if (r != NULL_INDEX) {
            prvClear(left[r]);
            prvClear(right[r]);
            prvFree(r);     // Use prvFree() to delete a node in prvClear()
        }
    }

    uint32_t count(uint32_t r) {
        if (r == NULL_INDEX) {
            return 0;
        }
        else {
            return 1 + count(left[r]) + count(right[r]);
        }
    }

    uint32_t calculateHeight(uint32_t r) {
        if (r == NULL_INDEX) {
            return 0;
        }
        else {
            uint32_t lHeight = calculateHeight(left[r]);
            uint32_t rHeight = calculateHeight(right[r]);
            return (lHeight > rHeight) ? (lHeight + 1) : (rHeight + 1);
        }
    }

    void prvAdjust(uint32_t r) {
        counts[r] = getCount(left[r]) + getCount(right[r]) + 1;
        heights[r] = std::max(getHeight(left[r]), getHeight(right[r])) + 1;
    }

    uint32_t getCount(uint32_t index) {
        // Implement a function to get count information for the node index
        return (index != NULL_INDEX) ? counts[index] : 0;
    }

    uint32_t getHeight(uint32_t index) {
        // Implement a function to get height information for the node index
        return (index != NULL_INDEX) ? heights[index] : 0;
    }

    uint32_t prvInsert(uint32_t r, uint32_t& n, const KeyType& k) {
        if (r == NULL_INDEX) {
            keys[n] = k;
            return n;       // n is root of formerly empty tree
        }

        if (k == keys[r]) {
            n = r;      // key found, remember where
        }
        else if (k < keys[r]) {
            left[r] = prvInsert(left[r], n, k);
        }
        else {
            right[r] = prvInsert(right[r], n, k);
        }

        prvAdjust(r);
        return r;
    }

    uint32_t prvRemove(uint32_t r, uint32_t ntbd, const KeyType& k) {
        if (r == NULL_INDEX) {
            throw std::domain_error("Remove: Key not found");
        }

        if (k < keys[r]) {
            left[r] = prvRemove(left[r], ntbd, k);
        }
        else if (k > keys[r]) {
            right[r] = prvRemove(right[r], ntbd, k);
        }
        else {        // k is in node r
            ntbd = r;

            if (left[r] == NULL_INDEX) {
                if (right[r] == NULL_INDEX) {
                    r = NULL_INDEX; // r is a leaf, subtree is removed
                }
                else {
                    r = right[r]; // r only has right child, it is new root
                }
            }
            else {
                if (right[r] == NULL_INDEX) {
                    r = left[r]; // r only has left child, it is new root
                }
                else {
                    if (getHeight(right[r]) > getHeight(left[r])) {
                        uint32_t temp = right[r];
                        while (left[temp] != NULL_INDEX) {
                            temp = left[temp];
                        }
                        std::swap(keys[r], keys[temp]);
                        std::swap(values[r], values[temp]);
                        right[r] = prvRemove(right[r], ntbd, k);
                    }
                    else {
                        uint32_t temp = left[r];
                        while (right[temp] != NULL_INDEX) {
                            temp = right[temp];
                        }

                        std::swap(keys[r], keys[temp]);
                        std::swap(values[r], values[temp]);
                        left[r] = prvRemove(left[r], ntbd, k);
                    }
                }

                if (r != NULL_INDEX) {
                    prvAdjust(r);
                }
            }

            return r;
        }
    }
};

// Implementation of static member variables (outside class definition)
template <typename KeyType, typename ValueType>
uint32_t* BSTDictionary<KeyType, ValueType>::counts = nullptr;

template <typename KeyType, typename ValueType>
uint32_t* BSTDictionary<KeyType, ValueType>::heights = nullptr;

template <typename KeyType, typename ValueType>
uint32_t* BSTDictionary<KeyType, ValueType>::left = nullptr;

template <typename KeyType, typename ValueType>
uint32_t* BSTDictionary<KeyType, ValueType>::right = nullptr;

template <typename KeyType, typename ValueType>
KeyType* BSTDictionary<KeyType, ValueType>::keys = nullptr;

template <typename KeyType, typename ValueType>
ValueType* BSTDictionary<KeyType, ValueType>::values = nullptr;

template <typename KeyType, typename ValueType>
uint32_t BSTDictionary<KeyType, ValueType>::nTrees = 0;

template <typename KeyType, typename ValueType>
uint32_t BSTDictionary<KeyType, ValueType>::capacity = 0;

template <typename KeyType, typename ValueType>
uint32_t BSTDictionary<KeyType, ValueType>::freeListHead = 0;

#endif