//<p>给定一个数组&nbsp;<code>coordinates</code>&nbsp;，其中&nbsp;<code>coordinates[i] = [x, y]</code>&nbsp;，
// <meta charset="UTF-8" />&nbsp;<code>[x, y]</code>&nbsp;表示横坐标为 <code>x</code>、纵坐标为 <code>y</code>&nbsp;的点。请你来判断，这些点是否在该坐标系中属于同一条直线上。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/19/untitled-diagram-2.jpg" /></p>
//
//<pre>
//<strong>输入：</strong>coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/19/untitled-diagram-1.jpg" /></strong></p>
//
//<pre>
//<strong>输入：</strong>coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;=&nbsp;coordinates.length &lt;= 1000</code></li> 
// <li><code>coordinates[i].length == 2</code></li> 
// <li><code>-10^4 &lt;=&nbsp;coordinates[i][0],&nbsp;coordinates[i][1] &lt;= 10^4</code></li> 
// <li><code>coordinates</code>&nbsp;中不含重复的点</li> 
//</ul>
//
//<div><li>👍 126</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.geometry;

import java.util.Arrays;

//1232.缀点成线
//开题时间：2022-12-05 13:46:01
public class CheckIfItIsAStraightLine {
    public static void main(String[] args) {
        Solution solution = new CheckIfItIsAStraightLine().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //斜率法（不建议，因为要考虑除数为 0 的情况、且除法效率不高）
        public boolean checkStraightLine9(int[][] coordinates) {
            int x0 = coordinates[0][0];
            int y0 = coordinates[0][1];
            int diffX = coordinates[1][0] - x0;
            if (diffX == 0)
                return Arrays.stream(coordinates).allMatch(coordinate -> coordinate[0] == x0);

            double delta = (double) (coordinates[1][1] - y0) / diffX;
            for (int i = 2; i < coordinates.length; i++)
                if ((double) (coordinates[i][1] - y0) / (coordinates[i][0] - x0) != delta)
                    return false;
            return true;
        }

        //乘法代替除法，即从 (yi-y0)/(xi-x0)=?diffY/diffX 转为 (yi-y0)*diffX=?(xi-x0)*diffY
        //(yi-y0)/(xi-x0) = (y1-y0)/(x1-x0) ×
        //(yi-y0)*(x1-x0) = (y1-y0)*(xi-x0) √
        public boolean checkStraightLine(int[][] coordinates) {
            int x0 = coordinates[0][0];
            int y0 = coordinates[0][1];
            int diffY = coordinates[1][1] - y0;
            int diffX = coordinates[1][0] - x0;
            for (int i = 2; i < coordinates.length; i++)
                if ((coordinates[i][1] - y0) * diffX != (coordinates[i][0] - x0) * diffY)
                    return false;
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}