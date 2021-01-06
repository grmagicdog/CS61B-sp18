/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    int[] items;
    int size;

    /** Creates an empty list. */
    public AList() {
        items = new int[10];
        size = 0;
    }

    private void resize(int newLength) {
        int[] result = new int[newLength];
        System.arraycopy(items, 0, result, 0, size);
        items = result;
    }

    public void addFirst(int x) {
        if (size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items, 0, items, 1, size);
        items[0] = x;
        size++;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size++;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        if (i > size) {
            throw new IndexOutOfBoundsException();
        }
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        int x = getLast();
        size--;
        if (size < items.length * 0.25) {
            resize(items.length / 2);
        }
        return x;
    }
}
