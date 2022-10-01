package org.example.leetcode.problems.algorithm.sort.algorithm.nn;

import org.example.leetcode.problems.algorithm.sort.algorithm.Swap;

public class Insertion {
    public static void main(String[] args) {
//        Swap.sort(InsertSort::insertSort_swap);
        Swap.sort(Insertion::insertSort_shift);
    }

    public static void insertSort_swap(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            for (int j = i; j >= 1 && arr[j - 1] > arr[j]; j--)
                Swap.swap(arr, j - 1, j);
//        for (int i = 0; i < arr.length - 1; i++)
//            for (int j = i; j >= 0 && arr[j] > arr[j + 1]; j--)
//                Swap.swap(arr, j, j + 1);
    }

    public static void insertSort_shift(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int cur = arr[j];
            for (; j >= 1 && arr[j - 1] > cur; j--)
                arr[j] = arr[j - 1];
            arr[j] = cur;
        }
    }
}
