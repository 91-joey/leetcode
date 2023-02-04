//<p>给你一个整数数组 <code>arr</code> ，数组中的每个整数 <strong>互不相同</strong> 。另有一个由整数数组构成的数组 <code>pieces</code>，其中的整数也 <strong>互不相同</strong> 。请你以 <strong>任意顺序</strong> 连接 <code>pieces</code> 中的数组以形成 <code>arr</code> 。但是，<strong>不允许</strong> 对每个数组 <code>pieces[i]</code> 中的整数重新排序。</p>
//
//<p>如果可以连接<em> </em><code>pieces</code> 中的数组形成 <code>arr</code> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [15,88], pieces = [[88],[15]]
//<strong>输出：</strong>true
//<strong>解释：</strong>依次连接 <span><code>[15]</code></span> 和 <span><code>[88]</code></span>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [49,18,16], pieces = [[16,18,49]]
//<strong>输出：</strong>false
//<strong>解释：</strong>即便数字相符，也不能重新排列 pieces[0]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
//<strong>输出：</strong>true
//<strong>解释：</strong>依次连接 <span><code>[91]</code></span>、<span><code>[4,64]</code></span> 和 <span><code>[78]</code></span></pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= pieces.length &lt;= arr.length &lt;= 100</code></li> 
// <li><code>sum(pieces[i].length) == arr.length</code></li> 
// <li><code>1 &lt;= pieces[i].length &lt;= arr.length</code></li> 
// <li><code>1 &lt;= arr[i], pieces[i][j] &lt;= 100</code></li> 
// <li><code>arr</code> 中的整数 <strong>互不相同</strong></li> 
// <li><code>pieces</code> 中的整数 <strong>互不相同</strong>（也就是说，如果将 <code>pieces</code> 扁平化成一维数组，数组中的所有整数互不相同）</li> 
//</ul>
//
//<div><li>👍 134</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.Arrays;

// 1640.能否连接形成数组
// 开题时间：2023-01-10 12:20:25
public class CheckArrayFormationThroughConcatenation {
  public static void main(String[] args) {
    Solution solution = new CheckArrayFormationThroughConcatenation().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
      int[] val2idx = new int[101];
      Arrays.fill(val2idx, -1);
      for (int i = 0; i < pieces.length; i++)
        val2idx[pieces[i][0]] = i;
      
      for (int i = 0; i < arr.length; ) {
        int idx = val2idx[arr[i]];
        if (idx == -1)
          return false;
        
        for (int x : pieces[idx])
          if (x != arr[i++])
            return false;
      }
      
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}