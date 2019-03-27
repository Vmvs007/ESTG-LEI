/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;
/**
 *
 * @author Vitor Santos - 8170312
 */
public class BinaryTreeNode<T> {

    protected T element;
    protected BinaryTreeNode<T> left, right;

    /**
     * Creates a new tree node with the specified data.
     *
     * @param obj the element that will become a part of the new tree node
     */
    BinaryTreeNode(T obj) {
        element = obj;
        left = null;
        right = null;
    }

    /**
     * Returns the number of non-null children of this node. This method may be
     * able to be written more efficiently.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = 0;
        if (left != null) {
            children = 1 + left.numChildren();
        }
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        return children;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" + "element=" + element + ", left=" + left + ", right=" + right + '}';
    }
    
    
    

}