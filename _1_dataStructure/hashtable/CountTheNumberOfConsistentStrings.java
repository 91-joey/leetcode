//<p>给你一个由不同字符组成的字符串&nbsp;<code>allowed</code>&nbsp;和一个字符串数组&nbsp;<code>words</code>&nbsp;。如果一个字符串的每一个字符都在 <code>allowed</code>&nbsp;中，就称这个字符串是 <strong>一致字符串 </strong>。</p>
//
//<p>请你返回&nbsp;<code>words</code>&nbsp;数组中&nbsp;<strong>一致字符串</strong> 的数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
//<b>输出：</b>2
//<b>解释：</b>字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
//<b>输出：</b>7
//<b>解释：</b>所有字符串都是一致的。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<b>输入：</b>allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
//<b>输出：</b>4
//<b>解释：</b>字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= words.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= allowed.length &lt;=<sup> </sup>26</code></li> 
// <li><code>1 &lt;= words[i].length &lt;= 10</code></li> 
// <li><code>allowed</code>&nbsp;中的字符 <strong>互不相同</strong>&nbsp;。</li> 
// <li><code>words[i]</code> 和&nbsp;<code>allowed</code>&nbsp;只包含小写英文字母。</li> 
//</ul>
//
//<div><li>👍 59</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.HashSet;

// 1684.统计一致字符串的数目
// 开题时间：2022-11-08 10:51:31
public class CountTheNumberOfConsistentStrings {
  public static void main(String[] args) {
    Solution solution = new CountTheNumberOfConsistentStrings().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 哈希集合
    public int countConsistentStrings9(String allowed, String[] words) {
      HashSet<Character> set = new HashSet<>();
      for (int i = 0; i < allowed.length(); i++)
        set.add(allowed.charAt(i));
      
      int cnt = 0;
      out:
      for (String word : words) {
        for (int i = 0; i < word.length(); i++)
          if (!set.contains(word.charAt(i)))
            continue out;
        cnt++;
      }
      
      return cnt;
    }
    
    // 布尔数组
    public int countConsistentStrings8(String allowed, String[] words) {
      boolean[] allows = new boolean[123];
      for (int i = 0; i < allowed.length(); i++)
        allows[allowed.charAt(i)] = true;
      
      int cnt = 0;
      out:
      for (String word : words) {
        for (int i = 0; i < word.length(); i++)
          if (!allows[word.charAt(i)])
            continue out;
        cnt++;
      }
      
      return cnt;
    }
    
    // 位运算
    public int countConsistentStrings(String allowed, String[] words) {
      int mask = getMask(allowed);
      
      int cnt = 0;
      for (String word : words)
        cnt += (mask | getMask(word)) == mask ? 1 : 0;
      
      return cnt;
    }
    
    private int getMask(String allowed) {
      int mask = 0;
      for (int i = 0; i < allowed.length(); i++)
        mask |= 1 << (allowed.charAt(i) - 'a');
      return mask;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}