package org.example.leetcode.problems._2_algorithm.divideAndConquer.application;

import org.example.leetcode.problems._3_common.tool.Tools;

public class MergeSort {
  public static void main(String[] args) {
    Tools.sortHard(MergeSort::sort);
  }
  
  public static void sort(int[] arr) {
    mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
  }
  
  private static void mergeSort(int[] arr, int start, int end, int[] tmp) {
    if (start == end)
      return;
    
    int mid = start + end >> 1;
    mergeSort(arr, start, mid, tmp);
    mergeSort(arr, mid + 1, end, tmp);
    
    System.arraycopy(arr, start, tmp, start, end - start + 1);
    for (int i = start, l = start, r = mid + 1; i <= end; i++) {
      if (l > mid)
        arr[i] = tmp[r++];
      else if (r > end || tmp[l] <= tmp[r])
        arr[i] = tmp[l++];
      else
        arr[i] = tmp[r++];
    }
  }
}
