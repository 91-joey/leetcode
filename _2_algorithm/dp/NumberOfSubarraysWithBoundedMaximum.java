//<p>给你一个整数数组 <code>nums</code> 和两个整数：<code>left</code> 及 <code>right</code> 。找出 <code>nums</code> 中连续、非空且其中最大元素在范围&nbsp;<code>[left, right]</code> 内的子数组，并返回满足条件的子数组的个数。</p>
//
//<p>生成的测试用例保证结果符合 <strong>32-bit</strong> 整数范围。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,1,4,3], left = 2, right = 3
//<strong>输出：</strong>3
//<strong>解释：</strong>满足条件的三个子数组：[2], [2, 1], [3]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,9,2,5,6], left = 2, right = 8
//<strong>输出：</strong>7
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
// <li><code>0 &lt;= left &lt;= right &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><li>👍 217</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 795.区间子数组个数
// 开题时间：2022-11-24 08:53:57
public class NumberOfSubarraysWithBoundedMaximum {
  public static void main(String[] args) {
    Solution solution = new NumberOfSubarraysWithBoundedMaximum().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
      return numSubarrayBoundedMax(nums, left) - numSubarrayBoundedMax(nums, right + 1);
    }
    
    private int numSubarrayBoundedMax(int[] nums, int min) {
      int n = nums.length;
      int cnt = 0;
      for (int i = 0, cur = 0; i < n; i++) {
        if (nums[i] >= min)
          cur = i + 1;
        cnt += cur;
      }
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}