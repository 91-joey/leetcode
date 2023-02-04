//<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;（<strong>下标从 0 开始</strong>）。每一次操作中，你可以选择数组中一个元素，并将它增加&nbsp;<code>1</code>&nbsp;。</p>
//
//<ul> 
// <li>比方说，如果&nbsp;<code>nums = [1,2,3]</code>&nbsp;，你可以选择增加&nbsp;<code>nums[1]</code>&nbsp;得到&nbsp;<code>nums = [1,<b>3</b>,3]</code>&nbsp;。</li> 
//</ul>
//
//<p>请你返回使 <code>nums</code>&nbsp;<strong>严格递增</strong>&nbsp;的 <strong>最少</strong>&nbsp;操作次数。</p>
//
//<p>我们称数组&nbsp;<code>nums</code>&nbsp;是 <strong>严格递增的</strong>&nbsp;，当它满足对于所有的&nbsp;<code>0 &lt;= i &lt; nums.length - 1</code>&nbsp;都有&nbsp;<code>nums[i] &lt; nums[i+1]</code>&nbsp;。一个长度为 <code>1</code>&nbsp;的数组是严格递增的一种特殊情况。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>nums = [1,1,1]
//<b>输出：</b>3
//<b>解释：</b>你可以进行如下操作：
// 1) 增加 nums[2] ，数组变为 [1,1,<strong>2</strong>] 。
// 2) 增加 nums[1] ，数组变为 [1,<strong>2</strong>,2] 。
// 3) 增加 nums[2] ，数组变为 [1,2,<strong>3</strong>] 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>nums = [1,5,2,4,1]
//<b>输出：</b>14
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>nums = [8]
//<b>输出：</b>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 5000</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 36</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

// 1827.最少操作使数组递增
// 开题时间：2022-12-11 09:06:14
public class MinimumOperationsToMakeTheArrayIncreasing {
  public static void main(String[] args) {
    Solution solution = new MinimumOperationsToMakeTheArrayIncreasing().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int minOperations9(int[] nums) {
      int ans = 0;
      for (int i = 1; i < nums.length; i++)
        if (nums[i - 1] >= nums[i]) {
          ans += nums[i - 1] - nums[i] + 1;
          nums[i] = nums[i - 1] + 1;
        }
      return ans;
    }
    
    public int minOperations(int[] nums) {
      int ans = 0, pre = 0;
      for (int cur : nums)
        if (pre < cur)
          pre = cur;
        else
          ans += ++pre - cur;
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}