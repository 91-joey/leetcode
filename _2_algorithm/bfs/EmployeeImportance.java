package _2_algorithm.bfs;

import _3_common.entity.Employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 690.员工的重要性 <br>
 * 开题时间：2023-02-23 17:05:58
 */
public class EmployeeImportance {
  public static void main(String[] args) {
    Solution solution = new EmployeeImportance().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  // 总体思路：相当于是 N 叉树 后序遍历/层序遍历 统计值
  class Solution {
    
    Employee[] emp;
    
    // ☆☆☆☆☆ 数组哈希 + 递归
    public int getImportance9(List<Employee> employees, int id) {
      this.emp = new Employee[2001];
      for (Employee employee : employees) {
        emp[employee.id] = employee;
      }
      
      return getImportance(id);
    }
    
    // 哈希表 + bfs
    public int getImportance(List<Employee> employees, int id) {
      HashMap<Integer, Employee> map = new HashMap<>();
      employees.forEach(employee -> map.put(employee.id, employee));
      
      LinkedList<Employee> q = new LinkedList<>();
      q.add(map.get(id));
      int ans = 0;
      while (!q.isEmpty()) {
        Employee employee = q.poll();
        ans += employee.importance;
        for (Integer subordinate : employee.subordinates) {
          q.add(map.get(subordinate));
        }
      }
      return ans;
    }
    
    private int getImportance(int id) {
      int ans = emp[id].importance;
      
      for (Integer subordinate : emp[id].subordinates) {
        ans += getImportance(subordinate);
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}