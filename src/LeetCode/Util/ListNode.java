package LeetCode.Util;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }

    public static ListNode fromArray(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        for (int n : array) {
            ListNode next = new ListNode(n);
            cur.next = next;
            cur = cur.next;
        }

        cur.next = null;
        return dummy.next;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(this.val).append(", ");

        ListNode cur = this.next;
        while (cur != null) {
            sb.append(cur.val).append(", ");
            cur = cur.next;
        }

        sb.setLength(sb.length() - 2);
        sb.append(']');

        return sb.toString();
    }
}
