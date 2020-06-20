package LeetCode;

import java.util.*;

public class MergeSort {

    public List<Integer> mergeSort(List<Integer> list, boolean extraSpace) {
        if (list == null || list.size() <= 1) {
            return list;
        }

        if (extraSpace) {
            return divideAndMerge(list, 0, list.size() - 1);
        } else {
            List<Integer> helper = new ArrayList<>(list);
            divideAndMerge(list, 0, list.size() - 1, helper);
            return list;
        }
    }

    // O(n*log(n)) space
    private List<Integer> divideAndMerge(List<Integer> list, int start, int end) {
        if (start == end) {
            List<Integer> res = new ArrayList<>();
            res.add(list.get(start));
            return res;
        }

        int mid = start + (end - start) / 2;

        List<Integer> leftList = divideAndMerge(list, start, mid);
        List<Integer> rightList = divideAndMerge(list, mid + 1, end);

        return merge(leftList, rightList);
    }

    private List<Integer> merge(List<Integer> leftList, List<Integer> rightList){
        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i) <= rightList.get(j)) {
                res.add(leftList.get(i++));
            } else {
                res.add(rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            res.add(leftList.get(i++));
        }

        while (j < rightList.size()) {
            res.add(rightList.get(j++));
        }

        return res;
    }

    // O(n) space
    private void divideAndMerge(List<Integer> list, int start, int end, List<Integer> helper) {
        if (start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        divideAndMerge(list, start, mid, helper);
        divideAndMerge(list, mid + 1, end, helper);

        merge(list, start, mid + 1, end, helper);
    }

    private void merge(List<Integer> list, int leftStart, int rightStart, int end, List<Integer> helper) {
        int i = 0;
        int j = 0;

        while (leftStart + i < rightStart && rightStart + j <= end) {
            if (list.get(leftStart + i) <= list.get(rightStart + j)) {
                helper.set(leftStart + i + j, list.get(leftStart + i));
                i++;
            } else {
                helper.set(leftStart + i + j, list.get(rightStart + j));
                j++;
            }
        }

        while (i < rightStart - leftStart) {
            helper.set(leftStart + i + j, list.get(leftStart + i));
            i++;
        }

        for (int k = 0; k < rightStart - leftStart + j; k++) {
            list.set(leftStart + k, helper.get(leftStart + k));
        }
    }

    public static void main(String[] args) {
        MergeSort so = new MergeSort();
        List<Integer> empty = new ArrayList<>();
        List<Integer> sizeOne = Arrays.asList(1);
        List<Integer> sizeTwo = Arrays.asList(2, 1);
        List<Integer> sizeThree = Arrays.asList(3, 1, 2);
        List<Integer> sizeTen = Arrays.asList(2, 0, 6, 2, 2, 9, 5, 9, 3, 0);

        System.out.println(so.mergeSort(empty, true));
        System.out.println(so.mergeSort(sizeOne, true));
        System.out.println(so.mergeSort(sizeTwo, true));
        System.out.println(so.mergeSort(sizeThree, true));
        System.out.println(so.mergeSort(sizeTen, true));

        System.out.println(so.mergeSort(empty, false));
        System.out.println(so.mergeSort(sizeOne, false));
        System.out.println(so.mergeSort(sizeTwo, false));
        System.out.println(so.mergeSort(sizeThree, false));
        System.out.println(so.mergeSort(sizeTen, false));
    }
}
