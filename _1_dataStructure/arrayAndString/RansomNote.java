package org.example.leetcode.problems._1_dataStructure.arrayAndString;

//383. 赎金信
public class RansomNote {
    public static boolean canConstruct9(String ransomNote, String magazine) {
        if(ransomNote.length()>magazine.length()){
            return false;
        }
        int[] cnt=new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c-'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c-'a']--;
            if(cnt[c-'a']<0){
                return false;
            }
        }
        return true;
    }


    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[123];
        for (int i = 0; i < magazine.length(); i++)
            freq[magazine.charAt(i)]++;

        for (int i = 0; i < ransomNote.length(); i++)
            if (freq[ransomNote.charAt(i)]-- <= 0)
                return false;

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("aa","aab"));
    }
}
