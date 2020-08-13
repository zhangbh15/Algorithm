package LeetCode.LC1_100;

import LeetCode.util.ListNode;

/**
 * Reverse Nodes in k-Group
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class LC0025 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            if (cur == null) {
                return head;
            }
            cur = cur.next;
        }

        ListNode nextHead = reverseKGroup(cur, k);
        ListNode newHead = reverseByK(head, k);
        head.next = nextHead;
        return newHead;
    }

    private ListNode reverseByK(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }
        // nodes are guaranteed not null

        ListNode newHead = reverseByK(head.next, k - 1);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
