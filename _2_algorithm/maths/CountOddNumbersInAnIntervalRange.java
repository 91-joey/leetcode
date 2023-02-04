//<p>给你两个非负整数&nbsp;<code>low</code> 和&nbsp;<code>high</code>&nbsp;。请你返回<em>&nbsp;</em><code>low</code><em> </em>和<em>&nbsp;</em><code>high</code><em>&nbsp;</em>之间（包括二者）奇数的数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>low = 3, high = 7
//<strong>输出：</strong>3
//<strong>解释：</strong>3 到 7 之间奇数数字为 [3,5,7] 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>low = 8, high = 10
//<strong>输出：</strong>1
//<strong>解释：</strong>8 到 10 之间奇数数字为 [9] 。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= low &lt;= high&nbsp;&lt;= 10^9</code></li> 
//</ul>
//
//<div><li>👍 77</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

// 1523.在区间范围内统计奇数数目
// 开题时间：2022-12-01 17:14:07
public class CountOddNumbersInAnIntervalRange {
  public static void main(String[] args) {
    Solution solution = new CountOddNumbersInAnIntervalRange().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int countOdds9(int low, int high) {
      int n = high - low + 1;
      return n % 2 == 1 && low % 2 == 1 ? (n + 1) / 2 : n / 2;
    }
    
    // 容斥原理（前缀和）
    public int countOdds8(int low, int high) {
      return countOddsPreVal(high) - countOddsPreVal(low - 1);
    }
    
    //[0,val]内的奇数个数
    private int countOddsPreVal(int val) {
      return (val + 1) >> 1;
    }
    
    /*
     * 找规律：
     *   low  为奇数，则减一对结果无影响
     *   high 为奇数，则加一对结果无影响
     */
    public int countOdds(int low, int high) {
      if ((low & 1) == 1) low--;
      if ((high & 1) == 1) high++;
      return (high - low) >> 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}