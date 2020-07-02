package LeetCode.LC301_400;
import LeetCode.Util.TreeNode;

/**
 * Largest BST Subtree
 *
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.
 *
 * Note:
 * A subtree must include all of its descendants.
 *
 * Example:
 *
 * Input: [10,5,15,1,8,null,7]
 *
 *    10
 *    / \
 *   5  15
 *  / \   \
 * 1   8   7
 *
 * Output: 3
 *
 * Explanation: The Largest BST Subtree in this case is the highlighted one.
 *              The return value is the subtree's size, which is 3.
 * Follow up:
 * Can you figure out ways to solve it with O(n) time complexity?
 */
public class LC0333 {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return maxSize;
    }

    private class Result {
        int min;
        int max;
        int size;

        private Result(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    private int maxSize = 0;

    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(0, 0, 0);
        }

        Result left = dfs(root.left);
        Result right = dfs(root.right);
        if (left == null || right == null) {
            return null;
        }
        if ((left.size == 0 || left.max < root.val)
                && (right.size == 0 || root.val < right.min)) {
            int size = left.size + right.size + 1;
            maxSize = Math.max(maxSize, size);
            return new Result(left.size == 0 ? root.val : left.min,
                    right.size == 0 ? root.val : right.max, size);
        } else {
            return null;
        }
    }
}
