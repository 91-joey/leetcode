//<p>给定整数数组 <code>nums</code> 和整数 <code>k</code>，请返回数组中第 <code><strong>k</strong></code> 个最大的元素。</p>
//
//<p>请注意，你需要找的是数组排序后的第 <code>k</code> 个最大的元素，而不是第 <code>k</code> 个不同的元素。</p>
//
//<p>你必须设计并实现时间复杂度为 <code>O(n)</code> 的算法解决此问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>[3,2,1,5,6,4],</code></span> k = 2
//<strong>输出:</strong> 5
//</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>[3,2,3,1,2,4,5,5,6], </code></span>k = 4
//<strong>输出:</strong> 4</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示： </strong></p>
//
//<ul> 
// <li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 1881</li><li>👎 0</li></div>
package org.example.leetcode.problems;


import org.example.leetcode.problems.algorithm.sort.Swap;

import java.util.Arrays;

//215.数组中的第K个最大元素
//开题时间：2022-09-20 11:09:28
public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        Solution solution = new KthLargestElementInAnArray().new Solution();
        System.out.println(solution.findKthLargest_selectionSort(Swap.arr, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            Arrays.parallelSort(nums);
            return nums[nums.length - k];
        }

        public int findKthLargest_selectionSort(int[] nums, int k) {
            int length = nums.length;
            //若 k 小于数组长度一半，排 k 个最大值
            if (k < length / 2) {
                for (int i = 0; i < k; i++) {
                    int maxIdx = i;
                    for (int j = i + 1; j < length; j++)
                        if (nums[maxIdx] < nums[j]) maxIdx = j;
                    Swap.swap(nums, i, maxIdx);
                }
                return nums[k - 1];
            //若 k 大于数组长度一半，排 length + 1 - k 个最小值
            } else {
                for (int i = 0; i <= length - k; i++) {
                    int minIdx = i;
                    for (int j = i + 1; j < length; j++)
                        if (nums[minIdx] > nums[j]) minIdx = j;
                    Swap.swap(nums, i, minIdx);
                }
                return nums[length - k];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}