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
            for (int i = 1; i <= 7; i++)
                a.add(i);
            a.add(0);
            a.add(20);
            a.add(30);
            a.add(14);
            a.add(16);
            a.add(18);
            a.add(15);
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }
        System.out.println(a);

        try
        {
            a.delete(16);
            a.delete(30);
            a.delete(14);
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
