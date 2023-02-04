//<p>给你一个 <strong>严格升序排列</strong>&nbsp;的正整数数组 <code>arr</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>
//
//<p>请你找到这个数组里第&nbsp;<code>k</code>&nbsp;个缺失的正整数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [2,3,4,7,11], k = 5
//<strong>输出：</strong>9
//<strong>解释：</strong>缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [1,2,3,4], k = 2
//<strong>输出：</strong>6
//<strong>解释：</strong>缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr.length &lt;= 1000</code></li> 
// <li><code>1 &lt;= arr[i] &lt;= 1000</code></li> 
// <li><code>1 &lt;= k &lt;= 1000</code></li> 
// <li>对于所有&nbsp;<code>1 &lt;= i &lt; j &lt;= arr.length</code>&nbsp;的 <code>i</code>&nbsp;和 <code>j</code> 满足&nbsp;<code>arr[i] &lt; arr[j]</code>&nbsp;</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<p>你可以设计一个时间复杂度小于 O(n) 的算法解决此问题吗？</p>
//
//<div><li>👍 159</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

// 1539.第 k 个缺失的正整数
// 开题时间：2022-11-25 15:01:35
public class KthMissingPositiveNumber {
  public static void main(String[] args) {
    Solution solution = new KthMissingPositiveNumber().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int findKthPositive9(int[] arr, int k) {
      for (int i = 1, j = 0; i <= 2000; i++) {
        if (j >= arr.length || i != arr[j])
          k--;
        else
          j++;
        if (k == 0)
          return i;
      }
      return -1;
    }
    
    // BS
    public int findKthPositive8(int[] arr, int k) {
      int n = arr.length;
      if (k < arr[0])
        return k;
      if (arr[n - 1] - n < k)
        return k + n;
      
      int l = 0, r = n - 1;
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (k <= arr[mid] - mid - 1)
          r = mid;
        else
          l = mid + 1;
      }
      return r + k;
    }
    
    // BS(enhance)
    public int findKthPositive7(int[] arr, int k) {
      int l = 0, r = arr.length;
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (k <= arr[mid] - mid - 1)
          r = mid;
        else
          l = mid + 1;
      }
      return r + k;
    }
    
    // BL
    public int findKthPositive(int[] arr, int k) {
      for (int e : arr)
        if (e <= k)
          k++;
      return k;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}