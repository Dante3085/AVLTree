package com.AVLTree;

import java.util.*;

/**
 * An AVLTree is a self-balancing BinarySearchTree. This fact ensures worstCase: <font color=aqua>O( log (n) )</font> for all Operations on this kind of tree.
 * @author mjsch
 * @param <T> extends Comparable<T>.
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
     * @inheritDoc
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
     * @inheritDoc
     */
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

    /**
     * @inheritDoc
     */
    @Override
    public boolean contains(T element)
    {
        return containsRecursive(root, element);
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
     * @inheritDoc
     */
    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * @inheritDoc
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

        if (node == root)
            root = right;

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

        if (node == root)
            root = left;

        return left;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int balance(AVLNode<T> root)
    {
        return (height(root.right) - height(root.left));
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
     * <br><font color=aqua>Remember:</font> All AVLNodes in the left Subtree of a root AVLNode are smaller than that root AVLNode.
     * @param root
     * @return Smallest Data
     */
    private T findSmallestElement(AVLNode root)
    {
        return root.left == null ? (T) root.data : findSmallestElement(root.left); // Why is cast needed here ? Should be same Type.
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

    /**
     * Returns name of this AVLTree.
     * @return
     */
    public String name()
    {
        return name;
    }
}
