//<p>给你一个字符串 <code>s</code>&nbsp;和一个整数 <code>k</code>&nbsp;。请你用 <code>s</code>&nbsp;字符串中 <strong>所有字符</strong>&nbsp;构造 <code>k</code>&nbsp;个非空 <strong>回文串</strong>&nbsp;。</p>
//
//<p>如果你可以用&nbsp;<code>s</code>&nbsp;中所有字符构造&nbsp;<code>k</code>&nbsp;个回文字符串，那么请你返回 <strong>True</strong>&nbsp;，否则返回&nbsp;<strong>False</strong>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "annabelle", k = 2
//<strong>输出：</strong>true
//<strong>解释：</strong>可以用 s 中所有字符构造 2 个回文字符串。
// 一些可行的构造方案包括："anna" + "elble"，"anbna" + "elle"，"anellena" + "b"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "leetcode", k = 3
//<strong>输出：</strong>false
//<strong>解释：</strong>无法用 s 中所有字符构造 3 个回文串。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "true", k = 4
//<strong>输出：</strong>true
//<strong>解释：</strong>唯一可行的方案是让 s 中每个字符单独构成一个字符串。
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "yzyzyzyzyzyzyzy", k = 2
//<strong>输出：</strong>true
//<strong>解释：</strong>你只需要将所有的 z 放在一个字符串中，所有的 y 放在另一个字符串中。那么两个字符串都是回文串。
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "cr", k = 7
//<strong>输出：</strong>false
//<strong>解释：</strong>我们没有足够的字符去构造 7 个回文串。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10^5</code></li> 
// <li><code>s</code>&nbsp;中所有字符都是小写英文字母。</li> 
// <li><code>1 &lt;= k &lt;= 10^5</code></li> 
//</ul>
//
//<div><li>👍 44</li><li>👎 0</li></div>
package _2_algorithm.greedy;

import java.util.Arrays;

// 1400.构造 K 个回文字符串
// 开题时间：2023-01-25 12:27:19
public class ConstructKPalindromeStrings {
  public static void main(String[] args) {
    Solution solution = new ConstructKPalindromeStrings().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean canConstruct9(String s, int k) {
      if (s.length() < k)
        return false;
      int[] freq = new int[26];
      for (int i = 0; i < s.length(); i++) {
        int idx = s.charAt(i) - 'a';
        freq[idx] ^= 1;// 利用 异或位运算 进行 0-1 交替变换
      }
      return Arrays.stream(freq).sum() <= k;
    }
    
    /*
     * ☆☆☆☆☆ 贪心 + 状态压缩
     * 首先字符串长度 >= 1，所以， len(s) 一定要 >= k
     * 其次，回文字符串的长度分奇偶性，除了奇数长度的回文字符串中间的一个字符没有成对、其余字符都是成对存在。
     *
     * 我们对字符串 s 中的字符进行计数：
     *  - 若字符频率为偶数，则可以任意分配字符对（2个相同字符），去新建一个回文字符串、或者加在已有回文字符串的两侧（插入亦可，只要对称）
     *  - 若字符频率为奇数，除去能够分配的字符对，还剩余一个无法成对的字符，这个字符只能成为奇数长度回文字符串的中心
     *
     * 总结，频率为奇数的字符，只能一人带一个「奇数长度回文字符串」（设一共 x 个），则剩余的 k - x 个为「偶数长度回文字符串」
     *  k - x >= 0，即 x <= k
     *
     * 由于字符限定为小写英文字母，一共 26 个，故可以进行状态压缩
     */
    public boolean canConstruct8(String s, int k) {
      if (s.length() < k)
        return false;
      int freq = 0;
      for (int i = 0; i < s.length(); i++)
        freq ^= (1 << (s.charAt(i) - 'a'));
      return Integer.bitCount(freq) <= k;
    }
    
    // 流炫技
    public boolean canConstruct(String s, int k) {
      if (s.length() < k)
        return false;
      return Integer.bitCount(s.chars().reduce(0, (freq, c) -> freq ^ (1 << (c - 'a')))) <= k;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}