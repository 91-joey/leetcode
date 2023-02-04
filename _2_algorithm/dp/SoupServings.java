//<p>有&nbsp;<strong>A&nbsp;和&nbsp;B 两种类型&nbsp;</strong>的汤。一开始每种类型的汤有&nbsp;<code>n</code>&nbsp;毫升。有四种分配操作：</p>
//
//<ol> 
// <li>提供 <code>100ml</code> 的 <strong>汤A</strong> 和 <code>0ml</code> 的 <strong>汤B</strong> 。</li> 
// <li>提供 <code>75ml</code> 的 <strong>汤A</strong> 和 <code>25ml</code> 的 <strong>汤B</strong> 。</li> 
// <li>提供 <code>50ml</code> 的 <strong>汤A</strong> 和 <code>50ml</code> 的 <strong>汤B</strong> 。</li> 
// <li>提供 <code>25ml</code> 的 <strong>汤A</strong> 和 <code>75ml</code> 的 <strong>汤B</strong> 。</li> 
//</ol>
//
//<p>当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为 <code>0.25</code> 的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。</p>
//
//<p><strong>注意&nbsp;</strong>不存在先分配 <code>100</code> ml <strong>汤B</strong> 的操作。</p>
//
//<p>需要返回的值：&nbsp;<strong>汤A&nbsp;</strong>先分配完的概率 +&nbsp;&nbsp;<strong>汤A和汤B&nbsp;</strong>同时分配完的概率 / 2。返回值在正确答案&nbsp;<code>10<sup>-5</sup></code>&nbsp;的范围内将被认为是正确的。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> n = 50
//<strong>输出:</strong> 0.62500
//<strong>解释:</strong>如果我们选择前两个操作<strong>，</strong>A 首先将变为空。
// 对于第三个操作，A 和 B 会同时变为空。
// 对于第四个操作，B 首先将变为空。<strong>
//</strong>所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> n = 100
//<strong>输出:</strong> 0.71875
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>0 &lt;= n &lt;= 10<sup>9</sup></code>​​​​​​​</li> 
//</ul>
//
//<div><li>👍 102</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;

// 808.分汤
// 开题时间：2022-11-21 09:03:46
public class SoupServings {
  public static void main(String[] args) {
    Solution solution = new SoupServings().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // DP
    public double soupServings9(int n) {
      if (n >= 4475)
        return 1.0;
      
      n = (int) Math.ceil(n / 25.0);
      int len = n + 1;
      double[][] dp = new double[len][len];
      dp[0][0] = 0.5;
      Arrays.fill(dp[0], 1, len, 1.0);
      for (int i = 1; i < len; i++)
        for (int j = 1; j < len; j++)
          dp[i][j] = 0.25 * (
              dp[Math.max(0, i - 4)][j] +
                  dp[Math.max(0, i - 3)][j - 1] +
                  dp[Math.max(0, i - 2)][Math.max(0, j - 2)] +
                  dp[i - 1][Math.max(0, j - 3)]
          );
      
      return dp[n][n];
    }
    
    double[][] memo;
    
    // DP + 记忆化搜索
    public double soupServings(int n) {
      if (n >= 4475)
        return 1.0;
      
      n = (n + 24) / 25;
      memo = new double[n + 1][n + 1];
      return dfs(n, n);
    }
    
    private double dfs(int a, int b) {
      if (a <= 0 && b <= 0)
        return 0.5;
      else if (a <= 0)
        return 1.0;
      else if (b <= 0)
        return 0.0;
      
      if (memo[a][b] == 0)
        memo[a][b] = 0.25 * (
            dfs(Math.max(0, a - 4), b) +
                dfs(Math.max(0, a - 3), b - 1) +
                dfs(Math.max(0, a - 2), Math.max(0, b - 2)) +
                dfs(a - 1, Math.max(0, b - 3))
        );
      
      return memo[a][b];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}