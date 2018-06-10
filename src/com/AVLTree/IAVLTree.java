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
    void add( T element ) throws NodeAlreadyExistsException;

    /**
     * Removes the given element from the AVLTree if present. Automatically rebalances AVLTree if necessary.
     * TODO: Currently doesn't rebalance AVLTree.
     * @param element element to remove
     */
    void delete( T element ) throws TreeIsEmptyException, NodeDoesNotExistException;

    /**
     * Checks if the given element resides in the AVLTree.
     * @param element element to search.
     * @return <font color=aqua>True</font> if element resides in AVLTree. If tree is empty, <font color=aqua>false</font>. Otherwise <font color=aqua>false</font> as well.
     */
    boolean contains( T element );

    /**
     * Checks if this AVlTree is empty.
     * @return 'True' if m_root is null. Otherwise 'false'.
     */
    boolean isEmpty();

    /**
     * Calculates the number of AVLNodes in this AVLTree.
     * @return '0' if root is null. Otherwise number of AVLNodes.
     */
    int numNodes();

    /**
     * Calculates the length of the longest downward path from the root AVLNode of this Tree to a leaf. A.k.a. the height of the tree.
     * @return '0' if root is null. Otherwise height of the tree.
     */
    int height();

    /**
     * - Puts node.right as node.right.left
     * <br>- Puts node.right.left as node.
     * <br>- If node is root, puts former node.right as root.
     * <br>- <font color=aqua>Visual:</font> Imagine 3 nodes going from top to diagonally lower right. The top node will go to the left of the node inbetween.
     * <br>If the inbetween node had a left before that, it will now go to the right of the former top node.
     * @param node AVLNode on which the left-rotation is being performed.
     * @return Highest AVLNode that is part of the resulting structure after the left-rotation. TODO: Veränderungsbedarf
     */
    AVLNode<T> leftRotate(AVLNode<T> node);

    /**
     * - Puts node.left as node.left.right
     * <br>- Puts node as right of node.left
     * <br>- If node is root, puts former node.left as new root.
     * <br>- <font color=aqua>Visual:</font> Imagine 3 nodes going from top to diagonally lower left. The top node will go to the right of the node inbetween.
     * <br>If the inbetween node had a right before that, it will now go to the left of the former top node.
     * @param node AVLNode on which the right-rotation is being performed.
     * @return Highest AVLNode that is part of the resulting structure after the left-rotation. TODO: Veränderungsbedarf
     */
    AVLNode<T> rightRotate(AVLNode<T> node);

    /**
     * Calculates the balance-factor of a given AVLNode.
     * <br>The balance-factor can be understood as the number of AVLNodes that the left / right subtree of the given AVLNode has more than the other subtree.
     * (negative sign => left subtree has more; positive sign => right subtree has more.)
     * @param node AVLNode of which the balance-factor is being calculated.
     * @return The balance-factor of the given AVLNode.
     */
    int balance(AVLNode<T> node);

    /**
     * Returns root of this AVLTree.
     * @return
     */
    AVLNode<T> root();

    /**
     * Empties this AVLTree.
     * This AVLTree's root is set to null.
     */
    void flush();

    /**
     * Compiles a String with all there is to know about this AVLTree.
     * @return String with all info.
     */
    String toString();
}
