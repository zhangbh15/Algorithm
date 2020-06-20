package LeetCode.LC301_400;
import java.util.*;

/**
 * Shortest Distance from All Buildings
 *
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Example:
 *
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 7
 *
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 *              the point (1,2) is an ideal empty land to build a house, as the total
 *              travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */

// Distance from all buildings --> O(m * n) for each building
// k passes (k is the number of buildings)
// Compare with LC0286 Walls and Gates

public class LC0317 {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private void bfs(int[][] matrix, int i, int j, int[][] distance) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        visited[i][j] = true;
        Queue<Integer> que = new LinkedList<>();
        que.offer(i * cols + j);
        int minLen = 1;

        while (!que.isEmpty()) {
            int size = que.size();
            for (int k = 0; k < size; k++) {
                int cur = que.poll();
                for (int[] d : DIRECTIONS) {
                    int ii = cur / cols + d[0];
                    int jj = cur % cols + d[1];
                    if (ii >= 0 && ii < rows && jj >= 0 && jj < cols
                            && matrix[ii][jj] == 0 && !visited[ii][jj]) {
                        distance[ii][jj] += minLen;
                        visited[ii][jj] = true;
                        que.offer(ii * cols + jj);
                    }
                }
            }
            minLen++;
        }

        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                if (matrix[m][n] == 0 && !visited[m][n]) {
                    matrix[m][n] = 2;
                }
            }
        }
    }

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0
                || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distance = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, distance);
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int  j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    minDistance = Math.min(minDistance, distance[i][j]);
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    public static void main(String[] args) {
        LC0317 so = new LC0317();
        int[][] test = {{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
        int res = so.shortestDistance(test);
        System.out.println(res);
    }
}
