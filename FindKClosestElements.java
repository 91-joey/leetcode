//<p>给定一个 <strong>排序好</strong> 的数组&nbsp;<code>arr</code> ，两个整数 <code>k</code> 和 <code>x</code> ，从数组中找到最靠近 <code>x</code>（两数之差最小）的 <code>k</code> 个数。返回的结果必须要是按升序排好的。</p>
//
//<p>整数 <code>a</code> 比整数 <code>b</code> 更接近 <code>x</code> 需要满足：</p>
//
//<ul> 
// <li><code>|a - x| &lt; |b - x|</code> 或者</li> 
// <li><code>|a - x| == |b - x|</code> 且 <code>a &lt; b</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [1,2,3,4,5], k = 4, x = 3
//<strong>输出：</strong>[1,2,3,4]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [1,2,3,4,5], k = 4, x = -1
//<strong>输出：</strong>[1,2,3,4]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= k &lt;= arr.length</code></li> 
// <li><code>1 &lt;= arr.length&nbsp;&lt;= 10<sup>4</sup></code>
//  <meta charset="UTF-8" /></li> 
// <li><code>arr</code>&nbsp;按 <strong>升序</strong> 排列</li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= arr[i], x &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>二分查找</li><li>排序</li><li>滑动窗口</li><li>堆（优先队列）</li></div></div><br><div><li>👍 455</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.*;

//658.找到 K 个最接近的元素
//开题时间：2022-10-31 12:05:59
public class FindKClosestElements {
    public static void main(String[] args) {
        Solution solution = new FindKClosestElements().new Solution();
//        System.out.println(solution.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));
        System.out.println(solution.findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 1, 9));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int len = arr.length;
            int l = 0, r = len - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (x <= arr[mid])
                    r = mid;
                else
                    l = mid + 1;
            }
            /*
             * l=0 [l,l]
             *  [l-1,l-1] or [l,l]
             */

            for (int i = k; i > 0 && l >= 0 || r < len; i--) {
                if (x - arr[l - 1] <= arr[r] - x)
                    l--;
                else
                    r++;
            }
            if (l < 0) {
                l = 0;
                r = k;
            } else if (r >= len)
                l = len - k;

            ArrayList<Integer> ans = new ArrayList<>();
            for (; l <= r; l++)
                ans.add(arr[l]);
            return ans;
//            return Arrays.stream(arr)
//                    .limit(r)
//                    .skip(l)
//                    .boxed()
//                    .toList();
        }

        //双重排序  nlogn+klogk
        public List<Integer> findClosestElements2(int[] arr, int k, int x) {
            ArrayList<int[]> list = new ArrayList<>();
            for (int i = 0; i < arr.length; i++)
                list.add(new int[]{i, arr[i]});
            list.sort(Comparator.comparingInt(o -> Math.abs(x - o[1])));

            ArrayList<Integer> ans = new ArrayList<>();
            for (int[] idx2val : list.subList(0, k))
                ans.add(idx2val[1]);
            Collections.sort(ans);

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}