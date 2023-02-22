//<p>输入整数数组 <code>arr</code> ，找出其中最小的 <code>k</code> 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>arr = [3,2,1], k = 2
//<strong>输出：</strong>[1,2] 或者 [2,1]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>arr = [0,1,2,1], k = 1
//<strong>输出：</strong>[0]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>限制：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= k &lt;= arr.length &lt;= 10000</code></li> 
// <li><code>0 &lt;= arr[i]&nbsp;&lt;= 10000</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 483</li><li>👎 0</li></div>
package _2_algorithm.sort.problems;

import java.util.Arrays;
import java.util.PriorityQueue;

// 剑指 Offer 40.最小的k个数
// 开题时间：2022-09-23 11:08:19
public class ZuiXiaoDeKgeShuLcof {
  public static void main(String[] args) {
    Solution solution = new ZuiXiaoDeKgeShuLcof().new Solution();
    System.out.println(Arrays.toString(solution.getLeastNumbers(new int[]{3, 2, 1}, 2)));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 内置排序 api
    public int[] getLeastNumbers(int[] arr, int k) {
      int length = arr.length;
      if (k == 0) {
        return new int[]{};
      } else if (k == length) {
        return arr;
      }
      Arrays.sort(arr);
      return Arrays.copyOf(arr, k);
    }
    
    // 内置大根堆（PriorityQueue）
    public int[] getLeastNumbers_PriorityQueue(int[] arr, int k) {
      int[] kMins = new int[k];
      int length = arr.length;
      if (k == 0) {
        return kMins;
      } else if (k == length) {
        return arr;
      }
      
      // 初始化大根堆（填充前 k 个数）
      PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
      for (int i = 0; i < k; i++) {
        maxHeap.offer(arr[i]);
      }
      
      // 循环大根堆化（出列入列）
      for (int i = k; i < length; i++) {
        if (arr[i] < maxHeap.peek()) {
          maxHeap.poll();
          maxHeap.offer(arr[i]);
        }
      }
      
      // 返回大根堆中所有元素组成的数组
      for (int i = 0; i < k; i++) {
        kMins[i] = maxHeap.poll();
      }
      
      return kMins;
    }
    
    // region 自建堆排序
    public int[] getLeastNumbers_heap(int[] arr, int k) {
      int length = arr.length;
      int[] kMins = new int[k];
      if (k == 0) {
        return kMins;
      } else if (k == length) {
        return arr;
        // 若 k 小于数组长度一半，排 k 个最小值
      } else if (k < length / 2) {
        buildMinHeap(arr);
        // length - 1-(length-k+1)+1=k-1
        for (int heapSize = length - 1; heapSize > length - k; heapSize--) {
          swap(arr, 0, heapSize);
          minHeapify(arr, 0, heapSize);
        }
        kMins[0] = arr[0];
        if (k > 1) System.arraycopy(arr, length - k + 1, kMins, 1, k - 1);
        // 若 k 大于数组长度一半，排 length + 1 - k 个最大值
      } else {
        buildMaxHeap(arr);
        // length-1-(k+1)+1=length-k-1
        for (int heapSize = length - 1; heapSize > k; heapSize--) {
          swap(arr, 0, heapSize);
          maxHeapify(arr, 0, heapSize);
        }
        System.arraycopy(arr, 1, kMins, 0, k);
      }
      return kMins;
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
    // endregion
  }
  // leetcode submit region end(Prohibit modification and deletion)
}