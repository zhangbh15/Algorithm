package LeetCode.util;
import LeetCode.LC201_300.LC0297;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }
    public TreeNode() {}
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Construct a tree from its level-order traversal array.
     * All and only the leaf node nulls should be included int the array.
     * @param array: the level-order traversal array.
     */
    public TreeNode(Integer[] array) {
        if (array != null && array.length >= 1) {
            val = array[0];

            Queue<TreeNode> que = new LinkedList<>();
            que.offer(this);
            int len = array.length;
            for (int i = 1; i < len; i++) {
                TreeNode cur = array[i] == null ? null : new TreeNode(array[i]);
                if (i % 2 == 1) {
                    que.peek().left = cur;
                } else {
                    que.poll().right = cur;
                }
                if (cur != null) {
                    que.offer(cur);
                }
            }
        }
    }

    /**
     * Return the level-order traversal of the tree with current TreeNode as root.
     */
    public List<Integer> toArrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(val);
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(left);
        que.offer(right);
        while (!que.isEmpty()) {
            TreeNode cur = que.poll();
            if (cur != null) {
                list.add(cur.val);
                que.offer(cur.left);
                que.offer(cur.right);
            } else {
                list.add(null);
            }
        }
        return list;
    }

    public static String serialize(TreeNode root) { // BFS
        return LC0297.serializeBetterBFS(root);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) { // BFS
        return LC0297.deserialize(data);
    }
}
