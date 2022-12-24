//<p>给你一个整数数组 <code>cost</code> ，其中 <code>cost[i]</code> 是从楼梯第 <code>i</code> 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。</p>
//
//<p>你可以选择从下标为 <code>0</code> 或下标为 <code>1</code> 的台阶开始爬楼梯。</p>
//
//<p>请你计算并返回达到楼梯顶部的最低花费。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>cost = [10,<em><strong>15</strong></em>,20]
//<strong>输出：</strong>15
//<strong>解释：</strong>你将从下标为 1 的台阶开始。
//- 支付 15 ，向上爬两个台阶，到达楼梯顶部。
//总花费为 15 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>cost = [<em><strong>1</strong></em>,100,<em><strong>1</strong></em>,1,<em><strong>1</strong></em>,100,<em><strong>1</strong></em>,<em><strong>1</strong></em>,100,<em><strong>1</strong></em>]
//<strong>输出：</strong>6
//<strong>解释：</strong>你将从下标为 0 的台阶开始。
//- 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
//- 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
//- 支付 1 ，向上爬一个台阶，到达楼梯顶部。
//总花费为 6 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= cost.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= cost[i] &lt;= 999</code></li> 
//</ul>
//
//<div><li>👍 1082</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

//746.使用最小花费爬楼梯
//开题时间：2022-12-24 21:46:01
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new MinCostClimbingStairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCostClimbingStairs9(int[] cost) {
            int n = cost.length;
            int[] f = new int[n];
            f[0] = cost[0];
            f[1] = cost[1];
            for (int i = 2; i < n; i++)
                f[i] = Math.min(f[i - 2], f[i - 1]) + cost[i];
            return Math.min(f[n - 2], f[n - 1]);
        }

        //DP
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] f = new int[n];
            int a = cost[0];
            int b = cost[1];
            for (int i = 2; i < n; i++) {
                int tmp = b;
                b = Math.min(a, b) + cost[i];
                a = tmp;
            }
            return Math.min(a, b);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}