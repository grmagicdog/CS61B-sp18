package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {
    @Test
    public void testOpen() {
        int N = 20;
        Percolation percolation = new Percolation(20);
        for (int row = 0, col = 0; row < N; row++) {
            percolation.open(row, col);
            assertTrue(percolation.isOpen(row, col));
            assertFalse(percolation.isOpen(row, col + 1));
        }
    }

    @Test
    public void testFullPercolates() {
        int N = 20;
        Percolation percolation = new Percolation(20);
        for (int row = N - 1, col = 0; row > 0; row--) {
            percolation.open(row, col);
            assertFalse(percolation.isFull(row, col + 1));
            assertFalse(percolation.percolates());
        }
        percolation.open(0, 0);
        assertTrue(percolation.percolates());
        for (int row = 0, col = 0; row < N; row++) {
            assertTrue(percolation.isFull(row, col));
        }
    }

    @Test
    public void testNumberOfOpenSites() {
        int N = 20;
        Percolation percolation = new Percolation(20);
        for (int i = 0; i < N; i++) {
            assertEquals(i, percolation.numberOfOpenSites());
            percolation.open(i, i);
        }
    }
}
