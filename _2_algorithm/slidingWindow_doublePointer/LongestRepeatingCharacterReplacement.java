//<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 <code>k</code> 次。</p>
//
//<p>在执行上述操作后，返回包含相同字母的最长子字符串的长度。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ABAB", k = 2
//<strong>输出：</strong>4
//<strong>解释：</strong>用两个'A'替换为两个'B',反之亦然。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "AABABBA", k = 1
//<strong>输出：</strong>4
//<strong>解释：</strong>
// 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
// 子串 "BBBB" 有最长重复字母, 答案为 4。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>s</code> 仅由大写英文字母组成</li> 
// <li><code>0 &lt;= k &lt;= s.length</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 702</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 424.替换后的最长重复字符
// 开题时间：2022-10-05 14:39:21
public class LongestRepeatingCharacterReplacement {
  public static void main(String[] args) {
    Solution solution = new LongestRepeatingCharacterReplacement().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 最高频的字符以外的字符数为 k
    /*
     * 1. r++        直到「非最高频字符数」= k + 1
     * 2. l++ r++    直到「非最高频字符数」= k
     * 3. 执行步骤 1.
     */
    public int characterReplacement(String s, int k) {
      int len = s.length();
            /*
            由于字符仅限大写英文字母，设字符串长度为 len
            len 在 [1 ,26] 时，最高频字符数 >= 1
            len 在 [27,52] 时，最高频字符数 >= 2
            len 在 [53,78] 时，最高频字符数 >= 3
            故 k >= len - (len + 25) / 26 时，最长子串长度为 len
            */
      if (k >= len - (len + 25) / 26) {
        return len;
      }
      char[] chars = s.toCharArray();
      int[] cCnt = new int[91];
      int maxCnt = 1;
      int l = 0;
      int r = 0;
      // [l,r) <= k
      while (r < len) {
        maxCnt = Math.max(maxCnt, ++cCnt[chars[r++]]);
        while (r - l - maxCnt > k) {
          cCnt[chars[l++]]--;
          /*  这里不需要更新 maxCnt
           * l!=maxCntIdx maxCnt不变
           * l==maxCntIdx
           *   r==maxCntIdx maxCnt不变
           *   r!=maxCntIdx
           *       ++cCnt[s.charAt(r++)] <= maxCnt maxCnt-- 这种情况 `r - l - maxCnt > k` 仍成立
           *       ++cCnt[s.charAt(r++)] >  maxCnt maxCnt不变 maxCnt++
           */
        }
      }
      
      return r - l;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}