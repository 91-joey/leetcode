//<p>给你一个整数数组&nbsp;<code>nums</code>，返回 <em>数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;等于&nbsp;<code>nums</code>&nbsp;中除&nbsp;<code>nums[i]</code>&nbsp;之外其余各元素的乘积</em>&nbsp;。</p>
//
//<p>题目数据 <strong>保证</strong> 数组&nbsp;<code>nums</code>之中任意元素的全部前缀元素和后缀的乘积都在&nbsp; <strong>32 位</strong> 整数范围内。</p>
//
//<p>请<strong>不要使用除法，</strong>且在&nbsp;<code>O(<em>n</em>)</code> 时间复杂度内完成此题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = <span><code>[1,2,3,4]</code></span>
//<strong>输出:</strong> <span><code>[24,12,8,6]</code></span>
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [-1,1,0,-3,3]
//<strong>输出:</strong> [0,0,9,0,0]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-30 &lt;= nums[i] &lt;= 30</code></li> 
// <li><strong>保证</strong> 数组&nbsp;<code>nums</code>之中任意元素的全部前缀元素和后缀的乘积都在&nbsp; <strong>32 位</strong> 整数范围内</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你可以在 <code>O(1)</code>&nbsp;的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组<strong>不被视为</strong>额外空间。）</p>
//
//<div><li>👍 1322</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;

//238.除自身以外数组的乘积
//开题时间：2022-12-17 18:01:58
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        Solution solution = new ProductOfArrayExceptSelf().new Solution();
        System.out.println(Arrays.toString(solution.productExceptSelf(new int[]{1, 2, 3, 4})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //前缀和+后缀和   O(n)空间
        public int[] productExceptSelf9(int[] nums) {
            int n = nums.length + 1;
            int[] pre = new int[n];
            int[] suf = new int[n];
            pre[0] = suf[n - 1] = 1;
            for (int i = 1; i < n; i++)
                pre[i] = pre[i - 1] * nums[i - 1];
            for (int i = n - 2; i >= 0; i--)
                suf[i] = suf[i + 1] * nums[i];

            for (int i = 0; i < nums.length; i++)
                nums[i] = pre[i] * suf[i + 1];

            return nums;
        }

        //除法
        public int[] productExceptSelf8(int[] nums) {
            int product = 1;
            int cnt0 = 0;
            int idx = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    cnt0++;
                    idx = i;
                } else
                    product *= nums[i];
            }

            if (cnt0 == 0) {
                for (int i = 0; i < nums.length; i++)
                    nums[i] = product / nums[i];
            } else {
                Arrays.fill(nums, 0);
                if (cnt0 == 1)
                    nums[idx] = product;
            }

            return nums;
        }

        //☆☆☆☆☆ 前缀和+后缀和   O(1)空间
        public int[] productExceptSelf7(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];

            ans[0] = 1;
            for (int i = 1; i < n; i++)
                ans[i] = ans[i - 1] * nums[i];

            for (int i = n - 1, suf = 1; i >= 0; i--) {
                ans[i] *= suf;
                suf *= nums[i];
            }

            return ans;
        }


        //前缀和+后缀和（天秀写法）   O(1)空间
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];

            Arrays.fill(ans, 1);
            for (int l = 0, r = n - 1, pre = 1, suf = 1; l < n; l++, r--) {
                ans[l] *= pre;
                ans[r] *= suf;
                pre *= nums[l];
                suf *= nums[r];
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}