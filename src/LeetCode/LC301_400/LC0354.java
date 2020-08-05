package LeetCode.LC301_400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Russian Doll Envelopes
 *
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one envelope
 * is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class LC0354 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 ||
                envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }

        // sort on o[0], if tie, sort on o[1] in a decreasing order.
        // Then the longest increasing subsequence of o[1] won't have
        // elements with the same width (o[0])
        Arrays.sort(envelopes, (o1, o2) ->
                o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

        return longestIncreasingSubsequence(envelopes);
    }

    private int longestIncreasingSubsequence(int[][] pairs) {
        List<Integer> buffer = new ArrayList<>();
        for (int[] pair : pairs) {
            search(buffer, pair[1]);
        }

        return buffer.size();
    }

    private void search(List<Integer> list, int target) {
        if (list.size() == 0) {
            list.add(target);
            return;
        }

        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int cur = list.get(mid);

            if (cur == target) {
                return;
            }

            if (cur < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (start == list.size()) {
            list.add(target);
        } else {
            list.set(start, target);
        }
    }
}
