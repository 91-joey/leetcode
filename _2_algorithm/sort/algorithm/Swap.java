package org.example.leetcode.problems._2_algorithm.sort.algorithm;

public class Swap {
  
  public static void main(String[] args) {
    int a = 1;
    int b = 2;
    // a^a === 0
    System.out.println(a ^ a);
    // 0^b === b
    System.out.println(0 ^ b);
    
    System.out.println("Initialized:");
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    
    swap1(a, b);
    swap2(a, b);
    swap3(a, b);
    swap4(a, b);
    swap5(a, b);
  }
  
  // 交换法一：加法
  public static void swap1(int a, int b) {
    System.out.println("\n交换法一：加法");
    a = a + b;
    b = a - b;
    a = a - b;
    System.out.println("a = " + a);
    System.out.println("b = " + b);
  }
  
  // 交换法二：减法1
  public static void swap2(int a, int b) {
    System.out.println("\n交换法二：减法1");
    a = a - b;
    b = a + b;
    a = b - a;
    System.out.println("a = " + a);
    System.out.println("b = " + b);
  }
  
  // 交换法二：减法2
  public static void swap3(int a, int b) {
    System.out.println("\n交换法二：减法2");
    a = b - a;
    b = b - a;
    a = a + b;
    System.out.println("a = " + a);
    System.out.println("b = " + b);
  }
  
  // 交换法三：位运算（数值不会溢出）
  public static void swap4(int a, int b) {
    System.out.println("\n交换法三：位运算");
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    System.out.println("a = " + a);
    System.out.println("b = " + b);
  }
  
  // 交换法四：引入第三变量
  public static void swap5(int a, int b) {
    System.out.println("\n交换法四：引入第三变量");
    int tmp = a;
    a = b;
    b = tmp;
    System.out.println("a = " + a);
    System.out.println("b = " + b);
  }
  
}
