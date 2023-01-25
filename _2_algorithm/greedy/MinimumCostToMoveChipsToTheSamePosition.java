//<p>有&nbsp;<code>n</code>&nbsp;个筹码。第 <code>i</code> 个筹码的位置是
// <meta charset="UTF-8" />&nbsp;<code>position[i]</code>&nbsp;。</p>
//
//<p>我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 <code>i</code> 个筹码的位置从&nbsp;<code>position[i]</code>&nbsp;改变为:</p>
//
//<p>
// <meta charset="UTF-8" /></p>
//
//<ul> 
// <li><code>position[i] + 2</code>&nbsp;或&nbsp;<code>position[i] - 2</code>&nbsp;，此时&nbsp;<code>cost = 0</code></li> 
// <li><code>position[i] + 1</code>&nbsp;或&nbsp;<code>position[i] - 1</code>&nbsp;，此时&nbsp;<code>cost = 1</code></li> 
//</ul>
//
//<p>返回将所有筹码移动到同一位置上所需要的 <em>最小代价</em> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/08/15/chips_e1.jpg" style="height: 217px; width: 750px;" /></p>
//
//<pre>
//<strong>输入：</strong>position = [1,2,3]
//<strong>输出：</strong>1
//<strong>解释：</strong>第一步:将位置3的筹码移动到位置1，成本为0。
//第二步:将位置2的筹码移动到位置1，成本= 1。
//总成本是1。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/08/15/chip_e2.jpg" style="height: 306px; width: 750px;" /></p>
//
//<pre>
//<strong>输入：</strong>position = [2,2,2,3,3]
//<strong>输出：</strong>2
//<strong>解释：</strong>我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入：</strong>position = [1,1000000000]
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= position.length &lt;= 100</code></li> 
// <li><code>1 &lt;= position[i] &lt;= 10^9</code></li> 
//</ul>
//
//<div><li>👍 196</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

//1217.玩筹码
//开题时间：2022-12-27 10:05:18
public class MinimumCostToMoveChipsToTheSamePosition {
    public static void main(String[] args) {
        Solution solution = new MinimumCostToMoveChipsToTheSamePosition().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //贪心（统计奇偶数个数，取较小值）
        public int minCostToMoveChips(int[] position) {
            int odd = 0;
            for (int x : position)
                if ((x & 1) == 1)
                    odd++;
            return Math.min(odd, position.length - odd);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}