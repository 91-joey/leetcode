//<p>把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。</p>
//
//<p>给你一个可能存在&nbsp;<strong>重复</strong>&nbsp;元素值的数组&nbsp;<code>numbers</code>&nbsp;，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的<strong>最小元素</strong>。例如，数组&nbsp;<code>[3,4,5,1,2]</code> 为 <code>[1,2,3,4,5]</code> 的一次旋转，该数组的最小值为 1。&nbsp;&nbsp;</p>
//
//<p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> 旋转一次 的结果为数组 <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong><span><code>numbers = </code></span>[3,4,5,1,2]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong><span><code>numbers = </code></span>[2,2,2,0,1]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == numbers.length</code></li> 
// <li><code>1 &lt;= n &lt;= 5000</code></li> 
// <li><code>-5000 &lt;= numbers[i] &lt;= 5000</code></li> 
// <li><code>numbers</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li> 
//</ul>
//
//<p>注意：本题与主站 154 题相同：<a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/">https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/</a></p>
//
//<div><li>👍 734</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

//剑指 Offer 11.旋转数组的最小数字
//开题时间：2022-12-30 09:31:36
public class XuanZhuanShuZuDeZuiXiaoShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new XuanZhuanShuZuDeZuiXiaoShuZiLcof().new Solution();
        System.out.println(solution.minArray(new int[]{1, 3, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minArray(int[] numbers) {
            int l = 0, r = numbers.length - 1;
            while (l < r) {
                int mid = ((r - l) >> 1) + l;
                if (numbers[mid] < numbers[r])
                    r = mid;
                else if (numbers[mid] > numbers[r])
                    l = mid + 1;
                //无法二分，转为线性
                else
                    r--;
            }
            return numbers[r];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}