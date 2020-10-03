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
    public boolean canWin(String board) {
        if (board == null || board.length() == 0) {
            throw new IllegalArgumentException();
        }
        if (board.length() < 2) {
            return false;
        }

        HashMap<String, Boolean> mem = new HashMap<>();
        return dfs(board.toCharArray(), mem);
    }

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

                if (!ret) {
                    mem.put(str, true);
                    return true;
                }
            }
        }

        mem.put(str, false);
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


    /**
     * If board is no longer than 32 characters.
     */
    public boolean canWinBool(String board) {
        // cc
        Map<Integer, Boolean> mem = new HashMap<>();
        int bitBoard = 0;
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == '+') {
                bitBoard |= (1 << i);
            }
        }
        return dfs(bitBoard, mem);
    }

    private boolean dfs(int bitBoard, Map<Integer, Boolean> mem) {
        Boolean val = mem.get(bitBoard);
        if (val != null) {
            return val;
        }

        for (int i = 0; i < 31; i++) {
            int mask1 = 1 << i;
            int mask2 = 1 << i + 1;
            if ((bitBoard & mask1) != 0 && (bitBoard & mask2) != 0) {
                bitBoard ^= mask1;
                bitBoard ^= mask2;

                boolean ret = dfs(bitBoard, mem);

                bitBoard ^= mask1;
                bitBoard ^= mask2;
                if (!ret) {
                    mem.put(bitBoard, true);
                    return true;
                }
            }
        }

        mem.put(bitBoard, false);
        return false;
    }
}
