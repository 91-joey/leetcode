//<p>给你一个字符串 <code>s</code> ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "Hello"
//<strong>输出：</strong>"hello"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "here"
//<strong>输出：</strong>"here"
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "LOVELY"
//<strong>输出：</strong>"lovely"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 100</code></li> 
// <li><code>s</code> 由 ASCII 字符集中的可打印字符组成</li> 
//</ul>
//
//<div><li>👍 220</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

//709.转换成小写字母
//开题时间：2022-12-09 10:31:59
public class ToLowerCase {
    public static void main(String[] args) {
        Solution solution = new ToLowerCase().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String toLowerCase9(String s) {
            return s.toLowerCase();
        }

        public String toLowerCase8(String s) {
            int n = s.length();
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (Character.isUpperCase(c))
                    c = Character.toLowerCase(c);
                sb.append(c);
            }

            return sb.toString();
        }

        //☆☆☆☆☆ 位运算
        public String toLowerCase(String s) {
            int n = s.length();
            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (65 <= c && c <= 90)
                    c |= 32;
                sb.append(c);
            }

            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}