package LeetCode.LC1_100;

import java.util.*;
import LeetCode.Util.TreeNode;

/**
 * Binary Tree Inorder Traversal
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class LC0094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);
        res.addAll(left);
        res.add(root.val);
        res.addAll(right);
        return res;
    }

    public List<Integer> inorderTraversalIter(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else { // stack is not empty
                TreeNode popNode = stack.pop();
                res.add(popNode.val);
                cur = popNode.right;
            }
        }

        return res;
    }

    public List<Integer> inorderTraversalItr2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = null;
        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (prev == null || cur == prev.left || cur == prev.right) {
                if (cur.left != null) {
                    stack.push(cur.left);
                } else if (cur.right != null) {
                    res.add(cur.val);
                    stack.push(cur.right);
                } else {
                    TreeNode popNode = stack.pop();
                    res.add(popNode.val);
                }
            } else if (prev == cur.left) {
                if (cur.right != null) {
                    res.add(cur.val);
                    stack.push(cur.right);
                } else {
                    TreeNode popNode = stack.pop();
                    res.add(popNode.val);
                }
            } else if (prev == cur.right){
                TreeNode popNode = stack.pop();
            }
            prev = cur;
        }

        return res;
    }
}
