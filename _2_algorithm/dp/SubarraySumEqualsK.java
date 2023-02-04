//<p>给你一个整数数组 <code>nums</code> 和一个整数&nbsp;<code>k</code> ，请你统计并返回 <em>该数组中和为&nbsp;<code>k</code><strong>&nbsp;</strong>的连续子数组的个数&nbsp;</em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,1], k = 2
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3], k = 3
//<strong>输出：</strong>2
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li> 
// <li><code>-10<sup>7</sup> &lt;= k &lt;= 10<sup>7</sup></code></li> 
//</ul>
//
//<div><li>👍 1750</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.HashMap;

// 560.和为 K 的子数组
// 开题时间：2022-12-17 14:17:23
public class SubarraySumEqualsK {
  public static void main(String[] args) {
    Solution solution = new SubarraySumEqualsK().new Solution();
    System.out.println(solution.subarraySum(new int[]{1, 1, 1}, 2));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 前缀和 + 哈希映射（键：前缀和，值：个数）
    public int subarraySum(int[] nums, int k) {
      HashMap<Integer, Integer> sum2cnt = new HashMap<>();
      int sum = 0;
      sum2cnt.put(sum, 1);
      
      int ans = 0;
      for (int x : nums) {
        sum += x;
        ans += sum2cnt.getOrDefault(sum - k, 0);
        sum2cnt.merge(sum, 1, Integer::sum);
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}