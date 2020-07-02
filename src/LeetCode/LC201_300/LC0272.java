package LeetCode.LC201_300;
import LeetCode.Util.TreeNode;
import java.util.*;

/**
 * Closest Binary Search Tree Value II
 *
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 *
 * Note:
 *
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 * Example:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: [4,3]
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 */
public class LC0272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null || k <= 0) {
            return res;
        }

        Stack<TreeNode> ls = new Stack<>();
        Stack<TreeNode> rs = new Stack<>();
        initialStacks(root, target, ls, rs);

        for (int i = 0; i < k; i++) {
            if (ls.isEmpty() && rs.isEmpty()) {
                break;
            }

            if (ls.isEmpty()) {
                res.add(rs.peek().val);
                popRs(rs);
            } else if (rs.isEmpty()) {
                res.add(ls.peek().val);
                popLs(ls);
            } else {
                if (Math.abs(target - (double)ls.peek().val) < Math.abs(target - (double)rs.peek().val)) {
                    res.add(ls.peek().val);
                    popLs(ls);
                } else {
                    res.add(rs.peek().val);
                    popRs(rs);
                }
            }
        }
        return res;
    }

    private void initialStacks(TreeNode root, double target, Stack<TreeNode> ls, Stack<TreeNode> rs) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < target) {
                ls.push(cur);
                cur = cur.right;
            } else {
                rs.push(cur);
                cur = cur.left;
            }
        }
    }

    private void popLs(Stack<TreeNode> stack) {
        TreeNode cur = stack.pop().left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
    }

    private void popRs(Stack<TreeNode> stack) {
        TreeNode cur = stack.pop().right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    public static void main(String[] args) {
        LC0272 so = new LC0272();
        Integer[] treeArray = new Integer[] {-1, -2, 0, null, null, null, null};
        TreeNode root = new TreeNode(treeArray);
        double target = Integer.MAX_VALUE;
        int k = 2;
        List<Integer> res = so.closestKValues(root, target, k);
        System.out.println(res);
    }
}
