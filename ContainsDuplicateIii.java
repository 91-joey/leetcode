//<p>给你一个整数数组 <code>nums</code> 和两个整数&nbsp;<code>k</code> 和 <code>t</code> 。请你判断是否存在 <b>两个不同下标</b> <code>i</code> 和 <code>j</code>，使得&nbsp;<code>abs(nums[i] - nums[j]) &lt;= t</code> ，同时又满足 <code>abs(i - j) &lt;= k</code><em> </em>。</p>
//
//<p>如果存在则返回 <code>true</code>，不存在返回 <code>false</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,1], k<em> </em>= 3, t = 0
//<strong>输出：</strong>true</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,0,1,1], k<em> </em>=<em> </em>1, t = 2
//<strong>输出：</strong>true</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,5,9,1,5,9], k = 2, t = 3
//<strong>输出：</strong>false</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li> 
// <li><code>0 &lt;= k &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= t &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>桶排序</li><li>有序集合</li><li>排序</li><li>滑动窗口</li></div></div><br><div><li>👍 657</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//220.存在重复元素 III
//开题时间：2022-10-16 17:49:52
public class ContainsDuplicateIii {
    public static void main(String[] args) {
        Solution solution = new ContainsDuplicateIii().new Solution();
        System.out.println(solution.containsNearbyAlmostDuplicate2(new int[]{0, 10, 22, 15, 0, 5, 22, 12, 1, 5}, 3, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力    nk
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            int len = nums.length;
            if (k == 0 || len < 2)
                return false;

            int bound = Math.min(len - 1, k);
            for (int r = 1; r <= bound; r++)
                for (int l = r - 1; l >= 0; l--)
                    if (Math.abs(nums[r] - nums[l]) <= t)
                        return true;

            for (int r = k + 1; r < len; r++)
                for (int l = r - 1; l >= r - k; l--)
                    if (Math.abs(nums[r] - nums[l]) <= t)
                        return true;

            return false;
        }

        public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
            int len = nums.length;
            if (k == 0 || len < 2)
                return false;

            int size = Math.min(len, k + 1);
            int[] win = Arrays.copyOfRange(nums, 0, size);
            Arrays.sort(win);
            for (int i = 1; i < size; i++)
                if (win[i] - win[i - 1] <= t)
                    return true;

            for (int diff = k + 1, r = diff; r < len; r++) {
                int delNum = nums[r - diff];
                int addIdx = Arrays.binarySearch(win, nums[r]);
                if (addIdx < 0) {
                    addIdx = -addIdx - 1;
                } else if (delNum != nums[r])
                    return true;

                int delIdx = Arrays.binarySearch(win, delNum);
                if (delIdx < addIdx) {
                    addIdx--;
                    System.arraycopy(win, delIdx + 1, win, delIdx, addIdx - delIdx);
                } else
                    System.arraycopy(win, addIdx, win, addIdx + 1, delIdx - addIdx);
                if ((addIdx > 0 && nums[r] - win[addIdx - 1] <= t) ||
                        (addIdx < size - 1 && win[addIdx + 1] - nums[r] <= t))
                    return true;
                win[addIdx] = nums[r];
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}