//<p>给你一个坐标&nbsp;<code>coordinates</code>&nbsp;，它是一个字符串，表示国际象棋棋盘中一个格子的坐标。下图是国际象棋棋盘示意图。</p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/03/chessboard.png" style="width: 400px; height: 396px;" /></p>
//
//<p>如果所给格子的颜色是白色，请你返回&nbsp;<code>true</code>，如果是黑色，请返回&nbsp;<code>false</code>&nbsp;。</p>
//
//<p>给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>coordinates = "a1"
//<b>输出：</b>false
//<b>解释：</b>如上图棋盘所示，"a1" 坐标的格子是黑色的，所以返回 false 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>coordinates = "h3"
//<b>输出：</b>true
//<b>解释：</b>如上图棋盘所示，"h3" 坐标的格子是白色的，所以返回 true 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<b>输入：</b>coordinates = "c7"
//<b>输出：</b>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>coordinates.length == 2</code></li> 
// <li><code>'a' &lt;= coordinates[0] &lt;= 'h'</code></li> 
// <li><code>'1' &lt;= coordinates[1] &lt;= '8'</code></li> 
//</ul>
//
//<div><li>👍 21</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

// 1812.判断国际象棋棋盘中一个格子的颜色
// 开题时间：2022-12-08 08:51:48
public class DetermineColorOfAChessboardSquare {
  public static void main(String[] args) {
    Solution solution = new DetermineColorOfAChessboardSquare().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean squareIsWhite9(String coordinates) {
      return (((coordinates.charAt(0) - 'a') ^ (coordinates.charAt(1) - '0')) & 1) == 0;
    }
    
    public boolean squareIsWhite8(String coordinates) {
      return (((coordinates.charAt(0) - 'a') ^ (coordinates.charAt(1) - '1')) & 1) == 1;
    }
    
    public boolean squareIsWhite(String coordinates) {
      return ((coordinates.charAt(0) - 'a') & 1) ==
          ((coordinates.charAt(1) - '1') & 1);
    }
    
    public static final boolean[][] chessboard = {
        {false, true, false, true, false, true, false, true},
        {true, false, true, false, true, false, true, false},
        {false, true, false, true, false, true, false, true},
        {true, false, true, false, true, false, true, false},
        {false, true, false, true, false, true, false, true},
        {true, false, true, false, true, false, true, false},
        {false, true, false, true, false, true, false, true},
        {true, false, true, false, true, false, true, false},
    };
    
    public boolean squareIsWhite7(String coordinates) {
      return chessboard[coordinates.charAt(0) - 'a'][coordinates.charAt(1) - '1'];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}