package LeetCode.LC1_100;

import java.util.*;

/**
 * Merge Intervals
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019.
 * Please reset to default code definition to get new method signature.
 */
public class LC0056 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0
                || intervals[0] == null || intervals[0].length == 0) {
            return new int[][] {};
        }

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] prev = intervals[0];
        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            if (prev[1] >= interval[0]) {
                prev = new int[] {prev[0], Math.max(prev[1], interval[1])};
            } else {
                res.add(prev);
                prev = interval;
            }
        }

        res.add(prev);

        int[][] ret = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }

        return ret;
    }

    public static void main(String[] args) {
        LC0056 so = new LC0056();
        int[][] input = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res = so.merge(input);
        System.out.println(Arrays.deepToString(res));

        input = new int[][] {{1, 6}, {4, 5}};
        res = so.merge(input);
        System.out.println(Arrays.deepToString(res));

        input = new int[][] {{1, 4}, {2, 3}};
        res = so.merge(input);
        System.out.println(Arrays.deepToString(res));
    }
}
