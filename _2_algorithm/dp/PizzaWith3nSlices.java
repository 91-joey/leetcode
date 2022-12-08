//<p>给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨：</p>
//
//<ul> 
// <li>你挑选 <strong>任意</strong>&nbsp;一块披萨。</li> 
// <li>Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。</li> 
// <li>Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。</li> 
// <li>重复上述过程直到没有披萨剩下。</li> 
//</ul>
//
//<p>每一块披萨的大小按顺时针方向由循环数组 <code>slices</code>&nbsp;表示。</p>
//
//<p>请你返回你可以获得的披萨大小总和的最大值。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/03/21/sample_3_1723.png" style="height: 240px; width: 475px;" /></p>
//
//<pre>
//<strong>输入：</strong>slices = [1,2,3,4,5,6]
//<strong>输出：</strong>10
//<strong>解释：</strong>选择大小为 4 的披萨，Alice 和 Bob 分别挑选大小为 3 和 5 的披萨。然后你选择大小为 6 的披萨，Alice 和 Bob 分别挑选大小为 2 和 1 的披萨。你获得的披萨总大小为 4 + 6 = 10 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/03/21/sample_4_1723.png" style="height: 250px; width: 475px;" /></strong></p>
//
//<pre>
//<strong>输入：</strong>slices = [8,9,8,6,1,1]
//<strong>输出：</strong>16
//<strong>解释：</strong>两轮都选大小为 8 的披萨。如果你选择大小为 9 的披萨，你的朋友们就会选择大小为 8 的披萨，这种情况下你的总和不是最大的。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= slices.length &lt;= 500</code></li> 
// <li><code>slices.length % 3 == 0</code></li> 
// <li><code>1 &lt;= slices[i] &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 111</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;

//1388.3n 块披萨
//开题时间：2022-11-26 16:39:40
public class PizzaWith3nSlices {
    public static void main(String[] args) {
        Solution solution = new PizzaWith3nSlices().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //DP官解（拷贝数组）
        public int maxSizeSlices9(int[] slices) {
            int n = slices.length;
            return Math.max(
                    getMax(Arrays.copyOfRange(slices, 0, n - 1)),
                    getMax(Arrays.copyOfRange(slices, 1, n))
            );
        }

        private int getMax(int[] slices) {
            int n = slices.length;
            int choose = (n + 1) / 3;
            int[][] dp = new int[n + 1][choose + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= Math.min(choose, i / 2 + 1); j++) {
                    dp[i][j] = Math.max(
                            (i >= 2 ? dp[i - 2][j - 1] : 0) + slices[i - 1],
                            dp[i - 1][j]);
                }
            }
            return dp[n][choose];
        }

        //DP官解（不拷贝数组）
        public int maxSizeSlices(int[] slices) {
            return Math.max(
                    getMax(slices, 0),
                    getMax(slices, 1)
            );
        }

        private int getMax(int[] slices, int start) {
            int n = slices.length;
            int choose = n / 3;
            int[][] dp = new int[n + 1][choose + 1];

            for (int i = 2; i <= n; i++)
                for (int j = 1; j <= choose; j++)
                    dp[i][j] = Math.max(
                            dp[i - 2][j - 1] + slices[start + i - 2],
                            dp[i - 1][j]);

            return dp[n][choose];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}