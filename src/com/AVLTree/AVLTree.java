package com.AVLTree;

import com.util.Util;

import java.util.*;

/**
 * - An AVLTree is a self-balancing BinarySearchTree. This fact ensures worstCase: <font color=aqua>O( log (n) )</font> for all Operations on this kind of BinarySearchTree.
 * <br> Conventions: 1. Names of Object-attributes are preceded with 'm_'.
 * @author mjsch
 * @param <T> extends Comparable<T>.
 */
// TODO: Mache allgemein anwendbare Methoden static ?!
public class AVLTree<T extends Comparable<T>> implements IAVLTree<T>
{
    private AVLNode<T> m_root;
    private String m_name;

    /**
     * Creates an AVLTree with an empty m_root and the given m_name.
     * @param name
     */
    public AVLTree(String name)
    {
        m_root = null;
        m_name = name;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void add(T element) throws NodeAlreadyExistsException
    {
        if (isEmpty(m_root))
        {
            m_root = new AVLNode<T>(element); // TODO: Unchecked Assignment ???
            return;
        }
        m_root = addRecursive(m_root, element);
    }

    /**
     * Recursive Help-Method for add().
     * @return
     */
    private AVLNode<T> addRecursive(AVLNode<T> current, T element) throws NodeAlreadyExistsException
    {
        // Ist man den passenden Pfad zum Einfügen des neuen Elements bis zum Ende durchgelaufen, so kann man an der leeren Stelle das neue Element einfügen,
        // bzw. den neuen Knoten als Linken oder Rechten Nachfolger nach oben geben.
        if (current == null)
            return new AVLNode<T>(element);
        else if (current.data.equals(element))
            throw new NodeAlreadyExistsException("'" + element.toString() + "' already exists in the AVLTree.");

        // int cmp = element.compareTo((T) current.data); // TODO: Warum muss ich hier current.data nach T casten ?

        else if (current.data.compareTo(element) < 0)    // < 0 means that current.data is smaller than element TODO: Unchecked call to ... warning message.
        {
            current.right = addRecursive(current.right, element);

            int balanceCurrent = balance(current);
            int balanceCurrentRight = balance(current.right);

            // Kein Knick (Kein Vorzeichenwechsel) => Links-Rotation
            if (balanceCurrent == 2 && balanceCurrentRight > 0)
                current = leftRotate(current);

            // Vorzeichenwechsel (Knick) => Rechts-Links-Rotation
            else if (balanceCurrent == 2 && balanceCurrentRight < 0)
            {
                current.right = rightRotate(current.right);
                current = leftRotate(current);
            }
        }

        else if (current.data.compareTo(element) > 0)   // > 0 means that current.data is bigger than element.
        {
            current.left = addRecursive(current.left, element);

            int balanceCurrent = balance(current);
            int balanceCurrentLeft = balance(current.left);

            // Kein Knick (Kein Vorzeichenwechsel) => Rechts-Rotation
            if (balanceCurrent == -2 && balanceCurrentLeft < 0)
                current = rightRotate(current);

            // Vorzeichenwechsel (Knick) => Links-Rechts-Rotation
            else if (balanceCurrent == -2 && balanceCurrentLeft > 0)
            {
                current.left = leftRotate(current.left);
                current = rightRotate(current);
            }
        }
        return current;
    }

    /**
     * TODO: Rebalancing does not work properly.
     * @inheritDoc
     */
    @Override
    public void delete(T element) throws TreeIsEmptyException, NodeDoesNotExistException
    {
        if (isEmpty(m_root))
            throw new TreeIsEmptyException("Tree is empty");
        m_root = deleteRecursive(m_root, element);
    }

    private AVLNode<T> deleteRecursive(AVLNode<T> current, T element) throws NodeDoesNotExistException
    {
        // No AVLNode in this tree contains element.
        if (current == null)
        {
            System.out.println("Element does not reside in the tree.");
            return null;
        }

        // Found AVLNode that contains element.
        if (element.equals(current.data))
        {
            // AVLNode is a leaf (i.e. AVLNode has no Children.).
            if (current.left == null && current.right == null)
            {
                return null;
            }

            // AVLNode has no right child => return left Child.
            if (current.right == null)
            {
                return current.left;
            }

            // AVLNode has no left child => return right Child.
            if (current.left == null)
            {
                return current.right;
            }

            // REMEMBER: The following section is ONLY executed when the AVLNode has 2 Children.
            // AVLNode has 2 children => Find smallest Key in right subtree and replace current.data with it.
            T smallestKey = findSmallestKey(current.right); // Find smallest key in right subtree and remember it.
            current.data = smallestKey; // Override current data with smallestKey.
            current.right = deleteRecursive(current.right, smallestKey); // Delete AVLNode that originally had smallestKey.
            // return current; // Then return the changed subtree.

            int balanceCurrent = balance(current);

            // balanceCurrent = +2 => Overweight in right subtree.
            if (balanceCurrent == 2)
            {
                int balanceCurrentRight = balance(current.right);

                // balanceCurrentRight >= 0 => No change of sign (kein Vorzeichenwechsel) => no crinkle (kein Knick) => simple Left-Rotation.
                if (balanceCurrentRight >= 0)
                    current = leftRotate(current);

                // balanceCurrentRight < 0 => Change of sign (Vorzeichenwechsel) => crinkle (Knick) => Right-Left-Rotation.
                else
                {
                    current.right = rightRotate(current.right);
                    current = leftRotate(current);
                }
            }

            // balanceCurrent = -2 => Overweight in left subtree.
            else if (balanceCurrent == -2)
            {
                int balanceCurrentLeft = balance(current.left);

                // balanceCurrentLeft <= 0 => No change of sign (kein Vorzeichenwechsel) => no crinkle (kein Knick) => simple Right-Rotation.
                if (balanceCurrentLeft <= 0)
                    current = rightRotate(current);

                // balanceCurrentLeft > 0 => Change of sign (Vorzeichenwechsel) => crinkle (Knick) => Left-Right-Rotation.
                else
                {
                    current.left = leftRotate(current.left);
                    current = rightRotate(current);
                }
            }
        }

        // Value of current AVLNode is bigger than element => GO LEFT
        if (current.data.compareTo(element) > 0)
        {
            current.left = deleteRecursive(current.left, element);

            int balanceCurrent = balance(current);
            int balanceCurrentRight = balance(current.right);

            if (balanceCurrent == 2 && balanceCurrentRight >= 0)
                current = leftRotate(current);
            else if (balanceCurrent == 2 && balanceCurrentRight < 0)
            {
                current.right = rightRotate(current.right);
                current = leftRotate(current);
            }
        }

        // Value of current AVLNode is smaller than element => GO RIGHT
        else if (current.data.compareTo(element) < 0)
        {
            current.right = deleteRecursive(current.right, element);

            int balanceCurrent = balance(current);
            int balanceCurrentLeft = balance(current.left);

            if (balanceCurrent == -2 && balanceCurrentLeft <= 0)
                current = rightRotate(current);
            else if (balanceCurrent == -2 && balanceCurrentLeft > 0)
            {
                current.left = leftRotate(current.left);
                current = rightRotate(current);
            }
        }
        return current;
    }

    /**
     * @inheritDoc
     */
    // TODO: Soll in Zukunft einen AVLNode Parameter haben, damit in beliebigen AVLTrees gesucht werden kann.
    @Override
    public boolean contains(T element)
    {
        return containsRecursive(m_root, element);
    }

    /**
     * Recursive Help-Method for contains(T element).
     * <br>Returns 'true' if element is found. Otherwise 'false'.
     * @param root Root AVLNode of AVLTree in which element is searched.
     * @param element Element (Key) which is searched for.
     * @return 'True' if element is found. Otherwise false.
     */
    private boolean containsRecursive(AVLNode<T> root, T element)
    {
        if (root == null)
            return false;
        if (element == root.data)
            return true;
        if (root.data.compareTo(element) < 0)
            return containsRecursive(root.right, element);
        if (root.data.compareTo(element) > 0)
            return containsRecursive(root.left, element);
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isEmpty(AVLNode<T> root)
    {
        return root == null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int numNodes(AVLNode<T> root)
    {
        return numNodesRecursive(root);
    }

    /**
     * Recursive Help-Method for numNodes(AVLNode<T> root).
     * @param root Root AVLNode of AVLTree of which it's number of AVLNodes is being calculated.
     * @return '0' if root is null. Otherwise number of AVLNodes.
     */
    private int numNodesRecursive(AVLNode<T> root)
    {
        if (!isEmpty(root))
            return (numNodesRecursive(root.left) + numNodesRecursive(root.right) + 1);
        return 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int height(AVLNode<T> root)
    {
        if (root != null)
            return root.height();
        return 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AVLNode<T> leftRotate(AVLNode<T> node) // node = 1
    {
        AVLNode<T> right = node.right; // right = 2
        node.right = right.left; // 1.right = null
        right.left = node; // 2.left = 1

        if (node == m_root)
            m_root = right;

        return right;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AVLNode<T> rightRotate(AVLNode<T> node) // EXAMPLE: node = 3
    {
        // Dreieckstausch.
        AVLNode<T> left = node.left; // left = 2
        node.left = left.right; // 3.left = null
        left.right = node; // 2.right = 3

        if (node == m_root)
            m_root = left;

        return left;
    }

    /**
     * // TODO: Return "-1" is probably dangerous. Just happened to work for now. CHANGE IT!
     * @inheritDoc
     */
    @Override
    public int balance(AVLNode<T> node)
    {
        return (isEmpty(node)) ? -1 : (height(node.right) - height(node.left));
    }

    /**
     * Return m_root of this AVLTree.
     * @return
     */
    @Override
    public AVLNode<T> root()
    {
        return m_root;
    }

    @Override
    public void flush()
    {
        m_root = null;
    }

    /**
     * @inheritDoc
     */
    // TODO: Changed toString(). Now writes compiled String into a .txt File aswell.
    @Override
    public String toString()
    {
        String s = "Name: " + m_name
                + "\n\tisEmpty: " + isEmpty(m_root)
                + "\n\tNumNodes: " + numNodes(m_root)
                + "\n\tHeight: " + height(m_root)
                + "\n\tPreOrder: " + traversePreOrder(m_root)
                + "\n\tInOrder: " + traverseInOrder(m_root)
                + "\n\tPostOrder: " + traversePostOrder(m_root)
                + "\n\tIsBalanced: " + isBalanced(m_root)
                + "\n\tBalanceFactorsInOrder: " + traverseBalanceFactorsInOrder(m_root)
                + "\n\tPrintLevelWise: "  + "\n" + traverseLevelWise(m_root);
        Util.writeToFile("AVLTree.txt", s);
        return s;
    }

    /**
     * Checks wheter or not Tree is balanced.
     * <br>A Tree is balanced if every AVLNode fulfills "-2 < balance-factor < 2".
     * @return Returns true if Tree is balanced. Otherwise false.
     */
    public boolean isBalanced(AVLNode<T> root)
    {
        return (balance(root) == 1 || balance(root) == 0 || balance(root) == -1);
    }

    /**
     * Finds smallest Data in AVLTree specified by root AVLNode and returns that Data.
     * <br><font color=aqua>Remember:</font> All AVLNodes in the left Subtree of a m_root AVLNode are smaller than that m_root AVLNode.
     * @param root
     * @return Smallest Data
     */
    private T findSmallestKey(AVLNode<T> root)
    {
        return (root.left == null) ? (T)root.data : findSmallestKey(root.left); // Why is cast needed here ? Should be same Type.
    }

    /**
     * Traverses a tree specified by it's m_root AVLNode in PreOrder and returns a String with all AVLNodes in that Order.
     * @param node
     */
    public String traversePreOrder(AVLNode<T> node)
    {
        String s = "";
        if (node == null)
            return "";

        s += node.data.toString() + ", ";
        //System.out.println(node.data);
        s += traversePreOrder(node.left);
        s += traversePreOrder(node.right);

        // TODO: Cut last ',' from String.
        return s;
    }

    /**
     * Traverses a tree specified by it's m_root AVLNode in InOrder and returns a String with all AVLNodes in that Order.
     * @param node
     */
    public String traverseInOrder(AVLNode<T> node) // TODO: Change JavaDoc for all print...Order methods.
    {
        String s = "";
        if (node == null)
            return "";
        s += traverseInOrder(node.left);
        //System.out.print(node.data + " ");
        s += node.data.toString() + ", ";
        s += traverseInOrder(node.right);

        // TODO: Cut last ',' from String.
        return s;
    }

    /**
     * Traverses a tree specified by it's m_root AVLNode in PostOrder and returns a String with all AVLNodes in that Order.
     * @param node
     */
    public String traversePostOrder(AVLNode<T> node)
    {
        String s = "";
        if (node == null)
            return "";
        s += traversePostOrder(node.left);
        s += traversePostOrder(node.right);
        //System.out.print(node.data + " ");
        s += node.data.toString() + ", ";

        // TODO: Cut last ',' from String.
        return s;
    }

    /**
     * Prints all tree levels.
     * Each containing all AVLNodes in the same depth.
     * @param root
     */
    public String traverseLevelWise(AVLNode<T> root) {
        List<List<AVLNode<T>>> levels = traverseLevels(root);

        String s = "";
        for (List<AVLNode<T>> level : levels) {
            for (AVLNode<T> node : level) {
                //System.out.print(node.data + " ");
                s +=  "\t" + node.data + " ";
            }
            //System.out.println();
            s += "\n";
        }
        return s;
    }

    /**
     * Help Method for traverseLevelWise().
     * @param root
     * @return
     */
    private List<List<AVLNode<T>>> traverseLevels(AVLNode<T> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<AVLNode<T>>> levels = new LinkedList<>();

        Queue<AVLNode<T>> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            List<AVLNode<T>> level = new ArrayList<>(nodes.size());
            levels.add(level);

            for (AVLNode<T> node : new ArrayList<>(nodes)) {
                level.add(node);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
                nodes.poll();
            }
        }
        return levels;
    }

    /**
     * Returns a String with balance-factors of all AVLNodes traversed InOrder.
     * @param root Tree is specified by it's m_root.
     */
    public String traverseBalanceFactorsInOrder(AVLNode<T> root)
    {
        String s = "";
        if (root == null)
            return "";
        s += traverseBalanceFactorsInOrder(root.left);
        //System.out.print(balance(m_root) + ", ");
        s += balance(root) + ", ";
        s += traverseBalanceFactorsInOrder(root.right);

        // TODO: Cut last ',' from String.
        return s;
    }

    /**
     * Returns m_name of this AVLTree.
     * @return
     */
    public String name()
    {
        return m_name;
    }
}
