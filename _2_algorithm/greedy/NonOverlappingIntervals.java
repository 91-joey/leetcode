//<p>给定一个区间的集合&nbsp;<code>intervals</code>&nbsp;，其中 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;。返回 <em>需要移除区间的最小数量，使剩余区间互不重叠&nbsp;</em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> intervals = [[1,2],[2,3],[3,4],[1,3]]
//<strong>输出:</strong> 1
//<strong>解释:</strong> 移除 [1,3] 后，剩下的区间没有重叠。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> intervals = [ [1,2], [1,2], [1,2] ]
//<strong>输出:</strong> 2
//<strong>解释:</strong> 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> intervals = [ [1,2], [2,3] ]
//<strong>输出:</strong> 0
//<strong>解释:</strong> 你不需要移除任何区间，因为它们已经是无重叠的了。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>intervals[i].length == 2</code></li> 
// <li><code>-5 * 10<sup>4</sup>&nbsp;&lt;= start<sub>i</sub>&nbsp;&lt; end<sub>i</sub>&nbsp;&lt;= 5 * 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 870</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

//435.无重叠区间
//开题时间：2023-01-19 15:05:01
public class NonOverlappingIntervals {
    public static void main(String[] args) {
        Solution solution = new NonOverlappingIntervals().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //难点：直接求重复的区间是复杂的，转而求最大非重复区间个数。
    class Solution {
        //排序 + dp(LIS) + 贪心 n^2
        public int eraseOverlapIntervals9(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            int n = intervals.length;
            int[] f = new int[n];
            Arrays.fill(f, 1);
            int max = 1;
            for (int i = 1; i < n; i++) {
                //逆序遍历
                for (int j = i - 1; j >= 0; j--)
                    if (intervals[i][0] >= intervals[j][1]) {
                        f[i] = Math.max(f[i], f[j] + 1);
                        //由于更小的左端点的dp值不会更大，相同左端的dp值相同，因此可以直接跳出循环
                        break;
                    }
                max = Math.max(max, f[i]);
            }
            return n - max;
        }

        //dp(LIS) + 贪心 + 有序映射（相同左端点的区间只保留右端点最小的）    n^2
        public int eraseOverlapIntervals8(int[][] intervals) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int[] interval : intervals)
                map.put(interval[0], Math.min(map.getOrDefault(interval[0], Integer.MAX_VALUE), interval[1]));
            int[][] arr = map.entrySet().stream().map(e -> new int[]{e.getKey(), e.getValue()}).toArray(int[][]::new);
            int n = arr.length;
            int[] f = new int[n];
            Arrays.fill(f, 1);
            int max = 1;
            for (int i = 1; i < n; i++) {
                for (int j = i - 1; j >= 0; j--)
                    if (arr[i][0] >= arr[j][1]) {
                        f[i] = Math.max(f[i], f[j] + 1);
                        //由于更小的左端点的dp值不会更大，相同左端的dp值相同，因此可以直接跳出循环
                        break;
                    }
                max = Math.max(max, f[i]);
            }
            return intervals.length - max;
        }

        /*
         * ☆☆☆☆☆ 排序 + 贪心 nlogn
         * 「求移除区间的最小数量，使剩余区间互不重叠」等价于「互不重叠的区间集合子集的最大长度」
         * 我们考虑对这些互不重叠的区间进行自然排序，则第一个（最左侧）的区间的右端点 r0 是最小的
         *  若存在区间 k 的右端点 rk<=r0 ，则将区间 0 替换为 区间 k，答案不变
         *  若存在区间 k 的右端点 rk> r0 ，则将区间 0 替换为 区间 k，答案可能会变大（可能导致有区间与区间 k 相交）
         * 因此我们贪心的选择右端点最小的区间作为第一个区间
         * 对于第二个以及第 n 个区间，我们选择与之前区间不想交（在右侧，即 l_n >= r_n-1）的区间中右端点最小的。
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
            int n = intervals.length;
            int max = 1;
            for (int i = 1, minL = intervals[0][1]; i < n; i++) {
                if (intervals[i][0] >= minL) {
                    max++;
                    minL = intervals[i][1];
                }
            }
            return n - max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}