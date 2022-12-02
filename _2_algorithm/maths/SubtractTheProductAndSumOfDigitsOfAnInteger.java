//<p>给你一个整数&nbsp;<code>n</code>，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>n = 234
//<strong>输出：</strong>15 
//<strong>解释：</strong>
//各位数之积 = 2 * 3 * 4 = 24 
//各位数之和 = 2 + 3 + 4 = 9 
//结果 = 24 - 9 = 15
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>n = 4421
//<strong>输出：</strong>21
//<strong>解释： 
//</strong>各位数之积 = 4 * 4 * 2 * 1 = 32 
//各位数之和 = 4 + 4 + 2 + 1 = 11 
//结果 = 32 - 11 = 21
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10^5</code></li> 
//</ul>
//
//<div><li>👍 97</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

//1281.整数的各位积和之差
//开题时间：2022-12-02 10:41:42
public class SubtractTheProductAndSumOfDigitsOfAnInteger {
    public static void main(String[] args) {
        Solution solution = new SubtractTheProductAndSumOfDigitsOfAnInteger().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int subtractProductAndSum(int n) {
            int product = 1, sum = 0;

            while (n != 0) {
                int digit = n % 10;
                product *= digit;
                sum += digit;
                n /= 10;
            }

            return product - sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}