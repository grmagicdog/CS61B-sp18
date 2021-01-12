package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.ArrayList;

public class Percolation {
    private final WeightedQuickUnionUF openUF;
    private final WeightedQuickUnionUF fullUF;
    private final WeightedQuickUnionUF percolateUF;
    private final int n;
    private final int openSite;
    private final int top;
    private final int bottom;

    /** Creates N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException(String.format("Expected N > 0, but got %d", N));
        }
        openUF = new WeightedQuickUnionUF(N * N + 1);
        fullUF = new WeightedQuickUnionUF(N * N + 1);
        percolateUF = new WeightedQuickUnionUF(N * N + 2);
        n = N;
        openSite = N * N;
        top = N * N;
        bottom = N * N + 1;
    }

    private int site(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException();
        }
        return row * n + col;
    }

    private Integer[] adjacentSites(int row, int col) {
        ArrayList<Integer> sites = new ArrayList<>();
        if (row > 0) {
            sites.add(site(row - 1, col));
        }
        if (row < n - 1) {
            sites.add(site(row + 1, col));
        }
        if (col > 0) {
            sites.add(site(row, col - 1));
        }
        if (col < n - 1) {
            sites.add(site(row, col + 1));
        }
        Integer[] result = new Integer[sites.size()];
        return sites.toArray(result);
    }

    /** Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        int site = site(row, col);
        openUF.union(site, openSite);
        if (row == 0) {
            fullUF.union(site, top);
            percolateUF.union(site, top);
        }
        if (row == n - 1) {
            percolateUF.union(site, bottom);
        }
        for (int adj : adjacentSites(row, col)) {
            if (openUF.connected(adj, openSite)) {
                fullUF.union(site, adj);
                percolateUF.union(site, adj);
            }
        }
    }

    /** Is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        return openUF.connected(site(row, col), openSite);
    }

    /** Is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        return fullUF.connected(site(row, col), top);
    }

    /** Returns number of open sites */
    public int numberOfOpenSites() {
        return n * n + 1 - openUF.count();
    }

    /** Does the system percolate? */
    public boolean percolates() {
        return percolateUF.connected(top, bottom);
    }

    public static void main(String[] args) { }
}
