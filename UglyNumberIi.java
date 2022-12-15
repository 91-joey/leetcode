//<p>给你一个整数 <code>n</code> ，请你找出并返回第 <code>n</code> 个 <strong>丑数</strong> 。</p>
//
//<p><strong>丑数 </strong>就是只包含质因数&nbsp;<code>2</code>、<code>3</code> 和/或&nbsp;<code>5</code>&nbsp;的正整数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 10
//<strong>输出：</strong>12
//<strong>解释：</strong>[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出：</strong>1
//<strong>解释：</strong>1 通常被视为丑数。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 1690</code></li> 
//</ul>
//
//<div><li>👍 1011</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.HashSet;
import java.util.PriorityQueue;

//264.丑数 II
//开题时间：2022-12-15 16:06:07
public class UglyNumberIi {
    public static void main(String[] args) {
        Solution solution = new UglyNumberIi().new Solution();
        System.out.println(solution.nthUglyNumber(1690));
//        System.out.println(solution.nthUglyNumber(1352));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int nthUglyNumberX(int n) {
            for (int i = 1; ; i++)
                if (isUgly(i))
                    if (--n == 0)
                        return i;
        }

        public int nthUglyNumber9(int n) {
            int[] f = new int[n + 1];
            f[1] = 1;
            for (int i = 2, a = 1, b = 1, c = 1; i <= n; i++) {
                f[i] = Math.min(Math.min(f[a] * 2, f[b] * 3), f[c] * 5);
                if (f[i] == f[a] * 2) a++;
                if (f[i] == f[b] * 3) b++;
                if (f[i] == f[c] * 5) c++;
            }
            return f[n];
        }

        public int nthUglyNumber(int n) {
            int[] factors = {2, 3, 5};
            HashSet<Long> seen = new HashSet<>();
            PriorityQueue<Long> pq = new PriorityQueue<>();
            seen.add(1L);
            pq.offer(1L);

            long ans = 1;
            for (int i = 0; i < n; i++) {
                ans = pq.poll();
                for (int factor : factors)
                    if (seen.add(ans * factor))
                        pq.offer(ans * factor);
            }

            return (int) ans;
        }

        public static boolean isUgly(int n) {
            if (n <= 0) return false;
            while (n % 2 == 0) n /= 2;
            while (n % 3 == 0) n /= 3;
            while (n % 5 == 0) n /= 5;
            return n == 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}