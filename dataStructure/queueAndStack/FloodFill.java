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
//<p><img src="https://assets.leetcode.com/uploads/2021/06/01/flood1-image.jpg" /></p>
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
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 362</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.queueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//733.图像渲染
//开题时间：2022-08-24 11:12:14
public class FloodFill {
    public static void main(String[] args) {
        Solution solution = new FloodFill().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final int[][] DIRECTIONS = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };
        private int oldColor;

        //1.DFS+递归
        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            oldColor = image[sr][sc];
            if (oldColor != color) {
                image[sr][sc] = color;
                DFS(image, sr, sc, color);
            }
            return image;
        }

        private void DFS(int[][] image, int sr, int sc, int color) {
            for (int[] direction : DIRECTIONS) {
                int xChild = sr + direction[0];
                int yChild = sc + direction[1];
                if (0 <= xChild && xChild < image.length && 0 <= yChild && yChild < image[0].length &&
                        image[xChild][yChild] == oldColor) {
                    image[xChild][yChild] = color;
                    DFS(image, xChild, yChild, color);
                }
            }
        }


        //2.DFS+显示栈
        public int[][] floodFill2(int[][] image, int sr, int sc, int color) {
            oldColor = image[sr][sc];

            if (oldColor != color) {
                image[sr][sc] = color;
                Deque<int[]> stack = new ArrayDeque<>();
                stack.push(new int[]{sr, sc});

                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();
                    int x = pop[0];
                    int y = pop[1];
                    for (int[] direction : DIRECTIONS) {
                        int xChild = x + direction[0];
                        int yChild = y + direction[1];
                        if (0 <= xChild && xChild < image.length && 0 <= yChild && yChild < image[0].length &&
                                image[xChild][yChild] == oldColor) {
                            image[xChild][yChild] = color;
                            stack.push(new int[]{xChild, yChild});
                        }
                    }
                }
            }

            return image;
        }

        //3.BFS+队列
        public int[][] floodFill3(int[][] image, int sr, int sc, int color) {
            oldColor = image[sr][sc];

            if (oldColor != color) {
                image[sr][sc] = color;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{sr, sc});

                while (!queue.isEmpty()) {
                    int[] head = queue.poll();
                    int x = head[0];
                    int y = head[1];
                    for (int[] direction : DIRECTIONS) {
                        int xChild = x + direction[0];
                        int yChild = y + direction[1];
                        if (0 <= xChild && xChild < image.length && 0 <= yChild && yChild < image[0].length &&
                                image[xChild][yChild] == oldColor) {
                            image[xChild][yChild] = color;
                            queue.offer(new int[]{xChild, yChild});
                        }
                    }
                }
            }

            return image;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}