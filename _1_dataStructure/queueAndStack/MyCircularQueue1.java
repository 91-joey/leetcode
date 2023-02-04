package org.example.leetcode.problems._1_dataStructure.queueAndStack;

import org.example.leetcode.problems._3_common.entity.linkedlist.ListNode;

// 622. 设计循环队列
// 1.自解（数组）
public class MyCircularQueue1 {
  private final int[] data;
  private final int length;
  private int head;
  private int tail;
  
  public MyCircularQueue1(int k) {
    data = new int[k];
    length = k;
    head = -1;
    tail = -1;
  }
  
  public boolean enQueue(int value) {
    if (!isFull()) {
      if (isEmpty()) {
        head = 0;
      }
      tail = (tail + 1) % length;
      data[tail] = value;
      return true;
    } else {
      return false;
    }
  }
  
  public boolean deQueue() {
    if (!isEmpty()) {
      if (head == tail) {
        head = -1;
        tail = -1;
      } else {
        head = (head + 1) % length;
      }
      return true;
    } else {
      return false;
    }
  }
  
  public int Front() {
    if (!isEmpty()) {
      return data[head];
    } else {
      return -1;
    }
  }
  
  public int Rear() {
    if (!isEmpty()) {
      return data[tail];
    } else {
      return -1;
    }
  }
  
  public boolean isEmpty() {
    return head == -1 && tail == -1;
  }
  
  public boolean isFull() {
    if (tail >= head) {
      return head == 0 && tail == length - 1;
    } else {
      return tail + 1 == head;
    }
  }
  
  public static void main(String[] args) {
    MyCircularQueue1 obj = new MyCircularQueue1(1);
    boolean param_1 = obj.enQueue(1);
    boolean param_2 = obj.deQueue();
    int param_3 = obj.Front();
    int param_4 = obj.Rear();
    boolean param_5 = obj.isEmpty();
    boolean param_6 = obj.isFull();
    
  }
}

// 2.宫水三叶(数组) https://leetcode.cn/problems/design-circular-queue/solution/by-ac_oier-y11p/
class MyCircularQueue2 {
  private final int[] data;
  private final int length;
  private int head;
  private int tail;
  
  public MyCircularQueue2(int k) {
    data = new int[k];
    length = k;
  }
  
  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    } else {
      data[tail % length] = value;
      tail++;
      return true;
    }
  }
  
  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    } else {
      head++;
      return true;
    }
  }
  
  public int Front() {
    if (isEmpty()) {
      return -1;
    } else {
      return data[head % length];
    }
  }
  
  public int Rear() {
    if (isEmpty()) {
      return -1;
    } else {
      return data[(tail - 1) % length];
    }
  }
  
  public boolean isEmpty() {
    return head == tail;
  }
  
  public boolean isFull() {
    return tail - head == length;
  }
}

// 622. 设计循环队列
// 3.官解(链表)
class MyCircularQueue3 {
  private final int maxLength;
  private ListNode head;
  private ListNode tail;
  private int length;
  
  public MyCircularQueue3(int k) {
    maxLength = k;
  }
  
  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    } else {
      ListNode newItem = new ListNode(value);
      if (tail != null) {
        tail.next = newItem;
      } else {
        head = newItem;
      }
      tail = newItem;
      length++;
      return true;
    }
  }
  
  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    } else {
      if (head == tail) {
        tail = null;
      }
      head = head.next;
      length--;
      return true;
    }
  }
  
  public int Front() {
    if (isEmpty()) {
      return -1;
    } else {
      return head.val;
    }
  }
  
  public int Rear() {
    if (isEmpty()) {
      return -1;
    } else {
      return tail.val;
    }
  }
  
  public boolean isEmpty() {
    return length == 0;
  }
  
  public boolean isFull() {
    return length == maxLength;
  }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */