package LeetCode.LC101_200;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Max Points on a Line
 *
 * Given n points on a 2D plane, find the maximum number of points that
 * lie on the same straight line.
 *
 * Example 1:
 *
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 * Example 2:
 *
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * NOTE: input types have been changed on April 15, 2019. Please reset to
 * default code definition to get new method signature.
 */
public class LC0149 {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0
                || points[0] == null || points[0].length == 0) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }

        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int len = points.length;
        int max = 0;
        for (int i = 0; i < len - 1; i++) {
            Map<Double, Integer> cnt = new HashMap<>();
            int x1 = points[i][0];
            int y1 = points[i][1];
            int cntFixed = 1;
            for (int j = i + 1; j < len; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    cntFixed++;
                } else {
                    Double slope = x1 == x2 ? null :
                            (double) (y2 - y1) / (x2 - x1);
                    cnt.put(slope, cnt.getOrDefault(slope, 0) + 1);
                }
            }

            int maxPassingFixed = 0;
            for (int n : cnt.values()) {
                maxPassingFixed = Math.max(maxPassingFixed, n);
            }
            max = Math.max(max, maxPassingFixed + cntFixed);

            if (maxPassingFixed >= len - 1 - i) {
                break;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LC0149 so = new LC0149();
        int[][] points = {{2, 3}, {3, 3}, {-5, 3}};
        int res = so.maxPoints(points);
        System.out.println(res);
    }
}
