package LeetCode.LC301_400;

import LeetCode.util.Pair;
import LeetCode.util.TreeNode;
import java.util.*;

/**
 * Binary Tree Vertical Order Traversal
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples 1:
 *
 * Input: [3,9,20,null,null,15,7]
 *
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 *
 * Output:
 *
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 * Examples 2:
 *
 * Input: [3,9,8,4,0,1,7]
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *
 * Output:
 *
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 * Examples 3:
 *
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 *
 * Output:
 *
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 */
public class LC0314 {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> que = new LinkedList<>();
        que.offer(new Pair<>(root, 0));
        int minCol = 0;
        int maxCol = 0;

        while (!que.isEmpty()) {
            Pair<TreeNode, Integer> pair = que.poll();
            TreeNode cur = pair.getKey();
            int col = pair.getValue();

            minCol = Math.min(minCol, col);
            maxCol = Math.max(maxCol, col);

            if (!map.containsKey(col)) {
                map.put(col, new LinkedList<>());
            }
            map.get(col).add(cur.val);

            if (cur.left != null) {
                que.offer(new Pair<>(cur.left, col - 1));
            }
            if (cur.right != null) {
                que.offer(new Pair<>(cur.right, col + 1));
            }
        }

        for (int i = minCol; i <= maxCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        LC0314 so = new LC0314();
        TreeNode root = new TreeNode(new Integer[] {6,3,null,1,5,null,2,4});
        List<List<Integer>> res = so.verticalOrder(root);
        System.out.println(res);
    }
}
