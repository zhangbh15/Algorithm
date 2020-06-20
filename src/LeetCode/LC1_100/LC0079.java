package LeetCode.LC1_100;

/**
 * Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class LC0079 {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private boolean dfs(char[][] matrix, int i, int j, String target, int index, boolean[][] visited) {
        int targetLength = target.length();
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (index == targetLength) {
            return true;
        }
        if (i < 0 || i >= rows || j < 0 || j >= cols
                || matrix[i][j] != target.charAt(index) || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        boolean res = false;
        for (int[] d : DIRECTIONS) {
            int ii = i + d[0];
            int jj = j + d[1];
            res = dfs(matrix, ii, jj, target, index + 1, visited);
            if (res) {
                break;
            }
        }
        visited[i][j] = false;
        return res;
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0
                || word == null || word.length() == 0) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LC0079 so = new LC0079();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";
        boolean res = so.exist(board, word);
        System.out.println(res);
    }
}
