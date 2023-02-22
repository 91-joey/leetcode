// 已知一个长度为 <code>n</code> 的数组，预先按照升序排列，经由 <code>1</code> 到 <code>n</code> 次 <strong>旋转</strong> 后，得到输入数组。例如，原数组 <code>nums = [0,1,4,4,5,6,7]</code> 在变化后可能得到：
//
//<ul> 
// <li>若旋转 <code>4</code> 次，则可以得到 <code>[4,5,6,7,0,1,4]</code></li> 
// <li>若旋转 <code>7</code> 次，则可以得到 <code>[0,1,4,4,5,6,7]</code></li> 
//</ul>
//
//<p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> <strong>旋转一次</strong> 的结果为数组 <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code> 。</p>
//
//<p>给你一个可能存在 <strong>重复</strong> 元素值的数组 <code>nums</code> ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 <strong>最小元素</strong> 。</p>
//
//<p>你必须尽可能减少整个过程的操作步骤。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,3,5]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,2,2,0,1]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == nums.length</code></li> 
// <li><code>1 &lt;= n &lt;= 5000</code></li> 
// <li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li> 
// <li><code>nums</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>这道题与 <a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/">寻找旋转排序数组中的最小值</a> 类似，但 <code>nums</code> 可能包含重复元素。允许重复会影响算法的时间复杂度吗？会如何影响，为什么？</p>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 554</li><li>👎 0</li></div>
package _2_algorithm.binarySearch;

// 154.寻找旋转排序数组中的最小值 II
// 开题时间：2022-11-02 17:38:04
public class FindMinimumInRotatedSortedArrayIi {
  public static void main(String[] args) {
    Solution solution = new FindMinimumInRotatedSortedArrayIi().new Solution();
    //        System.out.println(solution.findMin2(new int[]{4, 5, 6, 7, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4}));
    //        System.out.println(solution.findMin2(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 0, 4, 4}));
    //        System.out.println(solution.findMin(new int[]{10, 1, 10, 10, 10}));
    System.out.println(solution.findMin(new int[]{2, 2, 2, 0, 2, 2}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int findMin2(int[] nums) {
      for (int i = 0; i < nums.length - 1; i++)
        if (nums[i] > nums[i + 1])
          return nums[i + 1];
      return nums[0];
    }
    
    public int findMin(int[] nums) {
      int l = 0, r = nums.length - 1;
      while (l < r && nums[l] == nums[r])
        l++;
      while (l < r) {
        int mid = l + r >> 1;
        if (nums[mid] <= nums[r])
          r = mid;
        else
          l = mid + 1;
      }
      return nums[r];
    }
    
    //☆☆☆☆☆ 直接二分，相等时不再折半缩小搜索区间、改为迭代。
    public int findMin3(int[] nums) {
      int l = 0, r = nums.length - 1;
      while (l < r) {
        int mid = l + r >> 1;
        if (nums[mid] == nums[r])
          // 精髓之处
          r--;
        else if (nums[mid] < nums[r])
          r = mid;
        else
          l = mid + 1;
      }
      return nums[r];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}