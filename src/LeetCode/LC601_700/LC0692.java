package LeetCode.LC601_700;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Top K Frequent Words
 *
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 */
public class LC0692 {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) {
            return null;
        }

        Map<String, Integer> cnt = new HashMap<>();
        for (String str : words) {
            int cur = cnt.getOrDefault(str, 0);
            cnt.put(str, cur + 1);
        }

        List<String> list = new ArrayList<>(cnt.keySet());
        quickSort(list, 0, list.size() - 1, cnt, k);
        return list.subList(0, k);
    }

    private void quickSort(List<String> list, int start, int end,
                             Map<String, Integer> map, int k) {
        if (start >= k || end <= start) {
            return;
        }

        String pivot = list.get(end);
        int left = start;
        int right = end - 1;
        while (left <= right) {
            if (compare(list.get(left), pivot, map) > 0 && compare(list.get(right), pivot, map) <= 0) {
                swap(list, left++, right--);
                continue;
            }
            if (compare(list.get(left), pivot, map) <= 0) {
                left++;
            }
            if (compare(list.get(right), pivot, map) > 0) {
                right--;
            }
        }

        swap(list, left, end);
        quickSort(list, start, left - 1, map, k);
        quickSort(list, left + 1, end, map, k);
    }

    private int compare(String o1, String o2, Map<String, Integer> map) {
        int cnt1 = map.get(o1);
        int cnt2 = map.get(o2);
        if (cnt1 != cnt2) { // difference of 2 non-negative number wont overflow
            return cnt2 - cnt1;
        }

        for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
            char ch1 = o1.charAt(i);
            char ch2 = o2.charAt(i);
            if (ch1 != ch2) {
                return ch1 - ch2;
            }
        }
        return o1.length() - o2.length();
    }

    private void swap(List<String> list, int i, int j) {
        String temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void main(String[] args) {
        LC0692 so = new LC0692();
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        List<String> res = so.topKFrequent(words, k);
        System.out.println(res);
    }
}
