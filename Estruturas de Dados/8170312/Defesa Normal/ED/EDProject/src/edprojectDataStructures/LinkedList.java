package edprojectDataStructures;

import java.util.Iterator;

public class LinkedList<T> implements ListADT<T>
{

    protected int count;
    protected Node<T> head, tail;

    //===========================================================
    //  Creates an empty list.
    //===========================================================
    public LinkedList() {
        count = 0;
        head = tail = null;
    }  // constructor List

    //===========================================================
    //  Removes the first element in the list and returns a reference
    //  to it.  Throws an EmptyListException if the list is empty.
    //===========================================================
    public T removeFirst() throws EmptyCollectionException {

        if (isEmpty())
            throw new EmptyCollectionException ("List");

        Node<T> result = head;
        head = head.getNext();
        if (head == null)
            tail = null;
        count--;

        return result.getElement();

    } // method removeFirst

    //===========================================================
    //  Removes the last element in the list and returns a reference
    //  to it.  Throws an EmptyListException if the list is empty.
    //===========================================================
    public T removeLast() throws EmptyCollectionException {

        if (isEmpty())
            throw new EmptyCollectionException ("List");

        Node<T> previous = null;
        Node<T> current = head;

        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }

        Node<T> result = tail;
        tail = previous;
        if (tail == null)
            head = null;
        else
            tail.setNext(null);
        count--;

        return result.getElement();

    } // method removeLast

    //===========================================================
    //  Removes the first instance of the specified element from the
    //  list if it is found in the list and returns a reference to it.
    //  Throws an EmptyListException if the list is empty.  Throws a
    //  NoSuchElementException if the specified element is not found
    //  on the list.
    //===========================================================
    public T remove (T targetElement) throws
            EmptyCollectionException, ElementNotFoundException
    {

        if (isEmpty())
            throw new EmptyCollectionException ("List");

        boolean found = false;

        Node<T> previous = null;
        Node<T> current = head;

        while (current != null && !found)
            if (targetElement.equals (current.getElement()))
                found = true;
            else {
                previous = current;
                current = current.getNext();
            }

        if (!found)
            throw new ElementNotFoundException ("List");

        if (size() == 1)
            head = tail = null;
        else if (current.equals (head))
            head = current.getNext();
        else if (current.equals (tail))
        {
            tail = previous;
            tail.setNext(null);
        }
        else
            previous.setNext(current.getNext());

        count--;

        return current.getElement();

    }  // method remove

    //===========================================================
    //  Finds the first instance of the specified element from the
    //  list if it is found in the list and returns true.
    //  Returns false otherwise
    //===========================================================
    public boolean contains (T targetElement) throws
            EmptyCollectionException
    {

        if (isEmpty())
            throw new EmptyCollectionException ("List");

        boolean found = false;
        Object result;

        Node<T> current = head;

        while (current != null && !found)
            if (targetElement.equals (current.getElement()))
                found = true;
            else {
                current = current.getNext();
            }

        return found;

    }  // method contains


    //===========================================================
    //  Returns true if the list is empty and false otherwise
    //===========================================================
    public boolean isEmpty() {
        return (count == 0);
    }  // method isEmpty

    //===========================================================
    //  Returns the number of elements in the list.
    //===========================================================
    public int size() {
        return count;
    }  // method size



    //===========================================================
    //  Returns a string representation of the list.
    //===========================================================
    public String toString() {

        Node<T> current = head;
        String result = "";

        while (current != null) {
            result = result + (current.getElement()).toString() + "\n";
            current = current.getNext();
        }

        return result;

    } // method toString

    //===========================================================
    //  Returns ...
    //===========================================================
    public Iterator<T> iterator() {
        return new LinkedIterator<T>(head, count);
    }  // method elements

    //===========================================================
    //  Returns the first element of the list.
    //===========================================================
    public T first() {
        return head.getElement();
    }  // method firstElement

    //===========================================================
    //  Returns the last element of the list.
    //===========================================================
    public T last() {
        return tail.getElement();
    }  // method lastElement

}  // class LinkedList


