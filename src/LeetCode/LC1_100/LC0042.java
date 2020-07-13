package LeetCode.LC1_100;

/**
 * Trapping Rain Water
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class LC0042 {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0] = height[0];
        rightMax[len - 1] = height[len - 1];

        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            rightMax[len - i - 1] = Math.max(rightMax[len - i], height[len - i - 1]);
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }

    public int trapTwoPointers(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;

        int sum = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                sum += leftMax - height[left++];
            } else {
                rightMax = Math.max(rightMax, height[right]);
                sum += rightMax - height[right--];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        LC0042 so = new LC0042();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int res = so.trapTwoPointers(height);
        System.out.println(res);
    }
}
