//<p>给你一个整数数组 <code>nums</code> 和一个整数&nbsp;<code>k</code> ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：</p>
//
//<ul> 
// <li>子数组大小 <strong>至少为 2</strong> ，且</li> 
// <li>子数组元素总和为 <code>k</code> 的倍数。</li> 
//</ul>
//
//<p>如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>如果存在一个整数 <code>n</code> ，令整数 <code>x</code> 符合 <code>x = n * k</code> ，则称 <code>x</code> 是 <code>k</code> 的一个倍数。<code>0</code> 始终视为 <code>k</code> 的一个倍数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [23<u>,2,4</u>,6,7], k = 6
//<strong>输出：</strong>true
//<strong>解释：</strong>[2,4] 是一个大小为 2 的子数组，并且和为 6 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [<u>23,2,6,4,7</u>], k = 6
//<strong>输出：</strong>true
//<strong>解释：</strong>[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [23,2,6,4,7], k = 13
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
// <li><code>0 &lt;= sum(nums[i]) &lt;= 2<sup>31</sup> - 1</code></li> 
// <li><code>1 &lt;= k &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><li>👍 481</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.HashMap;
import java.util.HashSet;

//523.连续的子数组和
//开题时间：2022-12-17 16:00:04
public class ContinuousSubarraySum {
    public static void main(String[] args) {
        Solution solution = new ContinuousSubarraySum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力前缀和（TLE）
        public boolean checkSubarraySumX(int[] nums, int k) {
            int n = nums.length;
            int[] prefix = new int[n + 1];
            for (int i = 1; i < n + 1; i++)
                prefix[i] = prefix[i - 1] + nums[i - 1];

            for (int i = 0; i < n - 1; i++)
                for (int j = i + 1; j < n; j++)
                    if ((prefix[j + 1] - prefix[i]) % k == 0)
                        return true;

            return false;
        }

        //暴力前缀和2（TLE）
        public boolean checkSubarraySum9(int[] nums, int k) {
            int n = nums.length;

            for (int i = 0; i < n - 1; i++)
                for (int j = i + 1, sum = nums[i]; j < n; j++)
                    if ((sum += nums[j]) % k == 0)
                        return true;

            return false;
        }

        //☆☆☆☆☆ 前缀和 + 哈希映射（同余原理：若a-b=n*k，则有a%k=b%k）
        public boolean checkSubarraySum8(int[] nums, int k) {
            HashMap<Integer, Integer> mod2idx = new HashMap<>();
            int sum = 0;
            mod2idx.put(0, -1);

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                int key = sum % k;
                if (mod2idx.containsKey(key)) {
                    if (i - mod2idx.get(key) > 1)
                        return true;
                } else
                    mod2idx.put(key, i);
            }

            return false;
        }

        //☆☆☆ 前缀和 + 哈希集合
        public boolean checkSubarraySum7(int[] nums, int k) {
            int n = nums.length;
            int[] prefix = new int[n + 1];
            for (int i = 1; i < n + 1; i++)
                prefix[i] = prefix[i - 1] + nums[i - 1];

            HashSet<Integer> mods = new HashSet<>();
            for (int i = 2; i <= n; i++) {
                mods.add(prefix[i - 2] % k);
                if (mods.contains(prefix[i] % k))
                    return true;
            }

            return false;
        }

        //单哈希集合
        public boolean checkSubarraySum(int[] nums, int k) {
            HashSet<Integer> mods = new HashSet<>();

            for (int i = 2, sum = 0; i <= nums.length; i++) {
                mods.add(sum % k);
                sum += nums[i - 2];
                if (mods.contains((sum + nums[i - 1]) % k))
                    return true;
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}