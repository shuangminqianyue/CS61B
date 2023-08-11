/** Implement a linked-list-based deque 'LinkListDeque' class. */

public class LinkedListDeque<T> {
    /* Represent a node of the deque. */
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /* A sentinel node serves as the head and tail of the linked list. */
    private Node senti;
    private int size = 0;

    /* Constuctor to create a deque. */
    public LinkedListDeque() {
        senti = new Node(null, null, null);
        senti.prev = senti;
        senti.next = senti;
        size = 0;
    }

    /* Add an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node newNode = new Node(item, senti, senti.next);
        senti.next.prev = newNode;
        senti.next = newNode;
        size += 1;
    }

    /* Add an item of type T to the back of the deque. */
    public void addLast(T item) {
        Node newNode = new Node(item, senti.prev, senti);
        senti.prev.next = newNode;
        senti.prev = newNode;
        size += 1;
    }

    /* Return true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /* Return the number of items in the deque. */
    public int size() {
        return size;
    }

    /* Print the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node curr = senti.next;
        while (curr != senti) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (!isEmpty()) {
            Node temp = senti.next;
            senti.next = senti.next.next;
            senti.next.prev = senti;
            size -= 1;
            return temp.item;
        } else {
            return null;
        }
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public T removeLast() {
        if (!isEmpty()) {
            Node temp = senti.prev;
            senti.prev = senti.prev.prev;
            senti.prev.next = senti;
            size -= 1;
            return temp.item;
        } else {
            return null;
        }
    }

    /**
     * Gets the item at the given index using iteration.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if (index >= 0 && index < size) {
            Node curr = senti.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.item;
        } else {
            return null;
        }
    }

    /**
     * Gets the item at the given index using recursive.
     * If no such item exists, returns null.
     */
    public T getRecursive(int index) {
        if (index >= 0 && index < size) {
            return getRecursiveHelper(senti.next, index);
        } else {
            return null;
        }
    }

    /** Recursive helper function to get an item by index. */
    private T getRecursiveHelper(Node curr, int index) {
        if (index == 0) {
            return curr.item;
        } else {
            return getRecursiveHelper(curr.next, index - 1);
        }
    }

}
