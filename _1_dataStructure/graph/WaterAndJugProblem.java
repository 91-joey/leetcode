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
package org.example.leetcode.problems._1_dataStructure.graph;

import java.util.HashSet;
import java.util.Set;

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
    public boolean canMeasureWater9(int jug1Capacity, int jug2Capacity, int targetCapacity) {
      return targetCapacity <= jug1Capacity + jug2Capacity && targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
    }
    
    public static int gcd(int a, int b) {
      return b != 0 ?
          gcd(b, a % b) :
          a;
    }
    
    // dfs + 哈希表
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
      return canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, 0, 0, new HashSet<>());
    }
    
    private boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity, int i, int j, Set<Long> vis) {
      if (i + j == targetCapacity)
        return true;
      vis.add(((long) i << 20) | j);
      
      boolean ans = false;
      int[][] operations = {
          {jug1Capacity, j},
          {i, jug2Capacity},
          {0, j},
          {i, 0},
          {i - Math.min(i, jug2Capacity - j), j + Math.min(i, jug2Capacity - j)},
          {i + Math.min(j, jug1Capacity - i), j - Math.min(j, jug1Capacity - i)}
      };
      for (int[] jugs : operations)
        if (!vis.contains(((long) jugs[0] << 20) | jugs[1]))
          ans = ans || canMeasureWater(jug1Capacity, jug2Capacity, targetCapacity, jugs[0], jugs[1], vis);
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}