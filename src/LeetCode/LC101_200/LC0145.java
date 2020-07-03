package LeetCode.LC101_200;

import java.util.*;
import LeetCode.util.TreeNode;

/**
 * Binary Tree Postorder Traversal
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
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
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class LC0145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        List<Integer> left = postorderTraversal(root.left);
        List<Integer> right = postorderTraversal(root.right);

        res.addAll(left);
        res.addAll(right);
        res.add(root.val);

        return res;
    }

    public List<Integer> postorderTraversalIter (TreeNode root) {
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
                    stack.push(cur.right);
                } else {
                   TreeNode popNode = stack.pop();
                   res.add(popNode.val);
                }
            } else if (prev == cur.left) {
                if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    TreeNode popNode = stack.pop();
                    res.add(popNode.val);
                }
            } else if (prev == cur.right){
                TreeNode popNode = stack.pop();
                res.add(popNode.val);
            }
            prev = cur;
        }

        return res;
    }
}
