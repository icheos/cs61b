public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] arr;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int maxSize;

    public ArrayDeque() {
        maxSize = 8;
        arr = (Item[]) new Object[maxSize];
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

    @Override
    public void addFirst(Item item) {
        if (size == maxSize - 1) {
            resizeUp();
        }
        arr[nextFirst] = item;
        nextFirst = back(nextFirst);
        size += 1;
    }


    @Override
    public void addLast(Item item) {
        if (size == maxSize - 1) {
            resizeUp();
        }
        arr[nextLast] = item;
        nextLast = forward(nextLast, 1);
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int s = size;
        for (int i = 0; i < size; i++) {
            System.out.println(get(i));
        }
    }

    @Override
    public Item removeFirst() {
        if (size * 2 == maxSize && size >= 16) {
            resizeDown();
        }
        if (!isEmpty()) {
            size -= 1;
            nextFirst = forward(nextFirst, 1);
            Item val = arr[nextFirst];
            return val;
        }
        return null;
    }

    @Override
    public Item removeLast() {
        if (size * 2 == maxSize && size >= 16) {
            resizeDown();
        }
        if (!isEmpty()) {
            size -= 1;
            nextLast = back(nextLast);
            Item val = arr[nextLast - 1];
            return val;
        }
        return null;
    }

    @Override
    public Item get(int index) {
        if (index < size) {
            int next = forward(nextFirst, index + 1);
            return arr[next];
        }

        return null;
    }

    private void resizeUp() {
        int newSize = (int) (size * 2);
        Item[] a = (Item[]) new Object[newSize];
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
        Item[] a = (Item[]) new Object[newSize];
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
