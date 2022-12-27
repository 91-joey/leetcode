package org.example.leetcode.problems._1_dataStructure.graph.leetbook.storage_of_graph;

public class Test {
    public static void main(String[] args) {
//        Graph graph = new MatrixGraph(10);
//        Graph graph = new LinkedListGraph(10);
        Graph graph = new ArrayLinkedListGraph(10);
        graph.add(1, 3, 4);
        graph.add(2, 3, 1);
        graph.add(3, 6, 3);
        graph.add(3, 8, 6);
        System.out.println(graph.isConnected(1, 2));
        graph.outEdges(3).forEach(System.out::println);
    }
}
