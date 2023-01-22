package org.example.leetcode.problems._9_contest.week329;

//
public class T3 {
    public static void main(String[] args) {
        Solution solution = new T3().new Solution();
        System.out.println(solution.makeStringsEqual("00100"
                , "00000"));
        System.out.println(solution.makeStringsEqual("0100"
                , "1100"));
    }

    class Solution {
        public boolean makeStringsEqual(String s, String target) {
            if (s.equals(target))
                return true;

            if (s.equals("00") && target.equals("11"))
                return false;
            if (target.equals("00") && s.equals("11"))
                return false;

            int n = s.length();
            int cnt0 = 0, diff = 0, cnt02 = 0;
            boolean one = false;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '0')
                    cnt0++;
                if (target.charAt(i) == '0')
                    cnt02++;
                if (c != target.charAt(i)) {
                    diff++;
                    if (c == '1')
                        one = true;
                }
            }
            if (cnt0 == n)
                return false;

            if (cnt0 == n - 1 && diff == 1 && one)
                return false;
            return cnt0 + 1 < cnt02;
        }
    }
}
