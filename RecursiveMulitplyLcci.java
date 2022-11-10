//<p>递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。</p>
//
//<p> <strong>示例1:</strong></p>
//
//<pre>
//<strong> 输入</strong>：A = 1, B = 10
//<strong> 输出</strong>：10
//</pre>
//
//<p> <strong>示例2:</strong></p>
//
//<pre>
//<strong> 输入</strong>：A = 3, B = 4
//<strong> 输出</strong>：12
//</pre>
//
//<p> <strong>提示:</strong></p>
//
//<ol> 
// <li>保证乘法范围不会溢出</li> 
//</ol>
//
//<div><li>👍 80</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//面试题 08.05.递归乘法
//开题时间：2022-11-10 15:06:20
public class RecursiveMulitplyLcci {
    public static void main(String[] args) {
        Solution solution = new RecursiveMulitplyLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //递归 （注意，较小数作为右乘数）
        public int multiply9(int A, int B) {
            if (A < B) return multiply9(B, A);
            return multiplyByRecursion(A, B);
        }

        private int multiplyByRecursion(int A, int B) {
            if (B == 1)
                return A;
            int half = multiply9(A, B / 2);
            return (B & 1) == 0 ? half << 1 : (half << 1) + A;
        }

        //☆☆☆☆☆ 迭代（快速幂）
        public int multiply(int A, int B) {
            if (A < B) return multiply(B, A);

            int ans = 0;
            while (B != 0) {
                if ((B & 1) == 1)
                    ans += A;
                A <<= 1;
                B >>= 1;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}