import edu.princeton.cs.algs4.*;
import java.util.Stack;

/**
 * Created by ryan on 2/17/17.
 */

public class Solver {

    private boolean isSolveable;
    private int totMoves;
    private SearchNode prev;

    private class SearchNode implements Comparable<SearchNode>
    {
        private Board board;
        private SearchNode previousNode;
        private int moves;
        private int priority;

        public SearchNode(Board b, SearchNode previous, int m)
        {
            board = b;
            previousNode = previous;
            moves = m;
            priority = board.manhattan() + moves;
        }

        public SearchNode previous(){
            return previousNode;
        }

        @Override
        public int compareTo(SearchNode that)
        {
            if      (this.priority > that.priority)  return 1;
            else if (this.priority == that.priority) return 0;
            else                                     return -1;
        }
    }

    private void enqueue(SearchNode n, MinPQ<SearchNode> pq)
    {
        for (Board b : n.board.neighbors())
        {
            if (n.previousNode == null || (!b.equals(n.previousNode.board)))
                pq.insert(new SearchNode(b, n, n.moves + 1));
        }
    }

    public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
        MinPQ<SearchNode> searchNodeMinPQ = new MinPQ<>();
        MinPQ<SearchNode> searchNodeMinPQTwin = new MinPQ<>();
        SearchNode start = new SearchNode(initial, null, 0);
        SearchNode twin  = new SearchNode(initial.twin(), null, 0);
        searchNodeMinPQ.insert(start);
        searchNodeMinPQTwin.insert(twin);

        while (true)
        {
            SearchNode peak     = searchNodeMinPQ.delMin();
            SearchNode twinPeak = searchNodeMinPQTwin.delMin();

            if (peak.board.isGoal())
            {
                prev = peak;
                isSolveable = true;
                totMoves = peak.moves;
                break;
            }

            if (twinPeak.board.isGoal())
            {
                isSolveable = false;
                totMoves = -1;
                break;
            }

            enqueue(peak, searchNodeMinPQ);
            enqueue(twinPeak, searchNodeMinPQTwin);
        }
    }

    public boolean isSolvable() { // is the initial board solvable?
        return isSolveable;
    }

    public int moves() { // min number of moves to solve the initial board; -1 is unsolvable
        return totMoves;
    }

    public Iterable<Board> solution(){// sequence of boards in a shortest solution; null if unsolvable
        Stack<Board> solutions = new Stack<>();

        if (!isSolveable)
        {
            solutions = null;
        } else
        {
            SearchNode p = prev;
            while(p != null)
            {
                solutions.push(p.board);
                p = p.previousNode;
            }
        }

        return solutions;
    }

    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}