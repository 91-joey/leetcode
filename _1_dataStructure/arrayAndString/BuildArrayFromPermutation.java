//<p>给你一个 <strong>从 0 开始的排列</strong> <code>nums</code>（<strong>下标也从 0 开始</strong>）。请你构建一个 <strong>同样长度</strong> 的数组 <code>ans</code> ，其中，对于每个 <code>i</code>（<code>0 &lt;= i &lt; nums.length</code>），都满足 <code>ans[i] = nums[nums[i]]</code> 。返回构建好的数组 <code>ans</code> 。</p>
//
//<p><strong>从 0 开始的排列</strong> <code>nums</code> 是一个由 <code>0</code> 到&nbsp;<code>nums.length - 1</code>（<code>0</code> 和 <code>nums.length - 1</code> 也包含在内）的不同整数组成的数组。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>nums = [0,2,1,5,3,4]
//<strong>输出：</strong>[0,1,2,4,5,3]<strong>
//解释：</strong>数组 ans 构建如下：
//ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
//    = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
//    = [0,1,2,4,5,3]</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>nums = [5,0,1,2,3,4]
//<strong>输出：</strong>[4,5,0,1,2,3]
//<strong>解释：</strong>数组 ans 构建如下：
//ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
//    = [nums[5], nums[0], nums[1], nums[2], nums[3], nums[4]]
//    = [4,5,0,1,2,3]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= nums[i] &lt; nums.length</code></li> 
// <li><code>nums</code> 中的元素 <strong>互不相同</strong></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>模拟</li></div></div><br><div><li>👍 43</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.Arrays;

//1920.基于排列构建数组
//开题时间：2022-11-03 08:40:26
public class BuildArrayFromPermutation {
    public static void main(String[] args) {
        Solution solution = new BuildArrayFromPermutation().new Solution();
        System.out.println(Arrays.toString(solution.buildArray3(new int[]{0, 2, 1, 5, 3, 4})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //弱智都想的出来
        public int[] buildArray(int[] nums) {
            int[] ans = new int[nums.length];
            for (int i = 0; i < nums.length; i++)
                ans[i] = nums[nums[i]];
            return ans;
        }

        //两次遍历法（第一次遍历：高位存储最终值，低位存储原值；第二次遍历：取高位最终值）
        public int[] buildArray2(int[] nums) {
            for (int i = 0; i < nums.length; i++)
                nums[i] += nums[nums[i]] % 1000 * 1000;

            for (int i = 0; i < nums.length; i++)
                nums[i] /= 1000;

            return nums;
        }

        //两次遍历法（优化：位运算）
        public int[] buildArray3(int[] nums) {
            int mask = (1 << 10) - 1;
            for (int i = 0; i < nums.length; i++)
                nums[i] |= ((nums[nums[i]] & mask) << 10);

            for (int i = 0; i < nums.length; i++)
                nums[i] >>= 10;

            return nums;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}