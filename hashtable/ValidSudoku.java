//<p>请你判断一个&nbsp;<code>9 x 9</code> 的数独是否有效。只需要<strong> 根据以下规则</strong> ，验证已经填入的数字是否有效即可。</p>
//
//<ol> 
// <li>数字&nbsp;<code>1-9</code>&nbsp;在每一行只能出现一次。</li> 
// <li>数字&nbsp;<code>1-9</code>&nbsp;在每一列只能出现一次。</li> 
// <li>数字&nbsp;<code>1-9</code>&nbsp;在每一个以粗实线分隔的&nbsp;<code>3x3</code>&nbsp;宫内只能出现一次。（请参考示例图）</li> 
//</ol>
//
//<p>&nbsp;</p>
//
//<p><strong>注意：</strong></p>
//
//<ul> 
// <li>一个有效的数独（部分已被填充）不一定是可解的。</li> 
// <li>只需要根据以上规则，验证已经填入的数字是否有效即可。</li> 
// <li>空白格用&nbsp;<code>'.'</code>&nbsp;表示。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714svg.png" style="height:250px; width:250px" /> 
//<pre>
//<strong>输入：</strong>board = 
//[["5","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>board = 
//[["8","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//<strong>输出：</strong>false
//<strong>解释：</strong>除了第一行的第一个数字从<strong> 5</strong> 改为 <strong>8 </strong>以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>board.length == 9</code></li> 
// <li><code>board[i].length == 9</code></li> 
// <li><code>board[i][j]</code> 是一位数字（<code>1-9</code>）或者 <code>'.'</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>矩阵</li></div></div><br><div><li>👍 949</li><li>👎 0</li></div>
package org.example.leetcode.problems.hashtable;

import java.util.HashSet;
import java.util.Set;

//36.有效的数独
//开题时间：2022-09-08 12:27:06
public class ValidSudoku {
    public static void main(String[] args) {
        Solution solution = new ValidSudoku().new Solution();
        System.out.println(solution.isValidSudoku4(new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力解 m*n*(m+n+z)
        public boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        for (int k = 0; k < 9; k++) {
                            if (k != i && board[i][j] == board[k][j]) return false;
                        }
                        for (int k = 0; k < 9; k++) {
                            if (k != j && board[i][j] == board[i][k]) return false;
                        }
                        for (int m = i / 3 * 3; m < i / 3 * 3 + 3; m++) {
                            for (int n = j / 3 * 3; n < j / 3 * 3 + 3; n++) {
                                if (m != i && n != j && board[i][j] == board[m][n]) return false;
                            }
                        }
                    }
                }
            }
            return true;
        }

        //hashset m*n*?
        public boolean isValidSudoku2(char[][] board) {
            //每行查重
            for (int i = 0; i < 9; i++) {
                Set<Character> set = new HashSet<>();
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.' && !set.add(board[i][j])) {
                        return false;
                    }
                }
            }
            //每列查重
            for (int i = 0; i < 9; i++) {
                Set<Character> set = new HashSet<>();
                for (int j = 0; j < 9; j++) {
                    if (board[j][i] != '.' && !set.add(board[j][i])) {
                        return false;
                    }
                }
            }
            //每个九宫格查重
            for (int i = 0; i <= 6; i += 3) {
                for (int j = 0; j <= 6; j += 3) {
                    Set<Character> set = new HashSet<>();
                    for (int m = i; m < i + 3; m++) {
                        for (int n = j; n < j + 3; n++) {
                            if (board[m][n] != '.' && !set.add(board[m][n])) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }

        //数组
        public boolean isValidSudoku3(char[][] board) {
            //每行查重
            for (int i = 0; i < 9; i++) {
                boolean[] exists = new boolean[9];
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        if (exists[board[i][j] - '1']) {
                            return false;
                        } else {
                            exists[board[i][j] - '1'] = true;
                        }
                    }
                }
            }
            //每列查重
            for (int i = 0; i < 9; i++) {
                boolean[] exists = new boolean[9];
                for (int j = 0; j < 9; j++) {
                    if (board[j][i] != '.') {
                        if (exists[board[j][i] - '1']) {
                            return false;
                        } else {
                            exists[board[j][i] - '1'] = true;
                        }
                    }
                }
            }
            //每个九宫格查重
            for (int i = 0; i <= 6; i += 3) {
                for (int j = 0; j <= 6; j += 3) {
                    boolean[] exists = new boolean[9];
                    for (int m = i; m < i + 3; m++) {
                        for (int n = j; n < j + 3; n++) {
                            if (board[m][n] != '.') {
                                if (exists[board[m][n] - '1']) {
                                    return false;
                                } else {
                                    exists[board[m][n] - '1'] = true;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

        //数组优化
        public boolean isValidSudoku3Upgrade(char[][] board) {
            boolean[][] rows = new boolean[9][9];
            boolean[][] cols = new boolean[9][9];
            boolean[][][] grids = new boolean[3][3][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int idx = board[i][j] - '1';
                        if (rows[i][idx] || cols[j][idx] || grids[i / 3][j / 3][idx]) return false;
                        rows[i][idx] = cols[j][idx] = grids[i / 3][j / 3][idx] = true;
                    }
                }
            }
            return true;
        }

        //☆☆☆☆☆位运算
        public boolean isValidSudoku4(char[][] board) {
            int[] rows = new int[9];
            int[] cols = new int[9];
            int[][] grids = new int[3][3];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int idx = board[i][j] - '1';
                        if (((rows[i] >> idx & 1) == 1) ||
                                ((cols[j] >> idx & 1) == 1) ||
                                ((grids[i / 3][j / 3] >> idx & 1) == 1))
                            return false;
                        int updater = 1 << idx;
                        rows[i] |= updater;
                        cols[j] |= updater;
                        grids[i / 3][j / 3] |= updater;
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}