package LeetCode.LC201_300;

/**
 * Valid Anagram
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class LC0242 {
    public boolean isAnagram(String s, String t) {
        if (s == null) {
            return t == null;
        }
        if (t == null || s.length() != t.length()) {
            return false;
        }

        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
            cnt[t.charAt(i) - 'a']--;
        }

        for (int n : cnt) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}
