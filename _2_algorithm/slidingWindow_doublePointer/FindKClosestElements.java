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
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//658.找到 K 个最接近的元素
//开题时间：2022-10-24 18:50:02
public class FindKClosestElements {
    public static void main(String[] args) {
        Solution solution = new FindKClosestElements().new Solution();
        System.out.println(solution.findClosestElements(new int[]{1, 1, 1, 10, 10, 10}, 1, 9));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            ArrayList<Integer> list = new ArrayList<>();
            int len = arr.length;
            if (x <= arr[0])
                for (int i = 0; i < k; i++)
                    list.add(arr[i]);
            else if (arr[len - 1] <= x)
                for (int i = len - k; i < len; i++)
                    list.add(arr[i]);
            else {
                int search = Arrays.binarySearch(arr, x);
                if (search < 0)
                    search = -search - 1;
                int l = search - 1;
                int r = search;
                while (l >= 0 && r < len && l + k >= r)
                    if (x - arr[l] <= arr[r] - x)
                        l--;
                    else
                        r++;
                if (l < 0)
                    for (int i = 0; i < k; i++)
                        list.add(arr[i]);
                else if (r >= len)
                    for (int i = len - k; i < len; i++)
                        list.add(arr[i]);
                else
                    for (int i = l + 1; i < r; i++)
                        list.add(arr[i]);
            }
            return list;
        }

        //排序    nlogn
        public List<Integer> findClosestElements2(int[] arr, int k, int x) {
            return Arrays.stream(arr)
                    .boxed()
                    .sorted((o1, o2) -> {
                        int diff = Math.abs(o1 - x) - Math.abs(o2 - x);
                        return diff == 0 ? o1 - o2 : diff;
                    })
                    .limit(k)
                    .sorted()
                    .toList();
        }

        //二分查找+双指针  logn+k
        public List<Integer> findClosestElements3(int[] arr, int k, int x) {
            ArrayList<Integer> list = new ArrayList<>();
            int len = arr.length;
            if (x <= arr[0])
                for (int i = 0; i < k; i++)
                    list.add(arr[i]);
            else if (arr[len - 1] <= x)
                for (int i = len - k; i < len; i++)
                    list.add(arr[i]);
            else {
                int search = Arrays.binarySearch(arr, x);
                if (search < 0)
                    search = -search - 1;
                int l = search - 1;
                int r = search;
                while (l >= 0 && r < len && l + k >= r)
                    if (x - arr[l] <= arr[r] - x)
                        l--;
                    else
                        r++;

                if (l < 0)
                    r = k;
                else if (r >= len)
                    l = len - k - 1;

                for (int i = l + 1; i < r; i++)
                    list.add(arr[i]);
            }
            return list;
        }

        //排除法（双指针）  n-k
        public List<Integer> findClosestElements4(int[] arr, int k, int x) {
            int len = arr.length;
            int l = 0, r = len - 1;
            for (int i = 0; i < len - k; i++) {
                if (x - arr[l] <= arr[r] - x)
                    r--;
                else
                    l++;
            }
            return Arrays.stream(arr)
                    .skip(l)
                    .limit(k)
                    .boxed()
                    .toList();
        }

        //todo 二分查找最优区间的左边界 https://leetcode.cn/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/
    }
//leetcode submit region end(Prohibit modification and deletion)
}