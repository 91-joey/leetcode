//<p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code> ，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的排列。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>换句话说，<code>s1</code> 的排列之一是 <code>s2</code> 的 <strong>子串</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s1 = "ab" s2 = "eidbaooo"
//<strong>输出：</strong>true
//<strong>解释：</strong>s2 包含 s1 的排列之一 ("ba").
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s1= "ab" s2 = "eidboaoo"
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>双指针</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 773</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

import java.util.Arrays;

// 567.字符串的排列
// 开题时间：2022-10-07 11:02:48
public class PermutationInString {
  public static void main(String[] args) {
    Solution solution = new PermutationInString().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 与「438.找到字符串中所有字母异位词」同解
    public boolean checkInclusion99(String s1, String s2) {
      int len1 = s1.length();
      int len2 = s2.length();
      if (len2 < len1)
        return false;
      
      // 字符计数
      int[] cnts = new int[123];
      for (int i = 0; i < len1; i++)
        cnts[s1.charAt(i)]++;
      
      char[] chars = s2.toCharArray();
      int cnt = 0;
      for (int i = 0; i < len1; i++)
        if (cnts[chars[i]]-- > 0)
          cnt++;
      if (cnt == len1)
        return true;
      
      for (int i = 0; i < len2 - len1; ) {
        if (cnts[chars[i + len1]]-- > 0)
          cnt++;
        if (++cnts[chars[i++]] > 0)
          cnt--;
        if (cnt == len1)
          return true;
      }
      
      return false;
    }
    
    // 固长滑窗 + 判断频率数组全为 0
    public boolean checkInclusion9(String s1, String s2) {
      int m = s1.length();
      if (m > s2.length())
        return false;
      int[] freq = new int[26];
      for (int i = 0; i < m; i++) {
        freq[s1.charAt(i) - 'a']++;
      }
      
      for (int i = 0; i < s2.length(); i++) {
        freq[s2.charAt(i) - 'a']--;
        if (i >= m)
          freq[s2.charAt(i - m) - 'a']++;
        if (Arrays.stream(freq).allMatch(x -> x == 0))
          return true;
      }
      
      return false;
    }
    
    //（优化）固长滑窗 + 维护字母频率不同的个数
    public boolean checkInclusion(String s1, String s2) {
      int m = s1.length();
      if (m > s2.length())
        return false;
      int[] freq = new int[26];
      for (int i = 0; i < m; i++)
        freq[s1.charAt(i) - 'a']++;
      int diffCnt = (int) Arrays.stream(freq).filter(x -> x != 0).count();
      
      char[] cs = s2.toCharArray();
      for (int i = 0; i < cs.length; i++) {
        int cnt = --freq[cs[i] - 'a'];
        if (cnt == 0)
          diffCnt--;
        else if (cnt == -1)
          diffCnt++;
        if (i >= m) {
          cnt = ++freq[cs[i - m] - 'a'];
          if (cnt == 0)
            diffCnt--;
          else if (cnt == 1)
            diffCnt++;
        }
        if (diffCnt == 0)
          return true;
      }
      
      return false;
    }
    
    //☆☆☆☆☆ （进一步优化）固长滑窗 + 维护核销的有效字母数
    public boolean checkInclusion8(String s1, String s2) {
      int m = s1.length();
      if (m > s2.length())
        return false;
      int[] freq = new int[26];
      for (int i = 0; i < m; i++)
        freq[s1.charAt(i) - 'a']++;
      
      char[] cs = s2.toCharArray();
      for (int i = 0, cnt = 0; i < cs.length; i++) {
        if (freq[cs[i] - 'a']-- > 0)
          cnt++;
        if (i >= m)
          if (++freq[cs[i - m] - 'a'] > 0)
            cnt++;
        if (cnt == m)
          return true;
      }
      
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}