package LeetCode.LC201_300;

import java.util.*;

/**
 * Meeting Rooms II
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 * NOTE: input types have been changed on April 15, 2019.
 * Please reset to default code definition to get new method signature.
 */
public class LC0253 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 ||
                intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }

        List<int[]> points = new ArrayList<>();

        for (int[] interval : intervals) {
            points.add(new int[] {interval[0], 1});
            points.add(new int[] {interval[1], -1});
        }

        points.sort(((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));

        int rooms = 0;
        int max = rooms;
        for (int[] point : points) {
            rooms += point[1];
            max = Math.max(max, rooms);
        }

        return max;
    }

    public static void main(String[] args) {
        LC0253 so = new LC0253();
        int[][] intervals = new int[][] {{0, 30}, {5, 10}, {15, 20}};
        int res = so.minMeetingRooms(intervals);
        System.out.println(res);

        intervals = new int[][] {{0, 30}, {5, 15}, {0, 5}};
        res = so.minMeetingRooms(intervals);
        System.out.println(res);

        intervals = new int[][] {{7, 10}, {2, 4}};
        res = so.minMeetingRooms(intervals);
        System.out.println(res);
    }
}
