
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class AlgoTest {

    Set<BoardQueue> visited = new HashSet<>();

    @BeforeEach
    public void cleanVisited() {
        visited.clear();
    }


    @Test
    public void threeTest11() {
        int n = 3;
        GoalBoard goalBoard = GoalBoard.BOARD_THREE;
        Board board = generateNewBoardBySteps(n, (int)1e5);
        board.show();
        BoardQueue boardQueue = new BoardQueue(board, goalBoard);
        BoardQueue resBoard = findGoalBoard(boardQueue, 1, 1);
        showSteps(resBoard);
    }

    @Test
    public void threeTest10() {
        int n = 3;
        GoalBoard goalBoard = GoalBoard.BOARD_THREE;
        Board board = generateNewBoardBySteps(n, (int)1e5);
        board.show();
        BoardQueue boardQueue = new BoardQueue(board, goalBoard);
        BoardQueue resBoard = findGoalBoard(boardQueue, 1, 0);
        showSteps(resBoard);
    }

    @Test
    public void fourTest11() {
        int n = 4;
        GoalBoard goalBoard = GoalBoard.BOARD_FOUR;
        Board board = generateNewBoardBySteps(n, (int)1e5);
        board.show();
        BoardQueue boardQueue = new BoardQueue(board, goalBoard);
        BoardQueue resBoard = findGoalBoard(boardQueue, 1, 1);
        showSteps(resBoard);
    }

    @Test
    public void fourTest10() {
        int n = 3;
        GoalBoard goalBoard = GoalBoard.BOARD_FOUR;
        Board board = generateNewBoardBySteps(n, (int)1e5);
        board.show();
        BoardQueue boardQueue = new BoardQueue(board, goalBoard);
        BoardQueue resBoard = findGoalBoard(boardQueue, 1, 0);
        showSteps(resBoard);
    }

    public Board generateNewBoardBySteps(int n, int numOfSteps) {
        Random rd = new Random();
        int[][] test = new int[n][n];
        List<Integer> arr = new ArrayList<>();
        for(int i = 1; i < n*n; i++) {
            arr.add(i);
        }
        arr.add(0);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                test[i][j] = arr.get(i*n + j);
            }
        }
        Board startBoard =  new Board(test);
        for(int i = 0; i < numOfSteps; i++) {
            startBoard = startBoard.neighbors().get(rd.nextInt(startBoard.neighbors().size()));
        }
        return new Board(startBoard.board);
    }

    public void showSteps(BoardQueue resBoard) {
        System.out.println("\n");
        System.out.println("Последовательность ходов");
        System.out.println("\n");
        resBoard.show();
        System.out.println("\n");
        while(resBoard.previousBoard != null) {
            resBoard.previousBoard.show();
            System.out.println("\n");
            resBoard = resBoard.previousBoard;
        }
    }

    public BoardQueue findGoalBoard(BoardQueue startBoard, int a, int b) {
        visited.clear();
        PriorityQueue<BoardQueue> queue = new PriorityQueue<>((b1, b2) -> BoardQueue.compare(b1, b2, a, b));
        queue.add(startBoard);
        visited.add(startBoard);
        while(!queue.peek().isGoalBoard()) {
            BoardQueue boardFromQueue = queue.poll();
            List<BoardQueue> neighbors = boardFromQueue.neighbors();
            var added = neighbors.stream().filter(board -> !board.equals(boardFromQueue.previousBoard) && !visited.contains(board)).collect(Collectors.toList());
            queue.addAll(added);
            visited.addAll(added);
        }
        return  queue.poll();
    }


}
