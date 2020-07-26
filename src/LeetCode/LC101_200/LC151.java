package LeetCode.LC101_200;

/**
 * Reverse Words in a String
 *
 * Given an input string, reverse the string word by word.
 *
 *
 *
 * Example 1:
 *
 * Input: "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: "  hello world!  "
 * Output: "world! hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Note:
 *
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 */
public class LC151 {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int end = s.length();
        int start = end - 1;
        while (start >= 0) {
            while (start >= 0 && s.charAt(start) == ' ') {
                start--;
            }
            end = start + 1;

            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }
            sb.append(s, start + 1, end);
            sb.append(' ');
        }

        sb.setLength(sb.length() - 1);
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }


    // reverse String & reverse word
}
