package LeetCode.LC601_700;
import java.util.*;

/**
 * Design Search Autocomplete System
 *
 * Design a search autocomplete system for a search engine.
 * Users may input a sentence (at least one word and end with a special character '#').
 * For each character they type except '#',
 * you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 * Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one).
 * If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Your job is to implement the following functions:
 *
 * The constructor function:
 *
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data.
 * Sentences is a string array consists of previously typed sentences.
 * Times is the corresponding times a sentence has been typed. Your system should record these historical data.
 *
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 *
 * List<String> input(char c): The input c is the next character typed by the user.
 * The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
 * Also, the previously typed sentence should be recorded in your system.
 * The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 *
 *
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 *
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree.
 * Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman".
 * Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 *
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 *
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 *
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved as a historical sentence in system.
 * And the following input will be counted as a new search.
 *
 *
 * Note:
 *
 * The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
 * The number of complete sentences that to be searched won't exceed 100.
 * The length of each sentence including those in the historical data won't exceed 100.
 * Please use double-quote instead of single-quote when you write test cases even for a character input.
 * Please remember to RESET your class variables declared in class AutocompleteSystem,
 * as static/class variables are persisted across multiple test cases. Please see here for more details.
 *
 *
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
class TrieNode {
    public char val;
    public TrieNode[] children;
    public boolean isWord;
    public HashMap<String, Integer> frequency;

    public TrieNode(char c) {
        val = c;
        children = new TrieNode[27];
        isWord = false;
        frequency = new HashMap<>();
    }
}

class Pair {
    public String key;
    public int val;
    public Pair(String key, int val) {
        this.key = key;
        this.val = val;
    }
}

public class LC0642 {
    private TrieNode root;
    private TrieNode cur;
    private StringBuilder sb;

    public LC0642(String[] sentences, int[] times) {
        //cc

        root = new TrieNode('\0');
        cur = root;
        sb = new StringBuilder();

        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    private void insert(String sentence, int time) {
        cur = root;

        for (char c : sentence.toCharArray()) {
            int idx = c >= 'a' && c <= 'z' ? c - 'a' : 26;

            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode(c);
            }

            cur = cur.children[idx];
            int cnt = cur.frequency.getOrDefault(sentence, 0);
            cur.frequency.put(sentence, cnt + time);
        }

        cur = root;
    }

    public List<String> input(char c) {
        if (c == '#') {
            insert(sb.toString(), 1);
            sb.setLength(0);
            cur = root;
            return new ArrayList<>();
        } else {
            sb.append(c);

            int idx = c >= 'a' && c <= 'z' ? c - 'a' : 26;

            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode(c);
            }
            cur = cur.children[idx];

            return getTop3(cur);
        }
    }

    private List<String> getTop3(TrieNode cur) {
        List<Pair> pairs = new ArrayList<>();
        for (String str : cur.frequency.keySet()) {
            pairs.add(new Pair(str, cur.frequency.get(str)));
        }

        pairs.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.val != o2.val) {
                    return o2.val - o1.val;
                } else {
                    return o1.key.compareTo(o2.key);
                }
            }
        });

        List<String> ret = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i >= pairs.size()) {
                break;
            }

            ret.add(pairs.get(i).key);
        }

        return ret;
    }

    public static void main(String[] args) {
        String[] sentences = new String[] {"i love you", "island","ironman", "i love leetcode"};
        int[] times = new int[] {5,3,2,2};

        LC0642 so = new LC0642(sentences, times);

        System.out.println(so.input('i'));
        System.out.println(so.input(' '));
        System.out.println(so.input('a'));
        System.out.println(so.input('#'));

        System.out.println(so.input('i'));
        System.out.println(so.input(' '));
        System.out.println(so.input('a'));
        System.out.println(so.input('#'));

        System.out.println(so.input('i'));
        System.out.println(so.input(' '));
        System.out.println(so.input('a'));
        System.out.println(so.input('#'));
    }
}


