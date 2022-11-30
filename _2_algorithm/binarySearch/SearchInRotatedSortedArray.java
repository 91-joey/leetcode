//<p>整数数组 <code>nums</code> 按升序排列，数组中的值 <strong>互不相同</strong> 。</p>
//
//<p>在传递给函数之前，<code>nums</code> 在预先未知的某个下标 <code>k</code>（<code>0 &lt;= k &lt; nums.length</code>）上进行了 <strong>旋转</strong>，使数组变为 <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code>（下标 <strong>从 0 开始</strong> 计数）。例如， <code>[0,1,2,4,5,6,7]</code> 在下标 <code>3</code> 处经旋转后可能变为&nbsp;<code>[4,5,6,7,0,1,2]</code> 。</p>
//
//<p>给你 <strong>旋转后</strong> 的数组 <code>nums</code> 和一个整数 <code>target</code> ，如果 <code>nums</code> 中存在这个目标值 <code>target</code> ，则返回它的下标，否则返回&nbsp;<code>-1</code>&nbsp;。</p>
//
//<p>你必须设计一个时间复杂度为 <code>O(log n)</code> 的算法解决此问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [<span><code>4,5,6,7,0,1,2]</code></span>, target = 0
//<strong>输出：</strong>4
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [<span><code>4,5,6,7,0,1,2]</code></span>, target = 3
//<strong>输出：</strong>-1</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1], target = 0
//<strong>输出：</strong>-1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 5000</code></li> 
// <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>nums</code> 中的每个值都 <strong>独一无二</strong></li> 
// <li>题目数据保证 <code>nums</code> 在预先未知的某个下标上进行了旋转</li> 
// <li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 2371</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

//33.搜索旋转排序数组
//开题时间：2022-10-28 17:39:56
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArray().new Solution();
        System.out.println(solution.search3(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //朴素解法: 先查找旋转点，再二分查找
        public int search(int[] nums, int target) {
            int len = nums.length;
            int i = 0;
            while (i < len - 1 && nums[i] < nums[i + 1])
                i++;

            if (target > nums[i] || (i < len - 1 && target < nums[i + 1]))
                return -1;

            int l, r;
            if (target >= nums[0]) {
                l = 0;
                r = i;
            } else {
                l = i + 1;
                r = len - 1;
            }

            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (target == nums[mid])
                    return mid;
                else if (target < nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            }

            return -1;
        }

        //暴力无脑
        public int search2(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++)
                if (target == nums[i])
                    return i;
            return -1;
        }

        int l;
        int r;
        int ans = -1;
        boolean found = false;

        //奇葩脑洞法
        public int search3(int[] nums, int target) {
            int len = nums.length;
            l = 0;
            r = len - 1;
            binarySearch(nums, target, l, r);
            if (ans != -1)
                return ans;

            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (target == nums[mid])
                    return mid;
                else if (target < nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            return -1;
        }

        private void binarySearch(int[] nums, int target, int l, int r) {
            if (found || l > r)
                return;
            if (nums[l] <= target && target <= nums[r]) {
                this.l = l;
                this.r = r;
                found = true;
                return;
            }

            int mid = l + (r - l) / 2;
            if (target == nums[mid]) {
                ans = mid;
                found = true;
            } else {
                binarySearch(nums, target, l, mid - 1);
                binarySearch(nums, target, mid + 1, r);
            }
        }

        //分4种情况，最后二分查找
        public int search4(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (target == nums[mid])
                    return mid;
                if (nums[l] <= nums[mid]) {
                    if (nums[l] <= target && target < nums[mid]) {
                        r = mid - 1;
                        break;
                    } else
                        l = mid + 1;
                } else {
                    if (nums[mid] < target && target <= nums[r]) {
                        l = mid + 1;
                        break;
                    } else {
                        r = mid - 1;
                    }
                }
            }

            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (target == nums[mid])
                    return mid;
                else if (target < nums[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            return -1;
        }


        public int search9(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            int lst = nums[nums.length - 1];
            while (l <= r) {
                int mid = ((r - l) >> 1) + l;
                if (target == nums[mid])
                    return mid;
                else if (
                        (target <= lst && nums[mid] <= lst && nums[mid] > target) ||
                                (target > lst && (nums[mid] > target || nums[mid] <= lst))
                )
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            return -1;
        }

        //☆☆☆☆☆ 极简异或
        public int search8(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            int lst = nums[r];
            while (l <= r) {
                int mid = ((r - l) >> 1) + l;
                if (target == nums[mid])
                    return mid;
                else if (target <= lst ^ nums[mid] <= lst ^ nums[mid] > target)
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}