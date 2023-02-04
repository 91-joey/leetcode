package org.example.leetcode.problems._1_dataStructure.queueAndStack;

import java.util.ArrayList;
import java.util.List;

// 队列实现 : 使用 动态数组 和 指向队列头部的索引
public class MyQueue {
  // store elements
  private final List<Integer> data;
  // a pointer to indicate the start position
  //    private int p_start;
  
  public MyQueue() {
    data = new ArrayList<>();
    //        p_start = 0;
  }
  
  /**
   * Insert an element into the queue. Return true if the operation is successful.
   */
  public boolean enQueue(int x) {
    data.add(x);
    return true;
  }
  
  /**
   * Delete an element from the queue. Return true if the operation is successful.
   */
  //    public boolean deQueue() {
  //        if (isEmpty()) {
  //            return false;
  //        }
  //        p_start++;
  //        return true;
  //    }
  public boolean deQueue() {
    if (data.isEmpty()) {
      return false;
    }
    data.remove(0);
    return true;
  }
  
  /**
   * Get the front item from the queue.
   */
  //    public int Front() {
  //        return data.get(p_start);
  //    }
  public int Front() {
    if (!data.isEmpty()) {
      return data.get(0);
    } else {
      throw new RuntimeException("Empty queue");
    }
  }
  
  /**
   * Checks whether the queue is empty or not.
   */
  //    public boolean isEmpty() {
  //        return p_start >= data.size();
  //    }
  public boolean hasElements() {
    return !data.isEmpty();
  }
  
  public static void main(String[] args) {
    MyQueue q = new MyQueue();
    q.enQueue(5);
    q.enQueue(3);
    if (q.hasElements()) {
      System.out.println(q.Front());
    }
    q.deQueue();
    if (q.hasElements()) {
      System.out.println(q.Front());
    }
    q.deQueue();
    if (q.hasElements()) {
      System.out.println(q.Front());
    }
  }
}

