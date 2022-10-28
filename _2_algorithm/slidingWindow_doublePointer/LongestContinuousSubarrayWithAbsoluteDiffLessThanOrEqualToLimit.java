//<p>给你一个整数数组 <code>nums</code> ，和一个表示限制的整数 <code>limit</code>，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 <code>limit</code><em> 。</em></p>
//
//<p>如果不存在满足条件的子数组，则返回 <code>0</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>nums = [8,2,4,7], limit = 4
//<strong>输出：</strong>2 
//<strong>解释：</strong>所有子数组如下：
//[8] 最大绝对差 |8-8| = 0 &lt;= 4.
//[8,2] 最大绝对差 |8-2| = 6 &gt; 4. 
//[8,2,4] 最大绝对差 |8-2| = 6 &gt; 4.
//[8,2,4,7] 最大绝对差 |8-2| = 6 &gt; 4.
//[2] 最大绝对差 |2-2| = 0 &lt;= 4.
//[2,4] 最大绝对差 |2-4| = 2 &lt;= 4.
//[2,4,7] 最大绝对差 |2-7| = 5 &gt; 4.
//[4] 最大绝对差 |4-4| = 0 &lt;= 4.
//[4,7] 最大绝对差 |4-7| = 3 &lt;= 4.
//[7] 最大绝对差 |7-7| = 0 &lt;= 4. 
//因此，满足题意的最长子数组的长度为 2 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>nums = [10,1,2,4,7,2], limit = 5
//<strong>输出：</strong>4 
//<strong>解释：</strong>满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 &lt;= 5 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>nums = [4,2,2,2,4,4,2,2], limit = 0
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10^5</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10^9</code></li> 
// <li><code>0 &lt;= limit &lt;= 10^9</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>队列</li><li>数组</li><li>有序集合</li><li>滑动窗口</li><li>单调队列</li><li>堆（优先队列）</li></div></div><br><div><li>👍 279</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.LinkedList;

//1438.绝对差不超过限制的最长连续子数组
//开题时间：2022-10-19 11:40:42
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public static void main(String[] args) {
        Solution solution = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution();
        System.out.println(solution.longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //双单调队列
        public int longestSubarray(int[] nums, int limit) {
            if (limit >= 1E9 - 1)
                return 0;

            LinkedList<Integer> qIncre = new LinkedList<>();
            LinkedList<Integer> qDecre = new LinkedList<>();
            qIncre.offer(nums[0]);
            qDecre.offer(nums[0]);
            int l = 0;
            int r = 1;
            while (r < nums.length) {
                int e = nums[r++];
                while (!qIncre.isEmpty() && qIncre.peekLast() > e) qIncre.pollLast();
                while (!qDecre.isEmpty() && qDecre.peekLast() < e) qDecre.pollLast();
                qIncre.offerLast(e);
                qDecre.offerLast(e);

                int min = qIncre.peekFirst();
                int max = qDecre.peekFirst();
                if (max - min > limit) {
                    int e2 = nums[l++];
                    if (min == e2) qIncre.pollFirst();
                    if (max == e2) qDecre.pollFirst();
                }
            }

            return r - l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}