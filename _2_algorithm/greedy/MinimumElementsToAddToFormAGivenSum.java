//<p>给你一个整数数组 <code>nums</code> ，和两个整数 <code>limit</code> 与 <code>goal</code> 。数组 <code>nums</code> 有一条重要属性：<code>abs(nums[i]) &lt;= limit</code> 。</p>
//
//<p>返回使数组元素总和等于 <code>goal</code> 所需要向数组中添加的 <strong>最少元素数量</strong> ，添加元素 <strong>不应改变</strong> 数组中 <code>abs(nums[i]) &lt;= limit</code> 这一属性。</p>
//
//<p>注意，如果 <code>x &gt;= 0</code> ，那么 <code>abs(x)</code> 等于 <code>x</code> ；否则，等于 <code>-x</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,-1,1], limit = 3, goal = -4
//<strong>输出：</strong>2
//<strong>解释：</strong>可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,-10,9,1], limit = 100, goal = 0
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>1 &lt;= limit &lt;= 10<sup>6</sup></code></li> 
// <li><code>-limit &lt;= nums[i] &lt;= limit</code></li> 
// <li><code>-10<sup>9</sup> &lt;= goal &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><li>👍 17</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

import java.util.Arrays;

// 1785.构成特定和需要添加的最少元素
// 开题时间：2022-12-16 09:34:16
public class MinimumElementsToAddToFormAGivenSum {
  public static void main(String[] args) {
    Solution solution = new MinimumElementsToAddToFormAGivenSum().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int minElements9(int[] nums, int limit, int goal) {
      long sum = 0;
      for (int x : nums)
        sum += x;
      
      long diff = Math.abs(sum - goal);
      return (int) ((diff + limit - 1) / limit);
    }
    
    public int minElements(int[] nums, int limit, int goal) {
      return (int) ((Math.abs(Arrays.stream(nums).mapToLong(Long::valueOf).sum() - goal) + limit - 1) / limit);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}