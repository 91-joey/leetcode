//<p><strong>丑数 </strong>就是只包含质因数&nbsp;<code>2</code>、<code>3</code> 和 <code>5</code>&nbsp;的正整数。</p>
//
//<p>给你一个整数 <code>n</code> ，请你判断 <code>n</code> 是否为 <strong>丑数</strong> 。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 6
//<strong>输出：</strong>true
//<strong>解释：</strong>6 = 2 × 3</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出：</strong>true
//<strong>解释：</strong>1 没有质因数，因此它的全部质因数是 {2, 3, 5} 的空集。习惯上将其视作第一个丑数。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 14
//<strong>输出：</strong>false
//<strong>解释：</strong>14 不是丑数，因为它包含了另外一个质因数&nbsp;<span><code>7 </code></span>。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><li>👍 359</li><li>👎 0</li></div>
package _2_algorithm.maths;

// 263.丑数
// 开题时间：2022-12-15 15:52:13
public class UglyNumber {
  public static void main(String[] args) {
    Solution solution = new UglyNumber().new Solution();
    System.out.println(solution.isUgly(-2147483648));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean isUgly(int n) {
      if (n <= 0) return false;
      while (n % 2 == 0) n /= 2;
      while (n % 3 == 0) n /= 3;
      while (n % 5 == 0) n /= 5;
      return n == 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}