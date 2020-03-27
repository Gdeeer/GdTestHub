package com.gdeer.gdtesthub.algorithm;

/**
 * 二叉树转双向链表
 */
public class TreeToList {
    Node head;
    Node tail;

    class Node {
        int val;
        Node left;
        Node right;
    }

    public void convert(Node node) {
        if (node == null) {
            return;
        }
        convert(node.left);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.right = node;
            node.left = tail;
            tail = node;
        }
        convert(node.right);
    }
}
