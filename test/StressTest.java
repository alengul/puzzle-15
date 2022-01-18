import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StressTest {

    List<Long> timeResults = new ArrayList<>();

    @BeforeEach
    public void clearTimeArray() {
        timeResults.clear();
    }

    @Test
    public void statisticsForThree11() {
        int n = 3;
        GoalBoard goalBoard = GoalBoard.BOARD_THREE;
        collectStatistic(1000, n, goalBoard, 1, 1);
    }

    @Test
    public void statisticsForThree10() {
        int n = 3;
        GoalBoard goalBoard = GoalBoard.BOARD_THREE;
        collectStatistic(1000, n, goalBoard, 1, 0);
    }

    @Test
    public void statisticsForFour11() {
        int n = 4;
        GoalBoard goalBoard = GoalBoard.BOARD_FOUR;
        collectStatistic(1000, n, goalBoard, 1, 1);
    }

    @Test
    public void statisticsForFour10() {
        int n = 4;
        GoalBoard goalBoard = GoalBoard.BOARD_FOUR;
        collectStatistic(1000, n, goalBoard, 1, 0);
    }

    @Test
    public void statisticsForFive10() {
        int n = 5;
        GoalBoard goalBoard = GoalBoard.BOARD_FIVE;
        collectStatistic(1000, n, goalBoard, 1, 0);
    }


    private void collectStatistic(int iterations, int n, GoalBoard goalBoard, int a, int b) {
        for (int i = 0; i < iterations; i++) {
            Board board = AlgoTest.generateNewBoardBySteps(n, (int) 1e5);
            BoardQueue boardQueue = new BoardQueue(board, goalBoard);
            long start = System.currentTimeMillis();
            AlgoTest.findGoalBoard(boardQueue, a, b);
            timeResults.add(System.currentTimeMillis() - start);
            System.out.println(i + 1);
        }
        double meanTimeSeconds = timeResults.stream().mapToDouble(t -> t).average().getAsDouble() / 1000.0;
        System.out.println("mean time: " + meanTimeSeconds);
    }
}
