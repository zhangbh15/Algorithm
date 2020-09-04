package LeetCode.LC401_500;

import java.util.*;

public class LC0417_FollowUp {
    /**
     * Follow-up return the place with the shortest total distance from Pacific and Atlantic.
     * Meanwhile there may be islands marked with -1 that cannot be reached from any ocean.
     * If there are more than one such places, return any of them.
     * If there are no such places, return {-1, -1}.
     */
    public int[] shortestDistPlace(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[] {-1, -1};
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        Queue<Integer> que = new LinkedList<>();
        // If consider edge places, then fill the matrix with -1.
        // If do not consider edge places, just leave them as 0.
        int[][] distPacific = new int[rows][cols];
        int[][] distAtlantic = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distPacific[i][j] = -1;
                distAtlantic[i][j] = -1;
            }
        }

        fillPacific(que, distPacific, matrix);
        bfs(distPacific, que, matrix);

        fillAtlantic(que, distAtlantic, matrix);
        bfs(distAtlantic, que, matrix);

        int minDist = Integer.MAX_VALUE;
        int[] res = {-1, -1};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (distPacific[i][j] == -1 || distAtlantic[i][j] == -1) {
                    continue;
                }
                int dist = distPacific[i][j] + distAtlantic[i][j];
                // use <= to handle the case where the minDist == Integer.MAX_VALUE;
                if (dist <= minDist) {
                    minDist = dist;
                    res[0] = i;
                    res[1] = j;
                }
            }
        }

        return res;
    }

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void bfs(int[][] dist, Queue<Integer> que, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int minDist = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int k = 0; k < size; k++) {
                int cur = que.poll();
                int i = cur / cols;
                int j = cur % cols;

                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < rows && jj >= 0 && jj < cols &&
                            dist[ii][jj] == -1 && matrix[ii][jj] >= matrix[i][j]) {
                        // no need to check -1 here, since all the places in que have positive values.
                        que.offer(ii * cols + jj);
                        dist[ii][jj] = minDist;
                    }
                }
            }

            minDist++;
        }
    }

    private void fillPacific(Queue<Integer> que, int[][] dist, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == -1) {
                continue;
            }
            que.offer(i * cols);
            dist[i][0] = 0;
        }
        for (int j = 1; j < cols; j++) {
            if (matrix[0][j] == -1) {
                continue;
            }
            que.offer(j);
            dist[0][j] = 0;
        }
    }

    private void fillAtlantic(Queue<Integer> que, int[][] dist, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][cols - 1] == -1) {
                continue;
            }
            que.offer(i * cols + cols - 1);
            dist[i][cols - 1] = 0;
        }
        for (int j = 0; j < cols - 1; j++) {
            if (matrix[rows - 1][j] == -1) {
                continue;
            }
            que.offer((rows - 1) * cols + j);
            dist[rows - 1][j] = 0;
        }
    }


    public static void main(String[] args) {
        LC0417_FollowUp so = new LC0417_FollowUp();
        int[][] matrix = {{0, 1, 2, 3, -1}, {0, 2, 3, 4, -1}, {2, -1, 5, 5, -1},
                {4, -1, 8, -1, -1}, {-1, -1, 8, 8, 0}};
        int[] res = so.shortestDistPlace(matrix);
        System.out.println(Arrays.toString(res));
    }
}
