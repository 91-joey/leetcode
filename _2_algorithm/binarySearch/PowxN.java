//<p>实现&nbsp;<a href="https://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(<em>x</em>, <em>n</em>)</a>&nbsp;，即计算 <code>x</code> 的整数&nbsp;<code>n</code> 次幂函数（即，<code>x<sup>n</sup></code><sup><span style="font-size:10.8333px"> </span></sup>）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>x = 2.00000, n = 10
//<strong>输出：</strong>1024.00000
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>x = 2.10000, n = 3
//<strong>输出：</strong>9.26100
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>x = 2.00000, n = -2
//<strong>输出：</strong>0.25000
//<strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-100.0 &lt; x &lt; 100.0</code></li> 
// <li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li> 
// <li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>递归</li><li>数学</li></div></div><br><div><li>👍 1067</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

//50.Pow(x, n)
//开题时间：2022-11-02 13:34:24
public class PowxN {
    public static void main(String[] args) {
        Solution solution = new PowxN().new Solution();
//        System.out.println(Math.pow(0, 2));
        System.out.println(solution.myPow5(2.0, 10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double myPow(double x, int n) {
            return Math.pow(x, n);
        }

        public double myPow2(double x, int n) {
            double pow = 1.0;
            for (int i = 0; i < Math.abs(n); i++)
                pow *= x;
            return n >= 0 ? pow : 1.0 / pow;
        }

        public double myPow3(double x, int n) {
            double pow = pow(x, Math.abs(n));
            return n >= 0 ? pow : 1.0 / pow;
        }

        private double pow(double x, int n) {
            if (n == 0)
                return 1.0;
            if (n == 1)
                return x;
            double pow = pow(x, n / 2);
            return (n & 1) == 0 ? pow * pow : pow * pow * x;
        }

        //递归    logn logn
        public double myPow4(double x, int n) {
            return n >= 0 ?
                    pow2(x, n) :
                    1.0 / pow2(x, -n);
        }

        private double pow2(double x, int n) {
            if (n == 0)
                return 1.0;
            double pow = pow2(x, n / 2);
            return (n & 1) == 0 ? pow * pow : pow * pow * x;
        }

        //☆☆☆☆☆ 迭代    logn    1
        //x^65=x^1 * x^2 * x^4 * x^8 * x^16 * x^32
        //x^57=x^1 * x^2 * x^4       * x^16 * x^32
        public double myPow5(double x, int n) {
            double pow = 1.0;
            long abs = Math.abs((long) n);
            while (abs != 0) {
                if ((abs & 1) == 1)
                    pow *= x;
                abs >>= 1;
                x *= x;
            }
            return n >= 0 ? pow : 1.0 / pow;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}