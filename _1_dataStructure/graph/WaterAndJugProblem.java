//<p>有两个水壶，容量分别为&nbsp;<code>jug1Capacity</code>&nbsp;和 <code>jug2Capacity</code> 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到&nbsp;<code>targetCapacity</code> 升。</p>
//
//<p>如果可以得到&nbsp;<code>targetCapacity</code>&nbsp;升水，最后请用以上水壶中的一或两个来盛放取得的&nbsp;<code>targetCapacity</code>&nbsp;升水。</p>
//
//<p>你可以：</p>
//
//<ul> 
// <li>装满任意一个水壶</li> 
// <li>清空任意一个水壶</li> 
// <li>从一个水壶向另外一个水壶倒水，直到装满或者倒空</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong>&nbsp;</p>
//
//<pre>
//<strong>输入:</strong> jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
//<strong>输出:</strong> true
//<strong>解释</strong>：来自著名的&nbsp;<a href="https://www.youtube.com/watch?v=BVtQNK_ZUJg"><em>"Die Hard"</em></a></pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
//<strong>输出:</strong> false
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
//<strong>输出:</strong> true
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= jug1Capacity, jug2Capacity, targetCapacity &lt;= 10<sup>6</sup></code></li> 
//</ul>
//
//<div><li>👍 406</li><li>👎 0</li></div>
package _1_dataStructure.graph;

import java.util.HashSet;
import java.util.LinkedList;

// 365.水壶问题
// 开题时间：2023-01-11 13:38:48
public class WaterAndJugProblem {
  public static void main(String[] args) {
    Solution solution = new WaterAndJugProblem().new Solution();
    System.out.println(Solution.gcd(3, 4));
    System.out.println(Solution.gcd(2, 6));
    //        System.out.println(solution.canMeasureWater(3, 5, 4));
    System.out.println(solution.canMeasureWater(4, 6, 8));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
  
    //☆☆☆☆☆ 数学（贝祖定理）
    public boolean canMeasureWater9(int X, int Y, int Z) {
      return Z <= X + Y &&
          Z % gcd(X, Y) == 0;
    }
    
    public static int gcd(int a, int b) {
      return b != 0 ?
          gcd(b, a % b) :
          a;
    }
  
    // bfs + 哈希表
    public boolean canMeasureWater8(int X, int Y, int Z) {
      if (X + Y < Z) {
        return false;
      }
    
      LinkedList<int[]> q = new LinkedList<>();
      HashSet<Long> vis = new HashSet<>();
      q.offer(new int[]{0, 0});
      vis.add(0L);
    
      while (!q.isEmpty()) {
        int[] poll = q.poll();
        int x = poll[0];
        int y = poll[1];
      
        // 罗列bfs树的子节点
        int literX2Y = Math.min(x, Y - y);
        int literY2X = Math.min(y, X - x);
        int[][] states = {
            {X, y},
            {x, Y},
            {0, y},
            {x, 0},
            {x - literX2Y, y + literX2Y},
            {x + literY2X, y - literY2X}
        };
      
        for (int[] state : states) {
          if (vis.add(hash(state[0], state[1]))) {
            // 判断是否达到目标
            if (state[0] + state[1] == Z) {
              return true;
            }
            q.offer(state);
          }
        }
      }
    
      return false;
    }
  
    private int X;
    private int Y;
    private int Z;
    HashSet<Long> vis;
    
    // dfs + 哈希表
    public boolean canMeasureWater(int X, int Y, int Z) {
      this.X = X;
      this.Y = Y;
      this.Z = Z;
      vis = new HashSet<>();
      
      return canMeasureWater(0, 0);
    }
  
    /**
     * @param x 水壶一中的当前水量
     * @param y 水壶二中的当前水量
     */
    private boolean canMeasureWater(int x, int y) {
      if (x + y == Z)
        return true;
  
      boolean ans = false;
      vis.add(hash(x, y));
      
      // 罗列dfs树的子节点
      int literX2Y = Math.min(x, Y - y);
      int literY2X = Math.min(y, X - x);
      int[][] states = {
          {X, y},
          {x, Y},
          {0, y},
          {x, 0},
          {x - literX2Y, y + literX2Y},
          {x + literY2X, y - literY2X}
      };
      
      // 递归dfs
      for (int[] state : states)
        if (!vis.contains(hash(state[0], state[1])))
          ans = ans || canMeasureWater(state[0], state[1]);
      
      return ans;
    }
    
    private long hash(int x, int y) {
      return ((long) x << 20) | y;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}