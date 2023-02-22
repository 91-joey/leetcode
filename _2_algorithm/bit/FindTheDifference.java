//<p>给定两个字符串 <code>s</code> 和 <code>t</code>&nbsp;，它们只包含小写字母。</p>
//
//<p>字符串 <code>t</code>&nbsp;由字符串 <code>s</code> 随机重排，然后在随机位置添加一个字母。</p>
//
//<p>请找出在 <code>t</code>&nbsp;中被添加的字母。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "abcd", t = "abcde"
//<strong>输出：</strong>"e"
//<strong>解释：</strong>'e' 是那个被添加的字母。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "", t = "y"
//<strong>输出：</strong>"y"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= s.length &lt;= 1000</code></li> 
// <li><code>t.length == s.length + 1</code></li> 
// <li><code>s</code> 和 <code>t</code> 只包含小写字母</li> 
//</ul>
//
//<div><li>👍 367</li><li>👎 0</li></div>
package _2_algorithm.bit;

import java.util.Arrays;
import java.util.stream.IntStream;

// 389.找不同
// 开题时间：2022-12-08 10:08:46
public class FindTheDifference {
  public static void main(String[] args) {
    Solution solution = new FindTheDifference().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public char findTheDifference9(String s, String t) {
      int ans = 0;
      for (int i = 0; i < s.length(); i++) ans ^= s.charAt(i);
      for (int i = 0; i < t.length(); i++) ans ^= t.charAt(i);
      return (char) ans;
    }
    
    public char findTheDifference6(String s, String t) {
      return (char) (s + t).chars().reduce(0, (a, b) -> a ^ b);
    }
    
    public char findTheDifference(String s, String t) {
      return (char) IntStream.concat(s.chars(), t.chars()).reduce(0, (a, b) -> a ^ b);
    }
    
    public char findTheDifference8(String s, String t) {
      int[] freq = new int[123];
      
      for (int i = 0; i < s.length(); i++)
        freq[s.charAt(i)]++;
      
      for (int i = 0; ; i++)
        if (freq[t.charAt(i)]-- == 0)
          return t.charAt(i);
    }
    
    public char findTheDifference7(String s, String t) {
      char[] cs1 = s.toCharArray();
      char[] cs2 = t.toCharArray();
      Arrays.sort(cs1);
      Arrays.sort(cs2);
      
      for (int i = 0; ; i++)
        if (i >= cs1.length || cs1[i] != cs2[i])
          return cs2[i];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}