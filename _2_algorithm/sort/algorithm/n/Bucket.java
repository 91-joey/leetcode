package org.example.leetcode.problems._2_algorithm.sort.algorithm.n;

import org.example.leetcode.problems._2_algorithm.sort.algorithm.Swap;
import org.example.leetcode.problems._2_algorithm.sort.algorithm.nlogn.Merge;

import java.util.Arrays;
/*
* 桶排序基于一个假设：所有输入数据都服从均匀分布，也就是说输入数据应该尽可能地均匀分布在每个桶中。
* 时间复杂度取决于依赖的其他排序算法
*   O(n^2)级：k(n/k)^2 即 n^2/k
*   O(nlogn)级：k(n/k)log(n/k) 即 nlog(n/k)
*   两者都约等于 O(n)
*/

public class Bucket {
    public static void main(String[] args) {
        Swap.sort(Bucket::bucketSort);
        Swap.sortHard(Bucket::bucketSort);
    }

    public static void bucketSort(int[] arr) {
        //获取值区间
        int min = arr[0];
        int max = arr[0];
        for (int e : arr) {
            if (e < min) min = e;
            else if (max < e) max = e;
        }

        int bucketCnt = 10;
        int length = arr.length;
        int[][] buckets = new int[bucketCnt][length];
        int[] bucketLengths = new int[bucketCnt];

        //落桶
        double gap = 1.0 * (max - min) / (bucketCnt - 1);
        for (int e : arr) {
            int i = (int) ((e - min) / gap);
            buckets[i][bucketLengths[i]++] = e;
        }

        //桶排序与合并
        for (int i = 0, idx = 0; i < bucketCnt; i++) {
            if (bucketLengths[i] == 0) continue;
            int[] trimmed = Arrays.copyOf(buckets[i], bucketLengths[i]);
            Merge.mergeSort(trimmed);
            System.arraycopy(trimmed, 0, arr, idx, bucketLengths[i]);
            idx += bucketLengths[i];
        }
    }
}
