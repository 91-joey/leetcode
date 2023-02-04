package org.example.leetcode.problems._1_dataStructure.arrayAndString;

// 498. 对角线遍历
public class DiagonalOrderOfMatrix {
  //    1.自解(单步走)    m*n m*n
  public static int[] findDiagonalOrder1(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    int[] ans = new int[m * n];
    int i = 0;
    int j = 0;
    boolean order = true;
    for (int x = 0; x < ans.length; x++) {
      ans[x] = mat[i][j];
      if (order) {
        if (i - 1 < 0 || j + 1 >= n) {
          if (i + j + 1 < n) {
            j++;
          } else {
            i++;
          }
          order = false;
        } else {
          i--;
          j++;
        }
      } else {
        if (i + 1 >= m || j - 1 < 0) {
          if (i + j + 1 < m) {
            i++;
          } else {
            j++;
          }
          order = true;
        } else {
          i++;
          j--;
        }
      }
    }
    return ans;
  }
  
  //    2.自解(单步走)_精简    m*n m*n
  public static int[] findDiagonalOrder2(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    int[] ans = new int[m * n];
    int i = 0;
    int j = 0;
    int limit = n;
    boolean order = true;
    boolean flag = false;
    for (int x = 0; x < ans.length; x++) {
      ans[x] = order ? mat[i][j] : mat[j][i];
      if (flag) {
        order = !order;
        limit = order ? n : m;
        int tmp = i;
        i = j;
        j = tmp;
        flag = false;
      }
      if (i - 1 < 0 || j + 1 >= limit) {
        if (i + j + 1 < limit) {
          j++;
        } else {
          i++;
        }
        flag = true;
      } else {
        i--;
        j++;
      }
    }
    return ans;
  }
  
  //    3.对角线遍历 m*n m*n
  public static int[] findDiagonalOrder3(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    int[] ans = new int[m * n];
    int idx = 0;
    for (int x = 0; x < m + n - 1; x++) {
      //            1.↗方向
      //            1.1确定起点
      int i = x < m ? x : m - 1;
      int j = x - i;
      //            1.2确定终点，循环遍历
      while (i >= 0 && j < n) {
        ans[idx++] = mat[i][j];
        i--;
        j++;
      }
      x++;
      
      //            2.↙方向
      //            2.1确定起点
      j = x < n ? x : n - 1;
      i = x - j;
      //            2.2确定终点，循环遍历
      while (j >= 0 && i < m) {
        ans[idx++] = mat[i][j];
        i++;
        j--;
      }
    }
    return ans;
  }
  
  //    4.对角线遍历_精简 m*n m*n
  public static int[] findDiagonalOrder4(int[][] mat) {
    int m = mat.length;
    int n = mat[0].length;
    int[] ans = new int[m * n];
    int idx = 0;
    //        true:↗  false:↙
    boolean order = true;
    for (int x = 0; x < m + n - 1; x++) {
      int limitA = order ? m : n;
      int limitB = order ? n : m;
      //            1.确定起点
      int i = x < limitA ? x : limitA - 1;
      int j = x - i;
      //            2.确定终点，循环遍历
      while (i >= 0 && j < limitB) {
        ans[idx++] = order ? mat[i][j] : mat[j][i];
        i--;
        j++;
      }
      //            3.反向
      order = !order;
    }
    return ans;
  }
  
  public static void main(String[] args) {
    findDiagonalOrder2(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
  }
}
