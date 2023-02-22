//<p>给你一个四位&nbsp;<strong>正</strong>&nbsp;整数&nbsp;<code>num</code>&nbsp;。请你使用 <code>num</code>&nbsp;中的 <strong>数位</strong> ，将&nbsp;<code>num</code>&nbsp;拆成两个新的整数&nbsp;<code>new1</code>&nbsp;和&nbsp;<code>new2</code>&nbsp;。<code>new1</code> 和&nbsp;<code>new2</code>&nbsp;中可以有&nbsp;<strong>前导 0</strong>&nbsp;，且&nbsp;<code>num</code>&nbsp;中 <strong>所有</strong>&nbsp;数位都必须使用。</p>
//
//<ul> 
// <li>比方说，给你&nbsp;<code>num = 2932</code>&nbsp;，你拥有的数位包括：两个&nbsp;<code>2</code>&nbsp;，一个&nbsp;<code>9</code>&nbsp;和一个&nbsp;<code>3</code>&nbsp;。一些可能的&nbsp;<code>[new1, new2]</code>&nbsp;数对为&nbsp;<code>[22, 93]</code>，<code>[23, 92]</code>，<code>[223, 9]</code> 和&nbsp;<code>[2, 329]</code>&nbsp;。</li> 
//</ul>
//
//<p>请你返回可以得到的&nbsp;<code>new1</code>&nbsp;和 <code>new2</code>&nbsp;的 <strong>最小</strong>&nbsp;和。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>num = 2932
//<b>输出：</b>52
//<b>解释：</b>可行的 [new1, new2] 数对为 [29, 23] ，[223, 9] 等等。
// 最小和为数对 [29, 23] 的和：29 + 23 = 52 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>num = 4009
//<b>输出：</b>13
//<b>解释：</b>可行的 [new1, new2] 数对为 [0, 49] ，[490, 0] 等等。
// 最小和为数对 [4, 9] 的和：4 + 9 = 13 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1000 &lt;= num &lt;= 9999</code></li> 
//</ul>
//
//<div><li>👍 22</li><li>👎 0</li></div>
package _2_algorithm.greedy;

import java.util.Arrays;

// 2160.拆分数位后四位数字的最小和
// 开题时间：2022-12-08 18:21:32
public class MinimumSumOfFourDigitNumberAfterSplittingDigits {
  public static void main(String[] args) {
    Solution solution = new MinimumSumOfFourDigitNumberAfterSplittingDigits().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 贪心+排序
    public int minimumSum(int num) {
      int[] arr = new int[4];
      for (int i = 0; num != 0; num /= 10, i++)
        arr[i] = num % 10;
      Arrays.sort(arr);
      
      return (arr[0] + arr[1]) * 10 + arr[2] + arr[3];
    }
    
    public int minimumSum8(int num) {
      char[] cs = String.valueOf(num).toCharArray();
      Arrays.sort(cs);
      return (cs[0] + cs[1]) * 10 + cs[2] + cs[3] - 22 * '0';
      //            return (cs[0] + cs[1] - 2 * '0') * 10 + cs[2] + cs[3] - 2 * '0';
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}