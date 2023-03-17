public class LinkedListDeque<T> {
    public class Node<T> {
       private T item;
       private Node next;
       private Node prev;
       public Node(T item, Node prev, Node next) {
          this.item = item;
          this.prev = prev;
          this.next = next;
       }
    }
    private Node head;
    int size;
    public LinkedListDeque() {
        head = new Node(1, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }
    public LinkedListDeque(LinkedListDeque other) {
        head = new Node(1, null, null);
        head.prev = head;
        head.next = head;
        other.head = other.head.next;
        while (other.head != null) {
            addLast((T)other.head.item);
            other.head = other.head.next;
        }
        size = 0;
    }

    public void addFirst(T item) {
        size += 1;
        Node p = head;
        Node n = new Node(item, null, null);
        n.next = p.next;
        p.next.prev = n;
        n.prev = p;
        p.next = n;
    }
    public void addLast(T item) {
        size += 1;
        Node p = head;
        Node n = new Node(item, null, null);
        n.prev = p.prev;
        p.prev.next = n;
        p.prev = n;
        n.next = p; }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = head.next;
        while (p != head) {
            System.out.println(p.item);
            p = p.next;
        }
    }

    public T removeFirst() {
        size -= 1;
        Node p = head.next;
        T val = (T)p.item;
        p.next.prev = head;
        head.next = p.next;
        return val;
    }

    public T removeLast() {
        size -= 1;
        Node p = head.prev;
        T val = (T)p.item;
        p.prev.next = head;
        head.prev = p.prev;
        return  val;

    }
    public T get(int index) {
        Node p = head;
        while (index > 0) {
           p = p.next;
           index -= 1;
        }
        return (T)p.item;
    }
    public  T getRecursice(int index) {
       if (index == 0) {
           return (T)head.item;
       }
       else {
           head = head.next;
           getRecursice(index - 1);
       }
       return (T)head.item;
    }
}
