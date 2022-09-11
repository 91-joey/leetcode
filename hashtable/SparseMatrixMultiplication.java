//<p>给定两个&nbsp;<a href="https://baike.baidu.com/item/%E7%A8%80%E7%96%8F%E7%9F%A9%E9%98%B5" target="_blank">稀疏矩阵</a>&nbsp;：大小为 <code>m x k</code> 的稀疏矩阵 <code>mat1</code> 和大小为 <code>k x n</code> 的稀疏矩阵 <code>mat2</code> ，返回 <code>mat1 x mat2</code> 的结果。你可以假设乘法总是可能的。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/03/12/mult-grid.jpg" style="height: 142px; width: 500px;" /></p>
//
//<pre>
//<strong>输入：</strong>mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
//<strong>输出：</strong>[[7,0,0],[-7,0,3]]
//</pre>
//
//<p><strong>&nbsp;示例 2:</strong></p>
//
//<pre>
//<b>输入：</b>mat1 = [[0]], mat2 = [[0]]
//<b>输出：</b>[[0]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>m == mat1.length</code></li> 
// <li><code>k == mat1[i].length == mat2.length</code></li> 
// <li><code>n == mat2[i].length</code></li> 
// <li><code>1 &lt;= m, n, k &lt;= 100</code></li> 
// <li><code>-100 &lt;= mat1[i][j], mat2[i][j] &lt;= 100</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>矩阵</li></div></div><br><div><li>👍 71</li><li>👎 0</li></div>
package org.example.leetcode.problems.hashtable;

import java.util.ArrayList;
import java.util.List;

//311.稀疏矩阵的乘法
//开题时间：2022-09-09 14:34:58
public class SparseMatrixMultiplication {
    public static void main(String[] args) {
        Solution solution = new SparseMatrixMultiplication().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力迭代  m*n*k   m*n
        public int[][] multiply(int[][] mat1, int[][] mat2) {
            int m = mat1.length;
            int n = mat2[0].length;
            int k = mat2.length;
            int[][] ans = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int sum = 0;
                    for (int p = 0; p < k; p++) {
                        sum += mat1[i][p] * mat2[p][j];
                    }
                    ans[i][j] = sum;
                }
            }

            return ans;
        }

        //高分解：利用矩阵的稀疏性  m*k+k*n+(m*k*k*n=max)
        public int[][] multiply2(int[][] mat1, int[][] mat2) {
            int m = mat1.length;
            int n = mat2[0].length;
            int[][] ans = new int[m][n];

            List<int[]> list1 = getTriple(mat1);
            List<int[]> list2 = getTriple(mat2);
            for (int[] arr1 : list1) {
                for (int[] arr2 : list2) {
                    if (arr1[1] == arr2[0]) {
                        ans[arr1[0]][arr2[1]] += arr1[2] * arr2[2];
                    }
                }
            }

            return ans;
        }

        private List<int[]> getTriple(int[][] mat1) {
            List<int[]> notZeroes = new ArrayList<>();

            for (int i = 0; i < mat1.length; i++) {
                for (int j = 0; j < mat1[0].length; j++) {
                    if (mat1[i][j] != 0) {
                        notZeroes.add(new int[]{i, j, mat1[i][j]});
                    }
                }
            }

            return notZeroes;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}