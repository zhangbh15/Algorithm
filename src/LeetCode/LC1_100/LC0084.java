package LeetCode.LC1_100;

import java.util.*;

/**
 *
 */
public class LC0084 {
    public int largestRectangleArea(int[] heights) {
        // cc
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length ? 0 : heights[i]);
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int idx = stack.pop();
                int area = heights[idx] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                max = Math.max(max, area);
            }
            stack.push(i);
        }

        return max;
    }
}
