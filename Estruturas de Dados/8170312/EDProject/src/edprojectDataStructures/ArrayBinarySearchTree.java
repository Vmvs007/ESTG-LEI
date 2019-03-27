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
import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import interfaces.BinarySearchTreeADT;
import java.util.Iterator;


// This class implements a binary search tree using an array
public class ArrayBinarySearchTree<T>  extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {

   protected int height;
   protected int maxIndex;

   //================================================================
   //  Creates an empty binary search tree.
   //================================================================
   public ArrayBinarySearchTree() 
   {
      super();
      height = 0;
      maxIndex = -1;
   }  // constructor BinarySearchTree

   //================================================================
   //  Creates a binary search with the specified element as its
   //  root.
   //================================================================
   public ArrayBinarySearchTree (T element) 
   {
      super(element);
      height = 1;
      maxIndex = 0;
   }  // constructor BinarySearchTree

   //================================================================
   //  Adds the specified object to the binary search tree in the
   //  appropriate position according to its key value.  Note that
   //  equal elements are added to the right.  Also note that the
   //  index of the left child of the current index can be found by
   //  doubling the current index and adding 1.  Finding the index
   //  of the right child can be calculated by doubling the current
   //  index and adding 2.
   //================================================================
   public void addElement (T element) 
   {

      if (tree.length < maxIndex*2+3)
         expandCapacity();

      Comparable<T> tempelement = (Comparable<T>)element;

      if (isEmpty()) {
         tree[0] = element;
         maxIndex = 0;
      }
      else 
      {
         boolean added = false;
         int currentIndex = 0;

         while (!added) 
         {
            if (tempelement.compareTo((tree[currentIndex]) ) < 0) 
            {
               // go left
               if (tree[currentIndex*2+1] == null) 
               {
                  tree[currentIndex*2+1] = element;
                  added = true;
                  if (currentIndex*2+1 > maxIndex)
                     maxIndex = currentIndex*2+1;
               }
               else
                  currentIndex = currentIndex*2+1;
            }
            else {
               // go right
               if (tree[currentIndex*2+2] == null) 
               {
                  tree[currentIndex*2+2] = element;
                  added = true;
                  if (currentIndex*2+2 > maxIndex)
                     maxIndex = currentIndex*2+2;
               }
               else
                  currentIndex = currentIndex*2+2;
            }
            
         }//while
      }//else

      height = (int)(Math.log(maxIndex + 1) / Math.log(2)) + 1;
      count++;

   }  // method addElement

   //================================================================
   //  Removes the first element that matches the specified target
   //  element from the binary search tree and returns a reference to
   //  it.  Throws a ElementNotFoundException if the specified target
   //  element is not found in the binary search tree.
   //================================================================
   public T removeElement (T targetElement) throws
   ElementNotFoundException 
   {
      T result = null;
      boolean found = false;

      if (isEmpty())
         return result;

      for (int i = 0; (i <= maxIndex) && !found; i++) {
         if ((tree[i] != null) && targetElement.equals(tree[i])) 
         {
            found = true;
            result = tree[i] ;               
            replace(i);
            count--;
         }
      }

      if (!found)
         throw new ElementNotFoundException("element not found in the binary tree");
      
      int temp = maxIndex;
      maxIndex = -1;
      for (int i = 0; i <= temp; i++)
         if (tree[i] != null)
            maxIndex = i;
      
      height = (int)(Math.log(maxIndex + 1) / Math.log(2)) + 1;
      
      return result;
      
   } // method removeElement

   //================================================================
   //  Returns a copy of the array containing the values in the tree
   //================================================================
   public T[] getArray()
   {
      T[] temp;
      if (size() == 0) {
         temp = (T[]) new Object[0];
         return temp;
      }

      temp = (T[]) new Object[tree.length];
      for (int i = 0; i < tree.length; i++) {
         if (tree[i] != null)
            temp[i] = tree[i];
         else
            temp[i] = null;
      }
      return temp;
   }

   //================================================================
   //  Returns the height of the tree
   //================================================================
   public int getHeight()
   {
      return height;
   }

   //================================================================
   //  Returns the max index
   //================================================================
   public int getMaxIndex()
   {
      return maxIndex;
   }

   //================================================================
   //  Deletes all nodes from the binary tree.
   //================================================================
   public void removeAllElements() 
   {
      super.removeAllElements();
      height = 0;
      maxIndex = -1;
   }

   //================================================================
   //  Removes elements that match the specified target
   //  element from the binary search tree 
   //  Throws a ElementNotFoundException if the sepcified target
   //  element is not found in the binary search tree.
   //================================================================
   public void removeAllOccurrences (T targetElement) throws
   ElementNotFoundException 
   {
        removeElement(targetElement);

	   while (contains(targetElement))
          removeElement(targetElement);

   }  // method removeAllOccurrences

