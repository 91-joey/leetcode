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
package org.example.leetcode.problems;

import java.util.Arrays;

//1230.抛掷硬币
//开题时间：2022-12-09 17:45:59
public class TossStrangeCoins {
    public static void main(String[] args) {
        Solution solution = new TossStrangeCoins().new Solution();
        System.out.println(solution.probabilityOfHeads(new double[]{0.4}, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double probabilityOfHeads(double[] prob, int target) {
            int n = prob.length + 1;
            double[][] f = new double[n][target + 2];
            Arrays.fill(f[0], 1.0);
            f[1][0] = 1.0;
            f[1][1] = 1 - prob[0];
            if (target > 0)
                f[1][2] = prob[0];

            for (int i = 2; i < n; i++)
                for (int j = 1; j < Math.min(target + 2, i); j++)
                    f[i][j] = f[i - 1][j - 1] * prob[i - 1] + f[i - 1][j] * (1 - prob[i - 1]);

            return f[n - 1][target + 1];
        }

        public double probabilityOfHeads9(double[] prob, int target) {
            int n = prob.length;
            double[][] f = new double[n][target + 2];
            f[0][0] = 1 - prob[0];
            f[0][1] = prob[1];

            for (int i = 1; i < n; i++)
                for (int j = 1; j < target + 2; j++)
                    f[i][j] = f[i - 1][j - 1] * prob[i] + f[i - 1][j] * (1 - prob[i]);

            return f[n - 1][target + 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}