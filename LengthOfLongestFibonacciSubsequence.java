//<p>如果序列&nbsp;<code>X_1, X_2, ..., X_n</code>&nbsp;满足下列条件，就说它是&nbsp;<em>斐波那契式&nbsp;</em>的：</p>
//
//<ul> 
// <li><code>n &gt;= 3</code></li> 
// <li>对于所有&nbsp;<code>i + 2 &lt;= n</code>，都有&nbsp;<code>X_i + X_{i+1} = X_{i+2}</code></li> 
//</ul>
//
//<p>给定一个<strong>严格递增</strong>的正整数数组形成序列 arr&nbsp;，找到 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span style="background-color:#f9f2f4">arr</span></span></span></font></font>&nbsp;中最长的斐波那契式的子序列的长度。如果一个不存在，返回&nbsp;&nbsp;0 。</p>
//
//<p><em>（回想一下，子序列是从原序列 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span style="background-color:#f9f2f4">arr</span></span></span></font></font>&nbsp;中派生出来的，它从 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.600000381469727px"><span style="caret-color:#c7254e"><span style="background-color:#f9f2f4">arr</span></span></span></font></font>&nbsp;中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如，&nbsp;<code>[3, 5, 8]</code>&nbsp;是&nbsp;<code>[3, 4, 5, 6, 7, 8]</code>&nbsp;的一个子序列）</em></p>
//
//<p>&nbsp;</p>
//
//<ul> 
//</ul>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入: </strong>arr =<strong> </strong>[1,2,3,4,5,6,7,8]
//<strong>输出: </strong>5
//<strong>解释: </strong>最长的斐波那契式子序列为 [1,2,3,5,8] 。
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入: </strong>arr =<strong> </strong>[1,3,7,11,12,14,18]
//<strong>输出: </strong>3
//<strong>解释</strong>: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>3 &lt;= arr.length &lt;= 1000</code></li> 
// <li> <p><code>1 &lt;= arr[i] &lt; arr[i + 1] &lt;= 10^9</code></p> </li> 
//</ul>
//
//<div><li>👍 350</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.HashMap;

//873.最长的斐波那契子序列的长度
//开题时间：2022-11-29 14:37:56
public class LengthOfLongestFibonacciSubsequence {
    public static void main(String[] args) {
        Solution solution = new LengthOfLongestFibonacciSubsequence().new Solution();
        System.out.println(solution.lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * dp[i][j](i<j):以 arr[i]、arr[j]结尾的斐波那契序列最大长度
         * dp[i][j]=max(dp[k][i]+1,3)(dp[k]+dp[i]=dp[j])
         */
        public int lenLongestFibSubseq(int[] arr) {
            HashMap<Integer, Integer> val2idx = new HashMap<>();
            int n = arr.length;
            for (int i = 0; i < n; i++)
                val2idx.put(arr[i], i);

            int max = 0;
            int[][] dp = new int[n - 1][n];

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n && 2 * arr[i] > arr[j]; j++) {
                    Integer idx = val2idx.get(arr[j] - arr[i]);
                    if (idx != null)
                        dp[i][j] = dp[idx][i] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }

            return max == 0 ? 0 : max + 2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}