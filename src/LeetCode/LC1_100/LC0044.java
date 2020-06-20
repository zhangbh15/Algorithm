package LeetCode.LC1_100;
import java.util.*;

/**
 * Wildcard Matching
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 *
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 * Example 4:
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 *
 * Example 5:
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */
public class LC0044 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int i = 0;
        int j = 0;
        int sLen = s.length();
        int pLen = p.length();
        int flag = -1;
        int tempi = i;
        while (i < sLen) {
            if (j < pLen && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < pLen && p.charAt(j) == '*') {
                flag = ++j;
                tempi = i;
            } else if (flag != -1) {
                j = flag;
                i = ++tempi;
            } else {
                return false;
            }
        }

        while (j < pLen && p.charAt(j) == '*') {
            j++;
        }
        return j == pLen;
    }

    public static void main(String[] args) {
        LC0044 so = new LC0044();
        String s = "acdcb";
        String p = "*a*b";
        boolean res = so.isMatch(s, p);
        System.out.println(res);
    }
}


