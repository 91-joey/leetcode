// 符合下列属性的数组 <code>arr</code> 称为 <strong>山脉数组</strong> ：
//
//<ul> 
// <li><code>arr.length &gt;= 3</code></li> 
// <li>存在 <code>i</code>（<code>0 &lt; i&nbsp;&lt; arr.length - 1</code>）使得： 
//  <ul> 
//   <li><code>arr[0] &lt; arr[1] &lt; ... arr[i-1] &lt; arr[i] </code></li> 
//   <li><code>arr[i] &gt; arr[i+1] &gt; ... &gt; arr[arr.length - 1]</code></li> 
//  </ul> </li> 
//</ul>
//
//<p>给你由整数组成的山脉数组 <code>arr</code> ，返回任何满足 <code>arr[0] &lt; arr[1] &lt; ... arr[i - 1] &lt; arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code> 的下标 <code>i</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [0,1,0]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [0,2,1,0]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [0,10,5,2]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [3,4,5,1]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [24,69,100,99,79,78,67,36,26,19]
//<strong>输出：</strong>2
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>3 &lt;= arr.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= arr[i] &lt;= 10<sup>6</sup></code></li> 
// <li>题目数据保证 <code>arr</code> 是一个山脉数组</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>很容易想到时间复杂度 <code>O(n)</code> 的解决方案，你可以设计一个 <code>O(log(n))</code> 的解决方案吗？</p>
//
//<div><li>👍 316</li><li>👎 0</li></div>
package _2_algorithm.binarySearch;

// 852.山脉数组的峰顶索引
// 开题时间：2022-11-21 10:55:51
public class PeakIndexInAMountainArray {
  public static void main(String[] args) {
    Solution solution = new PeakIndexInAMountainArray().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int peakIndexInMountainArray(int[] arr) {
      int l = 1, r = arr.length - 2;
      while (l < r) {
        int mid = l + r >> 1;
        if (arr[mid] > arr[mid + 1])
          r = mid;
        else
          l = mid + 1;
      }
      return r;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}