package _2_algorithm.greedy;

/**
 * 738.单调递增的数字 <br>
 * 开题时间：2023-01-27 12:25:31
 */
public class MonotoneIncreasingDigits {
  public static void main(String[] args) {
    Solution solution = new MonotoneIncreasingDigits().new Solution();
    System.out.println(solution.monotoneIncreasingDigits(603253281));
    // System.out.println(solution.monotoneIncreasingDigits(10));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 贪心 + 递归（字符串遍历数位）
     * 12344321
     * 12344567321
     *
     * 1.若 n 已经是数位单调递增，直接返回
     * 2.找到第一个数位 i ，满足 digit[i] > digit[i+1]，数位 i 减去 1，后续数位填充 9
     * 3.递归判断
     */
    public int monotoneIncreasingDigits9(int n) {
      if (isMonotoneIncreasing(n)) {
        return n;
      }
      
      StringBuilder sb = new StringBuilder();
      String s = String.valueOf(n);
      for (int i = 0; i < s.length() - 1; i++) {
        char c = s.charAt(i);
        if (c > s.charAt(i + 1)) {
          sb.append((char) (c - 1))
              .append("9".repeat(s.length() - (i + 1)));
          break;
        } else {
          sb.append(c);
        }
      }
      
      return monotoneIncreasingDigits(Integer.parseInt(sb.toString()));
    }
    
    // 贪心 + 递归（数学运算遍历数位）
    public int monotoneIncreasingDigits8(int n) {
      if (isMonotoneIncreasing(n)) {
        return n;
      }
      
      int ans = 0;
      int pre = 0;
      for (int i = (int) 1e9; i > 0; i /= 10) {
        int digit = n / i;
        if (pre > digit) {
          break;
        }
        ans += digit * i;
        pre = digit;
        n %= i;
      }
      
      return monotoneIncreasingDigits(ans - 1);
    }
    
    //☆☆☆☆☆ 形如 11……11 的数字累加
    public int monotoneIncreasingDigits(int n) {
      int ans = 0;
      int ones = 1_1111_1111;
      for (int i = 0; i < 9; i++) {
        while (ans + ones > n) {
          ones /= 10;
        }
        ans += ones;
      }
      return ans;
    }
    
    /**
     * 判断整数是否数位单调递增.
     */
    public static boolean isMonotoneIncreasing(int n) {
      int next = 10;
      for (int i = n; i != 0; i /= 10) {
        int digit = i % 10;
        if (digit > next) {
          return false;
        }
        next = digit;
      }
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}