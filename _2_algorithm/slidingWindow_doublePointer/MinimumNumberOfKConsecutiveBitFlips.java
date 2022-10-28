//<p>给定一个二进制数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>
//
//<p><strong>k位翻转</strong> 就是从 <code>nums</code> 中选择一个长度为 <code>k</code> 的 <strong>子数组</strong> ，同时把子数组中的每一个 <code>0</code> 都改成 <code>1</code> ，把子数组中的每一个 <code>1</code> 都改成 <code>0</code> 。</p>
//
//<p>返回数组中不存在 <code>0</code> 所需的最小 <strong>k位翻转</strong> 次数。如果不可能，则返回&nbsp;<code>-1</code>&nbsp;。</p>
//
//<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,1,0], K = 1
//<strong>输出：</strong>2
//<strong>解释：</strong>先翻转 A[0]，然后翻转 A[2]。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,0], K = 2
//<strong>输出：</strong>-1
//<strong>解释：</strong>无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,0,0,1,0,1,1,0], K = 3
//<strong>输出：</strong>3
//<strong>解释：</strong>
//翻转 A[0],A[1],A[2]:&nbsp;A变成 [1,1,1,1,0,1,1,0]
//翻转 A[4],A[5],A[6]:&nbsp;A变成 [1,1,1,1,1,0,0,0]
//翻转 A[5],A[6],A[7]:&nbsp;A变成 [1,1,1,1,1,1,1,1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>1 &lt;= k &lt;= nums.length</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>位运算</li><li>队列</li><li>数组</li><li>前缀和</li><li>滑动窗口</li></div></div><br><div><li>👍 252</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.LinkedList;

//995.K 连续位的最小翻转次数
//开题时间：2022-10-10 09:02:21
public class MinimumNumberOfKConsecutiveBitFlips {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfKConsecutiveBitFlips().new Solution();
        System.out.println(solution.minKBitFlips(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //模拟翻转（超时）
        public int minKBitFlips(int[] nums, int k) {
            int cnt = 0;
            int len = nums.length;
            for (int i = 0; i <= len - k; i++)
                if (nums[i] == 0) {
                    for (int j = i; j < i + k; j++)
                        nums[j] = (nums[j] + 1) % 2;
                    cnt++;
                }

            for (int i = len - k + 1; i < len; i++)
                if (nums[i] == 0)
                    return -1;

            return cnt;
        }

        //队列模拟滑动窗口
        public int minKBitFlips2(int[] nums, int k) {
            int len = nums.length;
            LinkedList<Integer> q = new LinkedList<>();
            int cnt = 0;

            for (int i = 0; i < len; i++) {
                if (!q.isEmpty() && q.peek() + k <= i)
                    q.poll();

                if (q.size() % 2 == nums[i]) {
                    if (i > len - k)
                        return -1;
                    q.offer(i);
                    cnt++;
                }
            }

            return cnt;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}