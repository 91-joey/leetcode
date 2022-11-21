//<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，表示由范围 <code>[0, n - 1]</code> 内所有整数组成的一个排列。</p>
//
//<p><strong>全局倒置</strong> 的数目等于满足下述条件不同下标对 <code>(i, j)</code> 的数目：</p>
//
//<ul> 
// <li><code>0 &lt;= i &lt; j &lt; n</code></li> 
// <li><code>nums[i] &gt; nums[j]</code></li> 
//</ul>
//
//<p><strong>局部倒置</strong> 的数目等于满足下述条件的下标 <code>i</code> 的数目：</p>
//
//<ul> 
// <li><code>0 &lt;= i &lt; n - 1</code></li> 
// <li><code>nums[i] &gt; nums[i + 1]</code></li> 
//</ul>
//
//<p>当数组 <code>nums</code> 中 <strong>全局倒置</strong> 的数量等于 <strong>局部倒置</strong> 的数量时，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,0,2]
//<strong>输出：</strong>true
//<strong>解释：</strong>有 1 个全局倒置，和 1 个局部倒置。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,0]
//<strong>输出：</strong>false
//<strong>解释：</strong>有 2 个全局倒置，和 1 个局部倒置。
//</pre>
//
//&nbsp;
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == nums.length</code></li> 
// <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt; n</code></li> 
// <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li> 
// <li><code>nums</code> 是范围 <code>[0, n - 1]</code> 内所有数字组成的一个排列</li> 
//</ul>
//
//<div><li>👍 124</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

//775.全局倒置与局部倒置
//开题时间：2022-11-16 10:23:52
public class GlobalAndLocalInversions {
    public static void main(String[] args) {
        Solution solution = new GlobalAndLocalInversions().new Solution();
        System.out.println(solution.isIdealPermutation(new int[]{1, 0, 2}));
//        System.out.println(solution.isIdealPermutation(new int[]{0, 1, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int inversionsGlobal;

        //基于归并排序：判断逆序对数==局部倒置数
        public boolean isIdealPermutation9(int[] nums) {
            int inversionsLocal = 0;
            for (int i = 0; i < nums.length - 1; )
                if (nums[i] > nums[++i])
                    inversionsLocal++;

            mergeSort(nums, 0, nums.length - 1, new int[nums.length]);

            return inversionsGlobal == inversionsLocal;
        }

        private void mergeSort(int[] nums, int start, int end, int[] tmp) {
            if (start == end)
                return;

            int mid = start + end >> 1;
            mergeSort(nums, start, mid, tmp);
            mergeSort(nums, mid + 1, end, tmp);

            System.arraycopy(nums, start, tmp, start, end - start + 1);
            for (int i = start, l = start, r = mid + 1; i <= end; i++) {
                if (l > mid)
                    nums[i] = tmp[r++];
                else if (r > end || tmp[l] <= tmp[r]) {
                    nums[i] = tmp[l++];
                    inversionsGlobal += r - 1 - mid;
                } else
                    nums[i] = tmp[r++];
            }
        }

        //基于归并排序（优化）：判断是否有逆序对的索引差值 > 1
        public boolean isIdealPermutation8(int[] nums) {
            int len = nums.length;
            int[] indices = new int[len];
            for (int i = 0; i < len; i++)
                indices[i] = i;

            return mergeSort(nums, indices, 0, len - 1, new int[len]);
        }

        private boolean mergeSort(int[] nums, int[] indices, int start, int end, int[] tmp) {
            if (start >= end)
                return true;

            int mid = start + end >> 1;
            if (mergeSort(nums, indices, start, mid, tmp) &&
                    mergeSort(nums, indices, mid + 1, end, tmp)) {
                System.arraycopy(indices, start, tmp, start, end - start + 1);
                for (int i = start, l = start, r = mid + 1; i <= end; i++) {
                    if (l > mid)
                        indices[i] = tmp[r++];
                    else if (r > end || nums[tmp[l]] <= nums[tmp[r]]) {
                        if ((r - 1 - mid > 0 && tmp[r - 1] != tmp[l] + 1) ||
                                (r - 1 - mid > 1 && tmp[r - 2] != tmp[l] + 1))
                            return false;
                        indices[i] = tmp[l++];
                    } else
                        indices[i] = tmp[r++];
                }
                return true;
            }

            return false;
        }

        //维护后缀最小值（两次遍历） n*2 n
        public boolean isIdealPermutation7(int[] nums) {
            int len = nums.length;
            int[] mins = new int[len];
            mins[len - 1] = nums[len - 1];
            for (int i = len - 2; i >= 2; i--)
                mins[i] = Math.min(mins[i + 1], nums[i]);

            for (int i = 0; i < len - 2; i++)
                if (nums[i] > mins[i + 2])
                    return false;

            return true;
        }


        //☆☆☆☆☆ 维护后缀最小值（一次遍历） n   1
        public boolean isIdealPermutation6(int[] nums) {
            int len = nums.length;
            for (int i = len - 3, minSuff = nums[len - 1]; i >= 0; i--) {
                if (nums[i] > minSuff)
                    return false;
                minSuff = Math.min(minSuff, nums[i + 1]);
            }
            return true;
        }

        //☆☆☆☆ 归纳证明
        public boolean isIdealPermutation(int[] nums) {
            for (int i = 0; i < nums.length; i++)
                if (Math.abs(nums[i] - i) > 1)
                    return false;
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}