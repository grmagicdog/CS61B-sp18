/** Deque data structure based on Array.
 * @author 高瑞 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int frontIndex;
    private int backIndex;

    /** Returns the underlying array index of the given deque index. */
    private int arrayIndex(int dequeIndex) {
        int result = dequeIndex + frontIndex;
        if (result >= items.length) {
            result -= items.length;
        }
        if (result < 0) {
            result += items.length;
        }
        return result;
    }

    /** Resizes the array to a size of capacity. */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        if (frontIndex > backIndex) {
            System.arraycopy(items, frontIndex, newItems, 0, items.length - frontIndex);
            System.arraycopy(items, 0, newItems, items.length - frontIndex, backIndex + 1);
        } else {
            System.arraycopy(items, frontIndex, newItems, 0, size);
        }
        items = newItems;
        frontIndex = 0;
        backIndex = arrayIndex(size - 1);
    }

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        frontIndex = 0;
        backIndex = 0;
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
        if (size == items.length) {
            resize(size * 2);
        }
        frontIndex = arrayIndex(-1);
        items[frontIndex] = item;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        backIndex = arrayIndex(size);
        items[backIndex] = item;
        size += 1;
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = items[frontIndex];
        items[frontIndex] = null;
        size -= 1;
        frontIndex = arrayIndex(0);

        if (size < items.length * 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return result;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = items[backIndex];
        items[backIndex] = null;
        size -= 1;
        backIndex = arrayIndex(size - 1);
        if (size < items.length * 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return result;
    }

    /** Gets the item at the given index.
     *  If no such item exists, returns null.
     *  Doesn't alter the deque. */
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return items[arrayIndex(index)];
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[arrayIndex(i)]);
            if (i + 1 < size) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
