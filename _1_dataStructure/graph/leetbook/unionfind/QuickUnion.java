package org.example.leetcode.problems._1_dataStructure.graph.leetbook.unionfind;

public class QuickUnion extends UnionFind {
  
  // O(n)
  public QuickUnion(int size) {
    super(size);
  }
  
  // O(H)
  public int find(int x) {
    while (x != root[x])
      x = root[x];
    return x;
  }
  
  // O(H)  元素值为伪根节点（需要深度遍历得到真根节点）
  public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX != rootY)
      root[rootY] = rootX;
  }
}
