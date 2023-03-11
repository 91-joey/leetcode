package _9_contest.history.week335;

import java.math.BigInteger;
import java.util.Arrays;

// 6309. Split the Array to Make Coprime Products
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(solution.findValidSplit(new int[]{4, 7, 15, 8, 3, 5}));
  }
  
  // import java.math.BigInteger;
  class Solution {
    /*
     * ☆☆☆☆☆ 质因数分解 + 贪心（跳跃问题）
     * 对于一个质因子 p，设它在数组中的最左和最右的位置为 left 和 right。
     * 那么答案是不能在区间 [left,right) 中的。注意区间右端点可能为答案。
     * 因此这题本质上和 55. 跳跃游戏 是类似的，找从 0 出发，最远遇到的区间右端点，即为答案。
     */
    public int findValidSplit(int[] arr) {
      int n = arr.length; // length of array
      
      int[] left = new int[1000000]; // 质因数第一次出现的位置
      Arrays.fill(left, -1);
      int[] right = new int[n]; // 左端点为 i 的区间（可能有多个不同的质因子的第一次出现的位置相同）的右端点的最大值
      
      for (int i = 0; i < n; i++) {
        int x = arr[i];
        // 质因数分解
        for (int d = 2; d * d <= x; d++) {
          if (x % d == 0) {
            // 更新区间端点
            updateEnds(left, right, i, d);
            
            do {
              x /= d;
            } while (x % d == 0);
          }
        }
        
        if (x > 1) {
          updateEnds(left, right, i, x);
        }
      }
      
      // rightmost 表示当且能走到的最远距离
      for (int i = 0, rightmost = 0; i < n; i++) {
        if (i > rightmost) {
          return rightmost;
        }
        rightmost = Math.max(rightmost, right[i]);
      }
      
      return -1;
    }
    
    private void updateEnds(int[] left, int[] right, int i, int d) {
      if (left[d] == -1) {
        left[d] = i;
      } else {
        right[left[d]] = i;
      }
    }
    
    public int findValidSplitX(int[] arr) {
      int n = arr.length; // length of array
      if (n < 2) return -1; // no valid split for single element array
      
      int gcd = 1;
      for (int i = 1, a = arr[0]; i < n; i++) {
        int x = gcd(a, arr[i]);
        gcd *= x;
        a /= x;
      }
      
      if (gcd == 1) {
        return 0;
      }
      
      for (int i = 1; i < n - 1; i++) {
        if (arr[i] % gcd == 0) {
          return i;
        }
        int x = gcd(gcd, arr[i]);
        gcd /= x;
      }
      
      return -1;
    }
    
    public int findValidSplitTLE(int[] arr) {
      // function to find smallest valid split index
      int n = arr.length; // length of array
      
      if (n < 2) return -1; // no valid split for single element array
      
      BigInteger leftProduct = BigInteger.valueOf(1); // initialize left subarray product with first element
      BigInteger rightProduct = BigInteger.valueOf(1); // initialize right subarray product
      for (int x : arr) {
        rightProduct = rightProduct.multiply(BigInteger.valueOf(x));
      }
      
      for (int i = 0; i < n - 1; i++) { // loop through possible split points from left to right
        leftProduct = leftProduct.multiply(BigInteger.valueOf(arr[i])); // update left subarray product with next element
        rightProduct = rightProduct.divide(BigInteger.valueOf(arr[i])); // update right subarray product
        
        if (leftProduct.gcd(rightProduct).equals(BigInteger.ONE)) {
          return i; // check if products are coprime and return index if true
        }
      }
      
      return -1; // no valid split found after checking all indices
    }
    
    public static int gcd(int a, int b) { // function to find GCD of two numbers
      if (b == 0) return a; // base case
      return gcd(b, a % b); // recursive case
    }
    
    public static boolean areCoprime(int a, int b) { // function to check if two numbers are coprime
      return gcd(a, b) == 1; // true if GCD is 1, false otherwise
    }
  }
}
