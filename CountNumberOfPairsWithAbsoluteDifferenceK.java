//<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回数对&nbsp;<code>(i, j)</code>&nbsp;的数目，满足&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>|nums[i] - nums[j]| == k</code>&nbsp;。</p>
//
//<p><code>|x|</code>&nbsp;的值定义为：</p>
//
//<ul> 
// <li>如果&nbsp;<code>x &gt;= 0</code>&nbsp;，那么值为&nbsp;<code>x</code>&nbsp;。</li> 
// <li>如果&nbsp;<code>x &lt; 0</code>&nbsp;，那么值为&nbsp;<code>-x</code>&nbsp;。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>nums = [1,2,2,1], k = 1
//<b>输出：</b>4
//<b>解释：</b>差的绝对值为 1 的数对为：
//- [<em><strong>1</strong></em>,<em><strong>2</strong></em>,2,1]
//- [<em><strong>1</strong></em>,2,<em><strong>2</strong></em>,1]
//- [1,<em><strong>2</strong></em>,2,<em><strong>1</strong></em>]
//- [1,2,<em><strong>2</strong></em>,<em><strong>1</strong></em>]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>nums = [1,3], k = 3
//<b>输出：</b>0
//<b>解释：</b>没有任何数对差的绝对值为 3 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>nums = [3,2,1,5,4], k = 2
//<b>输出：</b>3
//<b>解释：</b>差的绝对值为 2 的数对为：
//- [<em><strong>3</strong></em>,2,<em><strong>1</strong></em>,5,4]
//- [<em><strong>3</strong></em>,2,1,<em><strong>5</strong></em>,4]
//- [3,<em><strong>2</strong></em>,1,5,<em><strong>4</strong></em>]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 200</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 100</code></li> 
// <li><code>1 &lt;= k &lt;= 99</code></li> 
//</ul>
//
//<div><li>👍 85</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.HashMap;

//2006.差的绝对值为 K 的数对数目
//开题时间：2022-11-14 14:15:04
public class CountNumberOfPairsWithAbsoluteDifferenceK {
    public static void main(String[] args) {
        Solution solution = new CountNumberOfPairsWithAbsoluteDifferenceK().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //数组计数  n+z-k   z
        public int countKDifference9(int[] nums, int k) {
            int[] freqs = new int[101];
            for (int num : nums)
                freqs[num]++;

            int cnt = 0;
            for (int i = 0; i < 101 - k; i++)
                cnt += freqs[i] * freqs[i + k];

            return cnt;
        }

        //哈希计数  n+Z Z
        public int countKDifference8(int[] nums, int k) {
            HashMap<Integer, Integer> val2cnt = new HashMap<>();
            for (int num : nums)
                val2cnt.merge(num, 1, Integer::sum);

            final int[] cnt = {0};
            val2cnt.forEach((key, val) -> {
                cnt[0] += val * val2cnt.getOrDefault(key + k, 0);
            });
            return cnt[0];
        }

        //暴力    n^2 1
        public int countKDifference7(int[] nums, int k) {
            int cnt = 0;
            int len = nums.length;
            for (int i = 0; i < len - 1; i++)
                for (int j = i + 1; j < len; j++)
                    if (Math.abs(nums[i] - nums[j]) == k)
                        cnt++;
            return cnt;
        }

        //数组计数（一次遍历）  n z
        public int countKDifference6(int[] nums, int k) {
            int cnt = 0;
            int[] freqs = new int[101];
            for (int num : nums) {
                cnt += (num - k >= 0 ? freqs[num - k] : 0)
                        + (num + k < 101 ? freqs[num + k] : 0);
                freqs[num]++;
            }

            return cnt;
        }

        //哈希计数（一次遍历）  n Z
        public int countKDifference(int[] nums, int k) {
            int cnt = 0;
            HashMap<Integer, Integer> val2cnt = new HashMap<>();

            for (int num : nums) {
                cnt += val2cnt.getOrDefault(num - k, 0) +
                        val2cnt.getOrDefault(num + k, 0);
                val2cnt.merge(num, 1, Integer::sum);
            }

            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}