//<p>把字符串 <code>s</code> 看作 <code>"abcdefghijklmnopqrstuvwxyz"</code>&nbsp;的无限环绕字符串，所以&nbsp;<code>s</code> 看起来是这样的：</p>
//
//<ul> 
// <li><code>"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...."</code> 。</li> 
//</ul>
//
//<p>现在给定另一个字符串 <code>p</code> 。返回&nbsp;<code>s</code> 中 <strong>不同 </strong>的 <code>p</code> 的 <strong>非空子串</strong>&nbsp;的数量&nbsp;。&nbsp;</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>p = "a"
//<strong>输出：</strong>1
//<strong>解释：</strong>字符串 s 中只有 p 的一个 "a" 子字符。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>p = "cac"
//<strong>输出：</strong>2
//<strong>解释：</strong>字符串 s 中只有 p 的两个子串 ("a", "c") 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>p = "zab"
//<strong>输出：</strong>6
//<strong>解释：</strong>在字符串 s 中有 p 的六个子串 ("z", "a", "b", "za", "ab", "zab") 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= p.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>p</code>&nbsp;由小写英文字母组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 353</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.HashSet;

// 467.环绕字符串中唯一的子字符串
// 开题时间：2022-10-15 11:01:31
public class UniqueSubstringsInWraparoundString {
  public static void main(String[] args) {
    Solution solution = new UniqueSubstringsInWraparoundString().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    //"zabxabc"
    // 哈希表（超时）
    public int findSubstringInWraproundString(String p) {
      char[] chars = p.toCharArray();
      //            int cnt = 1;
      HashSet<String> set = new HashSet<>();
      set.add("" + chars[0] + 0);
      //[l,r] 为环绕字符串
      for (int l = 0, r = 1; r < chars.length; r++) {
        int diff = chars[r] - chars[r - 1];
        if (diff == 1 || diff == -25) {
          //                    cnt += r - l + 1;
          for (int i = 0; i <= r - l; i++)
            set.add("" + chars[r] + i);
        } else {
          l = r;
          //                    cnt++;
          set.add("" + chars[r] + 0);
        }
      }
      //            return cnt;
      return set.size();
    }
    
    // 动态规划
    public int findSubstringInWraproundString2(String p) {
      char[] chars = p.toCharArray();
      int[] dp = new int[123];
      dp[chars[0]] = 1;
      for (int i = 1, k = 1; i < chars.length; i++) {
        int diff = chars[i] - chars[i - 1];
        if (diff == 1 || diff == -25)
          k++;
        else
          k = 1;
        dp[chars[i]] = Math.max(dp[chars[i]], k);
      }
      
      int cnt = 0;
      for (int i = 97; i < dp.length; i++)
        cnt += dp[i];
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}