//<p>给你一个数组 <code>rectangles</code> ，其中 <code>rectangles[i] = [l<sub>i</sub>, w<sub>i</sub>]</code> 表示第 <code>i</code> 个矩形的长度为 <code>l<sub>i</sub></code> 、宽度为 <code>w<sub>i</sub></code> 。</p>
//
//<p>如果存在 <code>k</code> 同时满足 <code>k &lt;= l<sub>i</sub></code> 和 <code>k &lt;= w<sub>i</sub></code> ，就可以将第 <code>i</code> 个矩形切成边长为 <code>k</code> 的正方形。例如，矩形 <code>[4,6]</code> 可以切成边长最大为 <code>4</code> 的正方形。</p>
//
//<p>设 <code>maxLen</code> 为可以从矩形数组&nbsp;<code>rectangles</code> 切分得到的 <strong>最大正方形</strong> 的边长。</p>
//
//<p>请你统计有多少个矩形能够切出边长为<em> </em><code>maxLen</code> 的正方形，并返回矩形 <strong>数目</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>rectangles = [[5,8],[3,9],[5,12],[16,5]]
//<strong>输出：</strong>3
//<strong>解释：</strong>能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
// 最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>rectangles = [[2,3],[3,7],[4,3],[3,7]]
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= rectangles.length &lt;= 1000</code></li> 
// <li><code>rectangles[i].length == 2</code></li> 
// <li><code>1 &lt;= l<sub>i</sub>, w<sub>i</sub> &lt;= 10<sup>9</sup></code></li> 
// <li><code>l<sub>i</sub> != w<sub>i</sub></code></li> 
//</ul>
//
//<div><li>👍 58</li><li>👎 0</li></div>
package _1_dataStructure.arrayAndString;

// 1725.可以形成最大正方形的矩形数目
// 开题时间：2022-11-24 17:00:01
public class NumberOfRectanglesThatCanFormTheLargestSquare {
  public static void main(String[] args) {
    Solution solution = new NumberOfRectanglesThatCanFormTheLargestSquare().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int countGoodRectangles(int[][] rectangles) {
      int cnt = 0;
      int max = 0;
      for (int[] rectangle : rectangles) {
        int len = Math.min(rectangle[0], rectangle[1]);
        if (max == len)
          cnt++;
        else if (max < len) {
          max = len;
          cnt = 1;
        }
      }
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}