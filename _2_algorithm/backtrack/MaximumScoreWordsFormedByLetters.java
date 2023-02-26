package _2_algorithm.backtrack;

/**
 * 1255.得分最高的单词集合 <br>
 * 开题时间：2023-02-26 17:14:10
 */
public class MaximumScoreWordsFormedByLetters {
  public static void main(String[] args) {
    Solution solution = new MaximumScoreWordsFormedByLetters().new Solution();
    System.out.println(solution.maxScoreWords(new String[]{"dog","cat","dad","good"}
    , new char[]{'a','a','c','d','d','d','g','o','o'},
        new int[]{1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 二进制枚举（状态压缩）
    public int maxScoreWords9(String[] words, char[] letters, int[] score) {
      int[] cnt = new int[26];
      for (char c : letters) {
        cnt[c - 'a']++;
      }
      
      int ans = 0;
      int n = words.length;
      out:
      for (int s = (1 << n) - 1; s >= 0; s--) {
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
          if ((s & (1 << i)) > 0) {
            for (int j = 0; j < words[i].length(); j++) {
              int idx = words[i].charAt(j) - 'a';
              if (++freq[idx] > cnt[idx]) {
                continue out;
              }
            }
          }
        }
        
        int scores = 0;
        for (int j = 0; j < 26; j++) {
          scores += score[j] * freq[j];
        }
        ans = Math.max(ans, scores);
      }
      
      return ans;
    }
    
    // 二进制枚举（状态压缩）（优化）
    public int maxScoreWords8(String[] words, char[] letters, int[] score) {
      int[] cnt = new int[26];
      for (char c : letters) {
        cnt[c - 'a']++;
      }
      int n = words.length;
      int[][] wordsCnt = new int[n][26];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < words[i].length(); j++) {
          wordsCnt[i][words[i].charAt(j) - 'a']++;
        }
      }
      
      int ans = 0;
      out:
      for (int i = (1 << n) - 1; i >= 0; i--) {
        int[] freq = new int[26];
        for (int j = 0; j < n; j++) {
          if ((i & (1 << j)) > 0) {
            for (int k = 0; k < 26; k++) {
              if ((freq[k] += wordsCnt[j][k]) > cnt[k]) {
                continue out;
              }
            }
          }
        }
        
        int scores = 0;
        for (int j = 0; j < 26; j++) {
          scores += score[j] * freq[j];
        }
        ans = Math.max(ans, scores);
      }
      
      return ans;
    }
    
    private String[] words;
    private int[] score;
    int[] left = new int[26];
    int ans = 0;
    
    
    // ☆☆☆☆☆ 回溯（子集型）
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
      this.words = words;
      this.score = score;
      for (char c : letters) {
        left[c - 'a']++;
      }
      
      backtrack(words.length - 1, 0);
      
      return ans;
    }
    
    private void backtrack(int i, int curScore) {
      if (i < 0) {
        ans = Math.max(ans, curScore);
        return;
      }
      
      backtrack(i - 1, curScore);
      
      char[] cs = words[i].toCharArray();
      int j;
      for (j = 0; j < cs.length; j++) {
        int idx = cs[j] - 'a';
        if (--left[idx] < 0) {
          // 提前结束遍历单词
          break;
        }
        curScore += score[idx];
      }
      
      if (j >= cs.length) {
        backtrack(i - 1, curScore);
      }
      
      for (int k = Math.min(cs.length - 1, j); k >= 0; k--) {
        ++left[cs[k] - 'a'];
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}