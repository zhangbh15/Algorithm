package LeetCode.LC1_100;

/**
 * Implement strStr()
 *
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a
 * great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is
 * an empty string. This is consistent to C's strstr() and Java's indexOf().
 *
 *
 *
 * Constraints:
 *
 * haystack and needle consist only of lowercase English characters.
 */
public class LC0028 {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0 || haystack.length() < needle.length()) {
            return -1;
        }

        int targetLen = needle.length();
        long targetHash = 0;
        long windowHash = 0;

        for (int i = 0; i < targetLen; i++) {
            targetHash = (targetHash * 26 + (needle.charAt(i) - 'a')) % Integer.MAX_VALUE;
            windowHash = (windowHash * 26 + (haystack.charAt(i) - 'a')) % Integer.MAX_VALUE;
        }
        if (targetHash == windowHash) {
            return 0;
        }

        long startWeight = 1;
        for (int i = 0; i < targetLen - 1; i++) {
            startWeight = startWeight * 26 % Integer.MAX_VALUE;
        }

        for (int i = 0; i < haystack.length() - targetLen; i++) {
            windowHash -= (haystack.charAt(i) - 'a') * startWeight;
            windowHash *= 26;
            windowHash += (haystack.charAt(i + targetLen) - 'a');
            windowHash %= Integer.MAX_VALUE;
            if (windowHash == targetHash) {
                return i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LC0028 so = new LC0028();
        String str = "hello";
        String target = "ll";
        int res = so.strStr(str, target);
        System.out.println(res);
    }
}
