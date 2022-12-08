//给你一个由 <strong>无重复</strong> 正整数组成的集合 <code>nums</code> ，请你找出并返回其中最大的整除子集 <code>answer</code> ，子集中每一元素对 <code>(answer[i], answer[j])</code> 都应当满足：
//
//<ul> 
// <li><code>answer[i] % answer[j] == 0</code> ，或</li> 
// <li><code>answer[j] % answer[i] == 0</code></li> 
//</ul>
//
//<p>如果存在多个有效解子集，返回其中任何一个均可。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3]
//<strong>输出：</strong>[1,2]
//<strong>解释：</strong>[1,3] 也会被视为正确答案。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,4,8]
//<strong>输出：</strong>[1,2,4,8]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>9</sup></code></li> 
// <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li> 
//</ul>
//
//<div><li>👍 476</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//368.最大整除子集
//开题时间：2022-12-01 14:34:44
public class LargestDivisibleSubset {
    public static void main(String[] args) {
        Solution solution = new LargestDivisibleSubset().new Solution();
        System.out.println(solution.largestDivisibleSubset(new int[]{3, 17}));
//        System.out.println(solution.largestDivisibleSubset(new int[]{1, 2, 3}));
//        System.out.println(solution.largestDivisibleSubset(new int[]{3, 4, 16, 8}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 先排序
         *
         * 状态定义: dp[i]: 以 arr[i]为尾元素的子集最大长度，长度存储在dp[i][0]，次尾元素索引+1存储在dp[i][1]
         * 状态转移方程：dp[i][0]=max(dp[j][0]+1),j < i && nums[i] % nums[j] == 0
         * 初始化：
         *      dp[i]=1,0<=i<n
         *      max=0
         *      idx=1
         * 输出：
         *      max=max(dp[0..n-1])
         */
        public List<Integer> largestDivisibleSubset9(int[] nums) {
            Arrays.sort(nums);

            int max = 0;
            int idx = 1;
            int n = nums.length;
            int[][] dp = new int[n][2];
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++)
                    if (nums[i] % nums[j] == 0)
                        if (dp[i][0] < dp[j][0] + 1) {
                            dp[i][0] = dp[j][0] + 1;
                            dp[i][1] = j + 1;
                        }
                if (dp[i][0] > max) {
                    max = dp[i][0];
                    idx = i + 1;
                }
            }

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = idx; i != 0; i = dp[i - 1][1])
                list.add(nums[i - 1]);

            return list;
        }

        //GJ DP+回溯
        public List<Integer> largestDivisibleSubset8(int[] nums) {
            Arrays.sort(nums);

            int max = 0, idx = 0;
            int n = nums.length;
            int[] dp = new int[n];
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++)
                    if (nums[i] % nums[j] == 0)
                        if (dp[i] < dp[j] + 1) {
                            dp[i] = dp[j] + 1;
                        }
                if (dp[i] > max) {
                    max = dp[i];
                    idx = i;
                }
            }

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = idx; i >= 0; i--) {
                if (dp[i] == max && nums[idx] % nums[i] == 0) {
                    list.add(nums[i]);
                    max--;
                    idx = i;
                }
            }
            return list;
        }

        //☆☆☆☆☆ DP+回溯（多开一个数组记录状态转移来源）
        public List<Integer> largestDivisibleSubset(int[] nums) {
            Arrays.sort(nums);

            int max = 0, idx = 0;
            int n = nums.length;
            int[] f = new int[n];
            //对于求方案数的题目，多开一个数组来记录状态从何转移而来是最常见的手段。
            int[] g = new int[n];
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++)
                    if (nums[i] % nums[j] == 0)
                        if (f[i] < f[j] + 1) {
                            f[i] = f[j] + 1;
                            g[i] = j;
                        }
                if (f[i] > max) {
                    max = f[i];
                    idx = i;
                }
            }

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = max; i >= 0; i--) {
                list.add(nums[idx]);
                idx = g[idx];
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}