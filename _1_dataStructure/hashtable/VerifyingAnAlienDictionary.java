//<p>某种外星语也使用英文小写字母，但可能顺序 <code>order</code> 不同。字母表的顺序（<code>order</code>）是一些小写字母的排列。</p>
//
//<p>给定一组用外星语书写的单词 <code>words</code>，以及其字母表的顺序 <code>order</code>，只有当给定的单词在这种外星语中按字典序排列时，返回 <code>true</code>；否则，返回 <code>false</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
//<strong>输出：</strong>true
//<strong>解释：</strong>在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
//<strong>输出：</strong>false
//<strong>解释：</strong>在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] &gt; words[1]，因此单词序列不是按字典序排列的。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
//<strong>输出：</strong>false
//<strong>解释：</strong>当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" &gt; "app"，因为 'l' &gt; '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（<a href="https://baike.baidu.com/item/%E5%AD%97%E5%85%B8%E5%BA%8F" target="_blank">更多信息</a>）。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= words.length &lt;= 100</code></li> 
// <li><code>1 &lt;= words[i].length &lt;= 20</code></li> 
// <li><code>order.length == 26</code></li> 
// <li>在&nbsp;<code>words[i]</code>&nbsp;和&nbsp;<code>order</code>&nbsp;中的所有字符都是英文小写字母。</li> 
//</ul>
//
//<div><li>👍 227</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 953.验证外星语词典
// 开题时间：2022-12-09 10:47:16
public class VerifyingAnAlienDictionary {
  public static void main(String[] args) {
    Solution solution = new VerifyingAnAlienDictionary().new Solution();
    String[] strings = new String[]{"world", "word", "row"};
    Arrays.sort(strings);
    System.out.println(Arrays.toString(strings));
    System.out.println(solution.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    public boolean isAlienSorted9(String[] words, String order) {
      for (int j = 0; j < words.length; j++) {
        String s = "";
        for (int i = 0; i < words[j].length(); i++)
          s += ALPHABET.charAt(order.indexOf(words[j].charAt(i)));
        words[j] = s;
      }
      
      String[] copy = Arrays.copyOf(words, words.length);
      Arrays.sort(copy);
      
      return Arrays.equals(words, copy);
    }
    
    // 字符串数组转人类语、排序后比较
    public boolean isAlienSorted8(String[] words, String order) {
      Map<Character, Character> map = new HashMap<>();
      for (int i = 0; i < order.length(); i++)
        map.put(order.charAt(i), (char) ('a' + i));
      
      for (int j = 0; j < words.length; j++) {
        String s = "";
        for (int i = 0; i < words[j].length(); i++)
          s += map.get(words[j].charAt(i));
        words[j] = s;
      }
      
      String[] copy = Arrays.copyOf(words, words.length);
      Arrays.sort(copy);
      
      return Arrays.equals(words, copy);
    }
    
    //☆☆☆☆☆ 直接比较
    public boolean isAlienSorted(String[] words, String order) {
      Map<Character, Integer> char2weight = new HashMap<>();
      for (int i = 0; i < order.length(); i++)
        char2weight.put(order.charAt(i), i);
      
      out:
      for (int i = 1; i < words.length; i++) {
        String pre = words[i - 1];
        String cur = words[i];
        int j = 0;
        for (; j < pre.length() && j < cur.length(); j++) {
          int weightPre = char2weight.get(pre.charAt(j));
          int weightCur = char2weight.get(cur.charAt(j));
          if (weightPre > weightCur)
            return false;
          else if (weightPre < weightCur)
            continue out;
        }
        if (j < pre.length())
          return false;
      }
      
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}