   //================================================================
   //  Removes the node with the least value from the binary search
   //  tree and returns a reference to its element.  Throws an
   //  EmptyBinarySearchTreeException if the binary search tree is
   //  empty. 
   //================================================================
   public T removeMin() throws EmptyCollectionException 
   {

      T result = null;

      if (isEmpty())
           throw new EmptyCollectionException ("binary tree");
      else 
      {
         int currentIndex = 1;
         int previousIndex = 0;
         while (tree[currentIndex] != null && currentIndex <= tree.length) 
         {
            previousIndex = currentIndex;
            currentIndex = currentIndex * 2 + 1;
         } //while
         result = tree[previousIndex] ;
         replace(previousIndex);
      } //else

      count--;

      return result;
   }  // method removeMin

   //================================================================
   //  Removes the node with the highest value from the binary
   //  search tree and returns a reference to its element.  Throws an
   //  EmptyBinarySearchTreeException if the binary search tree is
   //  empty. 
   //================================================================
   public T removeMax() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
           throw new EmptyCollectionException ("binary tree");
      else 
      {
         int currentIndex = 2;
         int previousIndex = 0;
         while (tree[currentIndex] != null && currentIndex <= maxIndex) 
         {
            previousIndex = currentIndex;
            currentIndex = currentIndex * 2 + 2;
         } //while
         result = tree[previousIndex] ;
         replace(previousIndex);
      } //else

      count--;

