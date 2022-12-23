//<p>最初记事本上只有一个字符 <code>'A'</code> 。你每次可以对这个记事本进行两种操作：</p>
//
//<ul> 
// <li><code>Copy All</code>（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。</li> 
// <li><code>Paste</code>（粘贴）：粘贴<strong> 上一次 </strong>复制的字符。</li> 
//</ul>
//
//<p>给你一个数字&nbsp;<code>n</code> ，你需要使用最少的操作次数，在记事本上输出 <strong>恰好</strong>&nbsp;<code>n</code>&nbsp;个 <code>'A'</code> 。返回能够打印出&nbsp;<code>n</code>&nbsp;个 <code>'A'</code> 的最少操作次数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>3
//<strong>输出：</strong>3
//<strong>解释：</strong>
//最初, 只有一个字符 'A'。
//第 1 步, 使用 <strong>Copy All</strong> 操作。
//第 2 步, 使用 <strong>Paste </strong>操作来获得 'AA'。
//第 3 步, 使用 <strong>Paste</strong> 操作来获得 'AAA'。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 492</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;

//650.只有两个键的键盘
//开题时间：2022-12-15 11:00:35
public class TwoKeysKeyboard {
    public static void main(String[] args) {
        Solution solution = new TwoKeysKeyboard().new Solution();
        System.out.println(solution.minSteps(3));
        System.out.println(Integer.parseInt("3f3f3f3f", 16));
        System.out.println(Integer.MAX_VALUE / 2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minStepsX(int n) {
            int sqrt = (int) Math.sqrt(n);
            while (n % sqrt != 0)
                sqrt--;
            return (sqrt == 1 ? 0 : sqrt) + (n / sqrt == 1 ? 0 : n / sqrt);
        }

        //质因数分解（复杂版）
        public int minSteps9(int n) {
            int ans = 0;

            while (n != 1)
                for (int i = 2; i <= n; i++)
                    if (n % i == 0) {
                        ans += i;
                        n /= i;
                        break;
                    }

            return ans;
        }

        //☆☆☆☆☆ 质因数分解（优化版）
        public int minSteps8(int n) {
            int ans = 0;

            for (int i = 2; i * i <= n; i++)
                while (n % i == 0) {
                    ans += i;
                    n /= i;
                }

            if (n > 1)
                ans += n;

            return ans;
        }

        /*
         * DP定义：f[i][j]表示经过最后一次操作，记事本上有i个字符、粘贴板上有j个字符的最少操作数
         * 状态转移：f[i][j]=min(f[i-j][j],{f[i][k]})+1, 1<=k<i
         * 答案：min{f[n][j]}
         */
        public int minSteps7(int n) {
            if (n == 1)
                return 0;

            int[][] f = new int[n + 1][n + 1];
            for (int[] arr : f)
                Arrays.fill(arr, 0x3f3f3f3f);

            f[1][0] = 0;
            f[1][1] = 1;
            for (int i = 2; i < n + 1; i++) {
                int min = 0x3f3f3f3f;
                for (int j = 0; j <= i / 2; j++) {
                    f[i][j] = f[i - j][j] + 1;
                    min = Math.min(min, f[i][j]);
                }
                f[i][i] = min + 1;
            }

            return Arrays.stream(f[n])
//                    .limit(n / 2 + 1)
//                    .skip(1)
                    .min()
                    .getAsInt();
        }

        //按题意定义DP
        public int minSteps(int n) {
            int[] f = new int[n + 1];
            for (int i = 2; i < n + 1; i++) {
                f[i] = 0x3f3f3f3f;
                int sqrt = (int) Math.sqrt(i);
                for (int j = 1; j <= sqrt; j++)
                    if (i % j == 0)
                        f[i] = min(f[i], f[j] + i / j, f[i / j] + j);
            }
            return f[n];
        }

        public static int min(int... arr) {
            int min = Integer.MAX_VALUE;
            for (int x : arr) if (min > x) min = x;
            return min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}