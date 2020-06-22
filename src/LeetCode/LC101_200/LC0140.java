package LeetCode.LC101_200;
import java.util.*;

/**
 * Word Break II
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class LC0140 {
    // DFS with pruning
    public List<String> wordBreakDFS(String s, List<String> wordDict) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return res;
        }

        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] mem = new Boolean[s.length()];
        dfs(0, new StringBuilder(), mem, s, dict, res);
        return res;
    }

    private boolean dfs(int idx, StringBuilder sb, Boolean[] mem, String s, Set<String> dict, List<String> res) {
        int len = s.length();
        if (idx == len) {
            sb.setLength(sb.length() - 1);
            res.add(sb.toString());
            return true;
        }

        if (mem[idx] != null && !mem[idx]) {
            return false;
        }

        boolean flag = false;
        for (int i = idx + 1; i <= len; i++) {
            String str = s.substring(idx, i);
            if (dict.contains(str)) {
                int sbLen = sb.length();
                sb.append(str).append(' ');
                flag |= dfs(i, sb, mem, s, dict, res);
                sb.setLength(sbLen);
            }
        }

        mem[idx] = flag;
        return flag;
    }


    // DP
    public List<String> wordBreakDP(String s, List<String> wordDict) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return res;
        }

        Set<String> dict = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j <= len; j++) {
                if (dp[j] && dict.contains(s.substring(i, j))) {
                    dp[i] = true;
                    if (!graph.containsKey(i)) {
                        graph.put(i, new HashSet<>());
                    }
                    graph.get(i).add(j);
                }
            }
        }

        recover(0, new StringBuilder(), s, graph, res);
        return res;
    }

    private void recover(int idx, StringBuilder sb, String s, Map<Integer, Set<Integer>> path, List<String> res) {
        if (idx == s.length()) {
            sb.setLength(sb.length() - 1);
            res.add(sb.toString());
            return;
        }

        Set<Integer> next = path.get(idx);
        if (next == null) {
            return;
        }

        for (Integer i : next) {
            int setBack = sb.length();
            sb.append(s, idx, i).append(' ');
            recover(i, sb, s, path, res);
            sb.setLength(setBack);
        }
    }

    public static void main(String[] args) {
        LC0140 so = new LC0140();
        String s = "catsanddog";
        List<String> dict = Arrays.asList("cat", "cats", "sand", "and", "dog");
        List<String> res = so.wordBreakDP(s, dict);
        System.out.println(res);
    }
}