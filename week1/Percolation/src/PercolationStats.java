/**
 * Created by Ryan on 1/9/2017.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] openSites;
    private int size;
    private int trials;

    public PercolationStats(int s, int t) {
        size = s;
        trials = t;
        openSites = new double[t];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(size);
            int open = 0;

            while (!perc.percolates()) {
                open++;
                openRandomBlockedNode(perc);
            }

            openSites[i] = (double) open /(double) (size * size);
        }
    }

    private void openRandomBlockedNode(Percolation percObject){
        boolean isOpen = true;
        int randomRow = 0;
        int randomCol = 0;
        while(isOpen)
        {
            randomRow = StdRandom.uniform(1,size+1);
            randomCol = StdRandom.uniform(1,size+1);

            isOpen = percObject.isOpen(randomRow,randomCol);
        }
        percObject.open(randomRow,randomCol);
    }

    private void go() { }

    public double mean() {
        return StdStats.mean(openSites);
    }

    public double stddev() {
        return StdStats.stddev(openSites);
    }

    public double confidenceLo() {
        return mean()-((1.96*stddev())/Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean()+((1.96*stddev())/Math.sqrt(trials));
    }

    private int randInt(int min, int max) {
        int randomNum = StdRandom.uniform(min, max);
        return randomNum;

    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        if (size <= 0 && trials <= 0) {
            throw (new IllegalArgumentException());
        }

        PercolationStats stats = new PercolationStats(size, trials);
        stats.go();
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
