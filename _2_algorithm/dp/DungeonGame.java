//<style> table.dungeon, .dungeon th, .dungeon td { border:3px solid black; } .dungeon th, .dungeon td { text-align: center; height: 70px; width: 70px; } </style>
//
//<p>一些恶魔抓住了公主（<strong>P</strong>）并将她关在了地下城的右下角。地下城是由&nbsp;M x N 个房间组成的二维网格。我们英勇的骑士（<strong>K</strong>）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。</p>
//
//<p>骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。</p>
//
//<p>有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为<em>负整数</em>，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 <em>0</em>），要么包含增加骑士健康点数的魔法球（若房间里的值为<em>正整数</em>，则表示骑士将增加健康点数）。</p>
//
//<p>为了尽快到达公主，骑士决定每次只向右或向下移动一步。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。</strong></p>
//
//<p>例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 <code>右 -&gt; 右 -&gt; 下 -&gt; 下</code>，则骑士的初始健康点数至少为 <strong>7</strong>。</p>
//
//<table class="dungeon"> 
// <tr> 
//  <td>-2 (K)</td> 
//  <td>-3</td> 
//  <td>3</td> 
// </tr> 
// <tr> 
//  <td>-5</td> 
//  <td>-10</td> 
//  <td>1</td> 
// </tr> 
// <tr> 
//  <td>10</td> 
//  <td>30</td> 
//  <td>-5 (P)</td> 
// </tr> 
//</table> 
//<!--2K   -3  3
//-5   -10   1
// 10 30   5P-->
//
//<p>&nbsp;</p>
//
//<p><strong>说明:</strong></p>
//
//<ul> 
// <li> <p>骑士的健康点数没有上限。</p> </li> 
// <li>任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。</li> 
//</ul>
//
//<div><li>👍 682</li><li>👎 0</li></div>
package _2_algorithm.dp;

import _3_common.tool.Tools;

// 174.地下城游戏
// 开题时间：2022-12-14 13:55:32
public class DungeonGame {
  public static void main(String[] args) {
    Solution solution = new DungeonGame().new Solution();
    System.out.println(solution.calculateMinimumHP(Tools.to2DIntArray("[[-2,-3,3],[-5,-10,1],[10,30,-5]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 左上 → 右下 （失败）
    public int calculateMinimumHPX(int[][] dungeon) {
      int m = dungeon.length + 1;
      int n = dungeon[0].length + 1;
      
      int[][] f = new int[m][n];
      for (int j = 2; j < n; j++) f[0][j] = Integer.MIN_VALUE;
      for (int i = 2; i < m; i++) f[i][0] = Integer.MIN_VALUE;
      f[1][1] = dungeon[0][0];
      
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++) {
          f[i][j] = dungeon[i - 1][j - 1] + Math.max(f[i][j - 1], f[i - 1][j]);
          if (f[i][j] <= 0)
            f[i][j] = -99_9999;
        }
      
      return f[m - 1][n - 1] >= 0 ? 1 : -f[m - 1][n - 1] + 1;
    }
    
    // 右下 → 左上 （成功）
    public int calculateMinimumHP9(int[][] dungeon) {
      int m = dungeon.length + 1;
      int n = dungeon[0].length + 1;
      
      int[][] f = new int[m][n];
      for (int j = 0; j < n - 2; j++) f[m - 1][j] = Integer.MAX_VALUE;
      for (int i = 0; i < m - 2; i++) f[i][n - 1] = Integer.MAX_VALUE;
      
      for (int i = m - 2; i >= 0; i--)
        for (int j = n - 2; j >= 0; j--)
          f[i][j] = Math.max(Math.min(f[i][j + 1], f[i + 1][j]) - dungeon[i][j], 0);
      
      return f[0][0] + 1;
    }
    
    //☆☆☆☆☆ DP（滚动数组）
    public int calculateMinimumHP(int[][] dungeon) {
      int m = dungeon.length + 1;
      int n = dungeon[0].length + 1;
      
      int[] f = new int[n];
      for (int j = 0; j < n - 2; j++) f[j] = Integer.MAX_VALUE;
      f[n - 1] = Integer.MAX_VALUE;
      
      for (int i = m - 2; i >= 0; i--)
        for (int j = n - 2; j >= 0; j--)
          f[j] = Math.max(Math.min(f[j + 1], f[j]) - dungeon[i][j], 0);
      
      return f[0] + 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}