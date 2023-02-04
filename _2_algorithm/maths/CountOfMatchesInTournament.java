//<p>给你一个整数 <code>n</code> ，表示比赛中的队伍数。比赛遵循一种独特的赛制：</p>
//
//<ul> 
// <li>如果当前队伍数是 <strong>偶数</strong> ，那么每支队伍都会与另一支队伍配对。总共进行 <code>n / 2</code> 场比赛，且产生 <code>n / 2</code> 支队伍进入下一轮。</li> 
// <li>如果当前队伍数为 <strong>奇数</strong> ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 <code>(n - 1) / 2</code> 场比赛，且产生 <code>(n - 1) / 2 + 1</code> 支队伍进入下一轮。</li> 
//</ul>
//
//<p>返回在比赛中进行的配对次数，直到决出获胜队伍为止。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>n = 7
//<strong>输出：</strong>6
//<strong>解释：</strong>比赛详情：
//- 第 1 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
//- 第 2 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
//- 第 3 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
// 总配对次数 = 3 + 2 + 1 = 6
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>n = 14
//<strong>输出：</strong>13
//<strong>解释：</strong>比赛详情：
//- 第 1 轮：队伍数 = 14 ，配对次数 = 7 ，7 支队伍晋级。
//- 第 2 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。 
//- 第 3 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
//- 第 4 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
// 总配对次数 = 7 + 3 + 2 + 1 = 13
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 200</code></li> 
//</ul>
//
//<div><li>👍 105</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

// 1688.比赛中的配对次数
// 开题时间：2022-11-14 15:27:39
public class CountOfMatchesInTournament {
  public static void main(String[] args) {
    Solution solution = new CountOfMatchesInTournament().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int numberOfMatches2(int n) {
      int matches = 0;
      while (n != 1) {
        int cur = n >> 1;
        matches += cur;
        n -= cur;
      }
      return matches;
    }
    
    // 配对次数=淘汰次数 -> 目标：淘汰 n-1 支队伍 -> 配对次数=n-1
    public int numberOfMatches(int n) {
      return n - 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}