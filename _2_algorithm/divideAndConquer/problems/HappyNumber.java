//<p>编写一个算法来判断一个数 <code>n</code> 是不是快乐数。</p>
//
//<p><strong>「快乐数」</strong>&nbsp;定义为：</p>
//
//<ul> 
// <li>对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。</li> 
// <li>然后重复这个过程直到这个数变为 1，也可能是 <strong>无限循环</strong> 但始终变不到 1。</li> 
// <li>如果这个过程 <strong>结果为</strong>&nbsp;1，那么这个数就是快乐数。</li> 
//</ul>
//
//<p>如果 <code>n</code> 是 <em>快乐数</em> 就返回 <code>true</code> ；不是，则返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 19
//<strong>输出：</strong>true
//<strong>解释：
//</strong>1<sup>2</sup> + 9<sup>2</sup> = 82
// 8<sup>2</sup> + 2<sup>2</sup> = 68
// 6<sup>2</sup> + 8<sup>2</sup> = 100
// 1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 2
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><li>👍 1125</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer.problems;

import java.util.HashSet;

// 202.快乐数
// 开题时间：2022-11-09 20:10:56
public class HappyNumber {
  public static void main(String[] args) {
    Solution solution = new HappyNumber().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 哈希表
    public boolean isHappy9(int n) {
      HashSet<Integer> visited = new HashSet<>();
      
      while (n != 1) {
        if (!visited.add(n))
          return false;
        n = getNext(n);
      }
      
      return true;
    }
    
    //☆☆☆☆☆ 快慢指针
    public boolean isHappy(int n) {
      for (int m = getNext(n); m != 1; ) {
        if (m == n)
          return false;
        n = getNext(n);
        m = getNext(getNext(m));
      }
      return true;
    }
    
    private int getNext(int n) {
      int sum = 0;
      for (; n != 0; n /= 10) {
        int digit = n % 10;
        sum += digit * digit;
      }
      return sum;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}