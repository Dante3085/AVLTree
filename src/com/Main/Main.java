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
            a.add(3);
            a.add(10);
            a.add(2);
            a.add(7);
            a.add(13);
            a.add(14);
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }

        System.out.println(a);

        //try
        //{
        //    a.delete(10);
        //} catch (TreeIsEmptyException e)
        //{
        //    e.printStackTrace();
        //} catch (NodeDoesNotExistException e)
        //{
        //    e.printStackTrace();
        //}

        System.out.println(a);
    }
}
