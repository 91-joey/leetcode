//<p>给定一个二维网格&nbsp;<code>grid</code>&nbsp;，其中：</p>
//
//<ul> 
// <li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'.'</span></span></font></font> 代表一个空房间</li> 
// <li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'#'</span></span></font></font> 代表一堵</li> 
// <li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'@'</span></span></font></font>&nbsp;是起点</li> 
// <li>小写字母代表钥匙</li> 
// <li>大写字母代表锁</li> 
//</ul>
//
//<p>我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。</p>
//
//<p>假设 k&nbsp;为 钥匙/锁 的个数，且满足&nbsp;<code>1 &lt;= k&nbsp;&lt;= 6</code>，字母表中的前 <code>k</code>&nbsp;个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。</p>
//
//<p>返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回&nbsp;<code>-1</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/07/23/lc-keys2.jpg" /></p>
//
//<pre>
//<strong>输入：</strong>grid = ["@.a.#","###.#","b.A.B"]
//<strong>输出：</strong>8
//<strong>解释：</strong>目标是获得所有钥匙，而不是打开所有锁。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/07/23/lc-key2.jpg" /></p>
//
//<pre>
//<strong>输入：</strong>grid = ["@..aA","..B#.","....b"]
//<strong>输出：</strong>6
//</pre>
//
//<p><strong>示例 3:</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/07/23/lc-keys3.jpg" /> 
//<pre>
//<strong>输入:</strong> grid = ["@Aa"]
//<strong>输出:</strong> -1</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>m == grid.length</code></li> 
// <li><code>n == grid[i].length</code></li> 
// <li><code>1 &lt;= m, n &lt;= 30</code></li> 
// <li><code>grid[i][j]</code>&nbsp;只含有&nbsp;<code>'.'</code>,&nbsp;<code>'#'</code>,&nbsp;<code>'@'</code>,&nbsp;<code>'a'-</code><code>'f</code><code>'</code>&nbsp;以及&nbsp;<code>'A'-'F'</code></li> 
// <li>钥匙的数目范围是&nbsp;<code>[1, 6]</code>&nbsp;</li> 
// <li>每个钥匙都对应一个 <strong>不同</strong> 的字母</li> 
// <li>每个钥匙正好打开一个对应的锁</li> 
//</ul>
//
//<div><li>👍 138</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//864.获取所有钥匙的最短路径
//开题时间：2022-11-10 09:10:05
public class ShortestPathToGetAllKeys {
    public static void main(String[] args) {
        Solution solution = new ShortestPathToGetAllKeys().new Solution();
        System.out.println(solution.shortestPathAllKeys(new String[]{
                "@...a",
                ".###A",
                "b.BCc"}));
//        System.out.println(solution.shortestPathAllKeys(new String[]{
//                "@.a.#",
//                "###.#",
//                "b.A.B"}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public static final char ROOM = '.';
        public static final char WALL = '#';
        public static final char START = '@';
        public final int[][] DIRECTIONS = {
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };

        public static final int[] DIRS = {-1, 0, 1, 0, -1};

        public int shortestPathAllKeys9(String[] grid) {
            HashSet<Character> keysNotHave = new HashSet<>();
            int m = grid.length;
            int n = grid[0].length();
            char[][] chars = new char[m][n];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++) {
                    char c = grid[i].charAt(j);
                    chars[i][j] = c;
                    if (Character.isLowerCase(c))
                        keysNotHave.add(c);
                    else if (c == START)
                        q.offer(new int[]{i, j});
                }

//            HashSet<Integer> visited = new HashSet<>();
            int step = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] poll = q.poll();
//                    visited.add(30 * poll[0] + poll[1]);
                    for (int j = 0; j < DIRECTIONS.length; j++) {
                        int r = poll[0] + DIRECTIONS[j][0];
                        int c = poll[1] + DIRECTIONS[j][1];
                        char ch;
                        if (0 <= r && r < m && 0 <= c && c < n &&
//                                !visited.contains(30 * r + c) &&
                                (ch = chars[r][c]) != WALL) {
                            if (Character.isLowerCase(ch))
                                keysNotHave.remove(ch);
                            else if (ch != ROOM) {
                                if (keysNotHave.contains(Character.toLowerCase(ch)))
                                    continue;
                            }
                            if (keysNotHave.isEmpty())
                                return step;
                            q.offer(new int[]{r, c});
                        }
                    }
                }
                step++;
            }

            return -1;
        }

        public int shortestPathAllKeys8(String[] grid) {
            int mask = 0;
            int m = grid.length;
            int n = grid[0].length();
            char[][] chars = new char[m][n];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++) {
                    char c = grid[i].charAt(j);
                    chars[i][j] = c;
                    if (Character.isLowerCase(c))
                        mask |= 1 << (c - 'a');
                    else if (c == START)
                        q.offer(new int[]{i, j, 0});
                }

            HashSet<Integer> visited = new HashSet<>();
            int step = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] poll = q.poll();
                    visited.add((poll[2] << 10) + (poll[0] << 5) + poll[1]);
                    for (int j = 0; j < DIRECTIONS.length; j++) {
                        int r = poll[0] + DIRECTIONS[j][0];
                        int c = poll[1] + DIRECTIONS[j][1];
                        int keys = poll[2];
                        char ch;
                        if (0 <= r && r < m && 0 <= c && c < n &&
                                !visited.contains((keys << 10) + (r << 5) + c) &&
                                (ch = chars[r][c]) != WALL) {
                            if (Character.isLowerCase(ch))
                                keys |= 1 << (ch - 'a');
                            else if (Character.isUpperCase(ch)) {
                                if ((keys >> (Character.toLowerCase(ch) - 'a') & 1) == 0)
                                    continue;
                            }
                            if (keys == mask)
                                return step;
                            q.offer(new int[]{r, c, keys});
                        }
                    }
                }
                step++;
            }

            return -1;
        }

        public int shortestPathAllKeys(String[] grid) {
            int k = 0, startR = 0, startC = 0;
            int m = grid.length, n = grid[0].length();
            char[][] chars = new char[m][n];
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++) {
                    char c = grid[i].charAt(j);
                    chars[i][j] = c;
                    if (Character.isLowerCase(c))
                        k++;
                    else if (c == START) {
                        startR = i;
                        startC = j;
                        q.offer(new int[]{i, j, 0});
                    }
                }

            int stateSize = 1 << k;
            int mask = stateSize - 1;
            boolean[][][] visited = new boolean[m][n][stateSize];
            visited[startR][startC][0] = true;
            int step = 1;
            while (!q.isEmpty()) {
                for (int i = q.size(); i > 0; i--) {
                    int[] poll = q.poll();
                    for (int j = 0; j < 4; j++) {
                        int r = poll[0] + DIRS[j];
                        int c = poll[1] + DIRS[j + 1];
                        int keys = poll[2];
                        char ch;
                        if (0 <= r && r < m && 0 <= c && c < n &&
                                (ch = chars[r][c]) != WALL) {
                            if (Character.isUpperCase(ch)) {
                                if ((keys >> (ch - 'A') & 1) == 0)
                                    continue;
                            } else if (Character.isLowerCase(ch)) {
                                keys |= 1 << (ch - 'a');
                                if (keys == mask)
                                    return step;
                            }
                            if (!visited[r][c][keys]) {
                                visited[r][c][keys] = true;
                                q.offer(new int[]{r, c, keys});
                            }
                        }
                    }
                }
                step++;
            }

            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}