package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] area;
    private WeightedQuickUnionUF uf;
    private int opened;
    private int topSide;
    private int[] bottomColum;
    private int bottomSize;
    private boolean isPercolate;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must bigger than 0");
        }
        opened = 0;
        isPercolate = false;
        area = new boolean[N][N];
        topSide = N * N;
        bottomColum = new int[N];
        bottomSize = 0;
        uf = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                area[i][j] = false;
            }
        }
        for (int i = 0; i < area.length; i++) {
            uf.union(topSide, i);
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
            uf.union(XYTo1D(row, col), XYTo1D(row + 1, col));
        }
        if (isOpen(row - 1, col)) {
            uf.union(XYTo1D(row, col), XYTo1D(row - 1, col));
        }
        if (isOpen(row, col - 1)) {
            uf.union(XYTo1D(row, col), XYTo1D(row, col - 1));
        }
        if (isOpen(row, col + 1)) {
            uf.union(XYTo1D(row, col), XYTo1D(row, col + 1));
        }
        if (row == (area.length - 1)) {
            bottomColum[bottomSize] = col;
            bottomSize++;
        }
        if (!isPercolate) {
            for (int i = 0; i < bottomSize; i++) {
                if (uf.connected(topSide, XYTo1D(N - 1,bottomColum[i]))) {
                    isPercolate = true;
                }
            }
        }
        opened += 1;
    }

    private int XYTo1D(int row, int col) {
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
        int d = XYTo1D(row, col);
        if (isOpen(row, col)) {
            return uf.connected(d, topSide);
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        return isPercolate;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        p.open(0, 0);
        System.out.println(p.percolates());
    }

}
