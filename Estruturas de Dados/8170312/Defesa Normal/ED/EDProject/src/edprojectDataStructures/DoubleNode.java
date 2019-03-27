package edprojectDataStructures;
public class DoubleNode<E>
{
    private DoubleNode<E> next;
    private E element;
    private DoubleNode<E> previous;

    //-----------------------------------------------------------------
    //  Creates an empty node.
    //-----------------------------------------------------------------
    public DoubleNode()
    {
        next = null;
        element = null;
        previous = null;
    }

    //-----------------------------------------------------------------
    //  Creates a node storing the specified element.
    //-----------------------------------------------------------------
    public DoubleNode (E elem)
    {
        next = null;
        element = elem;
        previous = null;
    }

    //-----------------------------------------------------------------
    //  Returns the node that follows this one.
    //-----------------------------------------------------------------
    public DoubleNode<E> getNext()
    {
        return next;
    }

    //-----------------------------------------------------------------
    //  Returns the node that precedes this one.
    //-----------------------------------------------------------------
    public DoubleNode<E> getPrevious()
    {
        return previous;
    }

    //-----------------------------------------------------------------
    //  Sets the node that follows this one.
    //-----------------------------------------------------------------
    public void setNext (DoubleNode<E> dnode)
    {
        next = dnode;
    }

    //-----------------------------------------------------------------
    //  Sets the node that follows this one.
    //-----------------------------------------------------------------
    public void setPrevious (DoubleNode<E> dnode)
    {
        previous = dnode;
    }


    //-----------------------------------------------------------------
    //  Returns the element stored in this node.
    //-----------------------------------------------------------------
    public E getElement()
    {
        return element;
    }

    //-----------------------------------------------------------------
    //  Sets the element stored in this node.
    //-----------------------------------------------------------------
    public void setElement (E elem)
    {
        element = elem;
    }


}


