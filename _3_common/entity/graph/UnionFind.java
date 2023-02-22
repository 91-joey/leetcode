package _3_common.entity.graph;

public class UnionFind {
  int[] root;
  int[] rank;
  
  public UnionFind(int size) {
    root = new int[size];
    rank = new int[size];
    for (int i = 0; i < size; i++)
      root[i] = i;
  }
  
  public int find(int x) {
    if (x == root[x])
      return x;
    return root[x] = find(root[x]);
  }
  
  public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX != rootY)
      if (rank[rootX] == rank[rootY]) {
        root[rootX] = rootY;
        rank[rootY]++;
      } else if (rank[rootX] < rank[rootY])
        root[rootX] = rootY;
      else
        root[rootY] = rootX;
  }
  
  public boolean connected(int x, int y) {
    return find(x) == find(y);
  }
}
