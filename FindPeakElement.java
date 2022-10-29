//<p>峰值元素是指其值严格大于左右相邻值的元素。</p>
//
//<p>给你一个整数数组&nbsp;<code>nums</code>，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 <strong>任何一个峰值</strong> 所在位置即可。</p>
//
//<p>你可以假设&nbsp;<code>nums[-1] = nums[n] = -∞</code> 。</p>
//
//<p>你必须实现时间复杂度为 <code>O(log n)</code><em> </em>的算法来解决此问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = <span><code>[1,2,3,1]</code></span>
//<strong>输出：</strong>2
//<strong>解释：</strong>3 是峰值元素，你的函数应该返回其索引 2。</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = <span><code>[</code></span>1,2,1,3,5,6,4]
//<strong>输出：</strong>1 或 5 
//<strong>解释：</strong>你的函数可以返回索引 1，其峰值元素为 2；
//&nbsp;    或者返回索引 5， 其峰值元素为 6。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li> 
// <li>对于所有有效的 <code>i</code> 都有 <code>nums[i] != nums[i + 1]</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 920</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//162.寻找峰值
//开题时间：2022-10-29 12:11:28
public class FindPeakElement {
    public static void main(String[] args) {
        Solution solution = new FindPeakElement().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力
        public int findPeakElement(int[] nums) {
            int len = nums.length;
            if (len == 1 || nums[0] > nums[1])
                return 0;
            if (nums[len - 2] < nums[len - 1])
                return len - 1;

            for (int i = 1; i < len; i++)
                if (nums[i] > nums[i + 1])
                    return i;

            return -1;
        }

        //暴力（精简）
        public int findPeakElement2(int[] nums) {
            int len = nums.length;
            if (len == 1)
                return 0;

            for (int i = 0; i < len - 1; i++)
                if (nums[i] > nums[i + 1])
                    return i;

            return len - 1;
        }

        //二分查找
        public int findPeakElement3(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] > nums[mid + 1])
                    r = mid;
                else
                    l = mid + 1;
            }
            return l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}