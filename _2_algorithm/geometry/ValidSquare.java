//<p>给定2D空间中四个点的坐标&nbsp;<code>p1</code>,&nbsp;<code>p2</code>,&nbsp;<code>p3</code>&nbsp;和&nbsp;<code>p4</code>，如果这四个点构成一个正方形，则返回 <code>true</code> 。</p>
//
//<p>点的坐标&nbsp;<code>p<sub>i</sub></code> 表示为 <code>[xi, yi]</code> 。 <code>输入没有任何顺序</code> 。</p>
//
//<p>一个 <strong>有效的正方形</strong> 有四条等边和四个等角(90度角)。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
//<strong>输出:</strong> True
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入：</strong>p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
//<b>输出：</b>false
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<b>输入：</b>p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
//<b>输出：</b>true
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>p1.length == p2.length == p3.length == p4.length == 2</code></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 167</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.geometry;

import java.util.HashSet;
import java.util.List;

//593.有效的正方形
//开题时间：2023-01-17 16:54:38
public class ValidSquare {
    public static void main(String[] args) {
        Solution solution = new ValidSquare().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //☆☆☆☆☆ 判断每三个点是否能构成直角三角形（一共4个）
        public boolean validSquare9(int[] p1, int[] p2, int[] p3, int[] p4) {
            return validRt(p1, p2, p3)
                    && validRt(p2, p3, p4)
                    && validRt(p3, p4, p1)
                    && validRt(p4, p1, p2);
        }

        /**
         * 中心旋转法（逆时针旋转90°（180°、270°也是可以的））
         *  1.4点的中心点移到原点，4点跟着移动
         *  2.每个点旋转90°后与原来4点中的其中一点重合，则会有效的正方形
         */
        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            int midX = p1[0] + p2[0] + p3[0] + p4[0];
            int midY = p1[1] + p2[1] + p3[1] + p4[1];

            HashSet<Point> set = new HashSet<>() {{
                List.of(p1, p2, p3, p4).forEach(p -> add(center2orign(p, midX, midY)));
            }};

            if (set.size() != 4)
                return false;

            return set.stream().allMatch(p -> set.contains(new Point(-p.y, p.x)));
        }

        private Point center2orign(int[] p, int midX, int midY) {
            return new Point(p[0] * 4 - midX, p[1] * 4 - midY);
        }

        /**
         * 验证直角三角形（right-angled triangle）
         * 注意不能存在点重叠的情况（即边长为0）
         */
        private boolean validRt(int[] a, int[] b, int[] c) {
            int sqLen1 = getSqLen(a, b);
            int sqLen2 = getSqLen(a, c);
            int sqLen3 = getSqLen(b, c);

            int min = Math.min(Math.min(sqLen1, sqLen2), sqLen3);
            int max = Math.max(Math.max(sqLen1, sqLen2), sqLen3);

            return min * 2 == max && min != 0;
        }

        private int getSqLen(int[] a, int[] b) {
            int deltaX = a[0] - b[0];
            int deltaY = a[1] - b[1];
            return deltaX * deltaX + deltaY * deltaY;
        }
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}