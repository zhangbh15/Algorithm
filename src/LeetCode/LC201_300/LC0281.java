package LeetCode.LC201_300;

import java.util.*;

/**
 * Zigzag Iterator
 *
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 *
 * Example:
 *
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
 *
 *
 * Follow up:
 *
 * What if you are given k 1d vectors? How well can your code be extended to such cases?
 *
 * Clarification for the follow up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:
 *
 * Input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 *
 * Output: [1,4,8,2,5,9,3,6,7].
 *
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
public class LC0281 {
    private final Queue<Iterator<Integer>> que;

    public LC0281(List<Integer> v1, List<Integer> v2) {
        que = new LinkedList<>();
        if (v1 != null && !v1.isEmpty()) {
            que.offer(v1.iterator());
        }
        if (v2 != null && !v2.isEmpty()) {
            que.offer(v2.iterator());
        }
    }

    // follow up
    public LC0281(List<List<Integer>> v) {
        que = new LinkedList<>();
        for (List<Integer> vi : v) {
            if (vi != null && !vi.isEmpty()) {
                que.offer(vi.iterator());
            }
        }
    }

    public int next() {
        Iterator<Integer> it = que.poll();
        int ret = it.next();
        if (it.hasNext()) {
            que.offer(it);
        }
        return ret;
    }

    public boolean hasNext() {
        return !que.isEmpty();
    }

    public static void main(String[] args) {
        int[] array1 = new int[] {1, 2};
        int[] array2 = new int[] {3, 4, 5, 6};

        List<Integer> v1 = Arrays.asList(1, 2);
        List<Integer> v2 = Arrays.asList(3, 4, 5, 6);
        LC0281 so = new LC0281(v1, v2);

        while (so.hasNext()) {
            System.out.println(so.next());
        }

        List<Integer> v3 = Arrays.asList(7, 8, 9);
        List<List<Integer>> v = new ArrayList<>();
        v.add(v1);
        v.add(v2);
        v.add(v3);

        LC0281 followup = new LC0281(v);
        while (followup.hasNext()) {
            System.out.println(followup.next());
        }
    }
}
