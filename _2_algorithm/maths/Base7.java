//<p>给定一个整数 <code>num</code>，将其转化为 <strong>7 进制</strong>，并以字符串形式输出。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> num = 100
//<strong>输出:</strong> "202"
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> num = -7
//<strong>输出:</strong> "-10"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-10<sup>7</sup>&nbsp;&lt;= num &lt;= 10<sup>7</sup></code></li> 
//</ul>
//
//<div><li>👍 195</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

//504.七进制数
//开题时间：2023-01-03 17:57:14
public class Base7 {
    public static void main(String[] args) {
        Solution solution = new Base7().new Solution();
        System.out.println(solution.convertToBase7(-7));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convertToBase7API(int num) {
            return Integer.toString(num, 7);
        }

        /*
         * 负数时，转正，最后加个负号
         * 可以采用「do while」循环结构，这样输入为 0 时，也能返回正确结果
         */
        public String convertToBase7(int num) {
            boolean positive = true;
            if (num < 0) {
                num = -num;
                positive = false;
            }

            StringBuilder sb = new StringBuilder();
            do {
                sb.append(num % 7);
                num /= 7;
            } while (num != 0);

            if (!positive)
                sb.append('-');
            return sb.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}