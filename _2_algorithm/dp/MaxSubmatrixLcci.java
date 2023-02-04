//<p>给定一个正整数、负整数和 0 组成的 N × M&nbsp;矩阵，编写代码找出元素总和最大的子矩阵。</p>
//
//<p>返回一个数组 <code>[r1, c1, r2, c2]</code>，其中 <code>r1</code>, <code>c1</code> 分别代表子矩阵左上角的行号和列号，<code>r2</code>, <code>c2</code> 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。</p>
//
//<p><strong>注意：</strong>本题相对书上原题稍作改动</p>
//
//<p><strong>示例：</strong></p>
//
//<pre><strong>输入：
//</strong><span><code>[
//&nbsp;  [-1,<strong>0</strong>],
//&nbsp;  [0,-1]
//]</code></span>
//<strong>输出：</strong>[0,1,0,1]
//<strong>解释：</strong>输入中标粗的元素即为输出所表示的矩阵</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>说明：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= matrix.length, matrix[0].length &lt;= 200</code></li> 
//</ul>
//
//<div><li>👍 168</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

// 面试题 17.24.最大子矩阵
// 开题时间：2022-11-23 16:27:27
public class MaxSubmatrixLcci {
  public static void main(String[] args) {
    Solution solution = new MaxSubmatrixLcci().new Solution();
    int[][] matrix = new int[][]{
        {9, -8, 1, 3, -2},
        {-3, 7, 6, -2, 4},
        {6, -4, -4, 8, -7}};
    //        System.out.println(Arrays.toString(solution.getMaxMatrix(
    //        )));
    System.out.println(Arrays.toString(solution.getMaxMatrix(new int[][]
        {
            {8, -4, 5},
            {-1, 4, 4},
            {-2, 3, 1},
            {-4, 4, 3}
        }
    )));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] getMaxMatrix10(int[][] matrix) {
      int m = matrix.length;
      int n = matrix[0].length;
      
      if (m > n) {
        int[][] rotated = new int[n][m];
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            rotated[i][j] = matrix[j][i];
          }
        }
        int[] ans = getMaxMatrix(rotated);
        return new int[]{ans[1], ans[0], ans[3], ans[2]};
      }
      
      // prefix sum
      int[][] prefix = new int[m][n];
      for (int c = 0; c < n; c++) {
        prefix[0][c] = matrix[0][c];
        for (int r = 1; r < m; r++)
          prefix[r][c] = prefix[r - 1][c] + matrix[r][c];
      }
      
      int max = Integer.MIN_VALUE;
      int[] ans = new int[4];
      for (int r1 = 0; r1 < m; r1++)
        for (int r2 = r1; r2 < m; r2++)
          for (int c2 = 0, c1 = 0, cur = 0; c2 < n; c2++) {
            int incr = prefix[r2][c2] - (r1 > 0 ? prefix[r1 - 1][c2] : 0);
            if (cur > 0) {
              cur += incr;
            } else {
              c1 = c2;
              cur = incr;
            }
            
            if (cur > max) {
              ans = new int[]{r1, c1, r2, c2};
              max = cur;
            }
          }
      
      return ans;
    }
    
    // 二维前缀和
    public int[] getMaxMatrix9(int[][] matrix) {
      int m = matrix.length + 1;
      int n = matrix[0].length + 1;
      
      int[][] prefix = new int[m][n];
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + matrix[i - 1][j - 1];
      
      int max = Integer.MIN_VALUE;
      int[] ans = new int[]{};
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          for (int p = i; p < m; p++)
            for (int q = j; q < n; q++) {
              int sum = prefix[p][q] + prefix[i - 1][j - 1] - prefix[p][j - 1] - prefix[i - 1][q];
              if (max < sum) {
                max = sum;
                ans = new int[]{i - 1, j - 1, p - 1, q - 1};
              }
            }
      
      return ans;
    }
    
    //☆☆☆☆☆ DP（二维最大子数组和）    m^2*n
    public int[] getMaxMatrix5(int[][] matrix) {
      int m = matrix.length;
      int n = matrix[0].length;
      
      int max = Integer.MIN_VALUE;
      int[] ans = new int[]{};
      
      for (int i = 0; i < m; i++) {
        int[] arr = new int[n];
        for (int j = i; j < m; j++) {
          for (int k = 0, c = 0, sum = 0; k < n; k++) {
            arr[k] += matrix[j][k];
            
            if (sum >= 0)
              sum += arr[k];
            else {
              sum = arr[k];
              c = k;
            }
            
            if (max < sum) {
              max = sum;
              ans = new int[]{i, c, j, k};
            }
          }
        }
      }
      
      return ans;
    }
    
    
    public int[] getMaxMatrix(int[][] matrix) {
      int m = matrix.length + 1;
      int n = matrix[0].length + 1;
      
      //            if (m > n) {
      //                int a = n - 1;
      //                int b = m - 1;
      //                int[][] transformed = new int[a][b];
      //                for (int i = 0; i < a; i++)
      //                    for (int j = 0; j < b; j++)
      //                        transformed[i][j] = matrix[j][i];
      //                return getMaxMatrix(transformed);
      //            }
      
      int max = Integer.MIN_VALUE;
      int[] ans = new int[]{};
      
      for (int top = 1; top < m; top++) {
        int[] prefix = new int[n];
        for (int bot = top; bot < m; bot++) {
          TreeMap<Integer, Integer> map = new TreeMap<>();
          map.put(0, 0);
          int right = 0;
          for (int j = 1; j < n; j++) {
            prefix[j] += matrix[bot - 1][j - 1];
            right += prefix[j];
            Map.Entry<Integer, Integer> left = map.ceilingEntry(right - Integer.MAX_VALUE / 2);
            if (left != null)
              if (max < right - left.getKey()) {
                max = right - left.getKey();
                ans = new int[]{top - 1, left.getValue(), bot - 1, j - 1};
              }
            map.put(right, j);
          }
        }
      }
      
      return ans;
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}