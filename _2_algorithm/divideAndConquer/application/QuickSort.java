package _2_algorithm.divideAndConquer.application;

import java.util.Random;

import static _3_common.tool.Tools.sortHard;
import static _3_common.tool.Tools.swap;

public class QuickSort {
  public static void main(String[] args) {
    sortHard(QuickSort::sort);
  }
  
  public static final Random random = new Random();
  
  public static void sort(int[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }
  
  public static void quickSort(int[] arr, int start, int end) {
    if (start >= end)
      return;
    
    int pIdx = partition(arr, start, end);
    
    quickSort(arr, start, pIdx - 1);
    quickSort(arr, pIdx + 1, end);
  }
  
  private static int partition(int[] arr, int start, int end) {
    int randomIdx = random.nextInt(start, end + 1);
    swap(arr, start, randomIdx);
    
    int l = start + 1, r = end;
    while (true) {
      while (l <= end && arr[l] < arr[start]) l++;
      while (r > start && arr[r] > arr[start]) r--;
      if (l >= r)
        break;
      swap(arr, l++, r--);
    }
    swap(arr, start, r);
    return r;
  }
}
