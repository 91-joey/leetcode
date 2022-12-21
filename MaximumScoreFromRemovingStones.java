//<p>你正在玩一个单人游戏，面前放置着大小分别为 <code>a</code>​​​​​​、<code>b</code> 和 <code>c</code>​​​​​​ 的 <strong>三堆</strong> 石子。</p>
//
//<p>每回合你都要从两个 <strong>不同的非空堆</strong> 中取出一颗石子，并在得分上加 <code>1</code> 分。当存在 <strong>两个或更多</strong> 的空堆时，游戏停止。</p>
//
//<p>给你三个整数 <code>a</code> 、<code>b</code> 和 <code>c</code> ，返回可以得到的 <strong>最大分数</strong> 。</p> &nbsp;
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>a = 2, b = 4, c = 6
//<strong>输出：</strong>6
//<strong>解释：</strong>石子起始状态是 (2, 4, 6) ，最优的一组操作是：
//- 从第一和第三堆取，石子状态现在是 (1, 4, 5)
//- 从第一和第三堆取，石子状态现在是 (0, 4, 4)
//- 从第二和第三堆取，石子状态现在是 (0, 3, 3)
//- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
//- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
//- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
//总分：6 分 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>a = 4, b = 4, c = 6
//<strong>输出：</strong>7
//<strong>解释：</strong>石子起始状态是 (4, 4, 6) ，最优的一组操作是：
//- 从第一和第二堆取，石子状态现在是 (3, 3, 6)
//- 从第一和第三堆取，石子状态现在是 (2, 3, 5)
//- 从第一和第三堆取，石子状态现在是 (1, 3, 4)
//- 从第一和第三堆取，石子状态现在是 (0, 3, 3)
//- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
//- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
//- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
//总分：7 分 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>a = 1, b = 8, c = 8
//<strong>输出：</strong>8
//<strong>解释：</strong>最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
//注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= a, b, c &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><li>👍 47</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

//1753.移除石子的最大得分
//开题时间：2022-12-21 10:58:06
public class MaximumScoreFromRemovingStones {
    public static void main(String[] args) {
        Solution solution = new MaximumScoreFromRemovingStones().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximumScoreX(int a, int b, int c) {
            int[] arr = Stream.of(a, b, c)
                    .sorted()
                    .mapToInt(Integer::intValue)
                    .toArray();

            return Math.max(arr[1], Math.max(
                    arr[0] + Math.min(arr[2] - arr[0], arr[1]),
                    arr[1] + Math.min(arr[2] - arr[1], arr[0])
            ));
        }

        //贪心 + 排序
        public int maximumScore9(int a, int b, int c) {
            int[] arr = Stream.of(a, b, c)
                    .sorted()
                    .mapToInt(Integer::intValue)
                    .toArray();

            int ans = 0;
            while (arr[1] > 0 && arr[2] > 0) {
                arr[1]--;
                arr[2]--;
                ans++;
                Arrays.sort(arr);
            }

            return ans;
        }

        /*
         * ☆☆☆☆☆ 贪心 + 数学
         * 设 a<=b<=c，分类讨论：
         *      a+b<=c  不管怎样，c始终>=0，所以拿c和a、b匹配
         *      a+b>c
         *          解释一：拿c和a、b匹配，使得a、b尽可能的相等（相差小于1），最后拿a、b匹配
         *          解释二：拿a、b匹配，直到a+b<=c，转为情况一
         */
        public int maximumScore8(int a, int b, int c) {
            int sum = a + b + c;
            int max = Math.max(a, Math.max(b, c));
            return Math.min(sum - max, sum >> 1);
        }

        //贪心 + 优先队列
        public int maximumScore(int a, int b, int c) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(3, Comparator.reverseOrder());
            pq.addAll(List.of(a, b, c));

            int ans = 0;
            while (true) {
                int first = pq.poll();
                int second = pq.poll();
                if (second == 0)
                    break;
                ans++;
                pq.offer(first - 1);
                pq.offer(second - 1);
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}