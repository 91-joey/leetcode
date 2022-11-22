//<p>一个正整数如果能被 <code>a</code> 或 <code>b</code> 整除，那么它是神奇的。</p>
//
//<p>给定三个整数 <code>n</code> ,&nbsp;<code>a</code> , <code>b</code> ，返回第 <code>n</code> 个神奇的数字。因为答案可能很大，所以返回答案&nbsp;<strong>对&nbsp;</strong><code>10<sup>9</sup>&nbsp;+ 7</code> <strong>取模&nbsp;</strong>后的值。</p>
//
//<p>&nbsp;</p>
//
//<ol> 
//</ol>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1, a = 2, b = 3
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 4, a = 2, b = 3
//<strong>输出：</strong>6
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li> 
// <li><code>2 &lt;= a, b &lt;= 4 * 10<sup>4</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<div><li>👍 120</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

//878.第 N 个神奇数字
//开题时间：2022-11-22 08:38:56
public class NthMagicalNumber {
    public static void main(String[] args) {
        Solution solution = new NthMagicalNumber().new Solution();
//        System.out.println(solution.nthMagicalNumber(4, 2, 3));
        System.out.println(solution.nthMagicalNumber(307673195, 37340, 36427));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int MOD = 10_0000_0007;

        public int nthMagicalNumber9(int n, int a, int b) {
            if (a > b)
                return nthMagicalNumber(n, b, a);

            if (b % a == 0)
                return (int) ((long) n * a % MOD);

            int lcm = lcm(a, b);
            int circles = lcm / a + lcm / b - 1;
            long ans = (long) n / circles * lcm % MOD;

            int incr = 0;
            for (int i = 0, limit = n % circles, x = 0, y = 0; i < limit; i++) {
                if (x + a < y + b)
                    incr = (x = x + a);
                else
                    incr = (y = y + b);
            }
            return (int) ((ans + incr) % MOD);
        }


        //二分，设f(x)表示<=x的神奇数的个数，则f(x)为单调递增函数，f(x)=x/a+x/b+x/lcm
        public int nthMagicalNumber(int n, int a, int b) {
//            if (a > b)
//                return nthMagicalNumber(n, b, a);
//            if (b % a == 0)
//                return (int) ((long) n * a % MOD);

            int lcm = lcm(a, b);
            //最小公倍数范围内的神奇数个数
            int circles = lcm / a + lcm / b - 1;
            long ans = (long) n / circles * lcm;
            int l = 0, r = lcm - 1, target = n % circles;
            while (l < r) {
                int mid = l + ((r - l) >> 1);
                int cnt = mid / a + mid / b;
                if (target <= cnt)
                    r = mid;
                else
                    l = mid + 1;
            }
            return (int) ((ans + r) % MOD);
        }

        public static int gcd(int a, int b) {
            return b != 0 ?
                    gcd(b, a % b) :
                    a;
        }

        public static int lcm(int a, int b) {
            return a * b / gcd(a, b);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}