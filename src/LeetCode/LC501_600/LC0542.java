package LeetCode.LC501_600;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 01 Matrix
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 *
 * Note:
 *
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */
public class LC0542 {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return null;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        Queue<Integer> que = new LinkedList<>();
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                    que.offer(i * cols + j);
                }
            }
        }

        int distance = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int cur = que.poll();
                for (int[] d : DIRECTIONS) {
                    int ii = cur / cols +d[0];
                    int jj = cur % cols + d[1];
                    if (ii >= 0 && ii < rows && jj >= 0 && jj < cols
                            && matrix[ii][jj] == 1 && res[ii][jj] == 0) {
                        res[ii][jj] = distance;
                        que.offer(ii * cols + jj);
                    }
                }
            }
            distance++;
        }
        return res;
    }

    public static void main(String[] args) {
        LC0542 so = new LC0542();
        int[][] test = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] res = so.updateMatrix(test);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }
}
