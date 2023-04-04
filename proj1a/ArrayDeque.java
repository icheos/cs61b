public class ArrayDeque<T> {
    private T[] arr;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int maxSize;

    public ArrayDeque() {
        maxSize = 8;
        arr = (T[]) new Object[maxSize];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int back(int index) {
        if (index == 0) {
            index = maxSize - 1;
        } else {
            index -= 1;
        }
        return index;
    }

    private int forward(int index, int len) {
        while (len != 0) {
            if (index == maxSize - 1) {
                index = 0;
            } else {
                index += 1;
            }
            len -= 1;
        }
        return index;
    }

    public void addFirst(T item) {
        if (size == maxSize - 1) {
            resizeUp();
        }
        arr[nextFirst] = item;
        nextFirst = back(nextFirst);
        size += 1;
    }


    public void addLast(T item) {
        if (size == maxSize - 1) {
            resizeUp();
        }
        arr[nextLast] = item;
        nextLast = forward(nextLast, 1);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int s = size;
        for (int i = 0; i < size; i++) {
            System.out.println(get(i));
        }
    }

    public T removeFirst() {
        if (size * 2 == maxSize && size >= 16) {
            resizeDown();
        }
        if (!isEmpty()) {
            size -= 1;
            nextFirst = forward(nextFirst, 1);
            T val = arr[nextFirst];
            return val;
        }
        return null;
    }

    public T removeLast() {
        if (size * 2 == maxSize && size >= 16) {
            resizeDown();
        }
        if (!isEmpty()) {
            size -= 1;
            nextLast = back(nextLast);
            T val = arr[nextLast - 1];
            return val;
        }
        return null;
    }

    public T get(int index) {
        if (index < size) {
            int next = forward(nextFirst, index + 1);
            return arr[next];
        }

        return null;
    }

    private void resizeUp() {
        int newSize = (int) (size * 2);
        T[] a = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        maxSize = newSize;
        nextFirst = maxSize - 1;
        nextLast = size;
        arr = a;

    }

    private void resizeDown() {
        int newSize = (int) (maxSize / 2);
        T[] a = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        maxSize = newSize;
        nextFirst = maxSize - 1;
        nextLast = size;
        arr = a;
    }

    public static void main(String[] args) {
        ArrayDeque a = new ArrayDeque();
        a.addFirst(0);
        a.removeLast();
        a.addFirst(2);
        a.addLast(4);
        a.addLast(5);
        a.removeLast();
        a.removeFirst();
        a.removeFirst();
        a.addFirst(10);
        a.printDeque();
    }
}
