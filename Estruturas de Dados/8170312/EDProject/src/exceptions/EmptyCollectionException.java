package exceptions;

public class EmptyCollectionException extends RuntimeException
{
    //-----------------------------------------------------------------
    //  Sets up this exception with an appropriate message.
    //-----------------------------------------------------------------
    public EmptyCollectionException (String collection)
    {
        super ("The " + collection + " is empty.");
    }
}
