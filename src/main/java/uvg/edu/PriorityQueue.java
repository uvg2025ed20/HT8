package uvg.edu;
/**
 * Interface for a Priority Queue ADT.
 * Assumes smallest value has highest priority.
 * @param <E> the type of elements held in this collection, must be Comparable
 */
public interface PriorityQueue<E extends Comparable<E>> {

    /**
     * Returns the element with the highest priority without removing it.
     * @return the highest priority element, or null if the queue is empty.
     */
    E getFirst(); // Corresponds to peek()

    /**
     * Removes and returns the element with the highest priority.
     * @return the highest priority element, or null if the queue is empty.
     */
    E remove(); // Corresponds to poll()

    /**
     * Adds an element to the priority queue.
     * @param value the element to add. Must not be null.
     * @return true if the element was added successfully.
     */
    boolean add(E value); // Corresponds to add() or offer()

    /**
     * Checks if the priority queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the priority queue.
     * @return the size of the queue.
     */
    int size();

    /**
     * Removes all elements from the priority queue.
     */
    void clear();
}