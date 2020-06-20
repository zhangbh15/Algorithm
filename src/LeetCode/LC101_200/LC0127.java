package LeetCode.LC101_200;
import java.util.*;

/**
 * Word Ladder
 *
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class LC0127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0
                || beginWord == null || beginWord.length() == 0
                || endWord == null || endWord.length() == 0) {
            return 0;
        }

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        Queue<String> que = new LinkedList<>();
        dict.remove(beginWord);
        que.offer(beginWord);
        int minLen = 1;

        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String cur = que.poll();
                List<String> nextSet = transform(cur, dict);
                for (String next : nextSet) {
                    if (next.equals(endWord)) {
                        return minLen + 1;
                    }
                    que.offer(next);
                    dict.remove(next);
                }
            }
            minLen++;
        }
        return 0;
    }

    private List<String> transform(String cur, Set<String> dict) {
        char[] chars = cur.toCharArray();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char newChar = 'a'; newChar <= 'z'; newChar++) {
                chars[i] = newChar;
                String str = String.valueOf(chars);
                if (newChar != temp && dict.contains(str)) {
                    res.add(str);
                }
            }
            chars[i] = temp;
        }
        return res;
    }

    public int combined(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0
                || beginWord == null || beginWord.length() == 0
                || endWord == null || endWord.length() == 0) {
            return 0;
        }

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        Queue<String> que = new LinkedList<>();
        dict.remove(beginWord);
        que.offer(beginWord);
        int minLen = 1;

        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String cur = que.poll();
                char[] chars = cur.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char temp = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String str = String.valueOf(chars);
                        if (c != temp && dict.contains(str)) {
                            if (str.equals(endWord)) {
                                return minLen + 1;
                            }
                            que.offer(str);
                            dict.remove(str);
                        }
                    }
                    chars[j] = temp;
                }
            }
            minLen++;
        }
        return 0;
    }

    public int twoDirections(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        int minLen = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> tempSet = beginSet;
                beginSet = endSet;
                endSet = tempSet;
            }

            Set<String> nextLevel = new HashSet<>();
            for (String cur : beginSet) {
                char[] chars = cur.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char temp = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String str = String.valueOf(chars);
                        if (endSet.contains(str)) {
                            return minLen + 1;
                        }

                        if (c != temp && dict.contains(str)) {
                            nextLevel.add(str);
                            dict.remove(str);
                        }
                    }
                    chars[i] = temp;
                }
            }
            beginSet = nextLevel;
            minLen++;
        }
        return 0;
    }

    public static void main(String[] args) {
        LC0127 so = new LC0127();
        List<String> dict = new ArrayList<>(){{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};
        String beginWord = "hit";
        String endWord = "cog";
        int res = so.twoDirections(beginWord, endWord, dict);
        System.out.println(res);
    }
}
