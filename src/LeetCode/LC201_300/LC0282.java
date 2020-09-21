package LeetCode.LC201_300;

import java.util.*;

/**
 * Expression Add Operators
 *
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -,
 * or * between the digits so they evaluate to the target value.
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 *
 *
 * Constraints:
 *
 * 0 <= num.length <= 10
 * num only contain digits.
 */
public class LC0282 {
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() > 10) {
            throw new IllegalArgumentException();
        }

        List<String> res = new ArrayList<>();
//        dfs(res, num, new StringBuilder(), 0, target);
        helper(res, num, new StringBuilder(), 0, 0L, 0L, target);
        return res;
    }

    private void helper(List<String> res, String num, StringBuilder sb, int idx, long sum, long lastAdded, int target) {
        if (idx == num.length()) {
            if (sum == target) {
                res.add(sb.toString());
            }
            return;
        }

        long val = 0;
        int setBack = sb.length();
        for (int i = idx; i < num.length(); i++) {
            val = val * 10 + num.charAt(i) - '0';

            if (sb.length() == 0) {
                sb.append(val);
                helper(res, num, sb, i + 1, sum + val, val, target);
                sb.setLength(setBack);
            } else {
                sb.append('+').append(val);
                helper(res, num, sb, i + 1, sum + val, val, target);
                sb.setLength(setBack);

                sb.append('-').append(val);
                helper(res, num, sb, i + 1, sum - val, -val, target);
                sb.setLength(setBack);

                sb.append('*').append(val);
                helper(res, num, sb, i + 1, sum - lastAdded + lastAdded * val, lastAdded * val, target);
                sb.setLength(setBack);
            }

            if (val == 0) {
                break;
            }
        }
    }


    private void dfs(List<String> res, String num, StringBuilder sb, int idx, int target) {
        if (idx == num.length()) {
            String str = sb.toString();
            int val = compute(str);
            if (val == target) {
                res.add(str);
            }
            return;
        }

        char ch = num.charAt(idx);

        if (sb.length() == 0 || sb.charAt(sb.length() - 1) != '0') {
            sb.append(ch);
            dfs(res, num, sb, idx + 1, target);
            sb.setLength(sb.length() - 1);
        }

        if (idx == 0) {
            return;
        }

        sb.append('+').append(ch);
        dfs(res, num, sb, idx + 1, target);
        sb.setLength(sb.length() - 2);

        sb.append('-').append(ch);
        dfs(res, num, sb, idx + 1, target);
        sb.setLength(sb.length() - 2);

        sb.append('*').append(ch);
        dfs(res, num, sb, idx + 1, target);
        sb.setLength((sb.length() - 2));
    }

    private int compute(String str) {
        int len = str.length();
        int sum = 0;
        int lastAdded = 0;
        char optr = '+';

        int i = 0;
        while (i < len) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                int val = 0;
                while (i < len && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    val = val * 10 + str.charAt(i++) - '0';
                }

                if (optr == '+') {
                    lastAdded = val;
                    sum += lastAdded;
                } else if (optr == '-') {
                    lastAdded = -val;
                    sum += lastAdded;
                } else { // '*'
                    sum -= lastAdded;
                    lastAdded *= val;
                    sum += lastAdded;
                }
            } else {
                optr = str.charAt(i++);
            }
        }

        return sum;
    }


    public static void main(String[] args) {
        LC0282 so = new LC0282();
        String num = "2147483648";
        int target = -2147483648;
        List<String> res = so.addOperators(num, target);
        System.out.println(res);
    }
}
