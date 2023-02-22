//<p>你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong>。</p>
//
//<p>给定一个代表每个房屋存放金额的非负整数数组，计算你<strong> 不触动警报装置的情况下 </strong>，一夜之内能够偷窃到的最高金额。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>[1,2,3,1]
//<strong>输出：</strong>4
//<strong>解释：</strong>偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//&nbsp;    偷窃到的最高金额 = 1 + 3 = 4 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>[2,7,9,3,1]
//<strong>输出：</strong>12
//<strong>解释：</strong>偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//&nbsp;    偷窃到的最高金额 = 2 + 9 + 1 = 12 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 400</code></li> 
//</ul>
//
//<div><li>👍 2357</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 198.打家劫舍
// 开题时间：2022-11-24 18:41:16
public class HouseRobber {
  public static void main(String[] args) {
    Solution solution = new HouseRobber().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * dp1
     * 定义：
     *  f[i] 表示偷窃前 i 个房子的最高金额
     */
    public int rob9(int[] nums) {
      int n = nums.length;
      int[] f = new int[n + 2];
      for (int i = 2; i < n + 2; i++) {
        f[i] = Math.max(f[i - 1], f[i - 2] + nums[i - 2]);
      }
      return f[n + 1];
    }
    
    // dp1（优化）
    public int rob8(int[] nums) {
      int pre = 0, cur = 0;
      for (int num : nums) {
        int tmp = Math.max(cur, pre + num);
        pre = cur;
        cur = tmp;
      }
      return cur;
    }
    
    /*
     * dp2
     * 定义：
     *  f[i][0] 表示偷窃前 i 个房子的最高金额（第 i 个房子不偷）
     *  f[i][1] 表示偷窃前 i 个房子的最高金额（第 i 个房子 偷）
     */
    public int rob7(int[] nums) {
      int n = nums.length;
      int[][] f = new int[n + 1][2];
      for (int i = 1; i < n + 1; i++) {
        f[i][0] = Math.max(f[i - 1][0], f[i - 1][1]);
        f[i][1] = f[i - 1][0] + nums[i - 1];
      }
      return Math.max(f[n][0], f[n][1]);
    }
    
    // dp2（优化）
    public int rob(int[] nums) {
      int n = nums.length;
      int notRob = 0;
      int rob = 0;
      for (int i = 1; i < n + 1; i++) {
        int tmp = notRob;
        notRob = Math.max(notRob, rob);
        rob = tmp + nums[i - 1];
      }
      return Math.max(notRob, rob);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}