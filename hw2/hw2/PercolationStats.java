package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import jdk.jshell.Snippet;

public class PercolationStats {
    private double[] percolateTimes;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Too small");
        }
        percolateTimes = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            percolateTimes[i] = calPercolate(N, p);
            System.out.println(percolateTimes[i]);
        }
    }

    private double calPercolate(int N, Percolation p) {
        int runtime = 0;
        while (!p.percolates()) {
            int randomX = StdRandom.uniform(N);
            int randomY = StdRandom.uniform(N);
            if (!p.isOpen(randomX, randomY)) {
                p.open(randomX, randomY);
                runtime++;
            }
        }
        return runtime;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolateTimes);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolateTimes);
    }

    // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() - (1.96 * stddev() / Math.pow(percolateTimes.length, 0.5));
    }

    // high endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() + (1.96 * stddev() / Math.pow(percolateTimes.length, 0.5));
    }

}
