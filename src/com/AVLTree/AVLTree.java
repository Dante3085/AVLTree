package com.AVLTree;

import java.util.*;

/**
 * @author mjsch
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> implements IAVLTree<T>
{
    private AVLNode root;
    private String name;

    /**
     * Creates an AVLTree with an empty root and the given name.
     * @param name
     */
    public AVLTree(String name)
    {
        root = null;
        this.name = name;
    }

    /**
     * - Adds 'element' to the AVLTree.
     * <br>- If 'element' already resides in the AVLTree a 'NodeAlreadyExistsException' is thrown and immediately handled.
     * <br>- Since this an AVLTree, the add Method automatically detects an inbalance after a new element has been added and
     * rebalances the whole tree.
     * <br>- Due to the balanced structure of this tree, every single operation is at worst <font color=aqua>O( log (n) ).</font>
     * @param element element to add
     */
    @Override
    public void add(T element)
    {
        if (isEmpty())
        {
            root = new AVLNode(element);
            return;
        }

        try
        {
            if (contains(element))
                throw new NodeAlreadyExistsException("'" + element.toString() + "' already exists in the tree.");
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }
        root = addRecursive(root, element);
    }

    /**
     * Recursive Help-Method for add().
     * @return
     */
    private AVLNode addRecursive(AVLNode current, T element)
    {
        // Ist man den passenden Pfad zum Einfügen des neuen Elements bis zum Ende durchgelaufen, so kann man an der leeren Stelle das neue Element einfügen,
        // bzw. den neuen Knoten als Linken oder Rechten Nachfolger nach oben geben.
        if (current == null)
            return new AVLNode(element);

        // int cmp = element.compareTo((T) current.data); // TODO: Warum muss ich hier current.data nach T casten ?

        else if (current.data.compareTo(element) < 0)    // < 0 means that current.data is smaller than element TODO: Unchecked call to ... warning message.
        {
            current.right = addRecursive(current.right, element);

            // Kein Knick (Kein Vorzeichenwechsel) => Links-Rotation
            if (balance(current) == 2 && balance(current.right) > 0)
                current = leftRotate(current);

            // Vorzeichenwechsel (Knick) => Rechts-Links-Rotation
            else if (balance(current) == 2 && balance(current.right) < 0)
            {
                current.right = rightRotate(current.right);
                current = leftRotate(current);
            }
        }

        else if (current.data.compareTo(element) > 0)   // > 0 means that current.data is bigger than element.
        {
            current.left = addRecursive(current.left, element);

            // Kein Knick (Kein Vorzeichenwechsel) => Rechts-Rotation
            if (balance(current) == -2 && balance(current.left) < 0)
                current = rightRotate(current);

            // Vorzeichenwechsel (Knick) => Links-Rechts-Rotation
            else if (balance(current) == -2 && balance(current.left) > 0)
            {
                current.left = leftRotate(current.left);
                current = rightRotate(current);
            }
        }
        return current;
    }

    /**
     *
     * @param node
     * @return
     */
    private AVLNode<T> leftRotate(AVLNode<T> node) // node = 1
    {
        AVLNode<T> right = node.right; // right = 2
        node.right = right.left; // 1.right = null
        right.left = node; // 2.left = 1

        if (node == root)
            root = right;

        return right;
    }

    /**
     *
     * @param node
     * @return
     */
    private AVLNode<T> rightRotate(AVLNode<T> node) // EXAMPLE: node = 3
    {
        AVLNode<T> left = node.left; // left = 2
        node.left = left.right; // 3.left = null
        left.right = node; // 2.right = 3

        if (node == root)
            root = left;

        return left;
    }

    /**
     * Calculates balance-factor of this Tree.
     * @return balance-factor
     */
    public int balance(AVLNode<T> root)
    {
        return (height(root.right) - height(root.left));
    }

    /**
     * Returns true if Tree is balanced. Otherwise false.
     * A Tree is balanced if every AVLNode fulfills -2 < balance-factor < 2.
     * @return
     */
    public boolean isBalanced(AVLNode<T> root)
    {
        return (balance(root) == 1 || balance(root) == 0 || balance(root) == -1);
    }

    private T findSmallestElement(AVLNode root)
    {
        return root.left == null ? (T) root.data : findSmallestElement(root.left); // Why is cast needed here ? Should be same Type.
    }

    private AVLNode deleteRecursive(AVLNode current, T element)
    {
        if (current == null)
            return null;

        // Found element Node.
        if (element == current.data)
        {
            // Node is a leaf (i.e. Node has no Children.).
            if (current.left == null && current.right == null)
                return null;

            // Node has one Child. Return non-null Child, so it can be assigned to new Parent.
            if (current.right == null)
                return current.left;

            if (current.left == null)
                return current.right;

            // Node has two Children. Find smallest Value in Right-Subtree (Subtree with all values that are bigger than root.)
            T smallestValue = findSmallestElement(current.right);
            current.data = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if (current.data.compareTo(element) > 0)
        {
            current.left = deleteRecursive(current.left, element);
            return current;
        }

        current.right = deleteRecursive(current.right, element);
        return current;
    }

    @Override
    public void delete(T element)
    {
        if (isEmpty())
        {
            try
            {
                throw new TreeIsEmptyException("You can't delete something in an empty tree.");
            } catch (TreeIsEmptyException e)
            {
                e.printStackTrace();
            }
            return;
        }
        if (!contains(element))
        {
            try
            {
                throw new NodeDoesNotExistException("'" + element.toString() + "' is not in the tree.");
            } catch (NodeDoesNotExistException e)
            {
                e.printStackTrace();
            }
            return;
        }
        if (root.data.equals(element))
            root = deleteRecursive(root, element);
        deleteRecursive(root, element);
    }

    private boolean containsRecursive(AVLNode current, T element)
    {
        if (current == null)
            return false;
        if (element == current.data)
            return true;
        if (current.data.compareTo(element) < 0)
            return containsRecursive(current.right, element);
        if (current.data.compareTo(element) > 0)
            return containsRecursive(current.left, element);
        return false;
    }

    /**
     * Returns true if element is present in this AVLTree. Otherwise false.
     * @param element element to search
     * @return
     */
    @Override
    public boolean contains(T element)
    {
        return containsRecursive(root, element);
    }

    /**
     * Returns true if this AVLTree is empty. Otherwise false.
     * @return
     */
    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Help Method for numNodes().
     * @param node
     * @return
     */
    private int numNodesRecursive(AVLNode node)
    {
        if (node != null)
        {
            return
                    numNodesRecursive(node.left) + numNodesRecursive(node.right) + 1;
        }
        return 0;
    }

    /**
     * Returns the number of Nodes in the AVLTree.
     * @return
     */
    @Override
    public int numNodes()
    {
        return numNodesRecursive(root);
    }

    /**
     * Calculates height of a tree. The tree is specified by it's root.
     * @param root
     * @return
     */
    @Override
    public int height(AVLNode<T> root)
    {
        if (root != null)
            return root.getMaxHeightSubtrees();
        return 0;
    }

    /**
     * Traverses a tree specified by it's root AVLNode in PreOrder and returns a String with all AVLNodes in that Order.
     * @param node
     */
    public String traversePreOrder(AVLNode node)
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
     * Traverses a tree specified by it's root AVLNode in InOrder and returns a String with all AVLNodes in that Order.
     * @param node
     */
    public String traverseInOrder(AVLNode node) // TODO: Change JavaDoc for all print...Order methods.
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
     * Traverses a tree specified by it's root AVLNode in PostOrder and returns a String with all AVLNodes in that Order.
     * @param node
     */
    public String traversePostOrder(AVLNode node)
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
     * Return root of this AVLTree.
     * @return
     */
    public AVLNode root()
    {
        return root;
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
     * @param root Tree is specified by it's root.
     */
    public String traverseBalanceFactorsInOrder(AVLNode<T> root)
    {
        String s = "";
        if (root == null)
            return "";
        s += traverseBalanceFactorsInOrder(root.left);
        //System.out.print(balance(root) + ", ");
        s += balance(root) + ", ";
        s += traverseBalanceFactorsInOrder(root.right);

        // TODO: Cut last ',' from String.
        return s;
    }

    public String name()
    {
        return name;
    }

    /**
     * @inheritDoc
     * @return String
     */
    @Override
    public String toString()
    {
        return  "Name: " + name
                + "\n\tisEmpty: " + isEmpty()
                + "\n\tNumNodes: " + numNodes()
                + "\n\tHeight: " + height(root)
                + "\n\tPreOrder: " + traversePreOrder(root)
                + "\n\tInOrder: " + traverseInOrder(root)
                + "\n\tPostOrder: " + traversePostOrder(root)
                + "\n\tIsBalanced: " + isBalanced(root)
                + "\n\tBalanceFactorsInOrder: " + traverseBalanceFactorsInOrder(root)
                + "\n\tPrintLevelWise: "  + "\n" + traverseLevelWise(root);
    }
}
