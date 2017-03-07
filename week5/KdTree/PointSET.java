import edu.princeton.cs.algs4.*;
import java.awt.*;
import java.util.Stack;

/**
 * Created by ryan on 3/2/17.
 */
public class PointSET
{
    private SET<Point2D> set = new SET<>();

    public PointSET()
    {
        set = new SET<>();
    }

    public boolean isEmpty()
    {
        return set.size() == 0;
    }

    public int size()
    {
        return set.size();
    }

    public void insert(Point2D p)
    {
        set.add(p);
    }

    public boolean contains(Point2D p)
    {
        return set.contains(p);
    }

    public void draw()
    {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.BLACK);
        for (Point2D p : set)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect)
    {
        Stack<Point2D> s = new Stack<>();

        for (Point2D p : set)
        {
            if (rect.contains(p))
            {
                s.push(p);
            }
        }

        return s;
    }

    public Point2D nearest(Point2D p)
    {
        if (p == null)
        {
            return null;
        }

        Point2D nearest = null;

        for (Point2D s : set)
        {
            if (nearest == null)
            {
                nearest = s;
            }

            if (s.distanceTo(p) < nearest.distanceTo(p))
            {
                nearest = s;
            }
        }

        return nearest;
    }

    public static void main(String[] args)
    {
        PointSET ps = new PointSET();
        Point2D a = new Point2D(0.1, 0.2);

        //ps.insert(a);
        ps.insert(new Point2D(0.0, 0.3));
        ps.insert(new Point2D(0.1, 0.7));
        ps.insert(new Point2D(0.5, 0.2));

        System.out.println(ps.nearest(a));
    }

}
