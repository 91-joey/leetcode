package org.example.leetcode.problems.dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.List;

//118. 杨辉三角
public class PascalTriangle {
    //1.自解    n^2 1
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> rows = new ArrayList<>(numRows);
        List<Integer> row = new ArrayList<>();
        row.add(1);
        rows.add(row);
        if (numRows > 1) {
            row = new ArrayList<>();
            row.add(1);
            row.add(1);
            rows.add(row);
        }
        for (int i = 2; i < numRows; i++) {
            row = new ArrayList<>();
            row.add(1);
            row.add(i);
            for (int j = 2; j <= i / 2; j++) {
                List<Integer> lastRow = rows.get(i - 1);
                row.add(lastRow.get(j - 1) + lastRow.get(j));
            }
            int size = row.size();
            if ((i + 1) % 2 == 0) {
                row.add(row.get(size - 1));
            }
            for (int j = size - 2; j >= 0; j--) {
                row.add(row.get(j));
            }
            rows.add(row);
        }
        return rows;
    }

//        2.官解    n^2 1
    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> rows = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    List<Integer> lastRow = rows.get(i - 1);
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
            }
            rows.add(row);
        }
        return rows;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangle().generate2(8));
    }
}

