package LeetCode.LC201_300;
import java.util.*;


/**
 * Course Schedule II
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class LC0210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[] {};
        }

        Map<Integer, List<Integer>> pre = new HashMap<>();
        for (int[] pair : prerequisites) {
            if (!pre.containsKey(pair[0])) {
                pre.put(pair[0], new LinkedList<>());
            }
            pre.get(pair[0]).add(pair[1]);
        }
        Boolean[] status = new Boolean[numCourses];
        List<Integer> sol = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (!sort(i, pre, status, sol)) {
                return new int[] {};
            }
        }

        int[] ret = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ret[i] = sol.remove(0);
        }

        return ret;
    }

    // return if possible to complete all courses.
    private boolean sort(int i, Map<Integer, List<Integer>> pre, Boolean[] status, List<Integer> sol) {
        if (status[i] != null) {
            return status[i];
        }
        if (!pre.containsKey(i)) {
            sol.add(i);
            status[i] = true;
            return true;
        }

        status[i] = false;
        for (int next : pre.get(i)) {
            if (!sort(next, pre, status, sol)) {
                return false;
            }
        }

        status[i] = true;
        sol.add(i);
        return true;
    }

    public static void main(String[] args) {
        LC0210 so = new LC0210();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] res = so.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(res));
    }
}
