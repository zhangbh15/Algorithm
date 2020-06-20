package LeetCode.LC101_200;

import java.util.*;

/**
 * Number of Islands
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */
public class LC0200 {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private void bfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Integer> que = new LinkedList<>();
        grid[i][j] = '0';
        que.offer(i * cols + j);

        while (!que.isEmpty()) {
            int cur = que.poll();
            int m = cur / cols;
            int n = cur % cols;

            for (int[] dir : DIRECTIONS) {
                int mm = m + dir[0];
                int nn = n + dir[1];

                if (mm >= 0 && mm < rows && nn >= 0 && nn < cols
                        && grid[mm][nn] == '1') {
                    grid[mm][nn] = '0';
                    que.offer(mm * cols + nn);
                }
            }
        }
    }

    public int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private void dfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';
        for (int[] dir : DIRECTIONS) {
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }

    public int numIslandsUnionFind(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind uf = new UnionFind(rows * cols);

        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    int idx = i * cols + j;
                    uf.addIsland(idx);
                    cnt++;

                    for (int[] dir : DIRECTIONS) {
                        int ii = i + dir[0];
                        int jj = j + dir[1];
                        int neighbor = ii * cols + jj;

                        if (ii >= 0 && ii < rows && jj >= 0 && jj < cols
                                && uf.isIsland(neighbor) && !uf.find(idx, neighbor)) {
                            uf.union(idx, neighbor);
                            cnt--;
                        }
                    }
                }
            }
        }

        return cnt;
    }

    static class UnionFind {
        private final int[] parents;
        private final int[] sizes;

        public UnionFind(int cap) {
            parents = new int[cap];
            sizes = new int[cap];
        }

        private int getRoot(int p) {
            int cur = p;
            while (parents[p] != cur) {
                parents[cur] = parents[parents[cur]];
                cur = parents[cur];
            }

            parents[p] = cur;
            return cur;
        }

        public boolean find(int p, int q) {
            return getRoot(p) == getRoot(q);
        }

        public void union(int p, int q) {
            int rootP = getRoot(p);
            int rootQ = getRoot(q);
            if (sizes[rootP] < sizes[rootQ]) {
                parents[rootP] = rootQ;
                sizes[rootQ] += sizes[rootP];
            } else {
                parents[rootQ] = rootP;
                sizes[rootP] += sizes[rootQ];
            }
        }

        public void addIsland(int n) {
            parents[n] = n;
            sizes[n] = 1;
        }

        public boolean isIsland(int n) {
            return sizes[n] != 0;
        }
    }

    public static void main(String[] args) {
        LC0200 so = new LC0200();
        char[][] grid = new char[][] {{'1', '0', '1'}, {'1', '1', '0'}, {'0', '0', '0'}};
        int bfs = so.numIslandsBFS(grid);
        grid = new char[][] {{'1', '0', '1'}, {'1', '1', '0'}, {'0', '0', '0'}};
        int dfs = so.numIslandsDFS(grid);
        grid = new char[][] {{'1', '0', '1'}, {'1', '1', '0'}, {'0', '0', '0'}};
        int unionFind = so.numIslandsUnionFind(grid);
        System.out.println(bfs);
        System.out.println(dfs);
        System.out.println(unionFind);
    }
}
