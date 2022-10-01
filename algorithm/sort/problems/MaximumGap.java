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
package org.example.leetcode.problems.algorithm.sort.problems;

import java.util.Arrays;

//164.最大间距
//开题时间：2022-09-28 14:51:08
public class MaximumGap {
    public static void main(String[] args) {
        Solution solution = new MaximumGap().new Solution();
        System.out.println(solution.maximumGap_bucket2(new int[]{1, 3, 100}));
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

        /*
         * 基于桶的算法
         * 恒成立： maximum gap >=(max -min)/(length -1)
         * 可以推导出：元素之间的最大间距一定不会出现在某个桶的内部，而一定会出现在不同桶当中。
         *      故，maximum gap = max(min(buckets[i + 1]) - max(buckets[i]))
         */
        public int maximumGap_bucket(int[] nums) {
            int length = nums.length;
            if (length < 2) return 0;

            //获取值区间
            int min = nums[0];
            int max = nums[0];
            for (int e : nums) {
                if (e < min) min = e;
                else if (max < e) max = e;
            }

            double gap = 1.0 * (max - min) / (length - 1);
            int[][] buckets = new int[length][2];
            int dft = -1;
            for (int i = 0; i < length; i++)
                Arrays.fill(buckets[i], dft);

            //落桶
            for (int e : nums) {
                int i = (int) ((e - min) / gap);
                if (buckets[i][0] == dft)
                    buckets[i][0] = buckets[i][1] = e;
                else {
                    if (e < buckets[i][0]) buckets[i][0] = e;
                    else if (buckets[i][1] < e) buckets[i][1] = e;
                }
            }

            int maxGap = 0;
            for (int i = 0, prev = -1; i < length; i++)
                if (buckets[i][0] != dft) {
                    if (prev != -1)
                        maxGap = Math.max(maxGap, buckets[i][0] - buckets[prev][1]);
                    prev = i;
                }

            return maxGap;
        }

        //基于桶的算法_优化
        public int maximumGap_bucket2(int[] nums) {
            int length = nums.length;
            if (length < 2) return 0;

            //获取值区间
            int min = nums[0];
            int max = nums[0];
            for (int e : nums) {
                if (e < min) min = e;
                else if (max < e) max = e;
            }

            //数组中均为相同元素，相邻元素间差值均为 0
            if (max == min)
                return 0;

            int gap = (int) Math.ceil((double) (max - min) / (length - 1));
            int bucketCnt = (max - min) / gap + 1;
            int[] bucketsMin = new int[bucketCnt];
            int[] bucketsMax = new int[bucketCnt];
            Arrays.fill(bucketsMin, Integer.MAX_VALUE);
            Arrays.fill(bucketsMax, Integer.MIN_VALUE);

            //落桶
            for (int e : nums) {
                int i = (e - min) / gap;
                bucketsMin[i] = Math.min(bucketsMin[i], e);
                bucketsMax[i] = Math.max(bucketsMax[i], e);
            }

            for (int i = 1, prevMax = bucketsMax[0]; i < bucketCnt; i++)
                if (bucketsMin[i] != Integer.MAX_VALUE) {
                    gap = Math.max(gap, bucketsMin[i] - prevMax);
                    prevMax = bucketsMax[i];
                }

            return gap;
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