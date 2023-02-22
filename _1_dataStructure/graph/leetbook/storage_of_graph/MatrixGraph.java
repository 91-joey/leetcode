package _1_dataStructure.graph.leetbook.storage_of_graph;

import java.util.ArrayList;
import java.util.List;

// 矩阵存图法（适用于稠密图)
public class MatrixGraph extends Graph {
  // O(v^2)
  int[][] weight;
  
  // O(v^2)，v为顶点数
  public MatrixGraph(int size) {
    super(size);
    weight = new int[size][size];
  }
  
  @Override
  // O(1)
  public void add(int start, int end, int weight) {
    this.weight[start][end] = weight;
  }
  
  @Override
  // O(1)
  public boolean isConnected(int start, int end) {
    return weight[start][end] != 0;
  }
  
  @Override
  // O(v)，v为顶点数
  public List<Edge> outEdges(int start) {
    ArrayList<Edge> list = new ArrayList<>();
    for (int i = 0; i < weight[start].length; i++)
      if (isConnected(start, i))
        list.add(new Edge(i, weight[start][i]));
    return list;
  }
}
