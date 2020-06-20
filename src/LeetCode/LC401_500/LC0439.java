package LeetCode.LC401_500;

import java.util.*;

/**
 * Ternary Expression Parser
 *
 * Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression.
 * You can always assume that the given expression is valid and only consists of digits 0-9, ?, :,
 * T and F (T and F represent True and False respectively).
 *
 * Note:
 *
 * The length of the given string is ≤ 10000.
 * Each number will contain only one digit.
 * The conditional expressions group right-to-left (as usual in most languages).
 * The condition will always be either T or F. That is, the condition will never be a digit.
 * The result of the expression will always evaluate to either a digit 0-9, T or F.
 * Example 1:
 *
 * Input: "T?2:3"
 *
 * Output: "2"
 *
 * Explanation: If true, then result is 2; otherwise result is 3.
 * Example 2:
 *
 * Input: "F?1:T?4:5"
 *
 * Output: "4"
 *
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *
 *              "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
 *           -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
 *           -> "4"                                    -> "4"
 * Example 3:
 *
 * Input: "T?T?F:5:3"
 *
 * Output: "F"
 *
 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
 *
 *              "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
 *           -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
 *           -> "F"                                    -> "F"
 */
public class LC0439 {
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
            return expression;
        }

        Stack<String> stack = new Stack<>();
        int i = expression.length() - 1;

        while (i >= 0) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                i--;
            } else if (ch == ':') {
                i--;
            } else if (ch == '?') {
                String candT = stack.pop();
                String candF = stack.pop();
                i--;
                while (i >= 0 && expression.charAt(i) == ' ') {
                    i--;
                }
                if (expression.charAt(i--) == 'T') {
                    stack.push(candT);
                } else {
                    stack.push(candF);
                }
            } else { // string
                StringBuilder sb = new StringBuilder();
                while (i >= 0 && expression.charAt(i) != ' '
                        && expression.charAt(i) != ':' && expression.charAt(i) != '?') {
                    sb.append(expression.charAt(i--));
                }
                stack.push(sb.reverse().toString());
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        LC0439 so = new LC0439();
        String expression = "T?T?F:5:3";
        String res = so.parseTernary(expression);
        System.out.println(res);
    }
}
