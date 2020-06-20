package LeetCode.LC201_300;

/**
 * Basic Calculator II
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class LC0227 {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int sum = 0;
        int lastAdd = 0;
        int i = 0;
        char optr = '+';

        while (i < s.length()) {
            char ch = s.charAt(i++);

            if (ch == ' ') {
                continue;
            } else if (ch >= '0' && ch <= '9') {
                int num = ch - '0';
                while (i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == ' ')) {
                    if (s.charAt(i) != ' ') {
                        num = num * 10 + (s.charAt(i) - '0');
                    }
                    i++;
                }

                if (optr == '+' || optr == '-') {
                    sum = calculate(sum, optr, num);
                    lastAdd = optr == '+' ? num : -num;
                } else {
                    int newLastAdd = calculate(lastAdd, optr, num);
                    sum = sum - lastAdd + newLastAdd;
                    lastAdd = newLastAdd;
                }
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                optr = ch;
            } else {
                throw new IllegalArgumentException("Unresolvable character: " + ch);
            }
        }

        return sum;
    }

    private int calculate(int first, char optr, int last) {
        if (optr == '+') {
            return first + last;
        }
        if (optr == '-') {
            return first - last;
        }
        if (optr == '*') {
            return first * last;
        }
        if (optr == '/') {
            return first / last;
        }
        throw new IllegalArgumentException("unresolvable operator: " + optr);
    }

    public int calculatePlusMinus(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int sum = 0;
        char optr = '+';
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i++);

            if (ch == ' ') {
                continue;
            } else if (ch == '+' || ch == '-') {
                optr = ch;
            } else if (ch >= '0' && ch <= '9') {
                int num = ch - '0';
                while (i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == ' ')) {
                    if (s.charAt(i) != ' '){
                        num = num * 10 + (s.charAt(i) - '0');
                    }
                    i++;
                }

                if (optr == '+') {
                    sum += num;
                } else { // optr == '-'
                    sum -= num;
                }
            } else {
                throw new IllegalArgumentException("Unresolvable character: " + ch);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        LC0227 so = new LC0227();
        String s = " 12 3*2  + 1-2+3-4*5/1";
        int res = so.calculate(s);
        System.out.println(res);

        String plusMinus = "12 3+  10 - 321 + 1+1-2";
        int respm = so.calculatePlusMinus(plusMinus);
        System.out.println(respm);
    }
}
