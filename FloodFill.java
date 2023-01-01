//<p>有一幅以&nbsp;<code>m x n</code>&nbsp;的二维整数数组表示的图画&nbsp;<code>image</code>&nbsp;，其中&nbsp;<code>image[i][j]</code>&nbsp;表示该图画的像素值大小。</p>
//
//<p>你也被给予三个整数 <code>sr</code> ,&nbsp; <code>sc</code> 和 <code>newColor</code> 。你应该从像素&nbsp;<code>image[sr][sc]</code>&nbsp;开始对图像进行 上色<strong>填充</strong> 。</p>
//
//<p>为了完成<strong> 上色工作</strong> ，从初始像素开始，记录初始坐标的 <strong>上下左右四个方向上</strong> 像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应 <strong>四个方向上</strong> 像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为&nbsp;<code>newColor</code>&nbsp;。</p>
//
//<p>最后返回 <em>经过上色渲染后的图像&nbsp;</em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/06/01/flood1-grid.jpg" /></p>
//
//<pre>
//<strong>输入:</strong> image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, newColor = 2
//<strong>输出:</strong> [[2,2,2],[2,2,0],[2,0,1]]
//<strong>解析:</strong> 在图像的正中间，(坐标(sr,sc)=(1,1)),在路径上所有符合条件的像素点的颜色都被更改成2。
//注意，右下角的像素没有更改为2，因为它不是在上下左右四个方向上与初始点相连的像素点。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
//<strong>输出:</strong> [[2,2,2],[2,2,2]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>m == image.length</code></li> 
// <li><code>n == image[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 50</code></li> 
// <li><code>0 &lt;= image[i][j], newColor &lt; 2<sup>16</sup></code></li> 
// <li><code>0 &lt;= sr &lt;&nbsp;m</code></li> 
// <li><code>0 &lt;= sc &lt;&nbsp;n</code></li> 
//</ul>
//
//<div><li>👍 402</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.LinkedList;
import java.util.Queue;

//733.图像渲染
//开题时间：2023-01-01 09:36:02
public class FloodFill {
    public static void main(String[] args) {
        Solution solution = new FloodFill().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final int[] DIRS = {1, 0, -1, 0, 1};

        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            int m = image.length;
            int n = image[0].length;
            boolean[][] vis = new boolean[m][n];
            vis[sr][sc] = true;
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{sr, sc});
            int t = image[sr][sc];

            while (!q.isEmpty()) {
                int[] poll = q.poll();
                int r = poll[0];
                int c = poll[1];
                image[r][c] = color;
                for (int i = 0; i < 4; i++) {
                    int rNew = r + DIRS[i];
                    int cNew = c + DIRS[i + 1];
                    if (0 <= rNew && rNew < m && 0 <= cNew && cNew < n &&
                            !vis[rNew][cNew] && image[rNew][cNew] == t) {
                        q.offer(new int[]{rNew, cNew});
                        vis[rNew][cNew] = true;
                    }
                }
            }

            return image;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}