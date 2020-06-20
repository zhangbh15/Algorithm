package LeetCode.LC101_200;
import java.util.*;

/**
 * Word Ladder II
 *
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 *
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class LC0126_WordLadder {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList == null || wordList.size() == 0
                || beginWord == null || beginWord.length() == 0
                || endWord == null || endWord.length() == 0) {
            return res;
        }

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }
        Queue<String> que = new LinkedList<>();
        dict.remove(beginWord);
        que.offer(beginWord);
        Map<String, List<String>> resMap = new HashMap<>();

        boolean reachEnd = false;
        while (!que.isEmpty()) {
            int size = que.size();
            Set<String> levelVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String cur = que.poll();
                List<String> nexts = transform(cur, dict);
                for (String next : nexts) {
                    if (next.equals(endWord)) {
                        reachEnd = true;
                    }
                    if (levelVisited.add(next)) {
                        que.offer(next);
                        List<String> nextCurs = new LinkedList<>();
                        nextCurs.add(cur);
                        resMap.put(next, nextCurs);
                    } else {
                        List<String> nextCurs = resMap.get(next);
                        nextCurs.add(cur);
                        resMap.put(next, nextCurs);
                    }
                }
            }
            dict.removeAll(levelVisited);
            if (reachEnd) {
                List<String> path = new LinkedList<>();
                path.add(endWord);
                recoverPaths(resMap, endWord, beginWord, path, res);
                break;
            }
        }
        return res;
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

    private void recoverPaths(Map<String, List<String>> resMap,
                              String str, String target, List<String> path, List<List<String>> res) {
        if (str.equals(target)) {
            res.add(List.copyOf(path));
            return;
        }

        for (String next : resMap.get(str)) {
            path.add(0, next);
            recoverPaths(resMap, next, target, path, res);
            path.remove(0);
        }
    }

    public static void main(String[] args) {
        LC0126_WordLadder so = new LC0126_WordLadder();
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
        List<List<String>> res = so.findLadders(beginWord, endWord, dict);
        System.out.println(res);
    }
}
