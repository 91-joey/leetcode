//<p>给定一个整数数组&nbsp;<code>prices</code>，其中 <code>prices[i]</code>表示第&nbsp;<code>i</code>&nbsp;天的股票价格 ；整数&nbsp;<code>fee</code> 代表了交易股票的手续费用。</p>
//
//<p>你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。</p>
//
//<p>返回获得利润的最大值。</p>
//
//<p><strong>注意：</strong>这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>prices = [1, 3, 2, 8, 4, 9], fee = 2
//<strong>输出：</strong>8
//<strong>解释：</strong>能够达到的最大利润:  
//在此处买入&nbsp;prices[0] = 1
//在此处卖出 prices[3] = 8
//在此处买入 prices[4] = 4
//在此处卖出 prices[5] = 9
//总利润:&nbsp;((8 - 1) - 2) + ((9 - 4) - 2) = 8</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>prices = [1,3,7,5,10,3], fee = 3
//<strong>输出：</strong>6
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= prices.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= prices[i] &lt; 5 * 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= fee &lt; 5 * 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 828</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//714.买卖股票的最佳时机含手续费
//开题时间：2022-12-12 07:32:51
public class BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        Solution solution = new BestTimeToBuyAndSellStockWithTransactionFee().new Solution();
        System.out.println(solution.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp
        public int maxProfit9(int[] prices, int fee) {
            int n = prices.length;
            int[][] f = new int[n][2];
            f[0][1] = -prices[0];

            for (int i = 1; i < n; i++) {
                f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i] - fee);
                f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
            }

            return f[n - 1][0];
        }

        //☆☆☆☆☆ 贪心
        public int maxProfit(int[] prices, int fee) {
            //买入价，算入手续费
            //在最大化收益的前提下，如果我们手上拥有一支股票，那么它的最低买入价格是多少
            int buy = prices[0] + fee;
            int ans = 0;
            for (int i = 1; i < prices.length; ++i) {
                if (prices[i] + fee < buy) {
                    buy = prices[i] + fee;
                } else if (prices[i] > buy) {
                    ans += prices[i] - buy;
                    buy = prices[i];
                }
            }
            return ans;
        }

        //dp 空间优化
        public int maxProfi8(int[] prices, int fee) {
            int n = prices.length;
            int unhold = 0, hold = -prices[0];

            for (int i = 1; i < n; i++) {
                int tmp = unhold;
                unhold = Math.max(unhold, hold + prices[i] - fee);
                hold = Math.max(hold, tmp - prices[i]);
            }

            return unhold;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}