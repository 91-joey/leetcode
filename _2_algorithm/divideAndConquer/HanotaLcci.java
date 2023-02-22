//<p>在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:<br> (1) 每次只能移动一个盘子;<br> (2) 盘子只能从柱子顶端滑出移到下一根柱子;<br> (3) 盘子只能叠在比它大的盘子上。</br></br></br></p>
//
//<p>请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。</p>
//
//<p>你需要原地修改栈。</p>
//
//<p><strong>示例1:</strong></p>
//
//<pre><strong> 输入</strong>：A = [2, 1, 0], B = [], C = []
//<strong> 输出</strong>：C = [2, 1, 0]
//</pre>
//
//<p><strong>示例2:</strong></p>
//
//<pre><strong> 输入</strong>：A = [1, 0], B = [], C = []
//<strong> 输出</strong>：C = [1, 0]
//</pre>
//
//<p><strong>提示:</strong></p>
//
//<ol> 
// <li>A中盘子的数目不大于14个。</li> 
//</ol>
//
//<div><li>👍 198</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 面试题 08.06.汉诺塔问题
// 开题时间：2022-12-30 11:28:37
public class HanotaLcci {
  public static void main(String[] args) {
    Solution solution = new HanotaLcci().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public void hanotaX(List<Integer> A, List<Integer> B, List<Integer> C) {
      Deque<Integer> stack = new LinkedList<>();
      for (Integer x : A)
        stack.push(x);
      while (!stack.isEmpty())
        B.add(stack.pop());
      for (Integer x : B)
        stack.push(x);
      while (!stack.isEmpty())
        C.add(stack.pop());
    }
    
    //☆☆☆☆☆ 递归与分治
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
      helper(A, B, C, A.size());
    }
    
    private void helper(List<Integer> A, List<Integer> B, List<Integer> C, int n) {
      if (n == 1) {
        C.add(A.remove(A.size() - 1));
        return;
      }
      helper(A, C, B, n - 1);
      C.add(A.remove(A.size() - 1));
      helper(B, A, C, n - 1);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}