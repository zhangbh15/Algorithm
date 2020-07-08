package LeetCode.LC301_400;

import javax.print.attribute.IntegerSyntax;
import java.util.*;

/**
 * Kth Smallest Element in a Sorted Matrix
 *
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class LC0378 {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}};

    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        Queue<Integer> heap = new PriorityQueue<>(
                Comparator.comparingInt(o -> matrix[o / cols][o % cols]));
        Set<Integer> visited = new HashSet<>();
        heap.offer(0);
        visited.add(0);

        for (int i = 0; i < k - 1; i++) {
            int cur = heap.poll();
            for (int[] dir : DIRECTIONS) {
                int ii = cur / cols + dir[0];
                int jj = cur % cols + dir[1];
                int neighbor = ii * cols + jj;
                if (ii < rows && jj < cols && visited.add(neighbor)) {
                    heap.offer(neighbor);
                }
            }
        }

        int resIdx = heap.poll();
        return matrix[resIdx / cols][resIdx % cols];
    }


    // with customized data structure
    public int kthSmallestCell(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        Queue<Cell> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        Set<Cell> visited = new HashSet<>();
        Cell start = new Cell(matrix[0][0], 0, 0);
        heap.offer(start);
        visited.add(start);

        for (int i = 0; i < k - 1; i++) {
            Cell cur = heap.poll();
            if (cur.i + 1 < rows) {
                int ii = cur.i + 1;
                int jj = cur.j;
                Cell neighbor = new Cell(matrix[ii][jj], ii, jj);
                if (visited.add(neighbor)) {
                    heap.offer(neighbor);
                }
            }
            if (cur.j + 1 < cols) {
                int ii = cur.i;
                int jj = cur.j + 1;
                Cell neighbor = new Cell(matrix[ii][jj], ii, jj);
                if (visited.add(neighbor)) {
                    heap.offer(neighbor);
                }
            }
        }

        return heap.peek().val;
    }

    private static class Cell {
        private final int val;
        private final int i;
        private final int j;

        public Cell(int val, int i, int j) {
            this.val = val;
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            return i * 31 + j;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Cell) {
                Cell other = (Cell) o;
                return this.i == other.i && this.j == other.j;
            } else {
                return false;
            }
        }
    }
}
