package com.AVLTree.ui;

import com.AVLTree.AVLTree;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class AVLTreeView<T extends Comparable<T>> extends StackPane
{
    private ArrayList<AVLNodeView> avlNodeViews = new ArrayList<>();
    private AVLTree<T> avlTree;

    private int x = 100;
    private int y = 100;

    public AVLTreeView(String name)
    {
        avlTree = new AVLTree<T>(name);
    }

    public void add(T element)
    {
        avlNodeViews.add(new AVLNodeView(x, y, element.toString()));
        this.getChildren().add(avlNodeViews.get(avlNodeViews.size()-1));
    }
}
