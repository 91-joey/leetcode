//<p>给你一个非负整数数组 <code>nums</code> 。如果存在一个数 <code>x</code> ，使得 <code>nums</code> 中恰好有 <code>x</code> 个元素 <strong>大于或者等于</strong> <code>x</code> ，那么就称 <code>nums</code> 是一个 <strong>特殊数组</strong> ，而 <code>x</code> 是该数组的 <strong>特征值</strong> 。</p>
//
//<p>注意： <code>x</code> <strong>不必</strong> 是 <code>nums</code> 的中的元素。</p>
//
//<p>如果数组 <code>nums</code> 是一个 <strong>特殊数组</strong> ，请返回它的特征值 <code>x</code> 。否则，返回<em> </em><code>-1</code> 。可以证明的是，如果 <code>nums</code> 是特殊数组，那么其特征值 <code>x</code> 是 <strong>唯一的</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>nums = [3,5]
//<strong>输出：</strong>2
//<strong>解释：</strong>有 2 个元素（3 和 5）大于或等于 2 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>nums = [0,0]
//<strong>输出：</strong>-1
//<strong>解释：</strong>没有满足题目要求的特殊数组，故而也不存在特征值 x 。
// 如果 x = 0，应该有 0 个元素 &gt;= x，但实际有 2 个。
// 如果 x = 1，应该有 1 个元素 &gt;= x，但实际有 0 个。
// 如果 x = 2，应该有 2 个元素 &gt;= x，但实际有 0 个。
// x 不能取更大的值，因为 nums 中只有两个元素。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>nums = [0,4,3,0,4]
//<strong>输出：</strong>3
//<strong>解释：</strong>有 3 个元素大于或等于 3 。
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>nums = [3,6,7,7,0]
//<strong>输出：</strong>-1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 177</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

// 1608.特殊数组的特征值
// 开题时间：2022-11-26 11:51:37
public class SpecialArrayWithXElementsGreaterThanOrEqualX {
  public static void main(String[] args) {
    Solution solution = new SpecialArrayWithXElementsGreaterThanOrEqualX().new Solution();
    System.out.println(solution.specialArray(new int[]{3, 9, 7, 8, 3, 8, 6, 6}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 计数    n + C
    public int specialArray8(int[] nums) {
      int[] freq = new int[1001];
      for (int num : nums)
        freq[num]++;
      
      for (int i = 1000, tot = 0; i >= 0; i--) {
        tot += freq[i];
        if (i == tot)
          return i;
      }
      
      return -1;
    }
    
    //☆☆☆☆☆ 排序+双二分    nlogn + logn * logC
    public int specialArray9(int[] nums) {
      Arrays.sort(nums);
      
      int n = nums.length;
      int l = 1, r = Math.min(n, nums[n - 1]);
      while (l <= r) {
        int mid = ((r - l) >> 1) + l;
        int cnt = getCnt(nums, mid);
        
        if (mid == cnt)
          return mid;
        else if (mid > cnt)
          r = mid - 1;
        else
          l = mid + 1;
      }
      return -1;
    }
    
    private int getCnt(int[] nums, int target) {
      int n = nums.length;
      int l = 0, r = n - 1;
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (target <= nums[mid])
          r = mid;
        else
          l = mid + 1;
      }
      return n - r;
    }
    
    // 倒序排序 + 一次遍历
    public int specialArray7(int[] nums) {
      nums = Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
      int n = nums.length;
      for (int i = 1; i <= n; i++)
        if (nums[i - 1] >= i && (i == n || i > nums[i]))
          return i;
      return -1;
    }
    
    // 排序 + 一次遍历
    public int specialArray(int[] nums) {
      Arrays.sort(nums);
      int n = nums.length;
      for (int i = 1; i <= n; i++)
        if (nums[n - i] >= i && (i == n || i > nums[n - i - 1]))
          return i;
      return -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}