//<p>两个数对&nbsp;<code>(a, b)</code> 和 <code>(c, d)</code> 之间的 <strong>乘积差</strong> 定义为 <code>(a * b) - (c * d)</code> 。</p>
//
//<ul> 
// <li>例如，<code>(5, 6)</code> 和 <code>(2, 7)</code> 之间的乘积差是 <code>(5 * 6) - (2 * 7) = 16</code> 。</li> 
//</ul>
//
//<p>给你一个整数数组 <code>nums</code> ，选出四个 <strong>不同的</strong> 下标 <code>w</code>、<code>x</code>、<code>y</code> 和 <code>z</code> ，使数对 <code>(nums[w], nums[x])</code> 和 <code>(nums[y], nums[z])</code> 之间的 <strong>乘积差</strong> 取到 <strong>最大值</strong> 。</p>
//
//<p>返回以这种方式取得的乘积差中的 <strong>最大值</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>nums = [5,6,2,7,4]
//<strong>输出：</strong>34
//<strong>解释：</strong>可以选出下标为 1 和 3 的元素构成第一个数对 (6, 7) 以及下标 2 和 4 构成第二个数对 (2, 4)
//乘积差是 (6 * 7) - (2 * 4) = 34
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>nums = [4,2,5,9,7,4,8]
//<strong>输出：</strong>64
//<strong>解释：</strong>可以选出下标为 3 和 6 的元素构成第一个数对 (9, 8) 以及下标 1 和 5 构成第二个数对 (2, 4)
//乘积差是 (9 * 8) - (2 * 4) = 64
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>4 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 28</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort;

import java.util.Arrays;

//1913.两个数对之间的最大乘积差
//开题时间：2023-01-07 18:02:40
public class MaximumProductDifferenceBetweenTwoPairs {
    public static void main(String[] args) {
        Solution solution = new MaximumProductDifferenceBetweenTwoPairs().new Solution();
        System.out.println(solution.maxProductDifference(new int[]{4, 2, 5, 9, 7, 4, 8}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProductDifference9(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length - 1] * nums[nums.length - 2] - nums[0] * nums[1];
        }

        public int maxProductDifference(int[] nums) {
            int[] init = Arrays.copyOfRange(nums, 0, 4);
            Arrays.sort(init);
            int max = init[3], max2 = init[2];
            int min = init[0], min2 = init[1];
            for (int i = 4; i < nums.length; i++) {
                int x = nums[i];
                if (x > max) {
                    max2 = max;
                    max = x;
                } else if (x > max2) {
                    max2 = x;
                } else if (x < min) {
                    min2 = min;
                    min = x;
                } else if (x < min2) {
                    min2 = x;
                }
            }
            return max * max2 - min * min2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}