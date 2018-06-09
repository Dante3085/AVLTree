package com.AVLTree;

/**
 * Basic building block of an AVLTree.
 * <br>Contains generic Data and at most a reference to a left and right AVLNode.
 * <br>Supports generic Data.
 * @author mjsch
 * @param <T> extends Comparable<T>.
 */
// TODO: Balance in AVLNodes direkt beim Einfügen abspeichern. Die balance-Berechnung bei jedem add / delete ist sehr aufwending.
public class AVLNode<T extends Comparable<T>> implements IAVLNode<T>, TreePrinter.PrintableNode
{
    // TODO: Diese 3 Attribute sollten vielleicht private sein ?
    T data;
    AVLNode<T> left;
    AVLNode<T> right;

    public int height;

    /**
     * Creates an AVLNode with given data and no Child AVLNodes (i.e. left = right = null).
     * <br>Height of left and right subtrees are set to 0.
     */
    public AVLNode(T data)
    {
        this.data = data;
        left = right = null;
        height = 1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isLeaf()
    {
        return (right == null && left == null);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int balance()
    {
    int rightHeight = 0, leftHeight = 0;
    if (right!= null)
        rightHeight = right.height;
    if (left != null)
        leftHeight = left.height;

    return rightHeight - leftHeight;
}

    /**
     * @inheritdoc
     */
    // TODO: Change in functionality.
    @Override
    public int height()
    {
        int leftHeight = 0, rightHeight = 0;

        if (left != null)
            leftHeight = left.height;
        if (right != null)
            rightHeight = right.height;
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
