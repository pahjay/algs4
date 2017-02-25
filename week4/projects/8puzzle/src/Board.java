import java.util.Stack;

/**
 * Created by ryan on 2/17/17.
 */
public class Board {
    private int[][] board;
    private int N; // size of the array

    public Board(int[][] blocks)
    { // construct a board from an n-by-n array of blocks
        board = copy(blocks);
        N = board.length;
    }

    public int dimension()
    { // board dimension n
        return N;
    }

    /**
     * iterate over board, ignoring 0 value
     * if value is not consistent with solved puzzle, increase hamming score
     **/
    public int hamming() // number of blocks that are out of place
    {
        int val = 0, h = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                val++;
                if (board[i][j] == 0) continue;
                else if (board[i][j] != val)
                {
                    h++;
                }
            }
        }
        return h;
    }

    /**
     * iterate through board values
     * check value at i,j and calculate M-score
     * |yv-yx| + |xv -xx|
     * add individual M-score to total
     **/
    public int manhattan()
    { // sum of Manhattan distances between blocks and goal
        int mScore = 0;

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if(board[i][j] == 0) continue;
                int temp = (N*N) - board[i][j];
                int x, y = N-1;

                while(temp >= N)
                {
                    temp = temp - N;
                    y--;
                }
                x = N - temp - 1;
                mScore = mScore + (Math.abs((x - j)) + Math.abs(y - i));
            }
        }
        return mScore;
    }

    public boolean isGoal()
    { // is this board the goal board?
        return manhattan() == 0;
    }


    public Board twin()
    { // a board that is obtained by exchanging any pair of blocks
        int[][] boardCopy = copy(board);
        boolean twinFound = false;

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N - 1; j++)
            {
                if (board[i][j] != 0 && board[i][j+1] != 0)
                {
                    exchange(boardCopy, i , j, i , j + 1);
                    twinFound = true;
                    break;
                }
            }
            if (twinFound) break;
        }

        Board twinBoard = new Board(boardCopy);

        return twinBoard;
    }


    public boolean equals(Object y) // does this board equal y?
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (this.board.length != that.board.length) return false;

        for(int i = 0; i < this.board.length; i++)
        {
            for (int j = 0; j < this.board.length; j++)
            {
                if(this.board[i][j] != that.board[i][j]) return false;
            }
        }
        return true;

    }


    public Iterable<Board> neighbors() // all neighboring boards
    {
       Stack<Board> boardStack = new Stack<>();
       boolean isFound = false;
       int i = 0, j = 0, l = 0, k = 0;

       for (i = 0; i < board.length; i++)
       {
           for (j = 0; j < board.length; j++)
           {
               if (board[i][j] == 0)
               {
                   l = i;
                   k = j;
                   isFound = true;
                   break;
               }
           }
           if (isFound)
               break;
       }


        // right
        if (k + 1 <= board.length - 1)
        {
            //System.out.println("right");;
            int[][] temp = copy(board);
            exchange(temp, l, k, l, k + 1);
            boardStack.push(new Board(temp));
        }

        // left
        if (k - 1 >= 0)
        {
            //System.out.println("left");
            int[][] temp = copy(board);
            exchange(temp, l, k, l, k - 1);
            boardStack.push(new Board(temp));
        }

        // down
        if (l + 1 <= board.length - 1)
        {
            //System.out.println("down");
            int[][] temp = copy(board);
            exchange(temp, l, k, l + 1, k);
            boardStack.push(new Board(temp));
        }

        // up
        if (l - 1 >= 0)
        {
            //System.out.println("up");
            int[][] temp = copy(board);
            exchange(temp, l, k, l - 1, k);
            boardStack.push(new Board(temp));
        }


        return boardStack;

    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private static boolean less(int a, int b)
    {
        return a - b < 0;
    }

    private static int[][] copy(int[][] toBeCopied)
    {
        int[][] copy = new int[toBeCopied.length][];

        for (int i = 0; i < toBeCopied.length; i++)
        {
            copy[i] = toBeCopied[i].clone();
        }
        return copy;
    }

    private void exchange(int[][] copy, int i , int j, int ii, int jj)
    {
        int temp = copy[i][j];
        copy[i][j] = copy[ii][jj];
        copy[ii][jj] = temp;
    }


    public static void main(String[] args)
    { }
}
