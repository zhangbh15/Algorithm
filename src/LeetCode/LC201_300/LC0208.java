package LeetCode.LC201_300;

/**
 * Implement Trie (Prefix Tree)
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 *
 **
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
class TrieNode {
    public char val;
    public boolean isWord;
    public TrieNode[] children;

    public TrieNode() {
        val = '\0';
        isWord = false;
        children = new TrieNode[26];
    }
    public TrieNode(char c) {
        val = c;
        isWord = false;
        children = new TrieNode[26];
    }
}

public class LC0208 {

    private TrieNode root;
    private TrieNode cur;

    /** Initialize your data structure here. */
    public LC0208() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode(c);
            }
            cur = cur.children[idx];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return startsWith(word) && cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        LC0208 so = new LC0208();
        so.insert("apple");
        System.out.println(so.root.val);
        System.out.println(so.search("apple"));
        System.out.println(so.search("app"));
        System.out.println(so.startsWith("app"));
        so.insert("app");
        System.out.println(so.search("app"));
    }
}



