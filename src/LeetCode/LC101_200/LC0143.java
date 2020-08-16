package LeetCode.LC101_200;

import LeetCode.util.ListNode;

import java.util.Stack;

/**
 * Reorder List
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 *
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 *
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class LC0143 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        Stack<ListNode> stack = new Stack<>();
        stack.push(head);
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            stack.push(stack.peek().next);
            fast = fast.next.next;
        }

        head = stack.pop();
        ListNode nextNode = head.next;
        head.next = null;
        if (fast == null) {
            ListNode popNode = stack.pop();
            popNode.next = head;
            head = popNode;
        }

        while (!stack.isEmpty()) {
            ListNode temp = nextNode;
            nextNode = nextNode.next;
            temp.next = head;
            stack.peek().next = temp;
            head = stack.pop();
        }
    }

    public void reverseAndMerge(ListNode head) {
        // cc
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode cur1 = head;
        ListNode cur2 = reverse(slow.next);
        slow.next = null;

        while (cur2 != null) {
            ListNode temp1 = cur1.next;
            ListNode temp2 = cur2.next;
            cur1.next = cur2;
            cur2.next = temp1;
            cur1 = temp1;
            cur2 = temp2;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);

        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
