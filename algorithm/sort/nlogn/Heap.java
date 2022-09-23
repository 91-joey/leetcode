package org.example.leetcode.problems.algorithm.sort.nlogn;

import org.example.leetcode.problems.algorithm.sort.Swap;

public class Heap {
    public static void main(String[] args) {
        Swap.sort(Heap::heapSort);
    }

    public static void heapSort(int[] arr) {
        buildMaxHeap(arr);
        for (int heapSize = arr.length - 1; heapSize > 0; heapSize--) {
            swap(arr, 0, heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    private static void buildMaxHeap(int[] arr) {
        int length = arr.length;
        for (int i = length / 2 - 1; i >= 0; i--) heapify(arr, i, length);
    }

    private static void heapify(int[] arr, int root, int heapSize) {
        int l = 2 * root + 1;
        int r = l + 1;
        int maxIdx = root;
        if (l < heapSize) {
            if (arr[maxIdx] < arr[l]) maxIdx = l;
            if (r < heapSize && arr[maxIdx] < arr[r]) maxIdx = r;
        }
        if (maxIdx != root) {
            swap(arr, root, maxIdx);
            heapify(arr, maxIdx, heapSize);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
