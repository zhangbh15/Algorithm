package LeetCode.LC301_400;

import java.util.*;

/**
 *  Decode String
 *
 *  Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being
 * repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only
 * for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 * Example 4:
 *
 * Input: s = "abc3[cd]xyz"
 * Output: "abccdcdcdxyz"
 */
public class LC0394 {
    public String decodeString(String s) {
        // cc
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        strStack.push(new StringBuilder());

        int len = s.length();
        int val = 0;

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                val = val * 10 + (ch - '0');
            } else if (ch == '[') {
                numStack.push(val);
                val = 0;
                strStack.push(new StringBuilder());
            } else if (ch == ']') {
                int cnt = numStack.pop();
                StringBuilder str = strStack.pop();
                StringBuilder sb = new StringBuilder();
                while (cnt-- > 0) {
                    sb.append(str);
                }
                strStack.peek().append(sb);
            } else { // chars
                strStack.peek().append(ch);
            }
        }

        return strStack.pop().toString();
    }
}
