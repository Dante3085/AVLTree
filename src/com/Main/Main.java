package com.Main;

import com.AVLTree.AVLTree;
import com.AVLTree.NodeAlreadyExistsException;
import com.AVLTree.NodeDoesNotExistException;
import com.AVLTree.TreeIsEmptyException;

public class Main
{
    public static void main(String[] args)
    {
        AVLTree<Integer> a = new AVLTree<>("AVLTree");

        try
        {
            a.add(5);
            a.add(2);
            a.add(16);
            a.add(1);
            a.add(4);
            a.add(222);
            a.add(0);
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }
        System.out.println(a);

        try
        {
            a.delete(4);
        } catch (TreeIsEmptyException e)
        {
            e.printStackTrace();
        } catch (NodeDoesNotExistException e)
        {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
