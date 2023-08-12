public class ArrayDeque<T> {
    private static final int INITIAL_CAPACITY = 8;
    private static final double USAGE_FACTOR = 0.25;

    private T[] array;
    private int size;
    private int front;
    private int back;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resizeArray(array.length * 2);
        }
        front = (front - 1 + array.length) % array.length;
        array[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resizeArray(array.length * 2);
        }
        array[back] = item;
        back = (back + 1) % array.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = front;
        for (int i = 0; i < size; i++) {
            System.out.print(array[index] + " ");
            index = (index + 1) % array.length;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        if (array.length >= INITIAL_CAPACITY && (double) size / array.length < USAGE_FACTOR) {
            resizeArray(array.length / 2);
        }
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        back = (back - 1 + array.length) % array.length;
        T item = array[back];
        array[back] = null;
        size--;
        if (array.length >= INITIAL_CAPACITY && (double) size / array.length < USAGE_FACTOR) {
            resizeArray(array.length / 2);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (front + index) % array.length;
        return array[actualIndex];
    }

    @SuppressWarnings("unchecked")
    private void resizeArray(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % array.length];
        }
        array = newArray;
        front = 0;
        back = size;
    }

}
