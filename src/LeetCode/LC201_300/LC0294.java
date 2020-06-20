package LeetCode.LC201_300;
import java.util.*;

/**
 * Flip Game II
 *
 * You are playing the following Flip Game with your friend:
 * Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 *
 * Write a function to determine if the starting player can guarantee a win.
 *
 * Example:
 *
 * Input: s = "++++"
 * Output: true
 * Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up:
 * Derive your algorithm's runtime complexity.
 */
public class LC0294 {
    /* On current board, if the offensive can guarantee win. */
    private boolean dfs(char[] board, HashMap<String, Boolean> mem) { // if board shorter than 32, can use a Integer
        String str = String.valueOf(board);
        Boolean val = mem.get(str);
        if (val != null) {
            return val;
        }

        if (canNotFlip(board)) {
            return false;
        }

        for (int i = 0; i < board.length - 1; i++) {
            if (board[i] == '+' && board[i+ 1] == '+') {
                board[i] = '-';
                board[i + 1] = '-';
                boolean ret = dfs(board, mem);
                board[i] = '+';
                board[i + 1] = '+';
                mem.put(str, ret);
                if (!ret) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canNotFlip(char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == '+' && array[i + 1] == '+') {
                return false;
            }
        }
        return true;
    }

    public boolean canIWin(String board) {
        // CC
        HashMap<String, Boolean> mem = new HashMap<>();
        return dfs(board.toCharArray(), mem);
    }
}
