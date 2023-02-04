//<p>给你一个整数&nbsp;<code>n</code>&nbsp;，如果你可以将&nbsp;<code>n</code>&nbsp;表示成若干个不同的三的幂之和，请你返回&nbsp;<code>true</code>&nbsp;，否则请返回 <code>false</code>&nbsp;。</p>
//
//<p>对于一个整数 <code>y</code>&nbsp;，如果存在整数 <code>x</code>&nbsp;满足 <code>y == 3<sup>x</sup></code>&nbsp;，我们称这个整数 <code>y</code>&nbsp;是三的幂。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>n = 12
//<b>输出：</b>true
//<b>解释：</b>12 = 3<sup>1</sup> + 3<sup>2</sup>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>n = 91
//<b>输出：</b>true
//<b>解释：</b>91 = 3<sup>0</sup> + 3<sup>2</sup> + 3<sup>4</sup>
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>n = 21
//<b>输出：</b>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10<sup>7</sup></code></li> 
//</ul>
//
//<div><li>👍 68</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

import java.util.HashSet;
import java.util.Set;

// 1780.判断一个数字是否可以表示成三的幂的和
// 开题时间：2022-12-09 12:20:39
public class CheckIfNumberIsASumOfPowersOfThree {
  public static void main(String[] args) {
    Solution solution = new CheckIfNumberIsASumOfPowersOfThree().new Solution();
    System.out.println(solution.checkPowersOfThree(6378022));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final Set<Integer> set = new HashSet<>();
    
    static {
      int bound = 1 << 15;
      for (int i = 0; i < bound; i++) {
        int sum = 0;
        for (int j = i, pow = 1; j != 0; j >>= 1, pow *= 3)
          if ((j & 1) == 1)
            sum += pow;
        set.add(sum);
      }
    }
    
    // 二进制枚举打表
    public boolean checkPowersOfThree9(int n) {
      return set.contains(n);
    }
    
    //☆☆☆☆☆ 转三进制，某位是2则为false，否则为true
    public boolean checkPowersOfThree(int n) {
      while (n != 0) {
        if (n % 3 == 2)
          return false;
        n /= 3;
      }
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}