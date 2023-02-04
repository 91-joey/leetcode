package org.example.leetcode.problems._1_dataStructure.graph.leetbook.storage_of_graph;

import java.util.ArrayList;
import java.util.List;

// 邻接表存图法（链表数组，适用于稀疏图)
public class LinkedListGraph extends Graph {
  // O(m)，m为边数
  List<Edge>[] graph;
  
  public LinkedListGraph(int size) {
    super(size);
    graph = new List[size];
    for (int i = 0; i < size; i++)
      graph[i] = new ArrayList<>();
  }
  
  @Override
  // O(1)
  public void add(int start, int end, int weight) {
    graph[start].add(new Edge(end, weight));
  }
  
  @Override
  // O(出度)
  public boolean isConnected(int start, int end) {
    return graph[start].stream()
        .anyMatch(edge -> edge.end == end);
  }
  
  @Override
  // O(出度)
  public List<Edge> outEdges(int start) {
    return graph[start];
  }
}
