//<p>给定整数数组 <code>nums</code> 和整数 <code>k</code>，请返回数组中第 <code><strong>k</strong></code> 个最大的元素。</p>
//
//<p>请注意，你需要找的是数组排序后的第 <code>k</code> 个最大的元素，而不是第 <code>k</code> 个不同的元素。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>[3,2,1,5,6,4] 和</code></span> k = 2
//<strong>输出:</strong> 5
//</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>[3,2,3,1,2,4,5,5,6] 和</code></span> k = 4
//<strong>输出:</strong> 4</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示： </strong></p>
//
//<ul> 
// <li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p>
// <meta charset="UTF-8" />注意：本题与主站 215&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/kth-largest-element-in-an-array/">https://leetcode-cn.com/problems/kth-largest-element-in-an-array/</a></p>
//
//<div><li>👍 52</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import static org.example.leetcode.problems._2_algorithm.divideAndConquer.application.QuickSort.quickSort;

//剑指 Offer II 076.数组中的第 k 大的数字
//开题时间：2022-11-11 13:43:16
public class Xx4gT2 {
    public static void main(String[] args) {
        Solution solution = new Xx4gT2().new Solution();
        System.out.println(solution.findKthLargest(new int[]{5, 2, 4, 1, 3, 6, 0}, 2));
//        System.out.println(solution.findKthLargest(new int[]{2, 1}, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //基于堆排序 nlogn   logn
        public int findKthLargest9(int[] nums, int k) {
            return k < nums.length >> 1 ?
                    getKthLargest(nums, k) :
                    getKthSmallest(nums, nums.length - k + 1);
        }

        private Integer getKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> min = new PriorityQueue<>();
            for (int i = 0; i < k; i++)
                min.offer(nums[i]);

            for (int i = k; i < nums.length; i++) {
                if (nums[i] > min.peek()) {
                    min.poll();
                    min.offer(nums[i]);
                }
            }

            return min.peek();
        }

        private Integer getKthSmallest(int[] nums, int k) {
            PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 0; i < k; i++)
                min.offer(nums[i]);

            for (int i = k; i < nums.length; i++) {
                if (nums[i] < min.peek()) {
                    min.poll();
                    min.offer(nums[i]);
                }
            }

            return min.peek();
        }

        public static final Random rnd = new Random();

        //☆☆☆☆☆ 基于快排 n    logn
        /*
        * 分区后所得轴索引（pIdx）和目标排序后索引（idx = nums.length - k）比较
        *   pIdx == idx：找到目标元素，直接返回
        *   pIdx <  idx：在轴索引右区间继续分区
        *   pIdx >  idx：  ... 左 ...
        */
        public int findKthLargest(int[] nums, int k) {
            return quickSort(nums, 0, nums.length - 1, nums.length - k);
        }

        private int quickSort(int[] nums, int start, int end, int idx) {
            if (start >= end)
                return nums[end];
            int pIdx = partion(nums, start, end);
            if (pIdx == idx)
                return nums[pIdx];
            else if (pIdx < idx)
                return quickSort(nums, pIdx + 1, end, idx);
            else
                return quickSort(nums, start, pIdx - 1, idx);
        }

        private int partion(int[] nums, int start, int end) {
            int rndIdx = rnd.nextInt(start, end + 1);
            swap(nums, start, rndIdx);
            int l = start + 1, r = end;
            while (true) {
                while (l <= end && nums[l] < nums[start]) l++;
                while (r > start && nums[r] > nums[start]) r--;
                if (l >= r)
                    break;
                swap(nums, l++, r--);
            }
            swap(nums, start, r);
            return r;
        }

        public static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}