package LeetCode.LC801_900;

import java.util.*;

/**
 * Most Common Word
 *
 * Given a paragraph and a list of banned words, return the most frequent
 * word that is not in the list of banned words.  It is guaranteed there
 * is at least one word that isn't banned, and that the answer is unique.
 *
 * Words in the list of banned words are given in lowercase, and free of
 * punctuation.  Words in the paragraph are not case sensitive.  The answer
 * is in lowercase.
 *
 *
 *
 * Example:
 *
 * Input:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent
 * non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 *
 *
 * Note:
 *
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in
 * paragraph may have uppercase symbols, and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class LC0819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) {
            return paragraph;
        }

        Set<String> bannedSet = new HashSet<>();
        Collections.addAll(bannedSet, banned);

        Map<String, Integer> cnt = new HashMap<>();
        char[] chars = paragraph.toCharArray();
        StringBuilder sb = new StringBuilder();
        String res = null;
        int maxCnt = 0;

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ('a' + ch - 'A');
            }

            if (ch >= 'a' && ch <= 'z') {
                sb.append(ch);
                if (i != chars.length - 1) {
                    continue;
                }
            }

            if (sb.length() != 0){
                String str = sb.toString();
                sb.setLength(0);
                if (!bannedSet.contains(str)) {
                    cnt.put(str, cnt.getOrDefault(str, 0) + 1);
                    if (cnt.get(str) > maxCnt) {
                        maxCnt = cnt.get(str);
                        res = str;
                    }
                }
            }
        }

        return res;
    }
}
