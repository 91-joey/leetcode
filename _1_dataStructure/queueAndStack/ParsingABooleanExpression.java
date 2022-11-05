//<p>给你一个以字符串形式表述的&nbsp;<a href="https://baike.baidu.com/item/%E5%B8%83%E5%B0%94%E8%A1%A8%E8%BE%BE%E5%BC%8F/1574380?fr=aladdin" target="_blank">布尔表达式</a>（boolean） <code>expression</code>，返回该式的运算结果。</p>
//
//<p>有效的表达式需遵循以下约定：</p>
//
//<ul> 
// <li><code>"t"</code>，运算结果为 <code>True</code></li> 
// <li><code>"f"</code>，运算结果为 <code>False</code></li> 
// <li><code>"!(expr)"</code>，运算过程为对内部表达式 <code>expr</code> 进行逻辑 <strong>非的运算</strong>（NOT）</li> 
// <li><code>"&amp;(expr1,expr2,...)"</code>，运算过程为对 2 个或以上内部表达式 <code>expr1, expr2, ...</code> 进行逻辑 <strong>与的运算</strong>（AND）</li> 
// <li><code>"|(expr1,expr2,...)"</code>，运算过程为对 2 个或以上内部表达式 <code>expr1, expr2, ...</code> 进行逻辑 <strong>或的运算</strong>（OR）</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>expression = "!(f)"
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>expression = "|(f,t)"
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>expression = "&amp;(t,f)"
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>expression = "|(&amp;(t,f,t),!(t))"
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= expression.length &lt;= 20000</code></li> 
// <li><code>expression[i]</code> 由 <code>{'(', ')', '&amp;', '|', '!', 't', 'f', ','}</code> 中的字符组成。</li> 
// <li><code>expression</code> 是以上述形式给出的有效表达式，表示一个布尔值。</li> 
//</ul>
//
//<div><li>👍 97</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.queueAndStack;

import java.util.Deque;
import java.util.LinkedList;

//1106.解析布尔表达式
//开题时间：2022-11-05 10:08:26
public class ParsingABooleanExpression {
    public static void main(String[] args) {
        Solution solution = new ParsingABooleanExpression().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final char TRUE = 't';
        public static final char FALSE = 'f';
        public static final char NOT = '!';
        public static final char AND = '&';
        public static final char OR = '|';
        public static final char BRACE_LEFT = '(';
        public static final char BRACE_RIGHT = ')';
        public static final char SEPARATOR = ',';

        public boolean parseBoolExpr9(String expression) {
            Deque<Character> stack = new LinkedList<>();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if (c != BRACE_LEFT && c != SEPARATOR) {
                    if (c != BRACE_RIGHT) {
                        stack.push(c);
                    } else {
                        int cntAll = 0;
                        int cntTrue = 0;
                        char pop;
                        while ((pop = stack.pop()) == TRUE || pop == FALSE) {
                            cntAll++;
                            if (pop == TRUE)
                                cntTrue++;
                        }
                        stack.push(switch (pop) {
                            case NOT -> cntTrue == 0 ? TRUE : FALSE;
                            case AND -> cntTrue == cntAll ? TRUE : FALSE;
                            case OR -> cntTrue > 0 ? TRUE : FALSE;
                            default -> throw new IllegalStateException("Unexpected value: " + pop);
                        });
                    }
                }
            }

            return stack.pop() == TRUE;
        }

        //☆☆☆☆☆ stack
        public boolean parseBoolExpr(String expression) {
            Deque<Character> stack = new LinkedList<>();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                if (c != BRACE_LEFT && c != SEPARATOR) {
                    if (c != BRACE_RIGHT) {
                        stack.push(c);
                    } else {
                        int cntTrue = 0;
                        int cntFalse = 0;
                        char pop;
                        while ((pop = stack.pop()) == TRUE || pop == FALSE) {
                            if (pop == TRUE)
                                cntTrue++;
                            else
                                cntFalse++;
                        }
                        stack.push(switch (pop) {
                            case NOT -> cntTrue == 0 ? TRUE : FALSE;
                            case AND -> cntFalse == 0 ? TRUE : FALSE;
                            case OR -> cntTrue > 0 ? TRUE : FALSE;
                            default -> throw new IllegalStateException("Unexpected value: " + pop);
                        });
                    }
                }
            }

            return stack.pop() == TRUE;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}