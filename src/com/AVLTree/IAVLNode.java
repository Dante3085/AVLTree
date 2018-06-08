package com.AVLTree;

/**
 * @author mjsch
 */
public interface IAVLNode<T extends Comparable<T>>
{
    /**
     * Checks if this AVLNode is a leaf.
     * @return True if left and right AVLNodes are null. Otherwise false.
     */
    boolean isLeaf();

    /**
     * Calculates the AVLNode's balance-factor and returns it.
     * <br>Formula: heightRightSubtree - heightLeftSubtree
     * @return balance-factor
     */
    int balance();

    /**
     * Calculates the height of the AVLTree from which this AVLNode is root.
     * <br>This AVLNode is seen as the root AVLNode of that AVLTree.
     * @return
     */
    int height();

    /**
     * Returns AVLNode that is smaller than this AVLNode (i.e. left AVLNode).
     * @return
     */
    AVLNode<T> left();

    /**
     * Returns AVLNode that is bigger than this AVLNode (i.e. right AVLNode).
     * @return
     */
    AVLNode right();

    /**
     * Returns AVLNode's data as String.
     * @return
     */
    String toString();
}
