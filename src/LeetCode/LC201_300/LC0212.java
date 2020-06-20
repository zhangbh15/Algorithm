package LeetCode.LC201_300;

import java.util.*;

/**
 * Word Search II
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
 * cells are those horizontally or vertically neighboring. The same letter cell may not be
 * used more than once in a word.
 *
 *
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 *
 * Note:
 *
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
public class LC0212 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new LinkedList<>();
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0
                || words == null || words.length == 0) {
            return res;
        }

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (String str : words) {
            char[] chars = str.toCharArray();
            boolean found = false;
            for (int i = 0; i < rows; i++) {
                if (found) {
                    break;
                }
                for (int j = 0; j < cols; j++) {
                    if (dfs(i, j, 0, visited, chars, board)) {
                        res.add(str);
                        found = true;
                        break;
                    }
                }
            }
        }

        return res;
    }

    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private boolean dfs(int i, int j, int idx, boolean[][] visited, char[] chars, char[][] board) {
        if (idx == chars.length) {
            return true;
        }

        int rows = board.length;
        int cols = board[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j] || chars[idx] != board[i][j]) {
            return false;
        }

        visited[i][j] = true;
        boolean found = false;
        for (int[] dir : DIRECTIONS) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            found = dfs(ii, jj, idx + 1, visited, chars, board);
            if (found) {
                break;
            }
        }

        visited[i][j] = false;
        return found;
    }
}
