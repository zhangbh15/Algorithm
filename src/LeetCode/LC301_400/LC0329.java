package LeetCode.LC301_400;

import java.util.*;

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
    public int longestIncreasingPath(int[][] matrix) {
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

    private int dfs(int[][] matrix, int i, int j, int preVal, int[][] mem) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= preVal) {
            return 0;
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }

        // no cycle in a increasing path
        mem[i][j] = 1 + Math.max(Math.max(dfs(matrix, i - 1, j, matrix[i][j], mem),
                dfs(matrix, i + 1, j, matrix[i][j], mem)),
                Math.max(dfs(matrix, i, j - 1, matrix[i][j], mem),
                        dfs(matrix, i, j + 1, matrix[i][j], mem)));

        return mem[i][j];
    }


    /**
     * Followup 1: return any of the longest increasing paths.
     */
    public List<int[]> anyLongestPath(int[][] matrix) {
        // cc
        int rows = matrix.length;
        int cols = matrix[0].length;
        Map<Integer, Integer> path = new HashMap<>();
        int[][] mem = new int[rows][cols];

        int max = 0;
        int maxStart = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int pathLen = dfs(path, matrix, i, j, Integer.MIN_VALUE, mem);
                if (pathLen > max) {
                    max = pathLen;
                    maxStart = i * cols + j;
                }
            }
        }

        return recover(path, maxStart, rows, cols);
    }

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int dfs(Map<Integer, Integer> path, int[][] matrix, int i, int j, int preVal, int[][] mem) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= preVal) {
            return 0;
        }
        if (mem[i][j] != 0) {
            return mem[i][j];
        }

        int maxLen = 0;
        int maxPosition = i * cols + j;
        for (int[] dir : DIRECTIONS) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            int pathLen = dfs(path, matrix, ii, jj, matrix[i][j], mem);
            if (pathLen > maxLen) {
                maxLen = pathLen;
                maxPosition = ii * cols + jj;
            }
        }

        mem[i][j] = maxLen + 1;
        if (maxLen != 0) {
            path.put(i * cols + j, maxPosition);
        }

        return mem[i][j];
    }

    private List<int[]> recover(Map<Integer, Integer> path, int start, int rows, int cols) {
        List<int[]> res = new ArrayList<>();
        Integer cur = start;
        while (cur != null) {
            res.add(new int[] {cur / cols, cur % cols});
            cur = path.get(cur);
        }
        return res;
    }


    /**
     * Followup 2: return all of the longest increasing path.
     */
    public List<List<int[]>> allLongestPath(int[][] matrix) {
        // cc
        int rows = matrix.length;
        int cols = matrix[0].length;
        Map<Integer, List<Integer>> paths = new HashMap<>();
        int[][] mem = new int[rows][cols];

        int max = 0;
        List<Integer> maxStarts = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int pathLen = dfsAllPath(paths, matrix, i, j, Integer.MIN_VALUE, mem);
                if (pathLen > max) {
                    max = pathLen;
                    maxStarts = new ArrayList<>();
                    maxStarts.add(i * cols + j);
                } else if (pathLen == max) {
                    maxStarts.add(i * cols + j);
                }
            }
        }

        return recoverAll(paths, maxStarts, rows, cols);
    }

    private int dfsAllPath(Map<Integer, List<Integer>> path, int[][] matrix, int i, int j, int preVal, int[][] mem) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= preVal) {
            return 0;
        }
        if (mem[i][j] != 0) {
            return mem[i][j];
        }

        int maxLen = 0;
        List<Integer> maxPositions = new ArrayList<>();
        for (int[] dir : DIRECTIONS) {
            int ii = i + dir[0];
            int jj = j + dir[1];

            int pathLen = dfsAllPath(path, matrix, ii, jj, matrix[i][j], mem);

            if (pathLen > maxLen) {
                maxLen = pathLen;
                maxPositions = new ArrayList<>();
                maxPositions.add(ii * cols + jj);
            } else if (pathLen == maxLen) {
                maxPositions.add(ii * cols + jj);
            }
        }

        mem[i][j] = maxLen + 1;
        if (maxLen != 0) {
            path.put(i * cols + j, maxPositions);
        }

        return mem[i][j];
    }

    private List<List<int[]>> recoverAll(Map<Integer, List<Integer>> paths, List<Integer> starts, int rows, int cols) {
        List<List<int[]>> res = new ArrayList<>();
        for (int start : starts) {
            List<int[]> path = new ArrayList<>();
            path.add(new int[] {start / cols, start % cols});
            recoverSingle(res, path, paths, start, rows, cols);
        }
        return res;
    }

    private void recoverSingle(List<List<int[]>> res, List<int[]> path, Map<Integer, List<Integer>> pathMap, int cur, int rows, int cols) {
        List<Integer> nexts = pathMap.get(cur);
        if (nexts == null) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int next : nexts) {
            path.add(new int[] {next / cols, next % cols});
            recoverSingle(res, path, pathMap, next, rows, cols);
            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {
        LC0329 so = new LC0329();
        int[][] matrix = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};

        System.out.println(so.longestIncreasingPath(matrix));

        for (int[] pos : so.anyLongestPath(matrix)) {
            System.out.println(Arrays.toString(pos));
        }

        for (List<int[]> path : so.allLongestPath(matrix)) {
            for (int[] pos : path) {
                System.out.print(Arrays.toString(pos));
            }
            System.out.println();
        }
    }
}
