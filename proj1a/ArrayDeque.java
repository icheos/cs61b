public class ArrayDeque<T> {
    private T[] arr;
    private int size;

    public ArrayDeque() {
        arr = (T[]) new Object[100];
        size = 0;
    }

    public void addFirst(T item) {
        for (int i = size - 1; i >= 0; i++) {
            arr[i + 1] = arr[i];
        }
        arr[0] = item;
        size += 1;
    }

    public void addLast(T item) {
        arr[size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }

    public T removeFirst() {
        T val = arr[0];
        for (int i = 0; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size -= 1;
        return val;
    }

    public T removeLast() {
        T val = arr[size - 1];
        size -= 1;
        return val;
    }

    public T get(int index) {
        T val = arr[index];
        return val;
    }
}
