package LeetCode.LC201_300;

/**
 * Design a Tic-Tac-Toe
 *
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 *
 * You may assume the following rules:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Example:
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 */
public class LC0348 {
    static class TicTacToe {
        private final int[][] col;
        private final int[][] row;
        private final int[][] cross;

        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            col = new int[2][n];
            row = new int[2][n];
            cross = new int[2][2];
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            int n = this.col[0].length;
            if (++this.row[player - 1][row] == n || ++this.col[player - 1][col] == n) {
                return player;
            }
            if (row == col && ++this.cross[player - 1][0] == n) {
                return player;
            }
            if (row + col == n - 1 && ++this.cross[player - 1][1] == n) {
                return player;
            }

            return 0;
        }
    }

    static class TicTacToeBoard {
        private final char[][] board;

        private static final char[] PLAYER = new char[] {'X', 'O'};

        /** Initialize your data structure here. */
        public TicTacToeBoard(int n) {
            board = new char[n][n];
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            // cc
            int n = board.length;
            if (row < 0 || row >= n || col < 0 || col >= n || board[row][col] != '\0') {
                return -1;
            }

            char playerChar = PLAYER[player - 1];
            board[row][col] = playerChar;
            boolean win = true;
            for (int i = 0; i < n; i++) {
                if (board[row][i] != playerChar) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return player;
            }

            win = true;
            for (int i = 0; i < n; i++) {
                if (board[i][col] != playerChar) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return player;
            }


            if (row == col) {
                win = true;
                for (int i = 0; i < n; i++) {
                    if (board[i][i] != playerChar) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return player;
                }
            }

            if (row + col == n - 1) {
                win = true;
                for (int i = 0; i < n; i++) {
                    if (board[i][n - i - 1] != playerChar) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return player;
                }
            }

            return 0;
        }
    }
}
