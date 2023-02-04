package org.example.leetcode.problems._2_algorithm.sort.algorithm.nn;

import org.example.leetcode.problems._3_common.tool.Tools;

public class Insertion {
  public static void main(String[] args) {
    //        Swap.sort(InsertSort::insertSort_swap);
    Tools.sort(Insertion::insertSort_shift);
  }
  
  public static void insertSort_swap(int[] arr) {
    for (int i = 1; i < arr.length; i++)
      for (int j = i; j >= 1 && arr[j - 1] > arr[j]; j--)
        Tools.swap(arr, j - 1, j);
    //        for (int i = 0; i < arr.length - 1; i++)
    //            for (int j = i; j >= 0 && arr[j] > arr[j + 1]; j--)
    //                Tools.swap(arr, j, j + 1);
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
