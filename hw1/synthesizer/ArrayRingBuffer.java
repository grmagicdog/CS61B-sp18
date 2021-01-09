package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    private int ringIncrement(int index) {
        if (index + 1 == capacity) {
            return 0;
        }
        return index + 1;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount++;
        last = ringIncrement(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T firstItem = rb[first];
        rb[first] = null;
        fillCount--;
        first = ringIncrement(first);
        return firstItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator(fillCount());
    }

    private class ARBIterator implements Iterator<T> {
        int total;
        int count;

        ARBIterator(int fillCount) {
            total = fillCount;
            count = 0;
        }
        /** Returns {@code true} if the iteration has more elements. */
        @Override
        public boolean hasNext() {
            return count < total;
        }

        /** Returns the next element in the iteration. */
        @Override
        public T next() {
            int i = first;
            first = ringIncrementBy(first, count);
            T result = peek();
            count++;
            first = i;
            return result;
        }

        private int ringIncrementBy(int index, int x) {
            int result = index + x;
            return result % total;
        }
    }
}
