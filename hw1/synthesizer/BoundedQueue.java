package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /** Returns size of the buffer. */
    int capacity();

    /** Returns number of items currently in the buffer. */
    int fillCount();

    /** Adds item x to the end. */
    void enqueue(T x);

    /** Deletes and returns item from the front. */
    T dequeue();

    /** Returns (but does not delete) item from the front. */
    T peek();

    /** Returns true if the buffer is empty, and false otherwise. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** Returns true if the buffer is full, and false otherwise. */
    default boolean isFull() {
        return fillCount() == capacity();
    }

    Iterator<T> iterator();
}
