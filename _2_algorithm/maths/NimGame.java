//<p>你和你的朋友，两个人一起玩&nbsp;<a href="https://baike.baidu.com/item/Nim游戏/6737105" target="_blank">Nim 游戏</a>：</p>
//
//<ul> 
// <li>桌子上有一堆石头。</li> 
// <li>你们轮流进行自己的回合，&nbsp;<strong>你作为先手&nbsp;</strong>。</li> 
// <li>每一回合，轮到的人拿掉&nbsp;1 - 3 块石头。</li> 
// <li>拿掉最后一块石头的人就是获胜者。</li> 
//</ul>
//
//<p>假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 <code>n</code> 的情况下赢得游戏。如果可以赢，返回 <code>true</code>；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong><span><code>n = 4</code></span>
//<strong>输出：</strong>false 
//<strong>解释：</strong>以下是可能的结果:
// 1. 移除1颗石头。你的朋友移走了3块石头，包括最后一块。你的朋友赢了。
// 2. 移除2个石子。你的朋友移走2块石头，包括最后一块。你的朋友赢了。
// 3.你移走3颗石子。你的朋友移走了最后一块石头。你的朋友赢了。
// 在所有结果中，你的朋友是赢家。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 2
//<strong>输出：</strong>true
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><li>👍 650</li><li>👎 0</li></div>
package _2_algorithm.maths;

// 292.Nim 游戏
// 开题时间：2023-01-18 15:47:06
public class NimGame {
  public static void main(String[] args) {
    Solution solution = new NimGame().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * ☆☆☆☆☆ 找规律
     * n % 4 != 0时：
     *  先手每次都拿 n' % 4 块石头
     *  轮到后手时每次都是 4k 块石头，无论他怎么拿，轮到先手时依旧是 n % 4 != 0
     *  而当轮到先手时，正好有 4 块石头时，枚举所有可能的情况，发现先手必胜
     */
    public boolean canWinNim9(int n) {
      return n % 4 != 0;
    }
    
    // 位运算优化
    public boolean canWinNim(int n) {
      return (n & 3) != 0;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}