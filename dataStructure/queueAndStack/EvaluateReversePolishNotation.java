//<p>根据<a href="https://baike.baidu.com/item/%E9%80%86%E6%B3%A2%E5%85%B0%E5%BC%8F/128437" target="_blank"> 逆波兰表示法</a>，求表达式的值。</p>
//
//<p>有效的算符包括&nbsp;<code>+</code>、<code>-</code>、<code>*</code>、<code>/</code>&nbsp;。每个运算对象可以是整数，也可以是另一个逆波兰表达式。</p>
//
//<p><b>注意&nbsp;</b>两个整数之间的除法只保留整数部分。</p>
//
//<p>可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>tokens = ["2","1","+","3","*"]
//<strong>输出：</strong>9
//<strong>解释：</strong>该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>tokens = ["4","13","5","/","+"]
//<strong>输出：</strong>6
//<strong>解释：</strong>该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
//</pre>
//
//<p><strong>示例&nbsp;3：</strong></p>
//
//<pre>
//<strong>输入：</strong>tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
//<strong>输出：</strong>22
//<strong>解释：</strong>该算式转化为常见的中缀算术表达式为：
//  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//= ((10 * (6 / (12 * -11))) + 17) + 5
//= ((10 * (6 / -132)) + 17) + 5
//= ((10 * 0) + 17) + 5
//= (0 + 17) + 5
//= 17 + 5
//= 22</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= tokens.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>tokens[i]</code>&nbsp;是一个算符（<code>"+"</code>、<code>"-"</code>、<code>"*"</code> 或 <code>"/"</code>），或是在范围 <code>[-200, 200]</code> 内的一个整数</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>逆波兰表达式：</strong></p>
//
//<p>逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。</p>
//
//<ul> 
// <li>平常使用的算式则是一种中缀表达式，如 <code>( 1 + 2 ) * ( 3 + 4 )</code> 。</li> 
// <li>该算式的逆波兰表达式写法为 <code>( ( 1 2 + ) ( 3 4 + ) * )</code> 。</li> 
//</ul>
//
//<p>逆波兰表达式主要有以下两个优点：</p>
//
//<ul> 
// <li>去掉括号后表达式无歧义，上式即便写成 <code>1 2 + 3 4 + * </code>也可以依据次序计算出正确结果。</li> 
// <li>适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>数学</li></div></div><br><div><li>👍 585</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.queueAndStack;

import java.util.Stack;

//150.逆波兰表达式求值
//开题时间：2022-08-19 10:50:21
public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        Solution solution = new EvaluateReversePolishNotation().new Solution();
        System.out.println(solution.evalRPN3(new String[]{"4", "13", "5", "/", "+"}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.栈 n   n
        public int evalRPN(String[] tokens) {
            Stack<Integer> stack = new Stack<>();
            for (String token : tokens) {
                switch (token) {
                    case "+" -> stack.push(stack.pop() + stack.pop());
                    case "-" -> {
                        Integer subtraction = stack.pop();
                        Integer minuend = stack.pop();
                        stack.push(minuend - subtraction);
                    }
                    case "*" -> stack.push(stack.pop() * stack.pop());
                    case "/" -> {
                        Integer divisor = stack.pop();
                        Integer dividend = stack.pop();
                        stack.push(dividend / divisor);
                    }
                    default -> stack.push(Integer.valueOf(token));
                }
            }
            return stack.pop();
        }

        //2.官解二：数组模拟栈 n   n
        public int evalRPN2(String[] tokens) {
            int[] stack = new int[(tokens.length + 1) / 2];
            int idx = -1;

            for (String token : tokens) {
                switch (token) {
                    case "+" -> stack[--idx] = stack[idx] + stack[idx + 1];
                    case "-" -> stack[--idx] = stack[idx] - stack[idx + 1];
                    case "*" -> stack[--idx] = stack[idx] * stack[idx + 1];
                    case "/" -> stack[--idx] = stack[idx] / stack[idx + 1];
                    default -> stack[++idx] = Integer.parseInt(token);
                }
            }

            return stack[idx];
        }

        //3.高分解（递归）
        private int idx;
        public int evalRPN3(String[] tokens) {
            idx=tokens.length - 1;
            return eval(tokens);
        }

        private int eval(String[] tokens) {
            String token = tokens[idx];
            if (!"+-*/".contains(token)) {
                return Integer.parseInt(token);
            }
            idx--;
            int v2 = eval(tokens);
            idx--;
            int v1 = eval(tokens);
            return switch (token) {
                case "+" -> v1 + v2;
                case "-" -> v1 - v2;
                case "*" -> v1 * v2;
                default -> v1 / v2;
            };
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}