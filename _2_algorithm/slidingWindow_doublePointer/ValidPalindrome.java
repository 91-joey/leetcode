//<p>如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 <strong>回文串</strong> 。</p>
//
//<p>字母和数字都属于字母数字字符。</p>
//
//<p>给你一个字符串 <code>s</code>，如果它是 <strong>回文串</strong> ，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code><em> </em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入:</strong> s = "A man, a plan, a canal: Panama"
//<strong>输出：</strong>true
//<strong>解释：</strong>"amanaplanacanalpanama" 是回文串。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "race a car"
//<strong>输出：</strong>false
//<strong>解释：</strong>"raceacar" 不是回文串。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = " "
//<strong>输出：</strong>true
//<strong>解释：</strong>在移除非字母数字字符之后，s 是一个空字符串 "" 。
// 由于空字符串正着反着读都一样，所以是回文串。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li> 
// <li><code>s</code> 仅由可打印的 ASCII 字符组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 581</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 125.验证回文串
// 开题时间：2022-10-23 17:03:39
public class ValidPalindrome {
  public static void main(String[] args) {
    Solution solution = new ValidPalindrome().new Solution();
    System.out.println(solution.isPalindrome2("A man, a plan, a canal: Panama"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean isPalindrome(String s) {
      byte[] bytes = s.getBytes();
      for (int l = 0, r = bytes.length - 1; l < r; l++) {
        if ((65 <= bytes[l] && bytes[l] <= 90) ||
            (97 <= bytes[l] && bytes[l] <= 122)) {
          while (l < r && !((48 <= bytes[r] && bytes[r] <= 57) ||
              (65 <= bytes[r] && bytes[r] <= 90) ||
              (97 <= bytes[r] && bytes[r] <= 122)))
            r--;
          if (l == r) break;
          if (65 > bytes[r] || !(bytes[l] == bytes[r] || Math.abs(bytes[l] - bytes[r]) == 32))
            return false;
          else
            r--;
        } else if (48 <= bytes[l] && bytes[l] <= 57) {
          while (l < r && !((48 <= bytes[r] && bytes[r] <= 57) ||
              (65 <= bytes[r] && bytes[r] <= 90) ||
              (97 <= bytes[r] && bytes[r] <= 122)))
            r--;
          if (l == r) break;
          if (bytes[l] != bytes[r--])
            return false;
        }
      }
      return true;
    }
    
    // 移除无效字符 + 字符串反转比较
    public boolean isPalindrome2(String s) {
      var sb = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
        var c = s.charAt(i);
        if (Character.isLetterOrDigit(c))
          sb.append(Character.toLowerCase(c));
      }
      return sb.toString().equals(sb.reverse().toString());
    }
    
    // 移除无效字符 + 双指针比较
    public boolean isPalindrome3(String s) {
      var sb = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
        var c = s.charAt(i);
        if (Character.isLetterOrDigit(c))
          sb.append(Character.toLowerCase(c));
      }
      for (int l = 0, r = sb.length() - 1; l < r; )
        if (sb.charAt(l++) != sb.charAt(r--))
          return false;
      return true;
    }
    
    //☆☆☆☆☆ 原地双指针比较
    public boolean isPalindrome4(String s) {
      for (int l = 0, r = s.length() - 1; l < r; ) {
        while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
        while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
        if (l < r && Character.toLowerCase(s.charAt(l++)) != Character.toLowerCase(s.charAt(r--)))
          return false;
      }
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}