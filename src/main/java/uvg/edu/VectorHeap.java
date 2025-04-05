package uvg.edu;

import java.util.Vector;

public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    protected Vector<E> data;

    public VectorHeap() {
        data = new Vector<>();
    }

    public VectorHeap(Vector<E> v) {
        data = new Vector<>(v.size());
        for (E element : v) {
            add(element);
        }
    }

    protected static int parent(int i) {
        return (i - 1) / 2;
    }

    protected static int left(int i) {
        return 2 * i + 1;
    }

    protected static int right(int i) {
        return 2 * (i + 1);
    }

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

    protected void pushDownRoot(int root) {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                if ((right(root) < heapSize) &&
                        (data.get(right(root)).compareTo(data.get(childpos)) < 0))
                {
                    childpos = right(root);
                }
                if (data.get(childpos).compareTo(value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos;
                } else {
                    data.set(root, value);
                    return;
                }
            } else {
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
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (!isEmpty()) {
            pushDownRoot(0);
        }
        return minVal;
    }

    @Override
    public boolean add(E value) {
        if (value == null) {
            throw new NullPointerException("Cannot add null value to Priority Queue");
        }
        data.add(value);
        percolateUp(data.size() - 1);
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