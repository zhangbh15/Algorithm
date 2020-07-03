package LeetCode.LC1_100;

import LeetCode.util.ListNode;

/**
 * Remove Nth Node From End of List
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 */
public class LC0019 {
    public ListNode TwoPass(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }

        int idx = len - n;
        if (idx == 0) { // n == len
            return head.next;
        }

        cur = head;
        for (int i = 0; i < idx - 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }

    public ListNode OnePass(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode cur = head;
        for (int i = 0; i < n; i++) {
            cur = cur.next;
        }

        if (cur == null) { // n == len
            return head.next;
        }

        ListNode tail = cur;
        cur = head;

        while (tail.next != null) {
            cur = cur.next;
            tail = tail.next;
        }

        cur.next = cur.next.next;
        return head;
    }
}
