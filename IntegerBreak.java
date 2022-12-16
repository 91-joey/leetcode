//<p>给定一个正整数&nbsp;<code>n</code>&nbsp;，将其拆分为 <code>k</code> 个 <strong>正整数</strong> 的和（&nbsp;<code>k &gt;= 2</code>&nbsp;），并使这些整数的乘积最大化。</p>
//
//<p>返回 <em>你可以获得的最大乘积</em>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入: </strong>n = 2
//<strong>输出: </strong>1
//<strong>解释: </strong>2 = 1 + 1, 1 × 1 = 1。</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入: </strong>n = 10
//<strong>输出: </strong>36
//<strong>解释: </strong>10 = 3 + 3 + 4, 3 ×&nbsp;3 ×&nbsp;4 = 36。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>2 &lt;= n &lt;= 58</code></li> 
//</ul>
//
//<div><li>👍 1000</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//343.整数拆分
//开题时间：2022-12-16 11:49:13
public class IntegerBreak {
    public static void main(String[] args) {
        Solution solution = new IntegerBreak().new Solution();
        System.out.println(solution.integerBreak(58));
        System.out.println(Math.pow(3, -1));
        System.out.println(Math.pow(3, -2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int integerBreakX(int n) {
            int max = 1;
            for (int i = 2; i < n; i++) {
                int avrg = n / i;
                max = Math.max(max, (int) Math.pow(avrg, i - 1) * (n - avrg * (i - 1)));
                if (avrg == 1)
                    break;
            }
            return max;
        }

        /*
         * ☆☆☆ DP（自解）
         *  f[i]=max(i / 2 * (i - i / 2), {j * f[i - j]})    2 <= j <= i-2
         */
        public int integerBreak9(int n) {
            int[] f = new int[n + 1];
            f[2] = 1;
            for (int i = 3; i < n + 1; i++) {
                f[i] = i / 2 * (i - i / 2);
                for (int j = 2; i - j >= 2; j++)
                    f[i] = Math.max(f[i], j * f[i - j]);
            }
            return f[n];
        }

        //DP优化
        public int integerBreak7(int n) {
            int[] f = new int[n + 1];
            f[2] = 1;
            for (int i = 3; i < n + 1; i++)
                f[i] = Math.max(i / 2 * (i - i / 2), Math.max(2 * f[i - 2], 3 * f[i - 3]));
            return f[n];
        }

        //☆☆☆☆☆ 数学
        public int integerBreak(int n) {
            return switch (n) {
                case 2, 3 -> n - 1;
                default -> (int) (Math.pow(3, n / 3 - 1) * switch (n % 3) {
                    case 0 -> 3;
                    case 1 -> 4;
                    default -> 6;
                });
            };
        }

        /*
         * 贪心选择：
         *  当 n 足够大（n > 4)时，应尽可能的多拆分出 3 来
         *  其他情况直接作特殊考虑
         */
        public int integerBreak8(int n) {
            return switch (n) {
                case 2 -> 1;
                case 3 -> 2;
//                case 4 -> 4;
                default -> {
                    int ans = 1;
                    while (n > 4) {
                        ans *= 3;
                        n -= 3;
                    }
                    yield ans * n;
                }
            };
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}