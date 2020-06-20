package LeetCode.LC701_800;

import java.util.*;

/**
 *  Basic Calculator III
 *
 *  Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * The expression string contains only non-negative integers, +, -, *, / operators ,
 * open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Some examples:
 *
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 *
 *
 * Note: Do not use the eval built-in library function.
 */
public class LC0772 {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> optrStack = new Stack<>();
        Map<Character, Integer> optrMap = new HashMap<>();

        optrMap.put('+', 1);
        optrMap.put('-', 1);
        optrMap.put('*', 2);
        optrMap.put('/', 2);
        optrMap.put('(', 3);
        optrMap.put(')', 0);

        int i = 0;
        int len = s.length();
        handleOptr(numStack, optrStack, optrMap, '(', i, s);

        while (i < len) {
            char ch = s.charAt(i++);

            if (ch == ' ') {
                continue;
            } else if (optrMap.containsKey(ch)) {
                handleOptr(numStack, optrStack, optrMap, ch, i, s);
            } else if (ch >= '0' && ch <= '9') {
                int num = ch - '0';
                while (i < len && (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == ' ')) {
                    if (s.charAt(i) != ' ') {
                        num = num * 10 + (s.charAt(i) - '0');
                    }
                    i++;
                }
                numStack.push(num);
            } else {
                throw new IllegalArgumentException("Unresolvable character: " + ch);
            }
        }

        handleOptr(numStack, optrStack, optrMap, ')', i, s);
        return numStack.peek();
    }

    private void handleOptr(Stack<Integer> numStack, Stack<Character> optrStack,
                            Map<Character, Integer> optrMap, char ch, int i, String s) {
        if (ch == '(') {
            optrStack.push(ch);
            while (i < s.length()) { // handle hiding minus like (-5 + 2)
                if (s.charAt(i) == '-') {
                    numStack.push(0);
                } else if (s.charAt(i) != ' '){
                    break;
                }
                i++;
            }
        } else { // +-*/)
            while (!optrStack.isEmpty()) {
                char optr = optrStack.peek();
                if (optr == '(' || optrMap.get(optr) < optrMap.get(ch)) {
                    break;
                }

                int num2 = numStack.pop();
                int num1 = numStack.pop();
                numStack.push(compute(num1, optrStack.pop(), num2));
            }

            if (ch == ')') {
                optrStack.pop();
            } else {
                optrStack.push(ch);
            }
        }
    }

    private int compute(int num1, char optr, int num2) {
        if (optr == '+') {
            return num1 + num2;
        }
        if (optr == '-') {
            return num1 - num2;
        }
        if (optr == '*') {
            return num1 * num2;
        }
        if (optr == '/') {
            return num1 / num2;
        }
        throw new IllegalArgumentException("Unsupported operation: " + optr);
    }

    public static void main(String[] args) {
        LC0772 so = new LC0772();
        String s = "1 - (-7)";
        int res = so.calculate(s);
        System.out.println(res);
    }
}
