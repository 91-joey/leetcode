//<p>给定一个包含红色、白色和蓝色、共&nbsp;<code>n</code><em> </em>个元素的数组
// <meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;，<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a></strong>对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。</p>
//
//<p>我们使用整数 <code>0</code>、&nbsp;<code>1</code> 和 <code>2</code> 分别表示红色、白色和蓝色。</p>
//
//<ul> 
//</ul>
//
//<p>必须在不使用库的sort函数的情况下解决这个问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,0,2,1,1,0]
//<strong>输出：</strong>[0,0,1,1,2,2]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,0,1]
//<strong>输出：</strong>[0,1,2]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == nums.length</code></li> 
// <li><code>1 &lt;= n &lt;= 300</code></li> 
// <li><code>nums[i]</code> 为 <code>0</code>、<code>1</code> 或 <code>2</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<ul> 
// <li>你可以不使用代码库中的排序函数来解决这道题吗？</li> 
// <li>你能想出一个仅使用常数空间的一趟扫描算法吗？</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 1421</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort.problems;

import java.util.Arrays;

//75.颜色分类
//开题时间：2022-09-30 14:43:54
public class SortColors {
    public static void main(String[] args) {
        Solution solution = new SortColors().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //计数排序
        public void sortColors(int[] nums) {
            int cnt0 = 0;
            int cnt1 = 0;
            for (int num : nums)
                if (num == 0)
                    cnt0++;
                else if (num == 1)
                    cnt1++;

            int cnt01 = cnt0 + cnt1;
            Arrays.fill(nums, 0, cnt0, 0);
            Arrays.fill(nums, cnt0, cnt01, 1);
            Arrays.fill(nums, cnt01, nums.length, 2);
        }

        //双指针
        public void sortColors2(int[] nums) {
            /*
             *  all in [0,start) = 0
             *  all in [start,end] = 1
             *  all in (end,length-1] = 2
             */
            int start = 0;
            int end = nums.length - 1;
            for (int i = 0; i <= end; ) {
                if (nums[i] == 0)
                    swap(nums, i++, start++);
                else if (nums[i] == 1)
                    i++;
                else
                    swap(nums, i, end--);
            }
        }

        //双指针2
        public void sortColors3(int[] nums) {
            /*
             *  all in [0,start] = 0
             *  all in (start,end) = 1
             *  all in [end,length-1] = 2
             */
            int start = -1;
            int end = nums.length;
            for (int i = 0; i < end; ) {
                if (nums[i] == 0)
                    swap(nums, i++, ++start);
                else if (nums[i] == 1)
                    i++;
                else
                    swap(nums, i, --end);
            }
        }

        public static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}