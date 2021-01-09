package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        for (int i = 0; i < 9; i++) {
            arb.enqueue(i);
            assertEquals(i + 1, arb.fillCount());
            assertFalse(arb.isEmpty());
            assertFalse(arb.isFull());
        }
        arb.enqueue(9);
        assertTrue(arb.isFull());
        for (int i = 0; i < 10; i++) {
            assertEquals(i, (int) arb.dequeue());
        }
        assertTrue(arb.isEmpty());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
