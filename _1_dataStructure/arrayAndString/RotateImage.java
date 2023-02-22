package _1_dataStructure.arrayAndString;

// 48. 旋转图像
public class RotateImage {
  //    1.辅助数组    N*N N*N
  public static void rotate1(int[][] matrix) {
    int length = matrix.length;
    int[][] res = new int[length][length];
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        res[j][length - i - 1] = matrix[i][j];
      }
    }
    System.arraycopy(res, 0, matrix, 0, length);
  }
  
  //    2.原地旋转    N*N 1
  public static void rotate2(int[][] matrix) {
    int length = matrix.length;
    //        for (int i = 0; i < (length+1)/2; i++) {
    //            for (int j = 0; j < length/2; j++) {
    for (int i = 0; i < length / 2; i++) {
      for (int j = 0; j < (length + 1) / 2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[length - j - 1][i];
        matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
        matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
        matrix[j][length - i - 1] = temp;
      }
    }
  }
  
  //    3.原地翻转2次  N*N 1
  public static void rotate3(int[][] matrix) {
    int length = matrix.length;
    //        a.水平翻转
    for (int i = 0; i < length / 2; i++) {
      for (int j = 0; j < length; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[length - i - 1][j];
        matrix[length - i - 1][j] = temp;
      }
    }
    //        b.'\'对角线翻转
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < i; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
  }
  
  public static void main(String[] args) {
    rotate1(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
  }
}
