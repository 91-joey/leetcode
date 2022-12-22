//<p>给你一个整数数组 <code>nums</code> 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。</p>
//
//<p>玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 <code>0</code> 。每一回合，玩家从数组的任意一端取一个数字（即，<code>nums[0]</code> 或 <code>nums[nums.length - 1]</code>），取到的数字将会从数组中移除（数组长度减 <code>1</code> ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。</p>
//
//<p>如果玩家 1 能成为赢家，返回 <code>true</code> 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 <code>true</code> 。你可以假设每个玩家的玩法都会使他的分数最大化。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,5,2]
//<strong>输出：</strong>false
//<strong>解释：</strong>一开始，玩家 1 可以从 1 和 2 中进行选择。
//如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。 
//所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
//因此，玩家 1 永远不会成为赢家，返回 false 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,5,233,7]
//<strong>输出：</strong>true
//<strong>解释：</strong>玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
//最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 20</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li> 
//</ul>
//
//<div><li>👍 594</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//486.预测赢家
//开题时间：2022-12-22 16:08:46
public class PredictTheWinner {
    public static void main(String[] args) {
        Solution solution = new PredictTheWinner().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //递归
        public boolean PredictTheWinner9(int[] nums) {
            return helper(nums, 0, nums.length - 1) >= 0;
        }

        //当前玩家 在 当前数组区段上 所能获得的与另一个玩家的最大分数差
        private int helper(int[] nums, int l, int r) {
            if (l == r)
                return nums[l];
            return Math.max(
                    nums[l] - helper(nums, l + 1, r),
                    nums[r] - helper(nums, l, r - 1)
            );
        }

        int[][] memo;

        //记忆化递归
        public boolean PredictTheWinner8(int[] nums) {
            int n = nums.length;
            memo = new int[n][n];
            for (int[] arr : memo)
                Arrays.fill(arr, -1);
            return helper2(nums, 0, n - 1) >= 0;
        }

        private int helper2(int[] nums, int l, int r) {
            if (l == r)
                return nums[l];
            if (memo[l][r] != -1)
                return memo[l][r];
            memo[l][r] = Math.max(
                    nums[l] - helper(nums, l + 1, r),
                    nums[r] - helper(nums, l, r - 1)
            );
            return memo[l][r];
        }

        //DP
        public boolean PredictTheWinner7(int[] nums) {
            int n = nums.length;
            int[][] f = new int[n][n];

            for (int i = n - 1; i >= 0; i--) {
                f[i][i] = nums[i];
                for (int j = i + 1; j < n; j++)
                    f[i][j] = Math.max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
            }

            return f[0][n - 1] >= 0;
        }

        //☆☆☆☆☆ DP（滚动数组）
        public boolean PredictTheWinner(int[] nums) {
            int n = nums.length;
            int[] f = new int[n];

            for (int i = n - 1; i >= 0; i--) {
                f[i] = nums[i];
                for (int j = i + 1; j < n; j++)
                    f[j] = Math.max(nums[i] - f[j], nums[j] - f[j - 1]);
            }

            return f[n - 1] >= 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}