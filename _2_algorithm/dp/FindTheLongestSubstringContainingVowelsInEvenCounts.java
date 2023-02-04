//<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即&nbsp;'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "eleetminicoworoep"
//<strong>输出：</strong>13
//<strong>解释：</strong>最长子字符串是 "leetminicowor" ，它包含 <strong>e，i，o</strong>&nbsp;各 2 个，以及 0 个 <strong>a</strong>，<strong>u </strong>。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "leetcodeisgreat"
//<strong>输出：</strong>5
//<strong>解释：</strong>最长子字符串是 "leetc" ，其中包含 2 个 <strong>e</strong> 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "bcbcbc"
//<strong>输出：</strong>6
//<strong>解释：</strong>这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 <strong>a，</strong><strong>e，</strong><strong>i，</strong><strong>o，</strong><strong>u</strong> 都出现了 0 次。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 5 x 10^5</code></li> 
// <li><code>s</code>&nbsp;只包含小写英文字母。</li> 
//</ul>
//
//<div><li>👍 432</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;

// 1371.每个元音包含偶数次的最长子字符串
// 开题时间：2022-12-16 18:19:48
public class FindTheLongestSubstringContainingVowelsInEvenCounts {
  public static void main(String[] args) {
    Solution solution = new FindTheLongestSubstringContainingVowelsInEvenCounts().new Solution();
    System.out.println(solution.findTheLongestSubstring("eleetminicoworoep"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 异或前缀和 + 双层循环
    public int findTheLongestSubstring9(String s) {
      int n = s.length() + 1;
      int[] a = new int[n];
      int[] e = new int[n];
      int[] i = new int[n];
      int[] o = new int[n];
      int[] u = new int[n];
      for (int j = 0; j < n - 1; j++) {
        a[j + 1] = a[j];
        e[j + 1] = e[j];
        i[j + 1] = i[j];
        o[j + 1] = o[j];
        u[j + 1] = u[j];
        switch (s.charAt(j)) {
          case 'a' -> a[j + 1] ^= 1;
          case 'e' -> e[j + 1] ^= 1;
          case 'i' -> i[j + 1] ^= 1;
          case 'o' -> o[j + 1] ^= 1;
          case 'u' -> u[j + 1] ^= 1;
        }
      }
      
      for (int len = n - 1; len > 0; len--) {
        for (int j = 0; j < n - len; j++) {
          int k = j + len;
          if ((a[j] ^ a[k]) == 0 &&
              (e[j] ^ e[k]) == 0 &&
              (i[j] ^ i[k]) == 0 &&
              (o[j] ^ o[k]) == 0 &&
              (u[j] ^ u[k]) == 0)
            return len;
        }
      }
      
      return 0;
    }
    
    // 异或前缀和 + 双层循环（精简版）
    public int findTheLongestSubstring(String s) {
      int n = s.length() + 1;
      int[][] state = new int[n][5];
      
      for (int i = 1; i < n; i++) {
        for (int j = 0; j < 5; j++)
          state[i][j] = state[i - 1][j];
        switch (s.charAt(i - 1)) {
          case 'a' -> state[i][0] ^= 1;
          case 'e' -> state[i][1] ^= 1;
          case 'i' -> state[i][2] ^= 1;
          case 'o' -> state[i][3] ^= 1;
          case 'u' -> state[i][4] ^= 1;
        }
      }
      
      for (int len = n - 1; len > 0; len--) {
        for (int i = 0; i < n - len; i++) {
          int j = i + len;
          boolean even = true;
          for (int k = 0; k < 5 && even; k++)
            even = (state[i][k] ^ state[j][k]) == 0;
          if (even)
            return len;
        }
      }
      
      return 0;
    }
    
    //☆☆☆☆☆ 异或前缀和 + 状态压缩 + 数组哈希
    public int findTheLongestSubstring8(String s) {
      int[] state2idx = new int[32];
      Arrays.fill(state2idx, Integer.MIN_VALUE);
      state2idx[0] = -1;
      
      int max = 0;
      for (int i = 0, state = 0; i < s.length(); i++) {
        switch (s.charAt(i)) {
          case 'a' -> state ^= (1 << 0);
          case 'e' -> state ^= (1 << 1);
          case 'i' -> state ^= (1 << 2);
          case 'o' -> state ^= (1 << 3);
          case 'u' -> state ^= (1 << 4);
        }
        if (state2idx[state] >= -1)
          max = Math.max(max, i - state2idx[state]);
        else
          state2idx[state] = i;
      }
      
      return max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}