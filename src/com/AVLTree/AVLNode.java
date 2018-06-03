package com.AVLTree;

import java.util.*;

/**
 * <h4>Node of an AVLTreeView</h4>
 * Supports generic Data.
 * @author mjsch
 * @param <T>
 */
public class AVLNode<T extends Comparable<T>>
{
    T data;
    AVLNode left;
    AVLNode right;

    public AVLNode(T data)
    {
        this.data = data;
        left = right = null;
    }

    /**
     * Returns the highest subtree connected to this AVLNodeView.
     * @return
     */
    public int getMaxHeightSubtrees()
    {
        int leftHeight = 0, rightHeight = 0;

        if (this.left != null)
            leftHeight = this.left.getMaxHeightSubtrees();
        if (this.right != null)
            rightHeight = this.right.getMaxHeightSubtrees();
        return 1 + Math.max(leftHeight, rightHeight); // +1 is parent node. max-height of subtrees.
    }

    public boolean isLeaf()
    {
        return (right == null && left == null);
    }

    public AVLNode<T> right()
    {
        return right;
    }

    public AVLNode<T> left()
    {
        return left;
    }

    @Override
    public String toString()
    {
        return this.data.toString();
    }
}
