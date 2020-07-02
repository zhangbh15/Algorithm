package LeetCode.LC101_200;

import LeetCode.Util.TreeNode;

/**
 * Binary Tree Maximum Path Sum
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from
 * some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class LC0124 {
    public int maxPathSum(TreeNode root) {
        anyToRoot(root);
        return max;
    }

    private int max = 0;

    private int anyToRoot(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, anyToRoot(root.left));
        int right = Math.max(0, anyToRoot(root.right));

        int curMax = left + right + root.val;
        max = Math.max(max, curMax);

        return root.val + Math.max(left, right);
    }
}
