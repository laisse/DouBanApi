package com.example.chaowang.imageapplication.utils;

import java.util.ArrayList;
import java.util.LinkedList;

public class RBTree<T extends Comparable<T>> {

    private RBTNode<T> mRoot;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    LinkedList<String> linkedList = new LinkedList<String>();

    public void setBlack(RBTNode<T> node) {
        node.color = BLACK;
    }

    public void setRed(RBTNode<T> node) {
        node.color = RED;
    }

    public class RBTNode<T extends Comparable<T>> {
        boolean color;
        T key;
        RBTNode<T> left;
        RBTNode<T> right;
        RBTNode<T> parent;

        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right)
        {
            this.key = key;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    private void leftRotate(RBTNode<T> x)
    {
        RBTNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null)
        {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null)
        {
            this.mRoot = y;
        }
        else
        {
            if (x.parent.left == x)
            {
                x.parent.left = y;
            }
            else
            {
                x.parent.right = y;
            }
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(RBTNode<T> y)
    {
        RBTNode<T> x = y.left;

        y.left = x.right;
        if (x.right != null)
        {
            x.right.parent = y;
        }

        x.parent = y.parent;
        if (y.parent == null)
        {
            this.mRoot = x;
        }
        else
        {
            if (y.parent.right == y)
            {
                y.parent.right = x;
            }
            else
            {
                y.parent.left = x;
            }
        }

        x.right = y;
        y.parent = x;
    }

    private void insert(RBTNode<T> node)
    {
        int cmp;
        RBTNode<T> y = null;
        RBTNode<T> x = this.mRoot;

        while(x != null)
        {
            y = x;
            if (node.key.compareTo(x.key) < 0)
            {
                x = x.left;
            }
            else
            {
                x = x.right;
            }
        }

        node.parent = y;

        if (y != null)
        {
            if (node.key.compareTo(y.key) < 0)
            {
                y.left = node;
            }
            else
            {
                y.right = node;
            }
        }
        else
        {
            this.mRoot = node;
        }
        node.color = RED;

        insertFixUp(node);

    }

    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, gparent;

        while (((parent = parentOf(node)) != null) && isRed(parent))
        {
            gparent = parentOf(parent);

            if (parent == gparent.left)
            {
                RBTNode<T> uncle = gparent.right;
                if ((uncle != null) && isRed(uncle))
                {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                if (parent.right == node)
                {
                    RBTNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }
                setBlack(parent);
                setRed(gparent);
            }
        }
    }

    private boolean isRed(RBTNode<T> parent) {
        if (parent.color == RED)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node.parent;
    }

}
