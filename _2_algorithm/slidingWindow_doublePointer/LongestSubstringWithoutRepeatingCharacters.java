//<p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的&nbsp;<strong>最长子串&nbsp;</strong>的长度。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1:</strong></p>
//
//<pre>
//<strong>输入: </strong>s = "abcabcbb"
//<strong>输出: </strong>3 
//<strong>解释:</strong> 因为无重复字符的最长子串是 <span><code>"abc"，所以其</code></span>长度为 3。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入: </strong>s = "bbbbb"
//<strong>输出: </strong>1
//<strong>解释: </strong>因为无重复字符的最长子串是 <span><code>"b"</code></span>，所以其长度为 1。
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入: </strong>s = "pwwkew"
//<strong>输出: </strong>3
//<strong>解释: </strong>因为无重复字符的最长子串是&nbsp;<span><code>"wke"</code></span>，所以其长度为 3。
//&nbsp;    请注意，你的答案必须是 <strong>子串 </strong>的长度，<span><code>"pwke"</code></span>&nbsp;是一个<em>子序列，</em>不是子串。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>s</code>&nbsp;由英文字母、数字、符号和空格组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 8239</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 3.无重复字符的最长子串
// 开题时间：2022-10-06 08:39:41
public class LongestSubstringWithoutRepeatingCharacters {
  public static void main(String[] args) {
    Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * [l,r) not duplicate?
     * r++
     * if duplicate,l++,r++
     */
    public int lengthOfLongestSubstring(String s) {
      int length = s.length();
      if (length <= 1)
        return length;
      
      int[] exists = new int[128];
      int size = 0;
      
      char[] chars = s.toCharArray();
      int l = 0;
      int r = 0;
      while (r < length) {
        if (exists[chars[r++]]++ == 0)
          size++;
        if (size < r - l)
          if (--exists[chars[l++]] == 0)
            size--;
      }
      
      return r - l;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}