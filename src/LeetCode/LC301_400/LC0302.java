package LeetCode.LC301_400;

/**
 * Smallest Rectangle Enclosing Black Pixels
 *
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
 * The black pixels are connected, i.e., there is only one black region.
 * Pixels are connected horizontally and vertically.
 * Given the location (x, y) of one of the black pixels,
 * return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * Example:
 *
 * Input:
 * [
 *   "0010",
 *   "0110",
 *   "0100"
 * ]
 * and x = 0, y = 2
 *
 * Output: 6
 */
public class LC0302 {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0){
            return 0;
        }

        int width = findRightBoundary(image, x, y) - findLeftBoundary(image, x, y) + 1;
        int height = findLowerBoundary(image, x, y) - findUpperBoundary(image, x, y) + 1;
        return width * height;
    }

    private int findLeftBoundary(char[][] matrix, int x, int y) {
        int start = 0;
        int end = y;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (allZeroCol(matrix, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    private int findRightBoundary(char[][] matrix, int x, int y) {
        int start = y;
        int end = matrix[0].length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (allZeroCol(matrix, mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }

    private int findUpperBoundary(char[][] matrix, int x, int y) {
        int start = 0;
        int end = x;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (allZeroRow(matrix, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }

    private int findLowerBoundary(char[][] matrix, int x, int y) {
        int start = x;
        int end = matrix.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (allZeroRow(matrix, mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }

    private boolean allZeroCol(char[][] matrix, int col) {
        for (char[] row : matrix) {
            if (row[col] == '1') {
                return false;
            }
        }
        return true;
    }

    private boolean allZeroRow(char[][] matrix, int row) {
        for (char c : matrix[row]) {
            if (c == '1') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC0302 so = new LC0302();
        char[][] image = new char[][] {{'0', '0', '1', '0'}, {'0','1', '1', '0'}, {'0', '1', '0', '0'}};
        int x = 0;
        int y = 2;

        int res = so.minArea(image, x, y);
        System.out.println(res);
    }
}
