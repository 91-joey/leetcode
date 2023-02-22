//<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和 <code>p</code>，找到&nbsp;<code>s</code><strong>&nbsp;</strong>中所有&nbsp;<code>p</code><strong>&nbsp;</strong>的&nbsp;<strong>异位词&nbsp;</strong>的子串，返回这些子串的起始索引。不考虑答案输出的顺序。</p>
//
//<p><strong>异位词 </strong>指由相同字母重排列形成的字符串（包括相同的字符串）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1:</strong></p>
//
//<pre>
//<strong>输入: </strong>s = "cbaebabacd", p = "abc"
//<strong>输出: </strong>[0,6]
//<strong>解释:</strong>
// 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
// 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//</pre>
//
//<p><strong>&nbsp;示例 2:</strong></p>
//
//<pre>
//<strong>输入: </strong>s = "abab", p = "ab"
//<strong>输出: </strong>[0,1,2]
//<strong>解释:</strong>
// 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
// 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
// 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>s</code>&nbsp;和&nbsp;<code>p</code>&nbsp;仅包含小写字母</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 1023</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

import java.util.ArrayList;
import java.util.List;

// 438.找到字符串中所有字母异位词
// 开题时间：2022-10-07 08:42:21
public class FindAllAnagramsInAString {
  public static void main(String[] args) {
    Solution solution = new FindAllAnagramsInAString().new Solution();
    System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 固定长度滑动窗口
     * 1.r++ until r==p.length-1
     * 2.judge if [l,r) is an anagram
     * 3.l++ r++ until r==s.length
     * 4.run step 2
     */
    public List<Integer> findAnagrams(String s, String p) {
      ArrayList<Integer> ans = new ArrayList<>();
      int lenP = p.length();
      int lenS = s.length();
      if (lenS < lenP)
        return ans;
      
      // 字符计数
      int[] cnts = new int[123];
      for (int i = 0; i < lenP; i++)
        cnts[p.charAt(i)]++;
      
      char[] chars = s.toCharArray();
      int cnt = 0;
      for (int i = 0; i < lenP; i++)
        if (cnts[chars[i]]-- > 0)
          cnt++;
      if (cnt == lenP)
        ans.add(0);
      
      for (int i = 0; i < lenS - lenP; ) {
        if (cnts[chars[i + lenP]]-- > 0)
          cnt++;
        if (++cnts[chars[i++]] > 0)
          cnt--;
        if (cnt == lenP)
          ans.add(i);
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}