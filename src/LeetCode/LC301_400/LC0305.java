package LeetCode.LC301_400;

import java.util.*;

/**
 * Number of Islands II
 *
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 *
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 *
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * Follow up:
 *
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class LC0305 {
    static class UnionFind {
        private final int[] parents;
        private final int[] sizes;

        public UnionFind(int n) {
            parents = new int[n];
            sizes = new int[n];
        }

        private int getRoot(int p) {
            int cur = p;
            while (parents[cur] != cur) {
                parents[cur] = parents[parents[cur]];
                cur = parents[cur];
            }

            parents[p] = cur;
            return cur;
        }

        public void union(int p, int q) {
            int pRoot = getRoot(p);
            int qRoot = getRoot(q);
            if (sizes[pRoot] < sizes[qRoot]) {
                parents[pRoot] = qRoot;
                sizes[qRoot] += sizes[pRoot];
            } else {
                parents[qRoot] = pRoot;
                sizes[pRoot] += sizes[qRoot];
            }
        }

        public boolean find(int p, int q) {
            return getRoot(p) == getRoot(q);
        }

        public void addIsland(int p) {
            parents[p] = p;
            sizes[p] = 1;
        }

        public boolean isIsland(int p) {
            return sizes[p] > 0;
        }
    }

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0) {
            return null;
        }

        List<Integer> res = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);

        int numIslands = 0;
        for (int[] position : positions) {
            int i = position[0];
            int j = position[1];
            int idx = i * n + j;

            if (!uf.isIsland(idx)) {
                uf.addIsland(idx);
                numIslands++;

                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    int neighborIdx = ii * n + jj;

                    if (ii >= 0 && ii < m && jj >= 0 && jj < n
                            && uf.isIsland(neighborIdx)
                            && !uf.find(idx, neighborIdx)) {
                            uf.union(idx, neighborIdx);
                            numIslands--;
                    }
                }
            }

            res.add(numIslands);
        }

        return res;
    }

    public static void main(String[] args) {
        LC0305 so = new LC0305();
        int m = 1;
        int n = 3;
        int[][] positions = new int[][] {{0, 0}, {0, 2}, {0, 1}};

        List<Integer> out = so.numIslands2(m, n, positions);
        System.out.println(out);
    }
}
