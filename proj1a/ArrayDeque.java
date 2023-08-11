/* Implement a array-based deque 'ArrayDeque' class. */
public class ArrayDeque<T> {
    private static final int INITIAL_CAPACITY = 8;
    private static final double RESIZE_FACTOR = 2.0;
    private static final double USAGE_FACTOR_THRESHOLD = 0.25;

    private T[] items;
    private int size;
    private int front;
    private int rear;

    /* Constructor to creat a ArrayDeque. */
    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        rear = 0;
    }

    /* Add an item to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resizeArray((int) (items.length * RESIZE_FACTOR));
        }
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        size += 1;
    }

    /* Add an item to the end of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resizeArray((int) (items.length * RESIZE_FACTOR));
        }
        items[rear] = item;
        rear = (rear + 1) % items.length;
        size++;
    }

    /* Check if the deque is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /* Get the size of the deque. */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        for (int i = front; i != rear; i = (i + 1) % items.length) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /* Remove an item from the front of the deque. */
    public T removeFirst() {
        if (!isEmpty()) {
            T removedItem = items[front];
            items[front] = null;
            front = (front + 1) % items.length;
            size--;
            resizeIfNecessary();
            return removedItem;
        } else {
            return null;
        }
    }

    /* Remove an item from the end of the deque. */
    public T removeLast() {
        if (!isEmpty()) {
            rear = (rear - 1 + items.length) % items.length;
            T removedItem = items[rear];
            items[rear] = null;
            size--;
            resizeIfNecessary();
            return removedItem;
        } else {
            return null;
        }
    }

    /* Get an item by index. */
    public T get(int index) {
        if (index >= 0 && index < size) {
            int pos = (front + index) % items.length;
            return items[pos];
        }
        return null;
    }

    /* Resize the array to the given capacity. */
    @SuppressWarnings("unchecked")
    private void resizeArray(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        if (front <= rear) {
            System.arraycopy(items, front, newArray, 0, size);
        } else {
            System.arraycopy(items, front, newArray, 0, items.length - front);
            System.arraycopy(items, 0, newArray, items.length - front, rear + 1);
        }
        items = newArray;
        front = 0;
        rear = size; // Adjust the rear index after resizing
    }

    /**
     * Resize only if the array length is 16 or more, or the usage factor is too low
     */
    private void resizeIfNecessary() {
        double usageFactor = (double) size / items.length;
        if (items.length >= 16 || usageFactor < USAGE_FACTOR_THRESHOLD) {
            resizeArray(Math.max(INITIAL_CAPACITY, (int) (size / USAGE_FACTOR_THRESHOLD)));
        }
    }

}
