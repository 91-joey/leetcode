//<p>你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 <strong>围成一圈</strong> ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong> 。</p>
//
//<p>给定一个代表每个房屋存放金额的非负整数数组，计算你 <strong>在不触动警报装置的情况下</strong> ，今晚能够偷窃到的最高金额。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,3,2]
//<strong>输出：</strong>3
//<strong>解释：</strong>你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,1]
//<strong>输出：</strong>4
//<strong>解释：</strong>你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//&nbsp;    偷窃到的最高金额 = 1 + 3 = 4 。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3]
//<strong>输出：</strong>3
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
//<div><li>👍 1205</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

// 213.打家劫舍 II
// 开题时间：2022-11-26 14:56:23
public class HouseRobberIi {
  public static void main(String[] args) {
    Solution solution = new HouseRobberIi().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 第一个房屋和最后一个房屋是紧挨着的
     * 则只有两种情况：
     *   - 第一个房屋抢，  最后一个房屋不抢
     *   - 第一个房屋不抢，最后一个房屋抢
     */
    public int rob(int[] nums) {
      int n = nums.length;
      
      if (n == 1)
        return nums[0];
      
      return Math.max(
          getMax(nums, 0, n - 1),
          getMax(nums, 1, n)
      );
    }
    
    private int getMax(int[] nums, int start, int end) {
      int cur = 0;
      for (int i = start, pre = 0; i < end; i++) {
        int tmp = Math.max(cur, pre + nums[i]);
        pre = cur;
        cur = tmp;
      }
      return cur;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}