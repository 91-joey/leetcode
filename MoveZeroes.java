//<p>给定一个数组 <code>nums</code>，编写一个函数将所有 <code>0</code> 移动到数组的末尾，同时保持非零元素的相对顺序。</p>
//
//<p><strong>请注意</strong>&nbsp;，必须在不复制数组的情况下原地对数组进行操作。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = <span><code>[0,1,0,3,12]</code></span>
//<strong>输出:</strong> <span><code>[1,3,12,0,0]</code></span>
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = <span><code>[0]</code></span>
//<strong>输出:</strong> <span><code>[0]</code></span></pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示</strong>:</p> 
//<meta charset="UTF-8" />
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>-2<sup>31</sup>&nbsp;&lt;= nums[i] &lt;= 2<sup>31</sup>&nbsp;- 1</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><b>进阶：</b>你能尽量减少完成的操作次数吗？</p>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 1740</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems.algorithm.sort.Swap;

//283.移动零
//开题时间：2022-09-19 14:28:58
public class MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //冒泡排序
        public void moveZeroes(int[] nums) {
            int endIdx = nums.length - 1;
            int lstSwappedIdx = -1;
            boolean swapped = true;
            while (swapped) {
                swapped = false;
                for (int i = 0; i < endIdx; i++) {
                    if (nums[i] == 0) {
                        Swap.swap(nums, i, i + 1);
                        swapped = true;
                        lstSwappedIdx = i;
                    }
                }
                endIdx = lstSwappedIdx;
            }
        }

        //快慢指针
        public void moveZeroes2(int[] nums) {
            for (int slow = 0, fast = 0; fast < nums.length; fast++)
                if (nums[fast] != 0) {
//                    swap(nums, slow++, fast);
                    //快慢指针索引值相同时，不交换
                    if (fast > slow) {
                        nums[slow] = nums[fast];
                        nums[fast] = 0;
                    }
                    slow++;
                }
        }

        //补零法
        public void moveZeroes3(int[] nums) {
            int idx = 0;
            int length = nums.length;
            //非零值覆盖
            for (int i = 0; i < length; i++)
                if (nums[i] != 0)
                    nums[idx++] = nums[i];
            //补零
            for (int i = idx; i < length; i++)
                nums[i] = 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}