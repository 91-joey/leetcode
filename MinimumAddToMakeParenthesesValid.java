//<p>只有满足下面几点之一，括号字符串才是有效的：</p>
//
//<ul> 
// <li>它是一个空字符串，或者</li> 
// <li>它可以被写成&nbsp;<code>AB</code>&nbsp;（<code>A</code>&nbsp;与&nbsp;<code>B</code>&nbsp;连接）, 其中&nbsp;<code>A</code> 和&nbsp;<code>B</code>&nbsp;都是有效字符串，或者</li> 
// <li>它可以被写作&nbsp;<code>(A)</code>，其中&nbsp;<code>A</code>&nbsp;是有效字符串。</li> 
//</ul>
//
//<p>给定一个括号字符串 <code>s</code> ，在每一次操作中，你都可以在字符串的任何位置插入一个括号</p>
//
//<ul> 
// <li>例如，如果 <code>s = "()))"</code> ，你可以插入一个开始括号为 <code>"(()))"</code> 或结束括号为 <code>"())))"</code> 。</li> 
//</ul>
//
//<p>返回 <em>为使结果字符串 <code>s</code> 有效而必须添加的最少括号数</em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "())"
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "((("
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 1000</code></li> 
// <li><code>s</code> 只包含&nbsp;<code>'('</code> 和&nbsp;<code>')'</code>&nbsp;字符。</li> 
//</ul>
//
//<div><li>👍 235</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.ArrayDeque;
import java.util.Deque;

//921.使括号有效的最少添加
//开题时间：2023-01-25 14:29:03
public class MinimumAddToMakeParenthesesValid {
    public static void main(String[] args) {
        Solution solution = new MinimumAddToMakeParenthesesValid().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //错误写法：少考虑了 「)))(((((」 的情况
        public int minAddToMakeValidX(String s) {
            int cnt = (int) s.chars().filter(ch -> ch == '(').count();
            return Math.abs(cnt * 2 - s.length());
        }

        //栈 最终遗留在栈中的元素是 「)))(((((」 的形式，每个括号都需要添加对应的括号
        public int minAddToMakeValid9(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    continue;
                }
                stack.push(c);
            }
            return stack.size();
        }

        //☆☆☆☆☆ 贪心：将「有效括号问题」转化为「分值有效性」的数学判定
        public int minAddToMakeValid(String s) {
            int addOpening = 0, addClosing = 0;
            for (int i = 0; i < s.length(); i++) {
                addClosing += s.charAt(i) == '(' ? 1 : -1;
                if (addClosing < 0) {
                    addOpening++;
                    addClosing = 0;
                }
            }
            return addOpening + addClosing;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}