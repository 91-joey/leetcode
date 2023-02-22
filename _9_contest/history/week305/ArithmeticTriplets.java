package _9_contest.history.week305;

// 6136. 算术三元组的数目 (第 305 场周赛_题1)
public class ArithmeticTriplets {
  // 复杂解法
  public int arithmeticTriplets(int[] nums, int diff) {
    int length = nums.length;
    if (nums[length - 1] - nums[0] < 2 * diff)
      return 0;
    int cnt = 0;
    int lstJ = 0;
    int lstK = 1;
    outer:
    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[length - 1] - nums[i] < 2 * diff)
        break;
      for (int j = lstJ + 1; j < length - 1; j++) {
        if (nums[j] - nums[i] == diff) {
          lstJ = j;
          for (int k = lstK + 1; k < length; k++) {
            if (nums[k] - nums[j] == diff) {
              lstK = k;
              cnt++;
              continue outer;
            }
          }
        }
      }
    }
    return cnt;
  }
  
  // 哈希
  public int arithmeticTriplets2(int[] nums, int diff) {
    int cnt = 0;
    
    boolean[] exists = new boolean[201];
    for (int num : nums) {
      if (0 <= num - 2 * diff && exists[num - 2 * diff] && exists[num - diff])
        cnt++;
      exists[num] = true;
    }
    
    return cnt;
  }
  
  // 三指针?
  public int arithmeticTriplets3(int[] nums, int diff) {
    int cnt = 0;
    
    int length = nums.length;
    for (int i = 0, j = 1, k; j < length - 1; i++) {
      while (j < length - 1 && nums[j] - nums[i] < diff) j++;
      if (j < length - 1 && nums[j] - nums[i] == diff) {
        k = j + 1;
        while (k < length && nums[k] - nums[j] < diff) k++;
        if (k < length && nums[k] - nums[j] == diff) cnt++;
      }
    }
    
    return cnt;
  }
  
  // 三指针
  public int arithmeticTriplets4(int[] nums, int diff) {
    int cnt = 0;
    
    int length = nums.length;
    for (int i = 0, j = 1, k = 2; k < length; i++) {
      while (j < length - 1 && nums[j] - nums[i] < diff) j++;
      if (i == 0) k = j + 1;
      while (k < length && nums[k] - nums[j] < diff) k++;
      if (k < length && nums[j] - nums[i] == diff && nums[k] - nums[j] == diff)
        cnt++;
    }
    
    return cnt;
  }
}
