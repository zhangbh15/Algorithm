package LeetCode.LC1_100;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert Interval
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * NOTE: input types have been changed on April 15, 2019.
 * Please reset to default code definition to get new method signature.
 */
public class LC0057 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0
                || intervals[0] == null || intervals[0].length == 0) {
            return new int[][] {newInterval};
        }
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }

        List<int[]> list = new ArrayList<>();
        int[] insertInterval = newInterval;
        boolean inserted = false;
        for (int[] interval : intervals) {
            if (interval[1] < insertInterval[0]) {
                list.add(interval);
            } else if (interval[0] > insertInterval[1]) {
                if (!inserted) {
                    list.add(insertInterval);
                    inserted = true;
                }
                list.add(interval);
            } else {
                insertInterval = new int[] {Math.min(interval[0], insertInterval[0]),
                        Math.max(interval[1], insertInterval[1])};
            }
        }

        if (!inserted) {
            list.add(insertInterval);
        }

        int[][] res = new int[list.size()][];
        int k = 0;
        for (int[] interval : list) {
            res[k++] = interval;
        }
        return res;
    }
}
