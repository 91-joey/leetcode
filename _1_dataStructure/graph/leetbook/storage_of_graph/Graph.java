package org.example.leetcode.problems._1_dataStructure.graph.leetbook.storage_of_graph;

import java.util.List;

public abstract class Graph {
  
  public Graph(int size) {
  }
  
  public abstract void add(int start, int end, int weight);
  
  public abstract boolean isConnected(int start, int end);
  
  public abstract List<Edge> outEdges(int start);
}