      return result;
   }  // method removeMax

   //================================================================
   //  Returns the element with the least value in the binary search
   //  tree.  It does not remove the node from the binary search
   //  tree.  Throws an EmptyBinarySearchTreeException if the binary
   //  search tree is empty.
   //================================================================
   public T findMin() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
           throw new EmptyCollectionException ("binary tree");
      else {
         int currentIndex = 0;
         while ((currentIndex*2+1 <= maxIndex) && (tree[currentIndex*2+1] != null))
            currentIndex = currentIndex*2+1;
         result = tree[currentIndex] ;
      }
      return result;
   }

   //================================================================
   //  Returns the element with the highest value in the binary
   //  search tree.  It does not remove the node from the binary
   //  search tree.  Throws an EmptyBinarySearchTreeException if the 
   //  binary search tree is empty.
   //================================================================
   public T findMax() throws EmptyCollectionException 
   {
      T result = null;

      if (isEmpty())
           throw new EmptyCollectionException ("binary tree");
      else {
         int currentIndex = 0;
         while ((currentIndex*2+2 <= maxIndex) && (tree[currentIndex*2+2] != null))
            currentIndex = currentIndex*2+2;
         result = tree[currentIndex] ;
      }
      return result;
   }  


   //================================================================
   //  Removes the node specified for removal and shifts the tree
   //  array accordingly.
   //================================================================
   protected void replace (int targetIndex) 
   {
      int currentIndex, parentIndex, temp, oldIndex, newIndex;
      ArrayUnorderedList<Integer> oldlist = new ArrayUnorderedList<Integer>();
      ArrayUnorderedList<Integer> newlist = new ArrayUnorderedList<Integer>();
      ArrayUnorderedList<Integer> templist = new ArrayUnorderedList<Integer>();
      Iterator<Integer> oldIt, newIt;

      // if target node has no children
      if ((targetIndex*2+1 >= tree.length) || (targetIndex*2+2 >= tree.length))
         tree[targetIndex] = null;

      // if target node has no children
      else if ((tree[targetIndex*2+1] == null) && (tree[targetIndex*2+2] == null))
         tree[targetIndex] = null;
      
      // if target node only has a left child
      else if ((tree[targetIndex*2+1] != null) && (tree[targetIndex*2+2] == null)) {

         // fill newlist with indices of nodes that will replace 
         // the corresponding indices in oldlist
         
         // fill newlist
         currentIndex = targetIndex*2+1;
         templist.addToRear(new Integer(currentIndex));
         while (!templist.isEmpty()) {
            currentIndex = ((Integer)templist.removeFirst()).intValue();
            newlist.addToRear(new Integer(currentIndex));
            if ((currentIndex*2+2) <= (Math.pow(2,height)-2)) {
               templist.addToRear(new Integer(currentIndex*2+1));
               templist.addToRear(new Integer(currentIndex*2+2));
            }
         }
         
         // fill oldlist
         currentIndex = targetIndex;
         templist.addToRear(new Integer(currentIndex));
         while (!templist.isEmpty()) {
            currentIndex = ((Integer)templist.removeFirst()).intValue();
            oldlist.addToRear(new Integer(currentIndex));
            if ((currentIndex*2+2) <= (Math.pow(2,height)-2)) {
               templist.addToRear(new Integer(currentIndex*2+1));
               templist.addToRear(new Integer(currentIndex*2+2));
            }
         }
         
         // do replacement
         oldIt = oldlist.iterator();
         newIt = newlist.iterator();
         while (newIt.hasNext()) {
            oldIndex = oldIt.next();
            newIndex = newIt.next();
            tree[oldIndex] = tree[newIndex];
            tree[newIndex] = null;
         }         
      }

      // if target node only has a right child
      else if ((tree[targetIndex*2+1] == null) && (tree[targetIndex*2+2] != null)) {

         // fill newlist with indices of nodes that will replace 
         // the corresponding indices in oldlist
         
         // fill newlist
         currentIndex = targetIndex*2+2;
         templist.addToRear(new Integer(currentIndex));
         while (!templist.isEmpty()) {
            currentIndex = ((Integer)templist.removeFirst()).intValue();
            newlist.addToRear(new Integer(currentIndex));
            if ((currentIndex*2+2) <= (Math.pow(2,height)-2)) {
               templist.addToRear(new Integer(currentIndex*2+1));
               templist.addToRear(new Integer(currentIndex*2+2));
            }
         }
         
         // fill oldlist
         currentIndex = targetIndex;
         templist.addToRear(new Integer(currentIndex));
         while (!templist.isEmpty()) {
            currentIndex = ((Integer)templist.removeFirst()).intValue();
            oldlist.addToRear(new Integer(currentIndex));
            if ((currentIndex*2+2) <= (Math.pow(2,height)-2)) {
               templist.addToRear(new Integer(currentIndex*2+1));
               templist.addToRear(new Integer(currentIndex*2+2));
            }
         }
         
         // do replacement
         oldIt = oldlist.iterator();
         newIt = newlist.iterator();
         while (newIt.hasNext()) {
            oldIndex = oldIt.next();
            newIndex = newIt.next();
            tree[oldIndex] = tree[newIndex];
            tree[newIndex] = null;
         }         
      }

      // target node has two children
      else
      {
         currentIndex = targetIndex*2+2;
         
         while (tree[currentIndex*2+1] != null) {
            currentIndex = currentIndex*2+1;
         }
         
         tree[targetIndex] = tree[currentIndex];

         // the index of the root of the subtree to be replaced
         int currentRoot = currentIndex;  
         
         // if currentIndex has a right child
         if (tree[currentRoot*2+2] != null) {

            // fill newlist with indices of nodes that will replace 
            // the corresponding indices in oldlist
               
            // fill newlist
            currentIndex = currentRoot*2+2;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
               currentIndex = ((Integer)templist.removeFirst()).intValue();
               newlist.addToRear(new Integer(currentIndex));
               if ((currentIndex*2+2) <= (Math.pow(2,height)-2)) {
                  templist.addToRear(new Integer(currentIndex*2+1));
                  templist.addToRear(new Integer(currentIndex*2+2));
               }
            }
               
            // fill oldlist
            currentIndex = currentRoot;
            templist.addToRear(new Integer(currentIndex));
            while (!templist.isEmpty()) {
               currentIndex = ((Integer)templist.removeFirst()).intValue();
               oldlist.addToRear(new Integer(currentIndex));
               if ((currentIndex*2+2) <= (Math.pow(2,height)-2)) {
                  templist.addToRear(new Integer(currentIndex*2+1));
                  templist.addToRear(new Integer(currentIndex*2+2));
               }
            }
         
            // do replacement
            oldIt = oldlist.iterator();
            newIt = newlist.iterator();
            while (newIt.hasNext()) {
               oldIndex = oldIt.next();
               newIndex = newIt.next();
               tree[oldIndex] = tree[newIndex];
               tree[newIndex] = null;
            }               
         }
         else
            tree[currentRoot] = null;
      }

   }  // method replace

   //================================================================
   //  Returns a string representation of the binary tree
   //================================================================
   public String toString() throws EmptyCollectionException 
   {
      String result = "";

      for (int i = 0; i <= maxIndex; i++)
         if (tree[i] != null)
            result += tree[i] .toString() + "\n";
      
      return result;
   }  // method toString

   //================================================================
   //  Returns a string representation of the binary tree
   //================================================================
   public String toString2() throws EmptyCollectionException 
   {
      String result = "";
      int counter = 1;
      int level = 1;
      int index = 1;

      if (isEmpty())
         return result;
      
      for (int i = 0; i < Math.pow(2, height-level)-1; i++)
         result += " ";
      result += tree[0] .toString();
      
      while (index <= maxIndex) {
         if (index == counter) {
            counter = counter*2 + 1;
            level++;
            result += "\n";
            for (int i = 0; i < Math.pow(2, height-level)-1; i++)
               result += " ";
         }
         if (tree[index] != null)
            result += tree[index] .toString();
         else
            result += " ";
         for (int i = 0; i < Math.pow(2, height-level+1)-1; i++)
            result += " ";
         index++;
      }
      
      return result;
   }  // method toString2

}  // class BinarySearchTree
