package LeetCode.LC501_600;
import LeetCode.TreeNode;

/**
 * Binary Tree Longest Consecutive Sequence II
 *
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 *
 * Especially, this path can be either increasing or decreasing. For example,
 * [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
 * On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 * Example 1:
 *
 * Input:
 *         1
 *        / \
 *       2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 *
 *
 * Example 2:
 *
 * Input:
 *         2
 *        / \
 *       1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 *
 * Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */
public class LC0549 {
    private int maxLen;
    private int[] dfs(TreeNode root) {
        // res[0] is the length of increasing path. res[1] is the length of decreasing path.
        if (root == null) {
            return new int[] {0, 0};
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int[] ret = new int[] {1, 1};
        if (root.left != null) {
            if (root.val + 1 == root.left.val) {
                ret[0] += left[0];
                if (root.right != null && root.val - 1 == root.right.val) {
                    maxLen = Math.max(maxLen, left[0] + 1 + right[1]);
                }
            } else if (root.val - 1 == root.left.val) {
                ret[1] += left[1];
                if (root.right != null && root.val + 1 == root.right.val) {
                    maxLen = Math.max(maxLen, left[1] + 1 + right[0]);
                }
            }
        }
        if (root.right != null) {
            if (root.val + 1 == root.right.val) {
                ret[0] = Math.max(ret[0], right[0] + 1);
            } else if (root.val - 1 == root.right.val) {
                ret[1] = Math.max(ret[1], right[1] + 1);
            }
        }

        maxLen = Math.max(maxLen, Math.max(ret[0], ret[1]));
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
        LC0549 so = new LC0549();
        Integer[] array = new Integer[] {2, 1, 3};
        TreeNode root = new TreeNode(array);
        int res = so.longestConsecutive(root);
        System.out.println(res);
    }
}
