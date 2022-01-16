import java.util.List;
import java.util.stream.Collectors;

public class BoardQueue {

    BoardQueue previousBoard;
    Board currentBoard;
    int numberOfSteps;
    GoalBoard goalBoard;

    public BoardQueue(Board currentBoard, GoalBoard goalBoard) {
        this.currentBoard = currentBoard;
        this.goalBoard = goalBoard;
        numberOfSteps = 0;
        previousBoard = null;
    }

    public BoardQueue(BoardQueue previousBoard, Board currentBoard) {
        this.previousBoard = previousBoard;
        this.currentBoard = currentBoard;
        goalBoard = previousBoard.goalBoard;
        numberOfSteps = previousBoard.numberOfSteps + 1;
    }

    public boolean isGoalBoard() {
        return currentBoard.equals(goalBoard.getGoalBoard());
    }

    public int manhattan() {
        return currentBoard.manhattan();
    }

    public List<BoardQueue> neighbors() {
        return currentBoard.neighbors().stream().map(n -> new BoardQueue(this, n)).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if(previousBoard == null) {
            return false;
        }
        return currentBoard.equals(o);
    }

    public void show() {
        currentBoard.show();
    }

    public static int compare(BoardQueue board1, BoardQueue board2, int a, int b) {
        return a*board1.manhattan() + b*board1.numberOfSteps  - a*board2.manhattan() - b*board2.numberOfSteps;
    }
}
