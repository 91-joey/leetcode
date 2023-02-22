package _9_contest.history.week321;

import _3_common.entity.linkedlist.ListNode;

import java.util.LinkedList;

// 6247. Remove Nodes From Linked List
public class T3 {
  public static void main(String[] args) {
    System.out.println(removeNodes(
            new ListNode(5,
                new ListNode(2,
                    new ListNode(13,
                        new ListNode(3,
                            new ListNode(8))))
            )
        )
    );
  }
  
  // 递归
  public ListNode removeNodes8(ListNode head) {
    if (head.next == null)
      return head;
    
    ListNode node = removeNodes(head.next);
    
    if (head.val < node.val)
      return node;
    head.next = node;
    return head;
  }
  
  // 栈
  public ListNode removeNodes9(ListNode head) {
    ListNode dummy = new ListNode(Integer.MAX_VALUE, head);
    
    LinkedList<ListNode> stack = new LinkedList<>();
    for (ListNode node = dummy; node != null; node = node.next)
      stack.push(node);
    
    ListNode next = stack.pop();
    ListNode cur = stack.pop();
    while (!stack.isEmpty()) {
      if (cur.val < next.val) {
        ListNode pre = stack.pop();
        pre.next = next;
        cur = pre;
      } else {
        next = cur;
        cur = stack.pop();
      }
    }
    
    return dummy.next;
  }
  
  //☆☆☆☆☆ 反转+迭代+反转
  public static ListNode removeNodes(ListNode head) {
    head = reverseList(head);
    for (ListNode cur = head; cur.next != null; ) {
      if (cur.next.val < cur.val)
        cur.next = cur.next.next;
      else
        cur = cur.next;
    }
    return reverseList(head);
  }
  
  public static ListNode reverseList(ListNode head) {
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }
}
