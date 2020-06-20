package LeetCode.LC201_300;

public class LC0221 {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        dp[0][0] = matrix[0][0] == '1' ? 1 : 0;

        int maxSize = dp[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxSize = Math.max(maxSize, dp[i][0]);
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            maxSize = Math.max(maxSize, dp[0][j]);
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = 0;
                }
                maxSize = Math.max(maxSize, dp[i][j]);
            }
        }
        return maxSize * maxSize;
    }
}
