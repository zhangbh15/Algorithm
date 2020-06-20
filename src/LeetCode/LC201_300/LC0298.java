package LeetCode.LC201_300;
import LeetCode.TreeNode;

/**
 * Binary Tree Longest Consecutive Sequence
 *
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * Example 1:
 *
 * Input:
 *
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 *
 * Output: 3
 *
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 *
 * Example 2:
 *
 * Input:
 *
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 *
 * Output: 2
 *
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 */
public class LC0298 {
    private int maxLen = 0;
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        int ret = 1;
        if (root.left != null && root.val + 1 == root.left.val) {
            ret += left;
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            ret = Math.max(ret, right + 1);
        }
        maxLen = Math.max(maxLen, ret);
        return ret;
    }

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return maxLen;
    }

    public static void main(String[] args) {
        LC0298 so = new LC0298();
        Integer[] array = new Integer[] {2, null, 3, 2, null, 1};
        TreeNode root = new TreeNode(array);
        int res = so.longestConsecutive(root);
        System.out.println(res);
    }
}
