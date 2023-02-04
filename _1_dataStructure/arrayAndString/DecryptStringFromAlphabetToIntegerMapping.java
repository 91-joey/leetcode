//<p>给你一个字符串&nbsp;<code>s</code>，它由数字（<code>'0'</code> - <code>'9'</code>）和&nbsp;<code>'#'</code>&nbsp;组成。我们希望按下述规则将&nbsp;<code>s</code>&nbsp;映射为一些小写英文字符：</p>
//
//<ul> 
// <li>字符（<code>'a'</code> - <code>'i'</code>）分别用（<code>'1'</code> -&nbsp;<code>'9'</code>）表示。</li> 
// <li>字符（<code>'j'</code> - <code>'z'</code>）分别用（<code>'10#'</code>&nbsp;-&nbsp;<code>'26#'</code>）表示。&nbsp;</li> 
//</ul>
//
//<p>返回映射之后形成的新字符串。</p>
//
//<p>题目数据保证映射始终唯一。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "10#11#12"
//<strong>输出：</strong>"jkab"
//<strong>解释：</strong>"j" -&gt; "10#" , "k" -&gt; "11#" , "a" -&gt; "1" , "b" -&gt; "2".
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "1326#"
//<strong>输出：</strong>"acz"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 1000</code></li> 
// <li><code>s[i]</code> 只包含数字（<code>'0'</code>-<code>'9'</code>）和&nbsp;<code>'#'</code>&nbsp;字符。</li> 
// <li><code>s</code>&nbsp;是映射始终存在的有效字符串。</li> 
//</ul>
//
//<div><li>👍 75</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

// 1309.解码字母到整数映射
// 开题时间：2022-12-09 09:15:17
public class DecryptStringFromAlphabetToIntegerMapping {
  public static void main(String[] args) {
    Solution solution = new DecryptStringFromAlphabetToIntegerMapping().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 正序遍历
    public String freqAlphabets9(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      StringBuilder sb = new StringBuilder(n);
      
      for (int i = 0; i < n; )
        if (i + 2 >= n || cs[i + 2] != '#')
          sb.append((char) (cs[i++] + 'a' - '1'));
        else {
          sb.append((char) ((cs[i] - '0') * 10 + cs[i + 1] - '0' + 'j' - 10));
          i += 3;
        }
      
      return sb.toString();
    }
    
    // 倒序遍历
    public String freqAlphabets8(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      StringBuilder sb = new StringBuilder(n);
      
      for (int i = n - 1; i >= 0; )
        if (cs[i] != '#')
          sb.append((char) (cs[i--] + 'a' - '1'));
        else {
          sb.append((char) ((cs[i - 2] - '0') * 10 + cs[i - 1] - '0' + 'j' - 10));
          i -= 3;
        }
      
      return sb.reverse().toString();
    }
    
    // 倒序遍历+子字符串转数字
    public String freqAlphabets(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      StringBuilder sb = new StringBuilder(n);
      
      for (int i = n - 1; i >= 0; )
        if (cs[i] != '#')
          sb.append((char) (cs[i--] + 'a' - '1'));
        else {
          sb.append((char) (Integer.parseInt(s.substring(i - 2, i)) + 'j' - 10));
          i -= 3;
        }
      
      return sb.reverse().toString();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}