package LeetCode.LC101_200;

import LeetCode.util.ListNode;

/**
 * Sort List
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class LC0148 {
    // based on quick sort
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode pivot = head;

        while (cur.next != null) {
            if (cur.next.val <= pivot.val) {
                ListNode temp = cur.next;
                cur.next = cur.next.next;

                temp.next = head.next;
                head.next = temp;
            } else {
                cur = cur.next;
            }
        }

        cur = head;
        while (cur.next != null && cur.next.val <= pivot.val) {
            cur = cur.next;
        }

        head = head.next;
        pivot.next = cur.next;
        cur.next = null;

        head = sortList(head);
        ListNode afterHead = sortList(pivot.next);

        cur = head;
        while (head.next != null) {
            cur = cur.next;
        }
        cur.next = pivot;
        pivot.next = afterHead;

        return head;
    }

    // based on merge sort
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        head = mergeSort(head);
        head2 = mergeSort(head2);

        return merge(head, head2);
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode ret;
        if (head1.val <= head2.val) {
            ret = head1;
            head1 = head1.next;
        } else {
            ret = head2;
            head2 = head2.next;
        }

        ListNode cur = ret;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                cur = cur.next;
                head1 = head1.next;
            } else {
                cur.next = head2;
                cur = cur.next;
                head2 = head2.next;
            }
        }

        cur.next = head1 == null ? head2 : head1;
        return ret;
    }
}
