package LeetCode.LC101_200;

import LeetCode.LC201_300.LC0211;

import java.util.*;

/**
 * Word Break
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class LC0139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }

        Set<String> dict = new HashSet<>(wordDict);
        boolean[] visited = new boolean[s.length()];
        return dfs(0, s, dict, visited);
    }

    private boolean dfs(int idx, String str, Set<String> dict, boolean[] visited) {
        if (idx == str.length()) {
            return true;
        }

        if (visited[idx]) {
            return false;
        }

        for (int i = idx + 1; i <= str.length(); i++) {
            if (dict.contains(str.substring(idx, i))) {
                if (dfs(i, str, dict, visited)) {
                    return true;
                }
            }
        }
        visited[idx] = true;
        return false;
    }

    // TODO: DP

    public static void main(String[] args) {
        LC0139 so = new LC0139();
        String str = "leetcode";
        List<String> dict = Arrays.asList("leet", "code");
        System.out.println(so.wordBreak(str, dict));
    }
}
