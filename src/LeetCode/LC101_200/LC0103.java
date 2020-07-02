package LeetCode.LC101_200;

import LeetCode.Util.TreeNode;
import java.util.*;

/**
 * Binary Tree Zigzag Level Order Traversal
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class LC0103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> que = new LinkedList<>();
        que.offerLast(root);
        boolean oddLevel = true;

        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> level = new LinkedList<>();

            if (oddLevel) {
                for (int i = 0; i < size; i++) {
                    TreeNode cur = que.pollFirst();
                    level.add(cur.val);

                    if (cur.left != null) {
                        que.offerLast(cur.left);
                    }
                    if (cur.right != null) {
                        que.offerLast(cur.right);
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode cur = que.pollLast();
                    level.add(cur.val);
                    if (cur.right != null) {
                        que.offerFirst(cur.right);
                    }
                    if (cur.left != null) {
                        que.offerFirst(cur.left);
                    }
                }
            }

            oddLevel = !oddLevel;
            res.add(level);
        }

        return res;
    }

    public List<List<Integer>> zigzagLevelOrderReverse(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        boolean oddLevel = true;

        while (!que.isEmpty()) {
            int size = que.size();
            List<Integer> level = new LinkedList<>();

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

            if (!oddLevel) {
                Collections.reverse(level);
            }
            res.add(level);
            oddLevel = !oddLevel;
        }

        return res;
    }
}
