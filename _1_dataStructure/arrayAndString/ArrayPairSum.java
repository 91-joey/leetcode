package _1_dataStructure.arrayAndString;

import java.util.Arrays;

// 561. 数组拆分
public class ArrayPairSum {
  public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int sum = 0;
    for (int i = 0; i < nums.length; i += 2) {
      sum += nums[i];
    }
    return sum;
  }
}
