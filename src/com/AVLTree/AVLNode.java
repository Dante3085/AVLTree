package com.AVLTree;

/**
 * Basic building block of an AVLTree.
 * <br>Contains generic Data and at most a reference to a left and right AVLNode.
 * <br>Supports generic Data.
 * @author mjsch
 * @param <T> extends Comparable<T>.
 */
public class AVLNode<T extends Comparable<T>> implements IAVLNode<T>
{
    T data;
    AVLNode left;
    AVLNode right;

    /**
     * Creates an AVLNode with given data and no Child AVLNodes (i.e. left = right = null).
     */
    public AVLNode(T data)
    {
        this.data = data;
        left = right = null;
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean isLeaf()
    {
        return (right == null && left == null);
    }

    /**
     * @inheritdoc
     */
    @Override
    public int height()
    {
        int leftHeight = 0, rightHeight = 0;

        if (this.left != null)
            leftHeight = this.left.height();
        if (this.right != null)
            rightHeight = this.right.height();
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * @inheritDoc
     */
    public AVLNode<T> left()
    {
        return left;
    }

    /**
     * @inheritDoc
     */
    public AVLNode<T> right()
    {
        return right;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString()
    {
        return this.data.toString();
    }
}
