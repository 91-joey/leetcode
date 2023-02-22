//<p>给定两个列表 <code>A</code>and <code>B</code>，并且 <code>B</code> 是 <code>A</code> 的变位（即&nbsp;<code>B</code> 是由&nbsp;<code>A</code> 中的元素随机排列后组成的新列表）。</p>
//
//<p>我们希望找出一个从 <code>A</code> 到 <code>B</code>&nbsp;的索引映射 <code>P</code> 。一个映射 <code>P[i] = j</code>&nbsp;指的是列表&nbsp;<code>A</code> 中的第 <code>i</code> 个元素出现于列表&nbsp;<code>B</code> 中的第 <code>j</code> 个元素上。</p>
//
//<p>列表 <code>A</code> 和 <code>B</code> 可能出现重复元素。如果有多于一种答案，输出任意一种。</p>
//
//<p>例如，给定</p>
//
//<pre>A = [12, 28, 46, 32, 50]
// B = [50, 12, 32, 46, 28]
//</pre>
//
//<p>&nbsp;</p>
//
//<p>需要返回</p>
//
//<pre>[1, 4, 3, 2, 0]
//</pre>
//
//<p><code>P[0] = 1</code>&nbsp;，因为 <code>A</code> 中的第 <code>0</code> 个元素出现于 <code>B[1]</code>，而且 <code>P[1] = 4</code> 因为 <code>A</code> 中第 <code>1</code> 个元素出现于 <code>B[4]</code>，以此类推。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>注：</strong></p>
//
//<ol> 
// <li><code>A, B</code>&nbsp;有相同的长度，范围为&nbsp;<code>[1, 100]</code>。</li> 
// <li><code>A[i], B[i]</code> 都是范围在&nbsp;<code>[0, 10^5]</code> 的整数。</li> 
//</ol>
//
//<p>&nbsp;</p>
//
//<div><li>👍 44</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

import java.util.HashMap;

// 760.找出变位映射
// 开题时间：2022-11-16 19:38:17
public class FindAnagramMappings {
  public static void main(String[] args) {
    Solution solution = new FindAnagramMappings().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] anagramMappings9(int[] nums1, int[] nums2) {
      HashMap<Integer, Integer> val2idx = new HashMap<>();
      for (int i = 0; i < nums2.length; i++)
        val2idx.put(nums2[i], i);
      
      for (int i = 0; i < nums1.length; i++)
        nums1[i] = val2idx.get(nums1[i]);
      
      return nums1;
    }
    
    public int[] anagramMappings8(int[] nums1, int[] nums2) {
      int[] indices = new int[(int) 1e5];
      for (int i = 0; i < nums2.length; i++)
        indices[nums2[i]] = i;
      
      for (int i = 0; i < nums1.length; i++)
        nums1[i] = indices[nums1[i]];
      
      return nums1;
    }
    
    public int[] anagramMappings(int[] nums1, int[] nums2) {
      for (int i = 0; i < nums1.length; i++)
        for (int j = 0; j < nums2.length; j++)
          if (nums1[i] == nums2[j]) {
            nums1[i] = j;
            break;
          }
      return nums1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}