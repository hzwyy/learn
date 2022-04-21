package com.example.demo.medium;

import java.util.List;
import org.springframework.http.converter.json.GsonBuilderUtils;

/**
 * @author: YJY
 * @date: 2022/4/21 14:50
 * @description: 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的， 并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例 1： 输入：l1 = [2,4,3], l2 = [5,6,4] 输出：[7,0,8] 解释：342 + 465 = 807.
 * <p>
 * 示例 2： 输入：l1 = [0], l2 = [0] 输出：[0]
 * <p>
 * 示例 3： 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9] 输出：[8,9,9,9,0,0,0,1]
 */
public class AddTwoNumbers {

  public static class ListNode {

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

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode newNode = null;
    ListNode tail = null;
    int jinwei = 0;
    while (l1 != null || l2 != null) {

      if (l1 != null && l2 != null) {
        if (newNode == null) {
          newNode = tail =new ListNode ((l1.val + l2.val + jinwei) % 10);
        } else {
          tail.next = new ListNode((l1.val + l2.val + jinwei) % 10);
          tail = tail.next;
        }
        if (l1.val + l2.val + jinwei >= 10) {
          jinwei = 1;
        } else {
          jinwei = 0;
        }

      } else {
        int n = l1 == null ? l2.val : l1.val;

        if (newNode == null) {
          newNode = tail =new ListNode((n + jinwei) % 10);
        } else {
          tail.next = new ListNode((n + jinwei) % 10);
          tail = tail.next;
        }
        if (n + jinwei >= 10) {
          jinwei = 1;
        } else {
          jinwei = 0;
        }
      }
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }

    }
    if(jinwei == 1){
      tail.next = new ListNode(1);
    }

    return newNode;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode();
    l1.val = 9;
    l1.next = new ListNode(9);
    l1.next.next = new ListNode(9);
    l1.next.next.next = new ListNode(9);
    l1.next.next.next.next = new ListNode(9);
    l1.next.next.next.next.next = new ListNode(9);
    l1.next.next.next.next.next.next = new ListNode(9);

    ListNode l2 = new ListNode();

    l2.val = 9;
    l2.next = new ListNode(9);
    l2.next.next = new ListNode(9);
    l2.next.next.next = new ListNode(9);
    ListNode node= addTwoNumbers(l1,l2);
    System.out.println(node);
  }
}
