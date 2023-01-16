//<p>矩形以列表 <code>[x1, y1, x2, y2]</code> 的形式表示，其中 <code>(x1, y1)</code> 为左下角的坐标，<code>(x2, y2)</code> 是右上角的坐标。矩形的上下边平行于 x 轴，左右边平行于 y 轴。</p>
//
//<p>如果相交的面积为 <strong>正</strong> ，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。</p>
//
//<p>给出两个矩形 <code>rec1</code> 和 <code>rec2</code> 。如果它们重叠，返回 <code>true</code>；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>rec1 = [0,0,2,2], rec2 = [1,1,3,3]
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>rec1 = [0,0,1,1], rec2 = [1,0,2,1]
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>rec1 = [0,0,1,1], rec2 = [2,2,3,3]
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>rect1.length == 4</code></li> 
// <li><code>rect2.length == 4</code></li> 
// <li><code>-10<sup>9</sup> &lt;= rec1[i], rec2[i] &lt;= 10<sup>9</sup></code></li> 
// <li><code>rec1</code> 和 <code>rec2</code> 表示一个面积不为零的有效矩形</li> 
//</ul>
//
//<div><li>👍 276</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

//836.矩形重叠
//开题时间：2023-01-16 21:24:25
public class RectangleOverlap {
    public static void main(String[] args) {
        Solution solution = new RectangleOverlap().new Solution();
        System.out.println(solution.isRectangleOverlap(new int[]{7, 8, 13, 15}, new int[]{10, 8, 12, 20}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isRectangleOverlapX(int[] rec1, int[] rec2) {
            int x1 = rec1[0], y1 = rec1[1], x2 = rec1[2], y2 = rec1[3];
            int a1 = rec2[0], b1 = rec2[1], a2 = rec2[2], b2 = rec2[3];
            return (x1 <= a1 && a1 < x2 && y1 <= b1 && b1 < y2) ||
                    (x1 < a2 && a2 <= x2 && y1 <= b1 && b1 < y2) ||
                    (x1 <= a1 && a1 < x2 && y1 < b2 && b2 <= y2) ||
                    (x1 < a2 && a2 <= x2 && y1 < b2 && b2 <= y2);
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