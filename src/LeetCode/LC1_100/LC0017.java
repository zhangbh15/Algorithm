package LeetCode.LC1_100;

import java.util.*;

/**
 * Letter Combinations of a Phone Number
 *
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 *
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LC0017 {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        Map<Character, char[]> map = buildMap();
        dfs(0, new StringBuilder(), digits, map, res);
        return res;
    }

    private void dfs(int idx, StringBuilder sb, String digits, Map<Character, char[]> map, List<String> res) {
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }

        char[] chars = map.get(digits.charAt(idx));
        if (chars == null) {
            throw new IllegalArgumentException("Unresolvable digit." +  digits.charAt(idx));
        }

        int setBackLen = sb.length();
        for (char ch : chars) {
            sb.append(ch);
            dfs(idx + 1, sb, digits, map, res);
            sb.setLength(setBackLen);
        }
    }

    private Map<Character, char[]> buildMap() {
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x', 'y', 'z'});
        return map;
    }
}
