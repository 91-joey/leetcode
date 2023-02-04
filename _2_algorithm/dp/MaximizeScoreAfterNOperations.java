//<p>给你&nbsp;<code>nums</code>&nbsp;，它是一个大小为&nbsp;<code>2 * n</code>&nbsp;的正整数数组。你必须对这个数组执行 <code>n</code>&nbsp;次操作。</p>
//
//<p>在第&nbsp;<code>i</code>&nbsp;次操作时（操作编号从 <strong>1</strong>&nbsp;开始），你需要：</p>
//
//<ul> 
// <li>选择两个元素&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;。</li> 
// <li>获得分数&nbsp;<code>i * gcd(x, y)</code>&nbsp;。</li> 
// <li>将&nbsp;<code>x</code>&nbsp;和&nbsp;<code>y</code> 从&nbsp;<code>nums</code>&nbsp;中删除。</li> 
//</ul>
//
//<p>请你返回 <code>n</code>&nbsp;次操作后你能获得的分数和最大为多少。</p>
//
//<p>函数&nbsp;<code>gcd(x, y)</code>&nbsp;是&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;的最大公约数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>nums = [1,2]
//<b>输出：</b>1
//<b>解释：</b>最优操作是：
//(1 * gcd(1, 2)) = 1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>nums = [3,4,6,8]
//<b>输出：</b>11
//<b>解释：</b>最优操作是：
//(1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>nums = [1,2,3,4,5,6]
//<b>输出：</b>14
//<b>解释：</b>最优操作是：
//(1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 7</code></li> 
// <li><code>nums.length == 2 * n</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li> 
//</ul>
//
//<div><li>👍 64</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

// 1799.N 次操作后的最大分数和
// 开题时间：2022-12-22 13:36:31
public class MaximizeScoreAfterNOperations {
  public static void main(String[] args) {
    Solution solution = new MaximizeScoreAfterNOperations().new Solution();
    System.out.println(solution.maxScore(new int[]{1, 2}));
    int p = 1;
    for (int i = 1; i < 14; i++) {
      p *= i;
    }
    System.out.println(p);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 状态压缩 + 动态规划
     * 定义：f[s]表示状态为 s 时的最大得分，(s为二进制，从右往左的第 i 位为 1 ，表示数组第 i 个元素被删除)
     * 转移：
     *      由未删除 2 个元素的前状态转移而来：
     *      f[s] = max{f[s ^ (1 << i) ^ (1 << j)] + cnt / 2 * gcd[i][j]}， ((s >> i) & 1) == 1 && ((s >> j) & 1) == 1
     * T(2^n * n^2)
     */
    public int maxScore(int[] nums) {
      int n = nums.length;
      // 预处理「最大公约数」
      int[][] gcd = new int[n][n];
      for (int i = 0; i < n - 1; i++)
        for (int j = i + 1; j < n; j++)
          gcd[i][j] = gcd(nums[i], nums[j]);
      
      int size = 1 << n;
      int[] f = new int[size];
      for (int s = 3; s < size; s++) {
        int cnt = Integer.bitCount(s);
        if (cnt % 2 == 0)
          for (int i = 0; i < n; i++)
            if (((s >> i) & 1) == 1)
              for (int j = i + 1; j < n; j++)
                if (((s >> j) & 1) == 1)
                  f[s] = Math.max(f[s], f[s ^ (1 << i) ^ (1 << j)] + cnt / 2 * gcd[i][j]);
      }
      return f[size - 1];
    }
    
    public static int gcd(int a, int b) {
      return b != 0 ?
          gcd(b, a % b) :
          a;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}