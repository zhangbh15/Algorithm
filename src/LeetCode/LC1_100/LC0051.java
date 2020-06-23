package LeetCode.LC1_100;

import java.util.*;

/**
 * N-Queens
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class LC0051 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new LinkedList<>();
        if (n < 0) {
            return res;
        }

        dfs(0, n, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int level, int n, List<String> board, List<List<String>> res) {
        if (level == n) {
            res.add(new ArrayList<>(board));
        }

        for (int i = 0; i < n; i++) {
            if (isValid(level, i, board)) {
                board.add(buildLine(i, n));
                dfs(level + 1, n, board, res);
                board.remove(board.size() - 1);
            }
        }
    }

    private boolean isValid(int level, int idx, List<String> board) {
        for (int i = 0; i < board.size(); i++) {
            String str = board.get(i);
            int len = str.length();

            if (str.charAt(idx) == 'Q') {
                return false;
            }

            int upperLeft = i - level + idx;
            if (upperLeft >= 0 && upperLeft < len && str.charAt(upperLeft) == 'Q') {
                return false;
            }

            int upperRight = level + idx - i;
            if (upperRight >= 0 && upperRight < len && str.charAt(upperRight) == 'Q') {
                return false;
            }
        }

        return true;
    }

    private String buildLine(int idx, int n) {
        return ".".repeat(Math.max(0, idx)) +
                'Q' +
                ".".repeat(Math.max(0, n - (idx + 1)));
    }

    // Using int[]
    public List<List<String>> solveNQueensArray(int n) {
        List<List<String>> res = new LinkedList<>();
        if (n < 0) {
            return res;
        }

        int[] board = new int[n];
        helper(0, board, res);
        return res;
    }

    private void helper(int level, int[] board, List<List<String>> res) {
        if (level == board.length) {
            res.add(buildBoard(board));
        }

        for (int i = 0; i < board.length; i++) {
            if (valid(level, i, board)) {
                board[level] = i;
                helper(level + 1, board, res);
            }
        }
    }

    private boolean valid(int level, int idx, int[] board) {
        for (int i = 0; i < level; i++) {
            int upperLeft = i - level + idx;
            int upperRight = level + idx - i;
            if (board[i] == idx || board[i] == upperLeft || board[i] == upperRight) {
                return false;
            }
        }

        return true;
    }

    private List<String> buildBoard(int[] board) {
        List<String> ret = new LinkedList<>();
        for (int idx : board) {
            ret.add(buildLine(idx, board.length));
        }
        return ret;
    }

    public static void main(String[] args) {
        LC0051 so = new LC0051();
        int n = 4;
        List<List<String>> res = so.solveNQueensArray(n);
        System.out.println(res);
    }
}
