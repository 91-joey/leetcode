package _9_contest.week334;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    // System.out.println(solution.maxNumOfMarkedIndices(new int[]{9, 2, 5, 4}));
    System.out.println(solution.maxNumOfMarkedIndices(new int[]{42, 83, 48, 10, 24, 55, 9, 100, 10, 17, 17, 99, 51, 32, 16, 98, 99, 31, 28, 68, 71, 14, 64, 29, 15, 40}));
    // System.out.println(solution.maxNumOfMarkedIndices(new int[]{1, 78, 27, 48, 14, 86, 79, 68, 77, 20, 57, 21, 18, 67, 5, 51, 70, 85, 47, 56, 22, 79, 41, 8, 39, 81, 59, 74, 14, 45, 49, 15, 10, 28, 16, 77, 22, 65, 8, 36, 79, 94, 44, 80}));
  }
  
  class Solution {
  
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
    
    
    public int maxNumOfMarkedIndices9(int[] nums) {
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
    public int maxNumOfMarkedIndices(int[] nums) {
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
    public static int binarySearch(int[] arr, int l, int r, int target){
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
