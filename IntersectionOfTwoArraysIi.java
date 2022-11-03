//<p>给你两个整数数组&nbsp;<code>nums1</code> 和 <code>nums2</code> ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [1,2,2,1], nums2 = [2,2]
//<strong>输出：</strong>[2,2]
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//<strong>输出：</strong>[4,9]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong><strong>进阶</strong>：</strong></p>
//
//<ul> 
// <li>如果给定的数组已经排好序呢？你将如何优化你的算法？</li> 
// <li>如果&nbsp;<code>nums1</code><em>&nbsp;</em>的大小比&nbsp;<code>nums2</code> 小，哪种方法更优？</li> 
// <li>如果&nbsp;<code>nums2</code><em>&nbsp;</em>的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>双指针</li><li>二分查找</li><li>排序</li></div></div><br><div><li>👍 855</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//350.两个数组的交集 II
//开题时间：2022-11-03 14:59:54
public class IntersectionOfTwoArraysIi {
    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoArraysIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //哈希映射  m+n m
        public int[] intersect(int[] nums1, int[] nums2) {
            HashMap<Integer, Integer> num2cnt = new HashMap<>();
            for (int e : nums1)
                num2cnt.merge(e, 1, Integer::sum);

//            Map<Integer, Integer> num2cnt = Arrays.stream(nums1).boxed().collect(Collectors.toMap(i -> i, i -> 1, Integer::sum));

            return Arrays.stream(nums2).filter(num -> {
                if (!num2cnt.containsKey(num)) {
                    return false;
                } else {
                    num2cnt.merge(num, -1, Integer::sum);
                    num2cnt.remove(num, 0);
//                    int cnt = num2cnt.get(num);
//                    if (cnt == 1)
//                        num2cnt.remove(num);
//                    else
//                        num2cnt.put(num, cnt - 1);
                    return true;
                }
            }).toArray();
        }

        //排序+双指针    mlogm+nlogn 1
        public int[] intersect2(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);

            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
                if (nums1[i] == nums2[j]) {
                    ans.add(nums1[i]);
                    i++;
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            return ans.stream().mapToInt(Integer::intValue).toArray();
        }

        //排序+双指针(结果存储在输入数组中)    mlogm+nlogn 1
        public int[] intersect3(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);

            int idx = 0;
            for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
                if (nums1[i] == nums2[j]) {
                    nums1[idx++] = nums1[i];
                    i++;
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            return Arrays.copyOfRange(nums1, 0, idx);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}