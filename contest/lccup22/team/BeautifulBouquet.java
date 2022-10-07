package org.example.leetcode.problems.contest.lccup22.team;

public class BeautifulBouquet {
    public static void main(String[] args) {
        System.out.println(beautifulBouquet(new int[]{1, 2, 3, 4}, 5));
        System.out.println(beautifulBouquet(new int[]{1, 2, 3, 2}, 1));
        System.out.println(beautifulBouquet(new int[]{1, 2, 3, 4, 5, 6, 4}, 1));
        System.out.println(beautifulBouquet(new int[]{5, 3, 3, 3}, 2));
        System.out.println(beautifulBouquet(new int[]{1, 10, 1, 10, 1, 10}, 2));
        System.out.println(beautifulBouquet(new int[]{1, 10, 1, 10, 1, 10}, 3));
    }

    /*
     * 1.r++ until >  cnt
     * 2.l++ until <= cnt
     * run step 1
     */
    public static int beautifulBouquet(int[] flowers, int cnt) {
        int mod = 1000000007;
        int len = flowers.length;
        if (cnt >= len) {
            return len * (1 + len) / 2 % mod;
        }
        int sum = 0;
        int[] cnts = new int[1000001];

        int l = 0;
        //[l,r-1) <= cnt
        for (int r = 0; l < len; ) {
            if (r < len) {
                while (r < len && ++cnts[flowers[r++]] <= cnt) {
                }
                //r < len && ++cnts[flowers[r++]] >  cnt
                //r ==len &&   cnts[flowers[r-1]] <= cnt
                //r ==len &&   cnts[flowers[r-1]] >  cnt
                if (cnts[flowers[r - 1]] > cnt) {
                    sum += r - l - 1;
                } else {
                    sum += (len - l) * (1 + len - l) / 2;
                    break;
                }
            }
//            else if (r == len) {
//                sum += len - l;
//                r++;
//            }
            while (l < len && --cnts[flowers[l++]] < cnt) {
                sum += r - l - 1;
            }
            if (cnts[flowers[l - 1]] == cnt) {
                r += 2;
            }
//        if (cnts[flowers[l]] == cnt) {
//            sum += (len - l) * (1 + len - l) / 2;
//        }
        }
        return sum % mod;
    }
}