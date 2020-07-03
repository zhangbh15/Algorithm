package LeetCode.LC101_200;

import LeetCode.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Symmetric Tree
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * Follow up: Solve it both recursively and iteratively.
 */
public class LC0101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        boolean outter = dfs(left.left, right.right);
        boolean inner = dfs(left.right, right.left);
        return outter && inner;
    }

    public boolean isSymmetricIter(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();

        if (!push(root.left, root.right, leftStack, rightStack)) {
            return false;
        }

        while (!leftStack.isEmpty() && !rightStack.isEmpty()) { // leftStack and rightStack are actually always the same
            TreeNode leftTop = leftStack.pop();
            TreeNode rightTop = rightStack.pop();

            if (!push(leftTop.right, rightTop.left, leftStack, rightStack)) {
                return false;
            }
        }

        return true;
    }

    private boolean push(TreeNode leftRoot, TreeNode rightRoot, Stack<TreeNode> leftStack, Stack<TreeNode> rightStack) {
        TreeNode left = leftRoot;
        TreeNode right = rightRoot;
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }

            leftStack.push(left);
            rightStack.push(right);
            left = left.left;
            right = right.right;
        }

        return left == null && right == null;
    }

    public boolean isSymmetricQue(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root.left);
        que.offer(root.right);

        while (!que.isEmpty()) {
            TreeNode left = que.poll();
            TreeNode right = que.poll();

            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }

            que.offer(left.left);
            que.offer(right.right);
            que.offer(left.right);
            que.offer(right.left);
        }

        return true;
    }
}
