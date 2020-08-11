package LeetCode.LC401_500;

import java.util.*;

/**
 * Sort Characters by Frequency
 *
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 *
 * Input:
 * "tree"
 *
 * Output:
 * "eert"
 *
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 *
 * Input:
 * "cccaaa"
 *
 * Output:
 * "cccaaa"
 *
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 *
 * Input:
 * "Aabb"
 *
 * Output:
 * "bbAa"
 *
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 */
public class LC0451 {
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : chars) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        // can use bucket sort --> O(n) time
        List<Character> charList = new ArrayList<>(freq.keySet());
        charList.sort((o1, o2) -> freq.get(o2) - freq.get(o1));

        StringBuilder sb = new StringBuilder();
        for (char ch : charList) {
            sb.append(String.valueOf(ch).repeat(Math.max(0, freq.get(ch))));
        }

        return sb.toString();
    }
}
