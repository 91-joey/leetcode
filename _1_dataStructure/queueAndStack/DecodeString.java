//<p>给定一个经过编码的字符串，返回它解码后的字符串。</p>
//
//<p>编码规则为: <code>k[encoded_string]</code>，表示其中方括号内部的 <code>encoded_string</code> 正好重复 <code>k</code> 次。注意 <code>k</code> 保证为正整数。</p>
//
//<p>你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。</p>
//
//<p>此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 <code>k</code> ，例如不会出现像&nbsp;<code>3a</code>&nbsp;或&nbsp;<code>2[4]</code>&nbsp;的输入。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "3[a]2[bc]"
//<strong>输出：</strong>"aaabcbc"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "3[a2[c]]"
//<strong>输出：</strong>"accaccacc"
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "2[abc]3[cd]ef"
//<strong>输出：</strong>"abcabccdcdcdef"
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "abc3[cd]xyz"
//<strong>输出：</strong>"abccdcdcdxyz"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 30</code></li> 
// <li>
//  <meta charset="UTF-8" /><code>s</code>&nbsp;由小写英文字母、数字和方括号
//  <meta charset="UTF-8" />&nbsp;<code>'[]'</code> 组成</li> 
// <li><code>s</code>&nbsp;保证是一个&nbsp;<strong>有效</strong>&nbsp;的输入。</li> 
// <li><code>s</code>&nbsp;中所有整数的取值范围为
//  <meta charset="UTF-8" />&nbsp;<code>[1, 300]</code>&nbsp;</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>栈</li><li>递归</li><li>字符串</li></div></div><br><div><li>👍 1269</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.queueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;

// 394.字符串解码
// 开题时间：2022-08-23 11:34:45
public class DecodeString {
  public static void main(String[] args) {
    Solution solution = new DecodeString().new Solution();
    System.out.println(solution.decodeString3("a2[b]3[c4[d]]xyz"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    //"a2[b]3[c4[d]]xyz"    abbcddddcddddcddddxyz
    // 1.递归
    public String decodeString(String s) {
      return decode(s, 0)[0];
    }
    
    private String[] decode(String s, int idx) {
      StringBuilder cur = new StringBuilder();
      
      int i = idx;
      for (; i < s.length(); i++) {
        char c = s.charAt(i);
        if ('a' <= c && c <= 'z') {
          cur.append(c);
        } else if ('0' <= c && c <= '9') {
          int start = s.indexOf("[", i + 1);
          int dupCnt = Integer.parseInt(s.substring(i, start));
          String[] dup = decode(s, start + 1);
          cur.append(String.valueOf(dup[0]).repeat(dupCnt));
          i = Integer.parseInt(dup[1]);
          //"]"
        } else {
          break;
        }
      }
      
      return new String[]{cur.toString(), String.valueOf(i)};
    }
    
    // 2.栈
    //"a2[b]3[c4[d]]xyz"
    public String decodeString2(String s) {
      StringBuilder ans = new StringBuilder();
      Deque<Integer> dups = new ArrayDeque<>();
      
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if ('0' <= c && c <= '9') {
          int start = s.indexOf("[", i + 1);
          int dup = Integer.parseInt(s.substring(i, start));
          dups.push(dup);
          i = start - 1;
        } else if (c == ']') {
          int start = ans.lastIndexOf("[");
          String dupString = ans.substring(start + 1);
          int dupCnt = dups.pop();
          ans.replace(start, ans.length(), dupString.repeat(dupCnt));
        } else {
          ans.append(c);
        }
      }
      
      return ans.toString();
    }
    
    // 3.辅助栈
    //"a2[b]3[c4[d]]xyz"
    public String decodeString3(String s) {
      StringBuilder ans = new StringBuilder();
      Deque<Integer> dups = new ArrayDeque<>();
      Deque<String> strings = new ArrayDeque<>();
      
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if ('0' <= c && c <= '9') {
          int start = s.indexOf("[", i + 1);
          int dup = Integer.parseInt(s.substring(i, start));
          dups.push(dup);
          //"["
          strings.push(ans.toString());
          ans = new StringBuilder();
          i = start;
        } else if (c == ']') {
          int dupCnt = dups.pop();
          ans = new StringBuilder(strings.pop() + ans.toString().repeat(dupCnt));
        } else {
          ans.append(c);
        }
      }
      
      return ans.toString();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}