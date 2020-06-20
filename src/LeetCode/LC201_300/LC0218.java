package LeetCode.LC201_300;

import java.util.*;

/**
 * The Skyline Problem
 *
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A),
 * write a program to output the skyline formed by these buildings collectively (Figure B).
 *
 * Buildings Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
 * where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively,
 * and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment.
 * Note that the last key point, where the rightmost building ends,
 * is merely used to mark the termination of the skyline, and always has zero height.
 * Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
 * the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
public class LC0218 {
    static class Point implements Comparable<Point>{
        public int position;
        public int height;
        public boolean isLeft;

        public Point(int position, int height, boolean isLeft) {
            this.position = position;
            this.height = height;
            this.isLeft = isLeft;
        }

        @Override
        public int compareTo(Point other) {
            if (position == other.position) {
                if (isLeft && other.isLeft) {
                    return other.height - height;
                } else if (!isLeft && !other.isLeft) {
                    return height - other.height;
                } else {
                    return isLeft ? -1 : 1;
                }
            } else {
                return position - other.position;
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 ||
                buildings[0] == null || buildings[0].length == 0) {
            return res;
        }

        List<Point> points = new ArrayList<>();
        for (int[] build : buildings) {
            points.add(new Point(build[0], build[2], true));
            points.add(new Point(build[1], build[2], false));
        }

        Collections.sort(points);

        PriorityQueue<Integer> maxHeight = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (Point p : points) {
            if (p.isLeft) {
                if (maxHeight.isEmpty() || p.height > maxHeight.peek()) {
                    List<Integer> drawPoint = new ArrayList<>();
                    drawPoint.add(p.position);
                    drawPoint.add(p.height);
                    res.add(drawPoint);
                }

                maxHeight.add(p.height);
            } else {
                maxHeight.remove(p.height);

                if (maxHeight.isEmpty() || p.height > maxHeight.peek()) {
                    List<Integer> drawPoint = new ArrayList<>();
                    drawPoint.add(p.position);
                    drawPoint.add(maxHeight.isEmpty() ? 0 : maxHeight.peek());
                    res.add(drawPoint);
                }
            }
        }

        return res;
    }

    static class TreeSetPoint implements Comparable<TreeSetPoint>{
        public int position;
        public int height;
        public int index;
        public boolean isLeft;

        public TreeSetPoint(int position, int height, boolean isLeft, int index) {
            this.position = position;
            this.height = height;
            this.isLeft = isLeft;
            this.index = index;
        }

        @Override
        public int compareTo(TreeSetPoint other) {
            if (position == other.position) {
                if (isLeft && other.isLeft) {
                    return other.height - height;
                } else if (!isLeft && !other.isLeft) {
                    return height - other.height;
                } else {
                    return isLeft ? -1 : 1;
                }
            } else {
                return position - other.position;
            }
        }
    }

    public List<List<Integer>> getSkylineBST(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 ||
                buildings[0] == null || buildings[0].length == 0) {
            return res;
        }

        List<TreeSetPoint> points = new ArrayList<>();
        List<TreeSetPoint> leftPoints = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] build = buildings[i];
            points.add(new TreeSetPoint(build[0], build[2], true, i));
            points.add(new TreeSetPoint(build[1], build[2], false, i));
            leftPoints.add(new TreeSetPoint(build[0], build[2], true, i));
        }

        Collections.sort(points);

        TreeSet<Integer> maxHeight = new TreeSet<>((o1, o2)
                -> leftPoints.get(o1).height != leftPoints.get(o2).height
                ? leftPoints.get(o2).height - leftPoints.get(o1).height
                : o1 - o2);

        for (TreeSetPoint p : points) {
            if (p.isLeft) {
                if (maxHeight.isEmpty() || p.height > leftPoints.get(maxHeight.first()).height) {
                    List<Integer> drawPoint = new ArrayList<>();
                    drawPoint.add(p.position);
                    drawPoint.add(p.height);
                    res.add(drawPoint);
                }

                maxHeight.add(p.index);
            } else {
                maxHeight.remove(p.index);

                if (maxHeight.isEmpty() || p.height > leftPoints.get(maxHeight.first()).height) {
                    List<Integer> drawPoint = new ArrayList<>();
                    drawPoint.add(p.position);
                    drawPoint.add(maxHeight.isEmpty() ? 0 : leftPoints.get(maxHeight.first()).height);
                    res.add(drawPoint);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LC0218 so = new LC0218();
        int[][] buildings = new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> res = so.getSkyline(buildings);
        System.out.println(res);
        res = so.getSkylineBST(buildings);
        System.out.println(res);
    }
}
