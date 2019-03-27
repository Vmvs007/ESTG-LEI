/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

/**
 *
 * @author vmvs0
 */

import java.util.EmptyStackException;
import java.util.Iterator;

public class LinkedStack<T> implements StackADT<T>
{
  private int count;  // indicates the next open slot
  private LinearNode<T> top; 

  /*******************************************************************
    Creates an empty stack.
  *******************************************************************/
  public LinkedStack()
  {
    count = 0;
    top = null;
  }

  /*******************************************************************
    Adds the specified element to the top of this stack.
  *******************************************************************/
  public void push (T element)
  {
    LinearNode<T> temp = new LinearNode<T> (element);

    temp.setNext(top);
    top = temp;
    count++;
  }

  /*******************************************************************
    Removes the element at the top of this stack and returns a
    reference to it. Throws an EmptyStackException if the stack
    is empty.
  *******************************************************************/
  public T pop() throws EmptyStackException
  {
    if (isEmpty())
      throw new EmptyStackException();

    T result = top.getElement();
    top = top.getNext();
    count--;
 
    return result;
  }
   
  /*******************************************************************
    Returns a reference to the element at the top of this stack.
    The element is not removed from the stack.  Throws an
    EmptyStackException if the stack is empty.  
  *******************************************************************/
  public T peek() throws EmptyStackException
  {
    if (isEmpty())
      throw new EmptyStackException(); 

    return top.getElement();
  }

  /*******************************************************************
    Returns true if this stack is empty and false otherwise. 
  *******************************************************************/
  public boolean isEmpty()
  {
    return (count == 0);
  }
 
  /*******************************************************************
    Returns the number of elements in the stack.
  *******************************************************************/
  public int size()
  {
    return count;
  }

  /*******************************************************************
    Returns a string representation of this stack. 
  *******************************************************************/
  public String toString()
  {
    String result = "";
    LinearNode current = top;

    while (current != null)
    {
      result = result + (current.getElement()).toString() + "\n";
      current = current.getNext();
    }

    return result;
  }
}