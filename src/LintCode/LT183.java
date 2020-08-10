package LintCode;

/**
 * Wood Cut
 *
 * Given n pieces of wood with length L[i] (integer array).
 * Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
 * What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.
 *
 * You couldn't cut wood into float length.
 *
 * If you couldn't get >= k pieces, return 0.
 *
 * Example
 * Example 1
 *
 * Input:
 * L = [232, 124, 456]
 * k = 7
 * Output: 114
 * Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if any piece is 115cm long.
 * Example 2
 *
 * Input:
 * L = [1, 2, 3]
 * k = 7
 * Output: 0
 * Explanation: It is obvious we can't make it.
 * Challenge
 * O(n log Len), where Len is the longest length of the wood.
 */
public class LT183 {
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) {
            return 0;
        }

        int sum = 0;
        int longestPiece = 0;
        for (int wood : L) {
            sum += wood;
            longestPiece = Math.max(longestPiece, wood);
        }

        int min = 1;
        int max = Math.min(sum / k, longestPiece);
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (maxPieces(L, mid) >= k) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return max;
    }

    private int maxPieces(int[] woods, int len) {
        int cnt = 0;
        for (int wood : woods) {
            cnt += wood / len;
        }
        return cnt;
    }
}
