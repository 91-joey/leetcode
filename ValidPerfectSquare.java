//<p>给定一个 <strong>正整数</strong> <code>num</code> ，编写一个函数，如果 <code>num</code> 是一个完全平方数，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>
//
//<p><strong>进阶：不要</strong> 使用任何内置的库函数，如&nbsp; <code>sqrt</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>num = 16
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>num = 14
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= num &lt;= 2^31 - 1</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数学</li><li>二分查找</li></div></div><br><div><li>👍 451</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//367.有效的完全平方数
//开题时间：2022-11-02 14:34:48
public class ValidPerfectSquare {
    public static void main(String[] args) {
        Solution solution = new ValidPerfectSquare().new Solution();
        System.out.println(solution.isPerfectSquare2(16));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //API
        public boolean isPerfectSquare(int num) {
//            int sqrt = (int) Math.sqrt(num);
//            return num == sqrt * sqrt;
            double sqrt = Math.sqrt(num);
            return sqrt == (int) sqrt;
        }

        //二分
        public boolean isPerfectSquare2(int num) {
            int l = 1, r = num;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                long square = (long) mid * mid;
                if (square == num)
                    return true;
                else if (square < num)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}