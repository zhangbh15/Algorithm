package LeetCode.LC201_300;
import LeetCode.TreeNode;

/**
 * Closest Binary Search Tree Value
 *
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Note:
 *
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 *
 * Example:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: 4
 */
public class LC0270 {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        TreeNode cur = root;
        int ret = cur.val;
        while (cur != null) {
            if (Math.abs(cur.val - target) < Math.abs(ret - target)) {
                ret = cur.val;
            }

            if (cur.val < target) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return ret;
    }
}
