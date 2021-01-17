package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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

        boolean isLeaf() {
            return left == null && right == null;
        }

        boolean hasSuccessor() {
            return right != null;
        }

        boolean isSuccessor() {
            return left == null;
        }

        Node successor() {
            if (!hasSuccessor()) {
                return null;
            }
            Node s = right;
            while (!s.isSuccessor()) {
                s = s.left;
            }
            return s;
        }

        Node removeSuccessor() {
            if (isSuccessor()) {
                return remove();
            }
            left = left.removeSuccessor();
            return this;
        }

        void swap(Node other) {
            V tv = value;
            value = other.value;
            other.value = tv;
            K tk = key;
            key = other.key;
            other.key = tk;
        }

        Node remove() {
            if (isLeaf()) {
                return null;
            } else if (hasSuccessor()) {
                swap(successor());
                right = right.removeSuccessor();
                return this;
            } else {
                return left;
            }
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

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.key.compareTo(key) == 0) {
            return p.value;
        } else if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        if (p.key.compareTo(key) == 0) {
            p.value = value;
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
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
        TreeSet<K> keySet = new TreeSet<>();
        for (K key : this) {
            keySet.add(key);
        }
        return keySet;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V returnValue = get(key);
        root = removeHelper(root, key);
        size -= 1;
        return returnValue;
    }

    private Node removeHelper(Node current, K key) {
        if (current.key.compareTo(key) == 0) {
            return current.remove();
        }
        if (current.key.compareTo(key) > 0) {
            current.left = removeHelper(current.left, key);
        } else {
            current.right = removeHelper(current.right, key);
        }
        return current;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V storedValue = get(key);
        if (storedValue != null && storedValue.equals(value)) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new NodeIterator(root);
    }

    private class NodeIterator implements Iterator<K> {
        Node current;
        NodeIterator left;
        NodeIterator right;

        NodeIterator(Node node) {
            current = node;
            if (current != null) {
                left = new NodeIterator(current.left);
                right = new NodeIterator(current.right);
            }
        }

        @Override
        public boolean hasNext() {
            return current != null && (left.hasNext() || current.key != null || right.hasNext());
        }

        @Override
        public K next() {
            if (left.hasNext()) {
                return left.next();
            } else if (current.key != null) {
                K result = current.key;
                current.key = null;
                return result;
            } else {
                return right.next();
            }

        }
    }
}
