//<p>给定一个整数数组
// <meta charset="UTF-8" /><code>prices</code>，其中第&nbsp;<em>&nbsp;</em><code>prices[i]</code>&nbsp;表示第&nbsp;<code><em>i</em></code>&nbsp;天的股票价格 。​</p>
//
//<p>设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:</p>
//
//<ul> 
// <li>卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。</li> 
//</ul>
//
//<p><strong>注意：</strong>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> prices = [1,2,3,0,2]
//<strong>输出: </strong>3 
//<strong>解释:</strong> 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> prices = [1]
//<strong>输出:</strong> 0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= prices.length &lt;= 5000</code></li> 
// <li><code>0 &lt;= prices[i] &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 1362</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 309.最佳买卖股票时机含冷冻期
// 开题时间：2022-12-12 06:26:13
public class BestTimeToBuyAndSellStockWithCooldown {
  public static void main(String[] args) {
    Solution solution = new BestTimeToBuyAndSellStockWithCooldown().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    
    /*
     * dp
     * 定义：f[n][k],前 i-1 天、持股状态为k时的最大现金（可能为负）
     *      k=0,不持股
     *      k=1, 持股
     * 转移方程：
     *      f[i][0]=max(f[i - 1][0],    f[i - 1][1] + prices[i])
     *      f[i][1]=max(f[i - 1][1],    f[i - 2][0] - prices[i])
     */
    public int maxProfit9(int[] prices) {
      int n = prices.length;
      int[][] f = new int[n][2];
      f[0][1] = -prices[0];
      
      for (int i = 1; i < n; i++) {
        f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
        f[i][1] = Math.max(f[i - 1][1], (i > 1 ? f[i - 2][0] : 0) - prices[i]);
      }
      
      return f[n - 1][0];
    }
    
    public int maxProfit(int[] prices) {
      int n = prices.length;
      int a = 0, c = 0, d = -prices[0];
      
      for (int i = 1; i < n; i++) {
        int tmpC = c;
        c = Math.max(c, d + prices[i]);
        d = Math.max(d, a - prices[i]);
        a = tmpC;
      }
      
      return c;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}