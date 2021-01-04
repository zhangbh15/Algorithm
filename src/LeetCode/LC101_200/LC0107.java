package LeetCode.LC101_200;

import LeetCode.util.*;
import java.util.*;

/**
 * Binary Tree Level Order Traversal II
 *
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class LC0107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> level = new ArrayList<>(size);

            for (int i = 0; i < size; i++) {
                TreeNode cur = que.poll();
                level.add(cur.val);
                if (cur.left != null) {
                    que.offer(cur.left);
                }
                if (cur.right != null) {
                    que.offer(cur.right);
                }
            }

            res.add(level);
        }

        Collections.reverse(res);
        return res;
    }
}
