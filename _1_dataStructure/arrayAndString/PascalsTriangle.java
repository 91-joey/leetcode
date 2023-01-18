//<p>给定一个非负整数&nbsp;<em><code>numRows</code>，</em>生成「杨辉三角」的前&nbsp;<em><code>numRows</code>&nbsp;</em>行。</p>
//
//<p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>
//
//<p><img alt="" src="https://pic.leetcode-cn.com/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> numRows = 5
//<strong>输出:</strong> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入:</strong> numRows = 1
//<strong>输出:</strong> [[1]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= numRows &lt;= 30</code></li> 
//</ul>
//
//<div><li>👍 878</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.List;

//118.杨辉三角
//开题时间：2022-12-01 12:03:40
class PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new PascalsTriangle().new Solution();
        System.out.println(solution.generate(5));
//        System.out.println(solution.generate(5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate9(int numRows) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(List.of(1));
            for (int i = 1; i < numRows; i++) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(1);
                for (int j = 1; j < i; j++) {
                    List<Integer> pre = ans.get(i - 1);
                    list.add(pre.get(j - 1) + pre.get(j));
                }
                list.add(1);
                ans.add(list);
            }
            return ans;
        }

        //GJ
        public List<List<Integer>> generate8(int numRows) {
            List<List<Integer>> list = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i)
                        row.add(1);
                    else {
                        List<Integer> pre = list.get(i - 1);
                        row.add(pre.get(j - 1) + pre.get(j));
                    }
                }
                list.add(row);
            }
            return list;
        }

        //☆☆☆☆☆ 每一行是对称的，左半边计算完后，右半边对称复制即可
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(List.of(1));
            for (int i = 1; i < numRows; i++) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(1);
                List<Integer> pre = ans.get(i - 1);
                for (int j = 1; j < (i + 2) / 2; j++)
                    list.add(pre.get(j - 1) + pre.get(j));
                for (int j = (i + 2) / 2; j < i; j++)
                    list.add(list.get(i - j));
                list.add(1);
                ans.add(list);
            }
            return ans;
        }

        //1.自解    n^2 1
        public List<List<Integer>> generate1(int numRows) {
            List<List<Integer>> rows = new ArrayList<>(numRows);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            rows.add(row);
            if (numRows > 1) {
                row = new ArrayList<>();
                row.add(1);
                row.add(1);
                rows.add(row);
            }
            for (int i = 2; i < numRows; i++) {
                row = new ArrayList<>();
                row.add(1);
                row.add(i);
                for (int j = 2; j <= i / 2; j++) {
                    List<Integer> lastRow = rows.get(i - 1);
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
                int size = row.size();
                if ((i + 1) % 2 == 0) {
                    row.add(row.get(size - 1));
                }
                for (int j = size - 2; j >= 0; j--) {
                    row.add(row.get(j));
                }
                rows.add(row);
            }
            return rows;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}