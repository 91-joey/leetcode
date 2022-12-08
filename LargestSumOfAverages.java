//<p>给定数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。我们将给定的数组&nbsp;<code>nums</code>&nbsp;分成 <strong>最多</strong>&nbsp;<code>k</code>&nbsp;个相邻的非空子数组 。&nbsp;<strong>分数</strong> 由每个子数组内的平均值的总和构成。</p>
//
//<p>注意我们必须使用 <code>nums</code> 数组中的每一个数进行分组，并且分数不一定需要是整数。</p>
//
//<p>返回我们所能得到的最大 <strong>分数</strong> 是多少。答案误差在&nbsp;<code>10<sup>-6</sup></code>&nbsp;内被视为是正确的。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [9,1,2,3,9], k = 3
//<strong>输出:</strong> 20.00000
//<strong>解释:</strong> 
//nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20. 
//我们也可以把 nums 分成[9, 1], [2], [3, 9]. 
//这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [1,2,3,4,5,6,7], k = 4
//<strong>输出:</strong> 20.50000
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= k &lt;= nums.length</code></li> 
//</ul>
//
//<div><li>👍 280</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//813.最大平均值和的分组
//开题时间：2022-12-08 12:21:17
public class LargestSumOfAverages {
    public static void main(String[] args) {
        Solution solution = new LargestSumOfAverages().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double largestSumOfAverages(int[] nums, int k) {
            int n = nums.length;
            double[][] f = new double[n][k];

            //f[i][j]:前 i+1 个数，分成 j+1 个子数组的最大子数组平均值总和
            for (int i = 0; i < n; i++) {
                int bound = Math.min(k - 1, i);
                for (int j = 0; j <= bound; j++) {
                    for (int x = i - 1, sum = 0; x >= 0; x--) {
                        sum += nums[x];
                        f[i][j] = Math.max(f[i][j], f[x][j - 1] + (double) sum / (i - x));
                    }
                }
            }

            return f[n - 1][k - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}