//<p>给定两个字符串 <code>order</code> 和 <code>s</code> 。<code>order</code> 的所有单词都是 <strong>唯一</strong> 的，并且以前按照一些自定义的顺序排序。</p>
//
//<p>对 <code>s</code> 的字符进行置换，使其与排序的&nbsp;<code>order</code>&nbsp;相匹配。更具体地说，如果在&nbsp;<code>order</code>&nbsp;中的字符 <code>x</code> 出现字符 <code>y</code> 之前，那么在排列后的字符串中， <code>x</code>&nbsp;也应该出现在 <code>y</code> 之前。</p>
//
//<p>返回 <em>满足这个性质的 <code>s</code> 的任意排列&nbsp;</em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> order = "cba", s = "abcd"
//<strong>输出:</strong> "cbad"
//<strong>解释:</strong> 
//“a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
// 因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> order = "cbafg", s = "abcd"
//<strong>输出:</strong> "cbad"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= order.length &lt;= 26</code></li> 
// <li><code>1 &lt;= s.length &lt;= 200</code></li> 
// <li><code>order</code>&nbsp;和&nbsp;<code>s</code>&nbsp;由小写英文字母组成</li> 
// <li><code>order</code>&nbsp;中的所有字符都 <strong>不同</strong></li> 
//</ul>
//
//<div><li>👍 128</li><li>👎 0</li></div>
package _2_algorithm.sort.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

// 791.自定义字符串排序
// 开题时间：2022-11-13 08:59:43
public class CustomSortString {
  public static void main(String[] args) {
    Solution solution = new CustomSortString().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 自定义排序
    public String customSortString9(String order, String s) {
      ArrayList<Character> list = new ArrayList<>();
      for (int i = 0; i < s.length(); i++)
        list.add(s.charAt(i));
      
      list.sort(Comparator.comparingInt(order::indexOf));
      
      StringBuilder sb = new StringBuilder(s.length());
      list.forEach(sb::append);
      return sb.toString();
    }
    
    // 自定义排序（优化） nlogn+m m
    public String customSortString8(String order, String s) {
      int[] weights = new int[123];
      for (int i = 1; i < order.length(); i++)
        weights[order.charAt(i)] = i;
      
      int len = s.length();
      Character[] chars = new Character[len];
      for (int i = 0; i < len; i++)
        chars[i] = s.charAt(i);
      
      Arrays.sort(chars, Comparator.comparingInt(a -> weights[a]));
      
      StringBuilder sb = new StringBuilder(len);
      for (Character c : chars)
        sb.append(c);
      return sb.toString();
    }
    
    // 计数排序  n+m m
    public String customSortString7(String order, String s) {
      int[] cnts = new int[26];
      int len = s.length();
      for (int i = 0; i < len; i++)
        cnts[s.charAt(i) - 'a']++;
      
      StringBuilder sb = new StringBuilder(len);
      for (int i = 0; i < order.length(); i++) {
        char c = order.charAt(i);
        for (int j = 0; j < cnts[c - 'a']; j++)
          sb.append(c);
        cnts[c - 'a'] = 0;
      }
      
      for (int i = 0; i < 26; i++)
        for (int j = 0; j < cnts[i]; j++)
          sb.append((char) (i + 'a'));
      
      return sb.toString();
    }
    
    public String customSortString6(String order, String s) {
      int[] weights = new int[123];
      for (int i = 0; i < order.length(); i++)
        weights[order.charAt(i)] = i;
      
      StringBuilder sb = new StringBuilder();
      AtomicInteger i = new AtomicInteger();
      Stream.generate(() -> s.charAt(i.getAndIncrement()))
          .limit(s.length())
          .sorted(Comparator.comparingInt(c -> weights[c]))
          .forEach(sb::append);
      
      return sb.toString();
    }
    
    public String customSortString(String order, String s) {
      int[] weights = new int[123];
      for (int i = 0; i < order.length(); i++)
        weights[order.charAt(i)] = i;
      
      StringBuilder sb = new StringBuilder();
      s.chars()
          .mapToObj(i -> (char) i)
          .sorted(Comparator.comparingInt(c -> weights[c]))
          .forEach(sb::append);
      
      return sb.toString();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}