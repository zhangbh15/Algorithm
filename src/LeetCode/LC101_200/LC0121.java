package LeetCode.LC101_200;

/**
 * Best Time to Buy and Sell Stock
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 *
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class LC0121 {
    public int maxProfit(int[] prices) {
        // CC
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int p : prices) {
            maxProfit = Math.max(maxProfit, p - minPrice);
            minPrice = Math.min(minPrice, p);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        LC0121 so = new LC0121();
        int[] prices = {7,6,4,3,1};
        int res = so.maxProfit(prices);
        System.out.println(res);
    }
}
