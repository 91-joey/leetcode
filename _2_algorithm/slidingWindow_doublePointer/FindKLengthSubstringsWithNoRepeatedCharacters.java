//<p>给你一个字符串&nbsp;<code>S</code>，找出所有长度为&nbsp;<code>K</code>&nbsp;且不含重复字符的子串，请你返回全部满足要求的子串的&nbsp;<strong>数目</strong>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>S = "havefunonleetcode", K = 5
//<strong>输出：</strong>6
//<strong>解释：</strong>
// 这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>S = "home", K = 5
//<strong>输出：</strong>0
//<strong>解释：</strong>
// 注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ol> 
// <li><code>1 &lt;= S.length &lt;= 10^4</code></li> 
// <li><code>S</code> 中的所有字符均为小写英文字母</li> 
// <li><code>1 &lt;= K &lt;= 10^4</code></li> 
//</ol>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 43</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 1100.长度为 K 的无重复字符子串
// 开题时间：2022-10-12 14:26:22
public class FindKLengthSubstringsWithNoRepeatedCharacters {
  public static void main(String[] args) {
    Solution solution = new FindKLengthSubstringsWithNoRepeatedCharacters().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * k 值检查
     *   - 由于字符均为小写英文字母，故当 'k > 26'时，所有子串必有重复字符。
     *   - 当 'k > S.length'时，不存在子串。
     *
     * 用一个计数数组 `freq`，保存子串中字符对应的字符数
     * 同时维护一个变量 'cnt'，保存子串中的不同字符数
     *   当 'cnt == k'时，子串即满足要求。
     *   而不用遍历计数数组。
     */
    // 固长滑动窗口
    public int numKLenSubstrNoRepeats(String s, int k) {
      int len = s.length();
      if (k > 26 || k > len)
        return 0;
      
      int[] freq = new int[123];
      int cnt = 0;
      int ans = 0;
      char[] chars = s.toCharArray();
      
      // 初始化窗口
      for (int i = 0; i < k; i++)
        if (freq[chars[i]]++ == 0)
          cnt++;
      if (cnt == k)
        ans++;
      
      // 固定窗口大小，向右滑动
      for (int l = 0, r = k; r < len; ) {
        if (--freq[chars[l++]] == 0)
          cnt--;
        if (freq[chars[r++]]++ == 0)
          cnt++;
        if (cnt == k)
          ans++;
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}