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
            a.add(1);
            a.add(3);
            a.add(2);
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }
        System.out.println(a);

        //try
        //{
        //    a.delete(16);
        //    a.delete(30);
        //    a.delete(14);
        //    a.delete(4);
        //} catch (TreeIsEmptyException e)
        //{
        //    e.printStackTrace();
        //} catch (NodeDoesNotExistException e)
        //{
        //    e.printStackTrace();
        //}
        //System.out.println(a);
    }
}
