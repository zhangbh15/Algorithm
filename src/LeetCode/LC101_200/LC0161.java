package LeetCode.LC101_200;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * One Edit Distance
 *
 * Given two strings s and t, determine if they are both one edit distance apart.
 *
 * Note:
 *
 * There are 3 possiblities to satisify one edit distance apart:
 *
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 * Example 1:
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * Example 2:
 *
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * Example 3:
 *
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 */
public class LC0161 {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || Math.abs(s.length() - t.length()) > 1) {
            return false;
        }

        boolean equalLen = s.length() == t.length();
        if (s.length() > t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }

        int i = 0;
        int j = 0;
        int dist = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                continue;
            }

            if (equalLen) {
                i++;
            }
            j++;
            dist++;
            if (dist > 1) {
                return false;
            }
        }

        if (j != t.length()) {
            dist++;
        }
        return dist == 1;
    }

    public static void main(String[] args) {
        LC0161 so = new LC0161();
        String s = "ab";
        String t = "acb";
        boolean res = so.isOneEditDistance(s, t);
        System.out.println(res);
    }
}
