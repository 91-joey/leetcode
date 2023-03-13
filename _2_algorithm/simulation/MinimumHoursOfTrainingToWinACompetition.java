package _2_algorithm.simulation;

/**
 * 2383.赢得比赛需要的最少训练时长 <br>
 * 开题时间：2023-03-13 09:06:13
 */
public class MinimumHoursOfTrainingToWinACompetition {
  public static void main(String[] args) {
    Solution solution = new MinimumHoursOfTrainingToWinACompetition().new Solution();
    System.out.println(solution.minNumberOfHours(5, 3, new int[]{1, 4, 3, 2}, new int[]{2, 6, 3, 1}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 模拟
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
      int trainingEng = 1 - initialEnergy;
      int trainingExp = 0;
      for (int i = 0, curExp = initialExperience; i < energy.length; i++) {
        trainingEng += energy[i];
        
        int max = Math.max(curExp, experience[i] + 1);
        trainingExp += max - curExp;
        curExp = max + experience[i];
      }
      
      return trainingExp + Math.max(trainingEng, 0);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}