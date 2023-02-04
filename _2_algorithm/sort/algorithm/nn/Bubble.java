package org.example.leetcode.problems._2_algorithm.sort.algorithm.nn;

import org.example.leetcode.problems._3_common.tool.Tools;

// 稳定
public class Bubble {
  // 6 2 1 3 5 4
  // 2 1 3 5 4 6
  // 1 2 3 4 5 6
  public static void main(String[] args) {
    Tools.sort(Bubble::bubble3);
  }
  
  // 不优化：
  public static void bubble1(int[] arr) {
    int lst = arr.length - 1;
    for (int i = 0; i < lst; i++)
      for (int j = 0; j < lst - i; j++)
        if (arr[j] > arr[j + 1])
          Tools.swap(arr, j, j + 1);
  }
  
  // 优化（若当前轮次未交换，不再继续排序）
  public static void bubble2(int[] arr) {
    int lst = arr.length - 1;
    boolean swapped = true;
    for (int i = 0; i < lst; i++)
      if (swapped) {
        swapped = false;
        for (int j = 0; j < lst - i; j++)
          if (arr[j] > arr[j + 1]) {
            Tools.swap(arr, j, j + 1);
            swapped = true;
          }
      }
  }
  
  // 再优化（下轮排序,以上轮「最后一次交换索引」为右边界）
  public static void bubble3(int[] arr) {
    int lst = arr.length - 1;
    boolean swapped = true;
    int endIdx = lst;
    int lstSwappedIdx = -1;
    while (swapped) {
      swapped = false;
      for (int i = 0; i < endIdx; i++)
        if (arr[i] > arr[i + 1]) {
          Tools.swap(arr, i, i + 1);
          swapped = true;
          lstSwappedIdx = i;
        }
      endIdx = lstSwappedIdx;
    }
  }
}
