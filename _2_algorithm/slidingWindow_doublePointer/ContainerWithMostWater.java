//<p>给定一个长度为 <code>n</code> 的整数数组&nbsp;<code>height</code>&nbsp;。有&nbsp;<code>n</code>&nbsp;条垂线，第 <code>i</code> 条线的两个端点是&nbsp;<code>(i, 0)</code>&nbsp;和&nbsp;<code>(i, height[i])</code>&nbsp;。</p>
//
//<p>找出其中的两条线，使得它们与&nbsp;<code>x</code>&nbsp;轴共同构成的容器可以容纳最多的水。</p>
//
//<p>返回容器可以储存的最大水量。</p>
//
//<p><strong>说明：</strong>你不能倾斜容器。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg" /></p>
//
//<pre>
//<strong>输入：</strong>[1,8,6,2,5,4,8,3,7]
//<strong>输出：</strong>49 
//<strong>解释：</strong>图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为&nbsp;49。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>height = [1,1]
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == height.length</code></li> 
// <li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= height[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>双指针</li></div></div><br><div><li>👍 3849</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 11.盛最多水的容器
// 开题时间：2022-10-22 11:57:45
public class ContainerWithMostWater {
  public static void main(String[] args) {
    Solution solution = new ContainerWithMostWater().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // TLE
    public int maxArea(int[] height) {
      int len = height.length;
      int maxArea = 0;
      for (int i = 0; i < len - 1; i++)
        for (int j = i + 1; j < len; j++)
          maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
      return maxArea;
    }
    
    public int maxArea2(int[] height) {
      int maxArea = 0;
      for (int l = 0, r = height.length - 1; l < r; ) {
        int minHeight = Math.min(height[l], height[r]);
        maxArea = Math.max(maxArea, (r - l) * minHeight);
        if (minHeight == height[l])
          l++;
        else
          r--;
      }
      return maxArea;
    }
    
    public int maxArea3(int[] height) {
      int maxArea = 0;
      for (int l = 0, r = height.length - 1; l < r; ) {
        maxArea = (height[l] < height[r]) ?
            Math.max(maxArea, (r - l) * height[l++]) :
            Math.max(maxArea, (r - l) * height[r--]);
      }
      return maxArea;
    }
    
    public int maxArea4(int[] height) {
      int maxArea = 0;
      for (int l = 0, r = height.length - 1; l < r; ) {
        int minHeight = Math.min(height[l], height[r]);
        maxArea = Math.max(maxArea, (r - l) * minHeight);
        while (l < r && height[l] <= minHeight) l++;
        while (l < r && height[r] <= minHeight) r--;
      }
      return maxArea;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}