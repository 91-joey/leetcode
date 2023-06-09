package _9_contest.history.week324;

// 6268. Cycle Length Queries in a Tree
public class T4 {
  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(7));
    System.out.println(Integer.toBinaryString(12));
    System.out.println(Integer.highestOneBit(1024));
  }
  
  // 最近公共祖先（自顶向下）
  public int[] cycleLengthQueries9(int n, int[][] queries) {
    int m = queries.length;
    int[] ans = new int[m];
    
    for (int i = 0; i < m; i++) {
      String a = Integer.toBinaryString(queries[i][0]);
      String b = Integer.toBinaryString(queries[i][1]);
      int j = 0;
      while (j < a.length() && j < b.length())
        if (a.charAt(j) == b.charAt(j))
          j++;
        else
          break;
      ans[i] = a.length() + b.length() - 2 * j + 1;
    }
    
    return ans;
  }
  
  
  //☆☆☆ 最近公共祖先（自底向上）
  public int[] cycleLengthQueries8(int n, int[][] queries) {
    int m = queries.length;
    int[] ans = new int[m];
    
    for (int i = 0; i < m; i++) {
      int a = queries[i][0];
      int b = queries[i][1];
      int cnt = 1;
      while (a != b) {
        if (a > b)
          a /= 2;
        else
          b /= 2;
        cnt++;
      }
      ans[i] = cnt;
    }
    
    return ans;
  }
  
  //☆☆☆☆☆ 位运算优化
  public int[] cycleLengthQueries(int n, int[][] queries) {
    int m = queries.length;
    int[] ans = new int[m];
    
    for (int i = 0; i < m; i++) {
      int a = queries[i][0];
      int b = queries[i][1];
      if (a > b) {
        int tmp = a;
        a = b;
        b = tmp;
      }
      int d = Integer.numberOfLeadingZeros(a) - Integer.numberOfLeadingZeros(b);
      ans[i] = d + 2 * (32 - Integer.numberOfLeadingZeros(a ^ (b >> d))) + 1;
    }
    
    return ans;
  }
}
