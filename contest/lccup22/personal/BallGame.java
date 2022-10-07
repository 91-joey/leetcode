package org.example.leetcode.problems.contest.lccup22.personal;

import java.util.*;

//LCP 63. 弹珠游戏
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
//        System.out.println(Arrays.deepToString(ballGame(41, new String[]{
//                "E...W..WW",
//                ".E...O...",
//                "...WO...W",
//                "..OWW.O..",
//                ".W.WO.W.E",
//                "O..O.W...",
//                ".OO...W..",
//                "..EW.WEE."
//                //72 73 48
//                //[[0,2],[0,3],[0,5],[0,6],[1,0],[1,8],[3,0],[3,8],[4,0],[6,0],[7,1],[7,4]]
//        })));
    }


    public static final char SPACE = '.';//空白区域
    public static final char HOLE = 'O';//洞
    public static final char ANTI_CLOCKWISE = 'W';//逆时针转向器
    public static final char CLOCKWISE = 'E';//顺时针转向器
    public static final int[] DR = {1, 0, -1, 0};//行方向上的增量
    public static final int[] DC = {0, -1, 0, 1};//列方向上的增量
    public static final int[] DW = {3, 0, 1, 2};//逆时针转向 后的方向增量索引
    public static final int[] DE = {1, 2, 3, 0};//顺时针转向 后的方向增量索引
    public int num;
    public String[] plate;
    public int m;
    public int n;

    public int[][] ballGame(int num, String[] plate) {
        List<int[]> ans = new ArrayList<>();
        this.num = num;
        this.plate = plate;
        this.m = plate.length;
        this.n = plate[0].length();

        //行数为 1，必入不得洞
        if (1 < m)
            //上下边缘
            for (int col = 1; col < n - 1; col++) {
                //上边缘
                if (canReachHoles(0, col, 0))
                    ans.add(new int[]{0, col});
                //下边缘
                if (canReachHoles(m - 1, col, 2))
                    ans.add(new int[]{m - 1, col});
            }


        //列数为 1，必入不得洞
        if (1 < n)
            //左右边缘
            for (int row = 1; row < m - 1; row++) {
                //左边缘
                if (canReachHoles(row, 0, 3))
                    ans.add(new int[]{row, 0});
                //右边缘
                if (canReachHoles(row, n - 1, 1))
                    ans.add(new int[]{row, n - 1});
            }

        //集合转数组
        int size = ans.size();
        int[][] ansArr = new int[size][2];
        for (int i = 0; i < size; i++) {
            ansArr[i] = ans.get(i);
        }
        return ansArr;
    }

    private boolean canReachHoles(int row, int col, int dir) {
        //必须从「空白区域」开始
        if (plate[row].charAt(col) != SPACE)
            return false;

        for (int step = 0; step <= num; step++) {
            //出界
            if (row < 0 || m <= row || col < 0 || n <= col)
                return false;

            char c = plate[row].charAt(col);
            //入洞
            if (c == HOLE) {
                return true;
                //逆时针转向器
            } else if (c == ANTI_CLOCKWISE) {
                dir = DW[dir];
                //顺时针转向器
            } else if (c == CLOCKWISE) {
                dir = DE[dir];
            }

            //前进
            row += DR[dir];
            col += DC[dir];
        }

        //超过最大步数
        return false;
    }
}
