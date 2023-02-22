//<p>给你一个以行程长度编码压缩的整数列表&nbsp;<code>nums</code>&nbsp;。</p>
//
//<p>考虑每对相邻的两个元素 <code>[freq, val] = [nums[2*i], nums[2*i+1]]</code>&nbsp;（其中&nbsp;<code>i &gt;= 0</code>&nbsp;），每一对都表示解压后子列表中有 <code>freq</code>&nbsp;个值为&nbsp;<code>val</code>&nbsp;的元素，你需要从左到右连接所有子列表以生成解压后的列表。</p>
//
//<p>请你返回解压后的列表。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,4]
//<strong>输出：</strong>[2,4,4,4]
//<strong>解释：</strong>第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
// 第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
// 最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,2,3]
//<strong>输出：</strong>[1,3,3]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>nums.length % 2 == 0</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 55</li><li>👎 0</li></div>
package _1_dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.Arrays;

// 1313.解压缩编码列表
// 开题时间：2022-11-19 17:01:50
public class DecompressRunLengthEncodedList {
  public static void main(String[] args) {
    Solution solution = new DecompressRunLengthEncodedList().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] decompressRLElist9(int[] nums) {
      ArrayList<Integer> list = new ArrayList<>();
      for (int i = 0; i < nums.length; i += 2)
        for (int j = 0; j < nums[i]; j++)
          list.add(nums[i + 1]);
      return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int[] decompressRLElist(int[] nums) {
      int n = nums.length;
      int len = 0;
      for (int i = 0; i < n; i += 2)
        len += nums[i];
      
      int[] ans = new int[len];
      for (int i = 0, start = 0; i < n; i += 2)
        Arrays.fill(ans, start, (start = start + nums[i]), nums[i + 1]);
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}