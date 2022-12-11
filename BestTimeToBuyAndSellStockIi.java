//<p>给你一个整数数组 <code>prices</code> ，其中&nbsp;<code>prices[i]</code> 表示某支股票第 <code>i</code> 天的价格。</p>
//
//<p>在每一天，你可以决定是否购买和/或出售股票。你在任何时候&nbsp;<strong>最多</strong>&nbsp;只能持有 <strong>一股</strong> 股票。你也可以先购买，然后在 <strong>同一天</strong> 出售。</p>
//
//<p>返回 <em>你能获得的 <strong>最大</strong> 利润</em>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>prices = [7,1,5,3,6,4]
//<strong>输出：</strong>7
//<strong>解释：</strong>在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//&nbsp;    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
//     总利润为 4 + 3 = 7 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>prices = [1,2,3,4,5]
//<strong>输出：</strong>4
//<strong>解释：</strong>在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//&nbsp;    总利润为 4 。</pre>
//
//<p><strong>示例&nbsp;3：</strong></p>
//
//<pre>
//<strong>输入：</strong>prices = [7,6,4,3,1]
//<strong>输出：</strong>0
//<strong>解释：</strong>在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= prices.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 1918</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//122.买卖股票的最佳时机 II
//开题时间：2022-12-12 05:18:30
public class BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockIi().new Solution();
        System.out.println(solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //贪心（复杂版）
        public int maxProfit9(int[] prices) {
            int ans = 0;
            int n = prices.length;
            for (int l = 0, r = 0; l < n - 1; ) {
                while (l < n - 1 && prices[l] >= prices[l + 1])
                    l++;

                if (l >= n - 1)
                    break;

                r = l + 1;
                while (r < n - 1 && prices[r] <= prices[r + 1])
                    r++;

                ans += prices[r] - prices[l];

                l = r + 1;
            }
            return ans;
        }

        /*
         * 定义：f[n+1],前 n 天的最大利润
         * 转移方程：f[i]=max(f[j]+prices[i-1]-prices[j-1]),prices[i-1]-prices[j-1]>0, 1<=j<i
         */
        public int maxProfitX(int[] prices) {
            int n = prices.length + 1;
            int[] f = new int[n];

            for (int i = 2; i < n; i++) {
                for (int j = 1; j < i; j++) {
                    int profit = prices[i - 1] - prices[j - 1];
                    if (profit > 0)
                        f[i] = Math.max(f[i], f[j] + profit);
                }
            }

            return f[n - 1];
        }

        public int maxProfit8(int[] prices) {
            int n = prices.length;
            int[][] f = new int[n][2];
            f[0][1] = -prices[0];

            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
                f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
            }

            return f[n - 1][0];
        }

        /*
         * dp
         * 定义：f[n][k],前 i-1 天、持股状态为k时的最大现金（可能为负）
         *      k=0,不持股
         *      k=1, 持股
         * 转移方程：
         *      f[i][0]=max(f[i - 1][0],    f[i - 1][1] + prices[i])
         *      f[i][1]=max(f[i - 1][1],    f[i - 1][0] - prices[i])
         */
        public int maxProfit7(int[] prices) {
            int n = prices.length;
            int unhold = 0, hold = -prices[0];

            for (int i = 1; i < n; i++) {
                int tmp = unhold;
                unhold = Math.max(unhold, hold + prices[i]);
                hold = Math.max(hold, tmp - prices[i]);
            }

            return unhold;
        }

        //☆☆☆☆☆ 贪心
        public int maxProfit(int[] prices) {
            int ans = 0;

            for (int i = 1; i < prices.length; i++) {
                int diff = prices[i] - prices[i - 1];
                if (diff > 0)
                    ans += diff;
            }

            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}