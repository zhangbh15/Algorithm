package Amazon;

import java.util.HashMap;
import java.util.Map;

public class SplitIntoPrimes {
    public int splitWays(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int len = str.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        Map<Integer, Boolean> primes = new HashMap<>();

        for (int i = len - 1; i >= 0; i--) {
            int val = 0;
            for (int j = i; j < len; j++) {
                val = val * 10 + str.charAt(j) - '0';
                if (val < 0) {
                    break;
                }

                if (isPrime(val, primes)) {
                    dp[i] += dp[j + 1];
                }
            }
        }

        return dp[0];
    }

    private boolean isPrime(int val, Map<Integer, Boolean> primes) {
        if (primes.containsKey(val)) {
            return primes.get(val);
        }
        if (val <= 3) {
            return val > 1;
        }

        for (int i = 2; i <= Math.sqrt(val); i++) {
            if (val % i == 0) {
                primes.put(val, false);
                return false;
            }
        }

        primes.put(val, true);
        return true;
    }

    public static void main(String[] args) {
        SplitIntoPrimes so = new SplitIntoPrimes();
        String str = "11373";
        String str2 = "2222";
        String str3 = "142857";
        String str4 = "13131313131311313131313131313";
        String str5 = "3175";

        System.out.println(so.splitWays(str));
        System.out.println(so.splitWays(str2));
        System.out.println(so.splitWays(str3));
        System.out.println(so.splitWays(str4));
        System.out.println(so.splitWays(str5));
    }
}
