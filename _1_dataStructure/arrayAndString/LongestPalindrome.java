package org.example.leetcode.problems._1_dataStructure.arrayAndString;

//5. 最长回文子串
public class LongestPalindrome {
    //    1.自解  n*n*n   1
    public String longestPalindrome1(String s) {
//        1.确定子串长度
        for (int length = s.length(); length >= 1; length--) {
//            2.确定子串起点
            for (int i = 0; i <= s.length() - length; i++) {
                String str = s.substring(i, i + length);
//                3.判断是否回文
                for (int j = 0; j < length / 2; j++) {
                    if (str.charAt(j) != str.charAt(length - 1 - j)) {
                        break;
                    }
                    if (j == length / 2 - 1) {
                        return str;
                    }
                }
            }
        }
        return s.substring(0, 1);
    }

    //    2.中心扩展算法  n*n 1
    public String longestPalindrome2(String s) {
        String ans = s.substring(0, 1);
        int length = s.length();
//        1.中心为单字符
        for (int i = 1; i < length - 1; i++) {
            ans = getAns(s, ans, length, i - 1, i + 1);
        }
//        1.中心为双字符
        for (int i = 0; i < length - 1; i++) {
            ans = getAns(s, ans, length, i, i + 1);
        }
        return ans;
    }

    public String getAns(String s, String ans, int length, int start, int end) {
        while (start >= 0 && end < length && (s.charAt(start) == s.charAt(end))) {
            start--;
            end++;
        }
        if (end - start - 1 > ans.length()) {
            ans = s.substring(start + 1, end);
        }
        return ans;
    }

    //    3.中心扩展算法_精简   n*n 1
    public String longestPalindrome3(String s) {
        int length = s.length();
        int start = 0;
        int end = 0;
        for (int i = 0; i < length; i++) {
//            1.中心为单字符
            int len1 = lengthByExpandingAroundCenter(s, i, i);
//            2.中心为双字符
            int len2 = lengthByExpandingAroundCenter(s, i, i + 1);
            int maxLen = Math.max(len1, len2);
            if (maxLen > end - start + 1) {
                start = i - (maxLen - 1) / 2;
                end = start + maxLen - 1;
            }
        }
        return s.substring(start, end + 1);
    }

    public int lengthByExpandingAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && (s.charAt(left) == s.charAt(right))) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome3("babad"));
    }
}
