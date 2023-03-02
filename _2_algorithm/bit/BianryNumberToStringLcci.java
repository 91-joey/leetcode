package _2_algorithm.bit;

/**
 * 面试题 05.02.二进制数转字符串 <br>
 * 开题时间：2023-03-02 06:43:53
 */
public class BianryNumberToStringLcci {
  public static void main(String[] args) {
    Solution solution = new BianryNumberToStringLcci().new Solution();
    System.out.println(solution.printBin(0.625));
    // System.out.println(solution.printBin(0.078125));
    // System.out.println(Long.MAX_VALUE);
    // System.out.println(Double.toString(0.625));
    // System.out.println(Double.toHexString(0.625));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 转换整数（乘以2^30）
    public String printBin9(double num) {
      num = num * (1 << 30);
      int x = (int) num;
      if (num != x) {
        return "ERROR";
      }
      
      String s = Integer.toBinaryString(x);
      return "0." +
          "0".repeat(30 - s.length()) + // 前补 0
          s.substring(0, s.length() - Integer.numberOfTrailingZeros(x)); // 去除后缀 0
    }
    
    // 幂运算
    public String printBin8(double num) {
      double base = 0.5;
      StringBuilder sb = new StringBuilder("0.");
      // for (int i = 0; i < 30; i++, base *= 0.5) {
      for (int i = 0; i < 6; i++, base *= 0.5) {
        if (base < num) {
          num -= base;
          sb.append("1");
        } else if (base == num) {
          sb.append("1");
          return sb.toString();
        } else {
          sb.append("0");
        }
      }
      
      return "ERROR";
    }
    
    /*
     * ☆☆☆☆☆ 转换二进制数
     * 前置知识：任何进制表示的小数，乘上进制等价于小数点往右移一位
     * 步骤：
     *  1.设 num 的二进制表示为 0.b1b2...bk，乘以 2 得到 b1.b2...bk
     *  2.与 1 比较大小，即可得出 b1
     *  3.减去 b1
     *  重复上述过程 30 次（本题小数位数最多 6 位，实际只需重复 6 次）
     */
    public String printBin(double num) {
      StringBuilder sb = new StringBuilder("0.");
      // for (int i = 0; i < 30; i++) {
      for (int i = 0; i < 6; i++) {
        num *= 2;
        if (num >= 1) {
          sb.append("1");
          if (--num == 0) {
            return sb.toString();
          }
        } else {
          sb.append("0");
        }
      }
      return "ERROR";
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}