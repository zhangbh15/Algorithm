package LeetCode.LC201_300;
import java.util.*;


/**
 * Course Schedule
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 1 <= numCourses <= 10^5
 */
public class LC0207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1 <= numCourses <= 10^5
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        Map<Integer, List<Integer>> pre = new HashMap<>();

        for (int[] pair : prerequisites) {
            if (!pre.containsKey(pair[0])) {
                pre.put(pair[0], new LinkedList<>());
            }

            pre.get(pair[0]).add(pair[1]);
        }

        Boolean[] status = new Boolean[numCourses]; // null: unvisited, false: no circle, true: visiting

        for (int i = 0; i < numCourses; i++) {
            if (hasCircle(i, pre, status)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCircle(int idx, Map<Integer, List<Integer>> pre, Boolean[] status) {
        if (status[idx] != null) {
            return status[idx];
        }
        if (!pre.containsKey(idx)) {
            status[idx] = false;
            return false;
        }

        status[idx] = true;
        for (int next : pre.get(idx)) {
            if (hasCircle(next, pre, status)) {
                return true;
            }
        }
        status[idx] = false;
        return false;
    }

    public static void main(String[] args) {
        LC0207 so = new LC0207();
        int numCourse = 3;
        int[][] prerequisites = new int[][] {{0, 1}, {0, 2}, {1, 2}};
        boolean res = so.canFinish(numCourse, prerequisites);
        System.out.println(res);
    }
}
