//已知一个长度为 <code>n</code> 的数组，预先按照升序排列，经由 <code>1</code> 到 <code>n</code> 次 <strong>旋转</strong> 后，得到输入数组。例如，原数组 <code>nums = [0,1,2,4,5,6,7]</code> 在变化后可能得到：
//
//<ul> 
// <li>若旋转 <code>4</code> 次，则可以得到 <code>[4,5,6,7,0,1,2]</code></li> 
// <li>若旋转 <code>7</code> 次，则可以得到 <code>[0,1,2,4,5,6,7]</code></li> 
//</ul>
//
//<p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> <strong>旋转一次</strong> 的结果为数组 <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code> 。</p>
//
//<p>给你一个元素值 <strong>互不相同</strong> 的数组 <code>nums</code> ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 <strong>最小元素</strong> 。</p>
//
//<p>你必须设计一个时间复杂度为&nbsp;<code>O(log n)</code> 的算法解决此问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,4,5,1,2]
//<strong>输出：</strong>1
//<strong>解释：</strong>原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [4,5,6,7,0,1,2]
//<strong>输出：</strong>0
//<strong>解释：</strong>原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [11,13,15,17]
//<strong>输出：</strong>11
//<strong>解释：</strong>原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == nums.length</code></li> 
// <li><code>1 &lt;= n &lt;= 5000</code></li> 
// <li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li> 
// <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li> 
// <li><code>nums</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 850</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//153.寻找旋转排序数组中的最小值
//开题时间：2022-10-29 18:19:11
public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new FindMinimumInRotatedSortedArray().new Solution();
        System.out.println(solution.findMin3(new int[]{3,4,5,1,2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力
        public int findMin(int[] nums) {
            for (int i = 0; i < nums.length - 1; )
                if (nums[i] > nums[++i])
                    return nums[i];
            return nums[0];
        }

        public int findMin2(int[] nums) {
            int l = 0, r = nums.length - 1;
            if (nums[l] < nums[r])
                return nums[l];

            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] >= nums[0])
                    l = mid + 1;
                else
                    r = mid;
            }
            return nums[l];
        }

        //☆☆☆☆☆ 优雅精简二分法
        public int findMin3(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] > nums[r])
                    l = mid + 1;
                else
                    r = mid;
            }
            return nums[l];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}