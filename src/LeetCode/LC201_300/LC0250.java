package LeetCode.LC201_300;
import LeetCode.util.TreeNode;

/**
 * Count Univalue Subtrees
 *
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * Example :
 *
 * Input:  root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * Output: 4
 */
public class LC0250 {
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return -1;
        }
        dfs(root, root.val);
        return cnt;
    }
    private int cnt = 0;
    // if do not use global variable, then pass int[1] cnt to dfs()
    private boolean dfs(TreeNode root, int target) {
        if (root == null) {
            return true;
        }

        boolean left = dfs(root.left, root.val);
        boolean right = dfs(root.right, root.val);
        if (!left || !right) {
            return false;
        }
        cnt++;
        return root.val == target;
    }
}
