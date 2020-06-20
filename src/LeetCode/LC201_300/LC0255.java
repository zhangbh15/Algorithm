package LeetCode.LC201_300;

import java.util.*;

/**
 * Verify Preorder Sequence in Binary Search Tree
 *
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 *
 * You may assume each number in the sequence is unique.
 *
 * Consider the following binary search tree:
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * Example 1:
 *
 * Input: [5,2,6,1,3]
 * Output: false
 * Example 2:
 *
 * Input: [5,2,1,3,6]
 * Output: true
 * Follow up:
 * Could you do it using only constant space complexity?
 */
public class LC0255 {
    public boolean verifyPreorder(int[] preorder) {
        // cc
        Stack<Integer> stack = new Stack<>();
        int lowerBound = Integer.MIN_VALUE;
        for (int val : preorder) {
            if (val < lowerBound) {
                return false;
            }
            while (!stack.isEmpty() && val > stack.peek()) {
                lowerBound = stack.pop();
            }

            stack.push(val);
        }

        return true;
    }
}
