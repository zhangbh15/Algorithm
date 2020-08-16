package LeetCode.LC1_100;

import LeetCode.util.ListNode;

/**
 * Remove Duplicate from Sorted List
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class LC0083 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        while (slow != null) {
            ListNode fast = slow;
            while (fast != null && fast.next != null && fast.next.val == fast.val) {
                fast = fast.next;
            }

            slow.next = fast.next;
            slow = slow.next;
        }

        return head;
    }

    public ListNode better(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
