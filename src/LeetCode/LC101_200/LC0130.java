package LeetCode.LC101_200;

import java.util.*;

/**
 * Surrounded Regions
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of
 * the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected
 * to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent
 * cells connected horizontally or vertically.
 */
public class LC0130 {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int rows;
    private int cols;

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        if (board.length <= 2 || board[0].length <= 2) {
            return;
        }

        rows = board.length;
        cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                bfs(board, i, 0);
            }
            if (board[i][cols - 1] == 'O') {
                bfs(board, i, cols - 1);
            }
        }
        for (int j = 1; j < cols - 1; j++) {
            if (board[0][j] == 'O') {
                bfs(board, 0, j);
            }
            if (board[rows - 1][j] == 'O') {
                bfs(board, rows - 1, j);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '\0') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void bfs(char[][] board, int i, int j) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(i * cols + j);
        board[i][j] = '\0';

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int[] dir : DIRECTIONS) {
                int ii = cur / cols + dir[0];
                int jj = cur % cols + dir[1];
                if (ii >= 0 && ii < rows && jj >= 0 && jj < cols && board[ii][jj] == 'O') {
                    board[ii][jj] = '\0';
                    que.offer(ii * cols + jj);
                }
            }
        }
    }
}
