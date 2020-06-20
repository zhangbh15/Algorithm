package LeetCode.LC201_300;
import java.util.*;

/**
 * Different Ways to Add Parentheses
 *
 * Given a string of numbers and operators,
 * return all possible results from computing all the different possible ways to group numbers and operators.
 * The valid operators are +, - and *.
 *
 * Example 1:
 *
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * Example 2:
 *
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class LC0241 {
    private List<Integer> dfs(String input, int start, int end) {
        List<Integer> res = new ArrayList<>();

        boolean isNum = true;
        for (int i = start; i <= end; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                isNum = false;
                List<Integer> left = dfs(input, start, i - 1);
                List<Integer> right = dfs(input, i + 1, end);
                for (int l : left) {
                    for (int r : right) {
                        res.add(calculate(c, l, r));
                    }
                }
            }
        }
        if (isNum) {
            res.add(Integer.valueOf(input.substring(start, end + 1)));
        }
        return res;
    }

    private int calculate(char c, int l, int r) {
        if (c == '+') {
            return l + r;
        } else if (c == '-') {
            return l - r;
        } else if (c == '*') {
            return l * r;
        } else {
            throw new IllegalArgumentException("Cannot resolve the operator '" + c + "'.");
        }
    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (input == null || input.length() == 0) {
            return res;
        }

        return dfs(input, 0, input.length() - 1);
    }

    public static void main(String[] args) {
        LC0241 so = new LC0241();
        String input = "2*3-4*5";
        List<Integer> res = so.diffWaysToCompute(input);
        System.out.println(res);
    }
}
