package _1_dataStructure.arrayAndString;


// 1342. 将数字变成 0 的操作次数
public class NumberOfStepsToZero {
  //    1.模拟法
  public int numberOfSteps1(int num) {
    int steps = 0;
    if (num > 0) {
      while (num > 0) {
        steps += num & 1 + 1;
        num >>= 1;
      }
      steps--;
    }
    return steps;
  }
  
  //    2.二进制法
  public int numberOfSteps2(int num) {
    return num == 0 ? 0 : getIdxOfTop1(num) - 1 + getCntOf1(num);
  }
  
  private int getIdxOfTop1(int num) {
    for (int i = 30; i >= 0; i--) {
      if (num >> i == 1) {
        return i + 1;
      }
    }
    return -1;
  }
  
  private int getCntOf1(int num) {
    int cnt = 0;
    for (int i = 30; i >= 0; i--) {
      cnt += num >> i & 1;
    }
    return cnt;
  }
  
  /*
   * 3.二进制法优化
   *     - 二分法加速求解前导零数目
   *     - 分治法来加速求解二进制数位 1 的个数
   */
  public static int numberOfSteps3(int num) {
    return num == 0 ? 0 : getIdxOfTop1Optm(num) - 1 + getCntOf1Optm(num);
  }
  
  private static int getIdxOfTop1Optm(int num) {
    int cnt = 0;
    for (int i = 16; i >= 1; i = i / 2) {
      if (num >> (32 - i) == 0) {
        cnt += i;
        num <<= i;
      }
    }
    return 32 - cnt;
  }
  
  private static int getCntOf1Optm(int num) {
    for (int i = 1; i <= 16; i = i * 2) {
      int dup = (int) (Math.pow(2, i) - 1);
      int mask = dup;
      for (int j = 1; j < 16 / i; j++) {
        mask = (mask << (2 * i)) + dup;
      }
      num = (num & mask) + ((num >> i) & mask);
    }
    return num;
  }
  
  public static void main(String[] args) {
    System.out.println(numberOfSteps3(0x7FFFFFFF));
    System.out.println(Integer.toBinaryString(0));
    System.out.println(Integer.toBinaryString(-1).length());
    System.out.println(Integer.toBinaryString(-1023));
    System.out.println(Integer.toBinaryString(-2147483648));
    System.out.println(Integer.toBinaryString(1));
    System.out.println(Integer.toBinaryString(1024));
  }
}
