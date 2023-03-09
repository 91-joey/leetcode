//<p>有一些不规则的硬币。在这些硬币中，<code>prob[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;枚硬币正面朝上的概率。</p>
//
//<p>请对每一枚硬币抛掷&nbsp;<strong>一次</strong>，然后返回正面朝上的硬币数等于&nbsp;<code>target</code>&nbsp;的概率。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>prob = [0.4], target = 1
//<strong>输出：</strong>0.40000
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>prob = [0.5,0.5,0.5,0.5,0.5], target = 0
//<strong>输出：</strong>0.03125
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= prob.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= prob[i] &lt;= 1</code></li> 
// <li><code>0 &lt;= target&nbsp;</code><code>&lt;= prob.length</code></li> 
// <li>如果答案与标准答案的误差在&nbsp;<code>10^-5</code>&nbsp;内，则被视为正确答案。</li> 
//</ul>
//
//<div><li>👍 60</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 1230.抛掷硬币
// 开题时间：2022-12-09 17:45:59
public class TossStrangeCoins {
  public static void main(String[] args) {
    Solution solution = new TossStrangeCoins().new Solution();
    //        System.out.println(solution.probabilityOfHeads(new double[]{0.4}, 1));
    System.out.println(solution.probabilityOfHeads(new double[]{0.2, 0.8, 0, 0.3, 0.5}, 3));
    //        System.out.println(solution.probabilityOfHeads(new double[]{0.5,0.5,0.5,0.5,0.5}, 0));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 概率dp
     * 状态定义：dp[i][j]:前 i 枚硬币，(j - 1)枚硬币正面朝上的概率(i>=j-1)
     * 状态转移：
     *      i == 1时，特殊考虑：
     *          j == 1时，f[i][j] =      f[0][0](0) * prob[i - 1] +  f[0][1](1) * (1 - prob[i - 1])
     *          j == 2时，f[i][j] =      f[0][1](1) * prob[i - 1] +  f[0][2](0) * (1 - prob[i - 1])
     *      i >  1时，    f[i][j] = f[i - 1][j - 1] * prob[i - 1] + f[i - 1][j] * (1 - prob[i - 1])
     * 初始化　：f[0][1] = 1.0，则 `i == 1` 和 `i >  1` 两种情况可以合并为一种考虑
     * 答案　　：f[n][target + 1]
     */
    public double probabilityOfHeads9(double[] prob, int target) {
      int n = prob.length;
      double[][] f = new double[n + 1][target + 2];
      f[0][1] = 1.0;
      
      for (int i = 1; i < n + 1; i++)
        for (int j = 1; j < Math.min(target + 2, i + 2); j++)
          f[i][j] = f[i - 1][j - 1] * prob[i - 1] + f[i - 1][j] * (1 - prob[i - 1]);
      
      return f[n][target + 1];
    }
  
    // ☆☆☆☆☆ 概率dp（一维优化）
    public double probabilityOfHeads(double[] prob, int target) {
      double[] f = new double[target + 2];
      f[1] = 1.0;
      
      for (int i = 0; i < prob.length; i++)
        for (int j = Math.min(target + 1, i + 2); j >= 1; j--)
          f[j] = f[j - 1] * prob[i] + f[j] * (1 - prob[i]);
      
      return f[target + 1];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}