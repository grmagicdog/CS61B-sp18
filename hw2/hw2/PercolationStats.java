package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] samples;

    /** Performs T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        samples = new double[T];
        for (int t = 0; t < T; t++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            samples[t] = 1.0 * percolation.numberOfOpenSites() / (N * N);
        }
    }

    /** Returns sample mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(samples);
    }

    /** Returns sample standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(samples);
    }

    /** Returns low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(samples.length);
    }

    /** Returns high endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(samples.length);
    }
}
