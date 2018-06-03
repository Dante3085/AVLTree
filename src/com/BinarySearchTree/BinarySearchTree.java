package com.BinarySearchTree;

import java.util.Deque;
import java.util.LinkedList;

// "T" Typeparameter of com.binarySearchTree.BinarySearchTree has to extend Comparable, since the Interface IBinarySearchTree that it implements does it as well.
public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T>
{
    private Node root;

    public BinarySearchTree()
    {
        root = null;
    }

    /**
     * Help-Method for add(T element) that is supposed to be used recursively.
     * @return
     */
    private Node addRecursive(Node current, T element)
    {
        if (current == null)
            return new Node(element);
        else if (current.data.compareTo(element) < 0)    // < 0 means that current.data is smaller than element TODO: Unchecked call to ... warning message.
            current.right = addRecursive(current.right, element);
        else if (current.data.compareTo(element) > 0)   // > 0 means that current.data is bigger than element.
            current.left = addRecursive(current.left, element);
        return current;
    }

    @Override
    public void add(T element)
    {
        if (isEmpty())
        {
            root = new Node(element);
            return;
        }

        if (contains(element))
        {
            System.out.println("'" + element + "' is already in the BinarySearchTree!");
            return;
        }
        root = addRecursive(root, element);
    }

    private T findSmallestElement(Node root)
    {
        return root.left == null ? (T) root.data : findSmallestElement(root.left); // TODO: Warum muss hier casten.
    }

    private Node deleteRecursive(Node current, T element)
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

            // Node has two Children.
            T smallestValue = findSmallestElement(current.right);
            System.out.println("smallestValue = " + smallestValue);
            current.data = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if (current.data.compareTo(element) > 0) // TODO: Unchecked call to ...
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
            System.out.println("BinarySearchTree is empty!");
        }
        if (!contains(element))
        {
            System.out.println("'" + element + "' is not in the BinarySearchTree!");
            return;
        }
        if (root.data.equals(element))
            root = deleteRecursive(root, element);
        deleteRecursive(root, element);
    }

    private boolean containsRecursive(Node current, T element)
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

    @Override
    public boolean contains(T element)
    {
        return containsRecursive(root, element);
    }

    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    private int traverseInOrder(Node node)
    {
        if (node != null)
        {
            return
                traverseInOrder(node.left) +
                traverseInOrder(node.right) +
                        1;
        }
        return 0;
    }

    @Override
    public int numNodes()
    {
        return traverseInOrder(root);
    }

    @Override
    public int height()
    {
        if (root != null)
            return root.getHeight();
        return 0;
    }

    /**
     *
     * @param node
     */
    public void printPreOrder(Node node)
    {
        if (node == null)
            return;
        System.out.println(node.data);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    public void printInOrder(Node node)
    {
        if (node == null)
            return;
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

    public void printPostOrder(Node node)
    {
        if (node == null)
            return;
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.data + " ");
    }

    public Node root()
    {
        return root;
    }

    @Override
    public String toString()
    {
        return "isEmpty: " + isEmpty()
                + "\n\tNumNodes: " + numNodes()
                + "\n\tHeight: " + height();
    }

    public String traversiereStack(Node einKnoten)
    {
        String text = "";
        Deque<Node<T>> s = new LinkedList<Node<T>>();

        s.push(einKnoten);

        while (!s.isEmpty())
        {
            einKnoten = s.pop();
            text += einKnoten.data.toString();
            System.out.println(text);

            if (einKnoten.right != null)
                s.push(einKnoten.right);
            if (einKnoten.left != null)
                s.push(einKnoten.left);
        }
        return text;
    }
}
