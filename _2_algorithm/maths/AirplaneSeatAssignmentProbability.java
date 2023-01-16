//<p>有 <code>n</code> 位乘客即将登机，飞机正好有 <code>n</code> 个座位。第一位乘客的票丢了，他随便选了一个座位坐下。</p>
//
//<p>剩下的乘客将会：</p>
//
//<ul> 
// <li> <p>如果他们自己的座位还空着，就坐到自己的座位上，</p> </li> 
// <li>当他们自己的座位被占用时，随机选择其他座位</li> 
//</ul>
//
//<p>第 <code>n</code>&nbsp;位乘客坐在自己的座位上的概率是多少？</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出：</strong>1.00000
//<strong>解释：</strong>第一个人只会坐在自己的位置上。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入:</strong> n = 2
//<strong>输出:</strong> 0.50000
//<strong>解释：</strong>在第一个人选好座位坐下后，第二个人坐在自己的座位上的概率是 0.5。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10^5</code></li> 
//</ul>
//
//<div><li>👍 111</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

//1227.飞机座位分配概率
//开题时间：2023-01-16 10:31:37
public class AirplaneSeatAssignmentProbability {
    public static void main(String[] args) {
        Solution solution = new AirplaneSeatAssignmentProbability().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double nthPersonGetsNthSeat(int n) {
            return n == 1 ? 1.0 : 0.5;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}