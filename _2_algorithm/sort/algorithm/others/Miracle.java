package org.example.leetcode.problems._2_algorithm.sort.algorithm.others;

import org.example.leetcode.problems._2_algorithm.sort.algorithm.Swap;

//奇迹排序（纯属娱乐）
public class Miracle {
    public static void miracleSort(int[] arr) throws InterruptedException {
        while (!Swap.isSortedNaturally(arr))
            Thread.sleep(1000L);
    }
}