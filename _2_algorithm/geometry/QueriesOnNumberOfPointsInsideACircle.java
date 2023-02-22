//<p>给你一个数组&nbsp;<code>points</code>&nbsp;，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;，表示第&nbsp;<code>i</code>&nbsp;个点在二维平面上的坐标。多个点可能会有 <strong>相同</strong>&nbsp;的坐标。</p>
//
//<p>同时给你一个数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[j] = [x<sub>j</sub>, y<sub>j</sub>, r<sub>j</sub>]</code>&nbsp;，表示一个圆心在&nbsp;<code>(x<sub>j</sub>, y<sub>j</sub>)</code>&nbsp;且半径为&nbsp;<code>r<sub>j</sub></code><sub>&nbsp;</sub>的圆。</p>
//
//<p>对于每一个查询&nbsp;<code>queries[j]</code>&nbsp;，计算在第 <code>j</code>&nbsp;个圆 <strong>内</strong>&nbsp;点的数目。如果一个点在圆的 <strong>边界上</strong>&nbsp;，我们同样认为它在圆&nbsp;<strong>内</strong>&nbsp;。</p>
//
//<p>请你返回一个数组<em>&nbsp;</em><code>answer</code>&nbsp;，其中<em>&nbsp;</em><code>answer[j]</code>是第&nbsp;<code>j</code>&nbsp;个查询的答案。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/25/chrome_2021-03-25_22-34-16.png" style="width: 500px; height: 418px;"> <pre><b>输入：</b>points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
//<b>输出：</b>[3,2,2]
//<b>解释：</b>所有的点和圆如上图所示。
// queries[0] 是绿色的圆，queries[1] 是红色的圆，queries[2] 是蓝色的圆。
//</pre> </img>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/03/25/chrome_2021-03-25_22-42-07.png" style="width: 500px; height: 390px;"> <pre><b>输入：</b>points = [[1,1],[2,2],[3,3],[4,4],[5,5]], queries = [[1,2,2],[2,2,2],[4,3,2],[4,3,3]]
//<b>输出：</b>[2,3,2,4]
//<b>解释：</b>所有的点和圆如上图所示。
// queries[0] 是绿色的圆，queries[1] 是红色的圆，queries[2] 是蓝色的圆，queries[3] 是紫色的圆。
//</pre> </img>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= points.length &lt;= 500</code></li> 
// <li><code>points[i].length == 2</code></li> 
// <li><code>0 &lt;= x<sub>​​​​​​i</sub>, y<sub>​​​​​​i</sub> &lt;= 500</code></li> 
// <li><code>1 &lt;= queries.length &lt;= 500</code></li> 
// <li><code>queries[j].length == 3</code></li> 
// <li><code>0 &lt;= x<sub>j</sub>, y<sub>j</sub> &lt;= 500</code></li> 
// <li><code>1 &lt;= r<sub>j</sub> &lt;= 500</code></li> 
// <li>所有的坐标都是整数。</li> 
//</ul>
//
//<div><li>👍 28</li><li>👎 0</li></div>
package _2_algorithm.geometry;

import java.util.Arrays;
import java.util.Comparator;

// 1828.统计一个圆中点的数目
// 开题时间：2023-01-24 09:02:40
public class QueriesOnNumberOfPointsInsideACircle {
  public static void main(String[] args) {
    Solution solution = new QueriesOnNumberOfPointsInsideACircle().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // brute force
    public int[] countPoints9(int[][] points, int[][] queries) {
      int[] ans = new int[queries.length];
      for (int i = 0; i < queries.length; i++)
        for (int[] point : points)
          if (getSquareDistance(point, queries[i]) <= queries[i][2] * queries[i][2])
            ans[i]++;
      return ans;
    }
    
    public int getSquareDistance(int x1, int y1, int x2, int y2) {
      int disX = x1 - x2;
      int disY = y1 - y2;
      return disX * disX + disY * disY;
    }
    
    public int getSquareDistance(int[] a, int[] b) {
      return getSquareDistance(a[0], a[1], b[0], b[1]);
    }
    
    // 排序 + 二分横坐标的左右边界
    public int[] countPoints(int[][] points, int[][] queries) {
      Arrays.sort(points, Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]));
      
      int n = queries.length;
      int[] res = new int[n];
      for (int i = 0; i < n; i++) {
        int u = queries[i][0], v = queries[i][1], r = queries[i][2];
        int left = u - r, right = u + r;
        
        int idx1 = binarySearchLeft(points, left);
        int idx2 = binarySearchRight(points, right);
        
        int count = 0;
        for (int j = idx1; j < idx2; j++) {
          int x = points[j][0], y = points[j][1];
          if (v - r <= y && y <= v + r && (x - u) * (x - u) + (y - v) * (y - v) <= r * r) {
            count++;
          }
        }
        res[i] = count;
      }
      return res;
    }
    
    public int binarySearchLeft(int[][] points, int x) {
      int left = 0, right = points.length;
      while (left < right) {
        int mid = (left + right) / 2;
        if (points[mid][0] < x) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }
      return left;
    }
    
    public int binarySearchRight(int[][] points, int x) {
      int left = 0, right = points.length;
      while (left < right) {
        int mid = (left + right) / 2;
        if (points[mid][0] > x) {
          right = mid;
        } else {
          left = mid + 1;
        }
      }
      return left;
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}