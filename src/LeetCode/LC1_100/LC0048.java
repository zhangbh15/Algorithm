package LeetCode.LC1_100;


/**
 * Rotate Image
 *
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * Example 2:
 *
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */
public class LC0048 {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0
                || matrix.length != matrix[0].length) {
            return;
        }

        int offset = 0;
        int size = matrix.length;
        while (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                int temp = matrix[offset][offset + i];
                matrix[offset][offset + i] = matrix[offset + size - 1 - i][offset];
                matrix[offset + size - 1 - i][offset] = matrix[offset + size - 1][offset + size - 1 - i];
                matrix[offset + size - 1][offset + size - 1 - i] = matrix[offset + i][offset + size - 1];
                matrix[offset + i][offset + size - 1] = temp;
            }
            size -= 2;
            offset++;
        }
    }
}
