package LeetCode.LC601_700;

import LeetCode.util.TreeNode;

import java.util.Stack;

/**
 *  Two Sum IV - Input is a BST
 *
 *  Given a Binary Search Tree and a target number,
 *  return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 * Example 1:
 *
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * Output: True
 *
 *
 * Example 2:
 *
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * Output: False
 */

public class LC0653 {
    public boolean findTarget(TreeNode root, int target) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> ls = initializeLefts(root);
        Stack<TreeNode> rs = initializeRights(root);
        while (!ls.isEmpty() && !rs.isEmpty()) {
            TreeNode l = ls.peek();
            TreeNode r = rs.peek();
            if (l == r) {
                return false;
            }
            if (l.val + r.val == target) {
                return true;
            } else if (l.val + r.val < target) {
                popLs(ls);
            } else {
                popRs(rs);
            }
        }
        return false;
    }

    private Stack<TreeNode> initializeLefts(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return stack;
    }

    private Stack<TreeNode> initializeRights(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return stack;
    }

    private void popLs(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    private void popRs(Stack<TreeNode> stack) {
        TreeNode cur = stack.pop().left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
    }
}
