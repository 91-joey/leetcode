//<p>给你两个下标从 <strong>0</strong> 开始的字符串 <code>s</code> 和 <code>target</code> 。你可以从 <code>s</code> 取出一些字符并将其重排，得到若干新的字符串。</p>
//
//<p>从 <code>s</code> 中取出字符并重新排列，返回可以形成 <code>target</code> 的 <strong>最大</strong> 副本数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>s = "ilovecodingonleetcode", target = "code"
//<strong>输出：</strong>2
//<strong>解释：</strong>
// 对于 "code" 的第 1 个副本，选取下标为 4 、5 、6 和 7 的字符。
// 对于 "code" 的第 2 个副本，选取下标为 17 、18 、19 和 20 的字符。
// 形成的字符串分别是 "ecod" 和 "code" ，都可以重排为 "code" 。
// 可以形成最多 2 个 "code" 的副本，所以返回 2 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>s = "abcba", target = "abc"
//<strong>输出：</strong>1
//<strong>解释：</strong>
// 选取下标为 0 、1 和 2 的字符，可以形成 "abc" 的 1 个副本。
// 可以形成最多 1 个 "abc" 的副本，所以返回 1 。
// 注意，尽管下标 3 和 4 分别有额外的 'a' 和 'b' ，但不能重用下标 2 处的 'c' ，所以无法形成 "abc" 的第 2 个副本。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>s = "abbaccaddaeea", target = "aaaaa"
//<strong>输出：</strong>1
//<strong>解释：</strong>
// 选取下标为 0 、3 、6 、9 和 12 的字符，可以形成 "aaaaa" 的 1 个副本。
// 可以形成最多 1 个 "aaaaa" 的副本，所以返回 1 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 100</code></li> 
// <li><code>1 &lt;= target.length &lt;= 10</code></li> 
// <li><code>s</code> 和 <code>target</code> 由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 22</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

// 2287.重排字符形成目标字符串
// 开题时间：2023-01-13 09:45:32
public class RearrangeCharactersToMakeTargetString {
  public static void main(String[] args) {
    Solution solution = new RearrangeCharactersToMakeTargetString().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int rearrangeCharacters(String s, String t) {
      int[] freqS = new int[26];
      int[] freqT = new int[26];
      for (int i = 0; i < s.length(); i++) freqS[s.charAt(i) - 'a']++;
      for (int i = 0; i < t.length(); i++) freqT[t.charAt(i) - 'a']++;
      
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < 26; i++)
        if (freqT[i] > 0)
          min = Math.min(min, freqS[i] / freqT[i]);
      
      return min;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}