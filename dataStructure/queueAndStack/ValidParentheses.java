//<p>给定一个只包括 <code>'('</code>，<code>')'</code>，<code>'{'</code>，<code>'}'</code>，<code>'['</code>，<code>']'</code>&nbsp;的字符串 <code>s</code> ，判断字符串是否有效。</p>
//
//<p>有效字符串需满足：</p>
//
//<ol> 
// <li>左括号必须用相同类型的右括号闭合。</li> 
// <li>左括号必须以正确的顺序闭合。</li> 
//</ol>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "()"
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "()[]{}"
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例&nbsp;3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "(]"
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例&nbsp;4：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "([)]"
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例&nbsp;5：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "{[]}"
//<strong>输出：</strong>true</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>s</code> 仅由括号 <code>'()[]{}'</code> 组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>栈</li><li>字符串</li></div></div><br><div><li>👍 3456</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.queueAndStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//20.有效的括号
//开题时间：2022-08-18 08:53:27
public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        System.out.println(solution.isValid("(([]){})"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final char OPEN_BRACE = '{';
        public static final char CLOSE_BRACE = '}';
        public static final char OPEN_PAREN = '(';
        public static final char CLOSE_PAREN = ')';
        public static final char OPEN_BRACKET = '[';
        public static final char CLOSE_BRACKET = ']';

        //栈 n   n
        public boolean isValid(String s) {
            int length = s.length();
            if (length % 2 != 0) {
                return false;
            }

            Map<Character, Character> braces = new HashMap<>(4);
            braces.put(OPEN_BRACE, CLOSE_BRACE);
            braces.put(OPEN_PAREN, CLOSE_PAREN);
            braces.put(OPEN_BRACKET, CLOSE_BRACKET);
            Stack<Character> opens = new Stack<>();

            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                //opens
                if (braces.containsKey(c)) {
                    opens.push(c);
                //closes
                } else if (opens.empty() || braces.get(opens.pop()) != c) {
                    return false;
                }
            }

            return opens.empty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}