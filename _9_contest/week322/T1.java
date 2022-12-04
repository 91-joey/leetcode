package org.example.leetcode.problems._9_contest.week322;

//6253. Circular Sentence
public class T1 {
    public boolean isCircularSentence9(String sentence) {
        String[] split = sentence.split(" ");
        for (int i = 0; i < split.length; i++)
            if (split[i].charAt(split[i].length() - 1) !=
                    split[(i + 1) % split.length].charAt(0))
                return false;
        return true;
    }

    public boolean isCircularSentence(String sentence) {
        sentence += " ";
        for (int i = 1; i < sentence.length(); i++)
            if (sentence.charAt(i) == ' ' && sentence.charAt(i - 1) != sentence.charAt((i + 1) % sentence.length()))
                return false;
        return true;
    }

}
