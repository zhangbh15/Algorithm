package LeetCode.LC301_400;

import java.util.*;

/**
 * Minimum Height Trees
 *
 * A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi]
 * indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose
 * any node of the tree as the root. When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 *
 *
 * Example 1:
 *
 *      0           1           2           3
 *      |         / | \         |           |
 *      1        0  2  3        1           1
 *    /  \                    /  \        /  \
 *   2   3                   0    3      0    2
 *
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 * Example 2:
 *
 *      3               4
 *  /  /  |  \        /  \
 * 0  1   2   4      5    3
 *            |         / | \
 *            5        0  1  2
 *
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 * Example 3:
 *
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 4:
 *
 * Input: n = 2, edges = [[0,1]]
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 */
public class LC0310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }
        if (n == 2) {
            return Arrays.asList(0, 1);
        }
        res = new LinkedList<>();
        minHeight = n;
        List<List<Integer>> neighbors = buildGraph(n, edges);
        for (int i = 0; i < n; i++) {
            checkHeight(n, i, neighbors);
        }

        return res;
    }

    private int minHeight;
    private List<Integer> res;

    private void checkHeight(int n, int root, List<List<Integer>> neighbors){
        boolean[] visited = new boolean[n];
        int height = 0;
        Queue<Integer> que = new LinkedList<>();
        que.offer(root);
        visited[root] = true;

        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int cur = que.poll();
                List<Integer> nexts = neighbors.get(cur);
                for (int next : nexts) {
                    if (!visited[next]) {
                        visited[next] = true;
                        que.offer(next);
                    }
                }
            }
            height++;
        }

        if (height < minHeight) {
            minHeight = height;
            res = new LinkedList<>();
            res.add(root);
        } else if (height == minHeight) {
            res.add(root);
        }
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new LinkedList<>());
        }
        for (int[] edge : edges) {
            for (int i = 0; i <= 1; i++) { // both directions
                List<Integer> list = graph.get(edge[i]);
                list.add(i == 0 ? edge[1] : edge[0]);
            }
        }
        return graph;
    }


    // Topological Sort  O(N) solution
    public List<Integer> findMinHeight(int n, int[][] edges) {
        if (n == 1) {
            return Arrays.asList(0);
        }
        if (n == 2) {
            return Arrays.asList(0, 1);
        }

        List<Set<Integer>> graph = buildGraphSet(n, edges);
        return trim(n, graph);
    }

    private List<Set<Integer>> buildGraphSet(int n, int[][] edges) {
        List<Set<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    private List<Integer> trim(int n, List<Set<Integer>> graph) {
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                que.offer(i);
            }
        }

        int remain = n;

        while (remain > 2) {
            int size = que.size();
            remain -= size;
            for (int k = 0; k < size; k++) {
                int cur = que.poll();
                int neighbor = graph.get(cur).iterator().next();
                Set<Integer> set = graph.get(neighbor);
                set.remove(cur);
                if (set.size() == 1) {
                    que.offer(neighbor);
                }
            }
        }

        return new LinkedList<>(que);
    }

    public static void main(String[] args) {
        LC0310 so = new LC0310();
        int n = 4;
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
        List<Integer> res = so.findMinHeight(n, edges);
        System.out.println(res);
    }
}
