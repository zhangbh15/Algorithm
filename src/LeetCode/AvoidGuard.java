package LeetCode;

import java.util.*;

/**
 * Assumptions:
 *      The size of the map: 0 < size < 46340
 *      The thief and the guard take a random move in each round, not necessarily in the direction of shortest path.
 *      The thief and the guard cannot stay at the same position for 2 rounds.
 *      There can be more than 1 positions with money.
 */
public class AvoidGuard {
    public boolean canWin(char[][] matrix, int[] thief, int[] guard) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException("Invalid input matrix.");
        }

        rows = matrix.length;
        cols = matrix[0].length;
        if (thief[0] < 0 || thief[0] >= rows || thief[1] < 0 || thief[1] >= cols ||
                guard[0] < 0 || guard[0] >= rows || guard[1] < 0 || guard[1] >= cols) {
            throw new IllegalArgumentException("Invalid initial positions.");
        }

        return dfs(matrix, thief[0], thief[1], guard[0], guard[1], new HashSet<>());
    }

    private int rows;
    private int cols;

    private static final char WALL = 'X';
    private static final char MONEY = 'M';
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // add {0, 0} if can stay at the same position

    private boolean dfs(char[][] matrix, int thiefI, int thiefJ, int guardI, int guardJ, Set<Integer> visited) {
        if (thiefI < 0 || thiefI >= rows || thiefJ < 0 || thiefJ >= cols ||
                guardI < 0 || guardI >= rows || guardJ < 0 || guardJ >= cols ||
                matrix[thiefI][thiefJ] == WALL || matrix[guardI][guardJ] == WALL ||
                (thiefI == guardI && thiefJ == guardJ)) {
            return false;
        }
        if (matrix[thiefI][thiefJ] == MONEY) {
            return true;
        }

        int hash = (thiefI * cols + thiefJ) * rows * cols + (guardI * cols + guardJ);
        if (!visited.add(hash)) {
            // repeated path
            return  false;
        }

        for (int[] thiefDir : DIRECTIONS) {
            for (int[] guardDir : DIRECTIONS) {
                if (dfs(matrix, thiefI + thiefDir[0], thiefJ + thiefDir[1],
                        guardI + guardDir[0], guardJ + guardDir[1], visited)) {
                    return true;
                }
            }
        }

        visited.remove(hash);
        return false;
    }

    public static void main(String[] args) {
        AvoidGuard so = new AvoidGuard();
        char[][] matrix = {{' ', ' ', ' ', 'X', ' '},
                {' ', 'X', ' ', 'X', ' '},
                {' ', 'X', ' ', 'X', ' '},
                {' ', 'X', ' ', 'X', ' '},
                {' ', 'X', 'M', 'X', ' '},
                {' ', 'X', ' ', 'X', ' '},
                {' ', 'X', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' '}};
        int[] thief = {0, 0};
        int[] guard = {2, 2};
        boolean res = so.canWin(matrix, thief, guard);

        System.out.println(res);
    }
}
