//<p>公司计划面试 <code>2n</code> 人。给你一个数组 <code>costs</code> ，其中 <code>costs[i] = [aCost<sub>i</sub>, bCost<sub>i</sub>]</code> 。第 <code>i</code> 人飞往 <code>a</code> 市的费用为 <code>aCost<sub>i</sub></code> ，飞往 <code>b</code> 市的费用为 <code>bCost<sub>i</sub></code> 。</p>
//
//<p>返回将每个人都飞到 <code>a</code> 、<code>b</code> 中某座城市的最低费用，要求每个城市都有 <code>n</code> 人抵达<strong>。</strong></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>costs = [[10,20],[30,200],[400,50],[30,20]]
//<strong>输出：</strong>110
//<strong>解释：</strong>
//第一个人去 a 市，费用为 10。
//第二个人去 a 市，费用为 30。
//第三个人去 b 市，费用为 50。
//第四个人去 b 市，费用为 20。
//
//最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
//<strong>输出：</strong>1859
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
//<strong>输出：</strong>3086
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 * n == costs.length</code></li> 
// <li><code>2 &lt;= costs.length &lt;= 100</code></li> 
// <li><code>costs.length</code> 为偶数</li> 
// <li><code>1 &lt;= aCost<sub>i</sub>, bCost<sub>i</sub> &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 279</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;
import java.util.Comparator;

//1029.两地调度
//开题时间：2023-01-25 16:24:59
public class TwoCityScheduling {
    public static void main(String[] args) {
        Solution solution = new TwoCityScheduling().new Solution();
//        System.out.println(solution.twoCitySchedCost(Tools.to2DIntArray("[[10,20],[30,200],[400,50],[30,20]]")));
        System.out.println(solution.twoCitySchedCost(Tools.to2DIntArray("[[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int twoCitySchedCostX(int[][] costs) {
            int n = costs.length;
            int[][] arr = new int[2 * n][2];
            for (int i = 0; i < n; i++) {
                arr[2 * i] = new int[]{costs[i][0], 0, i};
                arr[2 * i + 1] = new int[]{costs[i][1], 1, i};
            }
            Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

            int ans = 0, t = n / 2, a = 0, b = 0;
            boolean[] vis = new boolean[n];
            for (int[] cost : arr) {
                if (a >= t && b >= t) {
                    break;
                } else if (!vis[cost[2]]) {
                    if (a >= t) {
                        if (cost[1] == 1) {
                            ans += cost[0];
                            vis[cost[2]] = true;
                            b++;
                            System.out.println(Arrays.toString(cost));
                        }
                    } else if (b >= t) {
                        if (cost[1] == 0) {
                            ans += cost[0];
                            vis[cost[2]] = true;
                            a++;
                            System.out.println(Arrays.toString(cost));
                        }
                    } else {
                        ans += cost[0];
                        vis[cost[2]] = true;
                        if (cost[1] == 0)
                            a++;
                        else
                            b++;
                        System.out.println(Arrays.toString(cost));
                    }
                }
            }

            return ans;
        }
        /*
         * 减法思维
         * 假设所有 2n 人都飞到 b 市，问题转变为从这 2n 人中选出 n 人，让他们改签到 a 市
         * 第 i 人改签的费用为 aCost_i - bCost_i，我们按照改签费用对数组排序，排序后的前 n 项进行改签，费用最低
         */
        public int twoCitySchedCost(int[][] costs) {
            Arrays.sort(costs, Comparator.comparingInt(a -> a[0] - a[1]));
            int ans = 0;
            for (int a = 0, b = costs.length - 1; a < costs.length / 2; a++, b--)
                ans += costs[a][0] + costs[b][1];
            return ans;
        }

        public int twoCitySchedCost9(int[][] costs) {
            return Arrays.stream(costs).mapToInt(a -> a[1]).sum()
                    + Arrays.stream(costs)
                    .mapToInt(a -> a[0] - a[1])
                    .sorted()
                    .limit(costs.length / 2)
                    .sum();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}