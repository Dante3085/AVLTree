package com.AVLTree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class AbstractAVLTreeTest
{
    public abstract <T extends Comparable<T>> IAVLTree<T> get();

    private IAVLTree<Integer> tree;

    @Before
    public void init() { tree = get(); }

    // Test if initialized tree is empty.
    @Test
    public void isEmpty()
    {
        assertTrue("new Tree must be empty!", tree.isEmpty());
        System.out.println("Test: 'isEmpty' has been successful.");
    }

//    @Test
//    public void addTest()
//    {
//        for (int i = 0; i < 10; i++)
//            tree.add(i);
//
//    }
}
