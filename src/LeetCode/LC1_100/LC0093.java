package LeetCode.LC1_100;

import java.util.*;

/**
 * Restore IP Addresses
 *
 * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s.
 * You can return them in any order.
 *
 * A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by
 * single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses
 * and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "1111"
 * Output: ["1.1.1.1"]
 * Example 4:
 *
 * Input: s = "010010"
 * Output: ["0.10.0.10","0.100.1.0"]
 * Example 5:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3000
 * s consists of digits only.
 */
public class LC0093 {
    public List<String> restoreIpAddresses(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }

        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }

        dfs(res, new StringBuilder(), s, 0);
        return res;
    }

    private void dfs(List<String> res, StringBuilder path, String s, int idx) {
        if (idx == s.length() && path.length() == s.length() + 4) {
            path.setLength(path.length() - 1);
            res.add(path.toString());
            return;
        }
        if (idx == s.length()) {
            return;
        }

        int val = 0;
        int setBackLen = path.length();
        for (int i = idx; i < s.length(); i++) {
            val = val * 10 + s.charAt(i) - '0';
            if (val > 255) {
                break;
            }

            path.append(val).append('.');
            dfs(res, path, s, i + 1);
            path.setLength(setBackLen);

            if (val == 0) {
                break;
            }
        }
    }



    public static void main(String[] args) {
        LC0093 so = new LC0093();
        String s = "0011255245";
        List<String> res = so.restoreIpAddresses(s);
        System.out.println(res);
    }
}
