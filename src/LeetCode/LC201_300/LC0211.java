package LeetCode.LC201_300;

/**
 * Add and Search Word - Data structure design
 *
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 **
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

// See L0208 for definition of TrieNode

public class LC0211 {

    public TrieNode root;

    /** Initialize your data structure here. */
    public LC0211() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null) {
            return;
        }
        if (word.length() == 0) {
            root.isWord = true;
        }
        for (char c : word.toCharArray()) {
            if (c < 'a' || c > 'z') {
                return;
            }
        }

        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode(c);
            }
            cur = cur.children[idx];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0) {
            return root.isWord;
        }

        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, TrieNode cur) {
        if (i == word.length()) {
            return cur.isWord;
        }

        char c = word.charAt(i);
        if (c == '.') {
            for (TrieNode tn : cur.children) {
                if (tn != null && dfs(word, i + 1, tn)) {
                    return true;
                }
            }
            return false;
        } else if (c >= 'a' && c <= 'z') {
            return cur.children[c - 'a'] != null && dfs(word, i + 1, cur.children[c - 'a']);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        LC0211 so = new LC0211();
        so.addWord("bad");
        so.addWord("bad");
        so.addWord("mad");
        System.out.println(so.search("pad"));
        System.out.println(so.search("bad"));
        System.out.println(so.search(".ad"));
        System.out.println(so.search("b.."));
    }
}


