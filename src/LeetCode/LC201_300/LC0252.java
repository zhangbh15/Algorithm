package LeetCode.LC201_300;

import java.util.*;

/**
 * Meeting Rooms
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 * NOTE: input types have been changed on April 15, 2019.
 * Please reset to default code definition to get new method signature.
 */
public class LC0252 {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0
                || intervals[0] == null ||intervals[0].length == 0) {
            return true;
        }

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }

        return true;
    }
}
