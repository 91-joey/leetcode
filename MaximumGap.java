//<p>给定一个无序的数组&nbsp;<code>nums</code>，返回 <em>数组在排序之后，相邻元素之间最大的差值</em> 。如果数组元素个数小于 2，则返回 <code>0</code> 。</p>
//
//<p>您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [3,6,9,1]
//<strong>输出:</strong> 3
//<strong>解释:</strong> 排序后的数组是 [1,3,6,9]<strong><em>, </em></strong>其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [10]
//<strong>输出:</strong> 0
//<strong>解释:</strong> 数组元素个数小于 2，因此返回 0。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>桶排序</li><li>基数排序</li><li>排序</li></div></div><br><div><li>👍 518</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//164.最大间距
//开题时间：2022-09-28 14:51:08
public class MaximumGap {
    public static void main(String[] args) {
        Solution solution = new MaximumGap().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //基数排序  o(d(n+k))   n+k
        public int maximumGap(int[] nums) {
            int length = nums.length;
            if (length < 2) return 0;

            radixSort_LSD(nums);

            int max = nums[1] - nums[0];
            for (int i = 2; i < length; i++) {
                int diff = nums[i] - nums[i - 1];
                if (max < diff) max = diff;
            }

            return max;
        }

        public static void radixSort_LSD(int[] arr) {
            int maxDigitLength = getMaxDigitLength(arr);

            int[] counting = new int[10];
            int length = arr.length;
            int[] sorted = new int[length];
            //从低位到高位（LSD: Least Significant Digit）基于「计数排序」依次对数组排序
            for (int i = 0, cur = 1; i < maxDigitLength; i++, cur *= 10) {
                //计数
                for (int e : arr) {
                    int digit = e / cur % 10;
                    counting[digit]++;
                }

                //计算最高索引
                counting[0]--;
                for (int j = 1; j < counting.length; j++)
                    counting[j] += counting[j - 1];

                //倒序遍历排序
                for (int j = length - 1; j >= 0; j--) {
                    int digit = arr[j] / cur % 10;
                    sorted[counting[digit]--] = arr[j];
                }

                //拷贝数组
                System.arraycopy(sorted, 0, arr, 0, length);
                //将计数数组重置为 0
                Arrays.fill(counting, 0);
            }
        }

        private static int getMaxDigitLength(int[] arr) {
            //求最大值
            int max = 0;
            for (int e : arr) {
                int abs = Math.abs(e);
                if (max < abs) max = abs;
            }

            //求最大位数（根据最大值）
            int maxDigitLength = 0;
            while (max != 0) {
                max /= 10;
                maxDigitLength++;
            }
            return maxDigitLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}