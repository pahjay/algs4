import java.util.Map;

/**
 * Created by ryan on 2/17/17.
 */
public class Board {
    int[][] board = new int[0][];
    int N; // size of the array

    public Board(int[][] blocks)
    { // construct a board from an n-by-n array of blocks
        board = new int[blocks.length][blocks.length];
        for(int i = 0; i < blocks.length; i++)
        {
            for(int j = 0; j < blocks.length; j++)
            {
                board[i][j] = blocks[i][j];
            }
            N++;
        }

    }

    public int dimension()
    { // board dimension n
        return N;
    }

    public int hamming() // number of blocks that are out of place
    {
        int val = 0, h = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                ++val;
                if (board[i][j] == 0) continue;
                else if (board[i][j] != val) h++;
            }
        }
        return h;
    }

    /**
     * iterate through board values
     * check value at i,j and calculate M-score
     * |yv-yx| + |xv -xx|
     * add individual M-score to total
     */
    public int manhatten() { // sum of Manhatten distances between blocks and goal
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

    public boolean isGoal() { // is this board the goal board?
        return hamming() == 0;
    }

    /**
    public Board twin(){ // a board that is obtained by exchanging any pair of blocks
        int[][] boardCopy = copy(board);
    }


    public boolean equals(Object y){ // does this board equal y?

    }

    public Iterable<Board> neighbors() { // all neighboring boards

    }*/

    public String toString() // string representation of this board (in the output format specified below
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "/n");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                s.append(String.format("%2d", board[i][j]));
            }
            s.append("/n");
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
            System.out.println(toBeCopied[i]);
            copy[i] = toBeCopied[i].clone();
        }
        return copy;
    }

    public static void main(String[] args){
        int[][] unsolved = {{8,1,3},
                            {4,0,2},
                            {7,6,5}};

         int[][] solved = {{1,2,3},
                           {4,5,6},
                           {7,8,0}};

         int[][] test = copy(unsolved);

        Board unsolvedB = new Board(unsolved);
        Board solvedB = new Board(solved);

        System.out.println(unsolvedB.hamming());

    }
}
