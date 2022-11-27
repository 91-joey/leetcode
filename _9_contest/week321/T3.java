package org.example.leetcode.problems._9_contest.week321;

import org.example.leetcode.problems._3_common.linkedlist.ListNode;

import java.util.LinkedList;

public class T3 {
    public static void main(String[] args) {

    }

    public ListNode removeNodesX(ListNode head) {
        ListNode dummy = new ListNode(Integer.MAX_VALUE, head);

        helper(dummy, dummy.next);

        return dummy.next;
    }

    private ListNode helper(ListNode pre, ListNode cur) {
        if (cur == null)
            return pre;

        ListNode next = helper(cur, cur.next);

        if (cur.val < next.val)
            pre.next = next.next;

        return pre;
    }

    public ListNode removeNodes(ListNode head) {
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

}
