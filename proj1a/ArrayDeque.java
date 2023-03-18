public class ArrayDeque<T> {
    private T[] arr;
    private int size;
    private int maxSize;

    public ArrayDeque() {
        maxSize = 10;
        arr = (T[]) new Object[maxSize];
        size = 0;
    }

    public void addFirst(T item) {
        if (size == maxSize) {
            resize(2);
        }
        for (int i = size - 1; i >= 0; i--) {
            arr[i + 1] = arr[i];
        }
        arr[0] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == maxSize) {
            resize(2);
        }
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
        if (!isEmpty()) {
            T val = arr[0];
            for (int i = 0; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
            size -= 1;
            if (maxSize / size >= 2) {
                resize(0.5f);
            }
            return val;
        }
        return null;
    }

    public T removeLast() {
        if (!isEmpty()) {
            T val = arr[size - 1];
            size -= 1;
            if (maxSize / size >= 2) {
                resize(0.5f);
            }
            return val;
        }
        return null;
    }

    public T get(int index) {
        return arr[index];
    }

    private void resize(float factor) {
        if (factor > 1) {
            maxSize = (int) (size * factor);
            T[] a = (T[]) new Object[maxSize];
            System.arraycopy(arr, 0, a, 0, size);
            arr = a;
        } else {
            maxSize = (int) (maxSize * factor);
            T[] a = (T[]) new Object[maxSize];
            System.arraycopy(arr, 0, a, 0, size);
            arr = a;
        }
    }

}
