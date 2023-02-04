//<p>请你将一些箱子装在 <strong>一辆卡车</strong> 上。给你一个二维数组 <code>boxTypes</code> ，其中 <code>boxTypes[i] = [numberOfBoxes<sub>i</sub>, numberOfUnitsPerBox<sub>i</sub>]</code> ：</p>
//
//<ul> 
// <li><code>numberOfBoxes<sub>i</sub></code> 是类型 <code>i</code> 的箱子的数量。</li> 
// <li><code>numberOfUnitsPerBox<sub>i</sub></code><sub> </sub>是类型 <code>i</code>&nbsp;每个箱子可以装载的单元数量。</li> 
//</ul>
//
//<p>整数 <code>truckSize</code> 表示卡车上可以装载 <strong>箱子</strong> 的 <strong>最大数量</strong> 。只要箱子数量不超过 <code>truckSize</code> ，你就可以选择任意箱子装到卡车上。</p>
//
//<p>返回卡车可以装载&nbsp;<strong>单元</strong> 的 <strong>最大</strong> 总数<em>。</em></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
//<strong>输出：</strong>8
//<strong>解释：</strong>箱子的情况如下：
//- 1 个第一类的箱子，里面含 3 个单元。
//- 2 个第二类的箱子，每个里面含 2 个单元。
//- 3 个第三类的箱子，每个里面含 1 个单元。
// 可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
// 单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
//<strong>输出：</strong>91
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= boxTypes.length &lt;= 1000</code></li> 
// <li><code>1 &lt;= numberOfBoxes<sub>i</sub>, numberOfUnitsPerBox<sub>i</sub> &lt;= 1000</code></li> 
// <li><code>1 &lt;= truckSize &lt;= 10<sup>6</sup></code></li> 
//</ul>
//
//<div><li>👍 62</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort.problems;

import java.util.Arrays;
import java.util.Comparator;

// 1710.卡车上的最大单元数
// 开题时间：2022-11-15 09:25:37
public class MaximumUnitsOnATruck {
  public static void main(String[] args) {
    Solution solution = new MaximumUnitsOnATruck().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maximumUnits9(int[][] boxTypes, int truckSize) {
      Arrays.sort(boxTypes, Comparator.<int[]>comparingInt(boxType -> boxType[1]).reversed());
      
      int maximumUnits = 0;
      for (int i = 0; i < boxTypes.length && truckSize != 0; i++) {
        int min = Math.min(truckSize, boxTypes[i][0]);
        maximumUnits += min * boxTypes[i][1];
        truckSize -= min;
      }
      
      return maximumUnits;
    }
    
    public int maximumUnits(int[][] boxTypes, int truckSize) {
      int ans = 0;
      Arrays.sort(boxTypes, Comparator.<int[]>comparingInt(a -> a[1]).reversed());
      for (int[] boxType : boxTypes) {
        int min = Math.min(boxType[0], truckSize);
        truckSize -= min;
        ans += min * boxType[1];
        if (truckSize == 0)
          break;
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}