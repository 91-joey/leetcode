//<p>给出矩阵&nbsp;<code>matrix</code>&nbsp;和目标值&nbsp;<code>target</code>，返回元素总和等于目标值的非空子矩阵的数量。</p>
//
//<p>子矩阵&nbsp;<code>x1, y1, x2, y2</code>&nbsp;是满足 <code>x1 &lt;= x &lt;= x2</code>&nbsp;且&nbsp;<code>y1 &lt;= y &lt;= y2</code>&nbsp;的所有单元&nbsp;<code>matrix[x][y]</code>&nbsp;的集合。</p>
//
//<p>如果&nbsp;<code>(x1, y1, x2, y2)</code> 和&nbsp;<code>(x1', y1', x2', y2')</code>&nbsp;两个子矩阵中部分坐标不同（如：<code>x1 != x1'</code>），那么这两个子矩阵也不同。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/09/02/mate1.jpg" style="width: 242px; height: 242px;" /></p>
//
//<pre>
//<strong>输入：</strong>matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//<strong>输出：</strong>4
//<strong>解释：</strong>四个只含 0 的 1x1 子矩阵。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>matrix = [[1,-1],[-1,1]], target = 0
//<strong>输出：</strong>5
//<strong>解释：</strong>两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>matrix = [[904]], target = 0
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong><strong>提示：</strong></strong></p>
//
//<ul> 
// <li><code>1 &lt;= matrix.length &lt;= 100</code></li> 
// <li><code>1 &lt;= matrix[0].length &lt;= 100</code></li> 
// <li><code>-1000 &lt;= matrix[i] &lt;= 1000</code></li> 
// <li><code>-10^8 &lt;= target &lt;= 10^8</code></li> 
//</ul>
//
//<div><li>👍 209</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.HashMap;

//1074.元素和为目标值的子矩阵数量
//开题时间：2022-12-19 10:53:10
public class NumberOfSubmatricesThatSumToTarget {
    public static void main(String[] args) {
        Solution solution = new NumberOfSubmatricesThatSumToTarget().new Solution();
        System.out.println(solution.numSubmatrixSumTarget(Tools.to2DIntArray("[[904,1,0],[1,1,1],[0,1,0]]"), 0));
//        System.out.println(solution.numSubmatrixSumTarget(Tools.to2DIntArray("[[0,1,0],[1,1,1],[0,1,0]]"), 0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力二维前缀和
        public int numSubmatrixSumTarget9(int[][] matrix, int target) {
            int m = matrix.length + 1;
            int n = matrix[0].length + 1;

            int[][] prefix = new int[m][n];
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + matrix[i - 1][j - 1];

            int ans = 0;
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    for (int p = i; p < m; p++)
                        for (int q = j; q < n; q++)
                            if (target == prefix[p][q] - prefix[i - 1][q] - prefix[p][j - 1] + prefix[i - 1][j - 1])
                                ans++;

            return ans;
        }

        //☆☆☆☆☆ 固定上下界、枚举右边界 + 哈希计数：
        public int numSubmatrixSumTarget(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;

            int ans = 0;
            for (int i = 0; i < m; i++) {
                int[] prefix = new int[n];
                for (int j = i; j < m; j++) {
                    HashMap<Integer, Integer> sum2cnt = new HashMap<>();
                    sum2cnt.put(0, 1);
                    for (int k = 0, sum = 0; k < n; k++) {
                        prefix[k] += matrix[j][k];
                        sum += prefix[k];
                        ans += sum2cnt.getOrDefault(sum - target, 0);
                        sum2cnt.merge(sum, 1, Integer::sum);
                    }
                }
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}