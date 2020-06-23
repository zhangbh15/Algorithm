package LeetCode.LC401_500;

import java.util.*;

/**
 * Concatenated Words
 *
 * Given a list of words (without duplicates), please write a program that
 * returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of
 * at least two shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 */
public class LC0472 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new LinkedList<>();
        if (words == null || words.length <= 1) {
            return res;
        }

        Set<String> dict = new HashSet<>();
        Collections.addAll(dict, words);

        for (String str : dict) {
            Integer[] mem = new Integer[str.length()];
            if (numOfWords(str, 0, dict, mem) > 1) {
                res.add(str);
            }
        }

        return res;
    }

    private int numOfWords(String str, int idx, Set<String> dict, Integer[] mem) {
        int len = str.length();
        if (idx == len) {
            return 0;
        }

        if (mem[idx] != null) {
            return mem[idx];
        }

        for (int i = idx + 1; i <= len; i++) {
            if (dict.contains(str.substring(idx, i))) {
                int num = numOfWords(str, i, dict, mem);
                if (num != -1) {
                    mem[idx] = num + 1;
                    return mem[idx];
                }
            }
        }

        mem[idx] = -1;
        return -1;
    }

    public static void main(String[] args) {
        LC0472 so = new LC0472();
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        List<String> res = so.findAllConcatenatedWordsInADict(words);
        System.out.println(res);
    }
}
