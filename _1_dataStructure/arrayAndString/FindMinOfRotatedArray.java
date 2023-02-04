package org.example.leetcode.problems._1_dataStructure.arrayAndString;

// 153. 寻找旋转排序数组中的最小值
public class FindMinOfRotatedArray {
  //    1.自解    n   1
  public int findMin(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] < nums[i - 1]) {
        return nums[i];
      }
    }
    return nums[0];
  }
  
  //    2.二分法   logn    1
  public int findMin2(int[] nums) {
    int l = 0;
    int r = nums.length - 1;
    while (l < r) {
      int mid = l + (r - l) / 2;
      //            mid恒!=r
      if (nums[mid] < nums[r]) {
        r = mid;
      } else {
        l = mid + 1;
      }
    }
    return nums[l];
  }
  
  public static void main(String[] args) {
    System.out.println(new FindMinOfRotatedArray().findMin2(new int[]{4, 5, 6, 7, 1, 2, 3}));
    System.out.println(new FindMinOfRotatedArray().findMin2(new int[]{11, 13, 15, 17}));// l<m<r向左半
    System.out.println(new FindMinOfRotatedArray().findMin2(new int[]{3, 4, 5, 1, 2}));
    System.out.println(new FindMinOfRotatedArray().findMin2(new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2}));// l<m>r向右半//3,4,5,1,2
    System.out.println(new FindMinOfRotatedArray().findMin2(new int[]{8, 9, 1, 2, 3, 4, 5, 6, 7}));// l>m<r向左半//4,5,1,2,3
  }
}
