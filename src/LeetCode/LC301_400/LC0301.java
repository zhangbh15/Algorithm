package LeetCode.LC301_400;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */
public class LC0301 {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            res.add("");
            return res;
        }

        int[] removes = minRemoves(s);
        Set<String> set = new HashSet<>();
        dfs(set, s, new StringBuilder(), 0, removes[0], removes[1], 0);
        res.addAll(set);

        return res;
    }

    private int[] minRemoves(String str) {
        int removeLeft = 0;
        int removeRight = 0;

        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                removeLeft++;
            } else if (ch == ')') {
                if (removeLeft > 0) {
                    removeLeft--;
                } else {
                    removeRight++;
                }
            }
        }

        return new int[] {removeLeft, removeRight};
    }

    private void dfs(Set<String> res, String s, StringBuilder sb, int idx, int removeLeft, int removeRight, int delta) {
        if (idx == s.length() && removeLeft == 0 && removeRight == 0 && delta == 0) {
            res.add(sb.toString());
            return;
        }
        if (idx == s.length() || removeLeft < 0 || removeRight < 0 || delta < 0) {
            return;
        }

        char ch = s.charAt(idx);
        if (ch == '(') {
            // keep the '('
            sb.append('(');
            dfs(res, s, sb, idx + 1, removeLeft, removeRight, delta + 1);
            sb.setLength(sb.length() - 1);

            // remove the '("
            dfs(res, s, sb, idx + 1, removeLeft - 1, removeRight, delta);
        } else if (ch == ')') {
            // keep the ')'
            sb.append(')');
            dfs(res, s, sb, idx + 1, removeLeft, removeRight, delta - 1);
            sb.setLength(sb.length() - 1);

            // remove the ')'
            dfs(res, s, sb,idx + 1, removeLeft, removeRight - 1, delta);
        } else {
            sb.append(ch);
            dfs(res, s, sb, idx + 1, removeLeft, removeRight, delta);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LC0301 so = new LC0301();
        String str1 = "()())()";
        String str2 = "(a)())()";
        String str3 = ")(";
        String str4 = ")(f";

        List<String> res1 = so.removeInvalidParentheses(str1);
        List<String> res2 = so.removeInvalidParentheses(str2);
        List<String> res3 = so.removeInvalidParentheses(str3);
        List<String> res4 = so.removeInvalidParentheses(str4);

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
    }
}
