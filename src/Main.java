import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        int n = 3;
        GoalBoard goalBoard = GoalBoard.BOARD_THREE;
        Board board = generateNewBoardBySteps(n, (int) 1e5);
        board.show();
        BoardQueue boardQueue = new BoardQueue(board, goalBoard);

        Set<BoardQueue> visited = new HashSet<>();
        PriorityQueue<BoardQueue> queue = new PriorityQueue<>((a, b) -> a.manhattan() + a.numberOfSteps - b.manhattan() - b.numberOfSteps);
        queue.add(boardQueue);
        visited.add(boardQueue);
        while (!queue.peek().isGoalBoard()) {
            BoardQueue boardFromQueue = queue.poll();
            List<BoardQueue> neighbors = boardFromQueue.neighbors();
            var added = neighbors.stream().filter(a -> !a.equals(boardFromQueue.previousBoard) && !visited.contains(a)).collect(Collectors.toList());
            queue.addAll(added);
            visited.addAll(added);
        }
        BoardQueue resBoard = queue.poll();
        System.out.println("\n");
        System.out.println("Последовательность ходов");
        System.out.println("\n");
        resBoard.show();
        System.out.println("\n");
        while (resBoard.previousBoard != null) {
            resBoard.previousBoard.show();
            System.out.println("\n");
            resBoard = resBoard.previousBoard;
        }

    }

    public static Board generateNewBoardBySteps(int n, int numOfSteps) {
        Random rd = new Random();
        int[][] test = new int[n][n];
        List<Integer> arr = new ArrayList<>();
        for (int i = 1; i < n * n; i++) {
            arr.add(i);
        }
        arr.add(0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                test[i][j] = arr.get(i * n + j);
            }
        }
        Board startBoard = new Board(test);
        for (int i = 0; i < numOfSteps; i++) {
            startBoard = startBoard.neighbors().get(rd.nextInt(startBoard.neighbors().size()));
        }
        return new Board(startBoard.board);
    }
}
