package LeetCode.LC501_600;

import LeetCode.TreeNode;

/**
 * Diameter of Binary Tree
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class LC0543 {
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        height(root);
        return max;
    }

    private int max;

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        LC0543 so = new LC0543();
        Integer[] array = {1, null, 2, null, 3};
        TreeNode root = new TreeNode(array);
        int res = so.diameterOfBinaryTree(root);
        System.out.println(res);
    }
}
