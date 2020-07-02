package LeetCode.LC101_200;

import java.util.*;
import LeetCode.Util.TreeNode;

/**
 * Binary Tree Right Side View
 *
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class LC0199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        height = 0;
        helper(root, 1, res);
        return res;
    }

    private int height;

    private void helper(TreeNode root, int level, List<Integer> res) {
        if (root == null) {
            return;
        }

        if (level > height) {
            res.add(root.val);
            height = level;
        }

        helper(root.right, level + 1, res);
        helper(root.left, level + 1, res);
    }

    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            TreeNode cur = que.peek();
            for (int i = 0; i < size; i++) {
                cur = que.poll();
                if (cur.left != null) {
                    que.offer(cur.left);
                }
                if (cur.right != null) {
                    que.offer(cur.right);
                }
            }
            res.add(cur.val);
        }

        return res;
    }
}
