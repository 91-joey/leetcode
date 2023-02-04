//<p>平面上有&nbsp;<code>n</code>&nbsp;个点，点的位置用整数坐标表示 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 。请你计算访问所有这些点需要的 <strong>最小时间</strong>（以秒为单位）。</p>
//
//<p>你需要按照下面的规则在平面上移动：</p>
//
//<ul> 
// <li>每一秒内，你可以： 
//  <ul> 
//   <li>沿水平方向移动一个单位长度，或者</li> 
//   <li>沿竖直方向移动一个单位长度，或者</li> 
//   <li>跨过对角线移动 <code>sqrt(2)</code> 个单位长度（可以看作在一秒内向水平和竖直方向各移动一个单位长度）。</li> 
//  </ul> </li> 
// <li>必须按照数组中出现的顺序来访问这些点。</li> 
// <li>在访问某个点时，可以经过该点后面出现的点，但经过的那些点不算作有效访问。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/24/1626_example_1.png" style="height: 428px; width: 500px;" /></p>
//
//<pre>
//<strong>输入：</strong>points = [[1,1],[3,4],[-1,0]]
//<strong>输出：</strong>7
//<strong>解释：</strong>一条最佳的访问路径是： <strong>[1,1]</strong> -&gt; [2,2] -&gt; [3,3] -&gt; <strong>[3,4] </strong>-&gt; [2,3] -&gt; [1,2] -&gt; [0,1] -&gt; <strong>[-1,0]</strong>   
// 从 [1,1] 到 [3,4] 需要 3 秒
// 从 [3,4] 到 [-1,0] 需要 4 秒
// 一共需要 7 秒</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>points = [[3,2],[-2,2]]
//<strong>输出：</strong>5
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>points.length == n</code></li> 
// <li><code>1 &lt;= n&nbsp;&lt;= 100</code></li> 
// <li><code>points[i].length == 2</code></li> 
// <li><code>-1000&nbsp;&lt;= points[i][0], points[i][1]&nbsp;&lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 94</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.geometry;

// 1266.访问所有点的最小时间
// 开题时间：2023-01-07 12:14:28
public class MinimumTimeVisitingAllPoints {
  public static void main(String[] args) {
    Solution solution = new MinimumTimeVisitingAllPoints().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
      int ans = 0;
      for (int i = 1; i < points.length; i++)
        ans += Math.max(Math.abs(points[i - 1][0] - points[i][0]), Math.abs(points[i - 1][1] - points[i][1]));
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}