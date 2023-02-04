//<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>x</code> 。每一次操作时，你应当移除数组 <code>nums</code> 最左边或最右边的元素，然后从 <code>x</code> 中减去该元素的值。请注意，需要 <strong>修改</strong> 数组以供接下来的操作使用。</p>
//
//<p>如果可以将 <code>x</code>&nbsp;<strong>恰好</strong> 减到&nbsp;<code>0</code> ，返回<strong> 最小操作数 </strong>；否则，返回 <code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,4,2,3], x = 5
//<strong>输出：</strong>2
//<strong>解释：</strong>最佳解决方案是移除后两个元素，将 x 减到 0 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [5,6,7,8,9], x = 4
//<strong>输出：</strong>-1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,2,20,1,1,3], x = 10
//<strong>输出：</strong>5
//<strong>解释：</strong>最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>二分查找</li><li>前缀和</li><li>滑动窗口</li></div></div><br><div><li>👍 124</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.Arrays;
import java.util.HashMap;

// 1658.将 x 减到 0 的最小操作数
// 开题时间：2022-10-03 10:21:20
public class MinimumOperationsToReduceXToZero {
  public static void main(String[] args) {
    Solution solution = new MinimumOperationsToReduceXToZero().new Solution();
    //        System.out.println(solution.minOperations6(new int[]{3, 2, 20, 1, 1, 3}, 10));
    //        System.out.println(solution.minOperations6(new int[]{5, 2, 3, 1, 1}, 5));
    System.out.println(solution.minOperations6(new int[]{5, 1, 4, 2, 3}, 6));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // TLE
    public int minOperations(int[] nums, int x) {
      
      int length = nums.length;
      
      int[] sums = new int[length];
      for (int i = 0, sum = 0; i < length; i++) {
        sum += nums[i];
        sums[i] = sum;
      }
      
      for (int k = 0; k < length; k++) {
        if (sums[k] == x)
          return k + 1;
        for (int i = k, remains = length - k - 1, sumCur = sums[k]; i >= 0; i--) {
          sumCur += nums[remains + i] - nums[i];
          if (sumCur == x)
            return k + 1;
        }
      }
      
      return -1;
    }
    
    public int minOperations2(int[] nums, int x) {
      int length = nums.length;
      
      int[] sumsL = new int[length + 1];
      for (int i = 0, sum = 0; i < length; i++) {
        sum += nums[i];
        sumsL[i + 1] = sum;
      }
      int[] sumsR = new int[length + 1];
      for (int i = length - 1, sum = 0; i >= 0; i--) {
        sum += nums[i];
        sumsR[i] = sum;
      }
      
      boolean[] shouldNotContinue = new boolean[length + 1];
      for (int k = 0; k <= length; k++) {
        for (int i = k, remains = length - k; i >= 0; i--) {
          if (!shouldNotContinue[i]) {
            int sum = sumsL[i] + sumsR[remains + i];
            if (sum == x)
              return k;
            else if (sum > x)
              shouldNotContinue[i] = true;
          }
        }
      }
      
      return -1;
    }
    
    public int minOperations3(int[] nums, int x) {
      int length = nums.length;
      
      int[] sumsL = new int[length + 1];
      for (int i = 0, sum = 0; i < length; i++) {
        sum += nums[i];
        sumsL[i + 1] = sum;
      }
      int[] sumsR = new int[length + 1];
      for (int i = length - 1, sum = 0; i >= 0; i--) {
        sum += nums[i];
        sumsR[i] = sum;
      }
      
      int minOps = length + 1;
      if (sumsL[length] == x) minOps = length;
      for (int l = 0; l <= length; l++) {
        for (int r = length; r > l; r--) {
          int sum = sumsL[l] + sumsR[r];
          if (sum == x)
            minOps = Math.min(minOps, l + length - r);
          else if (sum > x)
            break;
        }
      }
      
      return minOps == length + 1 ? -1 : minOps;
    }
    
    // 二分查找
    public int minOperations4(int[] nums, int x) {
      int length = nums.length;
      
      int[] sumsL = new int[length + 1];
      for (int i = 0, sum = 0; i < length; i++) {
        sum += nums[i];
        sumsL[i + 1] = sum;
      }
      if (sumsL[length] < x) return -1;
      else if (sumsL[length] == x) return length;
      
      int[] sumsR = new int[length + 1];
      for (int i = length - 1, sum = 0; i >= 0; i--) {
        sum += nums[i];
        sumsR[length - i] = sum;
      }
      
      int minOps = length + 1;
      if (sumsL[length] == x) minOps = length;
      for (int l = 0; l <= length; l++) {
        int r = Arrays.binarySearch(sumsR, 0, length - l + 1, x - sumsL[l]);
        if (r >= 0)
          minOps = Math.min(minOps, l + r);
      }
      
      return minOps == length + 1 ? -1 : minOps;
    }
    
    // 哈希表
    public int minOperations5(int[] nums, int x) {
      int length = nums.length;
      
      int[] sumsL = new int[length + 1];
      for (int i = 0, sum = 0; i < length; i++) {
        sum += nums[i];
        sumsL[i + 1] = sum;
      }
      if (sumsL[length] < x) return -1;
      else if (sumsL[length] == x) return length;
      
      HashMap<Integer, Integer> sumsR = new HashMap<>(length * 4 / 3);
      sumsR.put(0, 0);
      for (int i = length - 1, sum = 0; i >= 0; i--) {
        sum += nums[i];
        sumsR.put(sum, length - i);
      }
      
      int minOps = length + 1;
      for (int l = 0; l <= length; l++) {
        Integer r = sumsR.get(x - sumsL[l]);
        if (r != null)
          minOps = Math.min(minOps, l + r);
      }
      
      return minOps == length + 1 ? -1 : minOps;
    }
    
    // 滑动窗口：转换题意 -> 求和为 sum - x 的最长子数组
    public int minOperations6(int[] nums, int x) {
      int length = nums.length;
      int target = Arrays.stream(nums).sum() - x;
      if (target < 0) return -1;
      else if (target == 0) return length;
      
      int maxLen = 0;
      // sum([l,r)) <target
      int sum = 0;
      int l = 0;
      int r = 0;
      while (r <= length) {
        if (sum == target) {
          maxLen = Math.max(maxLen, r - l);
          if (r < length) {
            sum -= nums[l++];
            sum += nums[r];
            if (r + 1 < length) {
              sum += nums[++r];
            }
          }
          r++;
        } else if (sum > target) {
          do {
            sum -= nums[l++];
          } while (sum > target);
        } else {
          if (r < length)
            sum += nums[r];
          r++;
        }
      }
      
      return maxLen == 0 ? -1 : length - maxLen;
    }
    
    //☆☆☆☆☆ 滑动窗口：转换题意 -> 求和为 sum - x 的最长子数组
    public int minOperations7(int[] nums, int x) {
      int length = nums.length;
      
      int t = -x;
      for (int num : nums)
        t += num;
      
      if (t < 0) return -1;
      else if (t == 0) return length;
      
      int max = -1;
      // sum([l,r)) <t
      for (int l = 0, r = 0, sum = 0; r < length; ) {
        sum += nums[r++];
        while (sum > t)
          sum -= nums[l++];
        if (sum == t)
          max = Math.max(max, r - l);
      }
      
      return max == -1 ? -1 : length - max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}