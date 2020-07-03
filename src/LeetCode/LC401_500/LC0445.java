package LeetCode.LC401_500;

import LeetCode.util.*;
import java.util.*;

/**
 * Add Two Numbers II
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class LC0445 {
    public ListNode addTwoNumbersStack(ListNode l1, ListNode l2) {
        if (l2 == null) {
            return l1;
        }
        if (l1 == null) {
            return l2;
        }

        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while (cur1 != null) {
            stack1.push(cur1);
            cur1 = cur1.next;
        }
        while(cur2 != null) {
            stack2.push(cur2);
            cur2 = cur2.next;
        }

        ListNode next = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int val1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int val2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            int val = val1 + val2 + carry;
            ListNode cur = new ListNode(val % 10, next);

            carry = val / 10;
            next = cur;
        }

        if (carry != 0) {
            next = new ListNode(carry, next);
        }

        return next;
    }

    // Reverse Linked List
    public ListNode addTwoNumbersReverse(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode cur1 = reverse(l1);
        ListNode cur2 = reverse(l2);
        int carry = 0;

        ListNode res = new ListNode();
        ListNode cur = res;
        while (cur1 != null || cur2 != null || carry != 0) {
            int val = carry;
            if (cur1 != null) {
                val += cur1.val;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                val += cur2.val;
                cur2 = cur2.next;
            }

            ListNode next = new ListNode(val % 10);
            cur.next = next;
            cur = next;
            carry = val / 10;
        }

        return reverse(res.next);
    }

    private ListNode reverse(ListNode head) {
        // guarantee head not null
        if (head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    // Recursion
    public ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2) {
        return reverse(add(reverse(l1),reverse(l2),0));
    }

    public ListNode add(ListNode l1, ListNode l2, int carry) {
        if(l1==null && l2==null & carry==0)
            return null;
        int sum = (l1 == null ? 0:l1.val) +( l2 == null ? 0:l2.val) + carry;
        ListNode res = new ListNode(sum%10);
        res.next = add((l1 == null ? null: l1.next), (l2 == null ? null: l2.next), sum/10);
        return res;
    }


    // No reverse, no stack
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        int len1 = getLength(l1);
        int len2 = getLength(l2);

        int diff = Math.abs(len1 - len2);
        ListNode longer = len1 >= len2 ? l1 : l2;
        ListNode shorter = len1 >= len2 ? l2 : l1;
        ListNode dummy = new ListNode();
        ListNode prev = dummy;

        for (int i = 0; i < diff; i++) {
            prev.next = new ListNode(longer.val);
            longer = longer.next;
            prev = prev.next;
        }
        for (int i = 0; i < Math.min(len1, len2); i++) {
            prev.next = new ListNode(longer.val + shorter.val);
            longer = longer.next;
            shorter = shorter.next;
            prev = prev.next;
        }

        int carry = normalize(dummy.next);
        if (carry != 0) {
            dummy.next = new ListNode(carry, dummy.next);
        }
        return dummy.next;
    }

    private int normalize(ListNode head) {
        if (head == null) {
            return 0;
        }

        int sum = head.val + normalize(head.next);
        head.val = sum % 10;
        return sum / 10;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }


    public static void main(String[] args) {
        LC0445 so = new LC0445();
        ListNode l1 = ListNode.fromArray((new int[] {7}));
        ListNode l2 = ListNode.fromArray(new int[] {5});
        ListNode res = so.addTwoNumbers(l1, l2);
        System.out.println(res.toString());
    }
}
