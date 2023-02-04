//<p>你总共有&nbsp;<code>n</code><em>&nbsp;</em>枚硬币，并计划将它们按阶梯状排列。对于一个由 <code>k</code> 行组成的阶梯，其第 <code>i</code><em> </em>行必须正好有 <code>i</code><em> </em>枚硬币。阶梯的最后一行 <strong>可能</strong> 是不完整的。</p>
//
//<p>给你一个数字&nbsp;<code>n</code><em> </em>，计算并返回可形成 <strong>完整阶梯行</strong> 的总行数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/04/09/arrangecoins1-grid.jpg" style="width: 253px; height: 253px;" /> 
//<pre>
//<strong>输入：</strong>n = 5
//<strong>输出：</strong>2
//<strong>解释：</strong>因为第三行不完整，所以返回 2 。
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/04/09/arrangecoins2-grid.jpg" style="width: 333px; height: 333px;" /> 
//<pre>
//<strong>输入：</strong>n = 8
//<strong>输出：</strong>3
//<strong>解释：</strong>因为第四行不完整，所以返回 3 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><li>👍 251</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

// 441.排列硬币
// 开题时间：2022-11-25 13:42:48
public class ArrangingCoins {
  public static void main(String[] args) {
    Solution solution = new ArrangingCoins().new Solution();
    System.out.println(solution.arrangeCoins(1));
    System.out.println(solution.arrangeCoins(2));
    System.out.println(solution.arrangeCoins(3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int arrangeCoins9(int n) {
      return (int) (0.5 * (Math.sqrt(8L * n + 1) - 1));
    }
    
    public int arrangeCoins8(int n) {
      int l = 1, r = n;
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if ((long) mid * (mid + 1) / 2 >= n)
          r = mid;
        else
          l = mid + 1;
      }
      return (long) r * (r + 1) / 2 == n ? r : r - 1;
    }
    
    public int arrangeCoins7(int n) {
      int l = 1, r = n;
      while (l < r) {
        int mid = ((r - l + 1) >> 1) + l;
        if ((long) mid * (mid + 1) / 2 > n)
          r = mid - 1;
        else
          l = mid;
      }
      return r;
    }
    
    public int arrangeCoins(int n) {
      int l = 1, r = n;
      while (l <= r) {
        int mid = ((r - l) >> 1) + l;
        long coins = (long) mid * (mid + 1) / 2;
        if (coins == n)
          return mid;
        if (coins > n)
          r = mid - 1;
        else
          l = mid + 1;
      }
      return r;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}