//<p>给你一个整数数组 <code>nums</code><em> </em>，按要求返回一个新数组&nbsp;<code>counts</code><em> </em>。数组 <code>counts</code> 有该性质： <code>counts[i]</code> 的值是&nbsp; <code>nums[i]</code> 右侧小于&nbsp;<code>nums[i]</code> 的元素的数量。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [5,2,6,1]
//<strong>输出：</strong><span><code>[2,1,1,0] 
//<strong>解释：</strong></code></span>
//5 的右侧有 <strong>2 </strong>个更小的元素 (2 和 1)
//2 的右侧仅有 <strong>1 </strong>个更小的元素 (1)
//6 的右侧有 <strong>1 </strong>个更小的元素 (1)
//1 的右侧有 <strong>0 </strong>个更小的元素
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1]
//<strong>输出：</strong>[0]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1,-1]
//<strong>输出：</strong>[0,0]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 911</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//315.计算右侧小于当前元素的个数
//开题时间：2022-11-10 18:24:19
public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        Solution solution = new CountOfSmallerNumbersAfterSelf().new Solution();
        System.out.println(solution.countSmaller(new int[]{5, 2, 6, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力 TLE
        public List<Integer> countSmaller9(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int cnt = 0;
                for (int j = i + 1; j < nums.length; j++)
                    if (nums[i] > nums[j])
                        cnt++;
                nums[i] = cnt;
            }
            return Arrays.stream(nums).boxed().toList();
        }

        //暴力2 TLE
        public List<Integer> countSmaller8(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int cnt = 0;
                for (int j = i + 1; j < nums.length; j++)
                    if (nums[i] > nums[j])
                        cnt++;
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                    nums[i] = cnt;
                    i++;
                }
                nums[i] = cnt;
            }

            ArrayList<Integer> ans = new ArrayList<>(nums.length);
            for (int num : nums)
                ans.add(num);
            return ans;
        }

        int[] cnts;
        int[] tmpNums;
        int[] indices;
        int[] tmpIndices;

        //归并排序（排序元素和索引）
        public List<Integer> countSmaller7(int[] nums) {
            int len = nums.length;
            cnts = new int[len];
            tmpNums = new int[len];
            indices = new int[len];
            tmpIndices = new int[len];
            for (int i = 0; i < len; i++)
                indices[i] = i;

            mergeSort(nums, 0, len - 1);
            return Arrays.stream(cnts).boxed().toList();
        }

        private void mergeSort(int[] arr, int start, int end) {
            if (start == end)
                return;

            int mid = start + end >> 1;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);

            System.arraycopy(arr, start, tmpNums, start, end - start + 1);
            System.arraycopy(indices, start, tmpIndices, start, end - start + 1);
            for (int i = start, l = start, r = mid + 1; i <= end; i++) {
                if (l > mid) {
                    arr[i] = tmpNums[r];
                    indices[i] = tmpIndices[r++];
                } else if (r > end || tmpNums[l] <= tmpNums[r]) {
                    arr[i] = tmpNums[l];
                    indices[i] = tmpIndices[l++];
                    cnts[indices[i]] += r - mid - 1;
                } else {
                    arr[i] = tmpNums[r];
                    indices[i] = tmpIndices[r++];
                }
            }
        }

        //☆☆☆☆☆ 归并排序（只排序索引）

        public List<Integer> countSmaller(int[] nums) {
            int len = nums.length;
            cnts = new int[len];
            indices = new int[len];
            tmpIndices = new int[len];
            for (int i = 0; i < len; i++)
                indices[i] = i;

            mergeSort2(nums, 0, len - 1);
            return Arrays.stream(cnts).boxed().toList();
        }

        private void mergeSort2(int[] arr, int start, int end) {
            if (start == end)
                return;

            int mid = start + end >> 1;
            mergeSort2(arr, start, mid);
            mergeSort2(arr, mid + 1, end);

            System.arraycopy(indices, start, tmpIndices, start, end - start + 1);
            for (int i = start, l = start, r = mid + 1; i <= end; i++) {
                if (l > mid) {
                    indices[i] = tmpIndices[r++];
                } else if (r > end || arr[tmpIndices[l]] <= arr[tmpIndices[r]]) {
                    indices[i] = tmpIndices[l++];
                    cnts[indices[i]] += r - mid - 1;
                } else {
                    indices[i] = tmpIndices[r++];
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}