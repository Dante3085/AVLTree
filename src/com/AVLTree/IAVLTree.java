package com.AVLTree;

/**
 * @author mjsch
 * @param <T>
 */
public interface IAVLTree<T extends Comparable<T>>
{
    /**
     * Adds the given element to the AVLTree.
     * If the element is already contained, an appropriate Exception is thrown.
     * @param element element to add
     */
    void add( T element );

    /**
     * Removes the given element from the AVLTree if present. Automatically rebalances AVLTree if necessary.
     * TODO: Currently doesn't rebalance AVLTree.
     * @param element element to remove
     */
    void delete( T element );

    /**
     * Checks if the given element resides in the AVLTree.
     * @param element element to search.
     * @return <font color=aqua>True</font> if element resides in AVLTree. If tree is empty, <font color=aqua>false</font>. Otherwise <font color=aqua>false</font> as well.
     */
    boolean contains( T element );

    /**
     * Checks if the AVlTree is empty.
     * @return True if root is null. Otherwise false.
     */
    boolean isEmpty();

    /**
     * Calculates the number of AVLNodes in this tree.
     * @return 0 if root is null. Otherwise number of AVlNodes.
     */
    int numNodes();

    /**
     * Calculates the length of the longest downward path from the root AVLNode to a leaf. A.k.a. the height of the tree.
     * @return 0 if root is null. Otherwise height of the tree.
     */
    int height(AVLNode<T> root);

    /**
     * Compiles a String with all there is to know about this AVLTree.
     * @return String with all info.
     */
    String toString();
}
