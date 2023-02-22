//<p>给你一个下标从 <strong>0</strong>&nbsp;开始长度为 <code>n</code>&nbsp;的字符串&nbsp;<code>num</code>&nbsp;，它只包含数字。</p>
//
//<p>如果对于 <strong>每个</strong><em>&nbsp;</em><code>0 &lt;= i &lt; n</code>&nbsp;的下标&nbsp;<code>i</code>&nbsp;，都满足数位<em>&nbsp;</em><code>i</code>&nbsp;在 <code>num</code>&nbsp;中出现了&nbsp;<code>num[i]</code>次，那么请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>num = "1210"
//<b>输出：</b>true
//<strong>解释：</strong>
// num[0] = '1' 。数字 0 在 num 中出现了一次。
// num[1] = '2' 。数字 1 在 num 中出现了两次。
// num[2] = '1' 。数字 2 在 num 中出现了一次。
// num[3] = '0' 。数字 3 在 num 中出现了零次。
//"1210" 满足题目要求条件，所以返回 true 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>num = "030"
//<b>输出：</b>false
//<strong>解释：</strong>
// num[0] = '0' 。数字 0 应该出现 0 次，但是在 num 中出现了一次。
// num[1] = '3' 。数字 1 应该出现 3 次，但是在 num 中出现了零次。
// num[2] = '0' 。数字 2 在 num 中出现了 0 次。
// 下标 0 和 1 都违反了题目要求，所以返回 false 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == num.length</code></li> 
// <li><code>1 &lt;= n &lt;= 10</code></li> 
// <li><code>num</code>&nbsp;只包含数字。</li> 
//</ul>
//
//<div><li>👍 21</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

// 2283.判断一个数的数字计数是否等于数位的值
// 开题时间：2023-01-11 09:39:06
public class CheckIfNumberHasEqualDigitCountAndDigitValue {
  public static void main(String[] args) {
    Solution solution = new CheckIfNumberHasEqualDigitCountAndDigitValue().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 数组计数
    public boolean digitCount(String num) {
      int[] freq = new int[10];
      for (int i = 0; i < num.length(); i++)
        freq[num.charAt(i) - '0']++;
      
      for (int i = 0; i < num.length(); i++)
        if (num.charAt(i) - '0' != freq[i])
          return false;
      
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}