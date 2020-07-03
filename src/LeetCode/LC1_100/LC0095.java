package LeetCode.LC1_100;
import java.util.*;
import LeetCode.util.TreeNode;

/**
 * Unique Binary Search Trees II
 *
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * Explanation:
 * The above output corresponds to the 5 unique BST's shown below:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * Definition for a binary tree node.
 *
 */
public class LC0095 {
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        for (int n = start; n <= end; n++) {
            List<TreeNode> lefts = dfs(start, n - 1);
            List<TreeNode> rights = dfs(n + 1, end);
            for (TreeNode leftNode : lefts) {
                for (TreeNode rightNode : rights) {
                    TreeNode root = new TreeNode(n);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LC0095 so = new LC0095();
        int n = 3;
        List<TreeNode> res = so.generateTrees(n);
        for (TreeNode root : res) {
            System.out.println(root.toArrayList());
        }
    }
}
