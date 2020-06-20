package LeetCode.LC101_200;

/**
 * Best Time to Buy and Sell Stock IV
 *
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class LC0188 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1 || k == 0) {
            return 0;
        }

        k = Math.min(k, prices.length / 2);
        int[] buy = new int[k];
        int[] sell = new int[k];
        for (int i = 0; i < k; i++) {
            buy[i] = Integer.MIN_VALUE;
        }

        for (int p : prices) {
            for (int j = k - 1; j >= 1; j--) {
                sell[j] = Math.max(sell[j], buy[j] + p);
                buy[j] = Math.max(buy[j], sell[j - 1] - p);
            }
            sell[0] = Math.max(sell[0], buy[0] + p);
            buy[0] = Math.max(buy[0], -p);
        }

        return sell[k - 1];
    }

    public static void main(String[] args) {
        LC0188 so = new LC0188();
        int[] prices = {2,4,1};
        int k = 2;
        int res = so.maxProfit(k, prices);
        System.out.println(res);
    }
}
