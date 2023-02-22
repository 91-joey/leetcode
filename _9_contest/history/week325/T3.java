package _9_contest.history.week325;

import java.util.Arrays;

// 6271. Maximum Tastiness of Candy Basket
public class T3 {
  public static void main(String[] args) {
    //        System.out.println(maximumTastiness(new int[]{13, 5, 1, 8, 21, 2}, 3));
    System.out.println(maximumTastiness(new int[]{34, 116, 83, 15, 150, 56, 69, 42, 26}, 6));
  }
  
  // 二分 +　贪心
  public static int maximumTastiness(int[] price, int k) {
    Arrays.sort(price);
    int n = price.length;
    int l = Integer.MAX_VALUE, r = (price[n - 1] - price[0]) / (k - 1);
    for (int i = 1; i < n; i++)
      l = Math.min(l, price[i] - price[i - 1]);
    
    while (l < r) {
      int mid = ((r - l + 1) >> 1) + l;
      if (k > getCnt(price, mid))
        r = mid - 1;
      else
        l = mid;
    }
    return r;
  }
  
  private static int getCnt(int[] price, int x) {
    int cnt = 1;
    for (int l = 0, r = 1; r < price.length; r++)
      if (x <= price[r] - price[l]) {
        cnt++;
        l = r;
      }
    return cnt;
  }
  
}
