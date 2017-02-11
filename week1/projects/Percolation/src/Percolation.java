/**
 * Created by Ryan on 1/9/2017.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[][] grid;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF qFind, auxFind;
    private int truths;

    // create n-by-n grid, with all of the sites blocked
    public Percolation(int n) {
        if (n <= 0) { throw new IllegalArgumentException(); }
      this.size = n;
      this.bottom = size * size + 1;
      this.qFind = new WeightedQuickUnionUF(size * size + 2);
      this.auxFind = new WeightedQuickUnionUF(size * size + 1);
      this.grid = new boolean[size][size];
    }



    // open site(row, col) if it is not open already
    public    void open(int row, int col) {
        if (!this.grid[row-1][col-1]) {
            truths++;
        }

        this.grid[row-1][col-1] = true;

        if (row < 1 || row > this.size || col < 1 || col > this.size)
            throw new IndexOutOfBoundsException("Illegal parameter value.");

        if (row == 1) {
            this.qFind.union(top, getIndex(row, col));
            this.auxFind.union(top, getIndex(row, col));
        }
        if (row == this.size) {
            this.qFind.union(bottom, getIndex(row, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            this.qFind.union(getIndex(row, col), getIndex(row, col - 1));
            this.auxFind.union(getIndex(row, col), getIndex(row, col - 1));
        }
        if (col < this.size && isOpen(row, col + 1)) {
            this.qFind.union(getIndex(row, col), getIndex(row, col + 1));
            this.auxFind.union(getIndex(row, col), getIndex(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            this.qFind.union(getIndex(row, col), getIndex(row - 1, col));
            this.auxFind.union(getIndex(row, col), getIndex(row - 1, col));
        }
        if (row < size && isOpen(row +  1, col)) {
            this.qFind.union(getIndex(row, col), getIndex(row + 1, col));
            this.auxFind.union(getIndex(row, col), getIndex(row + 1, col));
        }
    }

    // is site(row,col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size)
            throw new IndexOutOfBoundsException("Illegal parameter value.");

        return this.grid[row - 1][col - 1];
    }

    // is site(row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size)
            throw new IndexOutOfBoundsException("Illegal parameter value.");

        return this.qFind.connected(top, getIndex(row, col)) && this.auxFind.connected(top, getIndex(row, col));
    }

    // number of open sites
    public     int numberOfOpenSites() {
        return this.truths;

    }

    // does the system percolate?
    public boolean percolates() {
        return this.qFind.connected(0, (size*size+1));
    }

    private int getIndex(int row, int col) {
        return (this.size * (row - 1) + col);
    }

}
