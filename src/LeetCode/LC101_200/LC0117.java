package LeetCode.LC101_200;
import java.util.*;

/**
 * Populating Next Right Pointers in Each Node II
 *
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Follow up:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 *
 * Example 1:
 *
 *         1                    1 --> null
 *       /  \                 /  \
 *      2    3              2 --> 3 --> null
 *    /  \    \           /  \     \
 *   4    5    7        4 --> 5 --> 7 --> null
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is less than 6000.
 * -100 <= node.val <= 100
 */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

public class LC0117 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Node pollNode = que.poll();
                if (i < size - 1) {
                    pollNode.next = que.peek();
                }
                if (pollNode.left != null) {
                    que.offer(pollNode.left);
                }
                if (pollNode.right != null) {
                    que.offer(pollNode.right);
                }
            }
        }
        return root;
    }

    private Node nextHead = null;
    private Node nextCur = nextHead;

    public Node o1Space(Node root) {
        if (root == null) {
            return null;
        }

        Node cur = root;

        while (cur != null) {
            proceed(cur.left);
            proceed(cur.right);

            if (cur.next != null) {
                cur = cur.next;
            } else {
                cur = nextHead;
                nextHead = null;
                nextCur = nextHead;
            }
        }
        return root;
    }

    private void proceed(Node node) {
        if (node != null) {
            if (nextCur != null) {
                nextCur.next = node;
            } else {
                nextHead = node;
            }
            nextCur = node;
        }
    }
}
