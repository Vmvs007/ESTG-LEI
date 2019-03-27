package edprojectDataStructures;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedIterator<T> implements Iterator
{
    private int count;  // the number of elements in the collection
    private Node<T> current;  // the current position

    //-------------------------------------------------------------
    //  Sets up this iterator using the specified items.
    //-------------------------------------------------------------
    public LinkedIterator (Node<T> collection, int size)
    {
        current = collection;
        count = size;
    }

    //-------------------------------------------------------------
    //  Returns true if this iterator has at least one more element
    //  to deliver in the iteraion.
    //-------------------------------------------------------------
    public boolean hasNext()
    {
        return (current!= null);
    }

    //-------------------------------------------------------------
    //  Returns the next element in the iteration. If there are no
    //  more elements in this itertion, a NoSuchElementException is
    //  thrown.
    //-------------------------------------------------------------
    public T next()
    {
        if (! hasNext())
            throw new NoSuchElementException();

        T result = current.getElement();
        current = current.getNext();
        return result;
    }

    //-------------------------------------------------------------
    //  The remove operation is not supported.
    //-------------------------------------------------------------
    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
}

