//<p>颠倒给定的 32 位无符号整数的二进制位。</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。</li> 
// <li>在 Java 中，编译器使用<a href="https://baike.baidu.com/item/二进制补码/5295284" target="_blank">二进制补码</a>记法来表示有符号整数。因此，在 <strong>示例 2</strong>&nbsp;中，输入表示有符号整数 <code>-3</code>，输出表示有符号整数 <code>-1073741825</code>。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 00000010100101000001111010011100
//<strong>输出：</strong>964176192 (00111001011110000010100101000000)
//<strong>解释：</strong>输入的二进制串 <strong>00000010100101000001111010011100 </strong>表示无符号整数<strong> 43261596</strong><strong>，
//    </strong> 因此返回 964176192，其二进制表示形式为 <strong>00111001011110000010100101000000</strong>。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 11111111111111111111111111111101
//<strong>输出：</strong>3221225471 (10111111111111111111111111111111)
//<strong>解释：</strong>输入的二进制串 <strong>11111111111111111111111111111101</strong> 表示无符号整数 4294967293，
//   &nbsp; 因此返回 3221225471 其二进制表示形式为 <strong>10111111111111111111111111111111 。</strong></pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>输入是一个长度为 <code>32</code> 的二进制字符串</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶</strong>: 如果多次调用这个函数，你将如何优化你的算法？</p>
//
//<div><li>👍 588</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.bit;

//190.颠倒二进制位
//开题时间：2023-01-05 17:16:44
public class ReverseBits {
    public static void main(String[] args) {
        Solution solution = new ReverseBits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits9(int n) {
            return Integer.reverse(n);
        }

        public int reverseBits8(int n) {
            int ans = 0, idx = 31;
            while (n != 0) {
                ans += (n & 1) << (idx--);
                n >>>= 1;
            }
            return ans;
        }

        public static final int M1A = 0b11111111_00000000_11111111_00000000;
        public static final int M1B = 0b00000000_11111111_00000000_11111111;

        public static final int M2A = 0b11110000_11110000_11110000_11110000;
        public static final int M2B = 0b00001111_00001111_00001111_00001111;

        public static final int M3A = 0b11001100_11001100_11001100_11001100;
        public static final int M3B = 0b00110011_00110011_00110011_00110011;

        public static final int M4A = 0b10101010_10101010_10101010_10101010;
        public static final int M4B = 0b01010101_01010101_01010101_01010101;

        //位运算分治（自顶向下）
        public int reverseBits7(int n) {
            n = (n >>> 16) | (n << 16);
            n = ((n & M1A) >>> 8) | ((n & M1B) << 8);
            n = ((n & M2A) >>> 4) | ((n & M2B) << 4);
            n = ((n & M3A) >>> 2) | ((n & M3B) << 2);
            return ((n & M4A) >>> 1) | ((n & M4B) << 1);
        }


        public static final int M1 = 0b10101010_10101010_10101010_10101010;
        public static final int M2 = 0b11001100_11001100_11001100_11001100;
        public static final int M3 = 0b11110000_11110000_11110000_11110000;
        public static final int M4 = 0b11111111_00000000_11111111_00000000;

        public int reverseBits6(int n) {
            n = ((n & M1) >>> 1) | ((n & (M1 >>> 1)) << 1);
            n = ((n & M2) >>> 2) | ((n & (M2 >>> 2)) << 2);
            n = ((n & M3) >>> 4) | ((n & (M3 >>> 4)) << 4);
            n = ((n & M4) >>> 8) | ((n & (M4 >>> 8)) << 8);
            return (n >>> 16) | (n << 16);
        }


        public static final int N1 = 0b01010101_01010101_01010101_01010101;
        public static final int N2 = 0b00110011_00110011_00110011_00110011;
        public static final int N3 = 0b00001111_00001111_00001111_00001111;
        public static final int N4 = 0b00000000_11111111_00000000_11111111;

        //☆☆☆☆☆ 位运算分治（自底向上）
        public int reverseBits(int n) {
            n = ((n & N1) << 1) | ((n >>> 1) & N1);
            n = ((n & N2) << 2) | ((n >>> 2) & N2);
            n = ((n & N3) << 4) | ((n >>> 4) & N3);
            n = ((n & N4) << 8) | ((n >>> 8) & N4);
            return (n << 16) | (n >>> 16);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}