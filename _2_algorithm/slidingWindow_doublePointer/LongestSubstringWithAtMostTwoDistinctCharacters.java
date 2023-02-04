// 给你一个字符串 <code>s</code> ，请你找出&nbsp;<strong>至多&nbsp;</strong>包含 <strong>两个不同字符</strong> 的最长子串，并返回该子串的长度。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "eceba"
//<strong>输出：</strong>3
//<strong>解释：</strong>满足题目要求的子串是 "ece" ，长度为 3 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ccaabbb"
//<strong>输出：</strong>5
//<strong>解释：</strong>满足题目要求的子串是 "aabbb" ，长度为 5 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>s</code> 由英文字母组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 185</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 159.至多包含两个不同字符的最长子串
// 开题时间：2022-10-13 11:23:13
public class LongestSubstringWithAtMostTwoDistinctCharacters {
  public static void main(String[] args) {
    Solution solution = new LongestSubstringWithAtMostTwoDistinctCharacters().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
      int[] freq = new int[123];
      char[] chars = s.toCharArray();
      int cnt = 0;
      int l = 0;
      int r = 0;
      while (r < chars.length) {
        if (freq[chars[r++]]++ == 0)
          cnt++;
        if (cnt > 2)
          if (--freq[chars[l++]] == 0)
            cnt--;
      }
      return r - l;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}