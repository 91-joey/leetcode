package org.example.leetcode.problems._1_dataStructure.graph.leetbook.unionfind;

public abstract class UnionFind {
  
  int[] root;
  
  public UnionFind(int size) {
    root = new int[size];
    for (int i = 0; i < size; i++)
      root[i] = i;
  }
  
  public abstract int find(int x);
  
  public abstract void union(int x, int y);
  
  public boolean connected(int x, int y) {
    return find(x) == find(y);
  }
}
