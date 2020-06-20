package LeetCode.LC201_300;


/**
 * Graph Valid Tree
 *
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * Example 2:
 *
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Note: you can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */
public class LC0261 {
    static class UnionFind{
        private final int[] parents;
        private final int[] sizes;

        public UnionFind(int n) {
            parents = new int[n];
            sizes = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        private int root(int p) {
            int cur = p;
            while (parents[cur] != cur) {
                parents[cur] = parents[parents[cur]];
                cur = parents[cur];
            }
            parents[p] = cur;
            return cur;
        }

        public boolean find(int p, int q) {
            return root(p) == root(q);
        }

        public void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);
            if (sizes[rootP] < sizes[rootQ]) {
                parents[rootP] = rootQ;
                sizes[rootQ] += sizes[rootP];
            } else {
                parents[rootQ] = rootP;
                sizes[rootP] += sizes[rootQ];
            }
        }

        public boolean allConnected() {
            return sizes[root(0)] == sizes.length;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.find(edge[0], edge[1])) {
                return false;
            }
            uf.union(edge[0], edge[1]);
        }
        return uf.allConnected();
    }

    public static void main(String[] args) {
        LC0261 so = new LC0261();
        int n = 5;
        int[][] edges = new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(so.validTree(n, edges));
    }
}
