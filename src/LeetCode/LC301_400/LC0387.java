package LeetCode.LC301_400;

import java.util.*;

/**
 * First Unique Character in a String
 *
 * Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode"
 * return 2.
 *
 *
 * Note: You may assume the string contains only lowercase English letters.
 */
public class LC0387 {
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    public int onePass(String s) {
        Queue<Integer> que = new LinkedList<>();
        // null: no appearance, false: appear once, true: more than once
        Boolean[] visited = new Boolean[26];

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (visited[idx] == null) {
                que.offer(i);
                visited[idx] = false;
            } else {
                visited[idx] = true;
                while (!que.isEmpty() && visited[s.charAt(que.peek()) - 'a']) {
                    que.poll();
                }
            }
        }

        return que.isEmpty() ? -1 : que.peek();
    }


    public static void main(String[] args) {
        LC0387 so = new LC0387();
        String s = "leetcode";
        int res = so.onePass(s);
        System.out.println(res);
    }
}
