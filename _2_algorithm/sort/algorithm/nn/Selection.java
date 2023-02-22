package _2_algorithm.sort.algorithm.nn;

import _3_common.tool.Tools;

// 不稳定
public class Selection {
  public static void main(String[] args) {
    //        Swap.sort(SelectionSort::selectionSort1);
    Tools.sort(Selection::selectionSort2);
  }
  
  // 选择排序
  public static void selectionSort1(int[] arr) {
    int length = arr.length;
    for (int i = 0; i < length - 1; i++) {
      int minIdx = i;
      for (int j = i + 1; j < length; j++)
        if (arr[minIdx] > arr[j]) minIdx = j;
      Tools.swap(arr, i, minIdx);
    }
  }
  
  // 二元选择排序
  public static void selectionSort2(int[] arr) {
    int length = arr.length;
    for (int i = 0; i < length / 2; i++) {
      int minIdx = i;
      int maxIdx = i;
      for (int j = i + 1; j < length - i; j++) {
        if (arr[minIdx] > arr[j]) minIdx = j;
        if (arr[maxIdx] < arr[j]) maxIdx = j;
      }
      // arr[i] 后面的所有数字都与 arr[i] 相等，排序完成
      if (minIdx == maxIdx) break;
      Tools.swap(arr, i, minIdx);
      // 若最大值的下标刚好是 i ，由于 arr[i] 和 arr[minIndex] 已经交换了，所以这里要更新 maxIndex 的值。
      if (maxIdx == i) maxIdx = minIdx;
      Tools.swap(arr, length - 1 - i, maxIdx);
    }
  }
}
