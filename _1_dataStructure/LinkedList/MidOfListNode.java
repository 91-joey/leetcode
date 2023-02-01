package org.example.leetcode.problems._1_dataStructure.LinkedList;

import org.example.leetcode.problems._3_common.entity.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;

//876. 链表的中间结点
public class MidOfListNode {
    //    1.列表法
    public ListNode middleNode1(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        list.add(head);
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            list.add(cur);
        }
        return list.get(list.size() / 2);
    }

    //    2.单指针法
    public ListNode middleNode2(ListNode head) {
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }

        cur = head;
        for (int i = 0; i < size / 2; i++) {
            cur = cur.next;
        }

        return cur;
    }

    //    3.快慢指针法
    public ListNode middleNode3(ListNode head) {
        ListNode mid = head, fast = head;
        while (fast != null && fast.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        return mid;
    }

}