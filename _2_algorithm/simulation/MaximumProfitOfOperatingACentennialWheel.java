package _2_algorithm.simulation;

/**
 * 1599.经营摩天轮的最大利润 <br>
 * 开题时间：2023-03-05 08:53:40
 */
public class MaximumProfitOfOperatingACentennialWheel {
  public static void main(String[] args) {
    Solution solution = new MaximumProfitOfOperatingACentennialWheel().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 模拟
    public int minOperationsMaxProfit9(int[] customers, int boardingCost, int runningCost) {
      int ans = Integer.MAX_VALUE;
      int maxProfit = 0;
      int curCustomers = 0;
      int curProfit = 0;
      int rotate = 1;
      for (int customer : customers) {
        curCustomers += customer;
        int min = Math.min(curCustomers, 4);
        curCustomers -= min;
        
        curProfit += min * boardingCost - runningCost;
        if (curProfit > maxProfit) {
          maxProfit = curProfit;
          ans = rotate;
        }
        rotate++;
      }
      
      while (curCustomers > 0) {
        int min = Math.min(curCustomers, 4);
        curCustomers -= min;
        
        curProfit += min * boardingCost - runningCost;
        if (curProfit > maxProfit) {
          maxProfit = curProfit;
          ans = rotate;
        }
        rotate++;
      }
      
      return maxProfit > 0 ? ans : -1;
    }
    
    // 模拟（简化）
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
      int minOperations = Integer.MAX_VALUE;
      int maxProfit = 0;
      for (int i = 0, n = customers.length, curCustomers = 0, curOperations = 1, curProfit = 0; i < n || curCustomers > 0; i++, curOperations++) {
        curCustomers += i < n ? customers[i] : 0;
        int min = Math.min(curCustomers, 4);
        curCustomers -= min;
        
        curProfit += min * boardingCost - runningCost;
        if (curProfit > maxProfit) {
          maxProfit = curProfit;
          minOperations = curOperations;
        }
      }
      
      return maxProfit > 0 ? minOperations : -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}