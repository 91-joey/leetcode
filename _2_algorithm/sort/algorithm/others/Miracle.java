package org.example.leetcode.problems._2_algorithm.sort.algorithm.others;

import org.example.leetcode.problems._3_common.tool.Tools;

// 奇迹排序（纯属娱乐）
public class Miracle {
  public static void miracleSort(int[] arr) throws InterruptedException {
    while (!Tools.isSortedNaturally(arr))
      Thread.sleep(1000L);
  }
}
