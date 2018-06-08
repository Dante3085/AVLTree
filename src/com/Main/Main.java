package com.Main;

import com.AVLTree.*;

public class Main
{
    public static void main(String[] args)
    {
        AVLTree<Integer> a = new AVLTree<>("AVLTree");

        try
        {
            for (int i = 0; i < 1000; i++)
                a.add(i);
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }
        System.out.println(a);
        //TreePrinter.print(a.root());

        //try
        //{
        //    a.delete(42);
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
