package com.example.lic.digui;

/**
 * @author wy
 * @date 2019/12/19 14:26
 * @description 反转链表
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * TODO 递归的思想，在于反向思维。
 * TODO 一般，最后处理的应该为最先拿到的
 */
public class SolutionLinkedV1 {
    public static void main(String args[]) {
        ListNode listNode = new ListNode(0);
        ListNode head = listNode;
        int i = 1;
        while (i < 10) {
            listNode.next = new ListNode(i ++);
            listNode = listNode.next;
        }

        //head = new SolutionLinkedV1().swapPairs(head, null);
        head = new SolutionLinkedV1().swapPairsV1(head);

        while (head != null) {
            System.out.println("," + head.val);
            head = head.next;
        }
    }

    /**
     * 递归实现
     * @param head
     * @param before
     * @return
     */
    public ListNode swapPairs(ListNode head, ListNode before) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            head.next = before;
            return head;
        }

        ListNode falg = head.next;
        head.next = before;
        return swapPairs(falg, head);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 算法实现
     */
    public ListNode swapPairsV1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    /**
     * 标准答案
     * @param head
     * @return
     *
     * 1.
     * o -> ... -> head -> head.next <- head.next.next <- ...
     *             null <- head.next <- head.next.next
     *
     * 2.
     * o -> ... -> head -> head.next -> head.next.next <- ...
     *             head <- head.next <- head.next.next
     *
     * 3.
     * o -> ... -> head -> null
     *             head <- head.next <- head.next.next
     *
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        // TODO 1
        head.next.next = head;
        // TODO 2
        head.next = null;
        // TODO 3
        return p;
    }
}