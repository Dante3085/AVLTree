package com.BinarySearchTree;

public interface IBinarySearchTree<T extends Comparable<T>> {

    /**
     * Adds the given element into the tree.
     * IF the element is already contained, does nothing!
     * @param element element to add
     */
    void add( T element );

    /**
     * Removes the given element from the tree if present.
     * @param element element to remove
     */
    void delete( T element );

    /**
     * Checks if the given element is contained in the tree.
     * @param element element to search
     * @return true if element is contained in the tree
     */
    boolean contains( T element );

    /**
     * Checks if the tree is empty.
     * @return true if the tree contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the number of nodes in this tree.
     * @return number of nodes in tree
     */
    int numNodes();

    /**
     * Returns the height (aka the length of the longest path) of this tree.
     * @return height of tree
     */
    int height();
}
