//<p>给定一个数组 <code><em>nums</em></code> 和一个目标值 <code><em>k</em></code>，找到和等于<em> <code>k</code> </em>的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 <code>0</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入: </strong><em>nums</em> = <span><code>[1,-1,5,-2,3]</code></span>, <em>k</em> = <span><code>3</code></span>
//<strong>输出: </strong>4 
//<strong>解释: </strong>子数组 <span><code>[1, -1, 5, -2]</code></span> 和等于 3，且长度最长。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入: </strong><em>nums</em> = <span><code>[-2,-1,2,1]</code></span>, <em>k</em> = <span><code>1</code></span>
//<strong>输出: </strong>2 <strong>
// 解释: </strong>子数组<span><code> [-1, 2]</code></span> 和等于 1，且长度最长。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>-10<sup>9</sup>&nbsp;&lt;= k &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><li>👍 189</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.HashMap;

// 325.和等于 k 的最长子数组长度
// 开题时间：2022-12-16 17:24:24
public class MaximumSizeSubarraySumEqualsK {
  public static void main(String[] args) {
    Solution solution = new MaximumSizeSubarraySumEqualsK().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 前缀和 + 哈希映射
    public int maxSubArrayLen(int[] nums, int k) {
      HashMap<Integer, Integer> sum2idx = new HashMap<>();
      sum2idx.put(0, -1);
      
      int max = 0;
      for (int i = 0, sum = 0; i < nums.length; i++) {
        sum += nums[i];
        Integer left = sum2idx.get(sum - k);
        if (left != null)
          max = Math.max(max, i - left);
        sum2idx.putIfAbsent(sum, i);
      }
      
      return max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}