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

    public List<String> wordBreakII(String s, HashSet<String> dict) {
        int len = s.length();
        boolean dp[] = new boolean[len + 1];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        dp[len] = true;
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = false;
            for (int j = i + 1; j < len; j++) {
                String str = s.substring(i, j + 1);
                if (dict.contains(str) && dp[j + 1]) {
                    dp[i] = true;
                    List<Integer> curs = graph.get(j + 1);
                    if (curs == null) {
                        graph.put(j + 1, new ArrayList<>());
                    }
                    graph.get(j + 1).add(i);
                }
            }
        }
        List<String> res = new ArrayList<>();
        dfs(s, res, new StringBuilder(), len, graph);
        return res;
    }

    private void dfs(String s, List<String> res, StringBuilder path, int idx, HashMap<Integer, List<Integer>> graph) {
        if (idx == 0) {
            // TODO: res.add(convert(path));
            return;
        }
        for (Integer next : graph.get(idx)) {
            int setBackLen = path.length();
            path.append(s.substring(next, idx) + "");
            dfs(s, res, path, next, graph);
            path.setLength(setBackLen);
        }
    }

//    private void dfs(res, path, s, idx) {
//        if (idx == len) {
//            res.add(path.toString());
//            return;
//        }
//        for (int i = idx + 1; i <= len; i++) {
//            str = s.substring(idx, i);
//            if (dic.contains(str)) {
//                path.append(str);
//                dfs(res.path, s, i, dict);
//                path.setBack();
//            }
//        }
//    }
//    public List<String> wBII(s, dict) {
//        List<String> res;
//        dfs(res, new StringBuilder(), s, 0, dict);
//        return res;
//    }

}
