package com.example.demo.list;

/**
 * @author: YJY
 * @date: 2021/9/27 15:33
 * @description: 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * <p>
 * 如果有两个中间结点，则返回第二个中间结点。
 */
public class MiddleNode {

  class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }


  /**
   * @Description 利用额外空间 数组
   **/
  public ListNode middleNode(ListNode head) {

    ListNode[] arr = new ListNode[100];
    int l = 0;
    while (null != head) {
      arr[l] = head;
      head = head.next;
      l++;
    }

    return arr[l / 2];
  }

  /**
   * @Description 用两个指针 slow 与 fast 一起遍历链表。
   * slow 一次走一步，fast 一次走两步。那么当 fast 到达链表的末尾时，slow 必然位于中间。
   **/
  public ListNode middleNodeTwo(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }


  public static void main(String[] args) {

  }

}
