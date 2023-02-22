//<p>输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre><strong>输入:</strong> <span><code>[10,2]</code></span>
//<strong>输出:</strong> "<span><code>102"</code></span></pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre><strong>输入:</strong> <span><code>[3,30,34,5,9]</code></span>
//<strong>输出:</strong> "<span><code>3033459"</code></span></pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>0 &lt; nums.length &lt;= 100</code></li> 
//</ul>
//
//<p><strong>说明: </strong></p>
//
//<ul> 
// <li>输出结果可能非常大，所以你需要返回一个字符串而不是整数</li> 
// <li>拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>贪心</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 542</li><li>👎 0</li></div>
package _2_algorithm.sort.problems;

import _3_common.tool.Tools;

import java.util.Arrays;

// 剑指 Offer 45.把数组排成最小的数
// 开题时间：2022-09-19 11:30:38
public class BaShuZuPaiChengZuiXiaoDeShuLcof {
  public static void main(String[] args) {
    Solution solution = new BaShuZuPaiChengZuiXiaoDeShuLcof().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 内置排序函数
    public String minNumber(int[] nums) {
      int length = nums.length;
      String[] arr = new String[length];
      for (int i = 0; i < length; i++)
        arr[i] = String.valueOf(nums[i]);
      
      Arrays.parallelSort(arr, (a, b) -> (a + b).compareTo(b + a));
      
      StringBuilder sb = new StringBuilder();
      for (String s : arr) sb.append(s);
      return sb.toString();
    }
    
    // 冒泡排序（优化）
    public String minNumber2(int[] nums) {
      int length = nums.length;
      String[] arr = new String[length];
      for (int i = 0; i < length; i++)
        arr[i] = String.valueOf(nums[i]);
      
      int lst = length - 1;
      boolean swapped = true;
      int endIdx = lst;
      int lstSwappedIdx = -1;
      while (swapped) {
        swapped = false;
        for (int i = 0; i < endIdx; i++)
          if ((arr[i] + arr[i + 1]).compareTo(arr[i + 1] + arr[i]) > 0) {
            Tools.swap(arr, i, i + 1);
            swapped = true;
            lstSwappedIdx = i;
          }
        endIdx = lstSwappedIdx;
      }
      
      return String.join("", arr);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}