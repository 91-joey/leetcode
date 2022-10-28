package org.example.leetcode.problems._2_algorithm.sort.algorithm.nlogn;

import org.example.leetcode.problems._2_algorithm.sort.algorithm.Swap;

public class Merge {
    public static void main(String[] args) {
        Swap.sort(Merge::mergeSort);
    }

    //归并排序（即对2个已排序子数组递归合并）  nlogn   n
    public static void mergeSort(int[] arr) {
        int[] sorted = mergeSort(arr, 0, arr.length - 1);
        System.arraycopy(sorted, 0, arr, 0, arr.length);
    }

    private static int[] mergeSort(int[] arr, int start, int end) {
        if (start == end) return new int[]{arr[start]};
        int mid = (start + end) >> 1;
        return merge(mergeSort(arr, start, mid), mergeSort(arr, mid + 1, end));
    }

    private static int[] merge(int[] left, int[] right) {
        int lengthL = left.length;
        int lengthR = right.length;
        int[] mergeSorted = new int[lengthL + lengthR];

        int i = 0, l = 0, r = 0;
        for (; l < lengthL && r < lengthR; i++) {
            if (left[l] <= right[r]) mergeSorted[i] = left[l++];
            else mergeSorted[i] = right[r++];
        }

        while (l < lengthL) mergeSorted[i++] = left[l++];
        while (r < lengthR) mergeSorted[i++] = right[r++];

        return mergeSorted;
    }

}
