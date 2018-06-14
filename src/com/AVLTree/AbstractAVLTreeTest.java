package com.AVLTree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractAVLTreeTest
{
    public abstract <T extends Comparable<T>> IAVLTree<T> get();

    private IAVLTree<Integer> tree;

    @Before
    public void init() { tree = get(); }

    /*// Test if initialized tree is empty.
    @Test
    public void test_isEmpty()
    {
        assertTrue("new Tree must be empty!", tree.isEmpty());
    }

    @Test
    public void test_add()
    {
        try
        {
            tree.add(0);
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }

        assertTrue("tree.contains(0) should be true", tree.contains(0));
    }

    @Test
    public void test_numNodes()
    {
        try
        {
            for (int i = 1; i < 11; i++)
                tree.add(i);
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }
        assertTrue("tree.numNodes() must be 10", tree.numNodes() == 10);
    }

    @Test
    public void test_flush()
    {
        tree.flush();
        assertTrue("tree.isEmpty() should be true.", tree.isEmpty());
    }

    *//**
     * Tests if the AVLTree's height is correct.
     * Requires test_flush() to work
     *//*
    @Test
    public void test_height()
    {
        try
        {
            for (int i = 0; i < 5; i++)
                tree.add(i);
        } catch (NodeAlreadyExistsException e)
        {
            e.printStackTrace();
        }
        assertTrue("tree.height() should be 3", tree.height() == 3);
    }*/

    @Test
    public void testDeleteRandom() throws Exception, NodeDoesNotExistException {
        //size setzt die Größe des zu testendes Baumes fest.
        int size =14;
        //repeat gibt an wieviele verschiedene Bäume getestet werden
        int repeat=1000;
        while (repeat > 0) {
        /*    ArrayList<Integer> list = new ArrayList<Integer>();
            //erzeugt eine ArrayList mit zufälligen Zahlen basierend auf size
            while (list.size() != size)
            {
                int n = (int) (Math.random() * size*10);
                while (!list.contains((Integer) n) && n <= size*10)
                {
                    n++;
                    if (!list.contains((Integer) n))
                        list.add((Integer) n);
                }
            }

 */


         //   int num = list.size();
            int[] list = {50, 140};
            int num = list.length;
            for (int value : list)
                tree.add((Integer) value);
            for (int value : list)
            {
                assertTrue("contains(" + value + ") must be true in the list: " + list, tree.contains(value));
                tree.delete(value);
                System.out.println("delete" + value + " size: " + tree.numNodes());
                assertFalse("contains(" + value + ") must be false: " + list, tree.contains(value));

                assertEquals(--num, tree.numNodes());
            }
            repeat--;
        }
    }

}
