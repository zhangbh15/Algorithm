package LeetCode.LC1_100;

/**
 * Pow(x, n)
 *
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 *
 * Example 1:
 *
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 *
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */
public class LC0050 {
    public double myPow(double x, int n) {
        if (x == 0 || x == 1) {
            return x;
        }

        if (n < 0) {
            return helper(1 / x, -(long) n);
        } else {
            return helper(x, n);
        }
    }

    private double helper(double x, long n) {
        if (n == 0) {
            return 1;
        }

        double half = helper(x, n / 2);
        double res = half * half;
        if (n % 2 == 1) {
            res *= x;
        }

        return res;
    }
}
