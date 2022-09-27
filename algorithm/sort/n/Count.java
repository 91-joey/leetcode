package org.example.leetcode.problems.algorithm.sort.n;

import org.example.leetcode.problems.algorithm.sort.Swap;

//计数排序  n+range n+range
public class Count {
    public static void main(String[] args) {
//        Swap.sort(Count::countSort);
//        Swap.sort(Count::countSort2);
        Swap.sort(Count::countSort3);
    }
    //伪计数排序
    public static void countSort(int[] arr) {

        CountingAndMin countingAndMin = count(arr);
        int[] counting = countingAndMin.counting;
        int min = countingAndMin.min;

        //依次遍历，得到结果
        for (int i = 0, cntIdx = 0; i < arr.length; cntIdx++) {
            int val = cntIdx + min;
            while (counting[cntIdx] != 0) {
                arr[i++] = val;
                counting[cntIdx]--;
            }
        }
    }

    //真计数排序 之 顺序遍历
    public static void countSort2(int[] arr) {

        CountingAndMin countingAndMin = count(arr);
        int[] counting = countingAndMin.counting;
        int min = countingAndMin.min;

        int length = arr.length;
        /*
        int prevCnt = length;
        for (int i = counting.length - 1; i >= 0; i--) {
            if (counting[i] != 0) {
                prevCnt -= counting[i];
                counting[i] = prevCnt;
            }
        }
        */

        //记录每个元素的「起始索引」
        int range = counting.length;
        counting[range - 1] = length - counting[range - 1];
        for (int i = range - 2; i >= 0; i--)
            counting[i] = counting[i + 1] - counting[i];

        int[] sorted = new int[length];
        for (int i : arr)
            sorted[counting[i - min]++] = i;

        System.arraycopy(sorted, 0, arr, 0, length);
    }


    //真计数排序 之 倒序遍历
    public static void countSort3(int[] arr) {

        CountingAndMin countingAndMin = count(arr);
        int[] counting = countingAndMin.counting;
        int min = countingAndMin.min;

        int length = arr.length;

        //记录每个元素的「终止索引」
        counting[0]--;
        for (int i = 1; i < counting.length; i++) {
            counting[i] += counting[i - 1];
        }

        int[] sorted = new int[length];
        //倒序遍历确保稳定性
        for (int i = length - 1; i >= 0; i--)
            sorted[counting[arr[i] - min]--] = arr[i];

        System.arraycopy(sorted, 0, arr, 0, length);
    }

    //计数
    public static CountingAndMin count(int[] arr) {
        //确定计数范围
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        int[] counting = new int[max - min + 1];

        //计数
        for (int i : arr)
            counting[i - min]++;

        return new CountingAndMin(counting, min);
    }

    static class CountingAndMin {
        int[] counting;
        int min;

        public CountingAndMin(int[] counting, int min) {
            this.counting = counting;
            this.min = min;
        }
    }

}
