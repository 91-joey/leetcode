//<p>给你一个字符串 <code>s</code>&nbsp;，它只包含三种字符 a, b 和 c 。</p>
//
//<p>请你返回 a，b 和 c 都&nbsp;<strong>至少&nbsp;</strong>出现过一次的子字符串数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>s = "abcabc"
//<strong>输出：</strong>10
//<strong>解释：</strong>包含 a，b 和 c 各至少一次的子字符串为<em> "</em>abc<em>", "</em>abca<em>", "</em>abcab<em>", "</em>abcabc<em>", "</em>bca<em>", "</em>bcab<em>", "</em>bcabc<em>", "</em>cab<em>", "</em>cabc<em>" </em>和<em> "</em>abc<em>" </em>(<strong>相同</strong><strong>字符串算多次</strong>)<em>。</em>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>s = "aaacb"
//<strong>输出：</strong>3
//<strong>解释：</strong>包含 a，b 和 c 各至少一次的子字符串为<em> "</em>aaacb<em>", "</em>aacb<em>" </em>和<em> "</em>acb<em>" 。</em>
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>s = "abc"
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>3 &lt;= s.length &lt;= 5 x 10^4</code></li> 
// <li><code>s</code>&nbsp;只包含字符 a，b 和 c 。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 84</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 1358.包含所有三种字符的子字符串数目
// 开题时间：2022-10-15 08:58:32
public class NumberOfSubstringsContainingAllThreeCharacters {
  public static void main(String[] args) {
    Solution solution = new NumberOfSubstringsContainingAllThreeCharacters().new Solution();
    System.out.println(solution.numberOfSubstrings2("abcabc"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * aaaabbaacb
     */
    // 反向子数组区间
    public int numberOfSubstrings(String s) {
      int[] freq = new int[100];
      char[] chars = s.toCharArray();
      int cnt = 0;
      //[l,r) abc都至少出现过一次
      for (int l = 0, r = 0, size = 0; r < chars.length; ) {
        if (freq[chars[r++]]++ == 0)
          size++;
        if (size == 3) {
          while (freq[chars[l]] > 1)
            freq[chars[l++]]--;
          cnt += l + 1;
        }
      }
      return cnt;
    }
    
    // 正向子数组区间
    public int numberOfSubstrings2(String s) {
      int[] freq = new int[100];
      char[] chars = s.toCharArray();
      int cnt = 0;
      int len = chars.length;
      for (int l = 0, r = 0, size = 0; r < len; r++) {
        if (freq[chars[r]]++ == 0)
          size++;
        if (size == 3) {
          int incre = len - r;
          do {
            cnt += incre;
          } while (freq[chars[l++]]-- > 1);
          size = 2;
        }
      }
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}