package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key == null) throw new IllegalArgumentException("key is null");
        if (p == null) return null;
        int cmp = key.compareTo(p.key);
        if (cmp > 0) {
            return getHelper(key, p.right);
        } else if (cmp < 0) {
            return getHelper(key, p.left);
        } else {
            return p.value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        V val = getHelper(key, root);
        return val;
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value is null");
        }
        if (p == null) {
            p = new Node(key, value);
            size += 1;
            return p;
        }
        int cmp = key.compareTo(p.key);
        if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.value = value;
        }
        return p;

    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Node p = root;
        if (p == null) {
            return null;
        }
        Set<K> sk = new HashSet<>();
//        Stack<Node> stack = new Stack<>();
//        stack.push(p);
//        while (!stack.empty()) {
//            Node n = stack.pop();
//            sk.add(n.key);
//            if (n.right != null) {
//                stack.push(n.right);
//            }
//            if (n.left != null) {
//                stack.push(n.left);
//            }
//        }
        print(sk, p);
        return sk;
    }

    public boolean isNoChild(Node p) {
        if (p.left == null && p.right == null) {
           return true;
        }
        return false;
    }

    private Node removeHelper(Node p, K key) {
        if (key == null) throw new IllegalArgumentException();
        V val = get(key);
        if (val == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp > 0) {
            p.right = removeHelper(p.right, key);
        }
        else if (cmp < 0) {
            p.left = removeHelper(p.left, key);
        }
        else {
            if (p.left == null) {
                return p.right;
            }
            if (p.right == null) {
                return p.left;
            }
            Node t = p.left;
            while (t.right != null) {
                t = t.right;
            }
            t.right = p.right;
            p.left.right = t.left;
            t.left = p.left;
        }
        return p;

    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node p =removeHelper(root, key);
        return p.value;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return this.keySet().iterator();
    }
    private Set<K> print(Set<K> set, Node p) {
        if (p == null) {
            return null;
        }
        set.add(p.key);
        System.out.println(p.key);
        print(set, p.left);
        print(set, p.right);
        return set;
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("k", 5);
        b.put("e", 5);
        b.put("v", 3);
        b.put("b", 3);
        b.put("g", 3);
        b.put("a", 3);
        b.put("b", 3);
        b.put("d", 3);
        b.put("f", 3);
        b.put("p", 3);
        b.put("y", 3);
        b.put("m", 3);
        b.put("r", 3);
        b.remove("k");
        b.keySet();
    }
}
