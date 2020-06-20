package LeetCode.LC301_400;
import LeetCode.LC101_200.LC0188;

import java.util.*;

/**
 * Best Time to Buy and Sell Stock with Cooldown
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times)
 * with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 *
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class LC0309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = - prices[0];
        buy[1] = Math.max(buy[0], - prices[1]);
        sell[1] = Math.max(sell[0], prices[1] + buy[0]);
        for (int i = 2; i < len; i++) {
            sell[i] = Math.max(sell[i - 1], prices[i] + buy[i - 1]);
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
        }
        return sell[len - 1];
    }

    public int o1Space(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int prevSell = 0;
        int sell = 0;
        int buy = - prices[0];

        for (int p : prices) {
            int nextSell = Math.max(sell, p + buy);
            int nextBuy = Math.max(buy, prevSell - p);
            prevSell = sell;
            sell = nextSell;
            buy = nextBuy;
        }
        return sell;
    }

    public static void main(String[] args) {
        LC0309 so = new LC0309();
        int[] prices = {1,2,3,0,2};
        // int res = so.maxProfit(prices);
        int res = so.o1Space(prices);
        System.out.println(res);
    }
}
