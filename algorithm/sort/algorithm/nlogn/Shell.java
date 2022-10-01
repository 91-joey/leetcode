package org.example.leetcode.problems.algorithm.sort.algorithm.nlogn;

import org.example.leetcode.problems.algorithm.sort.algorithm.Swap;

//希尔排序
public class Shell {
    public static void main(String[] args) {
//        Swap.sort(Shell::shellSort1);
//        Swap.sort(Shell::shellSort2);
        Swap.sort(Shell::shellSort_Knuth);
    }

    public static void shellSort1(int[] arr) {
        int length = arr.length;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int groupIdx = 0; groupIdx < gap; groupIdx++) {
                for (int i = groupIdx + gap; i < length; i += gap) {
                    int cur = arr[i];
                    int pre = i - gap;
                    for (; groupIdx <= pre && arr[pre] > cur; pre -= gap)
                        arr[pre + gap] = arr[pre];
                    arr[pre + gap] = cur;
                }
            }
        }
    }

    public static void shellSort2(int[] arr) {
        int length = arr.length;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; i++) {
                int cur = arr[i];
                int pre = i - gap;
                for (; 0 <= pre && arr[pre] > cur; pre -= gap)
                    arr[pre + gap] = arr[pre];
                arr[pre + gap] = cur;
            }
        }
    }

    public static void shellSort_Knuth(int[] arr) {
        int length = arr.length;
        int maxGap = 1;
        while (maxGap * 3 + 1 <= length - 2) maxGap = maxGap * 3 + 1;
        for (int gap = maxGap; gap > 0; gap = (gap - 1) / 3) {
            for (int i = gap; i < length; i++) {
                int cur = arr[i];
                int pre = i - gap;
                for (; 0 <= pre && arr[pre] > cur; pre -= gap)
                    arr[pre + gap] = arr[pre];
                arr[pre + gap] = cur;
            }
        }
    }
}
