package LeetCode.LC601_700;

import java.util.*;

/**
 * Max Area of Island
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no
 * island, the maximum area is 0.)
 *
 * Example 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island
 * must be connected 4-directionally.
 *
 * Example 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class LC0695 {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null ||grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, bfs(i, j, grid));
                }
            }
        }

        return maxArea;
    }

    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int bfs(int i, int j, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Integer> que = new LinkedList<>();
        que.offer(i * cols + j);
        grid[i][j] = 0;
        int area = 1;

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int[] dir : DIRECTIONS) {
                int ii = cur / cols + dir[0];
                int jj = cur % cols + dir[1];
                if (ii >= 0 && ii < rows && jj >= 0 && jj < cols && grid[ii][jj] == 1) {
                    area++;
                    grid[ii][jj] = 0;
                    que.offer(ii * cols + jj);
                }
            }
        }

        return area;
    }
}
