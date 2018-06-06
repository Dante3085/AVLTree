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
    public void test_isEmpty()
    {
        assertTrue("new Tree must be empty!", tree.isEmpty(tree.root()));
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
        assertTrue("tree.numNodes() must be 10", tree.numNodes(tree.root()) == 10);
    }

    @Test
    public void test_flush()
    {
        tree.flush();
        assertTrue("tree.isEmpty() should be true.", tree.isEmpty(tree.root()));
    }

    /**
     * Tests if the AVLTree's height is correct.
     * Requires test_flush() to work
     */
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
        assertTrue("tree.height() should be 3", tree.height(tree.root()) == 3);
    }
}
