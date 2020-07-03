package LeetCode.LC101_200;

import java.util.*;
import LeetCode.util.TreeNode;

/**
 * Binary Tree Preorder Traversal
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.
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
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class LC0144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);

        res.add(root.val);
        res.addAll(left);
        res.addAll(right);

        return res;
    }

    public List<Integer> preorderTraversalIter(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                res.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode popNode = stack.pop();
                cur = popNode.right;
            }
        }

        return res;
    }

    public List<Integer> preorderTraversalItr2(TreeNode root) {
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
                    res.add(cur.val);
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
                    stack.push(cur.right);
                } else {
                    stack.pop();
                }
            } else if (prev == cur.right){
                stack.pop();
            }
            prev = cur;
        }

        return res;
    }
}
