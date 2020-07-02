package LeetCode.LC1_100;

import LeetCode.Util.ListNode;

/**
 * Merge Two Sorted Lists
 *
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class LC0021 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        ListNode head;
        if (l1.val <= l2.val) {
            head = l1;
            cur1 = cur1.next;
        } else {
            head = l2;
            cur2 = cur2.next;
        }
        ListNode cur = head;

        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }

        if (cur1 != null) {
            cur.next = cur1;
        } else {
            cur.next = cur2;
        }

        return head;
    }
}
