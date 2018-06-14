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
            a.add(107);
            a.add(77);
            a.add(50);
            a.add(83);
            a.add(140);
            a.add(62);
            a.add(33);
            a.add(2);
            a.add(69);
            a.add(36);
            a.add(12);
            a.add(32);
            a.add(104);
            a.add(18);
            a.add(101);
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
