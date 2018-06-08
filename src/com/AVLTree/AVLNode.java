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

    private int heightLeftSubtree;
    private int heightRightSubtree;

    /**
     * Creates an AVLNode with given data and no Child AVLNodes (i.e. left = right = null).
     * <br>Height of left and right subtrees are set to 0.
     */
    public AVLNode(T data)
    {
        this.data = data;
        left = right = null;
        heightLeftSubtree = heightRightSubtree = 0;
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
        return heightRightSubtree - heightLeftSubtree;
    }

    /**
     * Sets heightLeftSubtree and heightRightSubtree to 0, if this AVLNode is a leaf. Otherwise does nothing.
     */
    public void resetSubtreeHeights()
    {
        if (isLeaf())
            heightLeftSubtree = heightRightSubtree = 0;
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

    public int HeightLeftSubtree()
    {
        return heightLeftSubtree;
    }

    public void setHeightLeftSubtree(int heightLeftSubtree)
    {
        this.heightLeftSubtree = heightLeftSubtree;
    }

    public int HeightRightSubtree()
    {
        return heightRightSubtree;
    }

    public void setHeightRightSubtree(int heightRightSubtree)
    {
        this.heightRightSubtree = heightRightSubtree;
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
