//给你一个正整数 <code>n</code> ，返回 <code>2</code><em> </em>和<em> </em><code>n</code> 的最小公倍数（正整数）。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>n = 5
//<strong>输出：</strong>10
//<strong>解释：</strong>5 和 2 的最小公倍数是 10 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>n = 6
//<strong>输出：</strong>6
//<strong>解释：</strong>6 和 2 的最小公倍数是 6 。注意数字会是它自身的倍数。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 150</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数学</li><li>数论</li></div></div><br><div><li>👍 12</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

//2413.最小偶倍数
//开题时间：2022-11-01 14:52:44
public class SmallestEvenMultiple {
    public static void main(String[] args) {
        Solution solution = new SmallestEvenMultiple().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int smallestEvenMultiple(int n) {
            return (n & 1) == 1 ? n * 2 : n;
        }

        public int smallestEvenMultiple2(int n) {
            return n << (n & 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}