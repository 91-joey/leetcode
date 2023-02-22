package _1_dataStructure.arrayAndString;

import java.util.Arrays;

// 1672. 最富有客户的资产总量
public class MaximumWealth {
  public int maximumWealth9(int[][] accounts) {
    int max = Integer.MIN_VALUE;
    for (int[] account : accounts) {
      int cur = 0;
      for (int asset : account) {
        cur += asset;
      }
      max = Math.max(max, cur);
    }
    return max;
  }
  
  public int maximumWealth(int[][] accounts) {
    int max = 0;
    for (int[] account : accounts)
      max = Math.max(max, Arrays.stream(account).sum());
    return max;
  }
}
