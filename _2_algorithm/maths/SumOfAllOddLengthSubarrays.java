//<p>给你一个正整数数组&nbsp;<code>arr</code>&nbsp;，请你计算所有可能的奇数长度子数组的和。</p>
//
//<p><strong>子数组</strong> 定义为原数组中的一个连续子序列。</p>
//
//<p>请你返回 <code>arr</code>&nbsp;中 <strong>所有奇数长度子数组的和</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [1,4,2,5,3]
//<strong>输出：</strong>58
//<strong>解释：</strong>所有奇数长度子数组和它们的和为：
//[1] = 1
//[4] = 4
//[2] = 2
//[5] = 5
//[3] = 3
//[1,4,2] = 7
//[4,2,5] = 11
//[2,5,3] = 10
//[1,4,2,5,3] = 15
//我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [1,2]
//<strong>输出：</strong>3
//<strong>解释：</strong>总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [10,11,12]
//<strong>输出：</strong>66
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr.length &lt;= 100</code></li> 
// <li><code>1 &lt;= arr[i] &lt;= 1000</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<p>你可以设计一个 O(n) 时间复杂度的算法解决此问题吗？</p>
//
//<div><li>👍 210</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

//1588.所有奇数长度子数组的和
//开题时间：2022-11-19 14:32:15
public class SumOfAllOddLengthSubarrays {
    public static void main(String[] args) {
        Solution solution = new SumOfAllOddLengthSubarrays().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前缀和
        public int sumOddLengthSubarrays9(int[] arr) {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i, cur = 0; j < arr.length; j += 2) {
                    cur += arr[j];
                    cur += j > i ? arr[j - 1] : 0;
                    sum += cur;
                }
            }
            return sum;
        }

        //数学（贡献法）：计算每个元素在多少个奇数长度子数组中出现。偶数=奇数+奇数 or 偶数+偶数
        public int sumOddLengthSubarrays(int[] arr) {
            int sum = 0;
            int n = arr.length;
            for (int i = 0; i < n; i++) {
                int j = n - i - 1;
                int oddL = (i + 1) / 2;
                int oddR = (j + 1) / 2;
                int evenL = i / 2 + 1;
                int evenR = j / 2 + 1;
                sum += arr[i] * (oddL * oddR + evenL * evenR);
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}