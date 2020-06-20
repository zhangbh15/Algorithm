package LeetCode.LC1_100;

/**
 * Regular Expression Matching
 *
 * Given an input string (s) and a pattern (p),
 * implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 *
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * Example 3:
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 * Example 4:
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 *
 * Example 5:
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class LC0010 { // related to LC44

    public boolean isMatch(String s, String p) {
        Boolean[][] mem = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(s, 0, p, 0, mem);
    }

    private boolean dfs(String s, int idxS, String p, int idxP, Boolean[][] mem) {
        if (idxP == p.length()) {
            return idxS == s.length();
        }

        if (mem[idxS][idxP] != null) {
            return mem[idxS][idxP];
        }

        if (idxP + 1 == p.length() || p.charAt(idxP + 1) != '*') {
            if (idxS < s.length() && isMatched(s, idxS, p, idxP)) {
                return dfs(s, idxS + 1, p, idxP + 1, mem);
            } else {
                return false;
            }
        } else {
            int i = idxS - 1;
            while (i < s.length() && (i < idxS || isMatched(s, i, p, idxP))) { // (null || a, aa, aaa...)
                if (dfs(s, i + 1, p, idxP + 2, mem)) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }

    private boolean isMatched(String s, int i, String p, int j) {
        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'; // change
    }



    public static void main(String[] args) {
        LC0010 so = new LC0010();

        String s = "aaaaaa";
        String p = ".*";

        boolean res = so.isMatch(s, p);

        System.out.println(res);
    }
}
