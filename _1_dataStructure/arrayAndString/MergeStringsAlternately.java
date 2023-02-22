//<p>给你两个字符串 <code>word1</code> 和 <code>word2</code> 。请你从 <code>word1</code> 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。</p>
//
//<p>返回 <strong>合并后的字符串</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>word1 = "abc", word2 = "pqr"
//<strong>输出：</strong>"apbqcr"
//<strong>解释：</strong>字符串合并情况如下所示：
// word1：  a   b   c
// word2：    p   q   r
// 合并后：  a p b q c r
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>word1 = "ab", word2 = "pqrs"
//<strong>输出：</strong>"apbqrs"
//<strong>解释：</strong>注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
// word1：  a   b
// word2：    p   q   r   s
// 合并后：  a p b q   r   s
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>word1 = "abcd", word2 = "pq"
//<strong>输出：</strong>"apbqcd"
//<strong>解释：</strong>注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
// word1：  a   b   c   d
// word2：    p   q
// 合并后：  a p b q c   d
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= word1.length, word2.length &lt;= 100</code></li> 
// <li><code>word1</code> 和 <code>word2</code> 由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 74</li><li>👎 0</li></div>
package _1_dataStructure.arrayAndString;

// 1768.交替合并字符串
// 开题时间：2022-12-08 09:17:01
public class MergeStringsAlternately {
  public static void main(String[] args) {
    Solution solution = new MergeStringsAlternately().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public String mergeAlternately9(String word1, String word2) {
      int m = word1.length();
      int n = word2.length();
      StringBuilder sb = new StringBuilder(m + n);
      
      int i = 0;
      while (i < m && i < n)
        sb.append(word1.charAt(i)).append(word2.charAt(i++));
      
      sb.append(i == m ? word2.substring(i) : word1.substring(i));
      return sb.toString();
    }
    
    public String mergeAlternately(String word1, String word2) {
      int m = word1.length();
      int n = word2.length();
      StringBuilder sb = new StringBuilder(m + n);
      
      for (int i = 0; i < m || i < n; i++) {
        if (i < m) sb.append(word1.charAt(i));
        if (i < n) sb.append(word2.charAt(i));
      }
      
      return sb.toString();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}