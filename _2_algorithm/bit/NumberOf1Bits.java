//<p>编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为<a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E9%87%8D%E9%87%8F" target="_blank">汉明重量</a>）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。</li> 
// <li>在 Java 中，编译器使用<a href="https://baike.baidu.com/item/二进制补码/5295284" target="_blank">二进制补码</a>记法来表示有符号整数。因此，在上面的&nbsp;<strong>示例 3</strong>&nbsp;中，输入表示有符号整数 <code>-3</code>。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>00000000000000000000000000001011
//<strong>输出：</strong>3
//<strong>解释：</strong>输入的二进制串 <span><code><strong>00000000000000000000000000001011</strong>&nbsp;中，共有三位为 '1'。</code></span>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>00000000000000000000000010000000
//<strong>输出：</strong>1
//<strong>解释：</strong>输入的二进制串 <strong>00000000000000000000000010000000</strong>&nbsp;中，共有一位为 '1'。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>11111111111111111111111111111101
//<strong>输出：</strong>31
//<strong>解释：</strong>输入的二进制串 <strong>11111111111111111111111111111101</strong> 中，共有 31 位为 '1'。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>输入必须是长度为 <code>32</code> 的 <strong>二进制串</strong> 。</li> 
//</ul>
//
//<ul> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶</strong>：</p>
//
//<ul> 
// <li>如果多次调用这个函数，你将如何优化你的算法？</li> 
//</ul>
//
//<div><li>👍 535</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.bit;

//191.位1的个数
//开题时间：2022-12-02 09:51:18
public class NumberOf1Bits {
    public static void main(String[] args) {
        Solution solution = new NumberOf1Bits().new Solution();
        System.out.println(1 << 30);
        System.out.println(-1 & (1 << 30));
        System.out.println(solution.hammingWeight(-1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        //循环检查二进制位
        public int hammingWeight9(int n) {
            int cnt = 0;

            for (int i = 0; i < 32; i++)
                if ((n & (1 << i)) != 0)
                    cnt++;

            return cnt;
        }

        //☆☆☆☆☆ 位运算优化：n & (n - 1)把 n 的二进制位中的最低位的 1 变为 0
        public int hammingWeight8(int n) {
            int cnt = 0;

            while (n != 0) {
                n &= n - 1;
                cnt++;
            }

            return cnt;
        }

        //API
        public int hammingWeight7(int n) {
            return Integer.bitCount(n);
        }

        /*
         * 循环检查二进制位（优化）
         * >>    有符号位移（算术位移）：舍弃低位，高位用符号位填补
         * >>>   无符号位移（逻辑位移）：舍弃低位，高位用 0   填补
         */
        public int hammingWeight(int n) {
            int cnt = 0;

            while (n != 0) {
                cnt += n & 1;
                n >>>= 1;
            }

            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}