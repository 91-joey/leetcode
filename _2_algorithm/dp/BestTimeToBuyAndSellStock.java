//<p>给定一个数组 <code>prices</code> ，它的第&nbsp;<code>i</code> 个元素&nbsp;<code>prices[i]</code> 表示一支给定股票第 <code>i</code> 天的价格。</p>
//
//<p>你只能选择 <strong>某一天</strong> 买入这只股票，并选择在 <strong>未来的某一个不同的日子</strong> 卖出该股票。设计一个算法来计算你所能获取的最大利润。</p>
//
//<p>返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 <code>0</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>[7,1,5,3,6,4]
//<strong>输出：</strong>5
//<strong>解释：</strong>在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>prices = [7,6,4,3,1]
//<strong>输出：</strong>0
//<strong>解释：</strong>在这种情况下, 没有交易完成, 所以最大利润为 0。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 2677</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 121.买卖股票的最佳时机
// 开题时间：2022-11-30 08:51:40
public class BestTimeToBuyAndSellStock {
  public static void main(String[] args) {
    Solution solution = new BestTimeToBuyAndSellStock().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // max[i]=max(arr[i+1...n-1])-arr[i]
    // max=max(arr[0...n-1])
    public int maxProfit9(int[] prices) {
      int max = 0;
      int n = prices.length;
      
      int[] maxes = new int[n];
      maxes[n - 1] = prices[n - 1];
      
      for (int i = n - 2; i >= 0; i--) {
        maxes[i] = Math.max(maxes[i + 1], prices[i]);
        max = Math.max(max, maxes[i + 1] - prices[i]);
      }
      
      return max;
    }
    
    public int maxProfit8(int[] prices) {
      int max = 0;
      
      for (int i = prices.length - 1, maxPrice = Integer.MIN_VALUE; i >= 0; i--) {
        maxPrice = Math.max(maxPrice, prices[i]);
        max = Math.max(max, maxPrice - prices[i]);
      }
      
      return max;
    }
    
    /*
     * dp[i]表示以arr[i]结尾的最大利润
     * 则有dp[i]=max(dp[i-1],arr[i]-min(arr[0...i])
     */
    public int maxProfit(int[] prices) {
      int max = 0;
      
      for (int i = 0, minPrice = Integer.MAX_VALUE; i < prices.length; i++) {
        minPrice = Math.min(minPrice, prices[i]);
        max = Math.max(max, prices[i] - minPrice);
      }
      
      return max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}