package com.example.lic.digui;

/**
 * @author wy
 * @date 2019/12/19 14:26
 * @description 定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class SolutionLinked {
    public static void main(String args[]) {
        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        int i = 1;
        while (i < 10) {
            listNode.next = new ListNode(i ++);
            listNode = listNode.next;
        }

        head = new SolutionLinked().swapPairs(head);

        while (head != null) {
            System.out.println("," + head.val);
            head = head.next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode flag = head.next;
        head.next = flag.next;
        flag.next = head;

        head.next = swapPairs(head.next);
        return flag;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}