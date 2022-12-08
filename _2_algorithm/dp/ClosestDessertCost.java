//<p>你打算做甜点，现在需要购买配料。目前共有 <code>n</code> 种冰激凌基料和 <code>m</code> 种配料可供选购。而制作甜点需要遵循以下几条规则：</p>
//
//<ul> 
// <li>必须选择 <strong>一种</strong> 冰激凌基料。</li> 
// <li>可以添加 <strong>一种或多种</strong> 配料，也可以不添加任何配料。</li> 
// <li>每种类型的配料 <strong>最多两份</strong> 。</li> 
//</ul>
//
//<p>给你以下三个输入：</p>
//
//<ul> 
// <li><code>baseCosts</code> ，一个长度为 <code>n</code> 的整数数组，其中每个 <code>baseCosts[i]</code> 表示第 <code>i</code> 种冰激凌基料的价格。</li> 
// <li><code>toppingCosts</code>，一个长度为 <code>m</code> 的整数数组，其中每个 <code>toppingCosts[i]</code> 表示 <strong>一份</strong> 第 <code>i</code> 种冰激凌配料的价格。</li> 
// <li><code>target</code> ，一个整数，表示你制作甜点的目标价格。</li> 
//</ul>
//
//<p>你希望自己做的甜点总成本尽可能接近目标价格 <code>target</code> 。</p>
//
//<p>返回最接近<em> </em><code>target</code> 的甜点成本。如果有多种方案，返回&nbsp;<strong>成本相对较低</strong> 的一种。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>baseCosts = [1,7], toppingCosts = [3,4], target = 10
//<strong>输出：</strong>10
//<strong>解释：</strong>考虑下面的方案组合（所有下标均从 0 开始）：
//- 选择 1 号基料：成本 7
//- 选择 1 份 0 号配料：成本 1 x 3 = 3
//- 选择 0 份 1 号配料：成本 0 x 4 = 0
//总成本：7 + 3 + 0 = 10 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>baseCosts = [2,3], toppingCosts = [4,5,100], target = 18
//<strong>输出：</strong>17
//<strong>解释：</strong>考虑下面的方案组合（所有下标均从 0 开始）：
//- 选择 1 号基料：成本 3
//- 选择 1 份 0 号配料：成本 1 x 4 = 4
//- 选择 2 份 1 号配料：成本 2 x 5 = 10
//- 选择 0 份 2 号配料：成本 0 x 100 = 0
//总成本：3 + 4 + 10 + 0 = 17 。不存在总成本为 18 的甜点制作方案。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>baseCosts = [3,10], toppingCosts = [2,5], target = 9
//<strong>输出：</strong>8
//<strong>解释：</strong>可以制作总成本为 8 和 10 的甜点。返回 8 ，因为这是成本更低的方案。
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>baseCosts = [10], toppingCosts = [1], target = 1
//<strong>输出：</strong>10
//<strong>解释：</strong>注意，你可以选择不添加任何配料，但你必须选择一种基料。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == baseCosts.length</code></li> 
// <li><code>m == toppingCosts.length</code></li> 
// <li><code>1 &lt;= n, m &lt;= 10</code></li> 
// <li><code>1 &lt;= baseCosts[i], toppingCosts[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= target &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 93</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

//1774.最接近目标价格的甜点成本
//开题时间：2022-12-04 14:43:13
public class ClosestDessertCost {
    public static void main(String[] args) {
        Solution solution = new ClosestDessertCost().new Solution();
        System.out.println(solution.closestCost(new int[]{2, 3}, new int[]{4, 5, 100}, 18));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //三进制枚举法
        public int closestCost9(int[] baseCosts, int[] toppingCosts, int target) {
            int l = Integer.MIN_VALUE, r = Integer.MAX_VALUE;

            int bound = (int) Math.pow(3, toppingCosts.length);
            for (int base : baseCosts)
                for (int i = 0; i < bound; i++) {
                    int sum = base;
                    for (int j = i, idx = 0; j != 0; j /= 3, idx++) {
                        int cnt = j % 3;
                        sum += cnt * toppingCosts[idx];
                    }

                    if (sum == target)
                        return target;
                    else if (l < sum && sum < target)
                        l = sum;
                    else if (target < sum && sum < r)
                        r = sum;
                }

            return Math.abs(target - l) <= Math.abs(target - r) ? l : r;
        }

        int ans = Integer.MIN_VALUE;

        //回溯 + DFS（递归）
        public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
            for (int base : baseCosts)
                dfs(toppingCosts, base, -1, target);

            return ans;
        }

        private void dfs(int[] toppingCosts, int cost, int i, int target) {
            int diffAns = Math.abs(target - ans);
            if (cost - target >= diffAns)
                return;

            int diffCur = Math.abs(target - cost);
            if (diffCur < diffAns || (diffCur == diffAns && cost < ans))
                ans = cost;

            if (i >= toppingCosts.length - 1)
                return;

            i++;
            dfs(toppingCosts, cost, i, target);
            dfs(toppingCosts, cost + toppingCosts[i], i, target);
            dfs(toppingCosts, cost + toppingCosts[i] * 2, i, target);
        }

        //todo DP 「01 背包」问题
    }
//leetcode submit region end(Prohibit modification and deletion)
}