//<p>给定整数数组 <code>nums</code> 和整数 <code>k</code>，请返回数组中第 <code><strong>k</strong></code> 个最大的元素。</p>
//
//<p>请注意，你需要找的是数组排序后的第 <code>k</code> 个最大的元素，而不是第 <code>k</code> 个不同的元素。</p>
//
//<p>你必须设计并实现时间复杂度为 <code>O(n)</code> 的算法解决此问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>[3,2,1,5,6,4],</code></span> k = 2
//<strong>输出:</strong> 5
//</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>[3,2,3,1,2,4,5,5,6], </code></span>k = 4
//<strong>输出:</strong> 4</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示： </strong></p>
//
//<ul> 
// <li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 1881</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort.problems;


import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

// 215.数组中的第K个最大元素
// 开题时间：2022-09-20 11:09:28
public class KthLargestElementInAnArray {
  public static void main(String[] args) {
    Solution solution = new KthLargestElementInAnArray().new Solution();
    System.out.println(solution.findKthLargest_selectionSort(Tools.SHUFFLED_ARR_EASY, 1));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // API选手
    public int findKthLargest(int[] nums, int k) {
      Arrays.parallelSort(nums);
      return nums[nums.length - k];
    }
    
    // 选择排序  n^2
    public int findKthLargest_selectionSort(int[] nums, int k) {
      int length = nums.length;
      // 若 k 小于数组长度一半，排 k 个最大值
      if (k < length / 2) {
        for (int i = 0; i < k; i++) {
          int maxIdx = i;
          for (int j = i + 1; j < length; j++)
            if (nums[maxIdx] < nums[j]) maxIdx = j;
          swap(nums, i, maxIdx);
        }
        return nums[k - 1];
        // 若 k 大于数组长度一半，排 length + 1 - k 个最小值
      } else {
        for (int i = 0; i <= length - k; i++) {
          int minIdx = i;
          for (int j = i + 1; j < length; j++)
            if (nums[minIdx] > nums[j]) minIdx = j;
          swap(nums, i, minIdx);
        }
        return nums[length - k];
      }
    }
    
    // 堆排序   nlogn
    public int findKthLargest_heapSort(int[] nums, int k) {
      int length = nums.length;
      // 若 k 小于数组长度一半，排 k 个最大值
      if (k < length / 2) {
        buildMaxHeap(nums);
        for (int heapSize = length - 1; heapSize > length - k; heapSize--) {
          swap(nums, 0, heapSize);
          maxHeapify(nums, 0, heapSize);
        }
        // 若 k 大于数组长度一半，排 length + 1 - k 个最小值
      } else {
        buildMinHeap(nums);
        for (int heapSize = nums.length - 1; heapSize >= k; heapSize--) {
          swap(nums, 0, heapSize);
          minHeapify(nums, 0, heapSize);
        }
      }
      return nums[0];
    }
    
    // partition减治   n
    public int findKthLargest_quickSort(int[] nums, int k) {
      int length = nums.length;
      int start = 0;
      int end = length - 1;
      int target = length - k;
      while (true) {
        int pivotIdx = partition(nums, start, end);
        if (pivotIdx == target)
          return nums[pivotIdx];
        else if (pivotIdx < target)
          start = pivotIdx + 1;
        else
          end = pivotIdx - 1;
      }
    }
    
    // 优先队列
    public int findKthLargest_minHeap(int[] nums, int k) {
      PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Integer::compareTo);
      for (int i = 0; i < k; i++)
        minHeap.offer(nums[i]);
      for (int i = k; i < nums.length; i++) {
        Integer peek = minHeap.peek();
        if (peek < nums[i]) {
          minHeap.poll();
          minHeap.offer(nums[i]);
        }
      }
      return minHeap.peek();
    }
    
    private static int partition(int[] arr, int start, int end) {
      if (start >= end)
        return start;
      // region 分区优化1（随机选择基数）
      int rndIdx = new Random().nextInt(start, end + 1);
      swap(arr, start, rndIdx);
      // endregion
      //  all in [start+1,l) <= pivot
      //  all in (r,end] > pivot
      int pivot = arr[start];
      int l = start + 1;
      int r = end;
      while (l < r) {
        if (arr[l] > pivot) {
          while (l < r && arr[r] > pivot) r--;
          if (l != r) swap(arr, l++, r--);
        } else {
          l++;
        }
      }
      if (l == r && arr[l] > pivot) r--;
      swap(arr, start, r);
      return r;
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
    
    public static void swap(int[] arr, int i, int j) {
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}