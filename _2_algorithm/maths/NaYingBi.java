//<p>桌上有 <code>n</code> 堆力扣币，每堆的数量保存在数组 <code>coins</code> 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。</p>
//
//<p><strong>示例 1：</strong></p>
//
//<blockquote> 
// <p>输入：<code>[4,2,1]</code></p> 
//</blockquote>
//
//<p>输出：<code>4</code></p>
//
//<p>解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。</p>
//
//<p><strong>示例 2：</strong></p>
//
//<blockquote> 
// <p>输入：<code>[2,3,10]</code></p> 
//</blockquote>
//
//<p>输出：<code>8</code></p>
//
//<p><strong>限制：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 4</code></li> 
// <li><code>1 &lt;= coins[i] &lt;= 10</code></li> 
//</ul>
//
//<div><li>👍 56</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

// LCP 06.拿硬币
// 开题时间：2022-11-14 15:41:12
public class NaYingBi {
  public static void main(String[] args) {
    Solution solution = new NaYingBi().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int minCount(int[] coins) {
      int cnt = 0;
      for (int coin : coins)
        cnt += (coin + 1) >> 1;
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}