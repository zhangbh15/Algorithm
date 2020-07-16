package LeetCode.LC201_300;

import java.util.LinkedList;
import java.util.List;

/**
 * Integer to English Words
 *
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 231 - 1.
 *
 * Example 1:
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven
 * Thousand Eight Hundred Ninety One"
 */
public class LC0273 {
    public String numberToWords(int num) {
        if (num < 0) {
            return null;
        }
        if (num == 0) {
            return "Zero";
        }

        List<Integer> chunks = new LinkedList<>();
        while (num != 0) {
            chunks.add(num % 1000);
            num /= 1000;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = chunks.size() - 1; i >= 0; i--) {
            sb.append(parseChunk(chunks.get(i)));
            switch (i) {
                case 0: sb.setLength((sb.length() - 1));
                    break;
                case 1:
                    if (chunks.get(i) != 0) {
                        sb.append("Thousand ");
                    }
                    break;
                case 2:
                    if (chunks.get(i) != 0) {
                        sb.append("Million ");
                    }
                    break;
                case 3:
                    if (chunks.get(i) != 0) {
                        sb.append("Billion ");
                    }
                    break;
                default: throw new IllegalArgumentException("Number is too large");
            }
        }

        return sb.toString();
    }

    private String parseChunk(int chunk) {
        int[] num = new int[3];
        for (int i = 0; i < 3; i++) {
            num[i] = chunk % 10;
            chunk /= 10;
        }

        StringBuilder sb = new StringBuilder();
        if (num[2] != 0) {
            sb.append(parseOneDigit(num[2]));
            sb.append("Hundred ");
        }

        if (num[1] != 0) {
            switch (num[1]) {
                case 1:
                    switch (num[0]) {
                        case 0: sb.append("Ten ");
                            break;
                        case 1: sb.append("Eleven ");
                            break;
                        case 2: sb.append("Twelve ");
                            break;
                        case 3: sb.append("Thirteen ");
                            break;
                        case 4: sb.append("Fourteen ");
                            break;
                        case 5: sb.append("Fifteen ");
                            break;
                        case 6: sb.append("Sixteen ");
                            break;
                        case 7: sb.append("Seventeen ");
                            break;
                        case 8: sb.append("Eighteen ");
                            break;
                        case 9: sb.append("Nineteen ");
                            break;
                    }
                    return sb.toString();
                case 2: sb.append("Twenty ");
                    break;
                case 3: sb.append("Thirty ");
                    break;
                case 4: sb.append("Forty ");
                    break;
                case 5: sb.append("Fifty ");
                    break;
                case 6: sb.append("Sixty ");
                    break;
                case 7: sb.append("Seventy ");
                    break;
                case 8: sb.append("Eighty ");
                    break;
                case 9: sb.append("Ninety ");
                    break;
            }
        }

        if (num[0] != 0) {
            sb.append(parseOneDigit(num[0]));
        }

        return sb.toString();
    }

    private String parseOneDigit(int num) {
        switch (num) {
            case 1: return "One ";
            case 2: return "Two ";
            case 3: return "Three ";
            case 4: return "Four ";
            case 5: return "Five ";
            case 6: return "Six ";
            case 7: return "Seven ";
            case 8: return "Eight ";
            case 9: return "Nine ";
            default: return ""; // 0 or too large number
        }
    }

    public static void main(String[] args) {
        LC0273 so = new LC0273();
        int num = 400882382;
        String res = so.numberToWords(num);
        System.out.println(res);
    }
}
