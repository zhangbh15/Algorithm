package LeetCode.LC201_300;

import java.util.Stack;

/**
 * Basic Calculator
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class LC0224 {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> optrStack = new Stack<>();

        numStack.push(0);
        optrStack.push('+');

        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i++);

            if (ch == ' ') {
                continue;
            } else if (ch == '(') {
                numStack.push(0);
                optrStack.push('+');
            } else if (ch == '+' || ch == '-') {
                optrStack.push(ch);
            } else if (ch == ')') {
                int num2 = numStack.pop();
                int num1 = numStack.pop();
                char optr = optrStack.pop();
                numStack.push(compute(num1, optr, num2));
            } else if (ch >= '0' && ch <= '9') {
                int num = ch - '0';
                while (i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == ' ')) {
                    if (s.charAt(i) != ' ') {
                        num = num * 10 + (s.charAt(i) - '0');
                    }
                    i++;
                }

                numStack.push(compute(numStack.pop(), optrStack.pop(), num));
            } else {
                throw new IllegalArgumentException("Unresolvable character: " + ch);
            }
        }

        return numStack.pop();
    }

    private int compute(int num1, char optr, int num2) {
        if (optr == '+') {
            return num1 + num2;
        }
        if (optr == '-') {
            return num1 - num2;
        }
        throw new IllegalArgumentException("Unsupported operator: " + optr);
    }

    public static void main(String[] args) {
        LC0224 so = new LC0224();
        String s = "1 + 2 2-3+(((4+5-6)-5+(1+1)-(1+2+3))+6)";
        int res = so.calculateBetter(s);
        System.out.println(res);
    }

    public int calculateBetter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> numStack = new Stack<>();
        Stack<Integer> signStack = new Stack<>();

        numStack.push(0);
        signStack.push(1);

        int i = 0;
        while (i <= s.length()) {
            char ch = i == s.length() ? ')' : s.charAt(i);
            i++;

            if (ch == ' ') {
                continue;
            } else if (ch == '(') {
                numStack.push(0);
                signStack.push(1);
            } else if (ch >= '0' && ch <= '9') {
                int num = ch - '0';
                while(i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == ' ')) {
                    if (s.charAt(i) != ' ') {
                        num = num * 10 + (s.charAt(i) - '0');
                    }
                    i++;
                }
                numStack.push(num);
            } else if (ch == '+' || ch == '-' || ch == ')') {
                int num2 = numStack.pop();
                int sign = signStack.pop();
                int num1 = numStack.pop();
                numStack.push(num1 + sign * num2);

                if (ch == '+') {
                    signStack.push(1);
                }
                if (ch == '-') {
                    signStack.push(-1);
                }
            }
        }

        return numStack.pop();
    }
}
