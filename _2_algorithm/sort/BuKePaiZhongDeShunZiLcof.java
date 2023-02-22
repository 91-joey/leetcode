//<p>从<strong>若干副扑克牌</strong>中随机抽 <code>5</code> 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1:</strong></p>
//
//<pre>
//<strong>输入:</strong> [1,2,3,4,5]
//<strong>输出:</strong> True</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入:</strong> [0,0,1,2,5]
//<strong>输出:</strong> True</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>限制：</strong></p>
//
//<p>数组长度为 5&nbsp;</p>
//
//<p>数组的数取值为 [0, 13] .</p>
//
//<div><li>👍 290</li><li>👎 0</li></div>
package _2_algorithm.sort;

import java.util.Arrays;
import java.util.HashSet;

// 面试题61.扑克牌中的顺子
// 开题时间：2022-12-23 09:54:28
public class BuKePaiZhongDeShunZiLcof {
  public static void main(String[] args) {
    Solution solution = new BuKePaiZhongDeShunZiLcof().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean isStraight9(int[] nums) {
      Arrays.sort(nums);
      int cnt0 = (int) Arrays.stream(nums).filter(x -> x == 0).count();
      for (int i = cnt0 + 1; i < nums.length; i++)
        if (nums[i - 1] == nums[i] || (cnt0 -= (nums[i] - nums[i - 1] - 1)) < 0)
          return false;
      return true;
    }
    
    /*
     * ☆☆☆☆☆ 哈希表 + 遍历
     *  为顺子的充分必要条件：
     *      - 除大小王外，无重复值
     *      - 除大小王外，最值的差值小于 5
     */
    public boolean isStraight8(int[] nums) {
      HashSet<Integer> set = new HashSet<>();
      int max = 0, min = 13;
      for (int x : nums) {
        if (x == 0) continue;
        if (!set.add(x))
          return false;
        max = Math.max(max, x);
        min = Math.min(min, x);
      }
      return max - min < 5;
    }
    
    // 排序 + 遍历
    public boolean isStraight(int[] nums) {
      Arrays.sort(nums);
      int joker = 0;
      for (int i = 1; i < nums.length; i++)
        if (nums[i - 1] == 0)
          joker++;
        else if (nums[i - 1] == nums[i])
          return false;
      return nums[4] - nums[joker] < 5;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}