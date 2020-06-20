package LeetCode.LC101_200;
import java.util.*;
import LeetCode.TreeNode;
import LeetCode.ListNode;

/**
 * Convert Sorted List to Binary Search Tree
 *
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted linked list: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class LC0109 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode cur = head;
        List<Integer> array = new ArrayList<>();
        while (cur != null) {
            array.add(cur.val);
            cur = cur.next;
            size++;
        }

        return helper(array, 0, size - 1);
    }

    private TreeNode helper(List<Integer> array, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(array.get(mid));
        root.left = helper(array, start, mid - 1);
        root.right = helper(array, mid + 1, end);
        return root;
    }

    private ListNode cur;

    public TreeNode o1Space(ListNode head) {
        if (head == null) {
            return null;
        }

        cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        cur = head;
        return helper(0, size - 1);
    }

    private TreeNode helper(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode left = helper(start, mid - 1);
        TreeNode root = new TreeNode(cur.val);
        root.left = left;
        cur = cur.next;
        root.right = helper(mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        LC0109 so = new LC0109();

        int[] array = {-10,-3,0,5,9};

        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for (int i = 1; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }

        TreeNode res = so.o1Space(head);
        System.out.println(res.toArrayList());
    }
}
