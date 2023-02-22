package _2_algorithm.sort.algorithm.nlogn;

import _3_common.tool.Tools;

public class Heap {
  public static void main(String[] args) {
    //        Swap.sort(Heap::heapSort);
    Tools.sort(Heap::heapSort2);
  }
  
  // region 大顶堆
  public static void heapSort(int[] arr) {
    buildMaxHeap(arr);
    for (int heapSize = arr.length - 1; heapSize > 0; heapSize--) {
      swap(arr, 0, heapSize);
      maxHeapify(arr, 0, heapSize);
    }
  }
  
  private static void buildMaxHeap(int[] arr) {
    int length = arr.length;
    for (int i = length / 2 - 1; i >= 0; i--) maxHeapify(arr, i, length);
  }
  
  private static void maxHeapify(int[] arr, int root, int heapSize) {
    int l = 2 * root + 1;
    int r = l + 1;
    int maxIdx = root;
    if (l < heapSize) {
      if (arr[maxIdx] < arr[l]) maxIdx = l;
      if (r < heapSize && arr[maxIdx] < arr[r]) maxIdx = r;
    }
    if (maxIdx != root) {
      swap(arr, root, maxIdx);
      maxHeapify(arr, maxIdx, heapSize);
    }
  }
  // endregion
  
  // region 小顶堆
  public static void heapSort2(int[] arr) {
    buildMinHeap(arr);
    for (int heapSize = arr.length - 1; heapSize > 0; heapSize--) {
      swap(arr, 0, heapSize);
      minHeapify(arr, 0, heapSize);
    }
  }
  
  private static void buildMinHeap(int[] arr) {
    int length = arr.length;
    for (int i = length / 2 - 1; i >= 0; i--) minHeapify(arr, i, length);
  }
  
  private static void minHeapify(int[] arr, int root, int heapSize) {
    int l = 2 * root + 1;
    int r = l + 1;
    int minIdx = root;
    if (l < heapSize) {
      if (arr[minIdx] > arr[l]) minIdx = l;
      if (r < heapSize && arr[minIdx] > arr[r]) minIdx = r;
    }
    if (minIdx != root) {
      swap(arr, root, minIdx);
      minHeapify(arr, minIdx, heapSize);
    }
  }
  // endregion
  
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
