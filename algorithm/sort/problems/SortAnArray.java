//<p>给你一个整数数组&nbsp;<code>nums</code>，请你将该数组升序排列。</p>
//
//<p>&nbsp;</p>
//
//<ol> 
//</ol>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [5,2,3,1]
//<strong>输出：</strong>[1,2,3,5]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [5,1,1,2,0,0]
//<strong>输出：</strong>[0,0,1,1,2,5]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>-5 * 10<sup>4</sup> &lt;= nums[i] &lt;= 5 * 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>桶排序</li><li>计数排序</li><li>基数排序</li><li>排序</li><li>堆（优先队列）</li><li>归并排序</li></div></div><br><div><li>👍 684</li><li>👎 0</li></div>
package org.example.leetcode.problems.algorithm.sort.problems;

import org.example.leetcode.problems.algorithm.sort.algorithm.Swap;

import java.util.Arrays;

//912.排序数组
//开题时间：2022-09-20 11:50:57
public class SortAnArray{
  public static void main(String[] args) {
       Solution solution = new SortAnArray().new Solution();
  }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] sortArray(int[] nums) {
        Arrays.parallelSort(nums);
        return nums;
    }

    public int[] sortArray2(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length / 2; i++) {
            int minIdx = i;
            int maxIdx = i;
            for (int j = i + 1; j < length - i; j++) {
                if (nums[minIdx] > nums[j]) minIdx = j;
                if (nums[maxIdx] < nums[j]) maxIdx = j;
            }
            // nums[i] 后面的所有数字都与 nums[i] 相等，排序完成
            if (minIdx == maxIdx) break;
            Swap.swap(nums, i, minIdx);
            //若最大值的下标刚好是 i ，由于 nums[i] 和 nums[minIndex] 已经交换了，所以这里要更新 maxIndex 的值。
            if (maxIdx == i) maxIdx = minIdx;
            Swap.swap(nums, length - 1 - i, maxIdx);
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}