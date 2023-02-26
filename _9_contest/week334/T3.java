package _9_contest.week334;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 6367. Find the Maximum Number of Marked Indices
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    // System.out.println(solution.maxNumOfMarkedIndices(new int[]{9, 2, 5, 4}));
    System.out.println(solution.maxNumOfMarkedIndices(new int[]{57, 40, 57, 51, 90, 51, 68, 100, 24, 39, 11, 85, 2, 22, 67, 29, 74, 82, 10, 96, 14, 35, 25, 76, 26, 54, 29, 44, 63, 49, 73, 50, 95, 89, 43, 62, 24, 88, 88, 36, 6, 16, 14, 2, 42, 42, 60, 25, 4, 58, 23, 22, 27, 26, 3, 79, 64, 20, 92}));
    // System.out.println(solution.maxNumOfMarkedIndices(new int[]{42, 83, 48, 10, 24, 55, 9, 100, 10, 17, 17, 99, 51, 32, 16, 98, 99, 31, 28, 68, 71, 14, 64, 29, 15, 40}));
    // System.out.println(solution.maxNumOfMarkedIndices(new int[]{1, 78, 27, 48, 14, 86, 79, 68, 77, 20, 57, 21, 18, 67, 5, 51, 70, 85, 47, 56, 22, 79, 41, 8, 39, 81, 59, 74, 14, 45, 49, 15, 10, 28, 16, 77, 22, 65, 8, 36, 79, 94, 44, 80}));
  }
  
  class Solution {
    private int[] nums;
  
    // ☆☆☆ 排序 + 贪心 + 二分
    public int maxNumOfMarkedIndices9(int[] nums) {
      Arrays.sort(nums);
      this.nums = nums;
      int n = nums.length;
      int l = 0;
      int r = n / 2;
      while (l < r) {
        int mid = ((r - l + 1) >> 1) + l;
        if (canMarkPairs(mid)) {
          l = mid;
        } else {
          r = mid - 1;
        }
      }
      return r * 2;
    }
    
    private boolean canMarkPairs(int k) {
      for (int i = 0; i < k; i++) {
        if (nums[i] * 2 > nums[nums.length - k + i]) {
          return false;
        }
      }
      return true;
    }
    
    // ☆☆☆☆☆ 排序 + 贪心 + 双指针：右指针的起点为$\lceil n/2 \rceil$，若取$\lfloor n/2 \rfloor$，则可能会导致 $nums[n/2]$ 作为 `j` 匹配了一次，作为 `i` 又匹配了一次。
    public int maxNumOfMarkedIndices(int[] nums) {
      Arrays.sort(nums);
      int n = nums.length;
      int l = 0;
      for (int r = (n + 1) / 2; r < n; r++) {
        if (nums[l] * 2 <= nums[r]) {
          l++;
        }
      }
      return l * 2;
    }
    
    public int maxNumOfMarkedIndicesX(int[] nums) {
      Arrays.sort(nums);
      int n = nums.length;
      boolean[] vis = new boolean[n];
      int ans = 0;
      for (int i = 0, l = 1; i < n; i++) {
        if (!vis[i]) {
          int search = binarySearch(nums, l, n, 2 * nums[i]);
          if (search == n) {
            break;
          }
          ans += 2;
          if (search != n - 1) {
            boolean a = true;
            for (int j = i + 1; j < search; j++) {
              if (!vis[j]) {
                // i = j - 1;
                a = false;
                break;
              }
            }
            if (a) {
              search++;
            }
          }
          vis[i] = true;
          vis[search] = true;
          l = search + 1;
        }
      }
      return ans;
    }
    
    
    public int maxNumOfMarkedIndicesXX(int[] nums) {
      Arrays.sort(nums);
      PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(o -> o[0]).reversed());
      int n = nums.length;
      boolean[] vis = new boolean[n];
      for (int i = 0; i < n; i++) {
        int[] arr = new int[]{nums[i], i};
        pq.add(arr);
      }
      int ans = 0;
      for (int i = n - 1; i >= 0; i--) {
        if (!vis[i]) {
          vis[i] = true;
          while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            if (poll[0] * 2 <= nums[i]) {
              ans += 2;
              vis[poll[1]] = true;
              break;
            }
          }
        }
      }
      return ans;
    }
    
    public int maxNumOfMarkedIndicesXXX(int[] nums) {
      Arrays.sort(nums);
      int n = nums.length;
      boolean[] vis = new boolean[n];
      int ans = 0;
      for (int i = 0, l = 1; i < n; i++) {
        if (!vis[i]) {
          int search = binarySearch(nums, l, n, 4 * nums[i]);
          if (search == n) {
            break;
          }
          ans += 2;
          // if (search != n - 1) {
          //   boolean a = true;
          //   for (int j = i + 1; j < search; j++) {
          //     if (!vis[j]) {
          //       // i = j - 1;
          //       a = false;
          //       break;
          //     }
          //   }
          //   if (a) {
          //     search++;
          //   }
          // }
          vis[i] = true;
          vis[search] = true;
          l = search + 1;
        }
      }
      return ans;
    }
    
    // public static int binarySearch(int[] arr, int l, int r, int target) {
    //   while (l < r) {
    //     int mid = ((r - l) >> 1) + l;
    //     if (target <= arr[mid])
    //       r = mid;
    //     else
    //       l = mid + 1;
    //   }
    //   return r;
    // }
    public static int binarySearch(int[] arr, int l, int r, int target) {
      while (l < r) {
        int mid = ((r - l + 1) >> 1) + l;
        if (target <= arr[mid])
          r = mid - 1;
        else
          l = mid;
      }
      return r;
    }
    
    
  }
}
