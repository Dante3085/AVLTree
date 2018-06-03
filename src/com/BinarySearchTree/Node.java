package com.BinarySearchTree;

class Node<T extends Comparable<T>>
{
    T data;
    Node left;
    Node right;

    Node(T data)
    {
        this.data = data;
        left = right = null;
    }

    int getHeight()
    {
        int leftHeight = 0, rightHeight = 0;

        if (this.left != null)
            leftHeight = this.left.getHeight();
        if (this.right != null)
            rightHeight = this.right.getHeight();
        return 1 + Math.max(leftHeight, rightHeight); // +1 is parent node. max-height of subtrees.
    }
}
