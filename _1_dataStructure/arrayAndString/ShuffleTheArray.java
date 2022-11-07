//<p>给你一个数组 <code>nums</code> ，数组中有 <code>2n</code> 个元素，按 <code>[x<sub>1</sub>,x<sub>2</sub>,...,x<sub>n</sub>,y<sub>1</sub>,y<sub>2</sub>,...,y<sub>n</sub>]</code> 的格式排列。</p>
//
//<p>请你将数组按 <code>[x<sub>1</sub>,y<sub>1</sub>,x<sub>2</sub>,y<sub>2</sub>,...,x<sub>n</sub>,y<sub>n</sub>]</code> 格式重新排列，返回重排后的数组。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>nums = [2,5,1,3,4,7], n = 3
//<strong>输出：</strong>[2,3,5,4,1,7] 
//<strong>解释：</strong>由于 x<sub>1</sub>=2, x<sub>2</sub>=5, x<sub>3</sub>=1, y<sub>1</sub>=3, y<sub>2</sub>=4, y<sub>3</sub>=7 ，所以答案为 [2,3,5,4,1,7]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>nums = [1,2,3,4,4,3,2,1], n = 4
//<strong>输出：</strong>[1,4,2,3,3,2,4,1]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>nums = [1,1,2,2], n = 2
//<strong>输出：</strong>[1,2,1,2]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 500</code></li> 
// <li><code>nums.length == 2n</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10^3</code></li> 
//</ul>
//
//<div><li>👍 150</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.Arrays;

//1470.重新排列数组
//开题时间：2022-11-07 09:07:24
public class ShuffleTheArray {
    public static void main(String[] args) {
        Solution solution = new ShuffleTheArray().new Solution();
//        System.out.println(solution.shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3));
        System.out.println(Arrays.toString(solution.shuffle(new int[]{2, 5, 1, 8, 3, 4, 7, 10}, 4)));
//        for (int j = 0; j < 6; j++) {
//            System.out.println(((j % 3) << 1) + (j < 3 ? 0 : 1));
//        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] shuffle9(int[] nums, int n) {
            int[] ans = new int[nums.length];

            for (int i = 0; i < ans.length; i++)
                ans[i] = nums[(i >> 1) + ((i & 1) == 0 ? 0 : n)];

            return ans;
        }

        public int[] shuffle8(int[] nums, int n) {
            int[] ans = new int[nums.length];

            for (int i = 0; i < n; i++)
                ans[i << 1] = nums[i];
            for (int i = n; i < nums.length; i++)
                ans[((i - n) << 1) + 1] = nums[i];

            return ans;
        }

        //☆☆☆☆☆ 高位存储  n   1
        public int[] shuffle7(int[] nums, int n) {
            for (int i = 0, mask = 1023; i < nums.length; i++)
                nums[i] |= (nums[(i >> 1) + ((i & 1) == 0 ? 0 : n)] & mask) << 10;
            for (int i = 0; i < nums.length; i++)
                nums[i] >>= 10;

            return nums;
        }

        //☆☆☆☆ GJ    n   n
        public int[] shuffle6(int[] nums, int n) {
            int[] ans = new int[nums.length];

            for (int i = 0; i < n; i++) {
                ans[i << 1] = nums[i];
                ans[(i << 1) + 1] = nums[i + n];
            }

            return ans;
        }

        //☆☆☆☆☆ 置换、取反 n   1
        public int[] shuffle5(int[] nums, int n) {
            for (int i = 0; i < nums.length; i++)
                if (nums[i] > 0)
                    for (int j = i; nums[i] > 0; ) {
                        j = ((j % n) << 1) + (j < n ? 0 : 1);
                        swap(nums, i, j);
                        nums[j] = ~nums[j];
                    }

            for (int i = 0; i < nums.length; i++)
                nums[i] = ~nums[i];

            return nums;
        }

        //置换、取反（优化） n   1
        public int[] shuffle4(int[] nums, int n) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0)
                    for (int j = i; nums[i] > 0; ) {
                        j = ((j % n) << 1) + (j < n ? 0 : 1);
                        swap(nums, i, j);
                        nums[j] = ~nums[j];
                    }
                nums[i] = ~nums[i];
            }
            return nums;
        }

        //☆☆☆☆☆ 置换、取反（再优化） n   1
        public int[] shuffle(int[] nums, int n) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    int j = i;
                    int tmp = ~nums[i];
                    do {
                        nums[j] = ~nums[(j = (j >> 1) + ((j & 1) == 0 ? 0 : n))];
                    } while (j != i);
                    nums[((i % n) << 1) + (i < n ? 0 : 1)] = tmp;
                }
                nums[i] = ~nums[i];
            }
            return nums;
        }

        public void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}