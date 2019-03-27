/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

import java.util.Iterator;
import java.util.*;
import interfaces.BinaryTreeADT;
import exceptions.ElementNotFoundException;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary
     * tree
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    @Override
    public T getRoot() {
        if (isEmpty()) {
            return null;
        } else {
            return root.element;
        }
    }

    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T targetElement) {
        try {
            find(targetElement);

        } catch (ElementNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null) {
            throw new ElementNotFoundException("binary tree");
        }

        return (current.element);
    }

    private BinaryTreeNode<T> findAgain(T targetElement,
            BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.element.equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }

        return temp;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        inorder(root, tempList);

        return tempList.iterator();
    }

    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        preorder(root, tempList);

        return tempList.iterator();
    }

    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            preorder(node.left, tempList);
            preorder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        postorder(root, tempList);
        return tempList.iterator();
    }

    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {

            postorder(node.left, tempList);
            postorder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>();
        levelorder(root, tempList);
        return tempList.iterator();
    }

    public void levelorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {

        LinkedQueue<BinaryTreeNode<T>> tempQueue = new LinkedQueue<>();

        if (node != null) {

            tempQueue.enqueue(node);
            while (!tempQueue.isEmpty()) {
                BinaryTreeNode<T> aux = tempQueue.dequeue();
                if (aux == null) {
                    tempList.addToRear(null);
                } else {
                    tempList.addToRear(aux.element);
                    tempQueue.enqueue(aux.left);
                    tempQueue.enqueue(aux.right);
                }

            }

        }
    }

}