//<p>给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用&nbsp;<a href="https://baike.baidu.com/item/%E8%A1%A5%E7%A0%81/6854613?fr=aladdin">补码运算</a>&nbsp;方法。</p>
//
//<p><strong>注意:</strong></p>
//
//<ol> 
// <li>十六进制中所有字母(<code>a-f</code>)都必须是小写。</li> 
// <li>十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符<code>'0'</code>来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。&nbsp;</li> 
// <li>给定的数确保在32位有符号整数范围内。</li> 
// <li><strong>不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。</strong></li> 
//</ol>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//输入:
//26
//
//输出:
//"1a"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//输入:
//-1
//
//输出:
//"ffffffff"
//</pre>
//
//<div><li>👍 260</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

import java.math.BigInteger;

//405.数字转换为十六进制数
//开题时间：2023-01-03 18:17:35
public class ConvertANumberToHexadecimal {
    public static void main(String[] args) {
        Solution solution = new ConvertANumberToHexadecimal().new Solution();
        System.out.println(solution.toHex(-1));
//        System.out.println(solution.toHex(26));
        System.out.println(Long.toString(4294967295L, 16));
        System.out.println(Long.MAX_VALUE);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    import java.math.BigInteger;

    class Solution {
        public String toHex9(int num) {
            return Integer.toHexString(num);
        }

        //attention：ffffffff is a number less than Long.MAX_VALUE
        public String toHex8(int num) {
            BigInteger x = new BigInteger(String.valueOf(num));
            BigInteger zero = new BigInteger("0");
            if (x.compareTo(zero) < 0)
                x = x.add(new BigInteger("100000000", 16));

            StringBuilder sb = new StringBuilder();
            BigInteger hex = new BigInteger("16");
            do {
                int mod = x.mod(hex).intValue();
                sb.append(switch (mod) {
                    case 10 -> 'a';
                    case 11 -> 'b';
                    case 12 -> 'c';
                    case 13 -> 'd';
                    case 14 -> 'e';
                    case 15 -> 'f';
                    default -> String.valueOf(mod);
                });
                x = x.divide(hex);
            } while (!x.equals(zero));

            return sb.reverse().toString();
        }

        //模拟 + 进制转换
        public String toHex7(int _num) {
            long num = _num;
            if (num < 0)
                num = (1L << 32) + num;
            StringBuilder sb = new StringBuilder();
            do {
                long mod = num % 16;
                sb.append((char) (mod + (mod < 10 ?
                        '0' :
                        -10 + 'a')));
                num /= 16;
            } while (num != 0);
            return sb.reverse().toString();
        }

        //☆☆☆☆☆ 位运算 + 分组统计
        public String toHex(int num) {
            StringBuilder sb = new StringBuilder();
            do {
                int mod = num & 15;
                sb.append((char) (mod + (mod < 10 ?
                        '0' :
                        -10 + 'a')));
                num >>>= 4;
            } while (num != 0);
            return sb.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}