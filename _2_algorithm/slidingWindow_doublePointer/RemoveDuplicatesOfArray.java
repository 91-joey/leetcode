package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 26. 删除有序数组中的重复项
public class RemoveDuplicatesOfArray {
  //☆☆☆☆☆1.自解(双指针)   n   1
  //  all in [0,nextDistinctIdx] are distinct
  public int removeDuplicates(int[] nums) {
    //        nextDistinctIdx存放重复值组的第一个值
    int nextDistinctIdx = 0;
    //        i找寻下一个重复值组
    for (int i = 1; i < nums.length; i++) {
      //            找到下一个重复值组
      if (nums[nextDistinctIdx] != nums[i]) {
        //                增加索引值
        //                存放重复值组的第一个值
        nums[++nextDistinctIdx] = nums[i];
      }
    }
    return nextDistinctIdx + 1;
  }
  
  //    1.官解(双指针)   n   1
  //  all in [0,nextDistinctIdx) are distinct
  public int removeDuplicates2(int[] nums) {
    //        nextDistinctIdx存放重复值组的第一个值
    int nextDistinctIdx = 1;
    //        i找寻下一个重复值组
    for (int i = 1; i < nums.length; i++) {
      //            找到下一个重复值组
      //            if (nums[i - 1] != nums[i]) {
      if (nums[nextDistinctIdx - 1] != nums[i]) {
        //                增加索引值
        //                存放重复值组的第一个值
        nums[nextDistinctIdx++] = nums[i];
      }
    }
    return nextDistinctIdx;
  }
  
  public static void main(String[] args) {
    System.out.println(new RemoveDuplicatesOfArray().removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    //        0, 0, 1, 1, 1, 2, 2, 3, 3, 4
    //        |  |
    //        |     |
    //           |  |
    //        0, 1, 1, 1, 1, 2, 2, 3, 3, 4
    //           |     |
    //           |        |
    //           |           |
    //              |        |
    //        0, 1, 2, 1, 1, 2, 2, 3, 3, 4
    //              |           |
    //              |              |
    //                 |           |
    //        0, 1, 2, 3, 1, 2, 2, 3, 3, 4
    //                 |              |
    //                 |                 |
    //        0, 1, 2, 3, 4, 2, 2, 3, 3, 4
  }
}
