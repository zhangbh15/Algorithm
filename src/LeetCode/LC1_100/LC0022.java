package LeetCode.LC1_100;

import java.util.*;

/**
 * Generate Parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class LC0022 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if (n <= 0) {
            return res;
        }

        dfs(new StringBuilder(), 0, n, res);
        return res;
    }

    private void dfs(StringBuilder sb, int delta, int n, List<String> res) {
        int len = sb.length();
        if (len == 2 * n && delta == 0) {
            res.add(sb.toString());
            return;
        }
        if (len == 2 * n || delta < 0) {
            return;
        }

        sb.append('(');
        dfs(sb, delta + 1, n, res);
        sb.setLength(len);

        sb.append(')');
        dfs(sb, delta - 1, n, res);
        sb.setLength(len);
    }
}
