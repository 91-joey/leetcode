//<p>给你一个大小为&nbsp;<code>m&nbsp;* n</code>&nbsp;的矩阵&nbsp;<code>mat</code>，矩阵由若干军人和平民组成，分别用 1 和 0 表示。</p>
//
//<p>请你返回矩阵中战斗力最弱的&nbsp;<code>k</code>&nbsp;行的索引，按从最弱到最强排序。</p>
//
//<p>如果第&nbsp;<em><strong>i</strong></em>&nbsp;行的军人数量少于第&nbsp;<em><strong>j</strong></em>&nbsp;行，或者两行军人数量相同但<em><strong> i</strong></em> 小于 <em><strong>j</strong></em>，那么我们认为第<em><strong> i </strong></em>行的战斗力比第<em><strong> j </strong></em>行弱。</p>
//
//<p>军人 <strong>总是</strong> 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>mat = 
//[[1,1,0,0,0],
// [1,1,1,1,0],
// [1,0,0,0,0],
// [1,1,0,0,0],
// [1,1,1,1,1]], 
//k = 3
//<strong>输出：</strong>[2,0,3]
//<strong>解释：</strong>
//每行中的军人数目：
//行 0 -&gt; 2 
//行 1 -&gt; 4 
//行 2 -&gt; 1 
//行 3 -&gt; 2 
//行 4 -&gt; 5 
//从最弱到最强对这些行排序后得到 [2,0,3,1,4]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>mat = 
//[[1,0,0,0],
//&nbsp;[1,1,1,1],
//&nbsp;[1,0,0,0],
//&nbsp;[1,0,0,0]], 
//k = 2
//<strong>输出：</strong>[0,2]
//<strong>解释：</strong> 
//每行中的军人数目：
//行 0 -&gt; 1 
//行 1 -&gt; 4 
//行 2 -&gt; 1 
//行 3 -&gt; 1 
//从最弱到最强对这些行排序后得到 [0,2,3,1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == mat.length</code></li> 
// <li><code>n == mat[i].length</code></li> 
// <li><code>2 &lt;= n, m &lt;= 100</code></li> 
// <li><code>1 &lt;= k &lt;= m</code></li> 
// <li><code>matrix[i][j]</code> 不是 0 就是 1</li> 
//</ul>
//
//<div><li>👍 190</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//1337.矩阵中战斗力最弱的 K 行
//开题时间：2022-11-28 15:40:09
public class TheKWeakestRowsInAMatrix {
    public static void main(String[] args) {
        Solution solution = new TheKWeakestRowsInAMatrix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //二分+排序
        public int[] kWeakestRows9(int[][] mat, int k) {
            int m = mat.length;
            int n = mat[0].length;
            int[][] idx2soldiers = new int[m][2];

            for (int i = 0; i < m; i++) {
                int idx = firstIdxOfZero(mat, i);
                idx2soldiers[i] = new int[]{i, idx};
            }
            Arrays.sort(idx2soldiers, Comparator.<int[]>comparingInt(arr -> arr[1]).thenComparingInt(arr -> arr[0]));
            return Arrays.stream(idx2soldiers).limit(k).mapToInt(arr -> arr[0]).toArray();
//            Arrays.sort(idx2soldiers,
//                    (o1, o2) -> o1[1] != o2[1] ?
//                            o1[1] - o2[1] :
//                            o1[0] - o2[0]
//            );
//            int[] ans = new int[k];
//            for (int i = 0; i < k; i++)
//                ans[i] = idx2soldiers[i][0];
//            return ans;
        }

        public static int firstIdxOfZero(int[][] mat, int row) {
            int l = 0, r = mat[0].length;
            while (l < r) {
                int mid = ((r - l) >> 1) + l;
                if (0 >= mat[row][mid])
                    r = mid;
                else
                    l = mid + 1;
            }
            return r;
        }

        //☆☆☆☆☆ 二分+优先队列
        public int[] kWeakestRows(int[][] mat, int k) {
            int m = mat.length;
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                    m,
                    Comparator.<int[]>comparingInt(arr -> arr[1]).
                            thenComparingInt(arr -> arr[0])
            );
            for (int i = 0; i < m; i++) {
                int idx = firstIdxOfZero(mat, i);
                pq.offer(new int[]{i, idx});
            }

            int[] ans = new int[k];
            for (int i = 0; i < k; i++)
                ans[i] = pq.poll()[0];
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}