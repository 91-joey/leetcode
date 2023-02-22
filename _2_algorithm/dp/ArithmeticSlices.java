//<p>如果一个数列 <strong>至少有三个元素</strong> ，并且任意两个相邻元素之差相同，则称该数列为等差数列。</p>
//
//<ul> 
// <li>例如，<code>[1,3,5,7,9]</code>、<code>[7,7,7,7]</code> 和 <code>[3,-1,-5,-9]</code> 都是等差数列。</li> 
//</ul>
//
//<div class="original__bRMd"> 
// <div> 
//  <p>给你一个整数数组 <code>nums</code> ，返回数组 <code>nums</code> 中所有为等差数组的 <strong>子数组</strong> 个数。</p> 
// </div>
//</div>
//
//<p><strong>子数组</strong> 是数组中的一个连续序列。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,4]
//<strong>输出：</strong>3
//<strong>解释：</strong>nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 5000</code></li> 
// <li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 493</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 413.等差数列划分
// 开题时间：2022-12-02 16:08:17
public class ArithmeticSlices {
  public static void main(String[] args) {
    Solution solution = new ArithmeticSlices().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int numberOfArithmeticSlicesX(int[] nums) {
      int cnt = 0;
      int n = nums.length;
      int[][] dp = new int[n][4001];
      
      for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
          int d = nums[i] - nums[j] + 2000;
          dp[i][d] = dp[j][d] + 1;
          if (dp[i][d] >= 2)
            cnt++;
        }
      }
      
      return cnt;
    }
    
    /*
     * dp[i]：以索引 i 结尾的最大等差子数组长度 - 2
     *   则索引 i 处对答案的贡献为 dp[i]
     */
    public int numberOfArithmeticSlices8(int[] nums) {
      int cnt = 0;
      int n = nums.length;
      int[] dp = new int[n];
      
      for (int i = 2; i < n; i++)
        if (2 * nums[i - 1] == nums[i - 2] + nums[i]) {
          dp[i] = dp[i - 1] + 1;
          cnt += dp[i];
        }
      
      return cnt;
    }
    
    // DP优化（状态压缩）
    public int numberOfArithmeticSlices(int[] nums) {
      int cnt = 0;
      
      for (int i = 2, pre = 0; i < nums.length; i++)
        if (2 * nums[i - 1] == nums[i - 2] + nums[i])
          cnt += ++pre;
        else
          pre = 0;
      
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}