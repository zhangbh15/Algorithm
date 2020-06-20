package LeetCode.LC101_200;

import java.util.*;

/**
 * Repeated DNA Sequences
 *
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * Example:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class LC0187 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new LinkedList<>();
        if (s == null || s.length() <= 10) {
            return res;
        }

        int window = 0;
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            window = (window << 2) & 0xfffff | convert(s.charAt(i));
            if (i < 9) {
                continue;
            }

            Boolean flag = map.get(window);
            if (flag == null) { // first meeting
                map.put(window, false);
            } else if (!flag) { // second meeting
                res.add(s.substring(i - 9, i + 1));
                map.put(window, true);
            } // else {continue;} third or more meeting
        }

        return res;
    }

    private int convert(char ch) {
        if (ch == 'A') {
            return 0;
        } else if (ch == 'C') {
            return 1;
        } else if (ch == 'G') {
            return 2;
        } else if (ch == 'T') {
            return 3;
        }
        throw new RuntimeException("Unsupported character.");
    }

    public static void main(String[] args) {
        LC0187 so = new LC0187();
        String input = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> res = so.findRepeatedDnaSequences(input);
        System.out.println(res);
    }
}
