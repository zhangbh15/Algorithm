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
//    public int woodCut(int[] woods, int k) {
//        if (woods == null || woods.length == 0 || k <= 0) return 0;
//
//        int start = 1;
//        int end = avg(woods, k); // min(avg(woods), max(woods));
//        while (start <= end) {
//            int mid = start + (end - start) / 2;
//            if (canCut(woods, k, mid)) {
//                start = mid + 1;
//            } else {
//                end = mid - 1;
//            }
//        }
//
//        return end;
//    }
}
