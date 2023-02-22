//<p>如果一个密码满足以下所有条件，我们称它是一个 <strong>强</strong>&nbsp;密码：</p>
//
//<ul> 
// <li>它有至少 <code>8</code>&nbsp;个字符。</li> 
// <li>至少包含 <strong>一个小写英文</strong>&nbsp;字母。</li> 
// <li>至少包含 <strong>一个大写英文</strong>&nbsp;字母。</li> 
// <li>至少包含 <strong>一个数字</strong>&nbsp;。</li> 
// <li>至少包含 <strong>一个特殊字符</strong>&nbsp;。特殊字符为：<code>"!@#$%^&amp;*()-+"</code>&nbsp;中的一个。</li> 
// <li>它 <strong>不</strong>&nbsp;包含&nbsp;<code>2</code>&nbsp;个连续相同的字符（比方说&nbsp;<code>"aab"</code>&nbsp;不符合该条件，但是&nbsp;<code>"aba"</code>&nbsp;符合该条件）。</li> 
//</ul>
//
//<p>给你一个字符串&nbsp;<code>password</code>&nbsp;，如果它是一个&nbsp;<strong>强</strong>&nbsp;密码，返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>password = "IloveLe3tcode!"
//<b>输出：</b>true
//<b>解释：</b>密码满足所有的要求，所以我们返回 true 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>password = "Me+You--IsMyDream"
//<b>输出：</b>false
//<b>解释：</b>密码不包含数字，且包含 2 个连续相同的字符。所以我们返回 false 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>password = "1aB!"
//<b>输出：</b>false
//<b>解释：</b>密码不符合长度要求。所以我们返回 false 。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= password.length &lt;= 100</code></li> 
// <li><code>password</code>&nbsp;包含字母，数字和&nbsp;<code>"!@#$%^&amp;*()-+"</code>&nbsp;这些特殊字符。</li> 
//</ul>
//
//<div><li>👍 16</li><li>👎 0</li></div>
package _1_dataStructure.arrayAndString;

import java.util.Set;
import java.util.stream.Collectors;

// 2299.强密码检验器 II
// 开题时间：2023-01-19 10:26:49
public class StrongPasswordCheckerIi {
  public static void main(String[] args) {
    Solution solution = new StrongPasswordCheckerIi().new Solution();
    System.out.println(solution);
    String s = "!@#$%^&*()-+";
    //        for (int i = 0; i < s.length(); i++) {
    //            System.out.println((int) s.charAt(i));
    //        }
    s.chars().sorted().forEach(System.out::println);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final String SPECIALS = "!@#$%^&*()-+";
    public static final Set<Character> SPECIALS_SET = SPECIALS.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
    
    public boolean strongPasswordCheckerII9(String password) {
      if (password.length() < 8)
        return false;
      
      boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;
      char[] cs = password.toCharArray();
      for (int i = 0; i < cs.length; i++) {
        char c = cs[i];
        if (i > 0 && cs[i - 1] == c)
          return false;
        
        if (Character.isLowerCase(c))
          hasLower = true;
        else if (Character.isUpperCase(c))
          hasUpper = true;
        else if (Character.isDigit(c))
          hasDigit = true;
          //                else if (SPECIALS.indexOf(c) != -1)
        else if (SPECIALS_SET.contains(c))
          hasSpecial = true;
      }
      
      return hasLower && hasUpper && hasDigit && hasSpecial;
    }
    
    //☆☆☆☆☆ 状态压缩
    public boolean strongPasswordCheckerII(String password) {
      if (password.length() < 8)
        return false;
      
      int mask = 0;
      char[] cs = password.toCharArray();
      for (int i = 0; i < cs.length; i++) {
        char c = cs[i];
        if (i > 0 && cs[i - 1] == c)
          return false;
        
        if (Character.isLowerCase(c))
          mask |= 1;
        else if (Character.isUpperCase(c))
          mask |= 2;
        else if (Character.isDigit(c))
          mask |= 4;
        else
          mask |= 8;
      }
      
      return mask == 15;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}