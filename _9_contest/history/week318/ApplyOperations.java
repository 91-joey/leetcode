package _9_contest.history.week318;

import java.util.Arrays;

// 6229. Apply Operations to an Array
public class ApplyOperations {
  public int[] applyOperations(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == nums[i + 1]) {
        nums[i] = nums[i] << 1;
        nums[i + 1] = 0;
      }
    }
    
    int idx = 0;
    for (int num : nums)
      if (num != 0)
        nums[idx++] = num;
    for (int i = idx; i < nums.length; i++)
      nums[i] = 0;
    return nums;
  }
  
  public int[] applyOperations2(int[] nums) {
    int idx = 0;
    for (int i = 0; i < nums.length - 1; i++)
      if (nums[i] != 0)
        nums[idx++] = nums[i] == nums[i + 1] ?
            nums[i++] << 1 :
            nums[i];
    
    if (nums[nums.length - 2] == 0 || (idx > 0 && nums[nums.length - 1] << 1 != nums[idx - 1]))
      nums[idx++] = nums[nums.length - 1];
    
    Arrays.fill(nums, idx, nums.length, 0);
    return nums;
  }
}
