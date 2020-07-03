package LeetCode.LC101_200;

import LeetCode.util.TreeNode;

/**
 * Flatten Binary Tree to Linked List
 *
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class LC0114 {
    public void flatten(TreeNode root) {
        if (root != null) {
            helper(root);
        }
    }

    private TreeNode helper(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        }
        if (root.left == null) {
            return helper(root.right);
        }
        if (root.right == null) {
            root.right = root.left;
            root.left = null;
            return helper(root.right);
        }

        TreeNode leftTail = helper(root.left);
        TreeNode rightTail = helper(root.right);

        leftTail.right = root.right;
        leftTail.left = null;

        root.right = root.left;
        root.left = null;

        return rightTail;
    }

    public void flattenIter(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
                continue;
            }

            TreeNode leftTail = cur.left;
            while (leftTail.right != null) {
                leftTail = leftTail.right;
            }

            leftTail.right = cur.right;
            cur.right = cur.left;
            cur.left = null;
            cur = cur.right;
        }
    }
}
