//<p>给你一个二维整数数组 <code>orders</code> ，其中每个 <code>orders[i] = [price<sub>i</sub>, amount<sub>i</sub>, orderType<sub>i</sub>]</code> 表示有 <code>amount<sub>i</sub></code><sub> </sub>笔类型为&nbsp;<code>orderType<sub>i</sub></code> 、价格为&nbsp;<code>price<sub>i</sub></code> 的订单。</p>
//
//<p>订单类型 <code>orderType<sub>i</sub></code> 可以分为两种：</p>
//
//<ul> 
// <li><code>0</code> 表示这是一批采购订单 <code>buy</code></li> 
// <li><code>1</code> 表示这是一批销售订单 <code>sell</code></li> 
//</ul>
//
//<p>注意，<code>orders[i]</code> 表示一批共计 <code>amount<sub>i</sub></code> 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 <code>i</code> ，由 <code>orders[i]</code> 表示的所有订单提交时间均早于 <code>orders[i+1]</code> 表示的所有订单。</p>
//
//<p>存在由未执行订单组成的 <strong>积压订单</strong> 。积压订单最初是空的。提交订单时，会发生以下情况：</p>
//
//<ul> 
// <li>如果该订单是一笔采购订单 <code>buy</code> ，则可以查看积压订单中价格 <strong>最低</strong> 的销售订单 <code>sell</code> 。如果该销售订单 <code>sell</code> 的价格 <strong>低于或等于</strong> 当前采购订单 <code>buy</code> 的价格，则匹配并执行这两笔订单，并将销售订单 <code>sell</code> 从积压订单中删除。否则，采购订单 <code>buy</code> 将会添加到积压订单中。</li> 
// <li>反之亦然，如果该订单是一笔销售订单 <code>sell</code> ，则可以查看积压订单中价格 <strong>最高</strong> 的采购订单 <code>buy</code> 。如果该采购订单 <code>buy</code> 的价格 <strong>高于或等于</strong> 当前销售订单 <code>sell</code> 的价格，则匹配并执行这两笔订单，并将采购订单 <code>buy</code> 从积压订单中删除。否则，销售订单 <code>sell</code> 将会添加到积压订单中。</li> 
//</ul>
//
//<p>输入所有订单后，返回积压订单中的 <strong>订单总数</strong> 。由于数字可能很大，所以需要返回对 <code>10<sup>9</sup> + 7</code> 取余的结果。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/03/21/ex1.png" style="width: 450px; height: 479px;" /> 
//<pre>
//<strong>输入：</strong>orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
//<strong>输出：</strong>6
//<strong>解释：</strong>输入订单后会发生下述情况：
//- 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
//- 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
//- 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
//- 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25 ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添加到积压订单中。
// 最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/03/21/ex2.png" style="width: 450px; height: 584px;" /> 
//<pre>
//<strong>输入：</strong>orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
//<strong>输出：</strong>999999984
//<strong>解释：</strong>输入订单后会发生下述情况：
//- 提交 10<sup>9</sup> 笔销售订单，价格为 7 。没有采购订单，所以这 10<sup>9</sup> 笔订单添加到积压订单中。
//- 提交 3 笔采购订单，价格为 15 。这些采购订单与价格最低（价格为 7 ）的 3 笔销售订单匹配，从积压订单中删除这 3 笔销售订单。
//- 提交 999999995 笔采购订单，价格为 5 。销售订单的最低价为 7 ，所以这 999999995 笔订单添加到积压订单中。
//- 提交 1 笔销售订单，价格为 5 。这笔销售订单与价格最高（价格为 5 ）的 1 笔采购订单匹配，从积压订单中删除这 1 笔采购订单。
// 最终，积压订单中有 (1000000000-3) 笔价格为 7 的销售订单，和 (999999995-1) 笔价格为 5 的采购订单。所以积压订单中的订单总数为 1999999991 ，等于 999999984 % (10<sup>9</sup> + 7) 。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= orders.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>orders[i].length == 3</code></li> 
// <li><code>1 &lt;= price<sub>i</sub>, amount<sub>i</sub> &lt;= 10<sup>9</sup></code></li> 
// <li><code>orderType<sub>i</sub></code> 为 <code>0</code> 或 <code>1</code></li> 
//</ul>
//
//<div><li>👍 45</li><li>👎 0</li></div>
package _1_dataStructure.heap;

