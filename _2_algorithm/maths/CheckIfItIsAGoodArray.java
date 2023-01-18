//<p>给你一个正整数数组 <code>nums</code>，你需要从中任选一些子集，然后将子集中每一个数乘以一个 <strong>任意整数</strong>，并求出他们的和。</p>
//
//<p>假如该和结果为&nbsp;<code>1</code>，那么原数组就是一个「<strong>好数组</strong>」，则返回 <code>True</code>；否则请返回 <code>False</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>nums = [12,5,7,23]
//<strong>输出：</strong>true
//<strong>解释：</strong>挑选数字 5 和 7。
//5*3 + 7*(-2) = 1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>nums = [29,6,10]
//<strong>输出：</strong>true
//<strong>解释：</strong>挑选数字 29, 6 和 10。
//29*1 + 6*(-3) + 10*(-1) = 1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>nums = [3,6]
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10^5</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10^9</code></li> 
//</ul>
//
//<div><li>👍 36</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

import java.util.Arrays;

//1250.检查「好数组」
//开题时间：2023-01-18 09:11:05
public class CheckIfItIsAGoodArray {
    public static void main(String[] args) {
        Solution solution = new CheckIfItIsAGoodArray().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * ☆☆☆☆☆ 裴蜀定理（两数：x、y的最大公约数为 d，ax+by=kd 恒成立）
         * 直接求所有数的最大公约数，「 n-1 个数的最大公约数」 恒大于等于 「 n 个数的最大公约数」
         */
        public boolean isGoodArray(int[] nums) {
            return Arrays.stream(nums).reduce(Solution::gcd).getAsInt() == 1;
        }

        public static int gcd(int a, int b) {
            return b != 0 ?
                    gcd(b, a % b) :
                    a;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}