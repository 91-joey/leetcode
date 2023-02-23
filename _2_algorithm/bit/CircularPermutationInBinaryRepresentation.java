package _2_algorithm.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1238.循环码排列 <br>
 * 开题时间：2023-02-23 08:53:26
 */
public class CircularPermutationInBinaryRepresentation {
  public static void main(String[] args) {
    Solution solution = new CircularPermutationInBinaryRepresentation().new Solution();
    System.out.println(solution.circularPermutation(2, 3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 镜面反射 + 数组轮转（查找 start 所在索引，另起数组轮转）
    public List<Integer> circularPermutation9(int n, int start) {
      ArrayList<Integer> list = new ArrayList<Integer>();
      list.add(0);
      
      for (int i = 0; i < n; i++) {
        int lst = list.size() - 1;
        for (int j = lst; j >= 0; j--) {
          list.add(list.get(j) | (1 << i));
        }
      }
      
      int idx = 0;
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i) == start) {
          idx = i;
          break;
        }
      }
      
      ArrayList<Integer> ans = new ArrayList<Integer>();
      for (int i = 0; i < list.size(); i++) {
        ans.add(list.get((idx + i) % list.size()));
      }
      
      return ans;
    }
    
    
    // 镜面反射 + 数组轮转（优化：边进行镜面反射，边查找 start 所在索引，原地数组轮转）
    public List<Integer> circularPermutation8(int n, int start) {
      int size = 1 << n;
      int[] arr = new int[size];
      
      int startIdx = 0;
      for (int i = 0, idx = 1; i < n; i++) {
        for (int l = (1 << i) - 1; l >= 0; l--) {
          if ((arr[idx] = arr[l] | (1 << i)) == start) {
            startIdx = idx;
          }
          idx++;
        }
      }
      
      rotate(arr, -startIdx);
      
      return Arrays.stream(arr).boxed().toList();
    }
    
    // 镜面反射 + 异或运算
    public List<Integer> circularPermutation7(int n, int start) {
      ArrayList<Integer> ans = new ArrayList<>() {{
        add(start);
      }};
      
      for (int i = 0; i < n; i++)
        for (int mask = ans.size(), j = mask - 1; j >= 0; j--)
          ans.add(((ans.get(j) ^ start) | mask) ^ start);
      
      return ans;
    }
  
    // ☆☆☆☆☆ 公式法 + 异或运算
    public List<Integer> circularPermutation(int n, int start) {
      List<Integer> ans = new ArrayList<>();
      for (int i = 0, bound = 1 << n; i < bound; i++)
        ans.add(i ^ (i >> 1) ^ start);
      return ans;
    }
    
    /**
     * 数组向右轮转 k 个位置
     */
    public static void rotate(int[] nums, int k) {
      int n = nums.length;
      k = (k + n) % n;
      reverse(nums, 0, n - 1);
      reverse(nums, 0, k - 1);
      reverse(nums, k, n - 1);
    }
    
    /**
     * 翻转数组
     */
    public static void reverse(int[] nums, int l, int r) {
      while (l < r) {
        int tmp = nums[l];
        nums[l++] = nums[r];
        nums[r--] = tmp;
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}