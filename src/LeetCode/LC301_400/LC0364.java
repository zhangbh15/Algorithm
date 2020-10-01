package LeetCode.LC301_400;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Nested List Weight Sum II
 *
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Different from the previous question where weight is increasing from root to leaf, now the weight is
 * defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have
 * the largest weight.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: 8
 * Explanation: Four 1's at depth 1, one 2 at depth 2.
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: 17
 * Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.
 */
public class LC0364 {
    public int followUp(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            throw new IllegalArgumentException();
        }
        if (nestedList.size() == 0) {
            return 0;
        }

        Queue<NestedInteger> que = new LinkedList<>();
        for (NestedInteger nest : nestedList) {
            que.offer(nest);
        }

        int unweightedSum = 0;
        int res = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger cur = que.poll();
                if (cur.isInteger()) {
                    levelSum += cur.getInteger();
                } else {
                    for (NestedInteger next : cur.getList()) {
                        que.offer(next);
                    }
                }
            }

            unweightedSum += levelSum;
            res += unweightedSum;
        }

        return res;
    }
}
