//<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> ，请你找出&nbsp;<strong>至多&nbsp;</strong>包含<em> <code>k</code></em> 个 <strong>不同</strong> 字符的最长子串，并返回该子串的长度。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "eceba", k = 2
//<strong>输出：</strong>3
//<strong>解释：</strong>满足题目要求的子串是 "ece" ，长度为 3 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aa", k = 1
//<strong>输出：</strong>2
//<strong>解释：</strong>满足题目要求的子串是 "aa" ，长度为 2 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= k &lt;= 50</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 213</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 340.至多包含 K 个不同字符的最长子串
// 开题时间：2022-10-13 11:44:29
public class LongestSubstringWithAtMostKDistinctCharacters {
  public static void main(String[] args) {
    Solution solution = new LongestSubstringWithAtMostKDistinctCharacters().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
      int len = s.length();
      if (k >= len)
        return len;
      if (k == 0)
        return 0;
      
      int[] freq = new int[128];
      char[] chars = s.toCharArray();
      int cnt = 0;
      int l = 0;
      int r = 0;
      while (r < len) {
        if (freq[chars[r++]]++ == 0)
          cnt++;
        if (cnt > k)
          if (--freq[chars[l++]] == 0)
            cnt--;
      }
      return r - l;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}