package org.example.leetcode.problems.algorithm.sort.n;

import java.util.Arrays;

public class Radix {
    public static void main(String[] args) {
//        Swap.sort(Radix::radixSort);
//        radixSort_LSD(new int[]{3424124, 232, -121, -1245, -999, 1});
//        Swap.sort(Radix::radixSort_MSD);
        radixSort_MSD(new int[]{3424124, 232, -121, -1245, -999, 1});
    }

    //从低位到高位（LSD: Least Significant Digit）  O(d(n+k))(d 表示最长数字的位数，k 表示每个基数可能的取值范围大小)
    public static void radixSort_LSD(int[] arr) {
        int maxDigitLength = getMaxDigitLength(arr);

        int[] counting = new int[19];
        int length = arr.length;
        int[] sorted = new int[length];
        //从低位到高位（LSD: Least Significant Digit）基于「计数排序」依次对数组排序
        for (int i = 0, cur = 1; i < maxDigitLength; i++, cur *= 10) {
            //计数
            for (int e : arr) {
                int digit = e / cur % 10;
                counting[digit + 9]++;
            }

            //计算最高索引
            counting[0]--;
            for (int j = 1; j < counting.length; j++)
                counting[j] += counting[j - 1];

            //倒序遍历排序
            for (int j = length - 1; j >= 0; j--) {
                int digit = arr[j] / cur % 10;
                sorted[counting[digit + 9]--] = arr[j];
            }

            //拷贝数组
            System.arraycopy(sorted, 0, arr, 0, length);
            //将计数数组重置为 0
            Arrays.fill(counting, 0);
        }
    }

    //MSD
    public static void radixSort_MSD(int[] arr) {
        radixSort(arr, 0, arr.length - 1, getMaxDigitLength(arr) - 1);
    }

    private static void radixSort(int[] arr, int start, int end, int position) {
        if (end <= start || position == -1)
            return;

        int range = 19;
        int[] counting = new int[range];
        int length = end - start + 1;
        int[] sorted = new int[length];
        int dev = (int) Math.pow(10, position);
        //计数
        for (int i = start; i <= end; i++) {
            int digit = arr[i] / dev % 10;
            counting[digit + 9]++;
        }

        //计算（最高索引 + 1）
        for (int j = 1; j < range; j++)
            counting[j] += counting[j - 1];

        //数组克隆，作递归参数复用
        int[] countingCopy = Arrays.copyOf(counting, range);

        //倒序遍历排序
        for (int j = end; j >= start; j--) {
            int digit = arr[j] / dev % 10;
            sorted[--counting[digit + 9]] = arr[j];
        }

        //拷贝数组
        System.arraycopy(sorted, 0, arr, start, length);

        //相同高位时，再排序
        for (int i = 0; i < range; i++)
            radixSort(arr, i == 0 ? start : start + countingCopy[i - 1], start + countingCopy[i] - 1, position - 1);
    }

    private static int getMaxDigitLength(int[] arr) {
        //求最大值
        int max = 0;
        for (int e : arr) {
            int abs = Math.abs(e);
            if (max < abs) max = abs;
        }

        //求最大位数（根据最大值）
        int maxDigitLength = 0;
        while (max != 0) {
            max /= 10;
            maxDigitLength++;
        }
        return maxDigitLength;
    }
}
