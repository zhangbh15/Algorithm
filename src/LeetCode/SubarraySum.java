package LeetCode;

public class SubarraySum {
    /**
     * Given an all-positive int array and an int target,
     * check if there exist two non-overlapping subarrays with the sums equal to target.
     * @return true if there exist such subarrays.
     */
    private int end;

    public boolean sumTarget(int[] array, int target, int start) {
        if (array == null || array.length == 0) {
            return false;
        }

        end = start + 1;
        long sum = array[0];

        while (end < array.length) {
            while (sum < target) {
                sum += array[end++];
            }

            while (sum > target){
                sum -= array[start++];
            }

            if (sum == target) {
                return true;
            }
        }

        return sum == target;
    }

    public boolean twoSumTarget(int[] array, int target) {
        if (array == null || array.length < 2) {
            return false;
        }

        if (!sumTarget(array, target, 0)) {
            return false;
        }

        return sumTarget(array, target, end);
    }

    public static void main(String[] args) {
        SubarraySum so = new SubarraySum();
        int[] array = new int[] {1, 2, 1};
        int target = 3;
        boolean res = so.twoSumTarget(array, target);
        System.out.println(res);
    }
}
