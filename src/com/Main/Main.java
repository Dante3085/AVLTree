package com.Main;

import com.AVLTree.*;

public class Main
{
    public static void main(String[] args)
    {
//        BinarySearchTree<Integer> b = new BinarySearchTree<>();
//        b.add(2);
//        b.add(7);
//        b.add(18);
//        b.add(-15);
//        b.printInOrder(b.root());

        AVLTree<Integer> a = new AVLTree<>("AVLTreeView");
        // Balance-Factor: +2 (not AVL)
//        a.add(8);
//        a.add(4);
//        a.add(2);
//        a.add(6);
//        a.add(20);
//        a.add(24);
//        a.add(22);
//        a.add(16);
//        a.add(12);
//        a.add(10);
//        a.add(14);
//        a.add(18);

//        a.add(7);
//        a.add(12);
//        a.add(18);
//        a.add(21);
//        a.add(19);
//        a.add(34);

//        // Balance-Factor: +1 (AVL)
//        a.add(4);
//        a.add(2);
//        a.add(1);
//        a.add(3);
//        a.add(8);
//        a.add(9);
//        a.add(6);
//        a.add(5);
//        a.add(7);

        // Balance-Factor: +1 (AVL)
//        a.add(2);
//        a.add(-15);
//        a.add(7);
//        a.add(18);

        // Balance-Faktor:
//        a.add(2);
//        a.add(-15);
//        a.add(7);
//        a.add(18);
//        a.add(-20);
//        a.add(20);

        // Test for LeftRotation: If AVL-Mechanism works correctly, this tree should look like the tree right below it. WORKS
//        a.add(1);
//        a.add(2);
//        a.add(3);

//        a.add(2);
//        a.add(1);
//        a.add(3);

        // Test for RightRotation WORKS
//        a.add(3);
//        a.add(2);
//        a.add(1);

        // Test for RightRotation then add another smallest Element.
//        a.add(3);
//        a.add(2);
//        a.add(1);
//        a.add(0);

        // Test for localized Rotation (Root of main tree is not involved).
//        a.add(3);
//        a.add(2);
//        a.add(4);
//        a.add(5);
//        a.add(6);

//        a.add(3);
//        a.add(5);
//        a.add(4);

//        a.add(5);
//        a.add(6);
//        a.add(7);

//        a.add(3);
//        a.add(1);
//        a.add(2);
//        a.add(4);

        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);

        System.out.println(a.toString());

        //a.delete(3);

        //System.out.println(a.toString());

//        a.delete(50);
//        a.delete(68);
//        a.delete(66);
//
//        System.out.println(a.isBalanced(a.root()));
//        System.out.println(a.toString());
    }
}
