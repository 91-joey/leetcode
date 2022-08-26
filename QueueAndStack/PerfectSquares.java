//<p>给你一个整数 <code>n</code> ，返回 <em>和为 <code>n</code> 的完全平方数的最少数量</em> 。</p>
//
//<p><strong>完全平方数</strong> 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，<code>1</code>、<code>4</code>、<code>9</code> 和 <code>16</code> 都是完全平方数，而 <code>3</code> 和 <code>11</code> 不是。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = <span><code>12</code></span>
//<strong>输出：</strong>3 
//<strong>解释：</strong><span><code>12 = 4 + 4 + 4</code></span></pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = <span><code>13</code></span>
//<strong>输出：</strong>2
//<strong>解释：</strong><span><code>13 = 4 + 9</code></span></pre>
//
//&nbsp;
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>广度优先搜索</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍 1464</li><li>👎 0</li></div>
package org.example.leetcode.problems.QueueAndStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//279.完全平方数
//开题时间：2022-08-17 10:09:09
public class PerfectSquares {
    public static void main(String[] args) {
        Solution solution = new PerfectSquares().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.自解（BFS）   n   sqrt(n)
        public int numSquares(int n) {
//            求出<=n的最大完全平方数
            int start = (int) Math.sqrt(n);
            int cnt = 1;
            if (start * start == n) {
                return cnt;
            }

            Queue<int[]> queue = new LinkedList<>();
            List<Integer> sqs = new ArrayList<>(start);
            for (int i = start; i >= 1; i--) {
                int i2 = i * i;
//                存放<=最大完全平方数的平方数
                sqs.add(i2);
//                存放<=最大完全平方数的平方数、初始总和
                queue.offer(new int[]{i2, i2});
            }
            while (!queue.isEmpty()) {
                cnt++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] head = queue.poll();
                    for (int nextNum : sqs.subList(sqs.indexOf(head[0]), sqs.size())) {
                        int sum = head[1] + nextNum;
                        if (sum == n) {
                            return cnt;
                        } else if (sum < n) {
                            queue.add(new int[]{nextNum, sum});
                        }
                    }
                }
            }

            return -1;
        }


        //2.官解（数学）   sqrt(n)    1
        public int numSquares2(int n) {
            if (isPerfectSquare(n)) {
                return 1;
            } else if (checkAnswer4(n)) {
                return 4;
            } else {
                //Answer==2?
                int sqrt = (int) Math.sqrt(n);
                for (int i = 1; i <= sqrt; i++) {
                    if (isPerfectSquare(n - i * i)){
                        return 2;
                    }
                }
                return 3;
            }
        }

        private boolean isPerfectSquare(int n) {
            int sqrt = (int) Math.sqrt(n);
            return sqrt * sqrt == n;
        }

        private boolean checkAnswer4(int n) {
            while (n % 4 == 0) {
                n /= 4;
            }
            return n % 8 == 7;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}