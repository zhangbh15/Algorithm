package LeetCode.LC201_300;
import java.util.*;

/**
 * Alien Dictionary
 *
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * Output: "wertf"
 * Example 2:
 *
 * Input:
 * [
 *   "z",
 *   "x"
 * ]
 *
 * Output: "zx"
 * Example 3:
 *
 * Input:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 *
 * Output: ""
 *
 * Explanation: The order is invalid, so return "".
 * Note:
 *
 * You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
public class LC0269 {
    public String alienOrder(String[] words) {
        if (words == null || words.length < 1) {
            return "";
        }

        Map<Character, List<Character>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            boolean valid = false;
            for (int j = 0; j < words[i].length(); j++) {
                char ch1 = words[i].charAt(j);
                if (!map.containsKey(ch1)) {
                    map.put(ch1, new ArrayList<>());
                }

                if (i < words.length - 1 && !valid) {
                    if (j < words[i + 1].length()) {
                        char ch2 = words[i + 1].charAt(j);
                        if (ch1 != ch2) {
                            map.get(ch1).add(ch2);
                            valid = true;
                        }
                    } else {
                        return "";
                    }
                }
            }
        }

        Map<Character, Boolean> status = new HashMap<>(); //true for visited, false for visiting
        StringBuilder sb = new StringBuilder();

        for (char ch : map.keySet()) {
            if (!sort(ch, map, status, sb)) {
                return "";
            }
        }

        return sb.reverse().toString();
    }

    private boolean sort(char cur, Map<Character, List<Character>> map, Map<Character, Boolean> status, StringBuilder sb) {
        if (status.containsKey(cur)) {
            return status.get(cur);
        }

        if (map.containsKey(cur)) {
            status.put(cur, false);
            for (char next : map.get(cur)) {
                if (!sort(next, map, status, sb)) {
                    return false;
                }
            }
        }

        status.put(cur, true);
        sb.append(cur);
        return true;
    }

    public static void main(String[] args) {
        LC0269 so = new LC0269();
        String[] words = new String[] {"wrt", "wrf", "er", "ett", "rftt"};
        String[] words2 = new String[] {"z", "x"};
        String[] words3 = new String[] {"z", "x", "z"};
        String[] words4 = new String[] {"z", "z"};
        String[] words5 = new String[] {"abc", "ab"};
        String[] words6 = new String[] {"za", "zb", "ca", "cb"};
        String res = so.alienOrder(words6);
        System.out.println(res);
    }
}
