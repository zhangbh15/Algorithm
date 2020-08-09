package LeetCode.LC401_500;

/**
 * Split Array Largest Sum
 *
 * Given an array which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays. Write an
 * algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 *
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */
public class LC0410 {
    // DFS time O(n^m)
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0 || m < 1 || m > nums.length) {
            return 0;
        }

        minSol = Integer.MAX_VALUE;
        dfs(0, nums, 0, m);
        return minSol;
    }

    private int minSol;

    private void dfs(int curMaxSum, int[] array, int startIdx, int numSubarray) {
        if (numSubarray == 1) {
            int subarraySum = 0;
            for (int i = startIdx; i < array.length; i++) {
                subarraySum += array[i];
            }
            curMaxSum = Math.max(curMaxSum, subarraySum);
            minSol = Math.min(curMaxSum, minSol);
            return;
        }

        int subarraySum = 0;
        for (int i = startIdx; i < array.length - numSubarray + 1; i++) {
            subarraySum += array[i];
            dfs(Math.max(curMaxSum, subarraySum), array, i + 1, numSubarray - 1);
        }
    }

    // DP time O(n^2*m) space O(m*n)

    // Binary Search + Greedy: time O(n∗log(sumOfArray))
    public int splitArrayGreedy(int[] nums, int m) {
        // cc
        long lowerBound = 0;
        long upperBound = 0;
        int len = nums.length;
        for (int num : nums) {
            upperBound += num;
            lowerBound = Math.max(lowerBound, num);
        }

        long res = upperBound;
        while (lowerBound <= upperBound) {
            long mid = lowerBound + (upperBound - lowerBound) / 2;
            long sum = 0;
            int cnt = 1;
            for (int num : nums) {
                if (sum + num > mid) {
                    cnt++;
                    sum = num;
                } else {
                    sum += num;
                }
            }

            if (cnt <= m) {
                res = Math.min(res, mid);
                upperBound = mid - 1;
            } else {
                lowerBound = mid + 1;
            }
        }

        return (int) res;
    }
}

