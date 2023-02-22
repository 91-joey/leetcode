//<p>给你长度相等的两个字符串 <code>s1</code> 和 <code>s2</code> 。一次<strong> 字符串交换 </strong>操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。</p>
//
//<p>如果对 <strong>其中一个字符串</strong> 执行 <strong>最多一次字符串交换</strong> 就可以使两个字符串相等，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>s1 = "bank", s2 = "kanb"
//<strong>输出：</strong>true
//<strong>解释：</strong>例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>s1 = "attack", s2 = "defend"
//<strong>输出：</strong>false
//<strong>解释：</strong>一次字符串交换无法使两个字符串相等
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>s1 = "kelb", s2 = "kelb"
//<strong>输出：</strong>true
//<strong>解释：</strong>两个字符串已经相等，所以不需要进行字符串交换
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>s1 = "abcd", s2 = "dcba"
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s1.length, s2.length &lt;= 100</code></li> 
// <li><code>s1.length == s2.length</code></li> 
// <li><code>s1</code> 和 <code>s2</code> 仅由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 97</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

// 1790.仅执行一次字符串交换能否使两个字符串相等
// 开题时间：2022-12-04 12:26:20
public class CheckIfOneStringSwapCanMakeStringsEqual {
  public static void main(String[] args) {
    Solution solution = new CheckIfOneStringSwapCanMakeStringsEqual().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean areAlmostEqual9(String s1, String s2) {
      int cntDiff = 0;
      char a = 0, b = 0;
      for (int i = 0; i < s1.length(); i++)
        if (s1.charAt(i) != s2.charAt(i)) {
          cntDiff++;
          if (cntDiff > 2)
            return false;
          else if (cntDiff == 1) {
            a = s1.charAt(i);
            b = s2.charAt(i);
          } else if (a != s2.charAt(i) || b != s1.charAt(i))
            return false;
        }
      return cntDiff != 1;
    }
    
    //☆☆☆☆☆ 计数变量+第一次字符不同的索引变量
    public boolean areAlmostEqual(String s1, String s2) {
      int cntDiff = 0;
      for (int i = 0, idx = -1; i < s1.length(); i++)
        if (s1.charAt(i) != s2.charAt(i)) {
          cntDiff++;
          if (cntDiff > 2)
            return false;
          else if (cntDiff == 1)
            idx = i;
          else if (s1.charAt(idx) != s2.charAt(i) || s2.charAt(idx) != s1.charAt(i))
            return false;
        }
      return cntDiff != 1;
    }
    
    // 三叶姐
    public boolean areAlmostEqual8(String s1, String s2) {
      int a = -1, b = -1;
      
      for (int i = 0; i < s1.length(); i++)
        if (s1.charAt(i) != s2.charAt(i))
          if (a == -1)
            a = i;
          else if (b == -1)
            b = i;
          else
            return false;
      
      if (a == -1)
        return true;
      else if (b == -1)
        return false;
      else
        return s1.charAt(a) == s2.charAt(b) && s1.charAt(b) == s2.charAt(a);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}