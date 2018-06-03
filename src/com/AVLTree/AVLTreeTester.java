package com.AVLTree;

public class AVLTreeTester extends AbstractAVLTreeTest
{
    @Override
    public <T extends Comparable<T>> IAVLTree<T> get()
    {
        return new AVLTree<T>("AVLTreeView - JUnitTest");
    }
}
