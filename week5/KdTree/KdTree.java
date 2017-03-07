import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * Created by ryan on 3/7/17.
 **/
public class KdTree
{
    private int size;
    private Node root;

    private class Node
    {
        private Node left; // lower value
        private Node right; // higher value
        private Point2D p;
        private RectHV r;

        public Node(Point2D p, RectHV r)
        {
            this.p = p;
            this.r = r;
        }
    }

    public KdTree()
    {
        size = 0;
        root = null;

    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void insert(Point2D p)
    {
        root = insert(root, p, 0.0, 0.0, 1.0, 1.0, true);
    }

    private Node insert(Node node, Point2D p, double x0, double y0, double x1, double y1, boolean orientation)
    {
        // ORIENTATION
        // TRUE = Vertical
        // FALSE = Horizontal

        // empty location reached
        if (node == null)
        {
            size++;
            RectHV r = new RectHV(x0, y0, x1, y1);
            return new Node(p, r);
        }
        // check if duplicate node
        else if (node.p.x() == p.x() && node.p.y() == p.y())
        {
            return node;
        }
        // vertical is true, compare x values
        if(orientation)
        {
            double cmp = p.x() - node.p.x();

            // new node.p.x value is less than current -> go left
            if (cmp < 0)
            {
                node.left = insert(node.left, p, x0, y0, node.p.x(), y1, !orientation);
            }
            // else go right
            else
            {
                node.right = insert(node.right, p, node.p.x(), y0, x1, y1, !orientation);
            }
        }
        else
        {
            double cmp = p.y() - node.p.y();
            if (cmp < 0)
            {
                node.left = insert(node.left, p, x0, y0, x1, node.p.y(), !orientation);
            }
            else
            {
                node.right = insert(node.right, p, x0, node.p.y(), x1, y1, !orientation);
            }
        }

        return node;
    }

    public boolean contains(Point2D p)
    {
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D p, boolean orientation)
    {
        if (node == null)
        {
            return false;
        }
        else if (node.p.x() == p.x() && node.p.y() == p.y())
        {
            return true;
        }

        if (orientation)
        {
            double cmp = p.x() - node.p.x();
            if (cmp < 0)
            {
                return contains(node.left, p, !orientation);
            }
            else
            {
                return contains(node.right, p, !orientation);
            }
        }
        else
        {
            double cmp = p.y() - node.p.y();
            if (cmp < 0)
            {
                return contains(node.left, p, !orientation);
            }
            else
            {
                return contains(node.right, p, !orientation);
            }
        }
    }

    public void draw()
    {
        draw(root, true);
    }

    private void draw(Node node, boolean orientation)
    {
        if (node == null) return;

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();
        // True  == Vertical
        // False == Horizontal
        if (orientation)
        {
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius();
            StdDraw.line(node.p.x(), node.r.ymin(), node.p.x(), node.r.ymax());
        }
        else
        {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(node.r.xmin(), node.p.y(), node.r.xmax(), node.p.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect)
    {
        Queue<Point2D> q = new Queue<>();
        range(root, rect, q);
        return q;
    }

    private void range(Node n, RectHV r, Queue<Point2D> q)
    {
        if (n == null)
        {
            return;
        }

        if (r.contains(n.p))
        {
            q.enqueue(n.p);
        }

        // check L and R subtrees to see if they intersect with input rectangle
        if (r.intersects(n.r))
        {
            range(n.left,  r, q);
            range(n.right, r, q);
        }
    }

    public Point2D nearest(Point2D p)
    {
        return nearest(root, p, root.p, true);
    }

    private Point2D nearest(Node node, Point2D p, Point2D q, boolean orientation)
    {
        Point2D nearest = q;
        Node near, far;
        if (node == null) return nearest;

        if (node.p.distanceTo(p) < nearest.distanceTo(p))
        {
            nearest = node.p;
        }

        // check to see if current rectangle is closer to p than the closets point
        if (node.r.distanceTo(p) < nearest.distanceTo(p))
        {
            if ((orientation && (p.x() < node.p.x()) || (!orientation && (p.y() < node.p.y()))))
            {
                near = node.left;
                far = node.right;
            }
            else
            {
                near = node.right;
                far = node.left;
            }
            // check subtree on same side as p
            nearest = nearest(near, p, nearest, !orientation);
            // check subtree on opposite side of p
            nearest = nearest(far, p, nearest, !orientation);
        }

        return nearest;
    }


}
