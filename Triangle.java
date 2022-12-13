//<p>给定一个三角形 <code>triangle</code> ，找出自顶向下的最小路径和。</p>
//
//<p>每一步只能移动到下一行中相邻的结点上。<strong>相邻的结点 </strong>在这里指的是 <strong>下标</strong> 与 <strong>上一层结点下标</strong> 相同或者等于 <strong>上一层结点下标 + 1</strong> 的两个结点。也就是说，如果正位于当前行的下标 <code>i</code> ，那么下一步可以移动到下一行的下标 <code>i</code> 或 <code>i + 1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//<strong>输出：</strong>11
//<strong>解释：</strong>如下面简图所示：
//   <strong>2</strong>
//  <strong>3</strong> 4
// 6 <strong>5</strong> 7
//4 <strong>1</strong> 8 3
//自顶向下的最小路径和为&nbsp;11（即，2&nbsp;+&nbsp;3&nbsp;+&nbsp;5&nbsp;+&nbsp;1&nbsp;= 11）。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>triangle = [[-10]]
//<strong>输出：</strong>-10
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= triangle.length &lt;= 200</code></li> 
// <li><code>triangle[0].length == 1</code></li> 
// <li><code>triangle[i].length == triangle[i - 1].length + 1</code></li> 
// <li><code>-10<sup>4</sup> &lt;= triangle[i][j] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<ul> 
// <li>你可以只使用 <code>O(n)</code>&nbsp;的额外空间（<code>n</code> 为三角形的总行数）来解决这个问题吗？</li> 
//</ul>
//
//<div><li>👍 1140</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//120.三角形最小路径和
//开题时间：2022-12-13 17:15:53
public class Triangle {
    public static void main(String[] args) {
        Solution solution = new Triangle().new Solution();
        ArrayList<Integer> l1 = new ArrayList<>();
        l1.add(2);
        ArrayList<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);
        ArrayList<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(8);
        ArrayList<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        ArrayList<List<Integer>> lists = new ArrayList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        lists.add(l4);

        System.out.println(solution.minimumTotal(lists));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //region 自顶向下
        public int minimumTotal9(List<List<Integer>> triangle) {
            int m = triangle.size();

            for (int i = 1; i < m; i++)
                for (int j = 0; j <= i; j++)
                    if (i == j)
                        triangle.get(i).set(j, triangle.get(i - 1).get(j - 1) + triangle.get(i).get(j));
                    else if (j == 0)
                        triangle.get(i).set(j, triangle.get(i - 1).get(j) + triangle.get(i).get(j));
                    else
                        triangle.get(i).set(j, Math.min(triangle.get(i - 1).get(j), triangle.get(i - 1).get(j - 1)) + triangle.get(i).get(j));

            return triangle.get(m - 1).stream().min(Integer::compareTo).get();
        }

        public int minimumTotal8(List<List<Integer>> triangle) {
            int m = triangle.size();
            int[][] f = new int[m][m];
            f[0][0] = triangle.get(0).get(0);

            for (int i = 1; i < m; i++)
                for (int j = 0; j <= i; j++)
                    if (i == j)
                        f[i][j] = f[i - 1][j - 1] + triangle.get(i).get(j);
                    else if (j == 0)
                        f[i][j] = f[i - 1][j] + triangle.get(i).get(j);
                    else
                        f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + triangle.get(i).get(j);

            return Arrays.stream(f[m - 1]).min().getAsInt();
        }

        public int minimumTotal7(List<List<Integer>> triangle) {
            int m = triangle.size();
            int[] f = new int[m];
            f[0] = triangle.get(0).get(0);

            for (int i = 1; i < m; i++)
                for (int j = i; j >= 0; j--)
                    if (i == j)
                        f[j] = f[j - 1] + triangle.get(i).get(j);
                    else if (j == 0)
                        f[j] = f[j] + triangle.get(i).get(j);
                    else
                        f[j] = Math.min(f[j], f[j - 1]) + triangle.get(i).get(j);

            return Arrays.stream(f).min().getAsInt();
        }


        //自顶向下+滚动数组
        public int minimumTotal(List<List<Integer>> triangle) {
            int m = triangle.size();
            int[] f = new int[m];
            f[0] = triangle.get(0).get(0);

            for (int i = 1; i < m; i++) {
                f[i] = f[i - 1] + triangle.get(i).get(i);
                for (int j = i - 1; j > 0; j--)
                    f[j] = Math.min(f[j], f[j - 1]) + triangle.get(i).get(j);
                f[0] += triangle.get(i).get(0);
            }

            return Arrays.stream(f).min().getAsInt();
        }
        //endregion

        //region 自底向上
        public int minimumTotal6(List<List<Integer>> triangle) {
            int m = triangle.size() + 1;
            int[][] f = new int[m][m];

            for (int i = m - 2; i >= 0; i--)
                for (int j = 0; j <= i; j++)
                    f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle.get(i).get(j);

            return f[0][0];
        }

        //☆☆☆☆☆ 自底向上+滚动数组
        public int minimumTotal5(List<List<Integer>> triangle) {
            int m = triangle.size() + 1;
            int[] f = new int[m];

            for (int i = m - 2; i >= 0; i--)
                for (int j = 0; j <= i; j++)
                    f[j] = Math.min(f[j], f[j + 1]) + triangle.get(i).get(j);

            return f[0];
        }
        //endregion
    }
//leetcode submit region end(Prohibit modification and deletion)
}