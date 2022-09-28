package org.example.leetcode.problems.dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle2 {
    //    1.递推(2个数组)  n^2 1
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return pre;
    }

    //    2.递推(1个数组)  n^2 1
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            row.add(0);
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    //    Cmn=C(m-1)n * (n-m+1)/m
    public List<Integer> getRow3(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangle2().getRow3(33));
    }
}
