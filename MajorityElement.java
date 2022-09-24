//<p>给定一个大小为 <code>n</code><em> </em>的数组&nbsp;<code>nums</code> ，返回其中的多数元素。多数元素是指在数组中出现次数 <strong>大于</strong>&nbsp;<code>⌊ n/2 ⌋</code>&nbsp;的元素。</p>
//
//<p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,2,3]
//<strong>输出：</strong>3</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,2,1,1,1,2,2]
//<strong>输出：</strong>2
//</pre>
//
//<p>&nbsp;</p> 
//<strong>提示：</strong>
//
//<ul> 
// <li><code>n == nums.length</code></li> 
// <li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。</p>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>分治</li><li>计数</li><li>排序</li></div></div><br><div><li>👍 1575</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems.algorithm.sort.Swap;

import java.util.*;

//169.多数元素
//开题时间：2022-09-24 13:35:07
public class MajorityElement {
    public static void main(String[] args) {
        Solution solution = new MajorityElement().new Solution();
        System.out.println(solution.majorityElement3(new int[]{1, 1, 1, 1, 1, 1, 8}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //内置排序函数    nlogn   1
        public int majorityElement(int[] nums) {
            //排序
            Arrays.sort(nums);

            //找多数元素
            //region 笨办法
            /*
            for (int i = 0; i < nums.length / 2; i++)
                if (nums[i] == nums[i + nums.length / 2])
                    return nums[i];

            return -1;
            */
            //endregion

            return nums[nums.length / 2];
        }

        //哈希映射  n   n
        public int majorityElement2(int[] nums) {
            Map<Integer, Integer> val2cnt = new HashMap<>();
            //计数
            for (int num : nums)
                val2cnt.merge(num, 1, Integer::sum);

            //找多数元素
            for (Map.Entry<Integer, Integer> entry : val2cnt.entrySet())
                if (entry.getValue() > nums.length / 2) return entry.getKey();

            return -1;
        }

        public int majorityElement3(int[] nums) {
            return helper(nums, 0, nums.length - 1);
        }

        //Boyer-Moore 投票算法  n   1
        public int majorityElement4(int[] nums) {
            int candidate = 0;
            int cnt = 0;
            for (int num : nums) {
                if (cnt == 0) candidate = num;
                cnt += candidate == num ? 1 : -1;
            }
            return candidate;
        }

        private static int helper(int[] arr, int start, int end) {
            if (end - start <= arr.length / 2) return arr[start];
            int mid = partition2(arr, start, end);
            if (2 * mid > start + end) return helper(arr, start, mid - 1);
            else if (2 * mid < start + end) return helper(arr, mid + 1, end);
            else return arr[mid];
        }

        //双指针分区
        private static int partition2(int[] arr, int start, int end) {
            //region 分区优化1（随机选择基数）
            int rndIdx = new Random().nextInt(start, end + 1);
            Swap.swap(arr, start, rndIdx);
            //endregion
            int pivot = arr[start];
            int l = start + 1;
            int r = end;
            while (l < r) {
                if (arr[l] > pivot) {
                    while (l < r && arr[r] > pivot) r--;
                    if (l != r) Swap.swap(arr, l++, r--);
                } else {
                    l++;
                }
            }
            if (l == r && arr[l] > pivot) r--;
            Swap.swap(arr, start, r);
            return r;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}