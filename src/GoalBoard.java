public enum GoalBoard {

    BOARD_THREE(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}),
    BOARD_FOUR(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}}),
    BOARD_FIVE(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 0}});

    private final Board goalBoard;

    GoalBoard(int[][] arrGoal) {
        goalBoard = new Board(arrGoal);
    }

    public Board getGoalBoard() {
        return goalBoard;
    }

}
