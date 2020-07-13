package LeetCode.LC101_200;


import java.util.ArrayList;
import java.util.List;

/**
 * Compare Version Numbers
 *
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 *
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 *
 * The . character does not represent a decimal point and is used to separate number sequences.
 *
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level
 * revision of the second first-level revision.
 *
 * You may assume the default revision number for each level of a version number to be 0. For example,
 * version number 3.4 has a revision number of 3 and 4 for its first and second level revision number.
 * Its third and fourth level revision number are both 0.
 *
 *
 *
 * Example 1:
 *
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Example 2:
 *
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 * Example 3:
 *
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 * Example 4:
 *
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
 * Example 5:
 *
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: The first version number does not have a third level revision number, which means its
 * third level revision number is default to "0"
 *
 *
 * Note:
 *
 * Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
 * Version strings do not start or end with dots, and they will not be two consecutive dots.
 */
public class LC0165 {
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null
                || version1.length() == 0 || version2.length() == 0) {
            return 0;
        }

        List<Integer> verList1 = parse(version1);
        List<Integer> verList2 = parse(version2);

        for (int i = 0; i < Math.min(verList1.size(), verList2.size()); i++) {
            if (verList1.get(i) < verList2.get(i)) {
                return -1;
            }
            if (verList1.get(i) > verList2.get(i)) {
                return 1;
            }
        }

        return Integer.compare(verList1.size(), verList2.size());
    }

    private List<Integer> parse(String version) {
        List<Integer> res = new ArrayList<>();
        int sect = 0;
        for (char ch : version.toCharArray()) {
            if (ch == '.') {
                res.add(sect);
                sect = 0;
                continue;
            }
            sect = sect * 10 + (ch - '0');
        }
        res.add(sect);

        for (int i = res.size() - 1; i >= 0; i--) {
            if (res.get(i) != 0) {
                break;
            }
            res.remove(i);
        }

        return res;
    }


    // String.split(), Integer.parseInt()
    public int compareVersion2(String version1, String version2) {
        // cc
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");

        int len1 = ver1.length;
        int len2 = ver2.length;
        for ( int i = 0; i < Math.max(len1, len2); i++) {
            int val1 = i < len1 ? Integer.parseInt(ver1[i]) : 0;
            int val2 = i < len2 ? Integer.parseInt(ver2[i]) : 0;
            if (val1 != val2) {
                return val1 < val2 ? -1 : 1;
            }
        }
        return 0;
    }
}
