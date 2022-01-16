import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    int[][] board;
    int size;


    public Board(int[][] tiles) {
        board = tiles;
        size = tiles.length;
    }

    // return number of blocks out of place
    public int hamming() {
        int res = -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res += board[i][j] == i * size + j + 1 ? 0 : 1;
            }
        }
        return res;
    }

    // return sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int res = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int elem = board[i][j];
                if (elem != 0) {
                    elem--;
                    int correctI = elem / size;
                    int correctJ = elem % size;
                    res += Math.abs(i - correctI);
                    res += Math.abs(j - correctJ);
                }
            }
        }
        return res;
    }

    // does this board position equal y
    public boolean equals(Board y) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != y.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // return the List of all neighboring board positions
    public List<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int emptyPosition = emptyPosition();
        if (emptyPosition == -1) {
            System.out.println("ERROR IN EMPTY POSITION");
            throw new RuntimeException();
        }
        int i = emptyPosition / size;
        int j = emptyPosition % size;
        if (i > 0) {
            neighbors.add(newBoardWithSwitchedPair(i, j, i - 1, j));
        }
        if (j > 0) {
            neighbors.add(newBoardWithSwitchedPair(i, j, i, j - 1));
        }
        if (i < size - 1) {
            neighbors.add(newBoardWithSwitchedPair(i, j, i + 1, j));
        }
        if (j < size - 1) {
            neighbors.add(newBoardWithSwitchedPair(i, j, i, j + 1));
        }
        return neighbors;
    }

    // return a string representation of the board
    public void show() {
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    private int emptyPosition() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    return i * size + j;
                }
            }
        }
        return -1;
    }

    private Board newBoardWithSwitchedPair(int x1, int y1, int x2, int y2) {
        Board res = new Board(Arrays.stream(board).map(int[]::clone).toArray(int[][]::new));
        int tmp = res.board[x1][y1];
        res.board[x1][y1] = res.board[x2][y2];
        res.board[x2][y2] = tmp;
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return equals(board1);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
