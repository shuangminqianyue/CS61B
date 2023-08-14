/** Deque interface. */
public interface Deque<T> {
    // Add an element to the front of the deque
    void addFirst(T item);

    // Add an element to the end of the deque
    void addLast(T item);

    // Remove and return an element from the front of the deque
    T removeFirst();

    // Remove and return an element from the end of the deque
    T removeLast();

    // Return whether the deque is empty
    boolean isEmpty();

    // Return the number of elements in the deque
    int size();

    // Print all elements in the deque
    void printDeque();

    // Return the item at the given index
    T get(int index);
}
