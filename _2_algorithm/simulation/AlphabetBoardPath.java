package _2_algorithm.simulation;

/**
 * 1138.字母板上的路径 <br>
 * 开题时间：2023-02-12 07:53:36
 */
public class AlphabetBoardPath {
  public static void main(String[] args) {
    Solution solution = new AlphabetBoardPath().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 模拟：从 'z' 到 其他字符 先上U再右R，从 其他字符 到 'z' 先左L再下D
    public String alphabetBoardPath9(String target) {
      StringBuilder sb = new StringBuilder();
      int preX = 0;
      int preY = 0;
      for (int i = 0; i < target.length(); i++) {
        int diff = target.charAt(i) - 'a';
        int curX = diff / 5;
        int curY = diff % 5;
        StringBuilder tmp = new StringBuilder();
        tmp.append((preX > curX ? "U" : "D").repeat(Math.abs(curX - preX)));
        tmp.append((preY > curY ? "L" : "R").repeat(Math.abs(curY - preY)));
        if (curX == 5) {
          tmp.reverse();
        }
        sb.append(tmp.append("!"));
        preX = curX;
        preY = curY;
      }
      return sb.toString();
    }
    
    // 模拟优化
    public String alphabetBoardPath(String target) {
      StringBuilder sb = new StringBuilder();
      int x = 0;
      int y = 0;
      for (int i = 0; i < target.length(); i++) {
        int diff = target.charAt(i) - 'a';
        int nx = diff / 5;
        int ny = diff % 5;
        String v = (x > nx ? "U" : "D").repeat(Math.abs(nx - x));
        String h = (y > ny ? "L" : "R").repeat(Math.abs(ny - y));
        sb.append(nx != 5 ? v + h : h + v).append('!');
        x = nx;
        y = ny;
      }
      return sb.toString();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}