package LeetCode.LC301_400;

/**
 * Longest Increasing Path in a Matrix
 *
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 *
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LC0329 {
    // Alternative: sort and dp

    private int dfs(int[][] matrix, int i, int j, int preVal, int[][] mem) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= preVal) {
            return 0;
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }

        mem[i][j] = 1 + Math.max(Math.max(dfs(matrix, i - 1, j, matrix[i][j], mem),
                                        dfs(matrix, i + 1, j, matrix[i][j], mem)),
                                Math.max(dfs(matrix, i, j - 1, matrix[i][j], mem),
                                        dfs(matrix, i, j + 1, matrix[i][j], mem)));

        return mem[i][j];
    }

    public int getLongestIncreasingPath(int[][] matrix) {
         // cc
        int max = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] mem = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0 ; j < cols; j++) {
                max = Math.max(max, dfs(matrix, i, j, Integer.MIN_VALUE, mem));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LC0329 so = new LC0329();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(so.getLongestIncreasingPath(matrix));
    }
}
