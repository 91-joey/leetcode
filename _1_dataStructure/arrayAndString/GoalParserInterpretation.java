//<p>请你设计一个可以解释字符串 <code>command</code> 的 <strong>Goal 解析器</strong> 。<code>command</code> 由 <code>"G"</code>、<code>"()"</code> 和/或 <code>"(al)"</code> 按某种顺序组成。Goal 解析器会将 <code>"G"</code> 解释为字符串 <code>"G"</code>、<code>"()"</code> 解释为字符串 <code>"o"</code> ，<code>"(al)"</code> 解释为字符串 <code>"al"</code> 。然后，按原顺序将经解释得到的字符串连接成一个字符串。</p>
//
//<p>给你字符串 <code>command</code> ，返回<em> </em><strong>Goal<em><strong> </strong></em>解析器 </strong>对<em> </em><code>command</code> 的解释结果。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>command = "G()(al)"
//<strong>输出：</strong>"Goal"
//<strong>解释：</strong>Goal 解析器解释命令的步骤如下所示：
// G -&gt; G
//() -&gt; o
//(al) -&gt; al
// 最后连接得到的结果是 "Goal"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>command = "G()()()()(al)"
//<strong>输出：</strong>"Gooooal"
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>command = "(al)G(al)()()G"
//<strong>输出：</strong>"alGalooG"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= command.length &lt;= 100</code></li> 
// <li><code>command</code> 由 <code>"G"</code>、<code>"()"</code> 和/或 <code>"(al)"</code> 按某种顺序组成</li> 
//</ul>
//
//<div><li>👍 65</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

// 1678.设计 Goal 解析器
// 开题时间：2022-11-06 17:51:37
public class GoalParserInterpretation {
  public static void main(String[] args) {
    Solution solution = new GoalParserInterpretation().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public String interpret9(String command) {
      return command.replace("()", "o").replace("(al)", "al");
    }
    
    public String interpret(String command) {
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0, len = command.length(); i < len; ) {
        char c = command.charAt(i);
        if (c != '(') {
          sb.append(c);
          i++;
        } else if (command.charAt(i + 1) == ')') {
          sb.append('o');
          i += 2;
        } else {
          sb.append("al");
          i += 4;
        }
      }
      
      return sb.toString();
    }
    
    public String interpret8(String command) {
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < command.length(); ) {
        char c = command.charAt(i);
        switch (c) {
          case '(' -> {
            switch (command.charAt(i + 1)) {
              case ')' -> {
                sb.append('o');
                i += 2;
              }
              default -> {
                sb.append("al");
                i += 4;
              }
            }
          }
          default -> {
            sb.append(c);
            i++;
          }
        }
      }
      
      return sb.toString();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}