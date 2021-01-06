/** Deque data structure based on Linked List.
 * @author 高瑞 */
public class LinkedListDeque<T> {
    /** Underlying data structure to represent nodes in the Linked List */
    private class ItemNode {
        T item;
        ItemNode next;
        ItemNode previous;

        private ItemNode(T first, ItemNode rest) {
            item = first;
            setNext(rest);
        }

        private void setNext(ItemNode rest) {
            next = rest;
            if (next != null) {
                next.previous = this;
            }
        }
    }

    private ItemNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ItemNode(null, null);
        sentinel.setNext(sentinel);
        size = 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        sentinel.setNext(new ItemNode(item, sentinel.next));
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        sentinel.previous.setNext(new ItemNode(item, sentinel));
        size += 1;
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    public T removeFirst() {
        ItemNode removedNode = sentinel.next;
        sentinel.setNext(removedNode.next);
        size = Math.max(size - 1, 0);
        return removedNode.item;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    public T removeLast() {
        ItemNode removedNode = sentinel.previous;
        removedNode.previous.setNext(sentinel);
        size = Math.max(size - 1, 0);
        return removedNode.item;
    }

    /** Gets the item at the given index.
     *  If no such item exists, returns null.
     *  Doesn't alter the deque. */
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        ItemNode result = sentinel.next;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(ItemNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line. */
    public void printDeque() {
        for (ItemNode curr = sentinel.next; curr != sentinel; curr = curr.next) {
            System.out.print(curr.item);
            if (curr.next != sentinel) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
