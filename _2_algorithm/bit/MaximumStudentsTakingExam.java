//<p>给你一个&nbsp;<code>m&nbsp;* n</code>&nbsp;的矩阵 <code>seats</code>&nbsp;表示教室中的座位分布。如果座位是坏的（不可用），就用&nbsp;<code>'#'</code>&nbsp;表示；否则，用&nbsp;<code>'.'</code>&nbsp;表示。</p>
//
//<p>学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的一起参加考试且无法作弊的最大学生人数。</p>
//
//<p>学生必须坐在状况良好的座位上。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/02/09/image.png" style="height: 197px; width: 339px;" /></p>
//
//<pre><strong>输入：</strong>seats = [["#",".","#","#",".","#"],
//&nbsp;             [".","#","#","#","#","."],
//&nbsp;             ["#",".","#","#",".","#"]]
//<strong>输出：</strong>4
//<strong>解释：</strong>教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。 
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>seats = [[".","#"],
//&nbsp;             ["#","#"],
//&nbsp;             ["#","."],
//&nbsp;             ["#","#"],
//&nbsp;             [".","#"]]
//<strong>输出：</strong>3
//<strong>解释：</strong>让所有学生坐在可用的座位上。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>seats = [["#",".","<strong>.</strong>",".","#"],
//&nbsp;             ["<strong>.</strong>","#","<strong>.</strong>","#","<strong>.</strong>"],
//&nbsp;             ["<strong>.</strong>",".","#",".","<strong>.</strong>"],
//&nbsp;             ["<strong>.</strong>","#","<strong>.</strong>","#","<strong>.</strong>"],
//&nbsp;             ["#",".","<strong>.</strong>",".","#"]]
//<strong>输出：</strong>10
//<strong>解释：</strong>让学生坐在第 1、3 和 5 列的可用座位上。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>seats</code>&nbsp;只包含字符&nbsp;<code>'.'&nbsp;和</code><code>'#'</code></li> 
// <li><code>m ==&nbsp;seats.length</code></li> 
// <li><code>n ==&nbsp;seats[i].length</code></li> 
// <li><code>1 &lt;= m &lt;= 8</code></li> 
// <li><code>1 &lt;= n &lt;= 8</code></li> 
//</ul>
//
//<div><li>👍 152</li><li>👎 0</li></div>
package _2_algorithm.bit;

import java.util.Arrays;

// 1349.参加考试的最大学生数
// 开题时间：2023-01-13 15:42:35
public class MaximumStudentsTakingExam {
  public static void main(String[] args) {
    Solution solution = new MaximumStudentsTakingExam().new Solution();
    System.out.println(solution.maxStudents(new char[][]{
        {'.', '#', '#', '.'},
        {'.', '.', '.', '#'},
        {'.', '.', '.', '.'},
        {'#', '.', '#', '#'}
    }));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxStudents9(char[][] seats) {
      int m = seats.length;
      int n = seats[0].length;
      int bound = 1 << n;
      int[][] f = new int[m + 1][bound];
      
      for (int i = 1; i < m + 1; i++)
        for (int j = 0; j < bound; j++)
          if ((j & (j << 1)) == 0 && (j & (j >> 1)) == 0 && isNotBroken(j, seats[i - 1]))
            for (int k = 0; k < bound; k++)
              if ((j & (k << 1)) == 0 && (j & (k >> 1)) == 0)
                f[i][j] = Math.max(f[i][j], f[i - 1][k] + Integer.bitCount(j));
      
      return Arrays.stream(f[m]).max().getAsInt();
    }
    
    private boolean isNotBroken(int state, char[] seat) {
      for (int i = 0; i < seat.length; i++) {
        if ((state & 1) == 1 && seat[i] == '#')
          return false;
        state >>= 1;
      }
      return true;
    }
    
    /*
     * ☆☆☆☆☆ 状压dp + 预处理位1数量
     * 状态定义：f[i][state] 表示第 i 行、就座状态为 state 时前 i 行的最大学生数
     * 状态转移：f[i][j] = max(f[i-1][k]+bitCount(j))
     *      f[i][j]、f[i-1][k]均为有效就座状态
     *          1.不能坐在坏座位上
     *          2.左右两侧不能就座（当前行不能相邻而坐）
     *          3.左上、右上不能就座
     * 小技巧：运用哨兵思想，设置第 0 行的所有状态值为 0
     * 最终结果：max(f[m])
     */
    public int maxStudents(char[][] seats) {
      int m = seats.length;
      int n = seats[0].length;
      int bound = 1 << n;
      int[][] f = new int[m + 1][bound];
      int[] bitCount = new int[bound];
      for (int i = 1; i < bound; i++)
        bitCount[i] = bitCount[i & (i - 1)] + 1;
      
      for (int i = 1; i < m + 1; i++) {
        // 每行座位的好坏（好为1，坏为0）
        int validSeats = 0;
        for (int j = 0; j < n; j++)
          if (seats[i - 1][j] == '.')
            validSeats |= (1 << j);
        for (int j = 0; j < bound; j++) {
          int adjacentMask1 = j << 1;
          // 无效状态：1.学生相邻而坐 或 2.学生坐在坏座位上
          if ((j & adjacentMask1) != 0 || (j | validSeats) != validSeats) {
            f[i][j] = -1;
            continue;
          }
          int adjacentMask2 = j >> 1;
          for (int k = 0; k < bound; k++)
            // 上一行的就座状态有效，且当前行的学生的左上、右上没有学生就座
            if (f[i - 1][k] != -1 && (k & adjacentMask1) == 0 && (k & adjacentMask2) == 0)
              f[i][j] = Math.max(f[i][j], f[i - 1][k] + bitCount[j]);
        }
      }
      
      return Arrays.stream(f[m]).max().getAsInt();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}