import _3_common.tool.Tools;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// 1801.积压订单中的订单总数
// 开题时间：2023-01-02 11:01:07
public class NumberOfOrdersInTheBacklog {
  public static void main(String[] args) {
    Solution solution = new NumberOfOrdersInTheBacklog().new Solution();
    System.out.println(solution.getNumberOfBacklogOrders(Tools.to2DIntArray("[[19,28,0],[9,4,1],[25,15,1]]")));
    //        System.out.println(solution.getNumberOfBacklogOrders(Tools.to2DIntArray("[[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int MOD = 10_0000_0007;
    
    public int getNumberOfBacklogOrders9(int[][] orders) {
      PriorityQueue<int[]> pqBuy = new PriorityQueue<>(Comparator.<int[]>comparingInt(arr -> arr[0]).reversed());
      PriorityQueue<int[]> pqSell = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
      for (int[] order : orders) {
        if (order[2] == 0) {
          while (!pqSell.isEmpty() && pqSell.peek()[0] <= order[0]) {
            int[] poll = pqSell.poll();
            if (order[1] > poll[1])
              order[1] -= poll[1];
            else {
              if (order[1] < poll[1]) {
                poll[1] -= order[1];
                pqSell.offer(poll);
              }
              order[1] = 0;
              break;
            }
          }
          if (order[1] > 0)
            pqBuy.offer(new int[]{order[0], order[1]});
        } else {
          while (!pqBuy.isEmpty() && pqBuy.peek()[0] >= order[0]) {
            int[] poll = pqBuy.poll();
            if (order[1] > poll[1])
              order[1] -= poll[1];
            else {
              if (order[1] < poll[1]) {
                poll[1] -= order[1];
                pqBuy.offer(poll);
              }
              order[1] = 0;
              break;
            }
          }
          if (order[1] > 0)
            pqSell.offer(new int[]{order[0], order[1]});
        }
      }
      
      int ans = 0;
      while (!pqBuy.isEmpty()) ans = (ans + pqBuy.poll()[1]) % MOD;
      while (!pqSell.isEmpty()) ans = (ans + pqSell.poll()[1]) % MOD;
      return ans;
    }
    
    
    public int getNumberOfBacklogOrders(int[][] orders) {
      PriorityQueue<int[]> pqBuy = new PriorityQueue<>(Comparator.<int[]>comparingInt(arr -> arr[0]).reversed());
      PriorityQueue<int[]> pqSell = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
      for (int[] order : orders)
        if (order[2] == 0)
          helper(pqBuy, pqSell, order[0], order[1], true);
        else
          helper(pqSell, pqBuy, order[0], order[1], false);
      
      int ans = 0;
      for (PriorityQueue<int[]> pq : List.of(pqBuy, pqSell))
        while (!pq.isEmpty())
          ans = (ans + pq.poll()[1]) % MOD;
      return ans;
    }
    
    private void helper(PriorityQueue<int[]> from, PriorityQueue<int[]> to, int price, int amount, boolean lessThan) {
      while (amount > 0 && !to.isEmpty() && (lessThan ? to.peek()[0] <= price : to.peek()[0] >= price)) {
        int[] poll = to.poll();
        int min = Math.min(amount, poll[1]);
        amount -= min;
        poll[1] -= min;
        if (0 < poll[1])
          to.offer(poll);
      }
      if (amount > 0)
        from.offer(new int[]{price, amount});
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}