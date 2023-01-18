//<p>给定整数 <code>n</code> ，返回 <em>所有小于非负整数&nbsp;<code>n</code>&nbsp;的质数的数量</em> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 10
//<strong>输出：</strong>4
//<strong>解释：</strong>小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 0
//<strong>输出：</strong>0
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出</strong>：0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= n &lt;= 5 * 10<sup>6</sup></code></li> 
//</ul>
//
//<div><li>👍 991</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

//204.计数质数
//开题时间：2023-01-01 12:41:52
public class CountPrimes {
    public static void main(String[] args) {
        Solution solution = new CountPrimes().new Solution();
        System.out.println(solution.countPrimes(10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //枚举 n*sqrt(n)
        public int countPrimesX(int n) {
            int ans = 0;
            for (int i = 2; i < n; i++)
                if (isPrime(i))
                    ans++;
            return ans;
        }

        //☆☆☆☆☆ 埃氏筛   n * log log n
        public int countPrimes(int n) {
            boolean[] notPrime = new boolean[n];
            int sqrt = (int) Math.sqrt(n);
            for (int i = 2; i <= sqrt; i++)
                if (!notPrime[i])
                    for (int j = i * i; j < n; j += i)
                        notPrime[j] = true;

            int ans = 0;
            for (int i = 2; i < n; i++)
                if (!notPrime[i])
                    ans++;
            return ans;
        }

        private static boolean isPrime(int x) {
            int sqrt = (int) Math.sqrt(x);
            for (int i = 2; i <= sqrt; i++)
                if (x % i == 0)
                    return false;
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}