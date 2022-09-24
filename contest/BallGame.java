package org.example.leetcode.problems.contest;

import java.util.*;

public class BallGame {
    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(ballGame(4, new String[]{"..E.", ".EOW", "..W."})));
//        System.out.println(Arrays.deepToString(ballGame(5, new String[]{".....", "..E..", ".WO..", "....."})));
//        System.out.println(Arrays.deepToString(ballGame(3, new String[]{".....", "....O", "....O", "....."})));
//        System.out.println(Arrays.deepToString(ballGame(6, new String[]{
//                "....",
//                ".EE.",
//                "O.E.",
//                "...."})));
        System.out.println(Arrays.deepToString(ballGame(41, new String[]{
                "E...W..WW",
                ".E...O...",
                "...WO...W",
                "..OWW.O..",
                ".W.WO.W.E",
                "O..O.W...",
                ".OO...W..",
                "..EW.WEE."
        })));
    }

    public static final int[][] DIRECTIONS = {
            {1, 0},
            {0, -1},
            {-1, 0},
            {0, 1}
    };

    public static int[][] ballGame(int num, String[] plate) {
        List<int[]> ans = new ArrayList<>();

        int m = plate.length;
        int n = plate[0].length();
        for (int col = 1; col < n - 1; col++) {
            if (canDoIt(num, plate, 0, col, 0)) ans.add(new int[]{0, col});
            if (1 < m && canDoIt(num, plate, m - 1, col, 2)) ans.add(new int[]{m - 1, col});
        }
        for (int row = 1; row < m - 1; row++) {
            if (canDoIt(num, plate, row, 0, 3)) ans.add(new int[]{row, 0});
            if (1 < n && canDoIt(num, plate, row, n - 1, 1)) ans.add(new int[]{row, n - 1});
        }

        int[][] ansArr = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ansArr[i] = ans.get(i);
        }
        return ansArr;
    }

    private static boolean canDoIt(int num, String[] plate, int row, int col, int dir) {
        if (plate[row].charAt(col) == 'O') {
            return false;
        }
        int m = plate.length;
        int n = plate[0].length();
        int[] dirs = DIRECTIONS[dir];
        int step = 0;
        Set<Coordinate> visited = new HashSet<>();
        while (step <= num) {
            row += DIRECTIONS[dir][0];
            col += DIRECTIONS[dir][1];
            if (row < 0 || m <= row || col < 0 || n <= col || !visited.add(new Coordinate(row, col, dir))) {
                return false;
            }
            char c = plate[row].charAt(col);
            if (c == 'O') {
                return true;
            } else if (c == 'W') {
                dir = dir == 0 ? 3 : dir - 1;
            } else if (c == 'E') {
                dir = dir == 3 ? 0 : dir + 1;
            }
            step++;
        }
        return false;
    }

    static class Coordinate {
        public int row;
        public int col;
        public int dir;

        public Coordinate(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return row == that.row && col == that.col && dir == that.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, dir);
        }
    }
}
