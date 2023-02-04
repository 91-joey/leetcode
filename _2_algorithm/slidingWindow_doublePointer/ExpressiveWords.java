//<p>有时候人们会用重复写一些字母来表示额外的感受，比如 <code>"hello" -&gt; "heeellooo"</code>, <code>"hi" -&gt; "hiii"</code>。我们将相邻字母都相同的一串字符定义为相同字母组，例如："h", "eee", "ll", "ooo"。</p>
//
//<p>对于一个给定的字符串 S ，如果另一个单词能够通过将一些字母组扩张从而使其和 S 相同，我们将这个单词定义为可扩张的（stretchy）。扩张操作定义如下：选择一个字母组（包含字母&nbsp;<code>c</code>&nbsp;），然后往其中添加相同的字母&nbsp;<code>c</code>&nbsp;使其长度达到 3 或以上。</p>
//
//<p>例如，以&nbsp;"hello" 为例，我们可以对字母组&nbsp;"o" 扩张得到 "hellooo"，但是无法以同样的方法得到 "helloo" 因为字母组 "oo" 长度小于&nbsp;3。此外，我们可以进行另一种扩张 "ll" -&gt; "lllll" 以获得&nbsp;"helllllooo"。如果&nbsp;<code>S = "helllllooo"</code>，那么查询词&nbsp;"hello" 是可扩张的，因为可以对它执行这两种扩张操作使得&nbsp;<code>query = "hello" -&gt; "hellooo" -&gt;&nbsp;"helllllooo" = S</code>。</p>
//
//<p>输入一组查询单词，输出其中可扩张的单词数量。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre>
//<strong>输入：</strong> 
// S = "heeellooo"
// words = ["hello", "hi", "helo"]
//<strong>输出：</strong>1
//<strong>解释</strong>：
// 我们能通过扩张 "hello" 的 "e" 和 "o" 来得到 "heeellooo"。
// 我们不能通过扩张 "helo" 来得到 "heeellooo" 因为 "ll" 的长度小于 3 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= len(S) &lt;= 100</code>。</li> 
// <li><code>0 &lt;= len(words) &lt;= 100</code>。</li> 
// <li><code>0 &lt;= len(words[i]) &lt;= 100</code>。</li> 
// <li><code>S</code>&nbsp;和所有在&nbsp;<code>words</code>&nbsp;中的单词都只由小写字母组成。</li> 
//</ul>
//
//<div><li>👍 67</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.ArrayList;

// 809.情感丰富的文字
// 开题时间：2022-11-25 08:49:55
public class ExpressiveWords {
  public static void main(String[] args) {
    Solution solution = new ExpressiveWords().new Solution();
    //        System.out.println(solution.expressiveWords("abcd", new String[]{"abc"}));
    System.out.println(solution.expressiveWordsX("abcccc", new String[]{"abcd"}));
    //        System.out.println(solution.expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
    //        System.out.println(solution.expressiveWords("zzzzzyyyyy", new String[]{"zzyy", "zy", "zyy"}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int expressiveWordsX(String s, String[] words) {
      ArrayList<int[]> list = new ArrayList<>();
      int l = 0, r = 1;
      int n = s.length();
      for (; r < n; r++) {
        char c = s.charAt(l);
        if (c != s.charAt(r)) {
          list.add(new int[]{c, r - l});
          l = r;
        }
      }
      list.add(new int[]{s.charAt(n - 1), n - l});
      
      int cnt = words.length;
      outer:
      for (String word : words) {
        if (word.length() > n) {
          cnt--;
          continue;
        }
        l = 0;
        r = 1;
        int i = 0;
        for (; r <= word.length(); i++, r++) {
          int[] char2cnt = list.get(i);
          char c = word.charAt(l);
          if (c != char2cnt[0]) {
            cnt--;
            continue outer;
          }
          
          while (r < word.length() && c == word.charAt(r))
            r++;
          
          if (char2cnt[1] < r - l || (char2cnt[1] > r - l && char2cnt[1] <= 2)) {
            cnt--;
            continue outer;
          }
          
          l = r;
        }
        
        if (i < list.size())
          cnt--;
      }
      
      return cnt;
    }
    
    public int expressiveWords(String s, String[] words) {
      int cnt = 0;
      char[] chars = s.toCharArray();
      int n = chars.length;
      out:
      for (String word : words) {
        int m = word.length();
        if (m > n)
          continue;
        
        int i = 0, j = 0;
        for (int a = 1, b = 1; i < n && j < m; a++, b++) {
          char c = s.charAt(i);
          if (c != word.charAt(j)) continue out;
          
          while (a < n && c == chars[a]) a++;
          while (b < m && c == word.charAt(b)) b++;
          
          int lenS = a - i, lenW = b - j;
          if (lenS != lenW && (lenS < lenW || lenS < 3)) continue out;
          i = a;
          j = b;
        }
        if (i == n && j == m) cnt++;
      }
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}