//<p>给定一个字符串&nbsp;<code>s</code>&nbsp;，找到 <em>它的第一个不重复的字符，并返回它的索引</em> 。如果不存在，则返回 <code>-1</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入:</strong> s = "leetcode"
//<strong>输出:</strong> 0
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> s = "loveleetcode"
//<strong>输出:</strong> 2
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> s = "aabb"
//<strong>输出:</strong> -1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>s</code>&nbsp;只包含小写字母</li> 
//</ul>
//
//<div><li>👍 624</li><li>👎 0</li></div>
package _2_algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

// 387.字符串中的第一个唯一字符
// 开题时间：2022-12-03 12:26:15
public class FirstUniqueCharacterInAString {
  public static void main(String[] args) {
    Solution solution = new FirstUniqueCharacterInAString().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int firstUniqChar9(String s) {
      int[][] freq2idx = new int[26][2];
      for (int i = s.length() - 1; i >= 0; i--) {
        int idx = s.charAt(i) - 'a';
        freq2idx[idx][0]++;
        freq2idx[idx][1] = i;
      }
      
      return Arrays.stream(freq2idx)
          .filter(arr -> arr[0] == 1)
          .min(Comparator.comparingInt(arr -> arr[1]))
          .orElse(new int[]{-1, -1})[1];
    }
    
    // 2次遍历
    public int firstUniqChar(String s) {
      int[] freq = new int[123];
      for (int i = 0; i < s.length(); i++)
        freq[s.charAt(i)]++;
      
      for (int i = 0; i < s.length(); i++)
        if (freq[s.charAt(i)] == 1)
          return i;
      
      return -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}