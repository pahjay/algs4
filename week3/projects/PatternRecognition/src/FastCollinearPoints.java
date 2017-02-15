import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ryan on 2/13/17.
 */
public class FastCollinearPoints {
    private ArrayList<LineSegment> seg = new ArrayList<>();

    /**
     * Think of p as the origin.
     * For each other point q, determine the slope it makes with p.
     * Sort the points according to the slopes they makes with p.
     * Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
     * If so, these points, together with p, are collinear.
     */
    public FastCollinearPoints(Point[] points){
        if (points == null) throw new NullPointerException("points == null");

        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        if (hasDuplicate(pointsCopy)) throw new IllegalArgumentException("duplicate entries");

        // pointer for position of segments in ArrayList
        for (int i = 0; i < pointsCopy.length - 3; i++){
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            for(int z = 0; z < pointsCopy.length - 1; z++){
                System.out.println(pointsCopy[z].slopeTo(pointsCopy[z+1]));
            }
            // set starting pointer
            for (int p = 0, q = 1, s = 2; s < pointsCopy.length; s++){
                // increase trailing pointer while slopes are the same
                while (s < pointsCopy.length && Double.compare(pointsCopy[p].slopeTo(pointsCopy[q]), pointsCopy[p].slopeTo(pointsCopy[s])) == 0){
                    s++;
                }
                // check to see if three matches have occurred and that point p is less than point q
                if(s - q >= 3 && pointsCopy[p].compareTo(pointsCopy[q]) < 0){
                    seg.add(new LineSegment(pointsCopy[p], pointsCopy[q - 1]));
                }

                // try and find the next segment
                q = s;
            }

        }
    }
    public int numberOfSegments(){
        return seg.size();
    }
    public LineSegment[] segments() {
        return seg.toArray(new LineSegment[seg.size()]);
    }

    private boolean hasDuplicate(Point[] points){
        for (int i = 0; i < points.length - 1; i++){
            if(points[i].compareTo(points[i+1]) == 0)
                return true;
        }
        return false;
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
        //StdDraw.enableDoubleBuffering();
        //StdDraw.setXscale(0, 32768);
        //StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            //p.draw();
        }
        //StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            //segment.draw();
        }
        //StdDraw.show();
    }
}
