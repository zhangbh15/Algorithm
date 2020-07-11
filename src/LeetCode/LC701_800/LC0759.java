package LeetCode.LC701_800;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Employee Free Time
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees,
 * also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
 * not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2,
 * and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer,
 * as they have zero length.
 *
 *
 * Example 1:
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 *
 * Example 2:
 *
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 *
 * Constraints:
 *
 * 1 <= schedule.length , schedule[i].length <= 50
 * 0 <= schedule[i].start < schedule[i].end <= 10^8
 */
public class LC0759 {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new LinkedList<>();
        if (schedule == null || schedule.size() == 0 || schedule.get(0) == null || schedule.get(0).size() == 0) {
            return res;
        }

        List<int[]> points = new ArrayList<>();
        for (List<Interval> employee : schedule) {
            for (Interval workTime : employee) {
                points.add(new int[] {workTime.start, 1});
                points.add(new int[] {workTime.end, -1});
            }
        }

        points.sort((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        int numWorkingEmployee = 0;
        Interval interval = new Interval();

        for (int[] point : points) {
            if (numWorkingEmployee == 0) {
                interval.end = point[0];
                res.add(interval);
                interval = new Interval();
            }
            numWorkingEmployee += point[1];
            if (numWorkingEmployee == 0) {
                interval.start = point[0];
            }
        }
        res.remove(0);
        return res;
    }

    private static class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }
}
