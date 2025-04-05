package uvg.edu;

import java.util.Vector;

/**
 * An implementation of a Priority Queue using a Vector-based binary heap.
 * Assumes smallest element (as defined by Comparable) has the highest priority.
 * @param <E> the type of elements held in this collection
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    protected Vector<E> data; // The vector storing heap elements

    public VectorHeap() {
        data = new Vector<>();
    }

    /**
     * Creates a priority queue from an existing vector.
     * @param v The vector to build the heap from.
     */
    public VectorHeap(Vector<E> v) {
        data = new Vector<>(v.size());
        for (E element : v) {
            add(element); // Add elements ensuring heap property is maintained
        }
    }

    /**
     * Returns the index of the parent of the node at index i.
     * @param i The index of the node.
     * @return The index of the parent.
     */
    protected static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Returns the index of the left child of the node at index i.
     * @param i The index of the node.
     * @return The index of the left child.
     */
    protected static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Returns the index of the right child of the node at index i.
     * @param i The index of the node.
     * @return The index of the right child.
     */
    protected static int right(int i) {
        return 2 * (i + 1);
    }

    /**
     * Moves the node at index leaf up the heap to its correct position.
     * @param leaf The index of the node to percolate up.
     */
    protected void percolateUp(int leaf) {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 && (value.compareTo(data.get(parent)) < 0)) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    /**
     * Moves the node at index root down the heap to its correct position.
     * @param root The index of the node to push down.
     */
    protected void pushDownRoot(int root) {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                // If right child exists and is smaller than left child, consider right child
                if ((right(root) < heapSize) &&
                        (data.get(right(root)).compareTo(data.get(childpos)) < 0))
                {
                    childpos = right(root);
                }
                // If the child is smaller than the value, swap and continue down
                if (data.get(childpos).compareTo(value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos; // Move down
                } else { // Found correct position
                    data.set(root, value);
                    return;
                }
            } else { // No children, position found
                data.set(root, value);
                return;
            }
        }
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return data.get(0);
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        E minVal = data.get(0);
        // Move last element to root
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1); // Remove last element
        if (!isEmpty()) {
            pushDownRoot(0); // Restore heap property
        }
        return minVal;
    }

    @Override
    public boolean add(E value) {
        if (value == null) {
            throw new NullPointerException("Cannot add null value to Priority Queue");
        }
        data.add(value); // Add to the end
        percolateUp(data.size() - 1); // Restore heap property
        return true;
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {
        data.clear();
    }
}