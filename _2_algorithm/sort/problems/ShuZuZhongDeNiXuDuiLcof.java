//<p>在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre><strong>输入</strong>: [7,5,6,4]
//<strong>输出</strong>: 5</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>限制：</strong></p>
//
//<p><code>0 &lt;= 数组长度 &lt;= 50000</code></p>
//
//<div><div>Related Topics</div><div><li>树状数组</li><li>线段树</li><li>数组</li><li>二分查找</li><li>分治</li><li>有序集合</li><li>归并排序</li></div></div><br><div><li>👍 853</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort.problems;

// 剑指 Offer 51.数组中的逆序对
// 开题时间：2022-09-26 06:42:46
public class ShuZuZhongDeNiXuDuiLcof {
  public static void main(String[] args) {
    Solution solution = new ShuZuZhongDeNiXuDuiLcof().new Solution();
    //        System.out.println(solution.reversePairs(new int[]{7, 5, 6, 4}));
    System.out.println(solution.reversePairs(new int[]{4, 5, 6, 7}));
    //        int[] merged = solution.merge(new int[]{1, 2, 3, 4, 5, 6}, new int[]{-2, -1, 0, 2, 3, 4});
    //        System.out.println("Arrays.toString(merged) = " + Arrays.toString(merged));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力    n^2 1
    public int reversePairs2(int[] nums) {
      int cnt = 0;
      
      int length = nums.length;
      for (int i = 0; i < length - 1; i++)
        for (int j = i + 1; j < length; j++)
          if (nums[i] > nums[j])
            cnt++;
      
      return cnt;
    }
    
    int cnt = 0;
    
    // 分治（归并排序）nlogn n
    public int reversePairs9(int[] nums) {
      int length = nums.length;
      if (length < 2) return 0;
      
      mergeSort(nums, 0, length - 1);
      
      return cnt;
    }
    
    private int[] mergeSort(int[] nums, int start, int end) {
      if (start == end) return new int[]{nums[start]};
      int mid = (start + end) >> 1;
      return merge(mergeSort(nums, start, mid),
          mergeSort(nums, mid + 1, end));
    }
    
    // 123456  -2 -1  0  2 3 4
    // 2*3+4+5+6+6
    private int[] merge(int[] left, int[] right) {
      int lengthL = left.length;
      int lengthR = right.length;
      int[] merged = new int[lengthL + lengthR];
      
      int l = 0, r = 0;
      while (l < lengthL && r < lengthR) {
                /*
                法一（复杂）：left中一个元素 对 right中多个元素
                int tmpL = l;
                while (r < lengthR && left[l] > right[r])
                    merged[l + r] = right[r++];
                if (r < lengthR)
                    while (l < lengthL && left[l] <= right[r])
                        merged[l + r] = left[l++];
                cnt += (l - tmpL) * r;
                */
        
        // region 法一（简单）：left中多个元素 对 right中一个元素
        if (left[l] <= right[r]) {
          merged[l + r] = left[l++];
        } else {
          cnt += lengthL - l;
          merged[l + r] = right[r++];
        }
        // endregion
      }
            /*
            法一尾部处理
            cnt += (lengthL - l) * r;
            */
      
      while (l < lengthL) merged[l + r] = left[l++];
      while (r < lengthR) merged[l + r] = right[r++];
      
      return merged;
    }
    
    
    public int reversePairs(int[] nums) {
      mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
      return cnt;
    }
    
    private void mergeSort(int[] arr, int start, int end, int[] tmp) {
      if (start >= end)
        return;
      
      int mid = start + end >> 1;
      mergeSort(arr, start, mid, tmp);
      mergeSort(arr, mid + 1, end, tmp);
      
      System.arraycopy(arr, start, tmp, start, end - start + 1);
      for (int i = start, l = start, r = mid + 1; i <= end; i++) {
        if (l > mid)
          arr[i] = tmp[r++];
        else if (r > end || tmp[l] <= tmp[r]) {
          arr[i] = tmp[l++];
          // 关键一步，其实计数逻辑写在 r变动时 或者 l变动时 都是可以的。
          cnt += r - mid - 1;
        } else
          arr[i] = tmp[r++];
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}