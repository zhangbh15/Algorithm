package LeetCode.LC601_700;

import java.util.*;

/**
 * Number of Distinct Islands
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only
 * if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class LC0694 {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        Set<Set<Integer>> shapes = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    bfs(i, j, grid, shapes);
                }
            }
        }

        return shapes.size();
    }

    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void bfs(int i, int j, int[][] grid, Set<Set<Integer>> shapes) {
        int rows = grid.length;
        int cols = grid[0].length;

        Set<Integer> shape = new HashSet<>();
        shape.add(0);

        Queue<Integer> que = new LinkedList<>();
        que.offer(i * cols + j);
        grid[i][j] = 0;

        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int[] dir : DIRECTIONS) {
                int ii = cur / cols + dir[0];
                int jj = cur % cols + dir[1];
                if (ii >= 0 && ii < rows && jj >= 0 && jj < cols && grid[ii][jj] == 1) {
                    shape.add((ii - i) * 2 * cols + jj - j);  // Times 2 * cols to handle negative index!!!!!!!!!
                    que.offer(ii * cols + jj);
                    grid[ii][jj] = 0;
                }
            }
        }

        shapes.add(shape);
    }
}
