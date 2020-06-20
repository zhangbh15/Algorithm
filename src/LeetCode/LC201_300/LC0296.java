package LeetCode.LC201_300;
import LeetCode.LC301_400.LC0317;

import java.util.*;

/**
 * Best Meeting Point
 *
 * A group of two or more people wants to meet and minimize the total travel distance.
 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Example:
 *
 * Input:
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 6
 *
 * Explanation: Given three people living at (0,0), (0,4), and (2,2):
 *              The point (0,2) is an ideal meeting point, as the total travel distance
 *              of 2+2+2=6 is minimal. So return 6.
 */
public class LC0296 {
    public int minTotalDistanceMath(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        List<Integer> onesX = new ArrayList<>();
        List<Integer> onesY = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    onesX.add(i);
                }
            }
        }

        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    onesY.add(j);
                }
            }
        }

        int meetPointX = onesX.get(onesX.size() / 2);
        int meetPointY = onesY.get(onesY.size() / 2);

        int dist = 0;

        for (int x : onesX) {
            dist += Math.abs(meetPointX - x);
        }
        for (int y : onesY) {
            dist += Math.abs(meetPointY - y);
        }

        return dist;
    }

    private int findKth(List<Integer> list, int start, int end, int k) {
        int left = start - 1;

        for (int i = start; i < end; i++) {
            if (list.get(i) < list.get(end)) {
                swap(list, ++left, i);
            }
        }

        swap(list, ++left, end);

        int rank = left - start + 1;
        if (rank == k) {
            return list.get(left);
        } else if (rank > k) {
            return findKth(list, start, left - 1, k);
        } else {
            return findKth(list, left + 1, end, k - rank);
        }
    }

    private void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
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
            for (int j = 0; j < cols; j++) {
                    minDistance = Math.min(minDistance, distance[i][j]);
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private void bfs(int[][] grid, int i, int j, int[][] distance) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Integer> que = new LinkedList<>();
        que.offer(i * cols + j);
        boolean[][] visited = new boolean[rows][cols];
        visited[i][j] = true;
        int minLen = 1;

        while (!que.isEmpty()) {
            int size = que.size();
            for (int k = 0; k < size; k++) {
                int cur = que.poll();
                for (int[] d : DIRECTIONS) {
                    int ii = cur / cols + d[0];
                    int jj = cur % cols + d[1];
                    if (ii >= 0 && ii < rows && jj >= 0 && jj < cols && !visited[ii][jj]) {
                        distance[ii][jj] += minLen;
                        visited[ii][jj] = true;
                        que.offer(ii * cols + jj);
                    }
                }
            }
            minLen++;
        }
    }

    public static void main(String[] args) {
        LC0296 so = new LC0296();
         int[][] test = {{1,0,0,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
//        int[][] test = {{1, 1}, {1, 1}};
        int res = so.minTotalDistance(test);
        int resMath = so.minTotalDistanceMath(test);
        System.out.println(res);
        System.out.println(resMath);
    }
}
