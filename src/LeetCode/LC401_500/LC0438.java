package LeetCode.LC401_500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find All Anagrams in an String
 *
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class LC0438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0
                || p.length() > s.length()) {
            return res;
        }

        int[] target = new int[26];
        for (char ch : p.toCharArray()) {
            target[ch - 'a']++;
        }

        int[] source = new int[26];
        int windowLen = p.length();
        for (int i = 0; i < s.length(); i++) {
            source[s.charAt(i) - 'a']++;
            if (i >= windowLen) {
                source[s.charAt(i - windowLen) - 'a']--;
            }

            if (i < windowLen - 1) {
                continue;
            }
            if (Arrays.equals(source, target)) {
                res.add(i - windowLen + 1);
            }
        }

        return res;
    }
}
