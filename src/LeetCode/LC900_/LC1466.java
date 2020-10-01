package LeetCode.LC900_;

import javax.print.attribute.IntegerSyntax;
import java.util.*;


/**
 * Reorder Routes to Make All Paths Lead to the City Zero
 *
 * There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way
 * to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach the city 0 after reorder.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 2:
 *
 *
 *
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 3:
 *
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 */
public class LC1466 {
    public int minReorder(int n, int[][] connections) {
        if (connections == null || connections.length != n - 1) {
            throw new IllegalArgumentException();
        }

        // edges: directed from the start city to the end city
        Map<Integer, List<Integer>> toTree = buildToTree(connections);
        // edges: directed from the end city to the start city
        Map<Integer, List<Integer>> fromTree = buildFromTree(connections);

//        return dfs(toTree, fromTree, 0, new boolean[n]);
        return bfs(n, toTree, fromTree);
    }

    private int bfs(int n, Map<Integer, List<Integer>> toTree, Map<Integer, List<Integer>> fromTree) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[n];

        que.offer(0);
        visited[0] = true;

        int cnt = 0;
        while (!que.isEmpty()) {
            int cur = que.poll();
            List<Integer> toList = toTree.get(cur);
            List<Integer> fromList = fromTree.get(cur);

            if (fromList != null) {
                for (int from : fromList) {
                    if (!visited[from]) {
                        que.offer(from);
                        visited[from] = true;
                    }
                }
            }
            if (toList != null) {
                for (int to : toList) {
                    if (!visited[to]) {
                        que.offer(to);
                        visited[to] = true;
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }

    private int dfs(Map<Integer, List<Integer>> toTree, Map<Integer, List<Integer>> fromTree, int cur, boolean[] visited) {
        // Base case can be covered by recursion body.
//        if (!toTree.containsKey(cur) && !fromTree.containsKey(cur)) {
//            return 0;
//        }

        visited[cur] = true;
        List<Integer> toList = toTree.remove(cur);
        List<Integer> fromList = fromTree.remove(cur);

        int numReverse = 0;
        if (toList != null) {
            for (int to : toList) {
                if (!visited[to]) {
                    numReverse++;
                    numReverse += dfs(toTree, fromTree, to, visited);
                }
            }
        }
        if (fromList != null) {
            for (int from : fromList) {
                if (!visited[from]) {
                    numReverse += dfs(toTree, fromTree, from, visited);
                }
            }
        }

        visited[cur] = false;
        return numReverse;
    }

    private Map<Integer, List<Integer>> buildToTree(int[][] connections) {
        Map<Integer, List<Integer>> res = new HashMap<>();

        for (int[] pair : connections) {
            List<Integer> list = res.get(pair[0]);
            if (list == null) {
                list = new ArrayList<>();
                res.put(pair[0], list);
            }

            list.add(pair[1]);
        }

        return res;
    }

    private Map<Integer, List<Integer>> buildFromTree(int[][] connections) {
        Map<Integer, List<Integer>> res = new HashMap<>();

        for (int[] pair : connections) {
            List<Integer> list = res.get(pair[1]);
            if (list == null) {
                list = new ArrayList<>();
                res.put(pair[1], list);
            }

            list.add(pair[0]);
        }

        return res;
    }

    public static void main(String[] args) {
        LC1466 so = new LC1466();
        int n = 6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        int res = so.minReorder(n, connections);

        System.out.println(res);
    }
}
