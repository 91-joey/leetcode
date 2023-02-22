package _9_contest.history.biweek96;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// 2540. Minimum Common Value
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    //☆☆☆☆☆ 双指针
    public int getCommon9(int[] nums1, int[] nums2) {
      int i = 0, j = 0;
      while (i < nums1.length && j < nums2.length) {
        if (nums1[i] == nums2[j]) {
          return nums1[i];
        } else if (nums1[i] < nums2[j]) {
          i++;
        } else {
          j++;
        }
      }
      return -1;
    }
    
    // 哈希表
    public int getCommon(int[] nums1, int[] nums2) {
      Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
      return Arrays.stream(nums2).filter(set::contains).findFirst().orElse(-1);
    }
  }
}
