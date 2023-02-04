//<p>给你一个数组&nbsp;<code>prices</code>&nbsp;，其中&nbsp;<code>prices[i]</code>&nbsp;是商店里第&nbsp;<code>i</code>&nbsp;件商品的价格。</p>
//
//<p>商店里正在进行促销活动，如果你要买第&nbsp;<code>i</code>&nbsp;件商品，那么你可以得到与 <code>prices[j]</code> 相等的折扣，其中&nbsp;<code>j</code>&nbsp;是满足&nbsp;<code>j &gt; i</code>&nbsp;且&nbsp;<code>prices[j] &lt;= prices[i]</code>&nbsp;的&nbsp;<strong>最小下标</strong>&nbsp;，如果没有满足条件的&nbsp;<code>j</code>&nbsp;，你将没有任何折扣。</p>
//
//<p>请你返回一个数组，数组中第&nbsp;<code>i</code>&nbsp;个元素是折扣后你购买商品 <code>i</code>&nbsp;最终需要支付的价格。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>prices = [8,4,6,2,3]
//<strong>输出：</strong>[4,2,4,2,3]
//<strong>解释：</strong>
// 商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
// 商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
// 商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
// 商品 3 和 4 都没有折扣。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>prices = [1,2,3,4,5]
//<strong>输出：</strong>[1,2,3,4,5]
//<strong>解释：</strong>在这个例子中，所有商品都没有折扣。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>prices = [10,1,1,6]
//<strong>输出：</strong>[9,0,1,6]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= prices.length &lt;= 500</code></li> 
// <li><code>1 &lt;= prices[i] &lt;= 10^3</code></li> 
//</ul>
//
//<div><li>👍 177</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.monotonic;

import java.util.Deque;
import java.util.LinkedList;

// 1475.商品折扣后的最终价格
// 开题时间：2022-12-28 18:29:52
public class FinalPricesWithASpecialDiscountInAShop {
  public static void main(String[] args) {
    Solution solution = new FinalPricesWithASpecialDiscountInAShop().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] finalPrices9(int[] prices) {
      int n = prices.length;
      for (int i = 0; i < n; i++) {
        int discount = 0;
        for (int j = i + 1; j < n; j++)
          if (prices[j] <= prices[i]) {
            discount = prices[j];
            break;
          }
        prices[i] -= discount;
      }
      return prices;
    }
    
    //☆☆☆☆☆ 单调递增栈（正序遍历、存索引）
    public int[] finalPrices8(int[] prices) {
      Deque<Integer> stack = new LinkedList<>();
      for (int i = 0; i < prices.length; i++) {
        while (!stack.isEmpty() && prices[stack.peek()] >= prices[i])
          prices[stack.pop()] -= prices[i];
        stack.push(i);
      }
      return prices;
    }
    
    //☆☆☆☆ 单调递减栈（倒序遍历、存值）
    public int[] finalPrices(int[] prices) {
      Deque<Integer> stack = new LinkedList<>();
      for (int i = prices.length - 1; i >= 0; i--) {
        while (!stack.isEmpty() && stack.peek() > prices[i])
          stack.pop();
        int tmp = prices[i];
        prices[i] -= stack.isEmpty() ? 0 : stack.peek();
        stack.push(tmp);
      }
      return prices;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}