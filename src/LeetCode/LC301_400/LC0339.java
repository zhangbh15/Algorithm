package LeetCode.LC301_400;

import java.util.*;


/**
 * Nested List Weight Sum
 *
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1.
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */
public class LC0339 {
    public int depthSum(List<NestedInteger> nestedList) {
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

        int sum = 0;
        int level = 1;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                NestedInteger cur = que.poll();
                if (cur.isInteger()) {
                    sum += level * cur.getInteger();
                } else {
                    for (NestedInteger next : cur.getList()) {
                        que.offer(next);
                    }
                }
            }

            level++;
        }

        return sum;
    }


    /**
     * Follow up:
     * The weight of each level is reversed.
     * For example:
     *          2           level 1, weight 2
     *        /   \
     *   [1, 1]  [1, 1]     level 2, weight 1
     */
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

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
