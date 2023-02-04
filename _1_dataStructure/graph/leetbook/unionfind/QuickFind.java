package org.example.leetcode.problems._1_dataStructure.graph.leetbook.unionfind;

public class QuickFind extends UnionFind {
  
  // O(n)
  public QuickFind(int size) {
    super(size);
  }
  
  // O(1)
  public int find(int x) {
    return root[x];
  }
  
  // O(n)  元素值始终为根节点
  public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX != rootY)
      for (int i = 0; i < root.length; i++)
        if (root[i] == rootY)
          root[i] = rootX;
  }
}
