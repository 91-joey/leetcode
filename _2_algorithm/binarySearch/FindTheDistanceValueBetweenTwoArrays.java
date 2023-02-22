//<p>给你两个整数数组&nbsp;<code>arr1</code>&nbsp;，&nbsp;<code>arr2</code>&nbsp;和一个整数&nbsp;<code>d</code>&nbsp;，请你返回两个数组之间的&nbsp;<strong>距离值</strong>&nbsp;。</p>
//
//<p>「<strong>距离值</strong>」<strong>&nbsp;</strong>定义为符合此距离要求的元素数目：对于元素&nbsp;<code>arr1[i]</code>&nbsp;，不存在任何元素&nbsp;<code>arr2[j]</code>&nbsp;满足 <code>|arr1[i]-arr2[j]| &lt;= d</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
//<strong>输出：</strong>2
//<strong>解释：</strong>
// 对于 arr1[0]=4 我们有：
//|4-10|=6 &gt; d=2 
//|4-9|=5 &gt; d=2 
//|4-1|=3 &gt; d=2 
//|4-8|=4 &gt; d=2 
// 所以 arr1[0]=4 符合距离要求
//
// 对于 arr1[1]=5 我们有：
//|5-10|=5 &gt; d=2 
//|5-9|=4 &gt; d=2 
//|5-1|=4 &gt; d=2 
//|5-8|=3 &gt; d=2
// 所以 arr1[1]=5 也符合距离要求
//
// 对于 arr1[2]=8 我们有：
//<strong>|8-10|=2 &lt;= d=2</strong>
//<strong>|8-9|=1 &lt;= d=2</strong>
//|8-1|=7 &gt; d=2
//<strong>|8-8|=0 &lt;= d=2</strong>
// 存在距离小于等于 2 的情况，不符合距离要求
//
// 故而只有 arr1[0]=4 和 arr1[1]=5 两个符合距离要求，距离值为 2</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr1.length, arr2.length &lt;= 500</code></li> 
// <li><code>-10^3 &lt;= arr1[i], arr2[j] &lt;= 10^3</code></li> 
// <li><code>0 &lt;= d &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 89</li><li>👎 0</li></div>
package _2_algorithm.binarySearch;

import java.util.Arrays;

// 1385.两个数组间的距离值
// 开题时间：2022-11-22 11:39:16
public class FindTheDistanceValueBetweenTwoArrays {
  public static void main(String[] args) {
    Solution solution = new FindTheDistanceValueBetweenTwoArrays().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // BL
    public int findTheDistanceValue9(int[] arr1, int[] arr2, int d) {
      int cnt = arr1.length;
      for (int a : arr1)
        for (int b : arr2)
          if (Math.abs(a - b) <= d) {
            cnt--;
            break;
          }
      return cnt;
    }
    
    // 二分
    public int findTheDistanceValue8(int[] arr1, int[] arr2, int d) {
      Arrays.sort(arr2);
      
      int cnt = 0;
      for (int t : arr1) {
        int l = 0, r = arr2.length - 1;
        while (l < r) {
          int mid = ((r - l) >> 1) + l;
          if (t <= arr2[mid])
            r = mid;
          else
            l = mid + 1;
        }
        if (Math.abs(arr2[r] - t) > d &&
            (r <= 0 || Math.abs(t - arr2[r - 1]) > d))
          cnt++;
      }
      return cnt;
    }
    
    // 二分（优化）
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
      Arrays.sort(arr2);
      
      int cnt = 0;
      for (int t : arr1)
        if (isAllOutOfDistance(arr2, t - d, t + d))
          cnt++;
      return cnt;
    }
    
    private boolean isAllOutOfDistance(int[] arr, int low, int high) {
      for (int l = 0, r = arr.length - 1; l <= r; ) {
        int mid = ((r - l) >> 1) + l;
        if (low <= arr[mid] && arr[mid] <= high)
          return false;
        else if (low > arr[mid])
          l = mid + 1;
        else
          r = mid - 1;
      }
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}