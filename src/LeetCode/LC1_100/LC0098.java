package LeetCode.LC1_100;

import LeetCode.util.TreeNode;

/**
 * Validate Binary Search Tree
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class LC0098 {
    public boolean isValidBST(TreeNode root) {
        return verify(root, null, null);
    }

    private boolean verify(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) {
            return true;
        }

        int val = root.val;
        if ((lowerBound != null && val <= lowerBound) || (upperBound != null && val >= upperBound)) {
            return false;
        }

        boolean left = verify(root.left, lowerBound, val);
        boolean right = verify(root.right, val, upperBound);
        return left && right;
    }
}
