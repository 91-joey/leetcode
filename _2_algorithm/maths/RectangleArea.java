//<p>给你 <strong>二维</strong> 平面上两个 <strong>由直线构成且边与坐标轴平行/垂直</strong> 的矩形，请你计算并返回两个矩形覆盖的总面积。</p>
//
//<p>每个矩形由其 <strong>左下</strong> 顶点和 <strong>右上</strong> 顶点坐标表示：</p>
//
//<div class="MachineTrans-Lines"> 
// <ul> 
//  <li class="MachineTrans-lang-zh-CN">第一个矩形由其左下顶点 <code>(ax1, ay1)</code> 和右上顶点 <code>(ax2, ay2)</code> 定义。</li> 
//  <li class="MachineTrans-lang-zh-CN">第二个矩形由其左下顶点 <code>(bx1, by1)</code> 和右上顶点 <code>(bx2, by2)</code> 定义。</li> 
// </ul> 
//</div>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="Rectangle Area" src="https://assets.leetcode.com/uploads/2021/05/08/rectangle-plane.png" style="width: 700px; height: 365px;" /> 
//<pre>
//<strong>输入：</strong>ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
//<strong>输出：</strong>45
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
//<strong>输出：</strong>16
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-10<sup>4</sup> &lt;= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 221</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

//223.矩形面积
//开题时间：2023-01-17 11:19:32
public class RectangleArea {
    public static void main(String[] args) {
        Solution solution = new RectangleArea().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int computeArea9(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            return getArea(ax1, ay1, ax2, ay2)
                    + getArea(bx1, by1, bx2, by2)
                    - ((isRectangleOverlap(new int[]{ax1, ay1, ax2, ay2}, new int[]{bx1, by1, bx2, by2})) ?
                    getOverlapLine(ax1, ax2, bx1, bx2) * getOverlapLine(ay1, ay2, by1, by2) :
                    0);
        }

        public int computeArea8(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            int overlapLine1 = getOverlapLine(ax1, ax2, bx1, bx2);
            int overlapLine2 = getOverlapLine(ay1, ay2, by1, by2);
            return getArea(ax1, ay1, ax2, ay2)
                    + getArea(bx1, by1, bx2, by2)
                    - (overlapLine1 > 0 && overlapLine2 > 0 ? overlapLine1 * overlapLine2 : 0);
        }

        public int computeArea7(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            return getArea(ax1, ay1, ax2, ay2)
                    + getArea(bx1, by1, bx2, by2)
                    - getOverlapArea(
                    Math.max(ax1, bx1),
                    Math.max(ay1, by1),
                    Math.min(ax2, bx2),
                    Math.min(ay2, by2)
            );
        }

        //☆☆☆☆☆ 容斥原理 + 降维运算（相交面积 = 横竖轴上的投影相交线段（必须是正数）相乘）
        public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            return getArea(ax1, ay1, ax2, ay2)
                    + getArea(bx1, by1, bx2, by2)
                    - getOverlapLine(ax1, ax2, bx1, bx2) * getOverlapLine(ay1, ay2, by1, by2);
        }

        private int getOverlapArea(int x1, int y1, int x2, int y2) {
            return x1 < x2 && y1 < y2 ? getArea(x1, y1, x2, y2) : 0;
        }

        private int getOverlapLine(int l1, int r1, int l2, int r2) {
            return Math.max(0, Math.min(r1, r2) - Math.max(l1, l2));
        }

        private int getArea(int x1, int y1, int x2, int y2) {
            return (x2 - x1) * (y2 - y1);
        }

        //二维重叠 = 双一维重叠（x、y轴的投影线段都重叠，这可以通过不重叠的情况取反）
        public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
            return isLineOverlap(rec1[0], rec1[2], rec2[0], rec2[2])
                    && isLineOverlap(rec1[1], rec1[3], rec2[1], rec2[3]);
        }

        private boolean isLineOverlap(int l1, int r1, int l2, int r2) {
            return !(l1 >= r2 || l2 >= r1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}