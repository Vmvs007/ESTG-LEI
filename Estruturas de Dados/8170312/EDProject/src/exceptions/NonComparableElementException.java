package exceptions;


public class NonComparableElementException extends RuntimeException
{
    //-----------------------------------------------------------------
    //  Sets up this exception with an appropriate message.
    //-----------------------------------------------------------------
    public NonComparableElementException (String collection)
    {
        super ("The " + collection + " requires comparable elements.");
    }
}