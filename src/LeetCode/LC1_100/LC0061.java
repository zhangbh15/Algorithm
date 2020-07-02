package LeetCode.LC1_100;

import LeetCode.Util.ListNode;

/**
 * Rotate List
 *
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class LC0061 {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode end = head;
        int len = 0;
        for (int i = 0; i < k; i++) {
            if (end == null) {
                break;
            }

            end = end.next;
            len++;
        }

        if (end == null){
            end = head;
            for (int i = 0; i < (k % len); i++) {
                end = end.next;
            }
        }

        ListNode cur = head;
        while (end.next != null) {
            end = end.next;
            cur = cur.next;
        }

        end.next = head;
        ListNode newHead = cur.next == null ? head : cur.next;
        cur.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        LC0061 so = new LC0061();
        ListNode head = ListNode.fromArray(new int[] {0,1});
        ListNode res = so.rotateRight(head, 4);
        System.out.println(res.toString());
    }
}
