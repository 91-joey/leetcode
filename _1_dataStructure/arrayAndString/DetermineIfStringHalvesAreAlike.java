//<p>给你一个偶数长度的字符串 <code>s</code> 。将其拆分成长度相同的两半，前一半为 <code>a</code> ，后一半为 <code>b</code> 。</p>
//
//<p>两个字符串 <strong>相似</strong> 的前提是它们都含有相同数目的元音（<code>'a'</code>，<code>'e'</code>，<code>'i'</code>，<code>'o'</code>，<code>'u'</code>，<code>'A'</code>，<code>'E'</code>，<code>'I'</code>，<code>'O'</code>，<code>'U'</code>）。注意，<code>s</code> 可能同时含有大写和小写字母。</p>
//
//<p>如果<em> </em><code>a</code><em> </em>和<em> </em><code>b</code> 相似，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "book"
//<strong>输出：</strong>true
//<strong>解释：</strong>a = "b<strong>o</strong>" 且 b = "<strong>o</strong>k" 。a 中有 1 个元音，b 也有 1 个元音。所以，a 和 b 相似。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "textbook"
//<strong>输出：</strong>false
//<strong>解释：</strong>a = "t<strong>e</strong>xt" 且 b = "b<strong>oo</strong>k" 。a 中有 1 个元音，b 中有 2 个元音。因此，a 和 b 不相似。
// 注意，元音 o 在 b 中出现两次，记为 2 个。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= s.length &lt;= 1000</code></li> 
// <li><code>s.length</code> 是偶数</li> 
// <li><code>s</code> 由 <strong>大写和小写</strong> 字母组成</li> 
//</ul>
//
//<div><li>👍 25</li><li>👎 0</li></div>
package _1_dataStructure.arrayAndString;

// 1704.判断字符串的两半是否相似
// 开题时间：2022-11-11 08:39:46
public class DetermineIfStringHalvesAreAlike {
  public static void main(String[] args) {
    Solution solution = new DetermineIfStringHalvesAreAlike().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean halvesAreAlike9(String s) {
      int cnt1 = 0, cnt2 = 0;
      for (int i = 0, half = s.length() >> 1; i < half; i++) {
        if (isVowel(s.charAt(i)))
          cnt1++;
        if (isVowel(s.charAt(i + half)))
          cnt2++;
      }
      return cnt1 == cnt2;
    }
    
    public boolean halvesAreAlike(String s) {
      int cnt1 = 0, cnt2 = 0;
      String vowels = "aeiouAEIOU";
      for (int i = 0, half = s.length() >> 1; i < half; i++) {
        if (vowels.indexOf(s.charAt(i)) >= 0)
          cnt1++;
        if (vowels.indexOf(s.charAt(i + half)) >= 0)
          cnt2++;
      }
      return cnt1 == cnt2;
    }
    
    public static boolean isVowel(char c) {
      return switch (c) {
        case 'a', 'e', 'i', 'o', 'u',
            'A', 'E', 'I', 'O', 'U' -> true;
        default -> false;
      };
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}