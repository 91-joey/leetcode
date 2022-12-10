//<p>给定一个非负整数数组 <code>nums</code> 和一个整数&nbsp;<code>m</code> ，你需要将这个数组分成&nbsp;<code>m</code><em>&nbsp;</em>个非空的连续子数组。</p>
//
//<p>设计一个算法使得这&nbsp;<code>m</code><em>&nbsp;</em>个子数组各自和的最大值最小。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [7,2,5,10,8], m = 2
//<strong>输出：</strong>18
//<strong>解释：</strong>
//一共有四种方法将 nums 分割为 2 个子数组。 
//其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,4,5], m = 2
//<strong>输出：</strong>9
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,4,4], m = 3
//<strong>输出：</strong>4
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li> 
// <li><code>1 &lt;= m &lt;= min(50, nums.length)</code></li> 
//</ul>
//
//<div><li>👍 759</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//410.分割数组的最大值
//开题时间：2022-12-10 17:02:33
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        Solution solution = new SplitArrayLargestSum().new Solution();
        System.out.println(solution.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int splitArray(int[] nums, int k) {
            int n = nums.length + 1;
            int[][] f = new int[n][k + 1];

            for (int i = 1; i < n; i++) {
                int bound = Math.min(k + 1, i + 1);
                for (int j = 1; j < bound; j++) {
                    for (int x = i - 1, sum = 0; x >= j - 1; x--) {
                        f[i][j] = Math.min(f[i][j], Math.max(f[x][j - 1], (sum += nums[x])));
                    }
                }
            }

            return f[n - 1][k];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}