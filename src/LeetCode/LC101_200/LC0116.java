package LeetCode.LC101_200;

/**
 * Populating Next Right Pointers in Each Node
 *
 * ** Related to LC0117 **
 *
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Follow up:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count
 * as extra space for this problem.
 *
 *
 * Example 1:
 *       1                  1 --> null
 *     /  \                /  \
 *   2     3             2 --> 3 --> null
 *  / \   / \           / \   / \
 * 4  5  6  7          4->5->6->7 -> null
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should
 * populate each next pointer to point to its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers,
 * with '#' signifying the end of each level.
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is less than 4096.
 * -1000 <= node.val <= 1000
 */
public class LC0116 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connect(root.left);
        connect(root.right);

        Node leftEnd = root.left;
        Node rightEnd = root.right;
        while (leftEnd != null) {
            leftEnd.next = rightEnd;
            leftEnd = leftEnd.right;
            rightEnd = rightEnd.left;
        }

        return root;
    }

    public Node connectInPlace(Node root) {
        if (root == null) {
            return null;
        }

        Node cur = root;
        Node leftEnd;
        while (cur.left != null) {
            leftEnd = cur.left;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }

            cur = leftEnd;
        }

        return root;
    }
}