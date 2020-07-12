package LeetCode.LC1_100;


import java.util.*;

/**
 * Group Anagram
 *
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter
 */
public class LC0049 {
    public List<List<String>> groupAnagramsSort(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }

        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<>());
            }
            groups.get(key).add(str);
        }

        for (String str : groups.keySet()) {
            res.add(groups.get(str));
        }
        return res;
    }

    public List<List<String>> groupAnagramsCount(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }

        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            int[] cnt = new int[26];
            for (char ch : str.toCharArray()) {
                cnt[ch - 'a']++;
            }
            String key = Arrays.toString(cnt);
            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<>());
            }
            groups.get(key).add(str);
        }

        for (String str : groups.keySet()) {
            res.add(groups.get(str));
        }
        return res;
    }

    public static void main(String[] args) {
        LC0049 so = new LC0049();
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> res = so.groupAnagramsCount(strs);
        System.out.println(res);
    }
}
