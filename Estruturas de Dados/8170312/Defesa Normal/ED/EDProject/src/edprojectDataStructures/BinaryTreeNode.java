/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

import java.util.Iterator;

/**
 *
 * @author vmvs0
 */

public class BinaryTreeNode<T> {

   protected T element;
   protected BinaryTreeNode<T> left, right;

   //================================================================
   //  Creates a new tree node with the specified data.
   //================================================================
   BinaryTreeNode (T obj) 
   {
      element = obj;
      left = null;
      right = null;
   }  // constructor BinaryTreeNode

   //================================================================
   //  Returns the number of non-null children of this node.
   //  This method may be able to be written more efficiently.
   //================================================================
   public int numChildren() 
   {

      int children = 0;

      if (left != null)
         children = 1 + left.numChildren();

      if (right != null)
         children = children + 1 + right.numChildren();

      return children;

   }  // method numChildren
   
}  // class BinaryTreeNode
