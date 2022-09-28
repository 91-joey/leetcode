package org.example.leetcode.problems.dataStructure.arrayAndString;

import java.util.HashSet;
import java.util.Set;

//面试题 01.08. 零矩阵
public class SetZeroesOfMatrix {
    //1.使用矩阵拷贝  m*n*(m+n)   m*n
    public static void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(matrix[i], 0, ans[i], 0, n);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < n; k++) {
                        ans[i][k] = 0;
                    }
                    for (int k = 0; k < m; k++) {
                        ans[k][j] = 0;
                    }
                }
            }
        }
        System.arraycopy(ans, 0, matrix, 0, m);
    }

    //2.使用两个Set   m*n*(m+n)   m+n
    public static void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

//        for (Integer row : rows) {
//            for (int k = 0; k < n; k++) {
//                matrix[row][k] = 0;
//            }
//        }
//        for (Integer col : cols) {
//            for (int k = 0; k < m; k++) {
//                matrix[k][col] = 0;
//            }
//        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    //3.使用两个标记数组  m*n m+n
    public static void setZeroes3(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    //4.使用2个标记变量  m*n 1
    public static void setZeroes4(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean col0 = false;
        boolean row0 = false;
//        1.row0 & col0 set zero?
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                row0 = true;
                break;
            }
        }
//        2.row1+ & col1+ set zero?   ->  store in row0 & col0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
//        3.store in row0 & col0  ->  set zero for row1+ & col1+
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
//        4.set zero for row0 & col0
        if (row0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    //5.使用1个标记变量  m*n 1
    public static void setZeroes5(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean col0 = false;
//      1.col0 set zero?
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
                break;
            }
        }
//      2.row0+ & col1+ set zero?   ->  store in row0 & col0
//          row0 set zero?  -> store in matrix[0][0]
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
//      3.store in row0 & col0  ->  set zero for row1+ & col1+
//          store in matrix[0][0]   ->  set zero for row0
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
//      4.set zero for col0
        if (col0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        setZeroes1(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
    }
}
