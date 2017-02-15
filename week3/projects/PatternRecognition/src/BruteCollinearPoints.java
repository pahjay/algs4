import edu.princeton.cs.algs4.*;

import java.util.*;

/**
 * Created by ryan on 2/13/17.
 */
public class BruteCollinearPoints {
    private LineSegment[] segments;
    // finds all line segments containing 4 points
    /*
    To check whether the 4 points p, q, r, and s are collinear,
    check whether the three slopes between p and q, between p and r,
    and between p and s are all equal.
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        ArrayList<LineSegment> segList = new ArrayList<>();

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        if (isUnique(pointsCopy)) {
            for (int p = 0; p < pointsCopy.length - 3; p++) {
                for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                    for (int r = q + 1; r < pointsCopy.length - 1; r++) {
                        for (int s = r + 1; s < pointsCopy.length; s++) {
                            if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s]) &&
                                    pointsCopy[p].slopeTo(pointsCopy[s]) == pointsCopy[p].slopeTo(pointsCopy[r])) {
                                segList.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                            }
                        }
                    }
                }
            }
            segments = segList.toArray(new LineSegment[segList.size()]);
        } else {
            throw new IllegalArgumentException("duplicate point.");
        }
    }

    // the number of line segments
    public int numberOfSegments(){ return segments.length; }

    // the line segments
    public LineSegment[] segments() { return Arrays.copyOf(segments, numberOfSegments()); }

    // checks LineSegment objects for duplicates
    private boolean isUnique (Point[] points){
        for(int i = 0; i < points.length - 1; i++){
            for(int k = i+1; k < points.length; k++){
                if (points[i].compareTo(points[k]) == 0) return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
