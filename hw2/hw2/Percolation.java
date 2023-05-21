package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] area;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF noBottomUf;
    private int opened;
    private int topSide;
    private int bottomSide;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must bigger than 0");
        }
        opened = 0;
        area = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 3);
        noBottomUf = new WeightedQuickUnionUF(N * N + 3);
        topSide = N * N;
        bottomSide = N * N + 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                area[i][j] = false;
            }
        }
        for (int i = 0; i < area.length; i++) {
            noBottomUf.union(topSide, i);
            uf.union(topSide, i);
            uf.union(bottomSide, xyTo1D(N - 1, i));
        }
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        int N = area.length;
        if (isOpen(row, col) || (row >= N || col >= N) || (row < 0 || col < 0)) {
            return;
        }
        area[row][col] = true;
        if (isOpen(row + 1, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            noBottomUf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (isOpen(row - 1, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            noBottomUf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (isOpen(row, col - 1)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            noBottomUf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (isOpen(row, col + 1)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            noBottomUf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }

        opened += 1;
    }

    private int xyTo1D(int row, int col) {
        int N = area.length;
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new RuntimeException("row or col out of bound");
        }
        return row * N + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        try {
            return area[row][col] == true;
        } catch (IndexOutOfBoundsException ibe) {
            return false;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= area.length || row < 0 || col >= area.length || col < 0) {
            return false;
        }
        if (isOpen(row, col)) {
            return noBottomUf.connected(xyTo1D(row, col), topSide);
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        if (area.length == 1 && !isOpen(0, 0)) {
            return false;
        }
        return uf.connected(topSide, bottomSide);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        p.open(0, 0);
        System.out.println(p.percolates());
    }

}
