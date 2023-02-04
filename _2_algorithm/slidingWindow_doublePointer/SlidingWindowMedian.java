//<p>中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。</p>
//
//<p>例如：</p>
//
//<ul> 
// <li><code>[2,3,4]</code>，中位数是&nbsp;<code>3</code></li> 
// <li><code>[2,3]</code>，中位数是 <code>(2 + 3) / 2 = 2.5</code></li> 
//</ul>
//
//<p>给你一个数组 <em>nums</em>，有一个长度为 <em>k</em> 的窗口从最左端滑动到最右端。窗口中有 <em>k</em> 个数，每次窗口向右移动 <em>1</em> 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<p>给出&nbsp;<em>nums</em> = <code>[1,3,-1,-3,5,3,6,7]</code>，以及&nbsp;<em>k</em> = 3。</p>
//
//<pre>
// 窗口位置                      中位数
//---------------               -----
//[1  3  -1] -3  5  3  6  7       1
// 1 [3  -1  -3] 5  3  6  7      -1
// 1  3 [-1  -3  5] 3  6  7      -1
// 1  3  -1 [-3  5  3] 6  7       3
// 1  3  -1  -3 [5  3  6] 7       5
// 1  3  -1  -3  5 [3  6  7]      6
//</pre>
//
//<p>&nbsp;因此，返回该滑动窗口的中位数数组&nbsp;<code>[1,-1,-1,3,5,6]</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>你可以假设&nbsp;<code>k</code>&nbsp;始终有效，即：<code>k</code> 始终小于等于输入的非空数组的元素个数。</li> 
// <li>与真实值误差在 <code>10 ^ -5</code> 以内的答案将被视作正确答案。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>滑动窗口</li><li>堆（优先队列）</li></div></div><br><div><li>👍 395</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 480.滑动窗口中位数
// 开题时间：2022-10-16 10:24:53
public class SlidingWindowMedian {
  public static void main(String[] args) {
    Solution solution = new SlidingWindowMedian().new Solution();
    System.out.println(Arrays.toString(solution.medianSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 数据结构：对顶堆（大根堆+小根堆，且大根堆顶<=小根堆顶）
     *      额外技巧：「延迟删除」（使用哈希表维护待删元素及个数）
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
      DualHeap dh = new DualHeap(k);
      for (int i = 0; i < k; ++i) {
        dh.insert(nums[i]);
      }
      double[] ans = new double[nums.length - k + 1];
      ans[0] = dh.getMedian();
      for (int i = k; i < nums.length; ++i) {
        dh.insert(nums[i]);
        dh.erase(nums[i - k]);
        ans[i - k + 1] = dh.getMedian();
      }
      return ans;
    }
    
    // 二分查找法（nk）
    public double[] medianSlidingWindow2(int[] nums, int k) {
      double[] ans = new double[nums.length - k + 1];
      int[] win = Arrays.copyOfRange(nums, 0, k);
      Arrays.sort(win);
      
      boolean odd = (k & 1) == 1;
      int mid = k / 2;
      int midPre = mid - 1;
      
      for (int r = k; r < nums.length; r++) {
        int l = r - k;
        ans[l] = odd ? win[mid] : ((double) win[midPre] + win[mid]) / 2;
        
        int delIdx = Arrays.binarySearch(win, nums[l]);
        int addIdx = Arrays.binarySearch(win, nums[r]);
        if (addIdx < 0)
          addIdx = -addIdx - 1;
        if (delIdx < addIdx) {
          addIdx--;
          System.arraycopy(win, delIdx + 1, win, delIdx, addIdx - delIdx);
        } else
          System.arraycopy(win, addIdx, win, addIdx + 1, delIdx - addIdx);
        win[addIdx] = nums[r];
      }
      ans[ans.length - 1] = odd ? win[mid] : ((double) win[midPre] + win[mid]) / 2;
      
      return ans;
    }
    
    class DualHeap {
      // 大根堆，维护较小的一半元素
      private final PriorityQueue<Integer> small;
      // 小根堆，维护较大的一半元素
      private final PriorityQueue<Integer> large;
      // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
      private final Map<Integer, Integer> delayed;
      
      private final int k;
      // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
      private int smallSize, largeSize;
      
      public DualHeap(int k) {
        this.small = new PriorityQueue<>(Comparator.reverseOrder());
        this.large = new PriorityQueue<>();
        this.delayed = new HashMap<>();
        this.k = k;
        this.smallSize = 0;
        this.largeSize = 0;
      }
      
      public double getMedian() {
        return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
      }
      
      public void insert(int num) {
        if (small.isEmpty() || num <= small.peek()) {
          small.offer(num);
          ++smallSize;
        } else {
          large.offer(num);
          ++largeSize;
        }
        makeBalance();
      }
      
      public void erase(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= small.peek()) {
          --smallSize;
          if (num == small.peek()) {
            prune(small);
          }
        } else {
          --largeSize;
          if (num == large.peek()) {
            prune(large);
          }
        }
        makeBalance();
      }
      
      // 不断地弹出 heap 的堆顶元素，并且更新哈希表
      private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
          int num = heap.peek();
          if (delayed.containsKey(num)) {
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
              delayed.remove(num);
            }
            heap.poll();
          } else {
            break;
          }
        }
      }
      
      // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
      private void makeBalance() {
        if (smallSize > largeSize + 1) {
          // small 比 large 元素多 2 个
          large.offer(small.poll());
          --smallSize;
          ++largeSize;
          // small 堆顶元素被移除，需要进行 prune
          prune(small);
        } else if (smallSize < largeSize) {
          // large 比 small 元素多 1 个
          small.offer(large.poll());
          ++smallSize;
          --largeSize;
          // large 堆顶元素被移除，需要进行 prune
          prune(large);
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}