package LeetCode.LC1_100;

/**
 * Sqrt(x)
 *
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 *
 * Example 1:
 *
 * Input: 4
 * Output: 2
 * Example 2:
 *
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 *              the decimal part is truncated, 2 is returned. *
 */
public class LC0069 {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int left = 1;
        int right = x / 2;

        while (left  <= right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid && mid + 1 > x / (mid + 1) ) {
                return mid;
            }

            if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        throw new RuntimeException("Should never happen.");
    }

    /**
     * @param x a non-negative integer
     * @return sqrt(x) with two decimal places
     */
    public String twoDecimal(int x) {
        if (x == 0) {
            return "0.00";
        }
        if (x == 1) {
            return "1.00";
        }

        int intPart = mySqrt(x);
        StringBuilder sb = new StringBuilder();
        sb.append(intPart);
        sb.append('.');

        int point1 = nextDigit(intPart, x, 0.1);
        sb.append(point1);
        double cur = intPart + 0.1 * point1;
        sb.append(nextDigit(cur, x, 0.01));

        return sb.toString();
    }

    private int nextDigit(double cur, int x, double digit) {
        int left = 0;
        int right = 9;

        while (left  <= right) {
            int mid = left + (right - left) / 2;
            double val = cur + digit * mid;

            if (val <= x / val && val + digit > x / (val + digit)) {
                return mid;
            }

            if (val <= x / val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        throw new RuntimeException("Should never happen.");
    }

    public static void main(String[] args) {
        LC0069 so = new LC0069();
        for (int i = 0; i < 10; i++) {
            System.out.println(so.mySqrt(i));
            System.out.println(so.twoDecimal(i));
        }

        System.out.print(so.mySqrt(Integer.MAX_VALUE));
        System.out.print(so.twoDecimal(Integer.MAX_VALUE));
    }
}
