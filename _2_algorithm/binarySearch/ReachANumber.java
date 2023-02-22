//<p>在一根无限长的数轴上，你站在<code>0</code>的位置。终点在<code>target</code>的位置。</p>
//
//<p>你可以做一些数量的移动 <code>numMoves</code> :</p>
//
//<ul> 
// <li>每次你可以选择向左或向右移动。</li> 
// <li>第 <code>i</code>&nbsp;次移动（从 &nbsp;<code>i == 1</code>&nbsp;开始，到&nbsp;<code>i == numMoves</code> ），在选择的方向上走 <code>i</code>&nbsp;步。</li> 
//</ul>
//
//<p>给定整数&nbsp;<code>target</code> ，返回 <em>到达目标所需的 <strong>最小&nbsp;</strong>移动次数(即最小 <code>numMoves</code> )&nbsp;</em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> target = 2
//<strong>输出:</strong> 3
//<strong>解释:</strong>
// 第一次移动，从 0 到 1 。
// 第二次移动，从 1 到 -1 。
// 第三次移动，从 -1 到 2 。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> target = 3
//<strong>输出:</strong> 2
//<strong>解释:</strong>
// 第一次移动，从 0 到 1 。
// 第二次移动，从 1 到 3 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>-10<sup>9</sup>&nbsp;&lt;= target &lt;= 10<sup>9</sup></code></li> 
// <li><code>target != 0</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数学</li><li>二分查找</li></div></div><br><div><li>👍 218</li><li>👎 0</li></div>
package _2_algorithm.binarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

// 754.到达终点数字
// 开题时间：2022-11-04 08:40:25
public class ReachANumber {
  public static void main(String[] args) {
    Solution solution = new ReachANumber().new Solution();
    //        System.out.println(solution.reachNumber(200));
    System.out.println(solution.reachNumber(-2));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // BFS   TLE
    public int reachNumber2(int target) {
      int step = 1;
      LinkedList<Integer> q = new LinkedList<>();
      q.add(0);
      HashSet<Integer> visited = new HashSet<>();
      while (!q.isEmpty()) {
        int size = q.size();
        System.out.print(step - 1 + "\t");
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer e : q)
          if (visited.add(e))
            list.add(e);
        Collections.sort(list);
        for (Integer e : list) {
          System.out.print(e + " , ");
        }
        
        System.out.println();
        for (int i = 0; i < size; i++) {
          Integer poll = q.poll();
          if (poll == target)
            return step - 1;
          long l = (long) poll - step;
          if (l >= Integer.MIN_VALUE)
            q.offer((int) l);
          long r = (long) poll + step;
          if (r <= Integer.MAX_VALUE)
            q.offer((int) r);
        }
        step++;
      }
      return step;
    }
    
    /*
     * 0
     * 1    -1      1
     * 2    -3 1    -1  3
     * 3    -6 0    -2 4    -4 2    0 6
     * 4    -10 -2  -4 4    -6 2    0 8     -8 0    -2 6    -4 4    2 10
     * 5    -15 -5 -13 3 -11 -1 -9 1 -7 3 -5 5 -3 7 -1 9 1 11 3 13 5 15
     */
    
    /*
     * 0 0
     * 1     -1  1
     * 2     -3  -1  1   3
     * 3     -6  -4  -2  0   2   4   6
     * 4     -10 -8  -6  -4  -2  0   2   4   6   8   10
     * 5     -15 -13 -11 -9  -7 -5   -3  -1  1   3   5 7 9 11 13 15
     */
    
    /*
     * 0
     * -1 1
     * -3 3
     * -6 -4 -2 2 4 6
     * -10 -8 8 10
     * -15 -13 -11 -9 -7 -5 5 7 9 11 13 15
     */
    
    /*
     * 0 0   1
     * 1 1   1
     * 2 3   1
     * 3 6   3
     * 4 10  2
     * 5 15  6
     * 6 21  3
     * 7 28  9
     * 8 36  4
     * 9 45  12
     * 10 55 5
     * 11 66 15
     * 12 78 6
     * 13 91 18
     * 14 105 7
     */
    // 0 1 3 2 3 5 3 5 4 5 4 5 7
    // 二分    logn    1
    public int reachNumber(int target) {
      int t = Math.abs(target);
      int l = 0, r = t;
      while (l < r) {
        int mid = l + ((r - l) >> 1);
        long max = (long) mid * (mid + 1) >> 1;
        if (t <= max)
          r = mid;
        else
          l = mid + 1;
      }
      return (t & 1) != ((r + 3) & 3) >> 1 ?
          r :
          (r & 1) == 0 ?
              r + 1 :
              r + 2;
    }
    
    //☆☆☆☆☆ 数学  1   1
    public int reachNumber3(int target) {
      target = Math.abs(target);
      int n = (int) Math.ceil((Math.sqrt(((long) target << 3) + 1) - 1) / 2);
      return (target & 1) != ((n + 3) & 3) >> 1 ? n : n + 1 + (n & 1);
      //            return (((n * (n + 1) >> 1) - target) & 1) == 0 ? n : n + 1 + (n & 1);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}