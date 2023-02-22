package _9_contest.history.week317;

// 6222. 美丽整数的最小增量
public class MakeIntegerBeautiful {
  public static void main(String[] args) {
    MakeIntegerBeautiful makeIntegerBeautiful = new MakeIntegerBeautiful();
    //        System.out.println(makeIntegerBeautiful.makeIntegerBeautiful2(16L, 6));
    //        System.out.println(makeIntegerBeautiful.makeIntegerBeautiful2(19L, 1));
    //        System.out.println(makeIntegerBeautiful.makeIntegerBeautiful2(6068060761L, 3));
    System.out.println(makeIntegerBeautiful.makeIntegerBeautiful2(9999_9999_9999L, 107));
    //        System.out.println(makeIntegerBeautiful.makeIntegerBeautiful(123456L, 7));
  }
  
  // 暴力
  public long makeIntegerBeautiful(long n, int target) {
    if (target >= 108)
      return 0;
    
    int sum = 0;
    long[] digits = new long[12];
    int idx = 11;
    for (long i = n; i != 0; i /= 10) {
      long digit = i % 10;
      digits[idx--] = digit;
      sum += digit;
    }
    if (sum <= target)
      return 0;
    
    //        int sum2 = 1;
    //        while (sum2 < target) {
    //            sum2 += digits[++idx];
    //        }
    
    for (long i = n; i <= 1_0000_0000_0000L; i++) {
      sum = 0;
      for (long j = i; j != 0; j /= 10) {
        sum += j % 10;
      }
      if (sum <= target)
        return i - n;
    }
    
    return -1;
  }
  
  //☆☆☆☆☆ 贪心
  public long makeIntegerBeautiful2(long n, int target) {
    // 当 n = 9999_9999_9999L 时，数字位和最大，为 9*12=108
    if (target >= 108)
      return 0;
    
    /*
     * 12345 1
     * n/10*10+10                12350
     * n/100*100+100             12400
     * n/1000*1000+1000          13000
     * n/10000*10000+10000       20000
     * n/100000*100000+100000   100000
     * 1000000==n*10
     */
    long i = 10;
    long m = n;
    do {
      int sum = 0;
      for (long j = m; j != 0; j /= 10)
        sum += j % 10;
      if (sum <= target)
        return m - n;
      
      m = m / i * i + i;
      i *= 10;
    } while (i != m * 10);
    
    return m - n;
  }
}
