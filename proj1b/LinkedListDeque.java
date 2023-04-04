public class LinkedListDeque<Item> implements Deque<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Item item;
    private Node head;
    private int size;

    public LinkedListDeque() {
        head = new Node(item, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

/*
    public LinkedListDeque(LinkedListDeque other) {
        head = new Node(item, null, null);
        head.prev = head;
        head.next = head;
        other.head = other.head.next;
        while (other.head != null) {
            addLast((T) other.head.item);
            other.head = other.head.next;
        }
        size = 0;
    }
*/

    @Override
    public void addFirst(Item val) {
        size += 1;
        Node p = head;
        Node n = new Node(val, null, null);
        n.next = p.next;
        p.next.prev = n;
        n.prev = p;
        p.next = n;
    }

    @Override
    public void addLast(Item val) {
        size += 1;
        Node p = head;
        Node n = new Node(val, null, null);
        n.prev = p.prev;
        p.prev.next = n;
        p.prev = n;
        n.next = p;
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
        Node p = head.next;
        while (p != head) {
            System.out.println(p.item);
            p = p.next;
        }
    }

    @Override
    public Item removeFirst() {
        if (!isEmpty()) {
            size -= 1;
            Node p = head.next;
            Item val = (Item) p.item;
            p.next.prev = head;
            head.next = p.next;
            return val;
        } else {
            return null;
        }
    }

    @Override
    public Item removeLast() {
        if (!isEmpty()) {
            size -= 1;
            Node p = head.prev;
            Item val = (Item) p.item;
            p.prev.next = head;
            head.prev = p.prev;
            return val;

        }
        return null;
    }

    @Override
    public Item get(int index) {
        Node p = head.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return (Item) p.item;
    }

    public Item getRecursive(int index) {
        Node p = head;
        if (index == 0) {
            head = head.next;
            Node tmp = p;
            p = head;
            head = tmp;
            return p.item;
        } else {
            head = head.next;
            Item val = getRecursive(index - 1);
            head = p;
            return val;
        }
    }

}
