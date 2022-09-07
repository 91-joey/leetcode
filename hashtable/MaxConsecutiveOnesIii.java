//<p>给定一个二进制数组&nbsp;<code>nums</code>&nbsp;和一个整数 <code>k</code>，如果可以翻转最多 <code>k</code> 个 <code>0</code> ，则返回 <em>数组中连续 <code>1</code> 的最大个数</em> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//<strong>输出：</strong>6
//<strong>解释：</strong>[1,1,1,0,0,<strong>1</strong>,1,1,1,1,<strong>1</strong>]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//<strong>输出：</strong>10
//<strong>解释：</strong>[0,0,1,1,<strong>1</strong>,<strong>1</strong>,1,1,1,<strong>1</strong>,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>nums[i]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code></li> 
// <li><code>0 &lt;= k &lt;= nums.length</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>前缀和</li><li>滑动窗口</li></div></div><br><div><li>👍 462</li><li>👎 0</li></div>
package org.example.leetcode.problems.hashtable;

//1004.最大连续1的个数 III
//开题时间：2022-09-07 10:30:56
public class MaxConsecutiveOnesIii {
    public static void main(String[] args) {
        Solution solution = new MaxConsecutiveOnesIii().new Solution();
        System.out.println(solution.longestOnes2(new int[]{1, 1, 1, 0, 0, 0, 0, 0, 0, 0}, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //自解:滑动窗口  n   1
        public int longestOnes(int[] nums, int k) {
            int length = nums.length;
            int cnt0 = 0;
            int max = 0;

            int l = 0;
            for (int r = 0; r < length; r++) {
                if (nums[r] == 0)
                    cnt0++;
                if (cnt0 > k) {
                    max = Math.max(max, r - l);
                    if (nums[l++] == 0) {
                        cnt0--;
                    }
                }
            }

            max = Math.max(max, length - l);
            return max;
        }

        //高分解:滑动窗口  n   1
        public int longestOnes2(int[] nums, int k) {
            int l = 0, r = 0;

            while (r < nums.length) {
                if (nums[r++] == 0) k--;
                if (k < 0 && nums[l++] == 0) k++;
            }

            return r - l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}