//<p>给你一个整数数组 <code>nums</code>，有一个大小为&nbsp;<code>k</code><em>&nbsp;</em>的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 <code>k</code>&nbsp;个数字。滑动窗口每次只向右移动一位。</p>
//
//<p>返回 <em>滑动窗口中的最大值 </em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [1,3,-1,-3,5,3,6,7], k = 3
//<b>输出：</b>[3,3,5,5,6,7]
//<b>解释：</b>
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       <strong>3</strong>
// 1 [3  -1  -3] 5  3  6  7       <strong>3</strong>
// 1  3 [-1  -3  5] 3  6  7      <strong> 5</strong>
// 1  3  -1 [-3  5  3] 6  7       <strong>5</strong>
// 1  3  -1  -3 [5  3  6] 7       <strong>6</strong>
// 1  3  -1  -3  5 [3  6  7]      <strong>7</strong>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [1], k = 1
//<b>输出：</b>[1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><b>提示：</b></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= k &lt;= nums.length</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>队列</li><li>数组</li><li>滑动窗口</li><li>单调队列</li><li>堆（优先队列）</li></div></div><br><div><li>👍 1913</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

//239.滑动窗口最大值
//开题时间：2022-10-15 12:35:45
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //堆（优先队列）（不当使用：remove()，超时）
        public int[] maxSlidingWindow(int[] nums, int k) {
            int len = nums.length;
            int[] maxes = new int[len - k + 1];
            PriorityQueue<Integer> heap = new PriorityQueue<>(k, Comparator.reverseOrder());
            for (int i = 0; i < k; i++)
                heap.offer(nums[i]);

            for (int i = k; i < len; i++) {
                int j = i - k;
                maxes[j] = heap.peek();
                heap.remove(nums[j]);
                heap.offer(nums[i]);
            }
            maxes[maxes.length - 1] = heap.peek();

            return maxes;
        }

        //堆（优先队列）（正确使用：存放值、索引对，使用poll()方法）
        public int[] maxSlidingWindow2(int[] nums, int k) {
            int len = nums.length;
            int[] maxes = new int[len - k + 1];
            //值最大（值相同时，索引最大）的元素，在堆顶
            PriorityQueue<int[]> heap = new PriorityQueue<>(k, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0]);
            for (int i = 0; i < k; i++)
                heap.offer(new int[]{nums[i], i});

            for (int i = k; i < len; i++) {
                int j = i - k;
                maxes[j] = heap.peek()[0];
                heap.offer(new int[]{nums[i], i});
                while (heap.peek()[1] <= j)
                    heap.poll();
            }
            maxes[maxes.length - 1] = heap.peek()[0];

            return maxes;
        }

        //单调队列（递减、双端）
        public int[] maxSlidingWindow3(int[] nums, int k) {
            int len = nums.length;
            int[] maxes = new int[len - k + 1];
            LinkedList<Integer> deque = new LinkedList<>();

            for (int i = 0; i < k; i++) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()])
                    deque.pollLast();
                deque.offer(i);
            }

            for (int i = k; i < len; i++) {
                int j = i - k;
                maxes[j] = nums[deque.peekFirst()];
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()])
                    deque.pollLast();
                deque.offer(i);
                if (deque.peekFirst() == j)
                    deque.pollFirst();
            }
            maxes[maxes.length - 1] = nums[deque.peekFirst()];

            return maxes;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}