package LeetCode;

import java.util.*;

public class QuickSort {
    public int[] quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = array[end];
        int left = start;
        int right = end - 1;

        while (left <= right) {
            if (array[left] < pivot) {
                left++;
            } else if (array[right] >= pivot) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }

        swap(array, left, end);
        quickSort(array, start, left - 1);
        quickSort(array, left + 1, end);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort so = new QuickSort();

        int[] empty = new int[] {};
        int[] sizeOne = new int[] {1};
        int[] sizeTwo = new int[] {2, 1};
        int[] sizeThree = new int[]{3, 1, 2};
        int[] sizeTen = new int[]{2, 0, 6, 2, 2, 9, 5, 9, 3, 0};

        System.out.println(Arrays.toString(so.quickSort(empty)));
        System.out.println(Arrays.toString(so.quickSort(sizeOne)));
        System.out.println(Arrays.toString(so.quickSort(sizeTwo)));
        System.out.println(Arrays.toString(so.quickSort(sizeThree)));
        System.out.println(Arrays.toString(so.quickSort(sizeTen)));
    }
}
