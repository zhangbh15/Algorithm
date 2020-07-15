package LeetCode.LC1_100;

import LeetCode.util.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Merge k Sroted Lists
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class LC0023 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode();
        Queue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode head : lists) {
            if (head != null) {
                heap.offer(head);
            }
        }

        ListNode cur = dummy;
        while (!heap.isEmpty()) {
            cur.next = heap.poll();
            cur = cur.next;
            if (cur.next != null) {
                heap.offer(cur.next);
            }
        }

        cur.next = null;
        return dummy.next;
    }

    public ListNode mergeKListsDivide(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        int len = lists.length;
        int mid = len / 2;
        ListNode[] leftLists = new ListNode[mid];
        ListNode[] rightLists = new ListNode[len - mid];
        System.arraycopy(lists, 0, leftLists, 0, mid);
        System.arraycopy(lists, mid, rightLists, 0, len - mid);

        ListNode leftHead = mergeKListsDivide(leftLists);
        ListNode rightHead = mergeKListsDivide(rightLists);

        return mergeTwo(leftHead, rightHead);
    }

    private ListNode mergeTwo(ListNode leftHead, ListNode rightHead) {
        ListNode leftCur = leftHead;
        ListNode rightCur = rightHead;
        ListNode dummy = new ListNode();

        ListNode cur = dummy;
        while (leftCur != null && rightCur != null) {
            if (leftCur.val <= rightCur.val) {
                cur.next = leftCur;
                leftCur = leftCur.next;
            } else {
                cur.next = rightCur;
                rightCur = rightCur.next;
            }
            cur = cur.next;
        }
        while (leftCur != null) {
            cur.next = leftCur;
            leftCur = leftCur.next;
            cur = cur.next;
        }
        while (rightCur != null) {
            cur.next = rightCur;
            rightCur = rightCur.next;
            cur = cur.next;
        }

        return dummy.next;
    }
}
