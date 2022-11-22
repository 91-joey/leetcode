//<p>给定一个未排序的整数数组
// <meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;，&nbsp;<em>返回最长递增子序列的个数</em>&nbsp;。</p>
//
//<p><strong>注意</strong>&nbsp;这个数列必须是 <strong>严格</strong> 递增的。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> [1,3,5,4,7]
//<strong>输出:</strong> 2
//<strong>解释:</strong> 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> [2,2,2,2,2]
//<strong>输出:</strong> 5
//<strong>解释:</strong> 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong>&nbsp;</p>
//
//<p>
// <meta charset="UTF-8" /></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 2000</code></li> 
// <li><code>-10<sup>6</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>6</sup></code></li> 
//</ul>
//
//<div><li>👍 688</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//673.最长递增子序列的个数
//开题时间：2022-11-21 18:10:47
public class NumberOfLongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new NumberOfLongestIncreasingSubsequence().new Solution();
        System.out.println(solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findNumberOfLIS(int[] nums) {
            int max = 1;
            int n = nums.length;
            int[] dp = new int[n];
            int[] cnts = new int[n + 1];
            cnts[1] = n;
            Arrays.fill(dp, 1);
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++)
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
//                        cnts[dp[i]]++;
                    }
                max = Math.max(max, dp[i]);
            }

            return cnts[max];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}