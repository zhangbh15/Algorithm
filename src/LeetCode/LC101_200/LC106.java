package LeetCode.LC101_200;

import LeetCode.Util.TreeNode;
import java.util.*;

/**
 * Construct Binary Tree from Inorder and Postorder Traversal
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class LC106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null
                || inorder.length != postorder.length || inorder.length == 0 ) {
            return null;
        }

        int len = inorder.length;
        postIdx = len - 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }

        return helper(0, len - 1, postorder, map);
    }

    private int postIdx;

    private TreeNode helper(int start, int end, int[] postorder, Map<Integer, Integer> map) {
        if (start > end) {
            return null;
        }

        int val = postorder[postIdx--];
        TreeNode root = new TreeNode(val);
        int inIdx = map.get(val);

        root.right = helper(inIdx + 1, end, postorder, map);
        root.left = helper(start, inIdx - 1, postorder, map);

        return root;
    }
}
