package LeetCode.LC401_500;
import java.util.*;

/**
 * Pacific Atlantic Water Flow
 *
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 *
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 * Note:
 *
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 *
 *
 * Example:
 *
 * Given the following 5x5 matrix:
 *
 *   Pacific ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 *
 * Return:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class LC0417 {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        Queue<Integer> que = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            pacific[i][0] = true;
            que.offer(i * cols);
        }
        for (int j = 1; j < cols; j++) {
            pacific[0][j] = true;
            que.offer(j);
        }
        bfs(matrix, que, pacific, atlantic, res);

        for (int i = 0; i < rows; i++) {
            atlantic[i][cols - 1] = true;
            que.offer(i * cols + cols - 1);
        }
        for (int j = 0; j < cols - 1; j++) {
            atlantic[rows - 1][j] = true;
            que.offer((rows - 1) * cols + j);
        }
        bfs(matrix, que, atlantic, pacific, res);
        return res;
    }

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private void bfs(int[][] matrix, Queue<Integer> que, boolean[][] self, boolean[][] other, List<List<Integer>> res) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        while (!que.isEmpty()) {
            int cur = que.poll();
            int i = cur / cols;
            int j = cur % cols;
            if (other[i][j]) {
                res.add(Arrays.asList(i, j));
            }
            for (int[] d : DIRECTIONS) {
                int ii = i + d[0];
                int jj = j + d[1];
                if (ii >= 0 && ii < rows && jj >= 0 && jj < cols
                        && !self[ii][jj] && matrix[ii][jj] >= matrix[i][j]) {
                    self[ii][jj] = true;
                    que.offer(ii * cols + jj);
                }
            }
        }
    }



    // another version with post processing
    public List<List<Integer>> pacificAtlanticV2(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        Queue<Integer> que = new LinkedList<>();
        boolean[][] canFlowPacific = new boolean[rows][cols];
        boolean[][] canFlowAtlantic = new boolean[rows][cols];

        fillPacific(que, canFlowPacific, matrix);
        bfs(canFlowPacific, que, matrix);

        fillAtlantic(que, canFlowAtlantic, matrix);
        bfs(canFlowAtlantic, que, matrix);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (canFlowPacific[i][j] && canFlowAtlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void bfs(boolean[][] canFlow, Queue<Integer> que, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        while (!que.isEmpty()) {
            int cur = que.poll();
            int i = cur / cols;
            int j = cur % cols;

            for (int[] dir : DIRECTIONS) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                if (ii >= 0 && ii < rows && jj >= 0 && jj < cols &&
                        !canFlow[ii][jj] && matrix[ii][jj] >= matrix[i][j]) {
                    que.offer(ii * cols + jj);
                    canFlow[ii][jj] = true;
                }
            }
        }
    }

    private void fillPacific(Queue<Integer> que, boolean[][] canFlow, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            que.offer(i * cols);
            canFlow[i][0] = true;
        }
        for (int j = 1; j < cols; j++) {
            que.offer(j);
            canFlow[0][j] = true;
        }
    }

    private void fillAtlantic(Queue<Integer> que, boolean[][] canFlow, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            que.offer(i * cols + cols - 1);
            canFlow[i][cols - 1] = true;
        }
        for (int j = 0; j < cols - 1; j++) {
            que.offer((rows - 1) * cols + j);
            canFlow[rows - 1][j] = true;
        }
    }



    public static void main(String[] args) {
        LC0417 so = new LC0417();
        int[][] matrix = {{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
        List<List<Integer>> res = so.pacificAtlantic(matrix);
        System.out.println(res);
    }

}
