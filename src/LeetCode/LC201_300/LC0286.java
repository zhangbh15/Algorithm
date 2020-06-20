package LeetCode.LC201_300;

import java.util.*;

/**
 * Walls and Gates
 *
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF
 * as you may assume that the distance to a gate is less than 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate.
 * If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example:
 *
 * Given the 2D grid:
 *
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 *
 * After running your function, the 2D grid should be:
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 */

// Shortest distance --> Only one pass. Directly add to que!!!!
// Compare with LC0317 Shortest Distance from All Buildings

public class LC0286 {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 ||
            rooms[0] == null || rooms[0].length == 0) {
            return;
        }

        Queue<Integer> que = new LinkedList<>();
        int rows = rooms.length;
        int cols = rooms[0].length;
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == 0) {
                    que.offer(i * cols + j);
                }
            }
        }

        int distance = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int curr = que.poll();
                for (int[] d : DIRECTIONS) {
                    int ii = curr / cols + d[0];
                    int jj = curr % cols + d[1];
                    if (ii >= 0 && ii < rows
                            && jj >= 0 && jj < cols
                            && rooms[ii][jj] == Integer.MAX_VALUE) {
                        rooms[ii][jj] = distance;
                        que.offer(ii * cols + jj);
                    }
                }
            }
            distance++;
        }
    }

    private static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        LC0286 so = new LC0286();
        int[][] matrix = {{0, INF, INF}, {INF, INF, 1}, {1, 1, INF}};
        so.wallsAndGates(matrix);
        if (matrix != null) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
