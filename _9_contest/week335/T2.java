package _9_contest.week335;

import _3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// 6308. Kth Largest Sum in a Binary Tree
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // bfs + 排序
    public long kthLargestLevelSum9(TreeNode root, int k) {
      // Base case: empty tree
      if (root == null)
        return -1;
      
      // Create an array list to store level sums
      ArrayList<Long> levelSums = new ArrayList<>();
      
      // Create a queue for level order traversal
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      
      // Traverse each level of the tree
      while (!queue.isEmpty()) {
        // Get the number of TreeNodes in current level
        int size = queue.size();
        
        // Initialize sum for current level
        long sum = 0;
        
        // Dequeue all TreeNodes of current level and enqueue their children
        while (size-- > 0) {
          TreeNode node = queue.poll();
          sum += node.val; // Add node's value to sum
          
          if (node.left != null)
            queue.add(node.left);
          if (node.right != null)
            queue.add(node.right);
        }
        
        // Add current level sum to array list
        levelSums.add(sum);
      }
      
      // Sort the array list in descending order
      Collections.sort(levelSums, Collections.reverseOrder());
      
      // Return kth element if it exists, otherwise return -1
      if (k > 0 && k <= levelSums.size())
        return levelSums.get(k - 1);
      else
        return -1;
    }
  
    // ☆☆☆☆☆ bfs + 排序（简化）
    public long kthLargestLevelSum(TreeNode root, int k) {
      // Create an array list to store level sums
      ArrayList<Long> levelSums = new ArrayList<>();
      
      // Create a queue for level order traversal
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      
      // Traverse each level of the tree
      while (!queue.isEmpty()) {
        // Get the number of TreeNodes in current level
        int size = queue.size();
        
        // Initialize sum for current level
        long sum = 0;
        
        // Dequeue all TreeNodes of current level and enqueue their children
        while (size-- > 0) {
          TreeNode node = queue.poll();
          sum += node.val; // Add node's value to sum
          
          if (node.left != null)
            queue.add(node.left);
          if (node.right != null)
            queue.add(node.right);
        }
        
        // Add current level sum to array list
        levelSums.add(sum);
      }
      
      // Sort the array list in descending order
      levelSums.sort(Collections.reverseOrder());
      
      // Return kth element if it exists, otherwise return -1
      return k <= levelSums.size() ?
          levelSums.get(k - 1) :
          -1;
    }
  }
}
